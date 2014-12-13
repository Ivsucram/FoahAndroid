package com.obdo.views;

import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;

import com.obdo.LoginRegistrationActivity;
import com.obdo.R;

/**
 * Unit tests for class LoginRegistrationAcvitity
 * @author Marcus Vinícius de Carvalho
 * @since 12/11/2014
 * @version 1.0
 * @see com.obdo.LoginRegistrationActivity
 */
public class LoginRegistrationActivityTest extends ActivityInstrumentationTestCase2<LoginRegistrationActivity> {
    private LoginRegistrationActivity loginRegistrationActivity;
    private EditText editText;
    private Button button;

    public LoginRegistrationActivityTest() {
        super(LoginRegistrationActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();


        loginRegistrationActivity = getActivity();
        assertNotNull(loginRegistrationActivity);
        editText = (EditText) loginRegistrationActivity.findViewById(R.id.editText);
        button = (Button) loginRegistrationActivity.findViewById(R.id.button);
    }

    /**
     * Verify that the editText is initialized with the correct default values
     * @since 12/11/2014
     */
    @SmallTest
    public void testInfoEditTextDefault() {
        assertTrue(editText.getText().toString().isEmpty());
        assertEquals("+91 123456789", editText.getHint());
    }

    /**
     * Verify that the button is initialized with the correct default values
     * @since 12/11/2014
     */
    @SmallTest
    public void testInfoButtonDefault() {
        assertEquals("OK", button.getText());
    }

    /**
     * Verify that the editText is behaving correctly to the user inputs
     * @since 12/11/2014
     */
    @MediumTest
    public void testInfoEditText() {
        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                editText.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("+++");
        getInstrumentation().waitForIdleSync();
        assertEquals("+", editText.getText().toString().trim());

        getInstrumentation().sendStringSync("123");
        getInstrumentation().waitForIdleSync();
        assertEquals("+123", editText.getText().toString().trim());

        getInstrumentation().sendStringSync("a");
        getInstrumentation().waitForIdleSync();
        assertEquals("+123", editText.getText().toString().trim());
    }

    //TODO: test SMS received
    //TODO: test send to nick activity
    //TODO: test send to main activity
}
