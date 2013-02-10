/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.ScreenRating;
import com.team33.entities.VideoInfo;
import com.team33.services.exception.DataAccessException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"file:src/main/webapp/WEB-INF/applicationContext-test.xml"})
public class BrowseDaoImplTest {
    
    private BrowseDaoImpl browseDao;
    
    public BrowseDaoImplTest() {
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
    
    public void setBrowseDao(BrowseDaoImpl browseDao){
        this.browseDao = browseDao;
    }

    /**
     * Test of displayFeaturedVideo method, of class BrowseDaoImpl.
     */
    @Test
    @Transactional
    public void testDisplayFeaturedVideo() {
        System.out.println("displayFeaturedVideo() : List<VideoInfo>");
        
        List<VideoInfo> videos = this.browseDao.displayFeaturedVideo();
        assertNotNull(videos);
        
        assertTrue(videos.size() > 0);
    }
    
    @Test
    @Transactional
    public void testSearchVideos_ScreenRating(){
        System.out.println("searchVideos(ScreenRating) : List<VideoInfo>");

        try{
            List<VideoInfo> videos = this.browseDao.searchVideos((ScreenRating)null);
            fail("Exception was not thrown");
        }catch(DataAccessException e){
        
        }
        ScreenRating testRating = new ScreenRating();
        testRating.setId(9999);
        List<VideoInfo> videos = this.browseDao.searchVideos(testRating);
        
        assertNotNull(videos);
        assertEquals(videos.size(), 0);
        
        testRating = new ScreenRating();
        testRating.setId(0);
        videos = this.browseDao.searchVideos(testRating);
        
        assertNotNull(videos);
        assertTrue(videos.size() > 0);
        
        // TODO Need to check if correct videos appear
    }
    
    @Test
    @Transactional
    public void testSearchVideos_String(){
        System.out.println("searchVideos(String) : List<VideoInfo>");
        
        try{
            List<VideoInfo> videos = this.browseDao.searchVideos((String)null);
            fail("Exception was not thrown");
        }catch(DataAccessException e){
        
        }
        
        List<VideoInfo> videos = this.browseDao.searchVideos("");
        assertNotNull(videos);
        assertEquals(videos.size(), 0);
        
        videos = this.browseDao.searchVideos("No Going to Find Something");
        assertNotNull(videos);
        assertEquals(videos.size(), 0);
        
        videos = this.browseDao.searchVideos("Single Title");
        assertNotNull(videos);
        assertEquals(videos.size(), 1);
        VideoInfo testVideoInfo = videos.get(0);
        
        assertNotNull(testVideoInfo);
        
        // TODO test for video retrieved
        
        videos = this.browseDao.searchVideos("Multiple Title");
        assertNotNull(videos);
        assertEquals(videos.size(), 2);
        
        assertNotNull(videos.get(0));
        assertNotNull(videos.get(1));
        
        // TODO test for videos retrieved
    }
}
