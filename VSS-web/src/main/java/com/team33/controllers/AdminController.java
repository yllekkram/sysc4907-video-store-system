/*
 * This will control the features for admin video management
 */
package com.team33.controllers;

import com.team33.services.AdminServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.portlet.mvc.AbstractController;

/**
 *
 * @author Samual
 */
@Controller
@RequestMapping(value = "/adminView.htm")
public class AdminController{
    @Autowired
    private AdminServiceImpl adminServiceImpl;
    
    public void setAdminServiceImpl(AdminServiceImpl adminServiceImpl){
        this.adminServiceImpl = adminServiceImpl;
    }
}
