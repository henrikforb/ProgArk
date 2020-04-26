package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputMultiplexer;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import com.badlogic.gdx.math.Vector3;

import com.badlogic.gdx.input.GestureDetector;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ImpossibleGravity;
import com.mygdx.game.controller.GameController;
import com.mygdx.game.controller.CharacterController;
import com.mygdx.game.controller.ViewController;
import com.mygdx.game.interactiveElements.MenuBtn;
import com.mygdx.game.interactiveElements.PauseBtn;
import com.mygdx.game.model.Obstacle;
import com.mygdx.game.model.World;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * The view shown when a game is playing
 * Contains all the textures from the world and a few buttons in addition to the SuperView
 * Several methods that handles input from the user to control the character in the came
 */
public class PlayView extends SuperView {

    protected GameController gameController;
    private CharacterController pc;
    private Stage stage;
    private World world;
    private boolean multiplayer;
    private int gameID;

    // private HashMap<String, Character> enemyCharacters;

    private Socket socket;

    private MenuBtn menuBtn;
    private PauseBtn pauseBtn;

    
    public PlayView(ViewController vc, boolean multiplayer){

        this.world = new World();
        this.gameController = new GameController(vc, world);
        this.pc = new CharacterController(vc);

        this.multiplayer = multiplayer;

        this.multiplayer = true;
        System.out.println(this.multiplayer);

        this.pauseBtn = new PauseBtn();
        this.menuBtn = new MenuBtn();

        stage = new Stage(new ScreenViewport());

        int btnHeight = Gdx.graphics.getHeight() / 10;
        int btnWidth = btnHeight * 2;

        menuBtn.getMenuBtn().setSize(btnWidth, btnHeight);
        pauseBtn.getPauseBtn().setSize(btnWidth, btnHeight);

        menuBtn.getMenuBtn().setPosition(
                (float)btnWidth/4,
                Gdx.graphics.getHeight() - (float)btnHeight/4,
                Align.topLeft);

        pauseBtn.getPauseBtn().setPosition(
                btnWidth + (float)btnWidth/4*2,
                Gdx.graphics.getHeight() - (float)btnHeight/4,
                Align.topLeft);

        if (this.multiplayer) {
            startOnline();
        }
        startListeners();
    }

