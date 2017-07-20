package com.yulei.core.business;

import com.yulei.core.app.ApplicationContextConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

/**
 * @author Yulei
 * @version 1.0
 * @date 2017/7/18
 * @description
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = {ApplicationContextConfig.class})
@WebAppConfiguration
public class UserBusinessImplTest {

    @Test
    public void testFindUser(){
        System.out.println("wocalei");
    }
}
