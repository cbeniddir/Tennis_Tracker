package com.example.tennis_tracker_project;

import android.app.ActionBar;
import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    private SettingsActivity activity;
    public boolean french = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.activity = this;

        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button changerLangue = findViewById(R.id.buttonLangue);

        changerLangue.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.d("test langue", "OK");
                showLanguageDialog();
            }
        });
    }

    public void showLanguageDialog() {
        Log.d("showLangueDialog", "OK");
        String[] languagesList = {"Français", "English"};

        AlertDialog.Builder mypopup = new AlertDialog.Builder(SettingsActivity.this);
        mypopup.setTitle("Choose language");

        mypopup.setSingleChoiceItems(languagesList, -1,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int i) {
                        Log.d("test popup", "OK 1");
                        if (i == 0) {
                            //French
                            switchLanguage("fr");
                            french = true;
                            //passBooleanLanguageValue();
                        }
                        if (i == 1) {
                            //English
                            switchLanguage("en");
                            french = false;
                        }
                        recreate();

                        dialog.dismiss();
                    }
                });

        mypopup.show();

    }

    public void switchLanguage(String languageCode) {
        Resources res = getResources();
        DisplayMetrics dm = res.getDisplayMetrics();
        android.content.res.Configuration conf = res.getConfiguration();
        conf.locale = new Locale(languageCode);
        res.updateConfiguration(conf, dm);
    }

    //public void passBooleanLanguageValue(){
    //    Intent i = new Intent(getBaseContext(), MainActivity.class);
    //    i.putExtra("bool_french", french);
    //    startActivity(i);
   // }





}
