/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.entities.User;
import com.team33.services.UserService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Mark
 */
@Controller
public class UserController {
    @Autowired
    private UserService userService;
    
    @RequestMapping("/users")
    public String listUsers(Map<String,Object> map) {
        map.put("userList", userService.listUsers());
        
        return "users";
    }
    
    @RequestMapping(value = "/user/add", method = RequestMethod.POST)
    public String addUser(@ModelAttribute("user")User user, BindingResult result) {
        userService.addUser(user);
        
        return "redirect:/users";
    }
    
    @RequestMapping("/user/{userID}/delete")
    public String deleteUser(@PathVariable("userID")Integer contactID) {
        userService.removeUser(contactID);
        
        return "redirect:/users";
    }
    
    @RequestMapping(value = "/users/{userID}/update", method = RequestMethod.POST)
    public String updateUser(@ModelAttribute("user")User user, BindingResult result) {
        userService.updateUser(user);
        
        return "redirect:/users";
    }
}
