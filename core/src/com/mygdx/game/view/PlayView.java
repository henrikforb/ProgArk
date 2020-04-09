package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ImpossibleGravity;
// import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.PlayerController;
import com.mygdx.game.controller.ViewController;
import com.mygdx.game.interactiveElements.PauseBtn;
import com.mygdx.game.model.BottomSpikes;
import com.mygdx.game.model.Obstacle;
import com.mygdx.game.model.ObstacleFatory;
import com.mygdx.game.model.Player;
import com.mygdx.game.model.TopSpikes;
import com.mygdx.game.model.World;

// Import the sprites here, when these are created in model (e.g. the character, obstacles)

public class PlayView extends SuperView {
    protected ViewController gameController;
    private PlayerController pc;
    private Stage stage;
    private PauseBtn pauseBtn;

    // Variables that can be used for our obstacles.
    // NOT SURE IF THESE BELONG HERE - MODEL PEOPLE CAN YOU LOOK AT THIS?
    private static final int OBSTACLE_SPACING = 125;
    private static final int OBSTACLE_COUNT = 4;
    private static final int GROUND_Y_OFFSET = -50;

    // The elements in our view - instantiate character, obstacles etc.
    // Can also import e.g. gameWorld, engine etc.
    private Player character;
    private World world;
    private ObstacleFatory obstacleFatory;
    private Obstacle obstacle;

    // Make an array of the obstacles (obstacle must be made as a model).
    //private Array<Obstacle> obstacles;

    public PlayView(ViewController vc){

        this.gameController = vc;
        this.pc = new PlayerController(vc);
        // this.pauseBtn = new PauseBtn();

        // Setting up the stage, adding the actors (buttons)
        stage = new Stage(new ScreenViewport());
        // stage.addActor(pauseBtn);
        Gdx.input.setInputProcessor(stage);

        // Position the button
        // pauseBtn.setPosition(camera.position.x - pauseBtn.getWidth() / 2, camera.position.y);

        world = new World();
        character = new Player();
        camera.setToOrtho(false, ImpossibleGravity.WIDTH / 2, ImpossibleGravity.HEIGHT / 2);

        //TODO fix dimentions for background
        world.setGroundPos1(new Vector2(camera.position.x - camera.viewportWidth / 2, GROUND_Y_OFFSET));
        world.setGroundPos2(new Vector2((camera.position.x - camera.viewportWidth / 2) + world.getGround().getWidth(), GROUND_Y_OFFSET));

        // GENERATING NEW OBSTACLES
        obstacleFatory = new ObstacleFatory();
        obstacle = obstacleFatory.generateObstacle(); // TODO: får kun laget en obstacle på denne måten, må flyttes til update() senere?

        /*
        obstacles = new Array<Obstacle>();
        for(int i = 1; i <= OBSTACLE_COUNT; i++){
            obstacles.add(new Obstacle(i * (OBSTACLE_SPACING + Obstacle.OBSTACLE_WIDTH)));
        }

         */
    }

    @Override
    protected void handleInput() {
        // Every input from the user should call on a function for the character.
        // The action is defined in the model-class of the character.

        if (Gdx.input.justTouched()) {
            pc.touch(character);
        }

        // Put the rest of the actions here
/*
        pauseBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("PauseBtn is pressed.");
                // gameController.pauseGame();
            }
        });

 */
    }

    @Override
    public void update(float dt) {
        handleInput();
        // TODO Animation of ground. We need the
        //updateGround(); and/or updateBackground();

        // The character must have an update -and getPosition-method in its model.
        // For other methods required, see which functions are called upon character below.
        character.update(dt);
        camera.position.x = character.getPosition().x + 80;

        // This is for making the obstacles "move", and then repositioning them
        /*
        for(int i = 0; i < obstacles.size; i++){
            Obstacle obstacle = obstacles.get(i);

            // When obstacle is out of camera view, reposition to other end
            if(camera.position.x - (camera.viewportWidth / 2) > obstacle.getPosTopTube().x + obstacle.getTopTube().getWidth()){
                obstacle.reposition(obstacle.getPosTopTube().x  + ((Obstacle.TUBE_WIDTH + OBSTACLE_SPACING) * OBSTACLE_COUNT));
            }

            // If character hits obstacle, change to menu state
            if(obstacle.collides(character.getBounds()))
                gameController.gameover(); //Have not been made yet
        }

         */

        // If character hits ground, change to menu state
        /*
        if(character.getPosition().y <= ground.getHeight() + GROUND_Y_OFFSET)
            gameController.gameover(); //Have not been made yet
        camera.update();

         */

    }

    @Override
    // Each view is responsible for knowing what it needs to draw.
    // Here we draw the background, character, obstacles and ground.
    public void render(SpriteBatch sb) {
        // sb.setProjectionMatrix(camera.combined);
        sb.begin();

        sb.draw(world.getBackground(), 0, 0, world.getBackground().getWidth()/4, world.getBackground().getHeight()/4);
        sb.draw(world.getGround(), world.getGroundPos1().x, world.getGroundPos1().y);
        sb.draw(world.getGround(), world.getGroundPos2().x, world.getGroundPos2().y);

        sb.draw(character.getTexture(), character.getPosition().x, character.getPosition().y);
        sb.draw(obstacle.getSpikes(), obstacle.getPosition().x, obstacle.getPosition().y, 70, 100);

        /*
        for(Obstacle obstacle : obstacles) {
            sb.draw(obstacle.getTopTube(), obstacle.getPosTopTube().x, obstacle.getPosTopTube().y);
            sb.draw(obstacle.getBottomTube(), obstacle.getPosBotTube().x, obstacle.getPosBotTube().y);
        }

         */
        sb.end();
    }

    //TODO Regine
    /*
    private void updateGround(){
        if(camera.position.x - (camera.viewportWidth / 2) > world.getGroundPos1().x + world.getGround().getWidth())
            world.getGroundPos1().add(world.getGround().getWidth() * 2, 0);
        if(camera.position.x - (camera.viewportWidth / 2) > world.getGroundPos2().x + world.getGround().getWidth())
            world.getGroundPos2().add(world.getGround().getWidth() * 2, 0);
    }
     */

    @Override
    public void dispose(){
        // Remember to dispose of everything drawn on the screen.
        world.dispose();
        character.dispose();
        obstacle.dispose();

        /*
        for(Obstacle obstacle : obstacles)
            obstacle.dispose();

         */
        System.out.println("Play View Disposed");
    }


}
