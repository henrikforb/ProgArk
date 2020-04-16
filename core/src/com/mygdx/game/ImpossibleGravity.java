package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.MenuController;
import com.mygdx.game.controller.ViewController;
import com.mygdx.game.model.Player;
import com.mygdx.game.view.MenuView;
import com.mygdx.game.view.PlayView;

public class ImpossibleGravity extends ApplicationAdapter {

	private SpriteBatch batch;

	public static final int WIDTH = 480; //width of the screen
	public static final int HEIGHT = 480; //height of the screen
	public static final String TITLE = "Impossible Gravity";

	public static final float GRAVITY = -1;

	private ViewController vc;
	private MenuController mc;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		vc = new ViewController();
		mc = new MenuController(vc);
		Gdx.gl.glClearColor(1, 0, 0, 1);

		vc.push(new PlayView(vc));
		vc.push(new MenuView(mc));
	}

	@Override
	public void render () {
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		vc.update(Gdx.graphics.getDeltaTime());
		vc.render(batch);
	}
	
	@Override
	public void dispose () {
		batch.dispose();
	}
}
