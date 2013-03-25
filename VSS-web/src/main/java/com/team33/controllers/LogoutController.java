/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Caleb
 */
@Controller
@RequestMapping(value = "/logout.htm")
public class LogoutController {
    
    @RequestMapping(method = RequestMethod.POST)
    public String logout(HttpSession session){
        session.removeAttribute(LoginController.ACCOUNT_ATTRIBUTE);
        return "redirect:/index.htm";
    }
}
