package com.mygdx.game.controller;

import com.mygdx.game.view.HighscoreView;
import com.mygdx.game.view.MenuView;
import com.mygdx.game.model.Settings;
import com.badlogic.gdx.graphics.g2d.BitmapFont;

public class HighScoreController {

    public Settings sm;
    public ViewController vc;


    public HighScoreController(ViewController vc){
        this.vc = vc;
        this.sm = Settings.getInstance();
    }

    public void backToMenu(){
        vc.set(new MenuView(new MenuController(vc)));
    }

    public BitmapFont getFont(){
        return sm.getFont();
    }
}
