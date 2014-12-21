package com.obdo;

import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.obdo.data.models.User;
import com.obdo.data.repos.Repo;


public class ObdoActivity extends ActionBarActivity {
    private CustomActionBar customActionBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_obdo);

        onCreateCustomActionBar();
    }

    private void onCreateCustomActionBar() {
        customActionBar = new CustomActionBar(this);
    }
}
