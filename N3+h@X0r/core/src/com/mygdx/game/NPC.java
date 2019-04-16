package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class NPC extends Sprite{
    private TiledMapTileLayer collisionLayer;
    private String[] dialogue;
    private char[] type;
    private int diagSoFar;

    NPC(Sprite sprite, TiledMapTileLayer collisionLayer, String[] dialogue, char[] type){
        super(sprite);
        this.collisionLayer = collisionLayer;
        this.dialogue = dialogue;
        this.type = type;
        diagSoFar = 0;
    }

    public void draw(SpriteBatch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    void update(float delta){
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    public String getDialogue(int num) {
        return dialogue[num];
    }

    public void setDialogue(String[] dialogue) {
        this.dialogue = dialogue;
    }

    public int getDiagSoFar(){return diagSoFar;}
    public void setdiagSoFar()
    {
        if(diagSoFar == (dialogue.length - 1))
        {
            diagSoFar = 0;
        }
        else
        {
            diagSoFar++;
        }
    }
    public char getDiagType()
    {
        return type[diagSoFar];
    }
}
