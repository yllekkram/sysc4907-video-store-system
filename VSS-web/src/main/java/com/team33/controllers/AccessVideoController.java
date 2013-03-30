/*
 * This class controls the access of videos
 */
package com.team33.controllers;

import com.team33.services.VideoAccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 *
 * @author Samual
 */
@Controller
@RequestMapping(value ="/accessVideoOrders.htm")
public class AccessVideoController{
    @Autowired
    private VideoAccessService videoAccessService;
    
    /**
     *
     * @param videoAccessService
     */
    public void setVideoAccessService(VideoAccessService videoAccessService){
        this.videoAccessService = videoAccessService;
    }
}
