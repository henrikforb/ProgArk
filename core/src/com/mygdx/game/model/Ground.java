package com.mygdx.game.model;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.mygdx.game.ImpossibleGravity;

import javax.xml.soap.Text;

public class Ground {

    private Texture ground;
    private Vector3 groundPos1, groundPos2;
    private float groundHeight;
    //public static final int GROUND_Y_OFFSET = -50;

    public Ground(){ // ta inn cam.pos osv i playView
        ground = new Texture("ground.png");
        groundPos1 = new Vector3(0, 0, 0);
        groundPos2 = new Vector3(0 + ground.getWidth(), 0, 0);
        groundHeight = new Float(ground.getHeight());
    }

    //public Ground(Vector3 groundPos) {
        //ground = new Texture("ground.png");
        //this.groundPos = groundPos;
    //}

    public Texture getGround(){
        return ground;
    }

    //public void setGround(Texture ground) {
        //ground = ground;
    //}

    public Vector3 getGroundPos1(){
        return groundPos1;
    }

    public Vector3 getGroundPos2(){
        return groundPos2;
    }

    public float getGroundHeight() {
        return groundHeight;
    }

    public void update(float dt){
    }

    public void dispose() {
        ground.dispose();
    }
}
