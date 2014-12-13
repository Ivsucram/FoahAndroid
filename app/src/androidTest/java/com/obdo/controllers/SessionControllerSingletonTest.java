package com.obdo.controllers;

import android.test.InstrumentationTestCase;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;


/**
 * Unit tests for class SessionControllerSingleton
 * @author Marcus Vinícius de Carvalho
 * @since 12/13/2014
 * @version 1.0
 * @see com.obdo.controllers.SessionControllerSingleton
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

    /**
     * Initialize parameters for test
     * @since 12/13/2014
     */
    private void init() {
        sessionControllerSingleton = SessionControllerSingleton.getInstance(getInstrumentation().getContext());
        sessionControllerSingleton.setPhoneNumber(phoneNumber);
        sessionControllerSingleton.setUID(uid);
        sessionControllerSingleton.setNick(nick);
    }

    /**
     * Test is class is creating a singleton
     * @since 12/13/2014
     */
    @SmallTest
    public void testSingleton() throws Exception {
        sone = SessionControllerSingleton.getInstance(getInstrumentation().getContext());
        stwo = SessionControllerSingleton.getInstance(getInstrumentation().getContext());
        assertSame(sone, stwo);
    }

    /**
     * @since 12/13/2014
     */
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

    /**
     * @since 12/13/2014
     */
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

    /**
     * @since 12/13/2014
     */
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

    /**
     * @since 12/13/2014
     */
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
