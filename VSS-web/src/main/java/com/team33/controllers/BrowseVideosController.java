/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.entities.VideoInfo;
import com.team33.services.BrowseService;
import com.team33.services.VideoAccessService;
import java.util.List;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 *
 * @author Mark
 */
@Controller
public class BrowseVideosController {
    @Autowired
    private BrowseService browseService;
    
    @Autowired
    private VideoAccessService videoAccessService;
            
    public void setBrowseService(BrowseService browseService){
        this.browseService = browseService;
    }

    public void setVideoAccessService(VideoAccessService videoAccessService) {
        this.videoAccessService = videoAccessService;
    }
    
    @RequestMapping(value = "/browseVideos", method = RequestMethod.GET)
    public String getVideos(Map<String,Object> map, @RequestParam(value = "keyword", required = false)String search) {
        List<VideoInfo> list = null;
        if (search == null){
            list = getDefaultBrowseList();
        }else{
            list = getSearchedBrowseList(search);
        }
        map.put("videoInfoList", list);
        System.out.println("Done Add to List : " + list.size());
        return "/browseVideos";
    }
    
    private List<VideoInfo> getDefaultBrowseList(){
        return this.browseService.displayAllVideoContent();
    }
    
    private List<VideoInfo> getSearchedBrowseList(String title){
        return this.browseService.searchLikeVideos(title);
    }
}
