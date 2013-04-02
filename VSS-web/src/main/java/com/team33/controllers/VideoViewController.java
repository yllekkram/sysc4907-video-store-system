/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.services.playback.VideoPlaybackService;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

/**
 *
 * @author Caleb
 */
@Controller
@SessionAttributes("alreadyLogin")
public class VideoViewController {
    
    @Autowired
    private VideoPlaybackService playbackService;
    
    public void setPlaybackService(VideoPlaybackService service){
        this.playbackService = service;
    }
    
    @RequestMapping(value = "/viewVideo/{orderId}/{videoId}", method = RequestMethod.GET)
    public String loadVideoOnStartup(Map<String, Object> map, @PathVariable("orderId")String orderId, @PathVariable("videoId")String videoId, @ModelAttribute("alreadyLogin")Integer tokenId){
        System.out.println("------ Loading Video -------");
        // Load video location from DB
        
        String videoLocation = playbackService.getVideoFileInformation(tokenId.intValue(), Integer.parseInt(videoId));
        int currentTime = playbackService.getInformation(tokenId.intValue(), Integer.parseInt(orderId), Integer.parseInt(videoId));
        
        // Tmp Until Service Layer added in
        //String videoLocation = "big_buck_bunny_480p_stereo";
        map.put("currentTime", currentTime);
        map.put("vidLocation_ogg", videoLocation + ".ogg");
        //map.put("vidLocation_mp4", videoLocation_mp4);
        //map.put("currentTime", 0);
        //logger.error("Loading Video");
        map.put("orderId", orderId);
        map.put("videoId", videoId);
        
        return "/viewVideo";
    }
    @RequestMapping(value = "/viewVideo/{orderId}/{videoId}", method = RequestMethod.POST)
    public void saveVideoTimeLocation(@ModelAttribute("savedTime") String currentTime, @PathVariable("orderId")String orderId, @PathVariable("videoId")String videoId, @ModelAttribute("alreadyLogin")Integer tokenId){
        // Save currentTime
        System.out.println("--------------------------------- Current Time is : " + currentTime);
        System.out.println("Order : " + orderId + ", Video : " + videoId + ", Login : " + tokenId);        
        
        playbackService.saveInformation(tokenId.intValue(), Integer.parseInt(orderId), Integer.parseInt(videoId), Integer.parseInt(currentTime));        
    }
}
