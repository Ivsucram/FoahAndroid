package com.obdo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ViewSwitcher;

import com.google.android.gms.maps.MapView;
import com.obdo.data.models.User;
import com.obdo.data.repos.Repo;


public class ObdoActivity extends ActionBarActivity {
    private CustomActionBar customActionBar;
    private ViewSwitcher viewSwitcher;
    private CustomWritePostChangeViewImageButton customWritePostChangeViewImageButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obdo);

        onCreateCustomActionBar();
        onCreateViewSwitcher();
        onCreateCustomWritePostChangeViewImageButton();
    }

    private void onCreateCustomActionBar() {
        customActionBar = new CustomActionBar(this);
        customActionBar.setScreenTitle("Obdo");
    }

    private void onCreateViewSwitcher() {
        viewSwitcher = (ViewSwitcher) findViewById(R.id.viewSwitcher);
        //TODO: Add Map View
        //TODO: Add List View
    }

    private void onCreateCustomWritePostChangeViewImageButton() {
        customWritePostChangeViewImageButton = new CustomWritePostChangeViewImageButton(this, viewSwitcher);
    }
}
