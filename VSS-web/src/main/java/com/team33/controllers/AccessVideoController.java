/*
 * This class controls the access of videos
 */
package com.team33.controllers;

import com.team33.services.VideoAccessServiceImpl;
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
    private VideoAccessServiceImpl videoAccessServiceImpl;
    
    public void setVideoAccessServiceImpl(VideoAccessServiceImpl videoAccessServiceImpl){
        this.videoAccessServiceImpl = videoAccessServiceImpl;
    }
}
