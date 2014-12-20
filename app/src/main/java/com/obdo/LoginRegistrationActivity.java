package com.obdo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.support.v7.app.ActionBarActivity;
import android.telephony.TelephonyManager;
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
 * This is the main activity of the application. It have 3 main behaviors:
 * 1. It is the first time the application be opened and the user is not registered
 * a) Asks for the user's cellphone number
 * b) Send cellphone number + Smarthphone UID to server
 * c) Receive SMS from server
 * d) Confirm registration
 * e) Transfer to com.obdo.NickActivity
 *
 * 2. It is not the first time the application be opened but the user is not logged in
 * a) Asks for the user's cellphone number
 * b) Send cellphone number + Smartphone UID to server
 * c) Receive SMS from server
 * d) Confirm login
 * e) Transfer to Obdo Activity
 *
 * 3. It is not the first time the application be opened and user is already logged
 * a) Transfer to Obdo Activity
 *
 * @author Marcus Vinícius de Carvalho
 * @since 12/10/2014
 * @version 1.0
 * @see com.obdo.NickActivity
 * @see com.obdo.ObdoActivity
 */
//TODO: Check if user is already logged - Save instance of the user on the cellphone
public class LoginRegistrationActivity extends ActionBarActivity {
    /**
     * EditText that will hold user cellphone number
     * @since 12/10/2014
     * @see android.widget.EditText
     */
    private EditText editTextPhoneNumber;
    /**
     * Button to confirm Login/Registration task
     * @since 12/10/2014
     * @see android.widget.Button
     */
    private Button buttonLoginRegister;
    private HTTPRequestLoginRegisrationController httpRequestController;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registration);

        httpRequestController = new HTTPRequestLoginRegisrationController(this);

        onCreateEditTextPhoneNumber();
        onCreateButtonLoginRegister();
    }

    /**
     * Initialize EditText and its behaviors.
     * Field is initialized with  line 1 number of the cellphone. In case it is not possible to retrieve the line 1 number, the field will be initialized with null.
     * @since 12/10/2014
     * @see android.widget.EditText
     * @see android.telephony.TelephonyManager
     */
    private void onCreateEditTextPhoneNumber() {
        editTextPhoneNumber = (EditText) findViewById(R.id.editTextPhoneNumber);

        TelephonyManager telephonyManager = (TelephonyManager)getApplicationContext().getSystemService(getApplicationContext().TELEPHONY_SERVICE);
        editTextPhoneNumber.setText(telephonyManager.getLine1Number());

        editTextPhoneNumber.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;

                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    String phoneNumber = editTextPhoneNumber.getText().toString();
                    String uid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                    httpRequestController.checkUserExists(phoneNumber, uid);
                    handled = true;
                }

                return handled;
            }
        });

        editTextPhoneNumber.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                if (after<0)
                    editTextPhoneNumber.setText("");
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String possibleValues = "+0123456789";

                if (!s.toString().isEmpty() && !possibleValues.contains(String.valueOf(s.charAt(s.length() - 1)))) {
                    editTextPhoneNumber.setText(editTextPhoneNumber.getText());
                } else if (!s.toString().isEmpty() && s.charAt(0) != '+') {
                    editTextPhoneNumber.setText("+" + s.toString());
                } else if (editTextPhoneNumber.getText().length() > 15){
                    editTextPhoneNumber.setText(editTextPhoneNumber.getText());
                } else if (s.toString().contains("+")) {
                    int j = 0;
                    for (int i = 0; i < s.length(); i++) {
                        j = s.charAt(i) == '+' ? j + 1 : j;
                        if (j > 1) {
                            editTextPhoneNumber.setText(s.subSequence(0, s.toString().length() - 1));
                            break;
                        }
                    }
                }
                editTextPhoneNumber.setSelection(editTextPhoneNumber.getText().length());
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });
    }

    /**
     * Initialize Button and its behaviors.
     * @since 12/10/2014
     * @see android.widget.Button
     */
    private void onCreateButtonLoginRegister() {
        buttonLoginRegister = (Button) findViewById(R.id.buttonLoginRegister);

        buttonLoginRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editTextPhoneNumber.getText() == null || editTextPhoneNumber.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your phone number before proceed", Toast.LENGTH_LONG).show();
                } else {
                    //loginRegisterUser();
                    String phoneNumber = editTextPhoneNumber.getText().toString();
                    String uid = Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID);
                    httpRequestController.checkUserExists(phoneNumber, uid);
                }
            }
        });
    }
}

