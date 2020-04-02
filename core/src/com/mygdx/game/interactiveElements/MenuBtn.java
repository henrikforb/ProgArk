package com.mygdx.game.interactiveElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class MenuBtn extends Actor {

    private Texture menuTexture;
    private TextureRegion menuTextureRegion;
    private TextureRegionDrawable menuTexRegionDrawable;
    public ImageButton menuBtn;

    public MenuBtn(){
        // Making the menu button
        menuTexture = new Texture(Gdx.files.internal("menuBtn.png"));
        menuTextureRegion = new TextureRegion(menuTexture);
        menuTexRegionDrawable = new TextureRegionDrawable(menuTextureRegion);
        menuBtn = new ImageButton(menuTexRegionDrawable);
    }
}
