/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.entities.Account;
import com.team33.entities.Orders;
import com.team33.entities.VideoInfo;
import com.team33.services.AccountService;
import com.team33.services.VideoAccessService;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.ModelAttribute;
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
    
    public void setVideoAccessService(VideoAccessService service){
        this.videoAccessService = service;
    }
    
    public void setAccountService(AccountService service){
        this.accountService = service;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String displayAccountInfo(Map<String, Object> map, RedirectAttributes redirect/*, @ModelAttribute("token")String token*/){
        
        String token = "12345"; // Debug UUID
        int uuid = Integer.parseInt(token);        
        
//        
//        List<VideoInfo> videoList = null;
//        try{
//            videoList = videoAccessService.getVideoInfoList(uuid);
//        }catch(DataAccessException e){
//            redirect.addFlashAttribute("exception", e);
//            return "redirect:/myAccountView.htm";
//        }catch(AccountNotActivatedException e){
//            redirect.addFlashAttribute("exception", e);
//            return "redirect:/myAccountView.htm";
//        }
//        
//        map.put("videoList", videoList);
        
        try{
            //Account account = this.accountService.getAccount(uuid);
            Account account = new Account();
            account.setName("Jim");
            map.put("Name", account.getName());
            map.put("Orders", (account.getOrdersCollection() == null ? new ArrayList<Orders>() : account.getOrdersCollection()));
        }catch(DataAccessException e){
            redirect.addFlashAttribute("exception", e);
            return "redirect:/myAccountView.htm";
        }                
        return "/myAccountView";
    }
}
