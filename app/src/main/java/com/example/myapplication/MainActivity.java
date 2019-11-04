package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;


//TODO : Utiliser asynchronicité lorsque on va chercher infos dans la bdd, ou lors de la réalisation d'un calcul ou d'un traitement retournant un résultat
//TODO : Afficher le formulaire a remplir pour ajouter un nouveau match
//TODO : Connecter à bdd
//TODO : Ajouter un nouveau match dans la bdd

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Toolbar
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        //Buttons
        Button buttonNewMatch = findViewById(R.id.buttonNewMatch);
        Button buttonPreviousMatch = findViewById(R.id.buttonPreviousMatchs);

        //To handle click on buttons
        //To go on the activity related to new match
        buttonNewMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openNewMatchActivity();
            }
        });

        //To go on the activity related to the list of previous matchs
        buttonPreviousMatch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openPreviousMatchActivity();
            }
        });

        //FloatingActionButton fab = findViewById(R.id.fab);
        //fab.setOnClickListener(new View.OnClickListener() {
        //    @Override
        //    public void onClick(View view) {
        //        Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
        //                .setAction("Action", null).show();
        //    }
        //});


    }

    //Method needed to open the activity of new matches by clicking on buttons
    public void openNewMatchActivity(){

        //First, we need to create an intent, which is a messaging object used to communicate between components
        //We use it there to communicate between activities
        Intent intent = new Intent(this, NewMatchActivity.class);
        startActivity(intent);
    }

    //Method needed to open the activity of previous matches by clicking on buttons
    public void openPreviousMatchActivity(){

        //First, we need to create an intent, which is a messaging object used to communicate between components
        //We use it there to communicate between activities
        Intent intent = new Intent(this, PreviousMatchActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
