package com.obdo.controllers;

import android.app.Activity;
import android.content.Context;
import android.widget.Toast;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.litesuits.http.LiteHttpClient;
import com.litesuits.http.async.HttpAsyncExecutor;
import com.litesuits.http.data.HttpStatus;
import com.litesuits.http.data.NameValuePair;
import com.litesuits.http.exception.HttpException;
import com.litesuits.http.request.Request;
import com.litesuits.http.request.param.HttpMethod;
import com.litesuits.http.response.Response;
import com.litesuits.http.response.handler.HttpResponseHandler;
import com.obdo.R;

/**
 * Created by richard on 1/3/15.
 */
public class HTTPRequestControllerV2 {
    private LiteHttpClient liteHttpClient;
    private HttpAsyncExecutor asyncExecutor;
    private Activity activity;
    private Context context;
    private String serverAddress;

    public HTTPRequestControllerV2(Context context) {
        this.activity = activity;
        this.context = context;
        //liteHttpClient = LiteHttpClient.newApacheHttpClient(activity.getApplicationContext());
        liteHttpClient = LiteHttpClient.newApacheHttpClient(context);
        asyncExecutor = HttpAsyncExecutor.newInstance(liteHttpClient);
        //serverAddress = activity.getApplicationContext().getString(R.string.server_address);
        serverAddress = context.getString(R.string.server_address);
    }

    public void getPosts() {
        Request request = new Request(serverAddress)
                .setMethod(HttpMethod.Get)
                .addUrlPrifix("http://")
                .addUrlSuffix(context.getString(R.string.url_get_posts))
                .addHeader("Accept", "application/json");

        asyncExecutor.execute(request, new HttpResponseHandler() {
            @Override
            protected void onSuccess(Response res, HttpStatus status, NameValuePair[] headers) {
                Toast.makeText(context, String.valueOf(status.getDescription()),Toast.LENGTH_LONG).show();
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(res.getString()).getAsJsonObject();
                /*
                //HttpEntity entity = res.
                InputStream content = res.getInputStream();

                try {
                    Reader reader = new InputStreamReader(content);
                    GsonBuilder gsonBuilder = new GsonBuilder();
                    gsonBuilder.setDateFormat("M/d/yy hh:mm a");
                    Gson gson = gsonBuilder.create();
                    List<PostModel> posts = new ArrayList<PostModel>();
                    posts = Arrays.asList(gson.fromJson(reader, PostModel[].class));
                    content.close();


                } catch (Exception ex) {
                    //Log.e(TAG, )
                }
                */

            }

            @Override
            protected void onFailure(Response res, HttpException e) {
                //TODO: handle failure
                Toast.makeText(activity.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    public class Tag {
        public String name;
        public String url;

        public Tag() {

        }
    }

}
