package com.obdo;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
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

import java.util.HashMap;
import java.util.Map;

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
        httpRequestController.checkPhoneIsActivated();

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
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {}

            @Override
            public void afterTextChanged(Editable s) {
                String newText = s.toString();
                String possibleValues = "+0123456789";

                //If typed value is null or empty, do nothing
                if (newText == null || newText.isEmpty()) return;

                //Make sure that we only have valid characters
                for (int i = 0; i < newText.length() && i <= 15 ; i++) {
                    if (!possibleValues.contains(String.valueOf(newText.charAt(i)))) {
                        String beforeText = newText.substring(0,i==0?0:i);
                        String afterText = i+1>=newText.length()?"":newText.substring(i+1);
                        newText = beforeText+afterText;
                    }
                }

                //Make sure that we have a '+' as first character
                if (!newText.isEmpty() && newText.charAt(0)!='+') {
                    newText="+"+newText;
                }

                //Make sure that we have only one '+'
                int j = 0;
                for (int i = 0; i < newText.length() && i <=15; i++) {
                    j = newText.charAt(i)=='+'?j+1:1;
                }
                if (j > 1) {
                    for (int i = newText.length() - 1; i > 0 ; i--) {
                        if (newText.charAt(i)=='+') {
                            String beforeText = newText.substring(0,i==0?0:i);
                            String afterText = i+1>=newText.length()?"":newText.substring(i+1);
                            newText = beforeText+afterText;
                        }
                    }
                }

                //Make sure that we always have a limit of 30 characters
                newText=newText.length()>15?newText.substring(0,15):newText;

                if (!newText.equals(editTextPhoneNumber.getText().toString())) editTextPhoneNumber.setText(newText);
                editTextPhoneNumber.setSelection(newText.length());
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

    /**
     * Enum with keys for the shared preferences file
     */
    private enum SharedPreferencesUserInformationENUM {
        PHONENUMBER, UID
    }

    public HTTPRequestLoginRegisrationController(Activity activity) {
        this.activity = activity;
        liteHttpClient = LiteHttpClient.newApacheHttpClient(activity.getApplicationContext());
        asyncExecutor = HttpAsyncExecutor.newInstance(liteHttpClient);
        serverAddress = activity.getApplicationContext().getString(R.string.server_address);
    }

    public void checkPhoneIsActivated() {
        Map<SharedPreferencesUserInformationENUM, String> dictionary = restorePreferences();
        if (dictionary.get(SharedPreferencesUserInformationENUM.PHONENUMBER)==null || dictionary.get(SharedPreferencesUserInformationENUM.UID)==null) return;

        Request request = new Request(serverAddress)
                .setMethod(HttpMethod.Get)
                .addUrlPrifix("http://")
                .addUrlSuffix(activity.getApplicationContext().getString(R.string.url_check_phone_activated_GET))
                .addUrlParam("number", dictionary.get(SharedPreferencesUserInformationENUM.PHONENUMBER))
                .addUrlParam("uid", dictionary.get(SharedPreferencesUserInformationENUM.UID))
                .addHeader("Accept", "application/json");

        asyncExecutor.execute(request, new HttpResponseHandler() {
            @Override
            protected void onSuccess(Response res, HttpStatus status, NameValuePair[] headers) {
                JsonParser jsonParser = new JsonParser();
                JsonObject jsonObject = jsonParser.parse(res.getString()).getAsJsonObject();
                if (jsonObject.get("success").getAsBoolean()) {
                    Intent intent = new Intent(activity, ObdoActivity.class);
                    activity.startActivity(intent);
                }
            }

            @Override
            protected void onFailure(Response res, HttpException e) {
                Toast.makeText(activity.getApplicationContext(), e.toString(), Toast.LENGTH_LONG).show();
            }
        });
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
    public void registerUser(final String phoneNumber,final String uid) {
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
                    savePreferences(phoneNumber, uid);
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
    public void loginUser(final String phoneNumber,final String uid) {
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
                    savePreferences(phoneNumber, uid);
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

    /**
     * Save Shared Preferences with user information: Phonenumber and smartphone UID
     * @param phoneNumber User cellphone to be saved
     * @param uid smartphone UID to be saved
     */
    private void savePreferences(String phoneNumber, String uid) {
        SharedPreferences settings = activity.getSharedPreferences(activity.getApplicationContext().getString(R.string.preferencesFileName), 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(activity.getApplicationContext().getString(R.string.phoneNumber),phoneNumber);
        editor.putString(activity.getApplicationContext().getString(R.string.uid), uid);
        editor.commit();
    }

    /**
     * Load Shared Preferences with user information: phonenumber and smartphone UID
     * @return a dictionary with the phonenumber and smartphone UID values
     */
    private Map<SharedPreferencesUserInformationENUM, String> restorePreferences() {
        SharedPreferences settings = activity.getSharedPreferences(activity.getApplicationContext().getString(R.string.preferencesFileName), 0);
        String phoneNumber = settings.getString(activity.getApplicationContext().getString(R.string.phoneNumber), null);
        String uid = settings.getString(activity.getApplicationContext().getString(R.string.uid), null);
        Map<SharedPreferencesUserInformationENUM, String> dictionary = new HashMap<SharedPreferencesUserInformationENUM, String>();
        dictionary.put(SharedPreferencesUserInformationENUM.PHONENUMBER, phoneNumber);
        dictionary.put(SharedPreferencesUserInformationENUM.UID, uid);
        return dictionary;
    }
}
