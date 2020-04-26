package com.mygdx.game.interactiveElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;


/**
 * Not used as of now. Logic for an alternative menu-solution that was changed
 */

public class StartBtn extends Actor {

    private Texture startTexture;
    public Image startBtn;

    public StartBtn() {
        startTexture = new Texture(Gdx.files.internal("startBtn.png"));
        this.startBtn = new Image(new TextureRegionDrawable(new TextureRegion(startTexture)));
    }

    public Image getStartBtn(){
        return this.startBtn;
    }

    public void dispose(){
        startTexture.dispose();
    }


}
