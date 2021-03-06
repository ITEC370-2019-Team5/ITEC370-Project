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

public class Desktop implements Screen{
    private NetworkingGame game; //Instance of the NetworkingGame class.
    private OrthographicCamera camera; //The camera object.
    private SpriteBatch batch; //Sprite batch object.
    private Stage stage; //Stage to be displayed.
    private Texture display;
    private Skin skin; //Skin object used for the TextField.
    private boolean init; //Stops memory leaks.

    //Method to init the class.
    public Desktop(NetworkingGame game){
        this.game = game;
        camera = new OrthographicCamera();
        init = false;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(display,camera.viewportWidth / 2 - 325, camera.viewportHeight / 2 - 325);


        if(Gdx.input.getX() >= 150 && Gdx.input.getX() <= 220
                &&
                Gdx.input.getY() >= 58 && Gdx.input.getY() <= 128){
            if (Gdx.input.isTouched() == true) {
                game.changeScreen(8);
            }
        }
        if(Gdx.input.getX() >= 150 && Gdx.input.getX() <= 220
                &&
                Gdx.input.getY() >= 142 && Gdx.input.getY() <= 212){
            if (Gdx.input.isTouched() == true) {
                game.changeScreen(10);
            }
        }
        if(Gdx.input.getX() >= 150 && Gdx.input.getX() <= 220
                &&
                Gdx.input.getY() >= 227 && Gdx.input.getY() <= 297){
            if (Gdx.input.isTouched() == true) {
                game.changeScreen(9);
            }
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
            init = true;
            display = new Texture("core/assets/DesktopPics/Desktop_Background.png");

            skin = new Skin(Gdx.files.internal("core/assets/clean-crispy/skin/clean-crispy-ui.json"));
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