package com.mygdx.game.view;

// Not planned in the view-section in architecture, but I made it læll since pausing the game
// is a functional requirement. Remember to add to the views in the document.

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ImpossibleGravity;
import com.mygdx.game.controller.PauseController;
import com.mygdx.game.interactiveElements.MenuBtn;
import com.mygdx.game.interactiveElements.PlayBtn;
// Here we need the import of the game instance!

public class PauseView extends SuperView{

    protected PauseController pauseController;
    private Stage stage;
    private Texture background;

    private PlayBtn playBtn;
    private MenuBtn menuBtn;

    // Constructor
    public PauseView(PauseController pauseController) {
        this.pauseController = pauseController;
        camera.setToOrtho(false, ImpossibleGravity.WIDTH / 2, ImpossibleGravity.HEIGHT / 2);

        this.playBtn = new PlayBtn();
        this.menuBtn = new MenuBtn();

        // Setting up the stage, adding the actors (buttons)
        stage = new Stage(new ScreenViewport());
        stage.addActor(playBtn);
        stage.addActor(menuBtn);
        Gdx.input.setInputProcessor(stage);

        // Position the buttons
        playBtn.setPosition(camera.position.x - playBtn.getWidth() / 2, camera.position.y);
        menuBtn.setPosition(camera.position.x - menuBtn.getWidth() / 2, camera.position.y+20);

        background = new Texture("bg.png");

    }

    @Override
    public void handleInput() {

        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("PlayBtn is pressed.");
                pauseController.ContinueGame();
            }
        });

        menuBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("MenuBtn is pressed.");
                pauseController.BackToMenu();
            }
        });
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    // Draws background, and the "play"-button.
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0,0);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        System.out.println("Pause View Disposed");
    }
}