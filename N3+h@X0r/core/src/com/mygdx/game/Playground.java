package com.mygdx.game;

import com.badlogic.gdx.ApplicationListener;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.maps.MapLayers;
import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

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
	private Texture standing;

	private float x = 8;
	private float y = 1;

	public Playground(NetworkingGame game){
		this.game = game;
	}

	@Override
	public void show() {
		standing = new Texture("core/assets/CharSelectPics/C1_WalkDown2.png");
		if(showOnce == false)
		{
			map = new TmxMapLoader().load("core/assets/OfficeRoom.tmx");
		}
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

		player = new Player(new Sprite(standing), platformingLayer);
		player.setBounds(0, 0, 16, 16);
		player.setPosition(x, y);

		camera = new OrthographicCamera();
	}

	@Override
	public void render (float delta) {
		Gdx.gl.glClearColor(0,0,0,1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		camera.position.x = 120;
		camera.position.y = 120;
		camera.zoom = 2/5f;
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


		x = player.updateCoordX(x);
		y = player.updateCoordY(y);
		player.setCollisionLayer(platformingLayer);
		player.setPosition(x, y);

		System.out.println("x: " + x);
		System.out.println("y: " + y);

		camera.update();
		renderer.setView(camera);
		renderer.render(decorationLayers);
		renderer.getBatch().begin();
		renderer.renderTileLayer(platformingLayer);
		player.update(delta);
		player.draw(renderer.getBatch());
		renderer.getBatch().end();
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
		map.dispose();
		renderer.dispose();
	}
	public double getID(){
		return id;
	}
}