package com.mygdx.game.controller;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.model.Settings;
import com.mygdx.game.view.SuperView;

import java.util.Stack;

public class ViewController {

    private Stack<SuperView> views;
    private Settings settings;

    public ViewController() {
        views = new Stack<SuperView>();
        settings = Settings.getInstance();
    }

    public void push(SuperView view){
        views.push(view);
        System.out.println("pushed view: "+view);
        System.out.println(views);
    }

    public void pop(){
        SuperView view = views.pop();
        views.peek().startListeners();
        System.out.println("pushed view: "+ view);
        System.out.println(views);
    }

    public SuperView peek(){
        return views.peek();
    }

    public void set(SuperView view){
        views.pop();
        views.push(view);
    }

    public void update(float dt){
        views.peek().update(dt);
    }

    public void render(SpriteBatch sb){
        views.peek().render(sb);
    }

    public Settings getModel(){
        return settings;
    }

}
