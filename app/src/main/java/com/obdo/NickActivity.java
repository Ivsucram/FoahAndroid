package com.obdo;

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

import com.obdo.com.obdo.controllers.SessionControllerSingleton;

/**
 * The user will be able to update his nickname information this screen
 *
 * @author Marcus Vinícius de Carvalho
 * @since 12/12/2014
 * @version 1.0
 * @see com.obdo.com.obdo.controllers.SessionControllerSingleton
 */
public class NickActivity extends ActionBarActivity {
    private EditText editText;
    private Button button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nick);

        editText = (EditText) findViewById(R.id.editText2);
        button = (Button) findViewById(R.id.button2);

        onCreateEditText();
        onCreateButton();
    }

    //TODO: Complete javadoc with name of application activity
    /**
     * Update User nickname. Call ... application main activitity ... after it
     * @since 12/12/2014
     * @see com.obdo.com.obdo.controllers.SessionControllerSingleton
     * @see ... main application ...
     */
    public void updateUserNickname() {
        SessionControllerSingleton sessionControllerSingleton = SessionControllerSingleton.getInstance();
        sessionControllerSingleton.setNick(editText.getText().toString());
        sessionControllerSingleton.updateNickUser();
        Intent intent = new Intent(NickActivity.this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Initialize EditText and its behaviors.
     * @since 12/12/2014
     * @see android.widget.EditText
     */
    public void onCreateEditText() {
        editText.setHint("Type your name");

        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;

                if (actionId == EditorInfo.IME_ACTION_DONE) {
                    updateUserNickname();
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
                //TODO: refactor with regular expressions
                String letters = "abcdefghijklmnopqrstuvwxyz";
                String numbers = "0123456789";
                String possibleValues = letters.toLowerCase() + letters.toUpperCase() + numbers;

                if (!possibleValues.contains(String.valueOf(s.charAt(s.length()-1)))) {
                    editText.setText(editText.getText());
                } else if (!s.toString().isEmpty() && numbers.contains(String.valueOf(s.charAt(0)))) {
                    editText.setText(editText.getText());
                } else if (!s.toString().isEmpty() && letters.toLowerCase().contains(String.valueOf(s.charAt(0)))) {
                    editText.setText(s.toString().toUpperCase());
                }

                editText.setSelection(editText.getText().length());
            }

            @Override
            public void afterTextChanged(Editable s) {}
        });
    }

    /**
     * Initialize Button and its behaviors.
     * @since 12/12/2014
     * @see android.widget.Button
     */
    public void onCreateButton() {
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateUserNickname();
            }
        });
    }

}
