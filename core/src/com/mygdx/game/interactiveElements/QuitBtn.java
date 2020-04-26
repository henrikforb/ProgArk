package com.mygdx.game.interactiveElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class QuitBtn extends Actor {
    private Texture quitTexture;
    public Image quitBtn;

    public QuitBtn(){
        quitTexture = new Texture(Gdx.files.internal("quitBtn.png"));
        this.quitBtn = new Image(new TextureRegionDrawable(new TextureRegion(quitTexture)));
    }

    public Image getQuitBtn(){
        return this.quitBtn;
    }

    public void dispose(){
        quitTexture.dispose();
    }
}
