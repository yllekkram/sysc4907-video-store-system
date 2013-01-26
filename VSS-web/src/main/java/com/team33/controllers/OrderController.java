/*
 *This controls the access and process of video orders 
 * made by an active account logged in to a web session
 */
package com.team33.controllers;

import com.team33.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.portlet.mvc.AbstractController;

/**
 * This is the controller for the order feature
 *
 * @author Samual
 */
@Controller
public class OrderController extends AbstractController {

    @Autowired
    private OrderService orderService;
    @Autowired
    private String commandName;
    @Autowired
    private Class commandClass;

    public void setOrderServiceImpl(OrderService orderService) {
        this.orderService = orderService;
    }
    
    public void setCommandName(String commandName) {
        this.commandName = commandName;
    }

    public void setCommandClass(Class commandClass) {
        this.commandClass = commandClass;
    }
    
    //The get orders method should be invoked here
    @RequestMapping(method = RequestMethod.GET)
    public String order() {
        return "orderVideoView";
    }
}
