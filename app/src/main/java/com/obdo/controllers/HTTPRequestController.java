package com.obdo.controllers;

import android.content.Context;

import com.litesuits.http.LiteHttpClient;
import com.litesuits.http.async.HttpAsyncExecutor;
import com.litesuits.http.request.Request;
import com.litesuits.http.response.Response;

/**
 * Class that controls HTTP Requests for the several calls that the app needs
 * @author Marcus Vinícius de Carvalho
 * @since 12/13/2014
 * @version 1.0
 * @see com.litesuits.http.LiteHttpClient
 */
public class HTTPRequestController  {
    private LiteHttpClient liteHttpClient;
    private String server = "http://www.obdo.com";

    public HTTPRequestController(Context context) {
        liteHttpClient = LiteHttpClient.newApacheHttpClient(context);
    }

    private Response SynchronousRequest(String url) {
        return liteHttpClient.execute(new Request(url));
    }

    public boolean checkUserExists(String phoneNumber, String uid) {
        return true;
    }

    public boolean registerUser(String phoneNumber, String uid) {
        return true;
    }

    public boolean loginUser(String phoneNumber, String uid) {
        return true;
    }

    public boolean updateNickUser(String phoneNumber, String uid, String nick) {
        return true;
    }

    public boolean checkSMS(String phoneNumber, String uid, String sms) {
        return true;
    }
}
