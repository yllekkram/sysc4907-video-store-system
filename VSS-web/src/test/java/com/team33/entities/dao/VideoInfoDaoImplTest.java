/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.VideoInfo;
import java.util.List;
import org.hibernate.SessionFactory;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext-test.xml"})
public class VideoInfoDaoImplTest {
    
    @Autowired
    private VideoInfoDaoImpl videoInfoDaoImpl;
    
    public VideoInfoDaoImplTest() {
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
    
    public void setVideoInfoDaoImpl(VideoInfoDaoImpl videoInfoDoaImpl){
        this.videoInfoDaoImpl = videoInfoDaoImpl;
    }

    /**
     * Test of getVideoInfoList method, of class VideoInfoDaoImpl.
     */
    @Test
    public void testGetVideoInfoList() {
        System.out.println("getVideoInfoList");
          
        VideoInfoDaoImpl instance = new VideoInfoDaoImpl();
        
        SessionFactory sessionFactory = null;
        instance.setSessionFactory(sessionFactory);
                
        assertNull("AssertNull - Expected Output : instance.getSessionFactory() == NULL, Output : " + instance.getSessionFactory(), instance.getSessionFactory());
        
        assertNotNull("AssertNotNull - Expected Output : this.videoInfoDaoImpl != NULL, Output : " + this.videoInfoDaoImpl, this.videoInfoDaoImpl);
        
        assertNotNull("AssertTrue - Expected Output : this.videoInfoDaoImpl.getSessionFactory() != NULL, Output : " + this.videoInfoDaoImpl.getSessionFactory(), this.videoInfoDaoImpl.getSessionFactory());
    }

    /**
     * Test of getVideoInfo method, of class VideoInfoDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testGetVideoInfo() {
        System.out.println("getVideoInfo");
        List<VideoInfo> expResult = null;
        try{
            expResult = this.videoInfoDaoImpl.getVideoInfoList();
        }catch(DataAccessException e){
            fail("Exception Thrown(Empty Test) : " + e.getLocalizedMessage());
        }
        
        assertNotNull("AssertNotNull - Expected Output : expResult != NULL, Output : " + expResult, expResult);
        assertTrue("AssertTrue - Expected Output : expResult.size() == 0, Output : " + expResult.size(), expResult.isEmpty());
        
        VideoInfo testVideo = new VideoInfo(0, "Test1", "Test Video", 0);
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveVideoInfo method, of class VideoInfoDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testSaveVideoInfo() {
        System.out.println("saveVideoInfo");
        
        try{
            this.videoInfoDaoImpl.saveVideoInfo(-1);
            fail("Did not throw an error for negative input");
        }catch(DataAccessException e){
            // Suppose to happen
        }
        
        VideoInfo testVideo = new VideoInfo(10, "Test1", "Test Video", 0);
        try{
            this.videoInfoDaoImpl.saveVideoInfo(10);
        }catch(DataAccessException e){
            fail("Exception Thrown : " + e.getLocalizedMessage());
        }
        
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeVideoInfo method, of class VideoInfoDaoImpl.
     */
    @Test
    public void testRemoveVideoInfo() {
        System.out.println("removeVideoInfo");
        int videoInfoId = 0;
        VideoInfoDaoImpl instance = new VideoInfoDaoImpl();
        instance.removeVideoInfo(videoInfoId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}
