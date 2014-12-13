package com.obdo.controllers;

import android.content.Context;

import com.litesuits.http.LiteHttpClient;

import org.apache.http.Header;
import org.apache.http.HeaderIterator;
import org.apache.http.HttpRequest;
import org.apache.http.ProtocolVersion;
import org.apache.http.RequestLine;
import org.apache.http.params.HttpParams;

/**
 * Created by Marcus Vinícius de Carvalho on 12/10/2014.
 */
public class HTTPRequestController  {
    private LiteHttpClient liteHttpClient;

    public HTTPRequestController(Context context) {
        liteHttpClient = LiteHttpClient.newApacheHttpClient(context);
    }
}
