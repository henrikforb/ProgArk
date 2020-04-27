package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;


import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.FileWriter;
import java.io.Reader;
import java.lang.reflect.Array;
import java.lang.reflect.Type;
import java.util.ArrayList;

public class HighScore {


    private String highscoreString;

        private final int highScoreEntriesLimit = 5;

        public HighScore(){
            //this.fileLocation = fileLocation;

        }

        public boolean shouldScoreBeAdded(ArrayList<Double> currentScores, Double scoreToEvaluate) {
            if (currentScores.size() < this.highScoreEntriesLimit) {
                return true;
            }
            for (Double score : currentScores) {
                if (scoreToEvaluate > score) {
                    return true;
                }
            }
            return false;
        }

        public ArrayList<Double> loadHighScoreFromFile() {
            try {
                Gson gson = new Gson();
                FileHandle file = Gdx.files.local("highscores.json");
                String jsonFileString = file.readString();
                // BufferedReader reader = new BufferedReader(new FileReader());
                Type highScoreListType = new TypeToken<ArrayList<Double>>(){}.getType();
                return gson.fromJson(jsonFileString, highScoreListType);
            } catch (Exception e) {
                System.out.println("huff da lese fra fil");
                return new ArrayList<Double>();
            }
        }

        public void writeHighScoreToFile(ArrayList<Double> scores) {
            Gson gson = new Gson();
            try {
                //BufferedWriter writer = new BufferedWriter(new FileWriter(fileLocation));
                FileHandle file = Gdx.files.local("highscores.json");
                String jsonString = gson.toJson(scores);
                file.writeString(jsonString, false);
            } catch (Exception e) {
                e.printStackTrace();
                System.out.println("huff da skrive til fil");
            }
        }

        public void addScoreToHighScore(Double newScore) {
            ArrayList<Double> currentScores = loadHighScoreFromFile();
            if (shouldScoreBeAdded(currentScores, newScore)) {
                int indexToAddOn = -1;
                ArrayList<Double> newHighScores = new ArrayList<Double>(currentScores);
                for (int i = 0; i < currentScores.size(); i++) {
                    if (newScore > currentScores.get(i)) {
                        indexToAddOn = i;
                        break;
                    }
                }
                if (indexToAddOn == -1) {
                    newHighScores.add(newScore);
                } else {
                    newHighScores.add(indexToAddOn, newScore);
                }
                if (newHighScores.size() > this.highScoreEntriesLimit) {
                    newHighScores.remove(newHighScores.size()-1);
                }
                writeHighScoreToFile(newHighScores);
            } else {
                writeHighScoreToFile(currentScores);
            }
        }




}