/**
 * HTTP Request Controller for the LoginRegistrationActivity
 * This class will handle every HTTP Request that the LoginRegistrationActivity needs, as well as UI manipulation
 * @author Marcus Vinícius de Carvalho
 * @since 12/20/2014
 * @version 1.0
 */
class HTTPRequestLoginRegisrationController  {
    private LiteHttpClient liteHttpClient;
    private HttpAsyncExecutor asyncExecutor;
    private Activity activity;
    private String serverAddress;

    public HTTPRequestLoginRegisrationController(Activity activity) {
        this.activity = activity;
        liteHttpClient = LiteHttpClient.newApacheHttpClient(activity.getApplicationContext());
        asyncExecutor = HttpAsyncExecutor.newInstance(liteHttpClient);
        serverAddress = activity.getApplicationContext().getString(R.string.server_address);
    }

    /**
     * Check if user cellphone + UID exist on the server database
     * If yes: login user
     * If no: register user
     * @param phoneNumber User cellphone
     * @param uid smartphone UID
     */
    public void checkUserExists(final String phoneNumber, final String uid) {
        Request request = new Request(serverAddress)
                .setMethod(HttpMethod.Get)
                .addUrlPrifix("http://")
                .addUrlSuffix(activity.getApplicationContext().getString(R.string.url_check_user_exists_GET))
                .addUrlParam("number", phoneNumber)
                .addHeader("Accept", "application/json");

        asyncExecutor.execute(request, new HttpResponseHandler() {
            @Override
            protected void onSuccess(Response res, HttpStatus status, NameValuePair[] headers) {
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(res.getString()).getAsJsonObject();
                if (jsonObject.get("success").getAsBoolean()) {
                    loginUser(phoneNumber, uid);
                } else {
                    registerUser(phoneNumber,uid);
                }
            }

            @Override
            protected void onFailure(Response res, HttpException e) {
                Toast.makeText(activity.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Register user on the server database and login it on the cellphone
     * @param phoneNumber user cellphone
     * @param uid smartphone UID
     */
    public void registerUser(final String phoneNumber, String uid) {
        Request request = new Request(serverAddress)
                .setMethod(HttpMethod.Post)
                .addUrlPrifix("http://")
                .addUrlSuffix(activity.getApplicationContext().getString(R.string.url_register_user_POST))
                .addUrlParam("number", phoneNumber)
                .addUrlParam("uid", uid)
                .addHeader("Accept", "application/json");

        asyncExecutor.execute(request, new HttpResponseHandler() {
            @Override
            protected void onSuccess(Response res, HttpStatus status, NameValuePair[] headers) {
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(res.getString()).getAsJsonObject();
                if (jsonObject.get("success").getAsBoolean()) {
                    //TODO: check SMS
                    //TODO: Save user information on the cellphone to make it logged
                    Intent intent = new Intent(activity, NickActivity.class);
                    intent.putExtra("EXTRA_PHONE_NUMBER", phoneNumber);
                    activity.startActivity(intent);
                } else {
                    Toast.makeText(activity.getApplicationContext(), jsonObject.get("message").toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected void onFailure(Response res, HttpException e) {
                Toast.makeText(activity.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Login user (new cellphone) on the server
     * @param phoneNumber User cellphone
     * @param uid Smartphone UID
     */
    public void loginUser(String phoneNumber, String uid) {
        Request request = new Request(serverAddress)
                .setMethod(HttpMethod.Post)
                .addUrlPrifix("http://")
                .addUrlSuffix(activity.getApplicationContext().getString(R.string.url_user_login_POST))
                .addUrlParam("number", phoneNumber)
                .addUrlParam("uid", uid)
                .addHeader("Accept", "application/json");

        asyncExecutor.execute(request, new HttpResponseHandler() {
            @Override
            protected void onSuccess(Response res, HttpStatus status, NameValuePair[] headers) {
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(res.getString()).getAsJsonObject();
                if (jsonObject.get("success").getAsBoolean()) {
                    //TODO: check SMS
                    //TODO: save user information on the smartphone
                    Intent intent = new Intent(activity, ObdoActivity.class);
                    activity.startActivity(intent);
                } else {
                    Toast.makeText(activity.getApplicationContext(), jsonObject.get("message").toString(), Toast.LENGTH_LONG).show();
                }
            }

            @Override
            protected void onFailure(Response res, HttpException e) {
                Toast.makeText(activity.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        });
    }
}
