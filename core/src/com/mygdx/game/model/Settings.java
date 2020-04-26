package com.mygdx.game.model;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;

import io.socket.client.Socket;

/**
 * Logic for all settings needed: Music enable and volume
 */
public final class Settings {

    private static final Settings INSTANCE = new Settings();

    public static Settings getInstance(){
        return INSTANCE;
    }

    private boolean enableGameMusic = true;
    private BitmapFont font;
    private boolean startMultiplayer = false;
    private String id = "";
    private Socket socket;

    private Settings(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Retro Gaming.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Gdx.graphics.getHeight() / 18;
        parameter.color = Color.BLACK;
        this.font = generator.generateFont(parameter);
        generator.dispose();
    }

    public void setSoundVolume(float volume){}

    public void toggleGameMusic(){
        this.enableGameMusic = !this.enableGameMusic;
    }

    public boolean gameMusicIsEnabled(){
        return enableGameMusic;
    }

    public BitmapFont getFont(){
        return this.font;
    }

    public void enableMultiplayer(String id, boolean x){
        this.id = id;
        this.startMultiplayer = x;
    }

    public boolean getStartMultiplayer(){
        return this.startMultiplayer;
    }

    public String getId(){
        return this.id;
    }

    public void setSocket(Socket socket){
        this.socket = socket;
    }

    public Socket getSocket(){
        return this.socket;
    }

}
