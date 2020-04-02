package com.mygdx.game.interactiveElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.ImageButton;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

// Making texture into a button
public class SettingsBtn extends Actor {
    private Texture settingsTexture;
    private TextureRegion settingsTextureRegion;
    private TextureRegionDrawable settingsTexRegionDrawable;
    public ImageButton settingsBtn;

    public SettingsBtn(){
        settingsTexture = new Texture(Gdx.files.internal("settingsBtn.png"));
        settingsTextureRegion = new TextureRegion(settingsTexture);
        settingsTexRegionDrawable = new TextureRegionDrawable(settingsTextureRegion);
        settingsBtn = new ImageButton(settingsTexRegionDrawable);
    }
}
