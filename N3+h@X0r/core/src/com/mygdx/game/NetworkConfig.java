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
    private Skin skin; //Skin object used for the TextField.
    private boolean init; //Stops memory leaks.
    private Label label1, label2;
    private String str1, str2 = "";
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

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
        {
            str2 = str2;
            str1 = textField.getText();
            setStr(str1);
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
            str1 = "For more info, you can type \"help\" \n To exit, you can type\"Exit\"";

            textField = new TextField("", skin);
            textField.setPosition(150, 150);
            textField.setSize(600, 50);
        }
        display = new Texture("core/assets/DesktopPics/Configure_Background.png");

        label1 = new Label(str1, skin);
        label2 = new Label(str1, skin);

        label1.setFontScale(2);
        label2.setFontScale(2);

        label1.setPosition(150, 200);
        label2.setPosition(150, 475);

        label1.setSize(600, 275);
        label2.setSize(600, 275);

        label1.setText(str1);
        label2.setText(str2);

        stage.addActor(textField);
        stage.addActor(label1);
        stage.addActor(label2);
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
        label1.remove();
        label2.remove();
    }

    public void setScreen(int num)
    {
        currentScreenNum = num;
    }
    public void setStr(String cmd)
    {
        if(cmd.equals("help"))
        {
            str2 = "test";
        }
        else if(cmd.equals(""))
        {
            str2 = "";
        }
        else if(cmd.equals(""))
        {
            str2 = "";
        }
        else if(cmd.equals(""))
        {
            str2 = "";
        }
        else
        {
            System.out.println("Command not found");
        }
    }
}

