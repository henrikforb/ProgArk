package com.mygdx.game.model;

import com.badlogic.gdx.graphics.g2d.BitmapFont;

import java.text.DecimalFormat;

/**
 * A players score equal to the time played
 * The score is rendered to screen in the PlayView
 */

public class Score {

    private float score;
    private String scoreString;
    private BitmapFont scoreFont;
    DecimalFormat numberFormat;

    public Score(){
        score = 0;
        this.numberFormat = new DecimalFormat("##0.00");
        scoreString = "Score: " + numberFormat.format(score);
        scoreFont = new BitmapFont();
    }

    public float getScore() {
        return score;
    }

    public BitmapFont getScoreFont() {
        return scoreFont;
    }

    public String getScoreString() {
        return scoreString;
    }

    public void update(float dt){
        score += dt;
        scoreString = "Score: " + numberFormat.format(score);
    }

    public void dispose() {
        scoreFont.dispose();
    }
}
