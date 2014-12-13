package com.obdo.views;

import android.content.Context;
import android.test.ActivityInstrumentationTestCase2;
import android.test.suitebuilder.annotation.MediumTest;
import android.test.suitebuilder.annotation.SmallTest;
import android.widget.Button;
import android.widget.EditText;

import com.obdo.NickActivity;
import com.obdo.R;

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
    private EditText editText;
    private Button button;

    public NickActivityTest() {
        super(NickActivity.class);
    }

    @Override
    protected void setUp() throws Exception {
        super.setUp();

        nickActivity = getActivity();
        assertNotNull(nickActivity);
        context = nickActivity.getApplicationContext();
        editText = (EditText) nickActivity.findViewById(R.id.editText2);
        button = (Button) nickActivity.findViewById(R.id.button2);
    }

    /**
     * Verify that the editText is initialized with the correct default values
     * @since 12/12/2014
     */
    @SmallTest
    public void testInfoEditTextDefault() {
        assertTrue(editText.getText().toString().isEmpty());
        assertEquals("Type your name", editText.getHint());
    }

    /**
     * Verify that the button is initialized with the correct default values
     * @since 12/12/2014
     */
    @SmallTest
    public void testInfoButtonDefault() {
        assertEquals("OK", button.getText());
    }

    /**
     * Verify that the editText is behaving correctly to the user inputs
     * @since 12/12/2014
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
        getInstrumentation().sendStringSync("1");
        getInstrumentation().waitForIdleSync();
        assertEquals("", editText.getText().toString().trim());

        getInstrumentation().sendStringSync("z");
        getInstrumentation().waitForIdleSync();
        assertEquals("Z", editText.getText().toString().trim());

        getInstrumentation().sendStringSync("a");
        getInstrumentation().waitForIdleSync();
        assertEquals("Za", editText.getText().toString().trim());

        getInstrumentation().sendStringSync("1");
        getInstrumentation().waitForIdleSync();
        assertEquals("Za1", editText.getText().toString().trim());
    }

    //TODO: test send to main activity
}
