package com.mygdx.game;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import io.socket.client.IO;
import io.socket.client.Socket;
import io.socket.emitter.Emitter;

import com.mygdx.game.controller.MenuController;
import com.mygdx.game.controller.ViewController;
import com.mygdx.game.view.MenuView;


/**
 * Main class to run the application
 * Defines the dimensions of the game along with the game name "Impossible Gravity"
 * Creates a ViewController as the supreme controller class and a new MenuView as first screen shown to user
 */

public class ImpossibleGravity extends ApplicationAdapter {

	private SpriteBatch batch;

	public static final int WIDTH = 854;
	public static final int HEIGHT = 480;
	public static final String TITLE = "Impossible Gravity";

	public static final float GRAVITY = -1;

	private ViewController vc;

	@Override
	public void create () {
		batch = new SpriteBatch();
		vc = new ViewController();
		Gdx.gl.glClearColor(1, 0, 0, 1);

		vc.push(new MenuView(new MenuController(vc)));

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
