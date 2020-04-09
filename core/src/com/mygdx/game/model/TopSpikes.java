package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.mygdx.game.ImpossibleGravity;


/**
 * Created by henrikforb on 07. April 2020.
 **/

public class TopSpikes extends Obstacle {

    public TopSpikes() {
        this.spikes = new Texture("topSpikes.png");
        this.position = new Vector2(200, ImpossibleGravity.HEIGHT - spikes.getHeight()/2); // x-pos må endres
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
