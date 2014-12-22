package com.obdo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ViewSwitcher;

/**
 * Here is where the most important functions of the application will happen
 * //TODO: Continue javadoc
 *
 * @author Marcus Vinícius de Carvalho
 * @since 12/23/2014
 * @version 1.0
 */
public class ObdoActivity extends ActionBarActivity {
    /**
     * Custom Action Bar
     * @since 12/23/2014
     * @see com.obdo.CustomActionBar
     */
    private CustomActionBar customActionBar;
    /**
     * View Switcher that will hold the map visualization view and the list visualization view
     * @since 12/23/2014
     * @see android.widget.ViewSwitcher
     */
    private ViewSwitcher viewSwitcher;
    /**
     * Custom buttons to change the View Switcher and go to the write Post View
     * @since 12/23/2014
     * @see android.widget.ViewSwitcher
     * @see com.obdo.WritePostActivity
     */
    private CustomWritePostChangeViewImageButton customWritePostChangeViewImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obdo);

        onCreateCustomActionBar();
        onCreateViewSwitcher();
        onCreateCustomWritePostChangeViewImageButton();
    }

    /**
     * Initialize customActionBar and its behaviors.
     * @since 12/23/2014
     * @see com.obdo.CustomActionBar
     */
    private void onCreateCustomActionBar() {
        customActionBar = new CustomActionBar(this);
        customActionBar.setScreenTitle("Obdo");
    }

    /**
     * Initialize viewSwitcher and its behaviors.
     * @since 12/23/2014
     * @see android.widget.ViewSwitcher
     */
    private void onCreateViewSwitcher() {
        viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
        //TODO: Add Map View
        //TODO: Add List View
    }

    /**
     * Initialize customWritePostChangeViewImageButton and its behaviors.
     * @since 12/23/2014
     * @see com.obdo.CustomWritePostChangeViewImageButton
     */
    private void onCreateCustomWritePostChangeViewImageButton() {
        customWritePostChangeViewImageButton = new CustomWritePostChangeViewImageButton(this, viewSwitcher);
    }
}
