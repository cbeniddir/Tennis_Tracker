package com.example.tennis_tracker_project;

import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;

public class NewMatcheActivity extends AppCompatActivity {

    private TextInputEditText joueur1;
    private TextInputEditText joueur2;
    private TextInputEditText formatMatche;
    private TextInputEditText formatDernierSet;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_matche);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        joueur1 = findViewById(R.id.joueur1);
        joueur2 = findViewById(R.id.joueur2);
        formatMatche = findViewById(R.id.formatMatche);
        formatDernierSet = findViewById(R.id.formatSet);


    }

}
