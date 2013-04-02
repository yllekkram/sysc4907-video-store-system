/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.controllers.ShoppingCart.ShoppingCartKey;
import com.team33.entities.VideoInfo;
import com.team33.services.BrowseService;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Caleb
 */
@Controller
public class ShoppingCartController {
    
    public static final String SHOPPING_CART_COOKIE_NAME = "VIDEO_LIST";
    
    @Autowired
    private BrowseService browseService;
    
    public void setBrowseService(BrowseService service){
        this.browseService = service;
    }
    
    @RequestMapping(value = "/shoppingCartView.htm", method = RequestMethod.GET)
    public String viewCart(Map<String, Object> map, HttpSession session){
        ArrayList<VideoInfo> info = new ArrayList<VideoInfo>();
        ShoppingCart cart = (ShoppingCart)session.getAttribute(SHOPPING_CART_COOKIE_NAME);
        if (cart == null){
            map.put("VideoList", info);
            return "/shoppingCartView"; 
        }
        
        List<ShoppingCart.ShoppingCartKey> shoppingCartinfo = cart.getShoppingCartInfo();
        for(ShoppingCart.ShoppingCartKey i : shoppingCartinfo){
            info.add(this.browseService.displayVideoDetails(i.getVideoId()));
        }
        
        ArrayList<ShoppingCartControllerDataFrame> frame = new ArrayList<ShoppingCartControllerDataFrame>();
        int totalCost = 0;   
        for (int i = 0; i < shoppingCartinfo.size(); i++){
            frame.add(new ShoppingCartControllerDataFrame(shoppingCartinfo.get(i), info.get(i)));
            if (shoppingCartinfo.get(i).getIsRented()){
                totalCost += info.get(i).getRentalPrice();
            }else{
                totalCost += info.get(i).getPurchasePrice();
            }
        }
        
        map.put("Frame", frame);
        map.put("TotalCost", totalCost);
        
        return "/shoppingCartView";
    }
    
    @RequestMapping(value = "/shoppingCartView/purchase/delete/{videoId}", method = RequestMethod.POST)
    public String deletePurchaseOrder(@PathVariable("videoId")String videoId, HttpSession session){
        ShoppingCart cart = (ShoppingCart)session.getAttribute(SHOPPING_CART_COOKIE_NAME);
        if (cart == null){
            return "redirect:/shoppingCartView.htm";
        }
        cart.removeFromCart(Integer.parseInt(videoId), false);
        session.setAttribute(SHOPPING_CART_COOKIE_NAME, cart);
        return "redirect:/shoppingCartView.htm";
    }
    
    @RequestMapping(value = "/shoppingCartView/rental/delete/{videoId}", method = RequestMethod.POST)
    public String deleteRentalOrder(@PathVariable("videoId")String videoId, HttpSession session){
        ShoppingCart cart = (ShoppingCart)session.getAttribute(SHOPPING_CART_COOKIE_NAME);
        if (cart == null){
            return "redirect:/shoppingCartView.htm";
        }
        cart.removeFromCart(Integer.parseInt(videoId), true);
        session.setAttribute(SHOPPING_CART_COOKIE_NAME, cart);
        return "redirect:/shoppingCartView.htm";
    }
    
    @RequestMapping(value = "/shoppingCartView/purchase/{videoId}", method = RequestMethod.GET)
    public String addPurchaseToCart(@PathVariable("videoId")String videoId, HttpSession session){
        System.out.println("----------------------- Add Purchase To Cart ----------------------------------");
        ShoppingCart cart = (ShoppingCart)session.getAttribute(SHOPPING_CART_COOKIE_NAME);
        if (cart == null){
            cart = new ShoppingCart();
        }
        cart.addToCart(Integer.parseInt(videoId), false);
        session.setAttribute(SHOPPING_CART_COOKIE_NAME, cart);
        return "redirect:/browseView/" + videoId + ".htm";
    }
    
    @RequestMapping(value = "/shoppingCartView/rental/{videoId}", method = RequestMethod.GET)
    public String addrentalToCart(@PathVariable("videoId")String videoId, HttpSession session){
        System.out.println("----------------------- Add Rental To Cart ----------------------------------");
        ShoppingCart cart = (ShoppingCart)session.getAttribute(SHOPPING_CART_COOKIE_NAME);
        if (cart == null){
            cart = new ShoppingCart();
        }
        cart.addToCart(Integer.parseInt(videoId), true);
        session.setAttribute(SHOPPING_CART_COOKIE_NAME, cart);
        return "redirect:/browseView/" + videoId + ".htm";
    }
    
    public class ShoppingCartControllerDataFrame{
        private ShoppingCart.ShoppingCartKey key;
        private VideoInfo videoInfo;
        
        public ShoppingCartControllerDataFrame(ShoppingCart.ShoppingCartKey key, VideoInfo videoInfo){
            this.key = key;
            this.videoInfo = videoInfo;
        }

        public ShoppingCartKey getKey() {
            return key;
        }

        public VideoInfo getVideoInfo() {
            return videoInfo;
        }
    }
}
