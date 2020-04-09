package com.mygdx.game.model;

import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;

public class World {

    private Texture background;
    private Texture ground;
    private Vector2 groundPos1, groundPos2;

    private Music music;
    private static float VOLUME;

    //TODO make background depended on input to variate between different backgrounds/modes
    private static int BG_MODE;

    public World() {
        background = new Texture("background.png"); //locally saved
        ground = new Texture("ground.png"); //locally saved

        /* Trenger en musikkfil for at dette skal kunne brukes
        music = Gdx.audio.newMusic("musikkfil.mp3");
        music.setLooping(true);
        music.setVolume(1F);
        music.play();
         */
    }

    public Texture getBackground() {
        return background;
    }
    /* Might need this to select different backgrounds
    public void setBackground(Texture background) {
        this.background = background;
    }

     */

    public Texture getGround() {
        return ground;
    }

    /* Might need this to select different backgrounds
    public void setGround(Texture ground) {
        this.ground = ground;
    }

     */

    public Vector2 getGroundPos1() {
        return groundPos1;
    }

    public Vector2 getGroundPos2() {
        return groundPos2;
    }

    public void setGroundPos1(Vector2 groundPos1) {
        this.groundPos1 = groundPos1;
    }

    public void setGroundPos2(Vector2 groundPos2) {
        this.groundPos2 = groundPos2;
    }


    public void dispose() {
        this.background.dispose();
        this.ground.dispose();
    }
}
>>>>>>> dev
