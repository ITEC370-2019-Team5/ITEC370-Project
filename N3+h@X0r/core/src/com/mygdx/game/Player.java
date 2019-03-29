package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.math.Vector2;

import java.awt.*;

public class Player extends Sprite {

    private Vector2 velocity = new Vector2();
    private float speed = 90;
    private double id = Math.random();

    private TiledMapTileLayer collisionLayer;
    private Texture[] c1Textures = new Texture[]{
            new Texture("core/assets/CharSelectPics/C1_WalkDown1.png"),
            new Texture("core/assets/CharSelectPics/C1_WalkDown2.png"),
            new Texture("core/assets/CharSelectPics/C1_WalkDown3.png"),
            new Texture("core/assets/CharSelectPics/C1_WalkLeft1.png"),
            new Texture("core/assets/CharSelectPics/C1_WalkLeft2.png"),
            new Texture("core/assets/CharSelectPics/C1_WalkLeft3.png"),
            new Texture("core/assets/CharSelectPics/C1_WalkRight1.png"),
            new Texture("core/assets/CharSelectPics/C1_WalkRight2.png"),
            new Texture("core/assets/CharSelectPics/C1_WalkRight3.png"),
            new Texture("core/assets/CharSelectPics/C1_WalkUp1.png"),
            new Texture("core/assets/CharSelectPics/C1_WalkUp2.png"),
            new Texture("core/assets/CharSelectPics/C1_WalkUp3.png"),
    };
    private boolean pressingS = Gdx.input.isKeyPressed(Input.Keys.S);
    private boolean pressingD = Gdx.input.isKeyPressed(Input.Keys.D);
    private boolean pressingW = Gdx.input.isKeyPressed(Input.Keys.W);
    private boolean pressingA = Gdx.input.isKeyPressed(Input.Keys.A);

    protected String playerName = "";
    public static final int COLLECTABLES = 0;
    private boolean beatGame = false;
    private int collectableCount = 0;
    private int virusesFixed = 0;

    private int upTime = 0;
    private int downTime = 0;
    private int leftTime = 0;
    private int rightTime = 0;

    //Empty Constructor
    public Player() {
        super();
    }

    //Full Constructor
    public Player(Sprite sprite, TiledMapTileLayer collisionLayer){
        super(sprite);
        this.collisionLayer = collisionLayer;
    }

    public Player(String playerName, boolean beatGame, int collectableCount, int virusesFixed){
        this.playerName = playerName;
        this.beatGame = beatGame;
        this.collectableCount = collectableCount;
        this.virusesFixed = virusesFixed;
    }

