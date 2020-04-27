package com.mygdx.game.controller;

import com.mygdx.game.view.PlayView;

public class LoadingController {

    private ViewController vc;

    public LoadingController(ViewController vc) {
        this.vc = vc;
    }

    public void startGame(String gameID, NetworkController nc){

        vc.set(new PlayView(vc, nc, gameID));
    }
}
