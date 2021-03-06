package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;

public class Player extends Sprite {

    private TiledMapTileLayer collisionLayer;
    private Texture[][] charTextures = new Texture[][]{
            new Texture[]{
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
                    new Texture("core/assets/CharSelectPics/C1_WalkUp3.png")},
            new Texture[]{
                    new Texture("core/assets/CharSelectPics/C2_WalkDown1.png"),
                    new Texture("core/assets/CharSelectPics/C2_WalkDown2.png"),
                    new Texture("core/assets/CharSelectPics/C2_WalkDown3.png"),
                    new Texture("core/assets/CharSelectPics/C2_WalkLeft1.png"),
                    new Texture("core/assets/CharSelectPics/C2_WalkLeft2.png"),
                    new Texture("core/assets/CharSelectPics/C2_WalkLeft3.png"),
                    new Texture("core/assets/CharSelectPics/C2_WalkRight1.png"),
                    new Texture("core/assets/CharSelectPics/C2_WalkRight2.png"),
                    new Texture("core/assets/CharSelectPics/C2_WalkRight3.png"),
                    new Texture("core/assets/CharSelectPics/C2_WalkUp1.png"),
                    new Texture("core/assets/CharSelectPics/C2_WalkUp2.png"),
                    new Texture("core/assets/CharSelectPics/C2_WalkUp3.png")},
            new Texture[]{
                    new Texture("core/assets/CharSelectPics/C3_WalkDown1.png"),
                    new Texture("core/assets/CharSelectPics/C3_WalkDown2.png"),
                    new Texture("core/assets/CharSelectPics/C3_WalkDown3.png"),
                    new Texture("core/assets/CharSelectPics/C3_WalkLeft1.png"),
                    new Texture("core/assets/CharSelectPics/C3_WalkLeft2.png"),
                    new Texture("core/assets/CharSelectPics/C3_WalkLeft3.png"),
                    new Texture("core/assets/CharSelectPics/C3_WalkRight1.png"),
                    new Texture("core/assets/CharSelectPics/C3_WalkRight2.png"),
                    new Texture("core/assets/CharSelectPics/C3_WalkRight3.png"),
                    new Texture("core/assets/CharSelectPics/C3_WalkUp1.png"),
                    new Texture("core/assets/CharSelectPics/C3_WalkUp2.png"),
                    new Texture("core/assets/CharSelectPics/C3_WalkUp3.png")},
            new Texture[]{
                    new Texture("core/assets/CharSelectPics/C4_WalkDown1.png"),
                    new Texture("core/assets/CharSelectPics/C4_WalkDown2.png"),
                    new Texture("core/assets/CharSelectPics/C4_WalkDown3.png"),
                    new Texture("core/assets/CharSelectPics/C4_WalkLeft1.png"),
                    new Texture("core/assets/CharSelectPics/C4_WalkLeft2.png"),
                    new Texture("core/assets/CharSelectPics/C4_WalkLeft3.png"),
                    new Texture("core/assets/CharSelectPics/C4_WalkRight1.png"),
                    new Texture("core/assets/CharSelectPics/C4_WalkRight2.png"),
                    new Texture("core/assets/CharSelectPics/C4_WalkRight3.png"),
                    new Texture("core/assets/CharSelectPics/C4_WalkUp1.png"),
                    new Texture("core/assets/CharSelectPics/C4_WalkUp2.png"),
                    new Texture("core/assets/CharSelectPics/C4_WalkUp3.png")},
            new Texture[]{
                    new Texture("core/assets/CharSelectPics/C5_WalkDown1.png"),
                    new Texture("core/assets/CharSelectPics/C5_WalkDown2.png"),
                    new Texture("core/assets/CharSelectPics/C5_WalkDown3.png"),
                    new Texture("core/assets/CharSelectPics/C5_WalkLeft1.png"),
                    new Texture("core/assets/CharSelectPics/C5_WalkLeft2.png"),
                    new Texture("core/assets/CharSelectPics/C5_WalkLeft3.png"),
                    new Texture("core/assets/CharSelectPics/C5_WalkRight1.png"),
                    new Texture("core/assets/CharSelectPics/C5_WalkRight2.png"),
                    new Texture("core/assets/CharSelectPics/C5_WalkRight3.png"),
                    new Texture("core/assets/CharSelectPics/C5_WalkUp1.png"),
                    new Texture("core/assets/CharSelectPics/C5_WalkUp2.png"),
                    new Texture("core/assets/CharSelectPics/C5_WalkUp3.png")}};

    private boolean pressingS = Gdx.input.isKeyPressed(Input.Keys.S);
    private boolean pressingD = Gdx.input.isKeyPressed(Input.Keys.D);
    private boolean pressingW = Gdx.input.isKeyPressed(Input.Keys.W);
    private boolean pressingA = Gdx.input.isKeyPressed(Input.Keys.A);
    private boolean pressingEnter = Gdx.input.isKeyPressed(Input.Keys.ENTER);

    protected String playerName = "";
    public static final int COLLECTABLES = 0;
    private boolean beatGame = false;
    private int collectableCount = 0;
    private int virusesFixed = 0;

    private int upTime = 0;
    private int downTime = 0;
    private int leftTime = 0;
    private int rightTime = 0;
    private int charSelect = 0;

    //Empty Constructor
    public Player() {
        super();
    }

