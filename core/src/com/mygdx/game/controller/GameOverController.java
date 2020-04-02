package com.mygdx.game.controller;

import com.mygdx.game.view.MenuView;

public class GameOverController {

    ViewController vc;

    public GameOverController(ViewController vc) {
        this.vc = vc;
    }

    public void BackToMenu(){
        vc.set(new MenuView(new MenuController(vc)));
    }
}
