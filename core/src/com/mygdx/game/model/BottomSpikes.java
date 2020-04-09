package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by henrikforb on 07. April 2020.
 **/

public class BottomSpikes extends Obstacle {

    public BottomSpikes() {
        this.spikes = new Texture("bottomSpikes.png");
        this.position = new Vector2(50, 0); // x-pos må endres
    }

    @Override
    public Texture getSpikes() {
        return spikes;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }

    @Override
    public void dispose() {
        this.spikes.dispose();
    }
}
