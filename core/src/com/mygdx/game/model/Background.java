package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Background shown in all views
 */
public class Background {

    private Animation backgroundAnimation;

    private Texture background;
    private static int BG_MODE;

    public Background(){
        this.background = new Texture("background.png"); //locally saved
        backgroundAnimation = new Animation(new TextureRegion(background), 2, 0.5f);
    }

    public void update(float dt) {
        backgroundAnimation.update(dt);
    }

    public TextureRegion getBackground() {
        return backgroundAnimation.getFrame();
    }

    public void dispose() {
        background.dispose();
    }

}
