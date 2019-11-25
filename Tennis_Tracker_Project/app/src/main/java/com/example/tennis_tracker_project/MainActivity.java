package com.example.tennis_tracker_project;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
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
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.core.view.GravityCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
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
import android.widget.FrameLayout;
import android.widget.Toast;

import java.util.Locale;
import java.util.Set;

public class MainActivity extends AppCompatActivity {

    private AppBarConfiguration mAppBarConfiguration;
    private FrameLayout frameLayout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);



        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);

        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {

            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem){

                selectDrawerItem(menuItem);
                return true;
            }


        });

    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        Log.d("test OK", "ok");
        super.onConfigurationChanged(newConfig);

        // Checks the orientation of the screen
        if (newConfig.orientation == Configuration.ORIENTATION_PORTRAIT) {
            Toast.makeText(this, "landscape", Toast.LENGTH_SHORT).show();


        } else if (newConfig.orientation == Configuration.ORIENTATION_LANDSCAPE){
            Toast.makeText(this, "portrait", Toast.LENGTH_SHORT).show();
        }
        setContentView(R.layout.activity_main);

    }

    public void selectDrawerItem(MenuItem menuItem) {
        // Create a new fragment and specify the fragment to show based on nav item clicked
        switch(menuItem.getItemId()){

            case R.id.nav_home :
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new HomeFragment()).commit();
                break;

            case R.id.nav_new_matches :
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new NewMatcheFragment()).commit();
                break;

            case R.id.nav_previous_matches :
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new PreviousMatchFragment()).commit();
                break;

            case R.id.nav_pictures :
                openTakePictureActivity();
                break;

            case R.id.nav_settings :
                getSupportFragmentManager().beginTransaction().replace(R.id.content_frame, new SettingsFragment()).commit();
                break;

        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);

    }


    //Method needed to open the activity of new matches by clicking on buttons
    public void openTakePictureActivity(){

        //First, we need to create an intent, which is a messaging object used to communicate between components
        //We use it there to communicate between activities
        Intent intent = new Intent(this, TakePhotoActivity.class);
        if (getPackageManager().hasSystemFeature(PackageManager.FEATURE_CAMERA_ANY)){
            // make sure there's a camera
            startActivity(intent);
        }

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
        NavController navController = Navigation.findNavController(this, R.id.content_frame);
        return NavigationUI.navigateUp(navController, mAppBarConfiguration)
                || super.onSupportNavigateUp();
    }


}