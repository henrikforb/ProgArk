package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ImpossibleGravity;
import com.mygdx.game.controller.MenuController;
import com.mygdx.game.interactiveElements.HelpBtn;
import com.mygdx.game.interactiveElements.PlayBtn;
import com.mygdx.game.interactiveElements.QuitBtn;
import com.mygdx.game.interactiveElements.SettingsBtn;
// Here we need the import of the game instance!

public class MenuView extends SuperView{

    protected MenuController menuController;
    private Stage stage;
    private Texture background;

    // Import the necessary buttons for this view
    private PlayBtn playBtn;
    private QuitBtn quitBtn;
    private SettingsBtn settingsBtn;
    private HelpBtn helpBtn;

    //Constructor
    public MenuView(final MenuController menuController) {
        this.menuController = menuController;
        this.playBtn = new PlayBtn();
        this.quitBtn = new QuitBtn();
        this.settingsBtn = new SettingsBtn();
        this.helpBtn = new HelpBtn();

        // Setting up the stage, adding the actors (buttons)
        stage = new Stage(new ScreenViewport());
        stage.addActor(playBtn);
        stage.addActor(quitBtn);
        stage.addActor(settingsBtn);
        stage.addActor(helpBtn);
        Gdx.input.setInputProcessor(stage);

        // Position the buttons
        playBtn.setPosition(camera.position.x - playBtn.getWidth() / 2, camera.position.y);
        quitBtn.setPosition(camera.position.x - quitBtn.getWidth() / 2, camera.position.y+20);
        settingsBtn.setPosition(camera.position.x - settingsBtn.getWidth() / 2, camera.position.y+40);
        helpBtn.setPosition(camera.position.x - helpBtn.getWidth() / 2, camera.position.y+60);

        // GameInstance is the equivalent to the FlappyDemo in the tutorial.
        camera.setToOrtho(false, ImpossibleGravity.WIDTH / 2, ImpossibleGravity.HEIGHT / 2);

        // Change to suitable background later (and put background in asset-folder!!!)
        background = new Texture("bg.png");
    }

    @Override
    public void handleInput() {
        // Add listeners to buttons
        // May have to move the listeners outside of a handleInput()-method.
        // Double clicks can be detected using getTapCount()playBtn.addListener(new ClickListener(){

        playBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("PlayBtn is pressed.");
                menuController.playGamePressed();
            }
        });

        quitBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("quitBtn is pressed.");
                menuController.quit();
            }
        });

        settingsBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("settingsBtn is pressed.");
                menuController.settingsPressed();
            }
        });

        helpBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("helpBtn is pressed.");
                // menuController.helpPressed();
            }
        });
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    // Draws background, the play button
    // Should add all the menu options later, but at least a "PLAY" button or something.
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0,0);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        System.out.println("Menu View Disposed");
    }
}
