package com.obdo;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.KeyEvent;
import android.view.View;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
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

/**
 * The user will be able to update his nickname information at this screen
 *
 * @author Marcus Vinícius de Carvalho
 * @since 12/12/2014
 * @version 1.0
 */
public class NickActivity extends ActionBarActivity {
    /**
     * EditText that hold user nickname
     * @since 12/13/2014
     * @see android.widget.EditText
     */
    private EditText editTextNickname;
    /**
     * Button to confirm nickname update
     * @since 12/13/2014
     * @see android.widget.Button
     */
    private Button buttonUpdateNick;
    private HTTPRequestNickController httpRequestController;
    private String phoneNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick);
        phoneNumber = getIntent().getStringExtra("EXTRA_PHONE_NUMBER");

        httpRequestController = new HTTPRequestNickController(this);

        onCreateEditTextNickname();
        onCreateButtonUpdateNick();
    }

    /**
     * Update User nickname. Call Obdo Activity after it
     * @since 12/12/2014
     * @see com.obdo.controllers.SessionControllerSingleton
     * @see com.obdo.ObdoActivity
     */
    /*public void updateUserNickname() {
        SessionControllerSingleton sessionControllerSingleton = SessionControllerSingleton.getInstance(getApplicationContext());
        sessionControllerSingleton.setNick(editTextNickname.getText().toString());
        sessionControllerSingleton.updateNickUser();
        Intent intent = new Intent(NickActivity.this, ObdoActivity.class);
        startActivity(intent);
    }*/

    /**
     * Initialize EditText and its behaviors.
     * @since 12/12/2014
     * @see android.widget.EditText
     */
    public void onCreateEditTextNickname() {
        editTextNickname = (EditText) findViewById(R.id.editTextNickname);

        editTextNickname.setHint("Type your name");

        editTextNickname.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    //updateUserNickname();
                    handled = true;
                }

                return handled;
            }
        });

        editTextNickname.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                //TODO: refactor with regular expressions
                String letters = "abcdefghijklmnopqrstuvwxyz";
                String numbers = "0123456789";
                String possibleValues = letters.toLowerCase() + letters.toUpperCase() + numbers;

                if (!s.toString().isEmpty() && !possibleValues.contains(String.valueOf(s.charAt(s.length() - 1)))) {
                    editTextNickname.setText(editTextNickname.getText());
                } else if (!s.toString().isEmpty() && numbers.contains(String.valueOf(s.charAt(0)))) {
                    editTextNickname.setText(editTextNickname.getText());
                } else if (!s.toString().isEmpty() && letters.toLowerCase().contains(String.valueOf(s.charAt(0)))) {
                    editTextNickname.setText(s.toString().toUpperCase());
                } else if (editTextNickname.getText().length() > 30) {
                    editTextNickname.setText(editTextNickname.getText());
                }

                editTextNickname.setSelection(editTextNickname.getText().length());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * Initialize Button and its behaviors.
     * @since 12/12/2014
     * @see android.widget.Button
     */
    public void onCreateButtonUpdateNick() {
        buttonUpdateNick = (Button) findViewById(R.id.buttonUpdateNick);

        buttonUpdateNick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                httpRequestController.updateUserNickname(phoneNumber,editTextNickname.getText().toString());
            }
        });
    }
}

/**
 * HTTP Request Controller for the NickActivity
 * This class will handle every HTTP Request that the NickActivity needs, as well as UI manipulation
 * @author Marcus Vinícius de Carvalho
 * @since 12/20/2014
 * @version 1.0
 */
class HTTPRequestNickController  {
    private LiteHttpClient liteHttpClient;
    private HttpAsyncExecutor asyncExecutor;
    private Activity activity;
    private String serverAddress;

    public HTTPRequestNickController(Activity activity) {
        this.activity = activity;
        liteHttpClient = LiteHttpClient.newApacheHttpClient(activity.getApplicationContext());
        asyncExecutor = HttpAsyncExecutor.newInstance(liteHttpClient);
        serverAddress = activity.getApplicationContext().getString(R.string.server_address);
    }

    /**
     * Update user's name at the server
     * @param phoneNumber User cellphone number
     * @param nick new nickname
     */
    public void updateUserNickname(String phoneNumber, String nick) {
        Request request = new Request(serverAddress)
                .setMethod(HttpMethod.Post)
                .addUrlPrifix("http://")
                .addUrlSuffix(activity.getApplicationContext().getString(R.string.url_update_user_nickname_POST))
                .addUrlParam("number", phoneNumber)
                .addUrlParam("name", nick)
                .addHeader("Accept", "application/json");

        asyncExecutor.execute(request, new HttpResponseHandler() {
            @Override
            protected void onSuccess(Response res, HttpStatus status, NameValuePair[] headers) {
                Intent intent = new Intent(activity, ObdoActivity.class);
                activity.startActivity(intent);
            }

            @Override
            protected void onFailure(Response res, HttpException e) {
                Toast.makeText(activity.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
