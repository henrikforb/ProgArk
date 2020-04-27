package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.NetworkController;

import java.util.Random;

import com.mygdx.game.singelton.Settings;

/**
 * Model for game environment called from play view
 * Every texture in the game is created here
 */
public class World {

    private Grass grass;
    private Heaven heaven;
    private Music music;
    private ObstacleFactory obstacleFactory;
    private Character character;
    private HighScore highScore = new HighScore();

    private Character enemy;
    private boolean online;
    private boolean enemyExists = false;
    private boolean enemyDead = false;
    private boolean enemyDisconnected = false;

    private NetworkController networkController;

    /**
     *  Help attributes for update-method
      */
    private long lastObstacle;
    private Random obstacle_occurrence;

    public World() {
        online = false;
        grass = new Grass();
        heaven = new Heaven();
        obstacleFactory = new ObstacleFactory(false);

        character = new Character("playeranimation.png", 100);

        music = Gdx.audio.newMusic(Gdx.files.internal("offLimits.wav"));
        music.setLooping(true);
        lastObstacle = System.currentTimeMillis();
        obstacle_occurrence = new Random();
    }

    public World(NetworkController nc) {
        online = true;
        grass = new Grass();
        heaven = new Heaven();
        obstacleFactory = new ObstacleFactory(true);
        character = new Character("playeranimation.png", 0);
        System.out.println("online");

        music = Gdx.audio.newMusic(Gdx.files.internal("offLimits.wav"));
        music.setLooping(true);
        lastObstacle = System.currentTimeMillis();
        obstacle_occurrence = new Random();

        this.networkController = nc;
    }

    public ObstacleFactory getObstacleFactory(){
        return obstacleFactory;
    }

    public Grass getGrass() {
        return grass;
    }

    public Heaven getHeaven() {
        return heaven;
    }

    public Character getCharacter(){
        return character;
    }

    public void createEnemy() {
        this.enemy = new Character("playeranimation_multi.png", 0);
        this.enemyExists = true;
    }

    public Character getEnemy() {
        return enemy;
    }

    public void setEnemyDead() {
        enemyDead = true;
    }

    public boolean getEnemyExists() {
        return this.enemyExists;
    }

    public void setEnemyDisconnected(){
        enemyDisconnected = true;
    }

    public void playMusic(){music.play();}

    public void stopMusic(){music.stop();}

    public void pauseMusic(){music.pause();}

    /**
     * The update-method in World model is called from the update-method in playView
     * - Makes sure all assets gets updated
     * - Increases player speed during the game
     * - Generates new obstacles
     * - Checks for collision between player and obstacle
     *
     * @param dt delta time
     * @param camera Orthographic camera defined in SuperView
     * @param gameController Controller class for playView
     */

    public void update(float dt, OrthographicCamera camera, GameController gameController) {
        character.update(dt);
        if (online) {
            enemy.update(dt);
        }
        grass.update(dt, camera);
        heaven.update(dt, camera);


        /**
         * Updates the ObstacleFactory to generate a new obstacle every 0,9 sec + random up tp 2 sec
         * Checks the speed of character to make obstacle occurrence proportional with speed
         */

        if (online && Settings.getInstance().getQueueSizeTime() != 0) {
            if (System.currentTimeMillis() - lastObstacle >= 900 + Settings.getInstance().getNextTime()) {
                obstacleFactory.update(dt, camera, getCharacter(), getGrass());
                lastObstacle = System.currentTimeMillis();
                Settings.getInstance().removeFirstTime();
            }
        } else if (!online) {
            if (System.currentTimeMillis() - lastObstacle >= 900 + obstacle_occurrence.nextInt((2000-character.getSpeed()))) {
                obstacleFactory.update(dt, camera, getCharacter(), getGrass());
                lastObstacle = System.currentTimeMillis();
            }
        }

        /**
         * Updates all the obstacles and checks for collision with player
         * ends game if collision is detected
         */
        for (Obstacle obstacle : obstacleFactory.getObstacles()) {
            obstacle.update(dt);
            if (obstacle.collides(character.getBounds())) {
                stopMusic();
                double score = character.getScore();
                highScore.addScoreToHighScore(score);
                //TODO save score to HighScore
                if (enemyExists) {
                    gameController.gameOver(true, false);
                    if (online) {
                        networkController.handleDeath();
                        Settings.getInstance().setMultiplayerNotReady();
                    }
                } else {
                    gameController.gameOver(false, false);
                }
            }
        }
        if (enemyDead) {
            stopMusic();
            gameController.gameOver(true, true);
            Settings.getInstance().setMultiplayerNotReady();
        }
    }

    public void dispose() {
        character.dispose();
        if (enemyExists) {
            enemy.dispose();
        }
        music.dispose();
        grass.dispose();
        heaven.dispose();
        for (Obstacle obstacle : obstacleFactory.getObstacles()) {
            obstacle.dispose();
        }
    }
}
