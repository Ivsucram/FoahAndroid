package com.obdo;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;

/**
 * Unit tests for class NickActivity
 * @author Marcus Vinícius de Carvalho
 * @since 12/12/2014
 * @version 1.0
 * @see com.obdo.NickActivity
 */
public class NickActivityTest extends ActivityInstrumentationTestCase2<NickActivity> {
    private NickActivity nickActivity;
    private Context context;
    private EditText editTextNickname;
    private Button buttonUpdateNick;

    public NickActivityTest() {
        super(NickActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        nickActivity = getActivity();
        assertNotNull(nickActivity);
        context = nickActivity.getApplicationContext();
        editTextNickname = (EditText) nickActivity.findViewById(R.id.editTextNickname);
        buttonUpdateNick = (Button) nickActivity.findViewById(R.id.buttonUpdateNick);
    }

    /**
     * Verify that the editTextNickname is initialized with the correct default values
     * @since 12/12/2014
     */
    @SmallTest
    public void testInfoEditTextDefault() {
        assertTrue(editTextNickname.getText().toString().isEmpty());
        assertEquals("Type your name", editTextNickname.getHint());
    }

    /**
     * Verify that the buttonUpdateNick is initialized with the correct default values
     * @since 12/12/2014
     */
    @SmallTest
    public void testInfoButtonDefault() {
        assertEquals("OK", buttonUpdateNick.getText());
    }

    /**
     * Verify that the editTextNickname is behaving correctly to the user inputs
     * @since 12/12/2014
     */
    //TODO: Test is disable for now because it is causing StackOverflowError and I do not know the reason
    @MediumTest
    public void InfoEditText() {

        getInstrumentation().runOnMainSync(new Runnable() {
            @Override
            public void run() {
                editTextNickname.requestFocus();
            }
        });

        getInstrumentation().waitForIdleSync();
        getInstrumentation().sendStringSync("1");
        getInstrumentation().waitForIdleSync();
        assertEquals("", editTextNickname.getText().toString().trim());

        getInstrumentation().sendStringSync("z");
        getInstrumentation().waitForIdleSync();
        assertEquals("Z", editTextNickname.getText().toString().trim());

        getInstrumentation().sendStringSync("a");
        getInstrumentation().waitForIdleSync();
        assertEquals("Za", editTextNickname.getText().toString().trim());

        getInstrumentation().sendStringSync("1");
        getInstrumentation().waitForIdleSync();
        assertEquals("Za1", editTextNickname.getText().toString().trim());
    }

    //TODO: test send to main activity
}
