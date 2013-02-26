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

/**
 *
 * @author LaFamiglia
 */
@Controller
public class AccountController extends AbstractController{

    @Autowired
    private AccountService accountService;
    
    /**
     *
     * @param service
     */
    public void setAccountService(AccountService service) {
        this.accountService = service;
    }
 
    /**
     *
     * @param map
     * @return
     */
    @RequestMapping("/index")
    public String getAccounts(Map<String, Object> map) {
 
        map.put("account", new Account());
        map.put("accountList", accountService.getAccounts());
 
        return "account";
    }
 
    /**
     *
     * @param account
     * @param result
     * @return
     */
    @RequestMapping(value = "/registerAccount", method = RequestMethod.POST)
    public String saveAccount(@ModelAttribute("account")
    Account account, BindingResult result) {
        
        //accountService.registerAccount(account);
 
        return "redirect:/index";
    }
 
    /**
     *
     * @param accountId
     * @return
     */
    @RequestMapping("/delete/{accountId}")
    public String removeAccount(@PathVariable("accountId")
    Integer accountId) {
 
        accountService.removeAccount(accountId);
 
        return "redirect:/index";
    }
}

