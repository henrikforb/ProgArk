package com.mygdx.game.controller;

public class HelpController {

    private ViewController vc;

    public HelpController(ViewController vc) {
        this.vc = vc;
    }

    public void backToMenu(){
        vc.pop();
    }

}
