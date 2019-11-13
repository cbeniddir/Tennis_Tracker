package com.example.tennis_tracker_project;

import android.os.AsyncTask;
import android.os.Bundle;

import com.google.android.material.textfield.TextInputEditText;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.util.Log;
import android.view.View;
import android.widget.Button;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.DriverManager;

public class NewMatcheActivity extends AppCompatActivity {

    private TextInputEditText joueur1;
    private TextInputEditText joueur2;
    private TextInputEditText formatMatche;
    private TextInputEditText formatDernierSet;
    private Button buttonSave;

    private NewMatcheAsyncTask newMatcheAsyncTask = null;

    String DB_URL = "jdbc:mysql://10.0.2.2:8889/Tennis_Tracker";
    String USER = "root";
    String PASSWORD = "root";
    //private static final String dbName = "Tennis_Tracker";


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
        buttonSave = findViewById(R.id.saveNewMatch);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnSave();

            }
        });
    }

    public void clickOnSave(){

        joueur1.setText(joueur1.getText().toString());
        joueur2.setText(joueur2.getText().toString());
        formatMatche.setText(formatMatche.getText().toString());
        formatDernierSet.setText(formatDernierSet.getText().toString());

        newMatcheAsyncTask = new NewMatcheAsyncTask(NewMatcheActivity.this);
        newMatcheAsyncTask.execute();
    };

    class NewMatcheAsyncTask extends AsyncTask<String, String, String> {

        //Weak reference to the activity
        private WeakReference<NewMatcheActivity> newMatcheActivityWR = null;

        //This method is called before the beginning of the asynchronous task
        //To configure the task (For example, to prepare a progress bar to show on the uI to the user)
        public NewMatcheAsyncTask(NewMatcheActivity newMatcheActivity){
            link(newMatcheActivity);
        }

        @Override
        protected void onPreExecute(){
            super.onPreExecute();
        }

        //doInBackground() is the only method working on the background thread
        //The others methods are working or the UI thread (main thread)
        @Override
        protected String doInBackground(String... number) {

            String msg = "";

            try{
                Log.d("Entrée dans try bdd", "OK");

                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                msg = "Connection done";

                if(connection == null){
                    msg = "Connection goes wrong, there is no one";
                }

                else{
                    //String query = "INSERT INTO "
                    //Statement statement = connection.createStatement();
                    //statement.execute(query);
                    System.out.println("The query was successfully done");

                }

                connection.close();

            }catch(Exception e){

                System.out.println("Exception");
                System.out.println("Message exception " + e.getMessage());
                System.out.println("StackTrace exception " + e.getStackTrace());
                e.printStackTrace();
            }

            return msg;

        }


        //Called after the end of the task's execution
        //The result is sent in parameters

        protected void onPostExecute(String msg){
            Log.d("Message fin", msg);
        }

        //To link tke calculator activity to the asynctask each time an action is done in activity's side
        public void link (NewMatcheActivity pActivity) {
            newMatcheActivityWR = new WeakReference<NewMatcheActivity>(pActivity);
        }

    }





}
