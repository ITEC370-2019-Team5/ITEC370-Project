package com.mygdx.game;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

public class TextScreen implements Screen{
    private NetworkingGame game; //Instance of the NetworkingGame class.
    private OrthographicCamera camera; //The camera object.
    private SpriteBatch batch; //Sprite batch object.
    private Stage stage; //Stage to be displayed.
    private Texture blackBox;
    private Skin skin; //Skin object used for the TextField.
    private boolean init; //Stops memory leaks.
    String displayThis = "";
    private Label label;
    private char whoami = 'h';
    private int t = 0;

    //Method to init the class.
    public TextScreen(NetworkingGame game){
        this.game = game;
        camera = new OrthographicCamera();
        init = false;
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(blackBox, 50, 100);

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER) && t >= 40)
        {

            if(whoami == 'h')
            {
                game.changeScreen(1);
            }
            else if(whoami == 'b')
            {
                if(game.getNextDiagType() == 'd')
                {
                    displayThis = game.getNextDialogue();
                    game.setIndex(game.getBossIndex());
                }
                else if(game.getNextDiagType() == 'q')
                {
                    if(game.questionDone())
                    {
                        displayThis = game.getNextDialogue();
                        game.setIndex(game.getBossIndex());
                    }
                }
                else
                {
                    game.changeScreen(1);
                }
            }
            else
            {
                game.changeScreen(1);
            }
            t = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.A) && t >= 40)
        {
            if(game.getNextDiagType() == 'q')
            {
                if(game.getAnswer().equals("a"))
                {
                    game.incAnsIndex();
                    displayThis = game.getNextDialogue();
                    game.setIndex(game.getBossIndex());
                }
                else
                {
                    System.out.println("That answer is incorrect");
                }
            }
            t = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.B) && t >= 40)
        {
            if(game.getNextDiagType() == 'q')
            {
                if(game.getAnswer().equals("b"))
                {
                    game.incAnsIndex();
                    displayThis = game.getNextDialogue();
                    game.setIndex(game.getBossIndex());
                }
                else
                {
                    System.out.println("That answer is incorrect");
                }
            }
            t = 0;
        }
        else if(Gdx.input.isKeyPressed(Input.Keys.C) && t >= 40)
        {
            if(game.getNextDiagType() == 'q')
            {
                if(game.getAnswer().equals("c"))
                {
                    game.incAnsIndex();
                    displayThis = game.getNextDialogue();
                    game.setIndex(game.getBossIndex());
                }
                else
                {
                    System.out.println("That answer is incorrect");
                }
            }
            t = 0;
        }
        t++;

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
            blackBox = new Texture("core/assets/TextScreen.png");
            init = true;

            skin = new Skin(Gdx.files.internal("core/assets/clean-crispy/skin/clean-crispy-ui.json"));
        }

        label = new Label(displayThis, skin);
        label.setFontScale(2);
        label.setPosition(100, 125);
        label.setSize(700, 200);

        label.setText(displayThis);


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
    public void dispose ()
    {
        label.remove();
    }
    public void changeStr(String str)
    {
        displayThis = str;
    }
    public void whoami(char myChar)
    {
        whoami = myChar;
    }
}

