package com.mygdx.game.interactiveElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class Text {
    private Texture textTexture;
    public Image textImage;

    public Text() {
    }

    public Image getHelpText(){
        textTexture = new Texture(Gdx.files.internal("helpText.png"));
        this.textImage = new Image(new TextureRegionDrawable(new TextureRegion(textTexture)));
        return this.textImage;
    }

    public void dispose(){
        textTexture.dispose();
    }
}
