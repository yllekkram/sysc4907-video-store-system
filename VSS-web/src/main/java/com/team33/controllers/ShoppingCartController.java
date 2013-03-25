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
    
    @Autowired
    private BrowseService browseService;
    
    public void setBrowseService(BrowseService service){
        this.browseService = service;
    }
    
    @RequestMapping(value = "/shoppingCartView.htm", method = RequestMethod.GET)
    public String viewCart(/*@CookieValue("VIDEO_LIST")String videoList,*/ Map<String, Object> map){
        
        //map.put("VideoList", buildPotentialVideoOrderList(""));
        
        ArrayList<VideoInfo> info = new ArrayList<VideoInfo>();
        info.add(new VideoInfo(0, "Hello World", "1234", 10, 10, 10, new Date(System.currentTimeMillis())));
        map.put("VideoList", info);
        
        return "/shoppingCartView";
    }
    
    @RequestMapping(value = "/shoppingCartView.htm", method = RequestMethod.POST)
    public String deleteOrder(@RequestParam("id")String id, /*@CookieValue("VIDEO_LIST") String videoList, */ HttpServletResponse response){
        String videoList = "";
        String newVideoList = videoList.replace("" + id, "");
        newVideoList = newVideoList.replaceAll(",,", ",");
        Cookie cookie = new Cookie("VIDEO_LIST", newVideoList);
        cookie.setPath("http://localhost:8080/cart");
        response.addCookie(cookie);
        return "redirect:shoppingCartView.htm";
    }
    
    @RequestMapping(value = "/shoppingCartView.htm/${videoId}", method = RequestMethod.POST)
    public String addToCart(@PathVariable("videoId")String videoId, HttpServletResponse response, HttpServletRequest request){
        Cookie cookies[] = request.getCookies();
        String videoList = "";
        boolean isFound = false;
        for(Cookie c : cookies){
            if (c.getName().equals("VIDEO_LIST")){
                videoList = c.getValue();
                isFound = true;
            }
        }
        Cookie cookie = null;
        if (isFound){
            cookie = new Cookie("VIDEO_LIST", videoList + "," + videoId);
        }else{
            cookie = new Cookie("VIDEO_LIST", videoId);
        }
        cookie.setPath("http://localhost:8080/cart");
        response.addCookie(cookie);
        return "redirect:/browseView.htm/" + videoId;
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
