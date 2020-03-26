package com.mygdx.game.model;

import com.badlogic.gdx.utils.Array;

public class GameInstance {

    private Array<Player> playerArray;
    private World world;
    private ObstacleFactory obstacleFactory;

    public GameInstance(Array<Player> playerArray, World world, ObstacleFactory obstacleFactory) {
        this.obstacleFactory = obstacleFactory;
        this.playerArray = playerArray;
        this.world = world;
    }

    public void update(float dt) {
        for(int i=0; i < playerArray.size; i++) {
            playerArray.get(i).update(dt);
            world.update(dt);
        }
    }
}
