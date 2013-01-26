/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.services.BrowseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
// Causing java.lang.NoClassDefFoundError
//import org.springframework.web.portlet.mvc.AbstractController;

/**
 * This is the controller for the browsing feature
 * @author Samual
 */
@Controller
// Removed. Causing java.lang.NoClassDefFoundError
public class BrowseController/* extends AbstractController*/{
    @Autowired
    private BrowseService browseService;
    
    public void setBrowseServiceImpl(BrowseService browseService){
        this.browseService = browseService;
    }
    
    /*Display featured content when the page loads*/
    @RequestMapping("/browseView")
    public String displayFeaturedVideo(){
        this.browseService.displayFeaturedVideo();
        return "redirect:/browseView";
    }
}
