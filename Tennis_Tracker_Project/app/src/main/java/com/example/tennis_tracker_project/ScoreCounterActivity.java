package com.example.tennis_tracker_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class ScoreCounterActivity extends AppCompatActivity {


    String DB_URL = "jdbc:mysql://10.0.2.2:8889/Tennis_Tracker_Project";
    String USER = "root";
    String PASSWORD = "root";

    private ScoreCounterActivity activity;

    Button addpoints;
    Button addpoints2;
    Button decreasespoint;
    Button decreasespoint2;
    TextView scoreplayer1;
    TextView scoreplayer2;
    TextView finalScorePlayer1;
    TextView finalScorePlayer2;
    Button saveScore;
    Button back;
    Score score;
    int scorePlayer1 = 0;
    int scorePlayer2 = 0;
    String scorePlayer1String = "";
    String scorePlayer2String = "";
    private ScoreCounterTask scoreCounterTask = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        this.activity = this;


        setContentView(R.layout.activity_score_counter);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        addpoints = findViewById(R.id.addpoints);
        addpoints2 = findViewById(R.id.addpoints2);
        decreasespoint = findViewById(R.id.decreasescore);
        decreasespoint2 = findViewById(R.id.decreasescore2);
        scoreplayer1 = findViewById(R.id.scorePlayer1);
        scoreplayer2 = findViewById(R.id.scoreplayer2);
        finalScorePlayer1 = findViewById(R.id.finalscoreplayer1);
        finalScorePlayer2 = findViewById(R.id.finalscoreplayer2);
        saveScore = findViewById(R.id.saveScore);
        back = findViewById(R.id.back);
        score = new Score();

        addpoints.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scorePlayer1 = score.computeScore(scorePlayer1);
                scorePlayer1String = String.valueOf(scorePlayer1);

                if(scorePlayer1 > 40){
                    scorePlayer1String = "JEU";
                }
                scoreplayer1.setText(scorePlayer1String);

            }
        });

        addpoints2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scorePlayer2 = score.computeScore(scorePlayer2);
                scorePlayer2String = String.valueOf(scorePlayer2);

                if(scorePlayer2 > 40){
                    scorePlayer2String = "JEU";
                }

                scoreplayer2.setText(scorePlayer2String);

            }
        });

        decreasespoint.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scorePlayer1 = score.decrementScorePlayer(scorePlayer1);
                scorePlayer1String = String.valueOf(scorePlayer1);

                if(scorePlayer1 > 40){
                    scorePlayer1String = "JEU";
                }

                scoreplayer1.setText(scorePlayer1String);

            }
        });

        decreasespoint2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                scorePlayer2 = score.decrementScorePlayer(scorePlayer2);
                scorePlayer2String = String.valueOf(scorePlayer2);

                if(scorePlayer2 > 40){
                    scorePlayer2String = "JEU";
                }

                scoreplayer2.setText(scorePlayer2String);

            }
        });


        saveScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnSave();
            }
        });



    }

    public void clickOnSave(){

        scoreCounterTask = new ScoreCounterTask(ScoreCounterActivity.this);
        scoreCounterTask.execute(scoreplayer1.getText().toString(), scoreplayer2.getText().toString());
    }


    class ScoreCounterTask extends AsyncTask<String, String, String> {

        //Weak reference to the activity
        private WeakReference<ScoreCounterActivity> scoreCounterWR = null;

        //This method is called before the beginning of the asynchronous task
        //To configure the task (For example, to prepare a progress bar to show on the uI to the user)
        public ScoreCounterTask(ScoreCounterActivity scoreCounterActivity) {
            link(scoreCounterActivity);
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        //doInBackground() is the only method working on the background thread
        //The others methods are working or the UI thread (main thread)
        @Override
        protected String doInBackground(String... parameters) {

            String msg = "";
            int id_match = 0;

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                msg = "Connection done";

                if (connection == null) {
                    msg = "Connection goes wrong, there is no one";
                } else {

                    String query = "SELECT id FROM matchTennis ORDER BY CreatedDate DESC LIMIT 1";
                    Statement stm = connection.createStatement();
                    ResultSet rs = stm.executeQuery(query);

                    while (rs.next()) {
                        id_match = rs.getInt("id");
                    }

                    stm.close();

                    query = "UPDATE matchTennis SET scorej1 = ?, scorej2 = ? WHERE id = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, parameters[0]);
                    preparedStatement.setString(2, parameters[1]);
                    preparedStatement.setInt(3, id_match);

                    preparedStatement.execute();
                    preparedStatement.close();

                }

                connection.close();

            } catch (Exception e) {
                e.printStackTrace();
            }

            return msg;

        }

        //Called after the end of the task's execution
        //The result is sent in parameters

        protected void onPostExecute(String msg) {
            Log.d("Message fin", msg);
        }

        //To link tke calculator activity to the asynctask each time an action is done in activity's side
        public void link(ScoreCounterActivity pActivity) {
            scoreCounterWR = new WeakReference<ScoreCounterActivity>(pActivity);
        }


    }



}
