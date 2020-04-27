package com.mygdx.game.model;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.utils.Array;
import com.mygdx.game.ImpossibleGravity;
import com.mygdx.game.singelton.Settings;

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

    private boolean online;
    private int nextObstacle = 10;  // initiated with standard values in case the server hasn't
    private int nextObstacleHeight = 25; // transmitted any yet


    public ObstacleFactory(boolean online) {
        this.online = online;
        if (!online) {
            random = new Random();
        }
        obstacles = new Array<Obstacle>();
    }

    public Array<Obstacle> getObstacles(){
        return obstacles;
    }

    /**
     * Generates a random value between 0 and 2 to decide whether to generate a bottom- or top obstacle
     * Generates a random height and width and returns an either a lightning- or cactus obstacle of the chosen size
     *
     * If online, the values chosen obstacle and chosenHeight will be recieved from the server instead
     *
     */
    public Obstacle generateObstacle(Character character, Grass grass) {

        if (online){
            chosenObstacle = Settings.getInstance().getNextObstacle();
            chosenHeight = 50 + Settings.getInstance().getNextObstacleHeight();
        } else {
            chosenObstacle = random.nextInt(11);
            chosenHeight = 50 + random.nextInt(50);
        }
        chosenWidth = chosenHeight / 3;


        if (chosenObstacle < 4) {
            return new Lightning(character.getPosition().x + ImpossibleGravity.WIDTH, ImpossibleGravity.HEIGHT, chosenWidth/2, chosenHeight*2);
        } else if (chosenObstacle < 10) {
            return new Cactus(character.getPosition().x + ImpossibleGravity.WIDTH, grass.getGroundHeight()-10, chosenHeight, chosenWidth);
        } else {
            return new Cactus(character.getPosition().x + ImpossibleGravity.WIDTH, grass.getGroundHeight()-10, 200, 50);
        }
    }

    /**
     * Receives random values from server and generates obstacles from them
     * Generates a random height and width from server and returns an either a lightning- or cactus obstacle of the chosen size
     *
     *
     */


    /**
     * Deletes obstacles from obstacles-Array as they are out of viewport to prevent use of unnecessary storage
     */
    public void cleanObstacleArray(OrthographicCamera camera){
        for (Obstacle obstacle : obstacles) {
            if (obstacle.position.x < camera.position.x - camera.viewportWidth){
                obstacles.removeValue(obstacle, true);
                obstacle.dispose();
            }

        }
    }

    public void setNextObstacle(int obstacle, int height) {
        this.nextObstacle = obstacle;
        this.nextObstacleHeight = height;
    }

    /**
     * Update-method is run on condition in Worlds update-method
     *
     * @param dt delta time
     * @param camera Orthographic camera defined in SuperView
     * @param character the player instance
     * @param grass bottom ground instance
     */
    public void update(float dt, OrthographicCamera camera, Character character, Grass grass) {
        if (!online) {
            obstacles.add(generateObstacle(character, grass));
        } else {
            if (Settings.getInstance().getMutiplayerReady()){
                obstacles.add(generateObstacle(character, grass));
            }
        }
        cleanObstacleArray(camera);
    }
}
