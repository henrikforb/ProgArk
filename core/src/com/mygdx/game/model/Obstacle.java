package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by henrikforb on 07. April 2020.
 *
 * Superclass for obstacles
 **/

public abstract class Obstacle {

    protected Vector2 position;
    protected Texture spikes;

    public Obstacle() {}

    public abstract Texture getSpikes();
    public abstract Vector2 getPosition();
    public abstract void dispose();
}
