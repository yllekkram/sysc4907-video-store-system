/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.VideoInfo;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author Caleb
 */
public class AdminDaoImplTest {
    
    public AdminDaoImplTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of addVideoInfo method, of class AdminDaoImpl.
     */
    @Test
    public void testAddVideoInfo() {
        System.out.println("addVideoInfo");
        VideoInfo videoInfo = null;
        AdminDaoImpl instance = new AdminDaoImpl();
        instance.addVideoInfo(videoInfo);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVideoInfo method, of class AdminDaoImpl.
     */
    @Test
    public void testGetVideoInfo() {
        System.out.println("getVideoInfo");
        int videoInfoId = 0;
        AdminDaoImpl instance = new AdminDaoImpl();
        VideoInfo expResult = null;
        VideoInfo result = instance.getVideoInfo(videoInfoId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeVideoInfo method, of class AdminDaoImpl.
     */
    @Test
    public void testRemoveVideoInfo() {
        System.out.println("removeVideoInfo");
        int videoInfoId = 0;
        AdminDaoImpl instance = new AdminDaoImpl();
        instance.removeVideoInfo(videoInfoId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
