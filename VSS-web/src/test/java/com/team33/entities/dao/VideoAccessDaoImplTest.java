/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.team33.entities.dao;

import com.team33.entities.LoginToken;
import com.team33.entities.VideoInfo;
import com.team33.services.exception.AccountNotActivatedException;
import com.team33.services.exception.DataAccessException;
import java.util.List;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import static org.junit.Assert.*;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
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
public class VideoAccessDaoImplTest {
    
    @Autowired
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
    @Rollback(true)
    public void testGetVideoInfo_NegVidId(){
        try{
            this.videoAccessDao.getVideoInfo(-1, 0);
            fail("Exception not thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("Wrong excpetion thrown");
        }
        System.out.println("testGetVideoInfo_NegVidId() passed");
    }
    
    @Test
    @Rollback(true)
    public void testGetVideoInfo_InvalidVidId(){
        try{
            this.videoAccessDao.getVideoInfo(9999, 0);
            fail("Exception not thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("Wrong excpetion thrown");
        }
        System.out.println("testGetVideoInfo_InvalidVidId() passed");
    }
    
    @Test
    @Rollback(true)
    public void testGetVideoInfo_ValidVidId(){
        try{
            VideoInfo info = this.videoAccessDao.getVideoInfo(0, 0);
            assertNotNull(info);
            assertEquals(info.getId().intValue(), 0);
        }catch(AccountNotActivatedException e){
            fail("Wrong excpetion thrown");
        }
        System.out.println("testGetVideoInfo_ValidVidId() passed");
    }
    
    @Test
    @Rollback(true)
    public void testGetVideoInfo_NegUUID(){
        try{
            this.videoAccessDao.getVideoInfo(0, -1);
            fail("Exception not thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("Wrong excpetion thrown");
        }
        System.out.println("testGetVideoInfo_NegUUID() passed");
    }
    
    @Test
    @Rollback(true)
    public void testGetVideoInfo_InvalidUUID(){
        try{
            this.videoAccessDao.getVideoInfo(0, 9999);
            fail("Exception not thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("Wrong excpetion thrown");
        }
        System.out.println("testGetVideoInfo_InvalidUUID() passed");
    }
    
    /**
     * Test of getVideoInfoList method, of class VideoAccessDaoImpl.
     */
    @Test
    @Rollback(true)
    public void testGetVideoInfoList_NegId(){
        try{
            this.videoAccessDao.getVideoInfoList(-1);
            fail("Exception not thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("Wrong excpetion thrown");
        }
        System.out.println("testGetVideoInfoList_NegId() passed");
    }
    @Test
    @Rollback(true)
    public void testGetVideoInfoList_InvalidId(){
        try{
            this.videoAccessDao.getVideoInfoList(9999);
            fail("Exception not thrown");
        }catch(DataAccessException e){
        }catch(AccountNotActivatedException e){
            fail("Wrong excpetion thrown");
        }
        System.out.println("testGetVideoInfoList_InvalidId() passed");
    }
    @Test
    @Rollback(true)
    public void testGetVideoInfoList_ValidInfo(){
        try{
            List<VideoInfo> info = this.videoAccessDao.getVideoInfoList(0);
            assertNotNull(info);
            assertTrue(info.size() > 0);
        }catch(AccountNotActivatedException e){
            fail("Wrong excpetion thrown");
        }
        System.out.println("testGetVideoInfoList_ValidInfo() passed");
    }
    @Test
    @Rollback(true)
    public void testGetLoginToken_NegUUID(){
        try{
            this.videoAccessDao.getLoginToken(-1);
            fail("Error was not thrown");
        }catch(DataAccessException e){
        }
        System.out.println("testGetLoginToken_NegUUID() passed");
    }
    
    @Test
    @Rollback(true)
    public void testGetLoginToken_InvalidUUID(){
        try{
            this.videoAccessDao.getLoginToken(9999);
            fail("Error was not thrown");
        }catch(DataAccessException e){
        }
        System.out.println("testGetLoginToken_InvalidUUID() passed");
    }
    
    @Test
    @Rollback(true)
    public void testGetLoginToken_ValidUUID(){
        LoginToken testToken = this.videoAccessDao.getLoginToken(0);
        assertNotNull(testToken);
        assertEquals(testToken.getLogintokenPK().getId(), 0);
        System.out.println("testGetLoginToken_ValidUUID() passed");
    }
}
