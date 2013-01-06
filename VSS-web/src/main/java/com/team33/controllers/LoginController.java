/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.entities.Account;
import com.team33.services.AccountService;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.AccountNotFoundException;
import com.team33.services.exception.AuthenticationException;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Samual Martelli
 */
@Controller
@RequestMapping(value = "/login")
public class LoginController {
     public static final String ACCOUNT_ATTRIBUTE = "account";

    @Autowired
    private AccountService accountService;
    
    public void setAccountService(AccountService service) {
        this.accountService = service;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String login() {
        return "login";
    }
    
    @RequestMapping(method = RequestMethod.POST)
    public String handleLogin(@RequestParam String username, 
    @RequestParam String password, RedirectAttributes redirect,HttpSession session)
    throws AuthenticationException {
        try {
             Account account = this.accountService.loginAccount(username, password);
             session.setAttribute(ACCOUNT_ATTRIBUTE, account);
             return "redirect:/index.htm";
         } catch (AuthenticationException ae) {
             redirect.addFlashAttribute("exception", ae);
             return "redirect:/login";
         } catch (AccountNotActivatedException ae) {
             redirect.addFlashAttribute("exception", ae);
             return "redirect:/login";
         } catch (AccountNotFoundException ae) {
             redirect.addFlashAttribute("exception", ae);
             return "redirect:/login";
         }
    }
}
