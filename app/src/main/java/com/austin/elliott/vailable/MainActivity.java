package com.austin.elliott.vailable;

import android.app.Fragment;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView eventNameTextView;
    private TextView dateTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbclient);
        ImageView eventToolbarIcon = (ImageView) toolbar.findViewById(R.id.eventsToolbarButton);
        eventToolbarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNewFrag(new CreateEvent());
            }
        });

        ImageView friendsToolbarIcon = (ImageView) toolbar.findViewById(R.id.friendsToolbarButton);
        friendsToolbarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                goToNewFrag(new FriendsFragment());
            }
        });

        ImageView homeToolbarIcon = (ImageView) toolbar.findViewById(R.id.homeToolbarButton);
        homeToolbarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNewFrag(new HomeFragment());
            }
        });

        ImageView settingsToolbarIcon = (ImageView) toolbar.findViewById(R.id.settingsToolbarButton);
        settingsToolbarIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                goToNewFrag(new SettingsFragment());
            }
        });

        setSupportActionBar(toolbar);

        goToNewFrag(new HomeFragment());
    }

    private void goToNewFrag(Fragment fragment) {
        FragmentTransaction transaction = getFragmentManager().beginTransaction();
        transaction.replace(R.id.frame_container, fragment);
        transaction.commit();
    }
}
