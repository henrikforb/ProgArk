package com.mygdx.game.view;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ImpossibleGravity;
import com.mygdx.game.controller.GameOverController;
import com.mygdx.game.interactiveElements.MenuBtn;
// Here we need the game instance import!

public class GameOverScreen extends SuperView{
    protected GameOverController gameOverController;
    private Stage stage;
    private MenuBtn menuBtn;

    // Make a simple background and a game over-logo and put it in the assets folder!
    private Texture background;
    private Texture gameOver; // NB: Not a button, just text on screen

    // Constructor
    public GameOverScreen(GameOverController gameOverController) {
        this.gameOverController = gameOverController;
        this.menuBtn = new MenuBtn();

        camera.setToOrtho(false, ImpossibleGravity.WIDTH / 2, ImpossibleGravity.HEIGHT / 2);
        background = new Texture("bg.png");
        gameOver = new Texture("gameOver.png");

        // Setting up the stage, adding the actors (buttons)
        stage = new Stage(new ScreenViewport());
        stage.addActor(menuBtn);
        Gdx.input.setInputProcessor(stage);

        // Position the button
        menuBtn.setPosition(camera.position.x - menuBtn.getWidth() / 2, camera.position.y);
    }

    @Override
    public void handleInput() {
        menuBtn.addListener(new ClickListener() {
            @Override
            public void clicked(InputEvent event, float x, float y) {
                System.out.println("MenuBtn is pressed.");
                gameOverController.BackToMenu();
            }
        });
    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    // Draws background, and the "Back to menu"-button.
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0,0);
        sb.draw(gameOver, camera.position.x - gameOver.getWidth() / 2, camera.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        gameOver.dispose();
        System.out.println("Game Over View Disposed");
    }
}
