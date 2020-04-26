package com.mygdx.game.controller;

import com.mygdx.game.model.Settings;
import com.mygdx.game.model.World;
import com.mygdx.game.view.MenuView;
import com.mygdx.game.view.PlayView;
import com.mygdx.game.view.SettingsView;

public class PauseController {

    private ViewController vc;
    private World world;

    public PauseController(ViewController vc, World world) {
        this.vc = vc;
        this.world = world;
    }

    public void BackToMenu(){
        world.dispose();
        vc.pop();
        vc.peek().dispose();
        vc.set(new MenuView(new MenuController(vc)));
    }

    public void ContinueGame(){
        if (Settings.getInstance().gameMusicIsEnabled()){
            world.playMusic();
        }
        vc.pop();
    }

    public void settings(){
        vc.push(new SettingsView(new SettingsController(vc)));
    }

}
