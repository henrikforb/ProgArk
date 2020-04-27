package com.mygdx.game.controller;

import com.badlogic.gdx.Gdx;
import com.mygdx.game.view.HelpView;
import com.mygdx.game.view.HighscoreView;
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
        if (vc.getModel().multiplayerChecked()) {
            NetworkController networkController = new NetworkController();
            vc.set(new LoadingView(new LoadingController(vc, networkController), networkController));
        } else {
            vc.set(new PlayView(vc));
        }
    }

    public void helpPressed(){
        vc.push(new HelpView(new HelpController(vc)));
    }

    public void highscorePressed() {
        vc.set((new HighscoreView(new HighScoreController(vc))));
    }

}
