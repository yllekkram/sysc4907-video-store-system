/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.Account;
import com.team33.entities.LoginToken;
import com.team33.entities.VideoInfo;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;
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
public class VideoAccessDaoImplTest {
    
    private VideoAccessDao videoAccessDao;
    
    public VideoAccessDaoImplTest() {
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
    
    public void setVideoAccessDao(VideoAccessDao videoAccessDao){
        this.videoAccessDao = videoAccessDao;
    }

    /**
     * Test of getVideoInfo method, of class VideoAccessDaoImpl.
     */
    @Test
    @Transactional
    public void testGetVideoInfo() throws Exception {
        System.out.println("getVideoInfo(Integer, LoginToken) : VideoInfo -> [DataAccessException,AccountNotActivatedException]");
        
        LoginToken exceptionTestToken = new LoginToken();
        Account testAccount = new Account();
        testAccount.setActivated(false);
        exceptionTestToken.setAccount(testAccount);
        // Cause exception from null token
//        try{
//            VideoInfo info = this.videoAccessDao.getVideoInfo(0, null);
//            fail("Exception was not thrown");
//        }catch(DataAccessException e){
//        }
        // Cause AccountNotActivatedException
//        try{
//            VideoInfo info = this.videoAccessDao.getVideoInfo(0, exceptionTestToken);
//            fail("Exception was not thrown");
//        }catch(AccountNotActivatedException e){
//        }
        
        LoginToken testToken = new LoginToken();
        testAccount = new Account();
        testAccount.setActivated(true);
        testToken.setAccount(testAccount);
        
        // Test valid token, valid video id
//        VideoInfo info = this.videoAccessDao.getVideoInfo(0, testToken);
//        assertNotNull(info);
//        
//        // Test valid token, invalid video id
//        try{
//            info = this.videoAccessDao.getVideoInfo(-1, testToken);        
//            fail("Exception was not thrown");
//        }catch(DataAccessException e){
//            
//        }
//        // Test valid token, invalid video id
//        try{
//            info = this.videoAccessDao.getVideoInfo(9999, testToken);
//            fail("Exception was not thrown");
//        }catch(DataAccessException e){
//        
//        }
    }

    /**
     * Test of getVideoInfoList method, of class VideoAccessDaoImpl.
     */
    @Test
    @Transactional
    public void testGetVideoInfoList() throws Exception {
        System.out.println("getVideoInfoList(LoginToken) : List<VideoInfo> -> [DataAccessException,AccountNotActivatedException]");
        
//        try{
//            List<VideoInfo> info = this.videoAccessDao.getVideoInfoList(null);
//            fail("Exception was not thrown");
//        }catch(DataAccessException e){
//        }
        
        LoginToken exceptionTestToken = new LoginToken();
        Account testAccount = new Account();
        testAccount.setActivated(false);
        exceptionTestToken.setAccount(testAccount);
        
//        try{
//            List<VideoInfo> info = this.videoAccessDao.getVideoInfoList(exceptionTestToken);
//        }catch(AccountNotActivatedException e){
//        }
        
        LoginToken testToken = new LoginToken();
        testAccount = new Account();
        testAccount.setActivated(true);
        testToken.setAccount(testAccount);
        
//        List<VideoInfo> info = this.videoAccessDao.getVideoInfoList(testToken);
//        assertNotNull(info);
//        assertTrue(info.size() > 0);
        
    }
}
