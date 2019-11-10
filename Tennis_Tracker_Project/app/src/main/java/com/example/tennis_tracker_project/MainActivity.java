package com.example.tennis_tracker_project;

import android.content.Intent;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.google.android.material.navigation.NavigationView;

import androidx.drawerlayout.widget.DrawerLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.WindowManager;
import android.widget.Toast;

import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    boolean french = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //onConfigurationChanged(getResources().getConfiguration());
        //savedInstanceState = getIntent().getExtras();

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        NavigationView navigationView = findViewById(R.id.nav_view);

        // Passing each menu ID as a set of Ids because each
        // menu should be considered as top level destinations.
        mAppBarConfiguration = new AppBarConfiguration.Builder(
                R.id.nav_home, R.id.nav_gallery, R.id.nav_slideshow,
                R.id.nav_tools, R.id.nav_share, R.id.nav_send)
                .setDrawerLayout(drawer)
                .build();

        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        NavigationUI.setupActionBarWithNavController(this, navController, mAppBarConfiguration);
        NavigationUI.setupWithNavController(navigationView, navController);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){

                switch(menuItem.getItemId()){

                    case R.id.nav_new_matches :
                        openNewMatchActivity();
                        break;

                    case R.id.nav_previous_matches :
                        openPreviousMatchActivity();
                        break;

                    case R.id.nav_settings :
                        openSettingsActivity();
                        break;

                }
                return false;
            }


        });

    }

    public void switchLanguage(String languageCode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(languageCode);
        res.updateConfiguration(conf, dm);
    }


    //Method needed to open the activity of new matches by clicking on buttons
    public void openNewMatchActivity(){

        //First, we need to create an intent, which is a messaging object used to communicate between components
        //We use it there to communicate between activities
        Intent intent = new Intent(this, NewMatcheActivity.class);
        startActivity(intent);
    }

    //Method needed to open the activity of previous matches by clicking on buttons
    public void openPreviousMatchActivity(){

        //First, we need to create an intent, which is a messaging object used to communicate between components
        //We use it there to communicate between activities
        Intent intent = new Intent(this, PreviousMatchesActivity.class);
        startActivity(intent);
    }

    public void openSettingsActivity(){

        //First, we need to create an intent, which is a messaging object used to communicate between components
        //We use it there to communicate between activities
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onSupportNavigateUp() {
        NavController navController = Navigation.findNavController(this, R.id.nav_host_fragment);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE) {
            Log.d("landscape", "orientation landscape");
            //Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();
            //setContentView(R.layout.activity_main);

        } else if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT){
            //Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
            Log.d("portrait", "orientation portrait");
            // setContentView(R.layout.activity_main);
        }

        setContentView(R.layout.activity_main);
    }


}