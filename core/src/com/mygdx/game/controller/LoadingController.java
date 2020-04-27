package com.mygdx.game.controller;

import com.mygdx.game.view.MenuView;
import com.mygdx.game.view.PlayView;

public class LoadingController {

    private ViewController vc;
    private PlayView playView;

    public LoadingController(ViewController vc, NetworkController nc) {
        this.vc = vc;
        playView = new PlayView(vc, nc);
    }

    public void startGame(){

        vc.set(playView);
        playView.startListeners();
    }

    public void quitGame(){
        vc.set(new MenuView(new MenuController(vc)));
    }
}