    /**
     * Listeners for touch gestures to notice input from the user
     */
    public void startListeners(){

        pauseBtn.getPauseBtn().clearListeners();
        menuBtn.getMenuBtn().clearListeners();

        InputMultiplexer multiplexer = new InputMultiplexer();
        multiplexer.addProcessor(stage);
        multiplexer.addProcessor(new GestureDetector(new GestureDetector.GestureAdapter() {
            @Override
            public boolean tap(float x, float y, int count, int button) {
                pc.touch(world.getCharacter());
                updateServer(0);
                return true;
            }
            @Override
            public boolean fling(float velocityX, float velocityY, int button) {
                if (velocityY > 10) { pc.swipe(world.getCharacter(), 0); }
                if (velocityY < -10) { pc.swipe(world.getCharacter(), 1); }
                updateServer(1);
                return true;
            }
        }));
        Gdx.input.setInputProcessor(multiplexer);

        stage.addActor(pauseBtn.getPauseBtn());
        stage.addActor(menuBtn.getMenuBtn());
        /*
        menuBtn.getMenuBtn().addListener(new ActorGestureListener() {
            @Override
            public boolean tap(float x, float y, int count, int button) {
                pc.touch(world.getCharacter());
                return true;
            }
            @Override
            public boolean fling(float velocityX, float velocityY, int button) {
                if (velocityY > 10) { pc.swipe(world.getCharacter(), 0); }
                if (velocityY < -10) { pc.swipe(world.getCharacter(), 1); }
                return true;
            }
        }));
        Gdx.input.setInputProcessor(multiplexer);

        stage.addActor(pauseBtn.getPauseBtn());
        stage.addActor(menuBtn.getMenuBtn());

         */

        pauseBtn.getPauseBtn().addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("pauseBtn is touched.");
                gameController.pauseGame();
            }
        });

        menuBtn.getMenuBtn().addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("menuBtn is touched.");
                gameController.quitGame();
                dispose();
            }
        });
    }

    /**
     * Inputs from the user calls on a function for the character
     * The action is defined in the characters Player model
     */
    @Override
    protected void handleInput() {
    }

    public void startOnline() {
        // enemyCharacters = new HashMap<String, Character>();
        connectSocket();
        configSocketEvents();
    }

    public void connectSocket() {
        try {
            // socket = IO.socket("https://progark-server.herokuapp.com/");
            socket = IO.socket("http://localhost:8080");
            socket.connect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

    //TODO pass socket to playview
    //TODO move connection logic to menu

    public void configSocketEvents() {
        socket.on(Socket.EVENT_CONNECT, new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                Gdx.app.log("SocketIO", "Connected");
            }
        }).on("socketID", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    Gdx.app.log("SocketIO", "My ID: " + id);
                    Gdx.app.log("SocketIO","connected to heroku server");
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting ID");
                }
            }
        }).on("newPlayer", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                    Gdx.app.log("SocketIO", "New Player Connect: " + id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting New PlayerID");
                }
            }
        }).on("playerDisconnected", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String id = data.getString("id");
                   // enemyCharacters.remove(id);
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting New PlayerID");
                }
            }
        }).on("playerMoved", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    String playerId = data.getString("id");
                    if (data.getInt("movement") == 0) {
                        pc.touch(world.getEnemy());
                    } else {
                        //pc.swipe(world.getEnemy(), );
                    }
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error getting New PlayerID");
                }
                Gdx.app.log("SocketIO", "Player jumped");
            }
        }).on("startGame", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    setGameID(data.getInt("gameID"));
                    world.createEnemy();
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error starting game");
                }
            }
        }).on("getPlayers", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONArray objects = (JSONArray) args[0];
                try {
                    for (int i = 0; i < objects.length(); i++) {

                        Vector3 position = new Vector3();
                        position.x = ((Double) objects.getJSONObject(i).getDouble("x")).floatValue();
                        position.y = ((Double) objects.getJSONObject(i).getDouble("y")).floatValue();
                        //externalPlayer
                    }
                } catch (JSONException e) {

                }
            }
        });
    }

    public void updateServer(int movementType) {
        JSONObject data = new JSONObject();
        try {
            data.put("movement", movementType);
            data.put("gameID", this.gameID);
            socket.emit("playerMoved", data);
        } catch (JSONException e) {
            Gdx.app.log("SocketIO", "Error sending update data");
        }
    }
    /**
     * Update method handles input from user, calls all textures update-methods and maked camera follow player
     *
     * @param dt Delta time
     */
    @Override
    public void update(float dt) {
        handleInput();

        world.update(dt, camera, gameController);
        background.update(dt);

        camera.position.set(world.getCharacter().getPosition().x + 100, ImpossibleGravity.HEIGHT/2, 0);
        camera.update();
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    /**
     * Each view is responsible for knowing what it needs to draw
     * The playView draws all the textures from the world model and the background
     *
     * @param sb SpriteBatch
     */
    @Override
    public void render (SpriteBatch sb){
        sb.setProjectionMatrix(camera.combined);
        sb.begin();

        sb.draw(background.getBackground(), camera.position.x-(camera.viewportWidth/2), 0, ImpossibleGravity.WIDTH, ImpossibleGravity.HEIGHT);

        for (Obstacle obstacle : world.getObstacleFactory().getObstacles()) {
            sb.draw(obstacle.getObstacle(), obstacle.getPosition().x, obstacle.getPosition().y, obstacle.getWidth(), obstacle.getHeight());
        }

        sb.draw(world.getCharacter().getSprite(), world.getCharacter().getPosition().x, world.getCharacter().getPosition().y);
        if (world.doesEnemyExists()) {
            sb.draw(world.getEnemy().getSprite(), world.getEnemy().getPosition().x, world.getEnemy().getPosition().y);
        }

        world.getCharacter().getScoreFont().setColor(Color.BLACK);
        world.getCharacter().getScoreFont().draw(sb, world.getCharacter().getScoreString(), camera.position.x+(ImpossibleGravity.WIDTH/3), ImpossibleGravity.HEIGHT-30);

        sb.draw(world.getGrass().getGround(), world.getGrass().getGroundPos1().x, world.getGrass().getGroundPos1().y);
        sb.draw(world.getGrass().getGround(), world.getGrass().getGroundPos2().x, world.getGrass().getGroundPos2().y);
        sb.draw(world.getHeaven().getGround(), world.getHeaven().getGroundPos1().x, world.getHeaven().getGroundPos1().y);
        sb.draw(world.getHeaven().getGround(), world.getHeaven().getGroundPos2().x, world.getHeaven().getGroundPos2().y);

        sb.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void dispose(){
        background.dispose();
        world.dispose();
        menuBtn.dispose();
        pauseBtn.dispose();
        System.out.println("PlayView Disposed");
    }

    @Override
    public void show() {}
}