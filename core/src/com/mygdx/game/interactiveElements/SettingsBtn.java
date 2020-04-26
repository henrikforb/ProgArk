package com.mygdx.game.interactiveElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class SettingsBtn extends Actor {
    private Texture settingsTexture;
    public Image settingsBtn;

    public SettingsBtn(){
        settingsTexture = new Texture(Gdx.files.internal("settingsBtn.png"));
        this.settingsBtn = new Image(new TextureRegionDrawable(new TextureRegion(settingsTexture)));
    }

    public Image getSettingsBtn(){
        return this.settingsBtn;
    }

    public void dispose(){
        settingsTexture.dispose();
    }
}
