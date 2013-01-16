/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.entities.Account;
import com.team33.services.AccountServiceImpl;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.AccountNotFoundException;
import com.team33.services.exception.AuthenticationException;
import com.team33.services.exception.LoginException;
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

/**
 *
 * @author Samual Martelli
 */
@Controller
@RequestMapping(value = "/login.htm")
public class LoginController {

    public static final String ACCOUNT_ATTRIBUTE = "account";
    // tells the application context to inject an instance of AccountServiceImpl here
    @Autowired
    private AccountServiceImpl accountServiceImpl;
    @Autowired
    private String successView;
    @Autowired
    private String commandName;
    @Autowired
    private Class commandClass;

    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void setCommandClass(Class commandClass) {
        this.commandClass = commandClass;
    }

    public AccountServiceImpl getAccountService() {
        return this.accountServiceImpl;
    }

    public void setAccountServiceImpl(AccountServiceImpl service) {
        this.accountServiceImpl = service;
    }

    public String getSuccessView() {
        return this.successView;
    }

    public void setSuccessView(String successView) {
        this.successView = successView;
    }

    @RequestMapping(method = RequestMethod.GET)
    public String login() {
        return "login";
    }

    public ModelAndView onSubmit(HttpServletRequest request,
            HttpServletResponse response, Object command, BindException errors) throws ServletException, IOException {

        ModelAndView modelAndView = null;
        System.out.println("Signup onSubmit get called");

        Account account = (Account) command;

        try {
            Account a = new Account();
            a.setName(account.getName());
            a.setPassword(account.getPassword());


            modelAndView = new ModelAndView(getSuccessView());
        } catch (Exception e) {
            e.printStackTrace();
        }

        return modelAndView;
    }

    @RequestMapping(method = RequestMethod.POST)
    public String handleLogin(@RequestParam String username,
            @RequestParam String password, RedirectAttributes redirect, HttpSession session)
            throws AuthenticationException, LoginException {
        try {
            if (username == null || username.equals("")
                    || password == null || password.equals("")) {
                throw new LoginException("Invalid login info!");
            }
            Account account = this.accountServiceImpl.loginAccount(username, password);
            session.setAttribute(ACCOUNT_ATTRIBUTE, account);
            return this.getSuccessView();
        } catch (LoginException le) {
            redirect.addFlashAttribute("exception", le);
            return "redirect:/login.htm";
        } catch (AuthenticationException ae) {
            redirect.addFlashAttribute("exception", ae);
            return "redirect:/login.htm";
        } catch (AccountNotActivatedException ae) {
            redirect.addFlashAttribute("exception", ae);
            return "redirect:/login.htm";
        } catch (AccountNotFoundException ae) {
            redirect.addFlashAttribute("exception", ae);
            return "redirect:/login.htm";
        }
    }
}
