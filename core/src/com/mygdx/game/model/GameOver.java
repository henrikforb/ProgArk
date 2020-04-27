package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.ui.Image;

public class GameOver {

    private Image gameOverImage;
    Texture gameOver;

    public GameOver(boolean multiplayer, boolean enemyDied){

        if (multiplayer){
            if (enemyDied){
                gameOver = new Texture("gameOverWon.png");
            } else {
                gameOver = new Texture("gameOverLose.png");
            }
        } else {
            gameOver = new Texture("gameOver.png");
        }
        this.gameOverImage = new Image(gameOver);
    }

    public Image getGameOverImage(){
        return gameOverImage;
    }

    public void dispose(){
        gameOver.dispose();
    }

}
