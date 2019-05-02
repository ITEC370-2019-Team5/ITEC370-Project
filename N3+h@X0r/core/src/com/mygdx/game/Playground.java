package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;

import java.io.File;
import java.text.Bidi;
import java.util.ArrayList;
import java.util.Scanner;

public class Playground implements Screen , ApplicationListener {
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
	private NPC boss;
	private Texture standing, itemTexture;
	private Item testItem, hintItem, leftDoor, topDoor, thirstItem1, hungerItem1, thirstItem2, hungerItem2;
	private ArrayList<Item> itemList = new ArrayList<Item>();
	private boolean pressingEnter;
	private Texture prevTexture;
	private Inventory inventory = new Inventory(game);

	private int bossIndex = 0;

	private BitmapFont hungerThirst;
	int thirstCount = 60;
	int hungerCount = 60;
	int timer = 0;

	private float x = 8;
	private float y = 1;

	private float itemX = 4;
	private float itemY = 1;

	private ArrayList<Integer> itemListX = new ArrayList<Integer>();
	private ArrayList<Integer> itemListY = new ArrayList<Integer>();

	private int upTime = 0;
	private int downTime = 0;
	private int leftTime = 0;
	private int rightTime = 0;
	private int charSelect;

	public Playground(NetworkingGame game){
		this.game = game;
	}


