/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.support.HibernateDaoSupport;

/**
 *
 * @author Samual
 */
public class BrowseDaoImpl extends HibernateDaoSupport implements BrowseDao{
    //tells Spring to inject the dependency
    @Autowired
    private SessionFactory sessionFactory;
    public void displayFeaturedVideo(){
        
    }
}
