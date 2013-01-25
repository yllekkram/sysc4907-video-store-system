/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author Mark
 */
@Controller
public class VideoInfoController {
    @RequestMapping(value = "/browseVideos", method = RequestMethod.GET)
    public String getVideos(Map<String,Object> map) {
        return "/browseVideos";
    }
}