	@Override
	public void show() {

		if(showOnce == false)
		{
			prevTexture = new Texture("core/assets/CharSelectPics/C" + (charSelect + 1) + "_WalkDown2.png");
			map = new TmxMapLoader().load("core/assets/OfficeRoom.tmx");

			itemListX.add(12);
			itemListY.add(3);

			itemListX.add(7);
			itemListY.add(3);

			itemListX.add(9); //thirst item 1 x
			itemListY.add(4); // thirst item 1 y

			itemListX.add(16); //thirst item 2 x
			itemListY.add(11); // thirst item 2 y

			itemListX.add(19); //hunger item 1 x
			itemListY.add(9); // hunger item 1 y

			itemListX.add(17); //hunger item 2 x
			itemListY.add(1); // hunger item 2 y
		}

		hungerThirst = new BitmapFont();
		hungerThirst.setColor(Color.RED);


		testItem = new Item(new Sprite(new Texture("core/assets/test_item.png")), 'I',
				"Test Item", "core/assets/test_item.png", 1, platformingLayer, itemListX.get(0), itemListY.get(0));
		hintItem = new Item(new Sprite(new Texture("core/assets/sticky_note.png")), 'H',
				"Hint 1", "core/assets/sticky_note.png", 1, platformingLayer, itemListX.get(1), itemListY.get(1),
				"Private IP ranges:\n" +
						"  192.168.0.0 - 192.168.255.255\n" +
						"  172.16.0.0 - 172.31.255.255\n" +
						"  10.0.0.0 - 10.255.255.255\n");

		//2 doors on the map
		leftDoor = new Item('D', platformingLayer, 0.0f, 2.0f, 1);
		topDoor = new Item('D', platformingLayer, 3.0f, 12.0f, 2);

		thirstItem1 = new Item(new Sprite(new Texture("core/assets/thrist_item.png")), 'T',
				"Thirst 1", "core/assets/thrist_item.png",1,  platformingLayer,
				itemListX.get(2), itemListY.get(2));

		thirstItem2 = new Item(new Sprite(new Texture("core/assets/thrist_item.png")), 'T',
				"Thirst 2", "core/assets/thrist_item.png", 1, platformingLayer,
				itemListX.get(3), itemListY.get(3));

		hungerItem1 = new Item(new Sprite(new Texture("core/assets/hunger_item.png")), 'G',
				"Hunger 1", "core/assets/hunger_item.png", 1, platformingLayer,
				itemListX.get(4), itemListY.get(4));

		hungerItem2 = new Item(new Sprite(new Texture("core/assets/hunger_item.png")), 'G',
				"Hunger 2", "core/assets/hunger_item.png", 1, platformingLayer,
				itemListX.get(5), itemListY.get(5));


		itemList.add(testItem);
		itemList.add(hintItem);
		itemList.add(leftDoor);
		itemList.add(topDoor);

		itemList.add(thirstItem1);
		itemList.add(thirstItem2);
		itemList.add(hungerItem1);
		itemList.add(hungerItem2);

		itemListX.clear();
		itemListY.clear();

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

		String[] diag = {"Oh good, you finally made it!\n" +
				"Now before we get started, let's see\n" +
				"if you know the basics",
							"Which of the following ip's are\n" +
							"within the private IP range?\n" +
									"A: 192.167.4.3\n" +
									"B: 172.16.94.217\n" +
									"C: 154.3.43.196",
				"Great, our computers use IP addresses 10.1.1.X\nThe 'X' represents 2-255.",
				"Our ten computers are named 'A' - 'J'",
						"Great now head over to your computer and set\ndevice B's ip to 10.1.1.12. Make sure the\nmask remains 255.255.255.0.",
						"Great work, now make yourself at home.\nGo check out the server room.\nIt's the room on the right."};

		char[] types = {'d', 'q', 'd', 'd', 'q'};
		String[] answers = {"b", "IP"};
		String[] ip = {"10.1.1.12"};
		String[] mask = {"255.255.255.0"};

		boss = new NPC(new Sprite(new Texture("core/assets/Boss_StandDown.png")), platformingLayer, diag, types, answers, ip, mask);
		boss.setBounds(0,0,16,16);
		boss.setPosition(3 * 16, 2 * 16);


		boss.setDialogue(bossIndex);

		//sets all items to 16x16 pixels to fit the boundaries of the map
		for(int i = 0;i < itemList.size(); i++) {
			itemList.get(i).setBounds(itemList.get(i).updateCoordX(), itemList.get(i).updateCoordY(), 16, 16);
		}

		//Setting the items
		for(int i = 0; i < itemList.size(); i++)
		{
			itemX = itemList.get(i).getX();
			itemY = itemList.get(i).getY();
			itemList.get(i).setPosition(itemX, itemY);
		}

		itemX = itemList.get(0).getX();
		itemY = itemList.get(0).getY();

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

		map = new TmxMapLoader().load("core/assets/OfficeRoom.tmx");
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

		//setting the pixel width and height of each item
		for(int i = 0;i < itemList.size(); i++) {
			char theType = itemList.get(i).getType();
			if(theType == 'H') {
				itemList.get(i).setBounds(itemList.get(i).getX(), itemList.get(i).getY(), 6, 6);
			}
			else if(theType == 'I' || theType == 'T' || theType == 'G') {
				itemList.get(i).setBounds(itemList.get(i).getX(), itemList.get(i).getY(), 16, 16);
			}
		}

		//giant if statement when you press ENTER at all throughout the playground
		if(pressingEnter) {
			float playerXCoord = player.getX();
			float playerYCoord = player.getY();

			for(int i = 0;i < itemList.size(); i++) {
				itemX = itemList.get(i).getX();
				itemY = itemList.get(i).getY();

				//doors
				if(itemList.get(i).getType() == 'D')
				{
					//Left door
					if(itemList.get(i).getID() == 1) {
						if (playerXCoord > ((itemX * 16) - 10) && playerXCoord < ((itemX * 16) + 10) &&
								playerYCoord > ((itemY * 16) - 24) && playerYCoord < ((itemY * 16) + 24)) {

							if(game.keycardFound(0))
							{
								game.changeScreen(11);
							}
							else
							{
								game.changeStr("You need a key card to access this door.", 'h');
								game.changeScreen(6);
							}
						}
					}
				}

				//items and hints
				else {
					if (playerXCoord > ((itemX * 16) - 10) && playerXCoord < ((itemX * 16) + 10) &&
							playerYCoord > ((itemY * 16) - 10) && playerYCoord < ((itemY * 16) + 10)) {
						//hints
						if (itemList.get(i).getType() == 'H') {

							game.changeStr(itemList.get(i).getHint(), 'h');
							game.changeScreen(6);
						}
						//items, thirst and hunger included.
						else {
							if(itemList.get(i).getType() == 'I') {
								game.addToInv(new Sprite(new Texture(itemList.get(i).getLocation())));
							}
							itemList.get(i).setX(itemList.get(i).updateCoordX() * -1);
							itemList.get(i).setY(itemList.get(i).updateCoordY() * -1);

							//if a hunger or thirst item, increment counter by 20
							if(itemList.get(i).getType() == 'G') {
								hungerCount = hungerCount + 20;
							}
							if(itemList.get(i).getType() == 'T') {
								thirstCount = thirstCount + 20;
							}
						}
					}
				}
			}
			//Boss Dialogue
			if(player.getX() > (boss.getX() - 20) && player.getX() < (boss.getX() + 4) &&
					player.getY() > (boss.getY() - 20) && player.getY() < (boss.getY() + 4)){

				game.changeStr(boss.getDialogue(boss.getDiagSoFar()), 'b');
				game.changeScreen(6);
			}

			if (playerXCoord > ((9 * 16) - 10) && playerXCoord < ((9 * 16) + 10) &&
					playerYCoord > ((8 * 16) - 10) && playerYCoord < ((8 * 16) + 10))
			{
				game.changeScreen(7);
			}
		}

		timer++;

		if(timer == 60)
		{
			hungerCount--;
			thirstCount--;
			timer = 0;
		}

		//Setting the player
		x = player.updateCoordX(x);
		y = player.updateCoordY(y);
		player.setCollisionLayer(platformingLayer);
		player.setPosition(x, y);

		boss.setPosition(3 * 16, 2 * 16);

		//Setting the items
		for(int i = 0; i < itemList.size(); i++)
		{
			itemX = itemList.get(i).getX();
			itemY = itemList.get(i).getY();
			if(itemList.get(i).getType() != 'D') {
				itemList.get(i).setPosition(itemX * platformingLayer.getTileWidth(), itemY * platformingLayer.getTileHeight());
			}
		}

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

		for( int i = 0; i < itemList.size(); i++) {
			if(itemList.get(i).getType() != 'D') {
				itemList.get(i).update(delta);
				itemList.get(i).draw(renderer.getBatch());

				itemListX.add((int) itemList.get(i).getX() / 16);
				itemListY.add((int) itemList.get(i).getY() / 16);
			}
		}

		player.update(delta);
		prevTexture = new Texture(player.prevTexture());
		boss.update(delta);

		upTime = player.getUpTime();
		downTime = player.getDownTime();
		leftTime = player.getLeftTime();
		rightTime = player.getRightTime();

		hungerThirst.getData().setScale(.6f, .6f);
		hungerThirst.draw(renderer.getBatch(), "Thirst:  " + thirstCount + "  \t Hunger:  " + hungerCount + "", 100, 15);

		player.draw(renderer.getBatch());
		boss.draw(renderer.getBatch());
		renderer.getBatch().end();

		itemList.clear();
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
		boss.getTexture().dispose();
		map.dispose();
		testItem.getTexture().dispose();
		renderer.dispose();
	}
	public double getID(){
		return id;
	}

	public void updateChar(int num)
	{
		charSelect = num;
	}

	public String getNextDialogue()
	{
		boss.setdiagSoFar();
		return boss.getDialogue(boss.getDiagSoFar());
	}
	public char getNextDiagType()
	{
		return boss.getDiagType();
	}
	public String getAnswer()
	{
		return boss.getAnswer();
	}
	public void incAnsIndex()
	{
		boss.incAnsIndex();
	}
	public String getIP()
	{
		return boss.getIP();
	}
	public void setIndex(int index)
	{
		bossIndex = index;
	}
	public int getDiagSoFar()
	{
		return boss.getDiagSoFar();
	}
	public void loadFile(String file)
	{
		File tempfile = new File("core/assets/" + file);
		if(tempfile.exists())
		{
			System.out.println("Loading file");
			try
			{
				Scanner scan = new Scanner(new File("core/assets/" + file));

				bossIndex = scan.nextInt();
			}
			catch(Exception e)
			{
				System.out.println("File not found, not loaded");
			}
		}
		else
		{
			System.out.println("File not found");
		}
	}
}