package com.example.tennis_tracker_project;

import android.content.res.Configuration;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.RadioButton;

import java.util.Locale;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        RadioButton radioButton_fr = findViewById(R.id.radiobuttonFr);
        final RadioButton radioButton_en = findViewById(R.id.radiobuttonEn);

        radioButton_en.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    switchLanguage("en");

            }
        });

        radioButton_fr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                    switchLanguage("fr");
            }
        });

    }

    public void switchLanguage(String newLangage) {
        String language = newLangage; // ta langue
        Locale locale = new Locale(language);
        Locale.setDefault(locale);
        Configuration config = new Configuration();
        config.locale = locale;
        getResources().updateConfiguration(config, getBaseContext().getResources().getDisplayMetrics());
    }

    /*public void checkLanguageEn(String language, RadioButton button) {
        if (button.isChecked()) {
            switchLanguage(language);
        } else {
            switchLanguage(language);
        }
    }*/

}
