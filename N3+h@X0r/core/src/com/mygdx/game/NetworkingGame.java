package com.mygdx.game;

import com.badlogic.gdx.*;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.util.Random;

public class NetworkingGame extends Game implements ApplicationListener {

    private int charSelect = 0;
    private String gameName = "N3+h@X0r";
    private int screen = 0;
    MainMenu mainMenu = new MainMenu(this);
    Playground playground = new Playground(this);
    CharacterScreen charScreen = new CharacterScreen(this);
    LeaderBoard leaderboard = new LeaderBoard(this);
    LoadGameScreen load = new LoadGameScreen(this);
    Inventory inventory = new Inventory(this);
    TextScreen textScreen = new TextScreen(this);
    Desktop desktop = new Desktop(this);
    Restart restart = new Restart(this);
    Viruses viruses = new Viruses(this);
    NetworkConfig nc = new NetworkConfig(this);
    ServerRoom sr = new ServerRoom(this);
    pauseMenu pm = new pauseMenu(this);

    int currentScreenNum = 0;//Keeps Track of screen number for leaderboard.

    @Override
    public void create () {
        setScreen(mainMenu);
    }

    public void changeScreen(int setting)
    {
        screen = setting;
    }

    //Look for button press

    @Override
    public void render () {
        super.render();
        playground.updateChar(charSelect);
        sr.updateChar(charSelect);
        if(screen == 0) //Main menu
        {
            currentScreenNum = 0;
            setScreen(mainMenu);
            if(Gdx.input.isKeyPressed(Input.Keys.L))
            {
                changeScreen(3); //shows leaderboard
            }
            if(Gdx.input.isKeyPressed(Input.Keys.I))
            {
                changeScreen(5); //shows inventory
            }
        }
        else if(screen == 1) //Playground
        {
            currentScreenNum = 1;
            setScreen(playground);
            if(Gdx.input.isKeyPressed(Input.Keys.L))
            {
                changeScreen(3); //shows leaderboard
            }
            if(Gdx.input.isKeyPressed(Input.Keys.I))
            {
                changeScreen(5); //shows inventory
            }
            if(Gdx.input.isKeyPressed(Input.Keys.P))
            {
                changeScreen(12); //Shows pause menu
            }
        }
        else if(screen == 2)//Character selection
        {
            currentScreenNum = 2;
            setScreen(charScreen);
            if(Gdx.input.isKeyPressed(Input.Keys.L))
            {
                changeScreen(3); //shows leaderboard
            }
            if(Gdx.input.isKeyPressed(Input.Keys.I))
            {
                changeScreen(5); //shows inventory
            }
        }
        else if(screen == 3)//Leader board
        {
            setScreen(leaderboard);
            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            {
                changeScreen(currentScreenNum);
            }
        }
        else if(screen == 4)//Loading screen
        {
            setScreen(load);
        }
        else if(screen == 5)//Inventory screen
        {
            setScreen(inventory);
            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            {
                changeScreen(currentScreenNum);
            }
        }
        else if(screen == 6)//TextBox screen
        {
            setScreen(textScreen);
            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            {
                changeScreen(currentScreenNum);
            }
        }
        else if(screen == 7)//Computer screen
        {
            setScreen(desktop);
            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            {
                changeScreen(currentScreenNum);
            }
        }
        else if(screen == 8)//Computer restarting
        {
            restart.setScreen(currentScreenNum);
           setScreen(restart);
        }
        else if(screen == 9)//Removing viruses
        {
            viruses.setScreen(currentScreenNum);
            setScreen(viruses);
        }
        else if(screen == 10)//CLI for network config
        {
            nc.setScreen(currentScreenNum);
            setScreen(nc);
        }
        else if(screen == 11)//Server Room
        {
            currentScreenNum = 11;
            setScreen(sr);
            if(Gdx.input.isKeyPressed(Input.Keys.P))
            {
                changeScreen(12); //Shows pause menu
            }
        }
        else if(screen == 12)
        {
            setScreen(pm);
            if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE))
            {
                changeScreen(currentScreenNum); //Shows pause menu
            }
        }
        else
        {
            System.out.println("Error!");
        }
    }

    @Override
    public void dispose () { super.dispose(); }

    @Override
    public void resize ( int width, int height){
        super.resize(width, height);
    }

    @Override
    public void pause () {
        super.pause();
    }

    @Override
    public void resume () {
        super.resume();
    }

    public void setGameName(String name)
    {
        gameName = name;
    }

    public void changeChar()
    {
        Random rand = new Random();
        charSelect = rand.nextInt(5);
    }
    public int getChar()
    {
        return charSelect;
    }
    public void changeStr(String str, char type)
    {
        textScreen.whoami(type);
        textScreen.changeStr(str);
    }
    public String getNextDialogue()
    {
        return playground.getNextDialogue();
    }
    public char getNextDiagType()
    {
        return playground.getNextDiagType();
    }
    public String getAnswer()
    {
        return playground.getAnswer();
    }
    public void incAnsIndex()
    {
        playground.incAnsIndex();
    }
    public void addToInv(Sprite s) { inventory.addToInv(s);}

    public boolean questionDone()
    {
        if(playground.getIP().equals(nc.getIP("B")))
        {
            return true;
        }
        else
        {
            return false;
        }
    }
}