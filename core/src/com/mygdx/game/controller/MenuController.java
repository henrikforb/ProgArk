package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.model.Settings;
import com.mygdx.game.view.HelpView;
import com.mygdx.game.view.LoadingView;
import com.mygdx.game.view.PlayView;
import com.mygdx.game.view.SettingsView;

public class MenuController {

    private ViewController vc;

    public MenuController(ViewController vc) {
        this.vc = vc;
    }

    public void quit() {
        Gdx.app.exit();
    }

    public void settingsPressed(){
        vc.push(new SettingsView(new SettingsController(vc)));
    }

    public void playGamePressed(){

        if (multiplayerChecked()) {
            vc.set(new LoadingView(new LoadingController(vc)));
        }else {
            vc.set(new PlayView(vc, multiplayerChecked()));
        }
    }

    public void helpPressed(){
        vc.push(new HelpView(new HelpController(vc)));
    }

    public boolean multiplayerChecked() {
        return vc.multiplayerChecked();
    }

    public void toggleMultiplayer() {
        vc.toggleMultiplayer();

    }
}
