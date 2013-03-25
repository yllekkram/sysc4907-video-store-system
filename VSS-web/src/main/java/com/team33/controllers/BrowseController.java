/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.entities.VideoInfo;
import com.team33.services.BrowseServiceImpl;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * This is the controller for the browsing feature
 *
 * @author Samual
 */
@Controller
@RequestMapping("/browseView.htm")
public class BrowseController {

    @Autowired
    private BrowseServiceImpl browseServiceImpl;

    /**
     *
     * @param browseServiceImpl
     */
    public void setBrowseServiceImpl(BrowseServiceImpl browseServiceImpl) {
        this.browseServiceImpl = browseServiceImpl;
    }

    /*
     * Display featured content when the page loads
     */
    /**
     *
     * @return
     */    
    @RequestMapping(value = "/{videoId}", method=RequestMethod.GET)
    public String displayVideoInformation(@PathVariable("videoId")String videoId, Map<String, Object> map){
        
        int vidId = Integer.parseInt(videoId);
        VideoInfo info = this.browseServiceImpl.displayVideoDetails(vidId);
        int runningTimeMin = (int)(info.getRunningTime() / 60);
        int runningTimeHour = (int)(runningTimeMin / 60);
        runningTimeMin = runningTimeMin % 60;
        map.put("videoInfo", info);
        map.put("runningMin", runningTimeMin);
        map.put("runningHour", runningTimeHour);
        return "redirect:/browseView";
    }
}
