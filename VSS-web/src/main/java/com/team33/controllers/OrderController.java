/*
 *This controls the access and process of video orders 
 * made by an active account logged in to a web session
 */
package com.team33.controllers;

import com.team33.entities.Orders;
import com.team33.entities.Purchase;
import com.team33.entities.VideoInfo;
import com.team33.form.OrderRequest;
import com.team33.services.AccountService;
import com.team33.services.BrowseService;
import com.team33.services.OrderService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

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

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }

    public OrderService getOrderService() {
        return this.orderService;
    }

    @RequestMapping(value = "order/create", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute("orderRequest") OrderRequest orderRequest, BindingResult result) {
        return "redirect:show.htm"; //?order=" + orderRequest.getVideotitle();
    }

    @RequestMapping("/order/new")
    public ModelAndView newOrder(HttpServletRequest request) {
        OrderRequest or = new OrderRequest();
        
        Integer tokenID = (Integer) request.getSession().getAttribute(LoginController.ACCOUNT_ATTRIBUTE);

        if (tokenID != null) {
            Integer totalPrice = 0;

            Cookie cartCookie = null;
            Cookie cookies[] = request.getCookies();
            for (Cookie c : cookies) {
                if (c.getName().equals(ShoppingCartController.SHOPPING_CART_COOKIE_NAME)) {
                    cartCookie = c;
                    break;
                }
            }

            if (cartCookie != null) {
                ShoppingCart cart = ShoppingCart.fromString(cartCookie.getValue());
                for (Integer pid : cart.getPurchaseList()) {
                    totalPrice += browseService.displayVideoDetails(pid).getPurchasePrice();
                }
                for (Integer rid : cart.getRentedList()) {
                    totalPrice += browseService.displayVideoDetails(rid).getRentalPrice();
                }
            }

            or.setTotalPrice(totalPrice);


            or.setAccount(accountService.getAccountByLoginToken(tokenID));
        }

        return new ModelAndView("newOrder", "command", or);
    }

    @RequestMapping("/order/show")
    public ModelAndView showOrder(@RequestParam("order") String order) {
        return new ModelAndView("showOrder", "order", order);
    }
}
