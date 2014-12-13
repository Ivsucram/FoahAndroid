package com.obdo;

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

import com.obdo.controllers.SessionControllerSingleton;

//TODO: Complete javadoc with name of application activity
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
 * e) Transfer to ... application acvitity ...
 *
 * 3. It is not the first time the application be opened and user is already logged
 * a) Transfer to ...  application activity ...
 *
 * @author Marcus Vinícius de Carvalho
 * @since 12/10/2014
 * @version 1.0
 * @see com.obdo.controllers.SessionControllerSingleton
 * @see com.obdo.NickActivity
 */
public class LoginRegistrationActivity extends ActionBarActivity {
    /**
     * EditText that will hold user cellphone number
     * @since 12/10/2014
     * @see android.widget.EditText
     */
    private EditText editText;
    /**
     * Button to confirm Login/Registration task
     * @since 12/10/2014
     * @see android.widget.Button
     */
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_registration);

        editText = (EditText) findViewById(R.id.editText);
        button = (Button) findViewById(R.id.button);

        onCreateEditText();
        onCreateButton();
    }


    //TODO: Complete javadoc with name of application activity
    /**
     * Register user if he have no account yet, Login user if he have already have account. Call NickActivity after it.
     * If user already have account, login it. Call ... application main activity ... after it
     * @since 12/10/2014
     * @see com.obdo.controllers.SessionControllerSingleton
     * @see com.obdo.NickActivity
     */
    private void loginRegisterUser() {
        SessionControllerSingleton sessionControllerSingleton = SessionControllerSingleton.getInstance(getApplicationContext());
        sessionControllerSingleton.setPhoneNumber(editText.getText().toString());
        sessionControllerSingleton.setUID(Settings.Secure.getString(getApplicationContext().getContentResolver(), Settings.Secure.ANDROID_ID));
        sessionControllerSingleton.createSMSBroadcastReceiver(getApplicationContext(), getIntent());
        if (sessionControllerSingleton.checkUserExists()) {
            sessionControllerSingleton.loginUser();
            if (sessionControllerSingleton.isSmsCheck()) {
                Intent intent = new Intent(LoginRegistrationActivity.this, MainActivity.class);
                startActivity(intent);
            }
        } else {
            sessionControllerSingleton.registerUser();
            if (sessionControllerSingleton.isSmsCheck()) {
                Intent intent = new Intent(LoginRegistrationActivity.this, NickActivity.class);
                startActivity(intent);
            }
        }
    }

    /**
     * Initialize EditText and its behaviors.
     * Field is initialized with  line 1 number of the cellphone. In case it is not possible to retrieve the line 1 number, the field will be initialized with null.
     * @since 12/10/2014
     * @see android.widget.EditText
     * @see android.telephony.TelephonyManager
     */
    private void onCreateEditText() {
        TelephonyManager telephonyManager = (TelephonyManager)getApplicationContext().getSystemService(getApplicationContext().TELEPHONY_SERVICE);
        editText.setText(telephonyManager.getLine1Number());

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;

                if (actionId == EditorInfo.IME_ACTION_SEND) {
                    loginRegisterUser();
                    handled = true;
                }

                return handled;
            }
        });

        editText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {}

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String possibleValues = "+0123456789";

                if (!possibleValues.contains(String.valueOf(s.charAt(s.length()-1)))) {
                    editText.setText(editText.getText());
                } else if (!s.toString().isEmpty() && s.charAt(0)!='+') {
                    editText.setText("+"+s.toString());
                } else if (s.toString().contains("+"))  {
                    int j = 0;
                    for (int i = 0; i < s.length(); i++) {
                        j = s.charAt(i)=='+'?j+1:j;
                        if (j > 1) {
                            editText.setText(s.subSequence(0,s.toString().length()-1));
                            break;
                        }
                    }
                }
                editText.setSelection(editText.getText().length());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    /**
     * Initialize Button and its behaviors.
     * @since 12/10/2014
     * @see android.widget.Button
     */
    private void onCreateButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (editText.getText() == null || editText.getText().toString().isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Please enter your phone number before proceed", Toast.LENGTH_LONG).show();
                } else {
                    loginRegisterUser();
                }
            }
        });
    }

}
