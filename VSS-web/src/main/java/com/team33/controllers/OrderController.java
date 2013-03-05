/*
 *This controls the access and process of video orders 
 * made by an active account logged in to a web session
 */
package com.team33.controllers;

import com.team33.entities.Orders;
import com.team33.form.OrderRequest;
import com.team33.services.OrderServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

/**
 * This is the controller for the order feature
 *
 * @author Mark
 */
@Controller
public class OrderController{

    @Autowired
    private OrderServiceImpl orderServiceImpl;  

    public void setOrderServiceImpl(OrderServiceImpl orderServiceImpl) {
        this.orderServiceImpl = orderServiceImpl;
    }
    
    public OrderServiceImpl getOrderServiceImpl(){
        return this.orderServiceImpl;
    }
    
    @RequestMapping(value = "order/create", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute("orderRequest")OrderRequest orderRequest, BindingResult result) {
        System.out.println("Video Title: " + orderRequest.getVideotitle());
        return "redirect:show.htm";
    }
    
    @RequestMapping("/order/new")
    public ModelAndView newOrder() {
        return new ModelAndView("newOrder", "command", new OrderRequest());
    }
    
    @RequestMapping("/order/show")
    public ModelAndView showOrder() {
        return new ModelAndView("showOrder");
    }
}
