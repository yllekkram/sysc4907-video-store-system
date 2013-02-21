/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.VideoInfo;
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
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Caleb
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:/test/dao/dao-test.xml"})
@TransactionConfiguration(transactionManager = "transactionManager")
@Transactional
public class VideoInfoDaoImplTest {
    
    @Autowired
    private VideoInfoDaoImpl videoInfoDao;
    
    private VideoInfo testVideoInfo1;
    private VideoInfo testVideoInfo2;
    
    public VideoInfoDaoImplTest() {
//        this.testVideoInfo1 = new VideoInfo(0, "Lion King");
//        this.testVideoInfo2 = new VideoInfo(1, "Happy Tree");
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
    
    public void setVideoInfoDao(VideoInfoDaoImpl videoInfoDao){
        this.videoInfoDao = videoInfoDao;
    }

    /**
     * Test of getVideoInfoList method, of class VideoInfoDaoImpl.
     */
    
    @Test
    @Rollback(true)
    public void testGetVideoInfoList_NotNull(){
        assertNotNull(this.videoInfoDao.getVideoInfoList());
        System.out.println("testGetVideoInfoList_NotNull() passed");
    }
    
    @Test
    @Rollback(true)
    public void testGetVideoInfoList_ValidSize(){
        assertTrue(this.videoInfoDao.getVideoInfoList().size() > 0);
        System.out.println("testGetVideoInfoList_ValidSize() passed");
    }
    
    @Test
    @Rollback(true)
    public void testGetVideoInfoList_NullSessionFactory(){
        SessionFactory tmpFactory = this.videoInfoDao.getSessionFactory();
        this.videoInfoDao.setSessionFactory(null);
        try{
            this.videoInfoDao.getVideoInfoList();
            fail("Exception was not thrown");
        }catch(DataAccessException e){
        
        }finally{
            this.videoInfoDao.setSessionFactory(tmpFactory);
        }
        System.out.println("testGetVideoInfoList_NullSessionFactory() passed");
    } 
    

    /**
     * Test of getVideoInfo method, of class VideoInfoDaoImpl.
     */
    @Test
    @Rollback(true)
    public void testGetVideoInfo_NegId(){
        try{
            this.videoInfoDao.getVideoInfo(-1);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
        }
        System.out.println("testGetVideoInfo_NegId() passed");
    }
    
    @Test
    @Rollback(true)
    public void testGetVideoInfo_InvalidId(){
        try{
            this.videoInfoDao.getVideoInfo(9999);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
        }
        System.out.println("testGetVideoInfo_InvalidId() passed");
    }
    
    @Test
    @Rollback(true)
    public void testGetVideoInfo_ValidId(){
        try{
            VideoInfo info = this.videoInfoDao.getVideoInfo(0);
            assertNotNull(info);
            assertEquals(info.getId().intValue(), 0);
        }catch(DataAccessException e){
            fail("Exception was thrown : " + e.getLocalizedMessage());
        }
        System.out.println("testGetVideoInfo_ValidId() passed");
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoInfo_NegId(){
        try{
            this.videoInfoDao.saveVideoInfo(-1);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
        }
        System.out.println("testSaveVideoInfo_NegId() passed");
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoInfo_InvalidId(){
        try{
            this.videoInfoDao.saveVideoInfo(9999);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
        }
        System.out.println("testSaveVideoInfo_InvalidId() passed");
    }
    
    @Test
    @Rollback(true)
    public void testSaveVideoInfo_ValidId(){
        try{
            this.videoInfoDao.saveVideoInfo(0);
        }catch(DataAccessException e){
            fail("Excpetion was thrown : " + e.getLocalizedMessage());
        }
        System.out.println("testSaveVideoInfo_ValidId() passed");
    }

    /**
     * Test of removeVideoInfo method, of class VideoInfoDaoImpl.
     */
    @Test
    @Rollback(true)
    public void testRemoveVideoInfo_NegId(){
        try{
            this.videoInfoDao.removeVideoInfo(-1);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
        }
        System.out.println("testRemoveVideoInfo_NegId() passed");
    }
    @Test
    @Rollback(true)
    public void testRemoveVideoInfo_InvalidId(){
        try{
            this.videoInfoDao.removeVideoInfo(9999);
            fail("Failed to throw DataAccessException");
        }catch(DataAccessException e){
        }
        System.out.println("testRemoveVideoInfo_InvalidId() passed");
    }
    @Test
    @Rollback(true)
    public void testRemoveVideoInfo_ValidId(){
        try{
            this.videoInfoDao.removeVideoInfo(0);
        }catch(DataAccessException e){
            fail("Exception was thrown : " + e.getLocalizedMessage());
        }
        System.out.println("testRemoveVideoInfo_ValidId() passed");
    }
   
}
