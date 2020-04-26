package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox;
import com.badlogic.gdx.scenes.scene2d.ui.CheckBox.CheckBoxStyle;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Scaling;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ImpossibleGravity;
import com.mygdx.game.controller.SettingsController;
import com.mygdx.game.interactiveElements.MenuBtn;
import com.mygdx.game.model.Settings;

/**
 * View for settings screen when "settings" is pushed in menu
 */
public class SettingsView extends SuperView{
    protected SettingsController settingsController;

    private Stage stage;
    private MenuBtn menuBtn;
    private CheckBox checkBox;
    private TextureRegionDrawable checked;
    private TextureRegionDrawable unchecked;
    private Settings model;

    public SettingsView(final SettingsController settingsController) {

        this.settingsController = settingsController;
        this.menuBtn = new MenuBtn();
        this.model = settingsController.getModel();

        CheckBoxStyle checkBoxStyle = new CheckBoxStyle();
        this.checked = new TextureRegionDrawable(new TextureRegion(new Texture("checked.png")));
        this.unchecked = new TextureRegionDrawable(new TextureRegion(new Texture("unchecked.png")));
        checkBoxStyle.checkboxOn = checked;
        checkBoxStyle.checkboxOff = unchecked;
        checkBoxStyle.font = model.getFont();
        this.checkBox = new CheckBox(" GAME MUSIC", checkBoxStyle);
        this.checkBox.setChecked(model.gameMusicIsEnabled());

        int btnHeight = Gdx.graphics.getHeight() / 6;
        int btnWidth = btnHeight*2;
        int checkBoxSize = Gdx.graphics.getHeight() / 10;

        menuBtn.getMenuBtn().setSize(btnWidth, btnHeight);
        checkBox.getImage().setScaling(Scaling.fill);
        checkBox.getImageCell().size(checkBoxSize);

        menuBtn.getMenuBtn().setPosition((float)Gdx.graphics.getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 5 * 1, Align.center);
        checkBox.setPosition((float)Gdx.graphics.getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 5 * 3, Align.center);

        stage = new Stage(new ScreenViewport());
        startListeners();
    }

    @Override
    public void show(){

    }

    /**
     * Listeners for touch gestures to notice input from the user
     */
    @Override
    public void startListeners() {

        menuBtn.getMenuBtn().clearListeners();

        Gdx.input.setInputProcessor(stage);
        stage.addActor(menuBtn.getMenuBtn());
        stage.addActor(checkBox);

        menuBtn.getMenuBtn().addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("menuBtn is touched.");
                settingsController.backToMenu();
                dispose();
            }
        });

        checkBox.addListener(new ChangeListener() {
            public void changed (ChangeEvent event, Actor actor) {
                settingsController.toggleGameMusic();
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
    // Draws background, the menu button
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background.getBackground(), 0, 0, ImpossibleGravity.WIDTH, ImpossibleGravity.HEIGHT);
        sb.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        menuBtn.dispose();
        System.out.println("Settings View Disposed");
    }

}