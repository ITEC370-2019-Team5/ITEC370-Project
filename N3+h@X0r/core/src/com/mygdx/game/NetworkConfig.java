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
import java.util.Scanner;

public class NetworkConfig implements Screen{
    private NetworkingGame game; //Instance of the NetworkingGame class.
    private OrthographicCamera camera; //The camera object.
    private SpriteBatch batch; //Sprite batch object.
    private Stage stage; //Stage to be displayed.
    private Texture display;
    private Skin skin; //Skin object used for the TextField.
    private boolean init; //Stops memory leaks.
    private Label label;
    private String str = "";
    private TextField textField;

    private int currentScreenNum;

    private Computer[] comps = new Computer[10];
    private String currentComp = "A";

    //Method to init the class.
    public NetworkConfig(NetworkingGame game){
        this.game = game;
        camera = new OrthographicCamera();
        init = false;
        comps[0] = new Computer("A", "10.1.1.2", "0:0:0:0:0:ffff:a01:102", "255.255.255.0", "162.96.48.231");
        comps[1] = new Computer("B", "10.1.1.3", "0:0:0:0:0:ffff:a01:103", "255.255.255.0", "162.96.48.231");
        comps[2] = new Computer("C", "10.1.1.4", "0:0:0:0:0:ffff:a01:104", "255.255.255.0", "162.96.48.231");
        comps[3] = new Computer("D", "10.1.1.5", "0:0:0:0:0:ffff:a01:105", "255.255.255.0", "162.96.48.231");
        comps[4] = new Computer("E", "10.1.1.6", "0:0:0:0:0:ffff:a01:106", "255.255.255.0", "162.96.48.231");
        comps[5] = new Computer("F", "10.1.1.7", "0:0:0:0:0:ffff:a01:107", "255.255.255.0", "162.96.48.231");
        comps[6] = new Computer("G", "10.1.1.8", "0:0:0:0:0:ffff:a01:108", "255.255.255.0", "162.96.48.231");
        comps[7] = new Computer("H", "10.1.1.9", "0:0:0:0:0:ffff:a01:109", "255.255.255.0", "162.96.48.231");
        comps[8] = new Computer("I", "10.1.1.10", "0:0:0:0:0:ffff:a01:10a", "255.255.255.0", "162.96.48.231");
        comps[9] = new Computer("J", "10.1.1.11", "0:0:0:0:0:ffff:a01:10b", "255.255.255.0", "162.96.48.231");
    }

    @Override
    public void render(float delta) {
        batch.begin();
        batch.draw(display,camera.viewportWidth / 2 - 325, camera.viewportHeight / 2 - 325);

        if(Gdx.input.isKeyPressed(Input.Keys.ENTER))
        {
            str = textField.getText();
            setStr(str);
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
            str = "For more info, you can type \"?\" \nTo exit, you can type\"exit\"";

            textField = new TextField("", skin);
            textField.setPosition(150, 120);
            textField.setSize(600, 50);
        }
        display = new Texture("core/assets/DesktopPics/Configure_Background.png");

        label = new Label(str, skin);

        label.setFontScale(2);
        label.setPosition(150, 300);
        label.setSize(600, 400);
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
    public void setStr(String cmd)
    {
        Scanner scan = new Scanner(cmd);
        scan.useDelimiter(" ");
        String command = "";
        command = scan.next();

        if(command.equals("?"))
        {
            str = "Commands: \n" +
                    "  ?: Displays this message\n" +
                    "  exit: exits the CLI\n" +
                    "  config <device>: Sets the config device\n" +
                    "  ip <addr> <mask>: Configures IP features\n" +
                    "  ipv6 <addr>: Configures IPv6 features\n" +
                    "  gateway <addr>: Configures the gateway\n" +
                    "  ipconfig: displays config info";
        }
        else if(command.equals("exit"))
        {
            str = "For more info, you can type \"help\" \nTo exit, you can type\"exit\"";
            game.changeScreen(7);

        }
        else if(command.equals("config"))
        {
            try
            {
                currentComp = scan.next();
                str = "Configuring device: " + currentComp;
            }
            catch(Exception e)
            {
                str = "Invalid arguments.\nCorrect use: config <device>";
            }
        }
        else if(command.equals("ip"))
        {
            for(int i = 0; i < comps.length; i++)
            {
                if(comps[i].getName().equals(currentComp))
                {
                    String ip;
                    String mask;
                    try
                    {
                        ip = scan.next();
                        mask = scan.next();
                        comps[i].setip(ip);
                        comps[i].setMask(mask);
                        str = "Configuration complete for device: " + currentComp;
                    }
                    catch(Exception e)
                    {
                        str = "Invalid arguments.\nCorrect use: ip <Addr> <mask>";
                    }
                }
            }
        }
        else if(command.equals("ipv6"))
        {
            for(int i = 0; i < comps.length; i++)
            {
                if(comps[i].getName().equals(currentComp))
                {
                    String ipv6;
                    try
                    {
                        ipv6 = scan.next();

                        comps[i].setipv6(ipv6);
                        str = "Configuration complete for device: " + currentComp;
                    }
                    catch(Exception e)
                    {
                        str = "Invalid arguments.\nCorrect use: ipv6 <Addr>";
                    }
                }
            }
        }
        else if(command.equals("gateway"))
        {
            for(int i = 0; i < comps.length; i++)
            {
                if(comps[i].getName().equals(currentComp))
                {
                    String gate;
                    try
                    {
                        gate = scan.next();
                        comps[i].setGateway(gate);
                        str = "Configuration complete for device: " + currentComp;
                    }
                    catch(Exception e)
                    {
                        str = "Invalid arguments.\nCorrect use: gateway <Addr>";
                    }
                }
            }
        }
        else if(command.equals("ipconfig"))
        {
            for(int i = 0; i < comps.length; i++)
            {
                if(comps[i].getName().equals(currentComp))
                {
                    str = "Device name: " + comps[i].getName() +
                            "\nIPV4: " + comps[i].getip() +
                            "\nIPV6: " + comps[i].getipv6() +
                            "\nSubnet Mask: " + comps[i].getMask() +
                            "\nDefault Gateway: " + comps[i].getGateway();
                }
            }
        }
        else
        {
            str = "'" + command + "' is not recognized as an internal\nor external command";
        }
    }

    public String getIP(String device)
    {
        return comps[1].getip();
    }
}