package com.mygdx.game.interactiveElements;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;

public class HighscoreBtn extends Actor {

    private Texture highscoreTexture;
    public Image highscoreBtn;

    public HighscoreBtn(){
        highscoreTexture = new Texture(Gdx.files.internal("highscoreBtn.png"));
        this.highscoreBtn = new Image(new TextureRegionDrawable(new TextureRegion(highscoreTexture)));
    }

    public Image getHighScoreBtn(){ return this.highscoreBtn;}

    public void dispose(){
        highscoreTexture.dispose();
    }

}
