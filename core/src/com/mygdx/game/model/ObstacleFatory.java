package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Array;

import java.util.Random;

/**
 * Created by henrikforb on 07. April 2020.
 **/

public class ObstacleFatory {

    private Random random;
    private int chosenObstacle;

    //private static final int noSpike = 0;
    private static final int topSpike = 0;
    private static final int bottomSpike = 1;

    public ObstacleFatory() {
        random = new Random();
    }

    public Obstacle generateObstacle() {

        chosenObstacle = random.nextInt(2);

        //if (chosenObstacle == 0) {
            //return null;

        if (chosenObstacle == 0) {
            return new TopSpikes();
        } else if (chosenObstacle == 1) {
            return new BottomSpikes();
        }
        return null;
    }

}
