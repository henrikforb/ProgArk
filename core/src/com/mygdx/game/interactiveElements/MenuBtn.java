package com.mygdx.game.interactiveElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuBtn extends Actor {
    private Texture menuTexture;
    public Image menuBtn;

    public MenuBtn(){
        menuTexture = new Texture(Gdx.files.internal("menuBtn.png"));
        this.menuBtn = new Image(new TextureRegionDrawable(new TextureRegion(menuTexture)));
    }

    public Image getMenuBtn(){
        return this.menuBtn;
    }

    public void dispose(){
        menuTexture.dispose();
    }
}
