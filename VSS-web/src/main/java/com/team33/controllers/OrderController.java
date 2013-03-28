/*
 *This controls the access and process of video orders 
 * made by an active account logged in to a web session
 */
package com.team33.controllers;

import com.team33.entities.Orders;
import com.team33.form.OrderRequest;
import com.team33.services.OrderService;
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
    private OrderService orderService;  

    public void setOrderService(OrderService orderService) {
        this.orderService = orderService;
    }
    
    public OrderService getOrderService(){
        return this.orderService;
    }
    
    @RequestMapping(value = "order/create", method = RequestMethod.POST)
    public String createOrder(@ModelAttribute("orderRequest")OrderRequest orderRequest, BindingResult result) {
        System.out.println("Video Title: " + orderRequest.getVideotitle() +
                " Order Type: " + orderRequest.getOrderType() +
                " Video ID: " + orderRequest.getVideoid());
        return "redirect:show.htm?order=" + orderRequest.getVideotitle();
    }
    
    @RequestMapping("/order/new")
    public ModelAndView newOrder(@RequestParam(value="videoid")Integer videoid) {
        OrderRequest orderRequest = new OrderRequest();
        orderRequest.setVideoid(videoid);
        return new ModelAndView("newOrder", "command", orderRequest);
    }
    
    @RequestMapping("/order/show")
    public ModelAndView showOrder(@RequestParam("order")String order) {
        return new ModelAndView("showOrder", "order", order);
    }
}
