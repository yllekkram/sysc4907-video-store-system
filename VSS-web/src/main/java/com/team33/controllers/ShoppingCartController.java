/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.entities.VideoInfo;
import com.team33.services.BrowseService;
import com.team33.services.exception.DataAccessException;
import java.util.ArrayList;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Caleb
 */
@Controller
@RequestMapping(value = "/shoppingCartView.htm")
public class ShoppingCartController {
    
    @Autowired
    private BrowseService browseService;
    
    public void setBrowseService(BrowseService service){
        this.browseService = service;
    }
    
    @RequestMapping(method = RequestMethod.GET)
    public String viewCart(/*@CookieValue("VIDEO_LIST")String videoList,*/ Map<String, Object> map){
        
        //map.put("VideoList", buildPotentialVideoOrderList(""));
        map.put("VideoList", new ArrayList<VideoInfo>());
        
        return "/shoppingCartView";
    }
    
    public ArrayList<VideoInfo> buildPotentialVideoOrderList(String videoList){
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
