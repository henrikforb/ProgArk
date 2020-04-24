package com.mygdx.game.model;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ImpossibleGravity;

import java.util.Random;

/**
 * creates a list of obstacles and generates new obstacles in randomized size to the list
 */
public class ObstacleFactory {

    private Array<Obstacle> obstacles;

    /**
     * Help attributes for generateObstacle-method
     */
    private Random random;
    private int chosenObstacle;
    private int chosenHeight;
    private int chosenWidth;


    public ObstacleFactory() {
        random = new Random();
        obstacles = new Array<Obstacle>();
    }

    public Array<Obstacle> getObstacles(){
        return obstacles;
    }

    /**
     * Generates a random value between 0 and 2 to decide weather to generate a bottom- or top obstacle
     * Generates a random height and width and returns an either a lightning- or cactus obstacle of the chosen size
     */
    public Obstacle generateObstacle(Player character, Grass grass) {

        System.out.println(obstacles.size);

        chosenObstacle = random.nextInt(3);
        chosenHeight = 20 + random.nextInt(50);
        chosenWidth = chosenHeight * (70/30);

        if (chosenObstacle == 0) {
            return new Lightning(character.getPosition().x + ImpossibleGravity.WIDTH, ImpossibleGravity.HEIGHT, chosenWidth/4, chosenHeight*4);
        } else if (chosenObstacle == 1 || chosenObstacle == 2) {
            return new Cactus(character.getPosition().x + ImpossibleGravity.WIDTH, grass.getGroundHeight()-10, chosenWidth, chosenHeight);
        }
        return null;
    }

    /**
     * Deletes obstacles from obstacles-Array as they are out of viewport to prevent use of unnecessary storage
     */
    public void cleanObstacleArray(OrthographicCamera camera){
        for (Obstacle obstacle : obstacles) {
            if (obstacle.position.x < camera.position.x - camera.viewportWidth){
                obstacles.removeValue(obstacle, true);
            }

        }
    }

    /**
     * Update-method is run on condition in Worlds update-method
     *
     * @param dt delta time
     * @param camera Orthographic camera defined in SuperView
     * @param character the player instance
     * @param grass bottom ground instance
     */
    public void update(float dt, OrthographicCamera camera, Player character, Grass grass) {
        obstacles.add(generateObstacle(character, grass));
        cleanObstacleArray(camera);
    }
}
