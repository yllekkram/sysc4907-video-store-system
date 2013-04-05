/*
 *This controls the access and process of video orders 
 * made by an active account logged in to a web session
 */
package com.team33.controllers;

import com.team33.entities.Account;
import com.team33.entities.Orders;
import com.team33.form.OrderRequest;
import com.team33.services.AccountService;
import com.team33.services.BrowseService;
import com.team33.services.OrderService;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
import com.team33.services.exception.InsufficientFundsException;
import com.team33.services.exception.PaymentException;
import java.util.Calendar;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 * This is the controller for the order feature
 *
 * @author Mark
 */
@Controller
public class OrderController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private BrowseService browseService;
    @Autowired
    private AccountService accountService;

    @RequestMapping(value = "/order/create", method = RequestMethod.POST)
    public String createOrder(
            @RequestHeader(value="referer", required=false)final String referer,
            @ModelAttribute("orderRequest") OrderRequest orderRequest,
            BindingResult result,
            HttpSession session,
            final RedirectAttributes redirectAttributes)
    {
        String refererOrHome = StringUtils.hasText(referer) ? referer : "/";
        ShoppingCart cart = getCart(session);
        Integer loginToken = orderRequest.getLoginToken();
        if (loginToken == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "No User Logged In");
            return "redirect:" + refererOrHome;
        }

        if (cart == null) {
            return "redirect:" + refererOrHome;
        }
        Orders newOrder = new Orders(accountService.getAccountByLoginToken(loginToken).getId());
        // This should (hopefully) force the id to be generated
        orderService.saveOrder(newOrder);
        
        for (Integer videoID : cart.getPurchaseList()) {
            try {
                orderService.addPurchase(videoID, newOrder, loginToken);
            } catch (AccountNotActivatedException ane) {
                redirectAttributes.addFlashAttribute("errorMessage", ane.getMessage());
                return "redirect:" + refererOrHome;
            }
        }

        for (Integer videoID : cart.getRentedList()) {
            try {
                Calendar c = Calendar.getInstance();
                // The rental period arbitrary
                c.add(Calendar.DAY_OF_MONTH, 5);
                orderService.addRental(videoID, newOrder, loginToken, c.getTime());
            } catch (AccountNotActivatedException ane) {
                redirectAttributes.addFlashAttribute("errorMessage", ane.getMessage());
                return "redirect:" + refererOrHome;
            }
        }

        try {
            orderService.confirmPayment(newOrder, loginToken, new Integer(orderRequest.getCreditCardVerification()), orderRequest.getTotalPrice());
        } catch (AccountNotActivatedException ane) {
                redirectAttributes.addFlashAttribute("errorMessage", ane.getMessage());
            return "redirect:" + refererOrHome;
        } catch (PaymentException pe) {
                redirectAttributes.addFlashAttribute("errorMessage", pe.getMessage());
            return "redirect:" + refererOrHome;
        } catch (InsufficientFundsException ife) {
                redirectAttributes.addFlashAttribute("errorMessage", ife.getMessage());
            return "redirect:" + refererOrHome;
        }
        
        orderService.saveOrUpdateOrder(newOrder);
        
        clearCart(session);
        
        return "redirect:show.htm?orderID=" + newOrder.getOrdersPK().getId();
    }

    @RequestMapping("/order/new")
    public String newOrder(
            @RequestHeader(value="referer", required=false)final String referer,
            Map<String,Object>model,
            HttpSession session)
    {
        String refererOrHome = StringUtils.hasText(referer) ? referer : "/";
        Integer totalPrice = 0;

        Integer tokenID = (Integer) session.getAttribute(LoginController.ACCOUNT_ATTRIBUTE);

        ShoppingCart cart = getCart(session);
        
        if (cart == null) {
            return "redirect:" + refererOrHome;
        }
        
        for (Integer videoID : cart.getPurchaseList()) {
            totalPrice += browseService.displayVideoDetails(videoID).getPurchasePrice();
        }
        
        for (Integer videoID : cart.getRentedList()) {
            totalPrice += browseService.displayVideoDetails(videoID).getRentalPrice();
        }

        Account a = null;
        if (tokenID != null) {
            a = accountService.getAccountByLoginToken(tokenID);
        }

        if (a == null) {
            a = new Account();
            a.setName("No User Logged In");
        }
        model.put("account", a);
        model.put("totalPrice", totalPrice);
        model.put("uuid", tokenID);

        return "newOrder";
    }

    @RequestMapping("/order/show")
    public String showOrder(
            @RequestHeader(value="referer", required=false)final String referer,
            @RequestParam("orderID") Integer orderID,
            Map<String,Object> model,
            HttpSession session,
            final RedirectAttributes redirectAttributes)
    {
        String refererOrHome = StringUtils.hasText(referer) ? referer : "/";
        Integer loginToken = (Integer)session.getAttribute(LoginController.ACCOUNT_ATTRIBUTE);
        
        if (loginToken == null) {
            redirectAttributes.addFlashAttribute("errorMessage", "You must be logged in to view an order.");
            return "redirect:" + refererOrHome;
        }
        
        Orders order;
        try {
            order = orderService.getOrder(orderID, loginToken);
        } catch (DataAccessException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:" + refererOrHome;
        } catch (AccountNotActivatedException ex) {
            Logger.getLogger(OrderController.class.getName()).log(Level.SEVERE, null, ex);
            redirectAttributes.addFlashAttribute("errorMessage", ex.getMessage());
            return "redirect:" + refererOrHome;
        }
        
        if (order == null) {
            model.put("errorMessage", "The order is null.");
        }
        
        model.put("order", order);
        model.put("orderID", orderID);
        
        return "showOrder";
    }

    private ShoppingCart getCart(HttpSession session) {
        return (ShoppingCart)session.getAttribute(ShoppingCartController.SHOPPING_CART_COOKIE_NAME);
    }
    
    private void clearCart(HttpSession session) {
        session.removeAttribute(ShoppingCartController.SHOPPING_CART_COOKIE_NAME);
    }
}
