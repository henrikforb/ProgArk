package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.game.controller.ViewController;
import com.mygdx.game.model.Player;
import com.mygdx.game.view.PlayView;

public class ImpossibleGravity extends ApplicationAdapter {

	private SpriteBatch batch;

	public static final int WIDTH = 480;
	public static final int HEIGHT = 480;
	public static final String TITLE = "Impossible Gravity";

	public static final int GRAVITY = 5;

	private ViewController vc;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		vc = new ViewController();
		Gdx.gl.glClearColor(1, 0, 0, 1);
		vc.push(new PlayView(vc));
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
