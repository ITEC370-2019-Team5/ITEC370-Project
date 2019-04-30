package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

import java.util.ArrayList;

public class ServerRoom implements Screen , ApplicationListener {
    private NetworkingGame game;

    private boolean showOnce = false;
    private boolean rendOnce = false;
    private double id = Math.random();
    private TiledMap map;
    private OrthogonalTiledMapRenderer renderer;
    private OrthographicCamera camera;
    private TiledMapTileLayer platformingLayer;
    private int[] decorationLayers;
    private Player player;
    private boolean pressingEnter;
    private Texture prevTexture;

    private float x = 0;
    private float y = 4;

    private int upTime = 0;
    private int downTime = 0;
    private int leftTime = 0;
    private int rightTime = 0;
    private int charSelect;

    public ServerRoom(NetworkingGame game){
        this.game = game;
    }

    @Override
    public void show() {

        if(showOnce == false)
        {
            prevTexture = new Texture("core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkDown2.png");
            map = new TmxMapLoader().load("core/assets/ServerRoom.tmx");
        }

        renderer = new OrthogonalTiledMapRenderer(map);
        MapLayers layers = map.getLayers();
        platformingLayer = (TiledMapTileLayer) layers.get("Platforming");
        decorationLayers = new int[]{
                layers.getIndex("Background")
        };

        if(showOnce == false)
        {
            x = x * platformingLayer.getTileWidth();
            y = y * platformingLayer.getTileHeight();

            showOnce = true;
        }
        player = new Player(new Sprite(prevTexture), platformingLayer, charSelect);
        player.setBounds(0, 0, 16, 16);
        player.setPosition(x, y);

        camera = new OrthographicCamera();
    }


    @Override
    public void render (float delta) {
        pressingEnter = Gdx.input.isKeyPressed(Input.Keys.ENTER);

        Gdx.gl.glClearColor(0,0,0,1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        camera.position.x = 120;
        camera.position.y = 120;
        camera.zoom = 3/5f;
        camera.setToOrtho(false, 600, 400);

        map = new TmxMapLoader().load("core/assets/ServerRoom.tmx");
        renderer = new OrthogonalTiledMapRenderer(map);
        MapLayers layers = map.getLayers();
        platformingLayer = (TiledMapTileLayer) layers.get("Platforming");
        decorationLayers = new int[]{
                layers.getIndex("Background")
        };

        if(rendOnce == false)
        {
            player.setBounds(player.getX(),player.getY(),16,16);
            rendOnce = true;
        }

        //debugging purposes
        //System.out.println("X : " + player.getX());
        //System.out.println(" Y : " + player.getY());

        //giant if statement when you press ENTER at all throughout the playground
        if(pressingEnter) {
            float playerXCoord = player.getX();
            float playerYCoord = player.getY();

            if (playerXCoord > ((0 * 16) - 10) && playerXCoord < ((0 * 16) + 10) &&
                    playerYCoord > ((3.5 * 16) - 10) && playerYCoord < ((3.5 * 16) + 10))
            {
                game.changeScreen(1);
            }
            else if (playerXCoord > ((4 * 16) - 10) && playerXCoord < ((4 * 16) + 10) &&
                    playerYCoord > ((6 * 16) - 10) && playerYCoord < ((6 * 16) + 10))
            {
                game.changeScreen(7);
            }
        }

        //Setting the player
        x = player.updateCoordX(x);
        y = player.updateCoordY(y);
        player.setCollisionLayer(platformingLayer);
        player.setPosition(x, y);

        //Updating the player
        player.setUpTime(upTime);
        player.setDownTime(downTime);
        player.setLeftTime(leftTime);
        player.setRightTime(rightTime);

        //Update render and camera
        camera.update();
        renderer.setView(camera);
        renderer.render(decorationLayers);
        renderer.getBatch().begin();
        renderer.renderTileLayer(platformingLayer);

        player.update(delta);
        prevTexture = new Texture(player.prevTexture());

        upTime = player.getUpTime();
        downTime = player.getDownTime();
        leftTime = player.getLeftTime();
        rightTime = player.getRightTime();

        player.draw(renderer.getBatch());
        renderer.getBatch().end();

    }

    @Override
    public void render() {
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
    }

    @Override
    public void create() {
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() {
        dispose();
    }

    @Override
    public void dispose () {
        player.getTexture().dispose();
        map.dispose();
        renderer.dispose();
    }
    public double getID(){
        return id;
    }

    public void updateChar(int num)
    {
        charSelect = num;
    }
}