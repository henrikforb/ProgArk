package com.mygdx.game.interactiveElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class HelpBtn extends Actor {
    private Texture helpTexture;
    public Image helpBtn;

    public HelpBtn(){
        helpTexture = new Texture(Gdx.files.internal("helpBtn.png"));
        this.helpBtn = new Image(new TextureRegionDrawable(new TextureRegion(helpTexture)));
    }

    public Image getHelpBtn(){
        return this.helpBtn;
    }

    public void dispose(){
        helpTexture.dispose();
    }
}
