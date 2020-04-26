package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.TextureRegionDrawable;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ImpossibleGravity;
import com.mygdx.game.controller.HelpController;
import com.mygdx.game.interactiveElements.MenuBtn;
import com.mygdx.game.interactiveElements.Text;

import java.awt.Label;

/**
 * View for help screen shown when "help" is clocked from menu
 */
public class HelpView extends SuperView{

    protected HelpController helpController;
    private Stage stage;
    private MenuBtn menuBtn;
    //private Text helpText;
    private Texture helpTexture;


    public HelpView(final HelpController helpController) {
        this.helpController = helpController;
        this.menuBtn = new MenuBtn();
        //this.helpText = new Text();
        this.helpTexture = new Texture(Gdx.files.internal("helpText.png"));

        int btnHeight = Gdx.graphics.getHeight() / 6;
        int btnWidth = btnHeight*2;

        menuBtn.getMenuBtn().setSize(btnWidth, btnHeight);

        //helpText.getHelpText().setPosition((Gdx.graphics.getWidth()/ - helpText.getHelpText().getWidth()), Gdx.graphics.getHeight() - helpText.getHelpText().getHeight());
        menuBtn.getMenuBtn().setPosition((float)Gdx.graphics.getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 5 * 1, Align.center);

        //stage.addActor(helpText.getHelpText());
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

        menuBtn.getMenuBtn().addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("menuBtn is touched.");
                helpController.backToMenu();
                dispose();
            }
        });
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
        sb.draw(background.getBackground(), 0, 0, ImpossibleGravity.WIDTH, ImpossibleGravity.HEIGHT);
        sb.draw(helpTexture, ImpossibleGravity.WIDTH / 2 - helpTexture.getWidth() / 2, ImpossibleGravity.HEIGHT / 2 - helpTexture.getHeight() / 3);
        // sb.draw(helpTexture, Align.center,ImpossibleGravity.HEIGHT, 200, 135);
        sb.end();
        stage.act();
        stage.draw();
    }

    @Override
    public void dispose() {
        background.dispose();
        System.out.println("Help View Disposed");
        menuBtn.dispose();
        helpTexture.dispose();

    }

}
