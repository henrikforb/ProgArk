package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.view.PlayView;

public class MenuController {

    private ViewController vc;

    public MenuController(ViewController vc) {
        this.vc = vc;
    }

    public void quit() {
        Gdx.app.exit();
    }

    public void settingsPressed(){
        vc.set(new SettingsView());

    }

    public void playGamePressed(){
        vc.set(new PlayView(new PlayController(vc)));
    }

    public void helpPressed(){
        vc.set(new HelpView());
    }


}
