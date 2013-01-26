/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.services;

import com.team33.entities.dao.BrowseDaoImpl;
import org.springframework.beans.factory.annotation.Autowired;

/**
 *
 * @author Samual
 */
public class BrowseServiceImpl implements BrowseService{
    /*Main display of featured content before user filters a new search*/
    @Autowired
    private BrowseDaoImpl browseDaoImpl;
    
    public void setBrowseDaoImpl(BrowseDaoImpl browseDaoImpl){
        this.browseDaoImpl = browseDaoImpl;
    }
    public void displayFeaturedVideo(){
        
    }
}
