package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ImpossibleGravity;
import com.mygdx.game.controller.SettingsController;
import com.mygdx.game.interactiveElements.MenuBtn;

public class SettingsView extends SuperView{
    protected SettingsController settingsController;
    private Texture background;
    private Stage stage;
    private MenuBtn menuBtn;

    // These are only used in this class, so we can implement the functionality for these in only this class.
    private Texture volumeBtn;
    private Texture toggleBtn;

    //Constructor
    public SettingsView(SettingsController settingsController) {
        this.settingsController = settingsController;
        // GameInstance is the equivalent to the FlappyDemo in the tutorial.
        camera.setToOrtho(false, ImpossibleGravity.WIDTH / 2, ImpossibleGravity.HEIGHT / 2);

        this.menuBtn = new MenuBtn();

        // Setting up the stage, adding the actors (buttons)
        stage = new Stage(new ScreenViewport());
        stage.addActor(menuBtn);
        Gdx.input.setInputProcessor(stage);

        // Position the buttons
        menuBtn.setPosition(camera.position.x - menuBtn.getWidth() / 2, camera.position.y);

        // Add specific settings
        volumeBtn = new Texture("volumeBtn.png"); // Should be a slider
        toggleBtn = new Texture("toggleBtn.png");

        background = new Texture("bg.png");

    }


    @Override
    public void handleInput() {
        menuBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("MenuBtn is pressed.");
                settingsController.BackToMenu();
            }
        });

        // When we know how to implement these buttons/sliders, we activate these functions.
        /*volumeBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("MenuBtn is pressed.");
                settingsController.ChangeSoundVolume();
            }
        });
        toggleBtn.addListener(new ClickListener() {
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("MenuBtn is pressed.");
                settingsController.toggleSound();
            }
        });*/
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    // Draws background, the menu button
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0,0);
        // May have to remove the following two lines when the sliders are implemented.
        sb.draw(volumeBtn, camera.position.x - volumeBtn.getWidth() / 2, camera.position.y+20);
        sb.draw(toggleBtn, camera.position.x - toggleBtn.getWidth() / 2, camera.position.y+40);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        toggleBtn.dispose();
        volumeBtn.dispose();
        System.out.println("Settings View Disposed");
    }

}
