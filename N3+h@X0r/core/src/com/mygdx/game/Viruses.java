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

public class Viruses implements Screen{
    private NetworkingGame game; //Instance of the NetworkingGame class.
    private OrthographicCamera camera; //The camera object.
    private SpriteBatch batch; //Sprite batch object.
    private Stage stage; //Stage to be displayed.
    private Texture display;
    private Skin skin; //Skin object used for the TextField.
    private int countPush = 0; //Used to only click button once. -AR
    private boolean init; //Stops memory leaks.
    private int screen = 1;

    private int currentScreenNum;

    //Method to init the class.
    public Viruses(NetworkingGame game){
        this.game = game;
        camera = new OrthographicCamera();
        init = false;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(display,camera.viewportWidth / 2 - 325, camera.viewportHeight / 2 - 325);

        batch.end();
        stage.act(delta);
        stage.draw();
        if(screen == 20)
        {
            screen = 1;
            game.changeScreen(7);
        }
    }

    @Override
    public void show() {
        if(init == false)
        {
            camera.setToOrtho(false, 600, 400);
            batch = new SpriteBatch();
            stage = new Stage();
            init = true;

            skin = new Skin(Gdx.files.internal("core/assets/clean-crispy/skin/clean-crispy-ui.json"));
        }
        display = new Texture("core/assets/DesktopPics/VirusPics/Desktop_Virus_" + screen + ".png");
        screen++;
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
        display.dispose();
    }

    public void setScreen(int num)
    {
        currentScreenNum = num;
    }
}

