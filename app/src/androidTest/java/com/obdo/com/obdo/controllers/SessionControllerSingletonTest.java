package com.obdo.com.obdo.controllers;

import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;


/**
 * Created by Marcus Vinícius de Carvalho on 12/11/2014.
 */
public class SessionControllerSingletonTest extends InstrumentationTestCase {
    private SessionControllerSingleton sone = null, stwo = null;
    private SessionControllerSingleton sessionControllerSingleton;
    private String phoneNumber = "+91123456789";
    private String uid = "123456789";
    private String nick = "foo";
    private String sms = "987654321";
    private String smsServerPhoneNumber = "55512345";

    protected void setUp() throws Exception {
        super.setUp();
        init();
    }

    private void init() {
        sessionControllerSingleton = SessionControllerSingleton.getInstance();
        sessionControllerSingleton.setPhoneNumber(phoneNumber);
        sessionControllerSingleton.setUID(uid);
        sessionControllerSingleton.setNick(nick);
    }

    @SmallTest
    public void testSingleton() throws Exception {
        sone = SessionControllerSingleton.getInstance();
        stwo = SessionControllerSingleton.getInstance();
        assertSame(sone, stwo);
    }

    @MediumTest
    public void testUserExists() throws Exception {
        try {
            sessionControllerSingleton.setPhoneNumber("");
            sessionControllerSingleton.checkUserExists();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        try {
            sessionControllerSingleton.setUID("");
            sessionControllerSingleton.checkUserExists();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        try {
            sessionControllerSingleton.setPhoneNumber("");
            sessionControllerSingleton.setUID("");
            sessionControllerSingleton.checkUserExists();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        init();
        assertTrue(sessionControllerSingleton.checkUserExists());
    }

    @MediumTest
    public void testRegisterUser() throws Exception {
        try {
            sessionControllerSingleton.setPhoneNumber("");
            sessionControllerSingleton.registerUser();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        try {
            sessionControllerSingleton.setUID("");
            sessionControllerSingleton.registerUser();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        try {
            sessionControllerSingleton.setPhoneNumber("");
            sessionControllerSingleton.setUID("");
            sessionControllerSingleton.registerUser();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        init();
        assertTrue(sessionControllerSingleton.registerUser());
    }

    @MediumTest
    public void testLoginUser() throws Exception {
        try {
            sessionControllerSingleton.setPhoneNumber("");
            sessionControllerSingleton.loginUser();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        try {
            sessionControllerSingleton.setUID("");
            sessionControllerSingleton.loginUser();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        try {
            sessionControllerSingleton.setPhoneNumber("");
            sessionControllerSingleton.setUID("");
            sessionControllerSingleton.loginUser();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        init();
        assertTrue(sessionControllerSingleton.loginUser());
    }

    @MediumTest
    public void testUpdateNickUser() throws Exception {
        try {
            sessionControllerSingleton.setPhoneNumber("");
            sessionControllerSingleton.updateNickUser();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        try {
            sessionControllerSingleton.setUID("");
            sessionControllerSingleton.updateNickUser();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        try {
            sessionControllerSingleton.setNick("");
            sessionControllerSingleton.updateNickUser();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        try {
            sessionControllerSingleton.setPhoneNumber("");
            sessionControllerSingleton.setUID("");
            sessionControllerSingleton.updateNickUser();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        try {
            sessionControllerSingleton.setPhoneNumber("");
            sessionControllerSingleton.setNick("");
            sessionControllerSingleton.updateNickUser();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        try {
            sessionControllerSingleton.setUID("");
            sessionControllerSingleton.setNick("");
            sessionControllerSingleton.updateNickUser();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        try {
            sessionControllerSingleton.setPhoneNumber("");
            sessionControllerSingleton.setUID("");
            sessionControllerSingleton.setNick("");
            sessionControllerSingleton.updateNickUser();
            assertFalse(true);
        }catch (NullPointerException e){
            assertTrue(true);
        } finally {
            init();
        }

        init();
        assertTrue(sessionControllerSingleton.updateNickUser());
    }

    @SmallTest
    public void testCheckSMS() throws Exception {
        //TODO: no idea
        assertTrue(sessionControllerSingleton.checkSMS(sms));
    }

    @MediumTest
    public void testSMSBroadcastReceiver() throws Exception {
        //TODO: no idea
    }
}
