package com.obdo;

import android.app.Activity;
import android.content.Intent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.ViewSwitcher;

/**
 * Custom LinearLayout that holds 2 buttons on 1.
 * When the user presses the first button, it becomes invisible and show 2 other options to the user.
 * @author Marcus Vinícius de Carvalho
 * @since 12/23/2014
 * @version 1.0
 * @see android.widget.ImageButton
 * @see android.widget.ViewSwitcher
 */
public class CustomWritePostChangeViewImageButton extends LinearLayout {
    /**
     * ImageButton that hold show/hide the 2 mains buttons
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private ImageButton imageButtonWritePostChangeView;
    /**
     * ImageButton that hold the Write Post Button
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private ImageButton imageButtonWritePost;
    /**
     * ImageButton that hold the Change View Button
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private ImageButton imageButtonChangeView;
    /**
     * Activity that calls this Custom Layout
     * @since 12/23/2014
     * @see android.app.Activity
     */
    private Activity activity;
    //TODO: javadoc
    private ViewSwitcher viewSwitcher;

    public CustomWritePostChangeViewImageButton(Activity activity, ViewSwitcher viewSwitcher) {
        super(activity.getApplicationContext());
        this.activity = activity;
        this.viewSwitcher = viewSwitcher;
        onCreateImageButtonWritePostChangeView();
    }

    /**
     * Initialize imageButtonWritePostChangeView and its behaviors.
     * It is set to visible. If the user click it, it calls showHideOptions
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private void onCreateImageButtonWritePostChangeView() {
        onCreateImageButtonWritePost();
        onCreateImageButtonChangeView();

        imageButtonWritePostChangeView = (ImageButton) activity.findViewById(R.id.imageButtonWritePostChangeView);
        imageButtonWritePostChangeView.setVisibility(VISIBLE);
        imageButtonWritePostChangeView.setActivated(true);

        imageButtonWritePostChangeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHideOptions();
            }
        });
    }

    /**
     * Initialize imageButtonWritePost and its behaviors.
     * It calls WritePostActivity when clicked
     * @since 12/23/2014
     * @see android.widget.ImageButton
     * @see com.obdo.WritePostActivity
     */
    private void onCreateImageButtonWritePost() {
        imageButtonWritePost = (ImageButton) activity.findViewById(R.id.imageButtonWritePost);
        imageButtonWritePost.setVisibility(INVISIBLE);
        imageButtonWritePost.setActivated(false);

        imageButtonWritePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHideOptions();
                Intent intent = new Intent(activity, WritePostActivity.class);
                activity.startActivity(intent);
            }
        });
    }

    /**
     * Initialize imageButtonChangeView and its behaviors.
     * It changes the view when clicked
     * @since 12/23/2014
     * @see android.widget.ImageButton
     * @see android.widget.ViewSwitcher
     */
    private void onCreateImageButtonChangeView() {
        imageButtonChangeView = (ImageButton) activity.findViewById(R.id.imageButtonChangeView);
        imageButtonChangeView.setVisibility(INVISIBLE);
        imageButtonChangeView.setActivated(false);

        imageButtonChangeView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showHideOptions();
                //TODO: Change the view
            }
        });
    }

    /**
     * Shows and Hide the right buttons on screen
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private void showHideOptions() {
        if (imageButtonWritePostChangeView.getVisibility()==VISIBLE) {
            imageButtonWritePostChangeView.setVisibility(INVISIBLE);
            imageButtonWritePost.setVisibility(VISIBLE);
            imageButtonChangeView.setVisibility(VISIBLE);

            imageButtonWritePostChangeView.setActivated(false);
            imageButtonWritePost.setActivated(true);
            imageButtonChangeView.setActivated(true);
        } else {
            imageButtonWritePostChangeView.setVisibility(VISIBLE);
            imageButtonWritePost.setVisibility(INVISIBLE);
            imageButtonChangeView.setVisibility(INVISIBLE);

            imageButtonWritePostChangeView.setActivated(true);
            imageButtonWritePost.setActivated(false);
            imageButtonChangeView.setActivated(false);
        }
    }
}
