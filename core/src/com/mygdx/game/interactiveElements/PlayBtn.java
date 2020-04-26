package com.mygdx.game.interactiveElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PlayBtn extends Actor {
    private Texture playTexture;
    public Image playBtn;

    public PlayBtn() {
        playTexture = new Texture(Gdx.files.internal("playBtn.png"));
        this.playBtn = new Image(new TextureRegionDrawable(new TextureRegion(playTexture)));
    }

    public Image getPlayBtn(){
        return this.playBtn;
    }

    public void dispose(){
        playTexture.dispose();
    }


}
