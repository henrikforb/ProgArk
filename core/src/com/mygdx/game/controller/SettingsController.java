package com.mygdx.game.controller;

import com.mygdx.game.model.SettingsModel;
import com.mygdx.game.view.MenuView;

public class SettingsController {

    private ViewController vc;
    private SettingsModel model;

    public SettingsController(ViewController vc) {
        this.vc = vc;

    }

    public void BackToMenu(){
        vc.set(new MenuView(new MenuController(vc)));
    }

    public void ChangeSoundVolume(float volume){
        model.setSoundVolume(volume);
    }

    public void toggleSound(Boolean enable){
        model.enableMusic(enable);
    }



}
