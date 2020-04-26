package com.mygdx.game.controller;

import com.mygdx.game.view.MenuView;


/**
 * Not used as of now. Logic for an alternative menu-solution that was changed
 */
public class StartController {

    private ViewController vc;

    public StartController(ViewController vc){
        this.vc = vc;
    }

    public void multiPlayerPressed(){

        vc.set(new MenuView(new MenuController(vc)));
    }

    public void singlePlayerPressed(){
        vc.set(new MenuView(new MenuController(vc)));
    }
}
