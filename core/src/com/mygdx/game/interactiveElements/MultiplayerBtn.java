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

public class MultiplayerBtn extends Actor {

    private Texture multiTexture;
    public Image multiplayerBtn;

    public MultiplayerBtn() {
        multiTexture = new Texture(Gdx.files.internal("multiplayerBtn.png"));
        this.multiplayerBtn = new Image(new TextureRegionDrawable(new TextureRegion(multiTexture)));
    }

    public Image getMultiplayerBtn(){
        return this.multiplayerBtn;
    }

    public void dispose(){
        multiTexture.dispose();
    }
}
