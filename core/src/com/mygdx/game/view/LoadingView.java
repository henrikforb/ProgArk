package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ImpossibleGravity;
import com.mygdx.game.controller.LoadingController;
import com.mygdx.game.controller.NetworkController;
import com.mygdx.game.interactiveElements.MenuBtn;
import com.mygdx.game.singelton.Settings;

/**
 * View for loanding screen while waiting to connect with another player in multiplayer mode
 * LoadingScreen can be displayed when a while-loop is true in another controller
 */
public class LoadingView extends SuperView{

    protected LoadingController loadingController;
    private NetworkController networkController;
    private Stage stage;
    private MenuBtn menuBtn;
    private Texture loadingBar;
    private Texture loadingText;
    private Image loadingBarImage;
    private Image loadingTextImage;

    public LoadingView(final LoadingController lc, final NetworkController nc){

        this.loadingController = lc;
        this.networkController = nc;
        this.menuBtn = new MenuBtn();
        this.loadingBar = new Texture("loadingBar.png");
        this.loadingText = new Texture("loading.png");
        this.loadingBarImage = new Image(loadingBar);
        this.loadingTextImage = new Image(loadingText);
        this.stage = new Stage(new ScreenViewport());

        int btnHeight = Gdx.graphics.getHeight() / 10;
        int btnWidth = btnHeight * 2;

        menuBtn.getMenuBtn().setSize(btnWidth, btnHeight);

        menuBtn.getMenuBtn().setPosition(
                (float)btnWidth/4,
                Gdx.graphics.getHeight() - (float)btnHeight/4,
                Align.topLeft);


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


    /**
     * Listeners for touch gestures and checkbox to notice input from the user
     */
    @Override
    public void startListeners() {
        menuBtn.getMenuBtn().clearListeners();

        Gdx.input.setInputProcessor(stage);
        stage.addActor(loadingBarImage);
        stage.addActor(loadingTextImage);
        stage.addActor(menuBtn.getMenuBtn());
        menuBtn.getMenuBtn().addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("menuBtn is touched.");
                loadingController.quitGame();
                networkController.disconnect();
                dispose();
            }
        });

    }

    @Override
    protected void handleInput() {

    }

    private void startGame() {
        loadingController.startGame();
    }

    @Override
    public void update(float dt) {
        if (Settings.getInstance().getMutiplayerReady()){
            startGame();
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
        stage.dispose();
        menuBtn.dispose();
        System.out.println("Loading View Disposed");
    }
}
