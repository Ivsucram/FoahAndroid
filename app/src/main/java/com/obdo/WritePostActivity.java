package com.obdo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.EditText;
import android.widget.ImageButton;

/**
 * Created by Ivsucram on 12/23/2014.
 */
public class WritePostActivity extends ActionBarActivity {
    private EditText editTextMessageTarget;
    private EditText editTextPostText;
    private ImageButton imageButtonAttachPhotoVideo;
    private ImageButton imageButtonSchedulePost;
    private ImageButton imageButtonVisibilityRadius;
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

    private void onCreateEditTextMessageTarget() {
        editTextMessageTarget = (EditText) findViewById(R.id.editTextMessageTarget);
        editTextMessageTarget.setText("To: Public");
    }

    private void onCreateEditTextPostText() {
        editTextPostText = (EditText) findViewById(R.id.editTextPostText);
        editTextPostText.setHint("Write your post");
    }

    private void onCreateImageButtonAttachPhotoVideo() {
        imageButtonAttachPhotoVideo = (ImageButton) findViewById(R.id.imageButtonAttachPhotoVideo);
    }

    private void onCreateImageButtonSchedulePost() {
        imageButtonSchedulePost = (ImageButton) findViewById(R.id.imageButtonSchedulePost);
    }

    private void onCreateImageButtonVisibilityRadius() {
        imageButtonVisibilityRadius = (ImageButton) findViewById(R.id.imageButtonVisibilityRadius);
    }

    private void onCreateImageButtonConfirmPost() {
        imageButtonConfirmPost = (ImageButton) findViewById(R.id.imageButtonConfirmPost);
    }
}
