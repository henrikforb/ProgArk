package com.mygdx.game.view;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.utils.ActorGestureListener;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.viewport.ScreenViewport;
import com.mygdx.game.ImpossibleGravity;
import com.mygdx.game.controller.HighScoreController;
import com.mygdx.game.interactiveElements.MenuBtn;
import com.mygdx.game.model.HighScore;

import java.text.DecimalFormat;
import java.util.ArrayList;

// Can develop this one later, but a low priority as of now.
public class HighscoreView extends SuperView {

    private HighScoreController highScoreController;
    private MenuBtn menuBtn;
    private Stage stage;
    private Rectangle rectangle;
    private ShapeRenderer sr = new ShapeRenderer();
    private HighScore highScore;
    private ArrayList<Double> highScores;


    public HighscoreView(HighScoreController highScoreController){

        this.highScoreController = highScoreController;
        this.highScore = new HighScore();
        this.menuBtn = new MenuBtn();
        this.highScores = highScore.loadHighScoreFromFile();

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
        //highScoreController.getFont().draw(sb, highScoreController.getFont(), camera.position.x+(ImpossibleGravity.WIDTH/3), ImpossibleGravity.HEIGHT-30);
        //sb.draw(helpTexture, ImpossibleGravity.WIDTH / 2 - helpTexture.getWidth() / 2, ImpossibleGravity.HEIGHT / 2 - helpTexture.getHeight() / 3);
        // sb.draw(helpTexture, Align.center,ImpossibleGravity.HEIGHT, 200, 135);
        sb.end();
        stage.act();
        stage.draw();
        drawHighScoreSquare();
        writeToScreen(sb);

    }

    @Override
    public void dispose() {
        background.dispose();
        System.out.println("HighScore View Disposed");
        menuBtn.dispose();
    }

    @Override
    public void show() {

    }

    @Override
    public void startListeners() {
        menuBtn.getMenuBtn().clearListeners();

        Gdx.input.setInputProcessor(stage);
        stage.addActor(menuBtn.getMenuBtn());

        // LISTENERS FOR CLICK GESTURES
        menuBtn.getMenuBtn().addListener(new ActorGestureListener(){
            @Override
            public void touchDown(InputEvent event, float x, float y, int pointer, int button) {
                System.out.println("menuBtn is clicked");
                highScoreController.backToMenu();
            }
        });

        // LISTENERS FOR TAP GESTURES
        menuBtn.getMenuBtn().addListener(new ActorGestureListener() {
            @Override
            public void tap(InputEvent event, float x, float y, int count, int button) {
                System.out.println("menuBtn is touched.");
                highScoreController.backToMenu();
            }
        });


    }

    private void drawHighScoreSquare(){
        float rectStartX = ImpossibleGravity.WIDTH/2 - ImpossibleGravity.WIDTH/6;
        float rectStartY = 150;
        float rectWidth = ImpossibleGravity.WIDTH/3;
        float rectHeigth = ImpossibleGravity.HEIGHT*5/8;
        sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.valueOf("#fff3e0"));


        sr.rect(rectStartX, rectStartY, rectWidth, rectHeigth);
        //sr.begin(ShapeRenderer.ShapeType.Filled);
        sr.setColor(Color.DARK_GRAY);
        sr.rect(rectStartX+25, rectStartY+25, rectWidth-50, rectHeigth-50);
        sr.end();
    }

    private void writeToScreen(SpriteBatch sb){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Retro Gaming.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Gdx.graphics.getHeight() / 30;
        parameter.color = Color.valueOf("#ff6f00"); // can be changed to orange to match buttons
        BitmapFont fontScores = generator.generateFont(parameter);

        DecimalFormat numberFormat = new DecimalFormat("##0.00");


        sb.begin();
        //highScoreController.getFont().setColor(Color.FIREBRICK);
        highScoreController.getFont().draw(sb, "HIGHSCORES", (ImpossibleGravity.WIDTH)/2-100, ImpossibleGravity.HEIGHT*6/7);


        int j = 9;

        for (int i = 0; i < 5; i++){

            fontScores.draw(sb, "" + (i+1) + ".", (ImpossibleGravity.WIDTH)/2-95, ImpossibleGravity.HEIGHT*j/12);
            fontScores.draw(sb, numberFormat.format(this.highScores.get(i)) + " points", (ImpossibleGravity.WIDTH)/2-50, ImpossibleGravity.HEIGHT*j/12);
            j--;
        }
        sb.end();


    }
}
