package com.mygdx.game.controller;

import com.mygdx.game.model.Character;
import com.mygdx.game.view.GameOverView;

public class CharacterController {

    private ViewController vc;

    public CharacterController(ViewController vc){
        this.vc = vc;
    }

    public void touch(Character character){
        character.jump();
    }

    public void swipe(Character character, int direction) {
        character.switchGravity(direction);
    }

    public void gameOver(){
        vc.set(new GameOverView(new GameOverController(vc)));
    }



}
