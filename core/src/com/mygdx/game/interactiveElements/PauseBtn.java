package com.mygdx.game.interactiveElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class PauseBtn extends Actor {

    private Texture pauseTexture;
    private TextureRegion pauseTextureRegion;
    private TextureRegionDrawable pauseTexRegionDrawable;
    public ImageButton pauseBtn;

    public PauseBtn() {
        // Making the play button
        pauseTexture = new Texture(Gdx.files.internal("pauseBtn.png"));
        pauseTextureRegion = new TextureRegion(pauseTexture);
        pauseTexRegionDrawable = new TextureRegionDrawable(pauseTextureRegion);
        pauseBtn = new ImageButton(pauseTexRegionDrawable);
    }
}
