package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ImpossibleGravity;
import com.mygdx.game.controller.LoadingController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

/**
 * View for loanding screen while waiting to connect with another player in multiplayer mode
 * LoadingScreen can be displayed when a while-loop is true in another controller
 */
public class LoadingView extends SuperView{

    protected LoadingController loadingController;
    private Stage stage;
    private Texture loadingBar;
    private Image loadingBarImage;
    private Socket socket;

    public LoadingView(final LoadingController loadingController){

        this.loadingController = loadingController;
        this.loadingBar = new Texture("loadingBar.png");
        this.loadingBarImage = new Image(loadingBar);
        this.stage = new Stage(new ScreenViewport());

        loadingBarImage.setSize((float) Gdx.graphics.getWidth() / 10 * 7, (float)Gdx.graphics.getHeight() / 2);
        loadingBarImage.setPosition((float)Gdx.graphics.getWidth() / 2, (float)Gdx.graphics.getHeight() / 5 * 3, Align.center);
        startListeners();
        startOnline();
    }

    @Override
    public void show(){

    }

    public void startOnline() {
        connectSocket();
        configSocketEvents();
    }

    public void connectSocket() {
        try {
            socket = IO.socket("https://progark-server.herokuapp.com/");
            //socket = IO.socket("http://localhost:8080");
            socket.connect();
        } catch (Exception e) {
            System.out.println(e);
        }
    }

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
        }).on("startGame", new Emitter.Listener() {
            @Override
            public void call(Object... args) {
                JSONObject data = (JSONObject) args[0];
                try {
                    startGame("" + data.getInt("gameID"));
                } catch (JSONException e) {
                    Gdx.app.log("SocketIO", "Error starting game");
                }
            }
        });
    }

    /**
     * Listeners for touch gestures and checkbox to notice input from the user
     */
    @Override
    public void startListeners() {
        Gdx.input.setInputProcessor(stage);
        stage.addActor(loadingBarImage);

    }

    @Override
    protected void handleInput() {

    }

    private void startGame(String gameID) {
        loadingController.startGame(socket, gameID);
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background.getBackground(), camera.position.x-(camera.viewportWidth/2), 0, ImpossibleGravity.WIDTH, ImpossibleGravity.HEIGHT);
        sb.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        loadingBar.dispose();
        System.out.println("Loading View Disposed");
    }
}
