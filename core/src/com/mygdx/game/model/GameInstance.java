package com.mygdx.game.model;

import com.badlogic.gdx.utils.Array;
import com.mygdx.game.controller.ViewController;

public class GameInstance {

    private Array<Player> playerArray;
    private World world;
    private ObstacleFactory obstacleFactory;
    private ViewController viewController;

    public GameInstance(Array<Player> playerArray, World world, ObstacleFactory obstacleFactory, ViewController viewController) {
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

    // Todo: find out how we want input given
    /*Idea: whatever class handles the actual input from the users puts a value into an array
     signifying either null, jump or gravity change which is then sent into game instance
    */
    public void handleInput(Array<Integer> playerInput) {
        for (int i = 0; i < playerArray.size; i++) {

        }
    }
}
