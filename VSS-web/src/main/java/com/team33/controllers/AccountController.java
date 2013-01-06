/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

/**
 *
 * @author Samual
 */
  import java.util.Map;
 
import com.team33.entities.Account;
import com.team33.services.AccountService;
 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.mvc.AbstractController;

@Controller
public class AccountController extends AbstractController{

    @Autowired
    private AccountService accountService;
    
    public void setAccountService(AccountService service) {
        this.accountService = service;
    }
 
    @RequestMapping("/index")
    public String getAccounts(Map<String, Object> map) {
 
        map.put("account", new Account());
        map.put("accountList", accountService.getAccounts());
 
        return "account";
    }
 
    @RequestMapping(value = "/registerAccount", method = RequestMethod.POST)
    public String saveAccount(@ModelAttribute("account")
    Account account, BindingResult result) {
 
        accountService.registerAccount(account);
 
        return "redirect:/index";
    }
 
    @RequestMapping("/delete/{accountId}")
    public String removeAccount(@PathVariable("accountId")
    Long accountId) {
 
        accountService.removeAccount(accountId);
 
        return "redirect:/index";
    }
}

