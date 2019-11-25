package com.example.tennis_tracker_project;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.google.android.material.textfield.TextInputEditText;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

public class NewMatcheFragment extends Fragment {

    private TextInputEditText joueur1;
    private TextInputEditText joueur2;
    private TextInputEditText formatMatch;
    private TextInputEditText formatDernierSet;
    private Button buttonSave;

    private NewMatcheAsyncTask newMatcheAsyncTask = null;

    String DB_URL = "jdbc:mysql://10.0.2.2:8889/Tennis_Tracker_Project";
    String USER = "root";
    String PASSWORD = "root";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_new_match, container, false);

        joueur1 = view.findViewById(R.id.joueur1);
        joueur2 = view.findViewById(R.id.joueur2);
        formatMatch = view.findViewById(R.id.formatMatche);
        formatDernierSet = view.findViewById(R.id.formatSet);
        buttonSave = view.findViewById(R.id.saveNewMatch);

        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clickOnSave();
                openScoreCounterActivity();
            }

        });

        return view;

    }

    public void clickOnSave(){

        joueur1.setText(joueur1.getText().toString());
        joueur2.setText(joueur2.getText().toString());
        formatMatch.setText(formatMatch.getText().toString());
        formatDernierSet.setText(formatDernierSet.getText().toString());

        newMatcheAsyncTask = new NewMatcheAsyncTask(NewMatcheFragment.this);
        newMatcheAsyncTask.execute(joueur1.getText().toString(), joueur2.getText().toString(),formatMatch.getText().toString(), formatDernierSet.getText().toString());
    };

    public void openScoreCounterActivity(){
        Intent intent = new Intent(getActivity(), ScoreCounterActivity.class);
        startActivity(intent);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("New Matches");
    }


    class NewMatcheAsyncTask extends AsyncTask<String, String, String> {

        //Weak reference to the activity
        private WeakReference<NewMatcheFragment> newMatcheActivityWR = null;

        //This method is called before the beginning of the asynchronous task
        //To configure the task (For example, to prepare a progress bar to show on the uI to the user)
        public NewMatcheAsyncTask(NewMatcheFragment newMatchFragment) {
            link(newMatchFragment);
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

            try {
                Class.forName("com.mysql.jdbc.Driver");
                Connection connection = DriverManager.getConnection(DB_URL, USER, PASSWORD);
                msg = "Connection done";

                if (connection == null) {
                    msg = "Connection goes wrong, there is no one";
                } else {
                    String query = "INSERT INTO matchTennis(joueur1, joueur2, formatMatch, formatDernierSet) VALUES (?, ?, ?, ?)";
                    PreparedStatement preparedStatement = connection.prepareStatement(query);
                    preparedStatement.setString(1, parameters[0]);
                    preparedStatement.setString(2, parameters[1]);
                    preparedStatement.setString(3, parameters[2]);
                    preparedStatement.setString(4, parameters[3]);

                    preparedStatement.execute();
                    preparedStatement.close();

                    System.out.println("The query was successfully done");

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
        public void link(NewMatcheFragment pFragment) {
            newMatcheActivityWR = new WeakReference<NewMatcheFragment>(pFragment);
        }

    }
}
