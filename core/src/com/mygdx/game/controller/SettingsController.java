package com.mygdx.game.controller;

import com.mygdx.game.model.Settings;

public class SettingsController {

    private ViewController vc;
    private Settings model;

    public SettingsController(ViewController vc) {
        this.model = vc.getModel();
        this.vc = vc;
    }

    public void backToMenu(){
        vc.pop();
    }

    public void changeSoundVolume(float volume){
        //model.setSoundVolume(volume);
    }

    public void toggleGameMusic(){
        model.toggleGameMusic();
    }

    public Settings getModel(){
        return this.model;
    }

    public boolean multiplayerChecked() {
        return model.multiplayerChecked();
    }

    public void toggleMultiplayer() {
        model.toggleMultiplayer();

    }

}