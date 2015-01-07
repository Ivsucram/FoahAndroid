package com.obdo;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Custom action bar that shows the name of the screen that the user is right now and show the buttons to activate the menu and the search
 * @author Marcus Vinícius de Carvalho
 * @since 12/13/2014
 * @version 1.0
 * @see android.widget.ImageButton
 * @see android.widget.TextView
 */
public class CustomActionBar extends LinearLayout {
    /**
     * ImageButton that hold the menu button
     * @since 12/13/2014
     * @see android.widget.ImageButton
     */
    private ImageButton imageButtonMenu;
    /**
     * Textview that holds the screen name
     * @since 12/13/2014
     * @see android.widget.TextView
     */
    private TextView textViewScreenTitle;
    /**
     * ImageButton that hold the search button
     * @since 12/13/2014
     * @see android.widget.ImageButton
     */
    private ImageButton imageButtonSearch;
    /**
     * Activity that calls this Custom Layout
     * @since 12/22/2014
     * @see android.app.Activity
     */
    private Activity activity;

    public CustomActionBar(Activity activity) {
        super(activity.getApplicationContext());
        this.activity = activity;
        onCreateImageButtonMenu();
        onCreateTextViewScreenTitle();
        onCreateImageButtonSearch();
    }

    /**
     * Initialize ImageButtonMenu and its behaviors.
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private void onCreateImageButtonMenu() {
        imageButtonMenu = (ImageButton) activity.findViewById(R.id.imageButtonMenu);

        imageButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHideMenu();
            }
        });
    }

    /**
     * Initialize TextViewScreenTitle and its behaviors.
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private void onCreateTextViewScreenTitle() {
        textViewScreenTitle = (TextView) activity.findViewById(R.id.textViewScreenTitle);
    }

    /**
     * Initialize ImageButtonSearch and its behaviors.
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private void onCreateImageButtonSearch() {
        imageButtonSearch = (ImageButton) activity.findViewById(R.id.imageButtonSearch);

        imageButtonSearch.setOnClickListener(new OnClickListener() {
            @Override
        public void onClick(View v) {
                search();
            }
        });
    }

    //TODO: implementation and javadoc
    public void showHideMenu() {}

    //TODO: implementation and javadoc
    public void search() {}

    /**
     * Get View name that is showed on screen
     * @return View name
     * @since 12/23/2014
     */
    public String getScreenTitle() {
        return textViewScreenTitle.getText().toString();
    }

    /**
     * Set View name to be show on screen
     * @param screenTitle
     * @since 12/23/2014
     */
    public void setScreenTitle(String screenTitle) {
        textViewScreenTitle.setText(screenTitle);
    }
}
