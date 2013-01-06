/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.entities.Account;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.AccountNotFoundException;
import com.team33.services.exception.AuthenticationException;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.portlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author LaFamiglia
 */
@Controller
public class IndexController {
    @RequestMapping(value = "/index")
    public ModelAndView indexPage() {
        return new ModelAndView("index");
    }
    @RequestMapping(method = RequestMethod.POST)
    public String handleRequest(RedirectAttributes redirect,HttpSession session){
             return "redirect:"+redirect;
    }    
}
