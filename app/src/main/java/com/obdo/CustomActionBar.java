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
    private Activity activity;

    public CustomActionBar(Activity activity) {
        super(activity.getApplicationContext());
        this.activity = activity;
        onCreateImageButtonMenu();
        onCreateTextViewScreenTitle();
        onCreateImageButtonSearch();
    }

    private void onCreateImageButtonMenu() {
        imageButtonMenu = (ImageButton) activity.findViewById(R.id.imageButtonMenu);

        imageButtonMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHideMenu();
            }
        });
    }

    private void onCreateTextViewScreenTitle() {
        textViewScreenTitle = (TextView) findViewById(R.id.textViewScreenTitle);
    }

    private void onCreateImageButtonSearch() {
        imageButtonSearch = (ImageButton) activity.findViewById(R.id.imageButtonSearch);

        imageButtonSearch.setOnClickListener(new OnClickListener() {
            @Override
        public void onClick(View v) {
                search();
            }
        });
    }

    public TextView getTextViewScreenTitle() {
        return textViewScreenTitle;
    }

    public void setTextViewScreenTitle(TextView textViewScreenTitle) {
        this.textViewScreenTitle = textViewScreenTitle;
    }

    //TODO: implementation and javadoc
    public void showHideMenu() {}

    //TODO: implementation and javadoc
    public void search() {}
}
