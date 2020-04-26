package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

/**
 * Bottom obstacle generated from ObstacleFactory
 **/

public class Cactus extends Obstacle {

    public Cactus(float x, float y, int height, int width) {
        this.obstacle = new Texture("bottomSpikes.png");
        this.position = new Vector3(x, y-5, 0);
        this.height = height;
        this.width = width;
        this.collision_bounds = new Rectangle(position.x + width/13, position.y, width - width/13, height - height/16);

    }
}
