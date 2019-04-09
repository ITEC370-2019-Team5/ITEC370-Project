package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Item extends Sprite{
    private String itemName;
    private String location;
    private int amount;
    private TiledMapTileLayer collisionLayer;
    private float x;
    private float y;
    private String hint;
    private char type;

    public Item() {

        super();
    }

    public Item(Sprite sprite, char type, String itemName, String location, int amount, TiledMapTileLayer collisionLayer, float x, float y) {
        super(sprite);
        this.type = type;
        this.itemName = itemName;
        this.location = location;
        this.amount = amount;
        this.collisionLayer = collisionLayer;
        this.x = x;
        this.y = y;
    }
    public Item(Sprite sprite, char type, String itemName, String location, int amount, TiledMapTileLayer collisionLayer, float x, float y, String hint)
    {
        super(sprite);
        this.type = type;
        this.itemName = itemName;
        this.location = location;
        this.amount = amount;
        this.collisionLayer = collisionLayer;
        this.x = x;
        this.y = y;
        this.hint = hint;
    }

    public void draw(SpriteBatch spriteBatch) {
        update(Gdx.graphics.getDeltaTime());
        super.draw(spriteBatch);
    }

    void update(float delta) {
        setPosition(getX(), getY());
    }


    //getters and setters
    public String getItemName() {
        return itemName;
    }

    public void setItemName(String itemName) {
        this.itemName = itemName;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public TiledMapTileLayer getCollisionLayer() {
        return collisionLayer;
    }

    public void setCollisionLayer(TiledMapTileLayer collisionLayer) {
        this.collisionLayer = collisionLayer;
    }

    public float updateCoordX()
    {
        return x;
    }
    public float updateCoordY()
    {
        return y;
    }
    public void setX(int num)
    {
        x = num;
    }
    public void setY(int num)
    {
        y = num;
    }
    public char getType()
    {
        return this.type;
    }
    public String getHint()
    {
        return this.hint;
    }

}
