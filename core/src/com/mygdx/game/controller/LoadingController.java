package com.mygdx.game.controller;

import com.mygdx.game.model.Character;
import com.mygdx.game.model.Settings;
import com.mygdx.game.view.PlayView;

import io.socket.client.Socket;

public class LoadingController {

    private ViewController vc;

    public LoadingController(ViewController vc) {
        this.vc = vc;
    }

    public void startGame(String gameID, NetworkController nc){

        vc.set(new PlayView(vc, nc, gameID));
    }
}
