package com.mygdx.game.controller;

import com.mygdx.game.view.MenuView;

public class PauseController {

    private ViewController vc;

    public PauseController(ViewController vc) {
        this.vc = vc;
    }

    public void BackToMenu(){
        vc.set(new MenuView(new MenuController(vc)));
    }

    public void ContinueGame(){
        vc.pop();
    }

}
