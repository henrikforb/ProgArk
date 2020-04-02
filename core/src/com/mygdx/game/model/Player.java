package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.ImpossibleGravity;

import static java.lang.Math.abs;

public class Player {

    private Texture texture;
    private Vector3 position;
    private Rectangle bounds;
    private boolean jump;
    private int gravity;
    private Vector3 velocity;
    private int score;

    public Player(){
        this.texture = new Texture("player.png"); // placeholder
        this.position = new Vector3(ImpossibleGravity.WIDTH/2,ImpossibleGravity.HEIGHT/2,0);
        this.bounds = new Rectangle(position.x, position.y, texture.getWidth(), texture.getHeight());
        this.gravity = ImpossibleGravity.GRAVITY; // set gravity to global value
        this.velocity = new Vector3(0, 0, 0);
        this.score = 0;
        this.jump = false;
    }

    public void update(float dt){

        this.position.y += this.velocity.y;

        if (this.jump){
            if (this.gravity > 0) {
                this.velocity.y -= this.gravity;
            } else {
                this.velocity.y += this.gravity;
            }
        }


        this.bounds.setPosition(this.position.x, this.position.y); // update bounds to players position

    }


    // called from controller based on input
    public void jump(){
        if (this.velocity.y == 0){
            if (this.gravity < 0) {
                this.velocity.y -= 35;
            } else if (this.gravity > 0){
                this.velocity.y += 35;
            }
            this.jump = true;
        }
    }

    // called from controller based on input
    public void switchGravity(){
        this.gravity = -this.gravity;
    }



    public Texture getTexture(){return this.texture;}

    public int getScore(){return this.score;}

    public Vector3 getPosition(){return this.position;}

    public void dispose(){
        this.texture.dispose();
    }


}
