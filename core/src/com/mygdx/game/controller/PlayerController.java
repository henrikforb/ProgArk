package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.Player;
import com.mygdx.game.view.GameOverScreen;

public class PlayerController {

    private ViewController vc;

    public PlayerController(ViewController vc){
        this.vc = vc;
    }

    public void touch(Player player){
        player.jump();
    }

    public void gameOver(){
        vc.set(new GameOverScreen(new GameOverController(vc)));
    }



}
