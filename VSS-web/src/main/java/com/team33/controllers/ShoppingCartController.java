/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.entities.VideoInfo;
import com.team33.services.BrowseService;
import com.team33.services.exception.DataAccessException;
import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

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
    public String viewCart(Map<String, Object> map, HttpServletRequest request){
        ArrayList<VideoInfo> info = new ArrayList<VideoInfo>();
        Cookie videoCookie = getCartCookie(request);
        if (videoCookie == null){
            map.put("VideoList", info);
            return "/shoppingCartView";
        }
        ShoppingCart cart = ShoppingCart.fromString(videoCookie.getValue());
        Set<Integer> videoList = cart.getVideoList();
        for(Integer i : videoList){
            info.add(this.browseService.displayVideoDetails(i.intValue()));
        }
        map.put("VideoList", info);
        
        return "/shoppingCartView";
    }
    
    @RequestMapping(value = "/shoppingCartView.htm", method = RequestMethod.POST)
    public String deleteOrder(@RequestParam("id")String id, HttpServletResponse response, HttpServletRequest request){
        Cookie videoList = getCartCookie(request);
        String cartString = "";
        if (videoList != null){
            ShoppingCart cart = ShoppingCart.fromString(videoList.getValue());
            cart.removeFromCart(Integer.parseInt(id));
            cartString = cart.toString();
        }else{
            return "redirect:/shoppingCartView.htm";
        }
        Cookie cookie = new Cookie(SHOPPING_CART_COOKIE_NAME, cartString);
        cookie.setPath("http://localhost:8080/cart");
        response.addCookie(cookie);
        return "redirect:/shoppingCartView.htm";
    }
    
    @RequestMapping(value = "/shoppingCartView.htm/purchase/${videoId}", method = RequestMethod.POST)
    public String addPurchaseToCart(@PathVariable("videoId")String videoId, HttpServletResponse response, HttpServletRequest request){
        Cookie videoList = getCartCookie(request);
        String cartString = handleAddToCart(videoId, false, videoList);
        Cookie cookie = new Cookie(SHOPPING_CART_COOKIE_NAME, cartString);
        cookie.setPath("http://localhost:8080/cart");
        response.addCookie(cookie);
        return "redirect:/browseView.htm/" + videoId;
    }
    
    @RequestMapping(value = "/shoppingCartView.htm/rental/${videoId}", method = RequestMethod.POST)
    public String addrentalToCart(@PathVariable("videoId")String videoId, HttpServletResponse response, HttpServletRequest request){
        Cookie videoList = getCartCookie(request);
        String cartString = handleAddToCart(videoId, true, videoList);
        Cookie cookie = new Cookie(SHOPPING_CART_COOKIE_NAME, cartString);
        cookie.setPath("http://localhost:8080/cart");
        response.addCookie(cookie);
        return "redirect:/browseView.htm/" + videoId;
    }
    
    private Cookie getCartCookie(HttpServletRequest request){
        Cookie cookies[] = request.getCookies();
        for(Cookie c : cookies){
            if (c.getName().equals(SHOPPING_CART_COOKIE_NAME)){
                return c;
            }
        }
        return null;
    }
    
    private String handleAddToCart(String videoId, boolean isRented, Cookie originalCookie){
        String videoList = "";
        if (originalCookie != null){
            videoList = originalCookie.getName();
        }
        ShoppingCart cart = ShoppingCart.fromString(videoList);
        cart.addToCart(Integer.parseInt(videoId), isRented);
        return cart.toString();
    }
    
    private ArrayList<VideoInfo> buildPotentialVideoOrderList(String videoList){
        ArrayList<VideoInfo> list = new ArrayList<VideoInfo>();
        
        String vidList[] = videoList.split(" ");
        
        for(String video : vidList){
            try{
                int videoId = Integer.parseInt(video);
                list.add(browseService.displayVideoDetails(videoId));
            }catch(NumberFormatException e){
                e.printStackTrace();
            }catch(DataAccessException e){
                e.printStackTrace();
            }
        }
        
        return list;
    }
}
