package com.mygdx.game.interactiveElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

// Making texture into a button
public class PlayBtn extends Actor {
    private Texture playTexture;
    private TextureRegion playTextureRegion;
    private TextureRegionDrawable playTexRegionDrawable;
    public ImageButton playBtn;

    public PlayBtn() {
        // Making the play button
        playTexture = new Texture(Gdx.files.internal("playBtn.png"));
        playTextureRegion = new TextureRegion(playTexture);
        playTexRegionDrawable = new TextureRegionDrawable(playTextureRegion);
        playBtn = new ImageButton(playTexRegionDrawable);
    }
}
