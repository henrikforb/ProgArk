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
import com.mygdx.game.controller.NetworkController;
import com.mygdx.game.model.Settings;

/**
 * View for loanding screen while waiting to connect with another player in multiplayer mode
 * LoadingScreen can be displayed when a while-loop is true in another controller
 */
public class LoadingView extends SuperView{

    protected LoadingController loadingController;
    private NetworkController networkController;
    private Stage stage;
    private Texture loadingBar;
    private Texture loadingText;
    private Image loadingBarImage;
    private Image loadingTextImage;

    public LoadingView(final LoadingController lc, final NetworkController nc){

        this.loadingController = lc;
        this.networkController = nc;
        this.loadingBar = new Texture("loadingBar.png");
        this.loadingText = new Texture("loading.png");
        this.loadingBarImage = new Image(loadingBar);
        this.loadingTextImage = new Image(loadingText);
        this.stage = new Stage(new ScreenViewport());

        loadingBarImage.setSize((float) Gdx.graphics.getWidth() / 10 * 7, (float)Gdx.graphics.getHeight() / 2);
        loadingBarImage.setPosition((float)Gdx.graphics.getWidth() / 2, (float)Gdx.graphics.getHeight() / 5 * 3, Align.center);

        loadingTextImage.setSize((float) Gdx.graphics.getWidth() / 2, (float)Gdx.graphics.getHeight() / 4);
        loadingTextImage.setPosition((float)Gdx.graphics.getWidth() / 2, (float)Gdx.graphics.getHeight() / 6, Align.center);

        startListeners();
        startOnline();
    }

    @Override
    public void show(){

    }

    public void startOnline() {
        this.networkController.connectSocket();
        this.networkController.configSocketEvents();
    }
    /*
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

     */

    /**
     * Listeners for touch gestures and checkbox to notice input from the user
     */
    @Override
    public void startListeners() {
        Gdx.input.setInputProcessor(stage);
        stage.addActor(loadingBarImage);
        stage.addActor(loadingTextImage);

    }

    @Override
    protected void handleInput() {

    }

    private void startGame(String gameID) {
        loadingController.startGame(gameID, this.networkController);
    }

    @Override
    public void update(float dt) {
        if (Settings.getInstance().getStartMultiplayer()){
            startGame(Settings.getInstance().getId());
        }
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
