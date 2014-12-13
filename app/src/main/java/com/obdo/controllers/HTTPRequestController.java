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

    public HTTPRequestController(Context context) {
        liteHttpClient = LiteHttpClient.newApacheHttpClient(context);
    }

    private Response SynchronousRequest(String url) {
        return liteHttpClient.execute(new Request(url));
    }
}
