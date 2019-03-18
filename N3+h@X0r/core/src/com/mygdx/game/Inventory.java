package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import java.util.ArrayList;

public class Inventory implements Screen{
    private NetworkingGame game; //Instance of the NetworkingGame class.
    private OrthographicCamera camera; //The camera object.
    private SpriteBatch batch; //Sprite batch object.
    private Stage stage; //Stage to be displayed.
    private Texture inventory, characterPic; //LeaderBoard's textures.
    private Skin skin; //Skin object used for the TextField.
    private boolean init; //Stops memory leaks.
    private ArrayList<Item> itemList = new ArrayList<Item>();

    //Method to init the class.
    public Inventory(NetworkingGame game){
        this.game = game;
        camera = new OrthographicCamera();
        init = false;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(inventory, 130, 60);

        batch.end();
        stage.act(delta);
        stage.draw();
    }

    @Override
    public void show() {
        if(init == false)
        {
            camera.setToOrtho(false, 600, 400);
            batch = new SpriteBatch();
            stage = new Stage();
            //inventory = new Texture("core/assets/bigleaderboard.png");
            //characterPic = new Texture("core/assets/star.png");
            init = true;

            skin = new Skin(Gdx.files.internal("core/assets/clean-crispy/skin/clean-crispy-ui.json"));

            //template code for adding items to inventory and setting coordinates and size
            //nameText1 = new TextField(lineArray[0], skin);
            //nameText1.setPosition(nameXCoord, 360);
            //nameText1.setSize(100, 25);
            //stage.addActor(nameText1);
        }
    }

    @Override
    public void resize(int width, int height) {
        camera.viewportWidth = width;
        camera.viewportHeight = height;
    }

    @Override
    public void pause() {
    }

    @Override
    public void resume() {
    }

    @Override
    public void hide() { dispose(); }
        @Override
        public void dispose () {
        }
    }

