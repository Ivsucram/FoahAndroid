package com.obdo.controllers;

import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.SmallTest;

/**
 * Unit tests for class HTTPRequestController
 * @author Marcus Vinícius de Carvalho
 * @since 12/13/2014
 * @version 1.0
 * @see com.obdo.controllers.HTTPRequestController
 */
public class HTTPRequestControllerTest extends InstrumentationTestCase {
    private String url = "http://litesuits.github.io/mockdata/user?id=18";
    private String phoneNumber = "+91123456789";
    private String uid = "123456789";
    private String nick = "foo";
    private String sms = "987654321";

    protected void setUp() throws Exception {
        super.setUp();
    }

    //TODO: Create mock or anything else to make sure that tests are using HTTP Request or using the internet
    @SmallTest
    public void testCheckUserExists() {}

    //TODO: Create mock or anything else to make sure that tests are using HTTP Request or using the internet
    @SmallTest
    public void testRegisterUser() {}

    //TODO: Create mock or anything else to make sure that tests are using HTTP Request or using the internet
    @SmallTest
    public void testLoginUser() {}

    //TODO: Create mock or anything else to make sure that tests are using HTTP Request or using the internet
    @SmallTest
    public void testUpdateNickUser() {}

    //TODO: Create mock or anything else to make sure that tests are using HTTP Request or using the internet
    @SmallTest
    public void testCheckSMS() {}
}
