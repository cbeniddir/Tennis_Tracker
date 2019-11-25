package com.example.tennis_tracker_project;

import android.util.Log;

public class Score {

    //int scorePlayerSelected;
    int jeu;
    int set;
    boolean winner;
    String scorePlayerString = "";

    public Score(){
        //this.scorePlayerSelected = 0;
        this.jeu = 0;
        this.set = 0;
        this.winner = false;
    }

    public int computeScore(int scorePlayer){

        scorePlayer = incrementScorePlayer(scorePlayer);
        Log.d("score player", ""+scorePlayer);
        //scorePlayerString = String.valueOf(scorePlayer);

        if(scorePlayer > 40){
            jeu++;
            //scorePlayer = 0;
        }

        if(jeu == 6 || jeu == 7){
            winner = true;
        }

        return scorePlayer;
    }

    public int incrementScorePlayer(int scorePlayer){

        if(scorePlayer == 0 || scorePlayer == 15){
            scorePlayer += 15;
        }
        else if(scorePlayer == 30){
            scorePlayer = 40;
        }
        else{
            scorePlayer = 41;
        }

        return scorePlayer;
    }

    public int decrementScorePlayer(int scorePlayer){

        return scorePlayer--;
    }


}
