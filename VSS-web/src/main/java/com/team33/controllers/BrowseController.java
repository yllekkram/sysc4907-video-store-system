/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.controllers;

import com.team33.services.BrowseServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.mvc.AbstractController;

/**
 * This is the controller for the browsing feature
 *
 * @author Samual
 */
@Controller
@RequestMapping(value = "/browseView.htm")
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
    @RequestMapping("/browseView")
    public String displayAllVideo() {
        this.browseServiceImpl.displayAllVideoContent();
        return "redirect:/browseView";
    }
}
