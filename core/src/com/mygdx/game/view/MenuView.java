package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ImpossibleGravity;
import com.mygdx.game.controller.MenuController;
import com.mygdx.game.interactiveElements.HelpBtn;
import com.mygdx.game.interactiveElements.HighscoreBtn;
import com.mygdx.game.interactiveElements.PlayBtn;
import com.mygdx.game.interactiveElements.QuitBtn;
import com.mygdx.game.interactiveElements.SettingsBtn;
import com.mygdx.game.model.Settings;

/**
 * View for menu screen with different buttons to choose action
 */
public class MenuView extends SuperView{

    protected MenuController menuController;
    private Stage stage;

    private PlayBtn playBtn;
    private QuitBtn quitBtn;
    private SettingsBtn settingsBtn;
    private HelpBtn helpBtn;
    private HighscoreBtn highscoreBtn;

    private TextureRegionDrawable checked;
    private TextureRegionDrawable unchecked;
    private Settings settings;

    public MenuView(final MenuController menuController) {

        this.menuController = menuController;
        this.playBtn = new PlayBtn();
        this.settingsBtn = new SettingsBtn();
        this.helpBtn = new HelpBtn();
        this.quitBtn = new QuitBtn();
        this.highscoreBtn = new HighscoreBtn();
        this.settings = Settings.getInstance();

        int btnHeight = Gdx.graphics.getHeight() / 7;
        int btnWidth = btnHeight * 2;

        CheckBox.CheckBoxStyle checkBoxStyle = new CheckBox.CheckBoxStyle();
        this.checked = new TextureRegionDrawable(new TextureRegion(new Texture("checked.png")));
        this.unchecked = new TextureRegionDrawable(new TextureRegion(new Texture("unchecked.png")));
        checkBoxStyle.checkboxOn = checked;
        checkBoxStyle.checkboxOff = unchecked;
        checkBoxStyle.font = settings.getFont();

        playBtn.getPlayBtn().setSize(btnWidth, btnHeight);
        settingsBtn.getSettingsBtn().setSize(btnWidth, btnHeight);
        helpBtn.getHelpBtn().setSize(btnWidth, btnHeight);
        quitBtn.getQuitBtn().setSize(btnWidth, btnHeight);
        highscoreBtn.getHighScoreBtn().setSize(btnWidth, btnHeight);
        playBtn.getPlayBtn().setPosition((float)Gdx.graphics.getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 6 * 5, Align.center);
        settingsBtn.getSettingsBtn().setPosition((float)Gdx.graphics.getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 6 * 4, Align.center);
        helpBtn.getHelpBtn().setPosition((float)Gdx.graphics.getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 6 * 3, Align.center);
        quitBtn.getQuitBtn().setPosition((float)Gdx.graphics.getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 6 * 1, Align.center);
        highscoreBtn.getHighScoreBtn().setPosition((float)Gdx.graphics.getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 6 * 2, Align.center);

        this.stage = new Stage(new ScreenViewport());
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

        playBtn.getPlayBtn().clearListeners();
        settingsBtn.getSettingsBtn().clearListeners();
        helpBtn.getHelpBtn().clearListeners();
        quitBtn.getQuitBtn().clearListeners();
        highscoreBtn.getHighScoreBtn().clearListeners();

        Gdx.input.setInputProcessor(this.stage);
        stage.addActor(playBtn.getPlayBtn());
        stage.addActor(settingsBtn.getSettingsBtn());
        stage.addActor(helpBtn.getHelpBtn());
        stage.addActor(quitBtn.getQuitBtn());
        stage.addActor(highscoreBtn.getHighScoreBtn());

        // LISTENERS FOR CLICK GESTURES

        /*playBtn.getPlayBtn().addListener(new ActorGestureListener(){
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("playBtn is clicked.");
                menuController.playGamePressed();
            }
        });

        settingsBtn.getSettingsBtn().addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("settingsBtn is clicked.");
                menuController.settingsPressed();
            }
        });

        helpBtn.getHelpBtn().addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("helpBtn is clicked.");
                menuController.helpPressed();

            }
        });

        quitBtn.getQuitBtn().addListener(new ActorGestureListener() {
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("quitBtn is clicked");
                menuController.quit();
            }
        });

        highscoreBtn.getHighScoreBtn().addListener(new ActorGestureListener(){
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button){
                System.out.println("highScoreBtn is clicked.");
                menuController.highscorePressed();
            }
        });*/

        // LISTENERS FOR TOUCH GESTURES

        playBtn.getPlayBtn().addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("playBtn is touched.");
                menuController.playGamePressed();
                dispose();
            }
        });

        settingsBtn.getSettingsBtn().addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("settingsBtn is touched.");
                menuController.settingsPressed();
            }
        });

        helpBtn.getHelpBtn().addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("helpBtn is touched.");
                menuController.helpPressed();
            }
        });

        quitBtn.getQuitBtn().addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("quitBtn is touched.");
                menuController.quit();
            }
        });

        highscoreBtn.getHighScoreBtn().addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("highscoreBtn is touched.");
                menuController.highscorePressed();
            }
        });
    }

    @Override
    public void handleInput() {
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
        playBtn.dispose();
        quitBtn.dispose();
        settingsBtn.dispose();
        helpBtn.dispose();
        highscoreBtn.dispose();
        stage.dispose();
        background.dispose();
        System.out.println("Menu View Disposed");
    }
}
