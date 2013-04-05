/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.entities.Account;
import com.team33.entities.Orders;
import com.team33.services.AccountService;
import com.team33.services.OrderService;
import com.team33.services.VideoAccessService;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Daywalker
 */
@Controller
@RequestMapping(value = "/myAccountView.htm")
public class MyAccountController {
    
    @Autowired
    private VideoAccessService videoAccessService;
    
    @Autowired
    private AccountService accountService;
    
    @Autowired
    private OrderService orderService;
    
    public void setVideoAccessService(VideoAccessService service){
        this.videoAccessService = service;
    }
    
    public void setAccountService(AccountService service){
        this.accountService = service;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String displayAccountInfo(@RequestHeader(value="referer", required=false)final String referer, final RedirectAttributes redirectAttributes, Map<String, Object> map, HttpSession session){
        String refererOrHome = StringUtils.hasText(referer) ? referer : "/";
        Integer token = (Integer)session.getAttribute(LoginController.ACCOUNT_ATTRIBUTE);
        System.out.println("TOKEN : " + token);
        
        if (token == null){
            return "redirect:/registerAccountView.htm";
        }
        
        int uuid = token.intValue();        
        
        try{
            Account account = this.accountService.getAccount(uuid);
            List<Orders> orders = this.orderService.getOrders(uuid);

            if (account != null){
                map.put("Name", account.getName());
                map.put("Orders", orders);
            }
        }catch(DataAccessException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getLocalizedMessage());
            return "redirect:" + refererOrHome;
        }catch(AccountNotActivatedException e){
            redirectAttributes.addFlashAttribute("errorMessage", e.getLocalizedMessage());
            return "redirect:" + refererOrHome;
        }            
        return "/myAccountView";
    }
}
