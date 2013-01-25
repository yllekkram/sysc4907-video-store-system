/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

/**
 *
 * @author Samual
 */
import com.team33.entities.Account;
import com.team33.services.AccountService;
import java.util.Map; 
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class AccountController{
    
    protected Log logger = LogFactory.getLog(this.getClass());

    @Autowired
    private AccountService accountService;
    
    public void setAccountService(AccountService service) {
        this.accountService = service;
    }
 
    @RequestMapping(value = "/registerAccountView", method = RequestMethod.GET)
    public String getAccounts(Map<String, Object> map) {
        logger.info("------------------- Get Account ---------------------------");
        map.put("account", new Account());
        map.put("accountList", accountService.getAccounts());
 
        return "/registerAccountView";
    }
 
    @RequestMapping(value = "/registerAccountView/add", method = RequestMethod.POST)
    public String saveAccount(@ModelAttribute("account")Account account, BindingResult result) {
        logger.info("------------------- Save Account ---------------------------\n" + account.toString());
        accountService.registerAccount(account);
 
        return "/registerAccountView";
    }
 
    @RequestMapping("/delete/{accountId}")
    public String removeAccount(@PathVariable("accountId")
    Integer accountId) {
        accountService.removeAccount(accountId);
 
        return "redirect:/registerAccountView";
    }
}

