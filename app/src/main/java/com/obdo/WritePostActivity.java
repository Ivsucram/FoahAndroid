package com.obdo;

import android.app.Activity;
import android.location.LocationManager;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
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
import com.obdo.controllers.SharedPreferencesController;
import com.obdo.data.models.Location;
import com.obdo.data.models.Post;
import com.obdo.data.models.User;
import com.obdo.data.repos.Repo;

import java.util.Map;

/**
 * Activity where the user will be able to write posts.
 * The user can:
 * - Select the target of the message (Public or Private to some friends
 * - Write the Post
 * - Attach Assets (Photos or Videos) to the post
 * - Define when the Post will be live
 * - Define the visibility radius of the post
 * @author Marcus Vinícius de Carvalho
 * @since 12/23/2014
 * @version 1
 */
public class WritePostActivity extends ActionBarActivity {
    /**
     * EdiText that holds the target to receive the message
     * @since 12/23/2014
     */
    private EditText editTextMessageTarget;
    /**
     * EditText that holds the post text
     * @since 12/23/2014
     */
    private EditText editTextPostText;
    /**
     * ImageButton that holds the function to add assets (Photos and Videos)
     * @since 12/23/2014
     */
    private ImageButton imageButtonAttachPhotoVideo;
    /**
     * ImageButton that holds the function to schedule post to be alive on a future date
     * @since 12/23/2014
     */
    private ImageButton imageButtonSchedulePost;
    /**
     * ImageButton that holds the function to change the visibility radius of the post
     * @since 12/23/2014
     */
    private ImageButton imageButtonVisibilityRadius;
    /**
     * ImageButton that holds the confirmation function to post the message
     * @since 12/23/2014
     */
    private ImageButton imageButtonConfirmPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        onCreateEditTextMessageTarget();
        onCreateEditTextPostText();
        onCreateImageButtonAttachPhotoVideo();
        onCreateImageButtonSchedulePost();
        onCreateImageButtonVisibilityRadius();
        onCreateImageButtonConfirmPost();
    }

    /**
     * Initialize editTextMessageTarget and its behaviors.
     * @since 12/23/2014
     * @see android.widget.EditText
     */
    private void onCreateEditTextMessageTarget() {
        editTextMessageTarget = (EditText) findViewById(R.id.editTextMessageTarget);
        editTextMessageTarget.setText("To: Public");
    }

    /**
     * Initialize editTextPostText and its behaviors.
     * @since 12/23/2014
     * @see android.widget.EditText
     */
    private void onCreateEditTextPostText() {
        editTextPostText = (EditText) findViewById(R.id.editTextPostText);
        editTextPostText.setHint("Write your post");
    }

    /**
     * Initialize imageButtonAttachPhotoVideo and its behaviors.
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private void onCreateImageButtonAttachPhotoVideo() {
        imageButtonAttachPhotoVideo = (ImageButton) findViewById(R.id.imageButtonAttachPhotoVideo);
    }

    /**
     * Initialize imageButtonSchedulePost and its behaviors.
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private void onCreateImageButtonSchedulePost() {
        imageButtonSchedulePost = (ImageButton) findViewById(R.id.imageButtonSchedulePost);
    }

    /**
     * Initialize imageButtonVisibilityRadius and its behaviors.
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private void onCreateImageButtonVisibilityRadius() {
        imageButtonVisibilityRadius = (ImageButton) findViewById(R.id.imageButtonVisibilityRadius);
    }

    /**
     * Initialize imageButtonConfirmPost and its behaviors.
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private void onCreateImageButtonConfirmPost() {
        imageButtonConfirmPost = (ImageButton) findViewById(R.id.imageButtonConfirmPost);

        imageButtonConfirmPost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postMessage();
            }
        });
    }

    /**
     * Post the message on the server
     * It follows the following steps:
     * 1. Recover the logged user information
     * 2. Grab the user location
     * 3. Insert everything on the post and send to the server. If the server saves it, the smartphone also saves it on the internal DB
     * @
     */
    private void postMessage() {
        Repo repo = new Repo(getApplicationContext());

        //Set User
        SharedPreferencesController sharedPreferencesController = new SharedPreferencesController(this);
        User user = repo.Users.getByPhoneNumber(sharedPreferencesController.loadPhoneNumber());

        //Set Location
        getApplicationContext();
        LocationManager locationManager = (LocationManager) this.getSystemService(LOCATION_SERVICE);
        android.location.Location locationTemp = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);

        Location location = new Location();
        location.setLongitude(locationTemp.getLongitude());
        location.setLatitude(locationTemp.getLatitude());
        location.setRadius(0.0);

        //Set Post
        Post post = new Post();
        post.setUser(user);
        post.setLocation(location);
        post.setText(editTextPostText.getText().toString());

        //Write it online
        HTTPRequestWritePostActivity httpRequestWritePostActivity = new HTTPRequestWritePostActivity(this);
        httpRequestWritePostActivity.postMessage(post);
    }
}

/**
 * HTTP Request Controller for the WritePostActivity
 * This class will handle every HTTP Request that the WritePostActivity needs, as weel as UI manipulation
 * @author Marcus Vinícius de Carvalho
 * @since 01/07/2014
 * @version 1.0
 */
class HTTPRequestWritePostActivity {
    /**
     * HTTP Client
     * @since 01/07/2014
     * @see com.litesuits.http.LiteHttpClient
     */
    private LiteHttpClient liteHttpClient;
    /**
     * HTTP Asynchronous Executor
     * @since 01/07/2014
     * @see com.litesuits.http.async.HttpAsyncExecutor
     */
    private HttpAsyncExecutor asyncExecutor;
    /**
     * Activity that calls this class
     * @since 01/07/2014
     */
    private Activity activity;
    /**
     * Server Address saved on the url.xml
     * @since 01/07/2014
     */
    private String serverAddress;

    public HTTPRequestWritePostActivity(Activity activity) {
        this.activity = activity;
        liteHttpClient = LiteHttpClient.newApacheHttpClient(activity.getApplicationContext());
        asyncExecutor = HttpAsyncExecutor.newInstance(liteHttpClient);
        serverAddress = activity.getApplicationContext().getString(R.string.server_address);
    }

    /**
     * Send the Post to the server
     * @param post Post to be sent to the server
     */
    public void postMessage(final Post post) {
        Request request = new Request(serverAddress)
                .setMethod(HttpMethod.Post)
                .addUrlPrifix("http://")
                .addUrlSuffix(activity.getApplicationContext().getString(R.string.url_create_post_POST))
                .addUrlParam("message", post.getText())
                .addUrlParam("number", post.getUser().getPhoneNumber())
                .addUrlParam("location[latitude]", String.valueOf(post.getLocation().getLatitude()))
                .addUrlParam("location[longitude]", String.valueOf(post.getLocation().getLongitude()))
                .addUrlParam("location[radius]", String.valueOf(post.getLocation().getRadius()))
                .addHeader("Accept", "application/json");

        asyncExecutor.execute(request, new HttpResponseHandler() {
            @Override
            protected void onSuccess(Response res, HttpStatus status, NameValuePair[] headers) {
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(res.getString()).getAsJsonObject();
                if (jsonObject.get("success").getAsBoolean()) {
                    Repo repo = new Repo(activity);
                    post.getLocation().save(repo);
                    post.save(repo);
                    activity.finish();
                }
            }

            @Override
            protected void onFailure(Response res, HttpException e) {
                Toast.makeText(activity.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}