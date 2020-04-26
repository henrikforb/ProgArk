package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ImpossibleGravity;
import com.mygdx.game.controller.StartController;
import com.mygdx.game.interactiveElements.MultiplayerBtn;
import com.mygdx.game.interactiveElements.SingleplayerBtn;


/**
 * View for a start screen shown before the menu screen
 * Not used as of now. Logic for an alternative menu-solution that was changed
 */

public class StartView extends SuperView {

    protected StartController startController;
    private Stage stage;

    private SingleplayerBtn singleplayerBtn;
    private MultiplayerBtn multiplayerBtn;

    public StartView(StartController startController) {

        this.startController = startController;
        this.singleplayerBtn = new SingleplayerBtn();
        this.multiplayerBtn = new MultiplayerBtn();

        singleplayerBtn.getSingleplayerBtn().setPosition((float)Gdx.graphics.getWidth() / 2,
                (float)Gdx.graphics.getHeight() /5 * 3, Align.center);
        multiplayerBtn.getMultiplayerBtn().setPosition((float)Gdx.graphics.getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 5 * 2, Align.center);

        this.stage = new Stage(new ScreenViewport());
        startListeners();

    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        show();
        background.update(dt);

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
        stage.dispose();
        background.dispose();
        multiplayerBtn.dispose();
        singleplayerBtn.dispose();
        System.out.println("Start View Disposed");

    }

    @Override
    public void show() {

    }


    /**
     * Listeners for touch gestures to notice input from the user
     */

    @Override
    public void startListeners() {

        singleplayerBtn.getSingleplayerBtn().clearListeners();
        multiplayerBtn.getMultiplayerBtn().clearListeners();

        Gdx.input.setInputProcessor(this.stage);
        stage.addActor(singleplayerBtn.getSingleplayerBtn());
        stage.addActor(multiplayerBtn.getMultiplayerBtn());


        singleplayerBtn.getSingleplayerBtn().addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("singleplayerBtn is touched.");
                startController.singlePlayerPressed();
                dispose();

            }
        });

        multiplayerBtn.getMultiplayerBtn().addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("multiplayerBtn is touched.");
                startController.multiPlayerPressed();
                dispose();
            }
        });

    }
}
