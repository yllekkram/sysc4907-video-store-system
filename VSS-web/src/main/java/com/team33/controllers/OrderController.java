/*
 *This controls the access and process of video orders 
 * made by an active account logged in to a web session
 */
package com.team33.controllers;

import com.team33.services.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.mvc.AbstractController;

/**
 * This is the controller for the order feature
 * @author Samual
 */
@Controller
public class OrderController extends AbstractController {
    @Autowired
    private OrderService orderService;
    
     public void setOrderService(OrderService orderService){
        this.orderService = orderService;
    }
}