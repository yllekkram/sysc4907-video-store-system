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
    
    private VideoInfo testVideoInfo1;
    private VideoInfo testVideoInfo2;
    
    public VideoInfoDaoImplTest() {
        this.testVideoInfo1 = new VideoInfo(0, "Lion King");
        this.testVideoInfo2 = new VideoInfo(1, "Happy Tree");
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
        
        //this.videoInfoDaoImpl.saveVideoInfo(testVideoInfo1);
        //this.videoInfoDaoImpl.saveVideoInfo(testVideoInfo2);
    }

    /**
     * Test of getVideoInfoList method, of class VideoInfoDaoImpl.
     */
    @Test
    @Transactional
    public void testGetVideoInfoList() {
        System.out.println("getVideoInfoList() : List<VideoInfo> -> [DataAccessException]");
        
        List<VideoInfo> videoList = this.videoInfoDaoImpl.getVideoInfoList();
        
        assertNotNull(videoList);
        assertEquals(videoList.size(), 2);
        
        
        SessionFactory tmpFactory = this.videoInfoDaoImpl.getSessionFactory();
        this.videoInfoDaoImpl.setSessionFactory(null);
        try{
            this.videoInfoDaoImpl.getVideoInfoList();
        }catch(DataAccessException e){
        
        }finally{
            this.videoInfoDaoImpl.setSessionFactory(tmpFactory);
        }
    }

    /**
     * Test of getVideoInfo method, of class VideoInfoDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testGetVideoInfo() {
        System.out.println("getVideoInfo(Integer) : VideoInfo -> [DataAccessException]");

        VideoInfo videoInfo = this.videoInfoDaoImpl.getVideoInfo(0);
        assertNotNull(videoInfo);
        assertEquals(videoInfo.getId(), this.testVideoInfo1.getId());
        assertEquals(videoInfo.getTitle(), this.testVideoInfo1.getTitle());
        
        try{
            videoInfo = null;
            videoInfo = this.videoInfoDaoImpl.getVideoInfo(-1);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
            assertNull(videoInfo);
        }
        
        try{
            videoInfo = null;
            videoInfo = this.videoInfoDaoImpl.getVideoInfo(9999);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
            assertNull(videoInfo);
        }
        
        try{
            videoInfo = null;
            videoInfo = this.videoInfoDaoImpl.getVideoInfo((Integer)null);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
            assertNull(videoInfo);
        }
    }

    /**
     * Test of saveVideoInfo method, of class VideoInfoDaoImpl.
     */
    @Test
    @Rollback(true)
    @Transactional
    public void testSaveVideoInfo() {
        System.out.println("saveVideoInfo(Integer) : Void -> [DataAccessException]");

    }

    /**
     * Test of removeVideoInfo method, of class VideoInfoDaoImpl.
     */
    @Test
    @Transactional
    @Rollback(true)
    public void testRemoveVideoInfo() {
        System.out.println("removeVideoInfo() : Void -> [DataAccessException]");
        try{
            this.videoInfoDaoImpl.removeVideoInfo(0);
            List<VideoInfo> videoInfo = this.videoInfoDaoImpl.getVideoInfoList();
            assertNotNull(videoInfo);
            assertEquals(videoInfo.size(), 1);
            assertEquals(videoInfo.get(0).getId(), this.testVideoInfo2.getId());
            assertEquals(videoInfo.get(0).getTitle(), this.testVideoInfo2.getTitle());
        }catch(DataAccessException e){
            fail("DataAccessException was thrown");
        }
        
        try{
            this.videoInfoDaoImpl.removeVideoInfo(-1);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
        
        }
        
        try{
            this.videoInfoDaoImpl.removeVideoInfo(9999);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
        
        }
        
        try{
            this.videoInfoDaoImpl.removeVideoInfo((Integer)null);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
        
        }
    }
}
