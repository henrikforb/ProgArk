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
/**
 * View for loanding screen while waiting to connect with another player in multiplayer mode
 * LoadingScreen can be displayed when a while-loop is true in another controller
 */
public class LoadingView extends SuperView{

    protected LoadingController loadingController;
    private Stage stage;
    private Texture loadingBar;
    private Image loadingBarImage;

    public LoadingView(final LoadingController loadingController){

        this.loadingController = loadingController;
        this.loadingBar = new Texture("loadingBar.png");
        this.loadingBarImage = new Image(loadingBar);
        this.stage = new Stage(new ScreenViewport());

        loadingBarImage.setSize((float) Gdx.graphics.getWidth() / 10 * 7, (float)Gdx.graphics.getHeight() / 2);
        loadingBarImage.setPosition((float)Gdx.graphics.getWidth() / 2, (float)Gdx.graphics.getHeight() / 5 * 3, Align.center);
        startListeners();
    }

    @Override
    public void show(){

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
