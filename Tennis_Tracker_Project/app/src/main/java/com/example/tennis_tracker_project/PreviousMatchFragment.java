package com.example.tennis_tracker_project;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.lang.ref.WeakReference;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;

public class PreviousMatchFragment extends Fragment {

    private PreviousMatchTask previousMatchTask = null;
    String DB_URL = "jdbc:mysql://10.0.2.2:8889/Tennis_Tracker_Project";
    String USER = "root";
    String PASSWORD = "root";
    String joueur1 = "";
    String joueur2 = "";
    int scorej1 = 0;
    int scorej2 = 0;
    String formatDernierSet = "";
    String formatMatch = "";

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_previous_match, container, false );
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //you can set the title for your toolbar here for different fragments different titles
        getActivity().setTitle("Previous Matches");
    }


    class PreviousMatchTask extends AsyncTask<String, String, String> {

        //Weak reference to the activity
        private WeakReference<PreviousMatchFragment> previousMatchesActivityWR = null;

        //This method is called before the beginning of the asynchronous task
        //To configure the task (For example, to prepare a progress bar to show on the uI to the user)
        public PreviousMatchTask(PreviousMatchFragment previousMatchFragment) {
            link(previousMatchFragment);
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

                    String query = "SELECT joueur1, joueur2, scorej1, scorej2, formatDernierSet, formatMatch FROM matchTennis ORDER BY CreatedDate DESC LIMIT 5";
                    Statement stm = connection.createStatement();
                    ResultSet rs = stm.executeQuery(query);

                    while (rs.next()) {
                        joueur1 = rs.getString("joueur1");
                        joueur2 = rs.getString("joueur2");
                        scorej1 = rs.getInt("scorej1");
                        scorej2 = rs.getInt("scorej2");
                        formatMatch = rs.getString("formatMatch");
                        formatDernierSet = rs.getString("formatDernierSet");
                    }

                    stm.close();
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
        public void link(PreviousMatchFragment pFragment) {
            previousMatchesActivityWR = new WeakReference<PreviousMatchFragment>(pFragment);
        }

    }





}