    public void draw(SpriteBatch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    void update(float delta){

        float oldX = getX(), oldY = getY();
        boolean collisionX = false, collisionY = false;

        setX(getX() + velocity.x * delta);
        if (velocity.x < 0)
            collisionX = collidesLeft();
        else if (velocity.x > 0)
            collisionX = collidesRight();
        if (collisionX) {
            setX(oldX);
            velocity.x = 0;
        }

        setY(getY() + velocity.y * delta);
        if (velocity.y < 0)
            collisionY = collidesBottom();
        else if (velocity.y > 0)
            collisionY = collidesTop();
        if (collisionY) {
            setX(oldY);
            velocity.y = 0;
        }
        
        pressingS = Gdx.input.isKeyPressed(Input.Keys.S);
        pressingD = Gdx.input.isKeyPressed(Input.Keys.D);
        pressingW = Gdx.input.isKeyPressed(Input.Keys.W);
        pressingA = Gdx.input.isKeyPressed(Input.Keys.A);

        // Moving Down
        if(pressingS && !pressingA && !pressingD && !pressingW){
            System.out.println("Down " + downTime+" " + getX()+" " + getY());
            translateY(-2f);
            System.out.println("Down " + downTime+" " + getX()+" " + getY());
            if(downTime < 10)
                setTexture(c1Textures[0]);
            if(downTime >= 10 && downTime < 20)
                setTexture(c1Textures[1]);
            if(downTime >= 20 && downTime < 30)
                setTexture(c1Textures[2]);
            if(downTime >= 30 && downTime < 40)
                setTexture(c1Textures[1]);
            if(downTime == 40)
                downTime = 0;
            downTime++;
        }

        // Moving Left
        if(!pressingS && pressingA && !pressingD && !pressingW){
            translateX(-2f);
            if(leftTime < 10)
                setTexture(c1Textures[3]);
            if(leftTime >= 10 && leftTime < 20)
                setTexture(c1Textures[4]);
            if(leftTime >= 20 && leftTime < 30)
                setTexture(c1Textures[5]);
            if(leftTime >= 30 && leftTime < 40)
                setTexture(c1Textures[4]);
            if(leftTime == 40)
                leftTime = 0;
            leftTime++;
        }

        // Moving Right
        if(!pressingS && !pressingA && pressingD && !pressingW){
            translateX(2f);
            if(rightTime < 10)
                setTexture(c1Textures[6]);
            if(rightTime >= 10 && rightTime < 20)
                setTexture(c1Textures[7]);
            if(rightTime >= 20 && rightTime < 30)
                setTexture(c1Textures[8]);
            if(rightTime >= 30 && rightTime < 40)
                setTexture(c1Textures[7]);
            if(rightTime == 40)
                rightTime = 0;
            rightTime++;
        }

        // Moving Up
        if(!pressingS && !pressingA && !pressingD && pressingW){
            translateY(2f);
            if(upTime < 10)
                setTexture(c1Textures[9]);
            if(upTime >= 10 && upTime < 20)
                setTexture(c1Textures[10]);
            if(upTime >= 20 && upTime < 30)
                setTexture(c1Textures[11]);
            if(upTime >= 30 && upTime < 40)
                setTexture(c1Textures[10]);
            if(upTime == 40)
                upTime = 0;
            upTime++;
        }

    }

    public String toString() {
        return beatGame+"\t"+playerName+"\t"+
                        collectableCount+"\t"+virusesFixed+"\n";
    }

    public boolean isCellBlocked(float x, float y) {
        TiledMapTileLayer.Cell cell = collisionLayer.getCell((int) (x / collisionLayer.getTileWidth()), (int) (y / collisionLayer.getTileHeight()));
        return cell != null && cell.getTile() != null;
    }

    public boolean collidesRight() {
        boolean collides = false;
        for (float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellBlocked(getX() + getWidth(), getY() + step))
                break;
        }
        return collides;
    }

    public boolean collidesLeft() {
        boolean collides = false;
        for (float step = 0; step < getHeight(); step += collisionLayer.getTileHeight() / 2) {
            if (collides = isCellBlocked(getX(), getY() + step))
                break;
        }
        return collides;
    }

    public boolean collidesTop() {
        boolean collides = false;
        for (float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2) {
            if (collides = isCellBlocked(getX() + 2 + step, getY() + getHeight()))
                break;
        }
        return collides;
    }

    public boolean collidesBottom() {
        boolean collides = false;
        for (float step = 0; step < getWidth(); step += collisionLayer.getTileWidth() / 2) {
            if (collides = isCellBlocked(getX() + 2 + step, getY()))
                break;
        }
        return collides;
    }

    //getters and setters
    public String getPlayerName() {
        return playerName;
    }

    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    public boolean getBeatGame() {
        return beatGame;
    }

    public void setBeatGame(boolean beatGame) {
        this.beatGame = beatGame;
    }

    public int getCollectableCount() {
        return collectableCount;
    }

    public void setCollectableCount(int collectableCount) {
        this.collectableCount = collectableCount;
    }

    public int getVirusesFixed() {
        return virusesFixed;
    }

    public void setVirusesFixed(int collectableCount) {
        this.collectableCount = collectableCount;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    public double getID(){
        return id;
    }
}
