package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
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
    private Texture inventory;
    private Skin skin; //Skin object used for the TextField.
    private boolean init; //Stops memory leaks.
    private Sprite[] itemSpriteList = new Sprite[14];
    private int x = 0;

    //Method to init the class.
    public Inventory(NetworkingGame game){
        this.game = game;
        camera = new OrthographicCamera();
        init = false;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(inventory, 200, 120);

       Sprite s = new Sprite(new Texture("core/assets/test_item.png"));
       itemSpriteList[13] = s;

        System.out.println(itemSpriteList[0]);

        if (itemSpriteList[0] != null)
        {
            itemSpriteList[0].setSize(73, 73);
            batch.draw(itemSpriteList[0],469, 497);
        }
        if (itemSpriteList[1] != null)
        {
            itemSpriteList[1].setSize(73, 73);
            batch.draw(itemSpriteList[1],576, 497);
        }
        if (itemSpriteList[2] != null)
        {
            itemSpriteList[2].setSize(73, 73);
            batch.draw(itemSpriteList[2],250, 399);
        }
        if (itemSpriteList[3] != null)
        {
            itemSpriteList[3].setSize(73, 73);
            batch.draw(itemSpriteList[3],361, 399);
        }
        if (itemSpriteList[4] != null)
        {
            itemSpriteList[4].setSize(73, 73);
            batch.draw(itemSpriteList[4],469, 399);
        }
        if (itemSpriteList[5] != null)
        {
            itemSpriteList[5].setSize(73, 73);
            batch.draw(itemSpriteList[5],576, 399);
        }
        if (itemSpriteList[6] != null)
        {
            itemSpriteList[6].setSize(73, 73);
            batch.draw(itemSpriteList[6],250, 302);
        }
        if (itemSpriteList[7] != null)
        {
            itemSpriteList[7].setSize(73, 73);
            batch.draw(itemSpriteList[7],361, 302);
        }
        if (itemSpriteList[8] != null)
        {
            itemSpriteList[8].setSize(73, 73);
            batch.draw(itemSpriteList[8],469, 302);
        }
        if (itemSpriteList[9] != null)
        {
            itemSpriteList[9].setSize(73, 73);
            batch.draw(itemSpriteList[9],576, 302);
        }
        if (itemSpriteList[10] != null)
        {
            itemSpriteList[10].setSize(73, 73);
            batch.draw(itemSpriteList[10],250, 204);
        }
        if (itemSpriteList[11] != null)
        {
            itemSpriteList[11].setSize(73, 73);
            batch.draw(itemSpriteList[11],361, 204);
        }
        if (itemSpriteList[12] != null)
        {
            itemSpriteList[12].setSize(73, 73);
            batch.draw(itemSpriteList[12],469, 204);
        }
        if (itemSpriteList[13] != null)
        {
            itemSpriteList[13].setSize(73, 73);
            batch.draw(itemSpriteList[13],576, 204);
        }



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
            inventory = new Texture("core/assets/inventory_page.png");
            init = true;

            skin = new Skin(Gdx.files.internal("core/assets/clean-crispy/skin/clean-crispy-ui.json"));
        }
    }

    //getter - gets Sprite of index
    public Sprite getItemSprite(int position) {
        return itemSpriteList[position];
    }

    //setter
    public void setItemSprite(Sprite[] itemSpriteList, int position) {
        this.itemSpriteList[position] = itemSpriteList[position];
    }

    public void addToInv (Sprite s) {
        itemSpriteList[x] = s;
        System.out.println(itemSpriteList[0]);
        x++;
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

