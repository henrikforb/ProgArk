package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.ImpossibleGravity;

/**
 * The player containing the interaction logic and movement of player
 */
public class Character {

    private int SPEED = 100;
    private Sprite player;
    private Vector3 position;
    private Rectangle bounds;
    private float gravity;
    private Vector3 velocity;
    private Score score;

    /**
     * help attribute for update-method
     */
    private long increaseSpeedCounter;
    private Animation playerAnimation;

    public Character(String texturePath) {
        player = new Sprite(new Texture(texturePath));
        position = new Vector3(ImpossibleGravity.WIDTH / 2 - player.getWidth() / 3, -ImpossibleGravity.HEIGHT / 2, 0);
        bounds = new Rectangle(position.x, position.y, player.getWidth() / 3 - (player.getWidth()/3)/6 , player.getHeight());
        gravity = ImpossibleGravity.GRAVITY; // set gravity to global value
        velocity = new Vector3(1, 0, 0);
        playerAnimation = new Animation(new TextureRegion(player), 3, 0.4f);
        increaseSpeedCounter = System.currentTimeMillis();
        score = new Score();
    }

    public int getSpeed() {
        return SPEED;
    }

    public float getScore() {
        return score.getScore();
    }

    public BitmapFont getScoreFont(){
        return score.getScoreFont();
    }

    public String getScoreString(){
        return score.getScoreString();
    }

    public Vector3 getPosition() {
        return this.position;
    }

    public Rectangle getBounds() {
        return bounds;
    }

    /**
     * Called from controller based on user input. Makes the player jump if touched
     */
    public void jump() {
        if (this.velocity.y == 0) {
            if (this.gravity < 0) {
                this.velocity.add(0, 18.5f, 0);
            } else if (this.gravity > 0) {
                this.velocity.add(0, -18.5f, 0);
            }
        }
    }

    /**
     * Called from controller based on user input. Makes the player switch gravity if swiped
     */
    public void switchGravity(int direction) {
        if (this.velocity.y == 0) {
            if (direction == 1 && this.gravity < 0) {
                this.gravity = -this.gravity;
                this.playerAnimation.flip();
            }
            if (direction == 0 && this.gravity > 0) {
                this.gravity = -this.gravity;
                this.playerAnimation.flip();
            }
        }
    }


    /**
     * Called on condition update-method. Increases player speed
     */
    public void increaseSPEED() {
        this.SPEED += 10;
    }



    public TextureRegion getSprite() {return playerAnimation.getFrame();}


    /**
     * Called from worlds update-method. Makes the player move in game world and makes sure the collision bounds follows and score is updated
     * Calls method for increasing the speed of the player a small amount every 2 seconds up to a speed of 500
     *
     * @param dt delta time
     */
    public void update(float dt) {

        playerAnimation.update(dt);
        position.add(SPEED * dt, 0, 0);
        score.update(dt);

        this.position.y += this.velocity.y;
        this.position.x += this.velocity.x;

        this.velocity.add(0, gravity, 0);

        if (this.position.y <= 50 && velocity.y < 0) {
            this.velocity.y = 0;
            this.position.y = 50;
        }
        if (this.position.y >= 430 && velocity.y > 0) {
            this.velocity.y = 0;
            this.position.y = 430;
        }
        this.bounds.setPosition(position.x, position.y); // update bounds to players position

        if (getSpeed() < 500 && System.currentTimeMillis() - increaseSpeedCounter >= 2000) {
            increaseSpeedCounter = System.currentTimeMillis();
            increaseSPEED();
        }
    }

    public void dispose() {
        this.player.getTexture().dispose();
        score.dispose();
    }
}