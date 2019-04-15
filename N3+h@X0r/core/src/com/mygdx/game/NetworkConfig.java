package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.TextField;

import java.util.ArrayList;

public class NetworkConfig implements Screen{
    private NetworkingGame game; //Instance of the NetworkingGame class.
    private OrthographicCamera camera; //The camera object.
    private SpriteBatch batch; //Sprite batch object.
    private Stage stage; //Stage to be displayed.
    private Texture display;
    private Texture blackBox;
    private Skin skin; //Skin object used for the TextField.
    private boolean init; //Stops memory leaks.
    private Label label;
    private String str = "";
    private TextField textField;

    private int currentScreenNum;

    //Method to init the class.
    public NetworkConfig(NetworkingGame game){
        this.game = game;
        camera = new OrthographicCamera();
        init = false;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(display,camera.viewportWidth / 2 - 325, camera.viewportHeight / 2 - 325);
        batch.draw(blackBox, 150, 200);

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
        {
            str = textField.getText();

            label.toBack();
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

            skin = new Skin(Gdx.files.internal("core/assets/clean-crispy/skin/clean-crispy-ui.json"));
            blackBox = new Texture("core/assets/DesktopPics/blackBox.png");
            str = "For more info, you can type \"help\" \n To exit, you can type\"Exit\"";

            textField = new TextField("", skin);
            textField.setPosition(150, 150);
            textField.setSize(600, 50);
        }
        display = new Texture("core/assets/DesktopPics/Configure_Background.png");

        label = new Label(str, skin);
        label.setFontScale(2);
        label.setPosition(150, 200);
        label.setSize(600, 550);

        label.setText(str);

        stage.addActor(textField);
        stage.addActor(label);
        Gdx.input.setInputProcessor(stage);
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
        label.remove();
    }

    public void setScreen(int num)
    {
        currentScreenNum = num;
    }
    public void changeStr(int num, String str)
    {
        if(num == 1)
        {

        }
        else if (num == 2)
        {

        }
        else
        {

        }
    }
}

