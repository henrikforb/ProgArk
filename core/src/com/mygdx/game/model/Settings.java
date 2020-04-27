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
    public boolean multiplayer = false;
    private BitmapFont font;
    private boolean mutiplayerReady = false;
    private String id = "";

    private Settings(){
        FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("Retro Gaming.ttf"));
        FreeTypeFontGenerator.FreeTypeFontParameter parameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
        parameter.size = Gdx.graphics.getHeight() / 18;
        parameter.color = Color.valueOf("#ff6f00"); // can be changed to orange to match buttons
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

    public void multiplayerReady(String id){
        this.id = id;
        this.mutiplayerReady = true;
        System.out.println("Multiplayer is set to ready");
    }

    public boolean getMutiplayerReady(){
        return this.mutiplayerReady;
    }

    public String getId(){
        return this.id;
    }

    public void setMultiplayerNotReady() {
        this.mutiplayerReady = false;
        System.out.println("Multiplayer is set to NOT ready");
    }

    public void toggleMultiplayer(){
        this.multiplayer = !this.multiplayer;
    }

    public boolean multiplayerChecked() {
        return multiplayer;
    }

}
