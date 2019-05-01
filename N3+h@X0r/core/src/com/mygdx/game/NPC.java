package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class NPC extends Sprite{
    private TiledMapTileLayer collisionLayer;
    private String[] dialogue;
    private char[] type;
    private String[] answer, ip, mask;
    private int diagSoFar, ipSoFar, maskSoFar;
    private int answerIndex = 0;

    NPC(Sprite sprite, TiledMapTileLayer collisionLayer, String[] dialogue, char[] type, String[] answer, String[] ip, String[] mask){
        super(sprite);
        this.collisionLayer = collisionLayer;
        this.dialogue = dialogue;
        this.type = type;
        this.answer = answer;
        this.ip = ip;
        this.mask = mask;
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
        if(num < dialogue.length)
        {
            return dialogue[num];
        }
        else
        {
            return "Something decided to go haywire.";
        }
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
        if(diagSoFar < type.length)
        {
            return type[diagSoFar];
        }
        else
        {
            return '`';
        }
    }
    public String getAnswer()
    {
        if(answerIndex < answer.length)
        {
            return answer[answerIndex];
        }
        else
        {
            return "`";
        }
    }
    public void incAnsIndex()
    {
        answerIndex++;
    }
    public String getIP()
    {
        if(ipSoFar == ip.length)
        {
            ipSoFar = 0;
        }
        ipSoFar++;
        return ip[ipSoFar - 1];
    }
    public String getMask()
    {
        if(maskSoFar == mask.length)
        {
            maskSoFar = 0;
        }
        maskSoFar++;
        return ip[maskSoFar - 1];
    }
}
