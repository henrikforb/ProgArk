package com.mygdx.game.controller;


import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ViewController {

    private Stack<SuperView> views;

    public ViewController() {
        views = new Stack<SuperView>();
    }

    public void push(SuperView view){
        views.push(view);
    }

    public void pop(){
        views.pop();
    }

    public void set(SuperView view){
        views.pop();
        views.push(view);
    }

    public void update(float dt){
        views.peek().update();
    }

    public void render(SpriteBatch sb){
        views.peek().render(sb);
    }


}
