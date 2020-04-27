package com.mygdx.game.controller;

import com.mygdx.game.singelton.Settings;
import com.mygdx.game.model.World;
import com.mygdx.game.view.GameOverView;
import com.mygdx.game.view.MenuView;
import com.mygdx.game.view.PauseView;

public class GameController {

    private ViewController vc;
    private World world;

    public GameController(ViewController vc, World world) {
        this.vc = vc;
        this.world = world;
        if (Settings.getInstance().gameMusicIsEnabled()) {
            this.world.playMusic();
        }
    }

    public void gameOver(){
        vc.set(new GameOverView(new GameOverController(vc)));
    }

    public void pauseGame(){
        world.pauseMusic();
        vc.push(new PauseView(new PauseController(vc, world)));
        System.out.println("game paused");
    }

    public void quitGame(){
        vc.set(new MenuView(new MenuController(vc)));
    }

    public void update(long dt){

    }




}
