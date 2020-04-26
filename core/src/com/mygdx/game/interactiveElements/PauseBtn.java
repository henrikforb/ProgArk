package com.mygdx.game.interactiveElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PauseBtn extends Actor {
    private Texture pauseTexture;
    public Image pauseBtn;

    public PauseBtn() {
        pauseTexture = new Texture(Gdx.files.internal("pauseBtn.png"));
        this.pauseBtn = new Image(new TextureRegionDrawable(new TextureRegion(pauseTexture)));
    }

    public Image getPauseBtn(){
        return this.pauseBtn;
    }

    public void dispose(){
        pauseTexture.dispose();
    }
}
