/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

/**
 *
 * @author Samual
 */
@Controller
public class IndexController {
    /**
     *
     * @return
     */
    @RequestMapping(value = "/index")
    public String indexPage(@ModelAttribute(value="errorMessage")String errorMessage, Map<String,Object>model) {
        model.put("errorMessage", errorMessage);
        return "index";
    }
    /**
     *
     * @param redirect
     * @param session
     * @return
     */
    @RequestMapping(method = RequestMethod.POST)
    public String handleRequest(RedirectAttributes redirect,HttpSession session){
             return "redirect:"+redirect;
    }    
}
