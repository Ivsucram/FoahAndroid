package com.obdo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Activity where the user will be able to write posts.
 * The user can:
 * - Select the target of the message (Public or Private to some friends
 * - Write the Post
 * - Attach Assets (Photos or Videos) to the post
 * - Define when the Post will be live
 * - Define the visibility radius of the post
 * @author Marcus Vinícius de Carvalho
 * @since 12/23/2014
 * @version 1
 */
public class WritePostActivity extends ActionBarActivity {
    /**
     * EdiText that holds the target to receive the message
     * @since 12/23/2014
     */
    private EditText editTextMessageTarget;
    /**
     * EditText that holds the post text
     * @since 12/23/2014
     */
    private EditText editTextPostText;
    /**
     * ImageButton that holds the function to add assets (Photos and Videos)
     * @since 12/23/2014
     */
    private ImageButton imageButtonAttachPhotoVideo;
    /**
     * ImageButton that holds the function to schedule post to be alive on a future date
     * @since 12/23/2014
     */
    private ImageButton imageButtonSchedulePost;
    /**
     * ImageButton that holds the function to change the visibility radius of the post
     * @since 12/23/2014
     */
    private ImageButton imageButtonVisibilityRadius;
    /**
     * ImageButton that holds the confirmation function to post the message
     * @since 12/23/2014
     */
    private ImageButton imageButtonConfirmPost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_write_post);

        onCreateEditTextMessageTarget();
        onCreateEditTextPostText();
        onCreateImageButtonAttachPhotoVideo();
        onCreateImageButtonSchedulePost();
        onCreateImageButtonVisibilityRadius();
        onCreateImageButtonConfirmPost();
    }

    /**
     * Initialize editTextMessageTarget and its behaviors.
     * @since 12/23/2014
     * @see android.widget.EditText
     */
    private void onCreateEditTextMessageTarget() {
        editTextMessageTarget = (EditText) findViewById(R.id.editTextMessageTarget);
        editTextMessageTarget.setText("To: Public");
    }

    /**
     * Initialize editTextPostText and its behaviors.
     * @since 12/23/2014
     * @see android.widget.EditText
     */
    private void onCreateEditTextPostText() {
        editTextPostText = (EditText) findViewById(R.id.editTextPostText);
        editTextPostText.setHint("Write your post");
    }

    /**
     * Initialize imageButtonAttachPhotoVideo and its behaviors.
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private void onCreateImageButtonAttachPhotoVideo() {
        imageButtonAttachPhotoVideo = (ImageButton) findViewById(R.id.imageButtonAttachPhotoVideo);
    }

    /**
     * Initialize imageButtonSchedulePost and its behaviors.
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private void onCreateImageButtonSchedulePost() {
        imageButtonSchedulePost = (ImageButton) findViewById(R.id.imageButtonSchedulePost);
    }

    /**
     * Initialize imageButtonVisibilityRadius and its behaviors.
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private void onCreateImageButtonVisibilityRadius() {
        imageButtonVisibilityRadius = (ImageButton) findViewById(R.id.imageButtonVisibilityRadius);
    }

    /**
     * Initialize imageButtonConfirmPost and its behaviors.
     * @since 12/23/2014
     * @see android.widget.ImageButton
     */
    private void onCreateImageButtonConfirmPost() {
        imageButtonConfirmPost = (ImageButton) findViewById(R.id.imageButtonConfirmPost);
    }
}
