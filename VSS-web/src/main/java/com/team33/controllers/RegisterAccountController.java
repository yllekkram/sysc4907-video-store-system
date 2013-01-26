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
import com.team33.services.AccountServiceImpl;
import com.team33.services.exception.RegistrationException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindException;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping(value = "/registerAccountView.htm")
public class RegisterAccountController {

    @Autowired
    private AccountServiceImpl accountServiceImpl;
    @Autowired
    private String successView;
    @Autowired
    private String commandName;
    @Autowired
    private Class commandClass;

    public void setAccountServiceImpl(AccountServiceImpl service) {
        this.accountServiceImpl = service;
    }

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void setCommandClass(Class commandClass) {
        this.commandClass = commandClass;
    }

    public AccountServiceImpl getAccountServiceImpl() {
        return this.accountServiceImpl;
    }

    public String getSuccessView() {
        return this.successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }
/*used to grab the JSP , this can be source of  404 errors if not named correctly*/
    @RequestMapping(method = RequestMethod.GET)
    public String register() {
        return "registerAccountView";
    }

    /*This method handles the JSP form action request with method post*/
    @RequestMapping(method = RequestMethod.POST)
    public String handleLogin(@RequestParam String username,
            @RequestParam String password, RedirectAttributes redirect, HttpSession session)
            throws RegistrationException {
        try {
            if (username == null || username.equals("")
                    || password == null || password.equals("")) {
                throw new RegistrationException("Invalid registration info!");
            }
            //if username already exists in system throw exception
            if (this.getAccountServiceImpl().getAccountDaoImpl().getAccount(username) != null) {
                throw new RegistrationException("Username already exists, please try another.");
            }
             //save password. id is autoincremental so should be fine, then save the account 
            Account acc = new Account();
            acc.setName(username);
            acc.setPassword(password);
            acc.setActivated(Boolean.TRUE);
            this.getAccountServiceImpl().getAccountDaoImpl().saveAccount(acc);
            return this.getSuccessView();
        } catch (RegistrationException re) {
            redirect.addFlashAttribute("exception", re);
            return "redirect:/registerAccountView.htm";
        } 
    }

    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors) throws ServletException, IOException{

        ModelAndView modelAndView = null;

        Account account = (Account) command;

        try {
            Account a = new Account();
                       
            a.setName(account.getName());
            a.setPassword(account.getPassword());
            //auto activate account for now and set new id
            a.setActivated(Boolean.TRUE);

            modelAndView = new ModelAndView(getSuccessView());
        } catch (Exception e) {
            e.printStackTrace();
        } 
        return modelAndView;
    }
}
