package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ImpossibleGravity;
import com.mygdx.game.controller.GameOverController;
import com.mygdx.game.interactiveElements.MenuBtn;

/**
 * View for game over screen shown when a player loses (hits an obstacle)
 */
public class GameOverView extends SuperView{
    protected GameOverController gameOverController;
    private Stage stage;
    private MenuBtn menuBtn;
    private Texture gameOver;
    private Image gameOverImage;

    public GameOverView(final GameOverController gameOverController) {
        this.gameOverController = gameOverController;
        this.menuBtn = new MenuBtn();

        gameOver = new Texture("gameOver.png");
        gameOverImage = new Image(gameOver);

        int btnHeight = Gdx.graphics.getHeight() / 6;
        int btnWidth = btnHeight*2;

        gameOverImage.setSize((float)Gdx.graphics.getWidth() / 10 * 7, (float)Gdx.graphics.getHeight() / 2);
        menuBtn.getMenuBtn().setSize(btnWidth, btnHeight);

        gameOverImage.setPosition((float)Gdx.graphics.getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 5 * 3, Align.center);
        menuBtn.getMenuBtn().setPosition((float)Gdx.graphics.getWidth() / 2,
                (float)Gdx.graphics.getHeight() / 5 * 1, Align.center);

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
        stage.addActor(gameOverImage);

        menuBtn.getMenuBtn().addListener(new ActorGestureListener(){
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("menuBtn is clicked");
                gameOverController.BackToMenu();
                dispose();
            }
        });

        /**
         * Listeners for touch gestures to make the game work on touch screen
         */
        menuBtn.getMenuBtn().addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("menuBtn is touched.");
                gameOverController.BackToMenu();
                dispose();
            }
        });
    }

    @Override
    public void handleInput() {
    }

    @Override
    public void update(float dt) {
        background.update(dt);

    }

    @Override
    // Draws background, and the "Back to menu"-button.
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background.getBackground(), 0, 0, ImpossibleGravity.WIDTH, ImpossibleGravity.HEIGHT);
        //sb.draw(gameOver, camera.position.x - gameOver.getWidth() / 2, camera.position.y+200);
        //sb.draw(gameOver, 50,50);
        sb.end();
        stage.draw();
        stage.act();
    }

    @Override
    public void dispose() {
        System.out.println("Game Over View Disposed");
        menuBtn.dispose();
    }
}
