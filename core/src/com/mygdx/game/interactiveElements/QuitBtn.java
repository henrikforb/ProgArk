package com.mygdx.game.interactiveElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

// Making texture into a button
public class QuitBtn extends Actor {
    private Texture quitTexture;
    private TextureRegion quitTextureRegion;
    private TextureRegionDrawable quitTexRegionDrawable;
    public ImageButton quitBtn;

    public QuitBtn(){
        quitTexture = new Texture(Gdx.files.internal("quitBtn.png"));
        quitTextureRegion = new TextureRegion(quitTexture);
        quitTexRegionDrawable = new TextureRegionDrawable(quitTextureRegion);
        quitBtn = new ImageButton(quitTexRegionDrawable);
    }
}