    //Full Constructor
    public Player(Sprite sprite, TiledMapTileLayer collisionLayer, int _charSelect){
        super(sprite);
        this.collisionLayer = collisionLayer;
        charSelect = _charSelect;
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

        pressingS = Gdx.input.isKeyPressed(Input.Keys.S);
        pressingD = Gdx.input.isKeyPressed(Input.Keys.D);
        pressingW = Gdx.input.isKeyPressed(Input.Keys.W);
        pressingA = Gdx.input.isKeyPressed(Input.Keys.A);

        // Moving Down
        if(pressingS && !pressingA && !pressingD && !pressingW && !collidesBottom()){
            if(downTime < 5)
                setTexture(charTextures[charSelect][0]);
            if(downTime >= 5 && downTime < 10)
                setTexture(charTextures[charSelect][1]);
            if(downTime >= 10 && downTime < 15)
                setTexture(charTextures[charSelect][2]);
            if(downTime >= 15 && downTime < 20)
                setTexture(charTextures[charSelect][1]);
            if(downTime >= 20)
                downTime = 0;
            downTime += 2;
        }
        // Moving Left
        else if(!pressingS && pressingA && !pressingD && !pressingW && !collidesLeft()){
            if(leftTime < 5)
                setTexture(charTextures[charSelect][3]);
            if(leftTime >= 5 && leftTime < 10)
                setTexture(charTextures[charSelect][4]);
            if(leftTime >= 10 && leftTime < 15)
                setTexture(charTextures[charSelect][5]);
            if(leftTime >= 15 && leftTime < 20)
                setTexture(charTextures[charSelect][4]);
            if(leftTime >= 20)
                leftTime = 0;
            leftTime += 2;
        }
        // Moving Right
        else if(!pressingS && !pressingA && pressingD && !pressingW && !collidesRight()){
            if(rightTime < 5)
                setTexture(charTextures[charSelect][6]);
            if(rightTime >= 5 && rightTime < 10)
                setTexture(charTextures[charSelect][7]);
            if(rightTime >= 10 && rightTime < 15)
                setTexture(charTextures[charSelect][8]);
            if(rightTime >= 15 && rightTime < 20)
                setTexture(charTextures[charSelect][8]);
            if(rightTime >= 20)
                rightTime = 0;
            rightTime += 2;
        }
        // Moving Up
        else if(!pressingS && !pressingA && !pressingD && pressingW && !collidesTop()){
            if(upTime < 5)
                setTexture(charTextures[charSelect][9]);
            if(upTime >= 5 && upTime < 10)
                setTexture(charTextures[charSelect][10]);
            if(upTime >= 10 && upTime < 15)
                setTexture(charTextures[charSelect][11]);
            if(upTime >= 15 && upTime < 20)
                setTexture(charTextures[charSelect][10]);
            if(upTime >= 20)
                upTime = 0;
            upTime += 2;
        }
        else
        {
            setTexture(charTextures[charSelect][1]);
        }

        if(collidesBottom() || collidesLeft() || collidesRight() || collidesTop())
            setPosition(getX(),getY());

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

    public float updateCoordY(float y)
    {
        pressingW = Gdx.input.isKeyPressed(Input.Keys.W);
        pressingS = Gdx.input.isKeyPressed(Input.Keys.S);

        if(pressingW && collidesTop() == false)
        {
            return (y + 3);
        }
        else if(pressingS && collidesBottom() == false)
        {
            return (y - 3);
        }
        else
        {
            return y;
        }
    }
    public float updateCoordX(float x)
    {
        pressingD = Gdx.input.isKeyPressed(Input.Keys.D);
        pressingA = Gdx.input.isKeyPressed(Input.Keys.A);

        if(pressingA && collidesLeft() == false)
        {
            return (x - 3);
        }
        else if(pressingD && collidesRight() == false)
        {
            return (x + 3);
        }
        else
        {
            return x;
        }
    }
    public int getUpTime()
    {
        return upTime;
    }
    public int getDownTime()
    {
        return downTime;
    }
    public int getLeftTime()
    {
        return leftTime;
    }
    public int getRightTime()
    {
        return rightTime;
    }

    public void setUpTime(int up){upTime = up;}
    public void setDownTime(int down){downTime = down;}
    public void setLeftTime(int left){leftTime = left;}
    public void setRightTime(int right){rightTime = right;}

    public String prevTexture()
    {
        if(pressingS && !pressingA && !pressingD && !pressingW && !collidesBottom()){
            if(downTime < 5)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkDown1.png";
            if(downTime >= 5 && downTime < 10)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkDown2.png";
            if(downTime >= 10 && downTime < 15)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkDown3.png";
            if(downTime >= 15 && downTime <= 20)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkDown2.png";
        }
        // Moving Left
        else if(!pressingS && pressingA && !pressingD && !pressingW && !collidesLeft()){
            if(leftTime < 5)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkLeft1.png";
            if(leftTime >= 5 && leftTime < 10)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkLeft2.png";
            if(leftTime >= 10 && leftTime < 15)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkLeft3.png";
            if(leftTime >= 15 && leftTime <= 20)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkLeft2.png";
        }
        // Moving Right
        else if(!pressingS && !pressingA && pressingD && !pressingW && !collidesRight()){
            if(rightTime < 5)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkRight1.png";
            if(rightTime >= 5 && rightTime < 10)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkRight2.png";
            if(rightTime >= 10 && rightTime < 15)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkRight3.png";
            if(rightTime >= 15 && rightTime <= 20)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkRight2.png";
        }
        // Moving Up
        else if(!pressingS && !pressingA && !pressingD && pressingW && !collidesTop()){
            if(upTime < 5)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkUp1.png";
            if(upTime >= 5 && upTime < 10)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkUp2.png";
            if(upTime >= 10 && upTime < 15)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkUp3.png";
            if(upTime >= 15 && upTime <= 20)
                return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkUp2.png";
        }
        return "core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkDown2.png";
    }

}
