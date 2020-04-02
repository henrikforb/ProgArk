package com.mygdx.game.view;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.ImpossibleGravity;
import com.mygdx.game.controller.LoadingController;
// LoadingScreen can be displayed when a while-loop is true in another controller.

public class LoadingScreen extends SuperView{

    protected LoadingController loadingController;
    private Texture loadingTexture;
    private Texture background;

    // Constructor
    public LoadingScreen(LoadingController loadingController){

        this.loadingController = loadingController;

        // GameInstance is the equivalent to the FlappyDemo in the tutorial.
        camera.setToOrtho(false, ImpossibleGravity.WIDTH / 2, ImpossibleGravity.HEIGHT / 2);

        background = new Texture("bg.png");
        loadingTexture = new Texture("loadingTexture.png");
    }

    @Override
    protected void handleInput() {

    }

    @Override
    public void update(float dt) {
        handleInput();
    }

    @Override
    // Draws background, the loading texture.
    public void render(SpriteBatch sb) {
        sb.setProjectionMatrix(camera.combined);
        sb.begin();
        sb.draw(background, 0,0);
        sb.draw(loadingTexture, camera.position.x - loadingTexture.getWidth() / 2, camera.position.y);
        sb.end();
    }

    @Override
    public void dispose() {
        background.dispose();
        loadingTexture.dispose();
        System.out.println("Loading View Disposed");
    }
}
