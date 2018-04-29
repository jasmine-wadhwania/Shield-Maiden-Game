package shield.states;

import java.awt.Graphics;
import java.awt.Point;
import java.util.ArrayList;
import java.util.HashMap;

import Audio.AudioPlayer;
import TileMap.Background;
import TileMap.TileMap;
import shield.display;
import shield.game;
import shield.entities.Coins;
import shield.entities.Door;
import shield.entities.Enemy;
import shield.entities.Explosion;
import shield.entities.HUD;
import shield.entities.Heart;
import shield.entities.Slugger;
import shield.entities.Witch;
import shield.entities.keys;
import shield.entities.creatures.Player;

public class Level2State extends state {
	private ArrayList<keys> key;
	private ArrayList<Coins> coin;
	private ArrayList<Heart> heart;
	private Door door;
	private TileMap tileMap;
	private Background bg;
	private Player player;
	private AudioPlayer bgMusic;
	private HashMap<String, AudioPlayer> sfx;
	private ArrayList<Enemy> enemies;
	private ArrayList<Explosion> explosions;
	private HUD hud;
	
	
	public Level2State(game Game) {
		super(Game);
		init();
	}

	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/textures/entieejeevithamwithoutbg.png");
		tileMap.loadMap("/textures/world2");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		bg = new Background("/textures/level 2 with medusa resized with water.jpg", 0);
		player = new Player(tileMap);
		door = new Door(tileMap);
		player.setPosition(200, 200);
		door.setPosition(12650, 120);
		populateEnemies();
		populateKeys();
		populateCoins();
		populateHeart();
		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("level", new AudioPlayer("/worlds/Winning-sound-effect.mp3"));
		sfx.put("end", new AudioPlayer("/worlds/Monster Growl-SoundBible.com-344645592.mp3"));
		explosions = new ArrayList<Explosion>();
		hud = new HUD(player);
		bgMusic = new AudioPlayer("/textures/spookybgfrl3.mp3");
		bgMusic.play();
	}

	private void populateEnemies() { // medusa at (1325,500) (2000,500) (10900,
										// 300), (12950,450)

		enemies = new ArrayList<Enemy>();

		Slugger s;
		Witch w;
		Point[] points = new Point[] { new Point(1600, 230), new Point(3900, 230), new Point(4600, 200),
				new Point(4750, 200), new Point(5500, 500), new Point(6275, 200), new Point(8100, 150),
				new Point(9850, 200), new Point(10000, 200), new Point(13300, 300),

		};
		for (int i = 0; i < points.length; i++) {
			s = new Slugger(tileMap);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		}
	}

	private void populateKeys() {
		key = new ArrayList<keys>();

		keys k;
		Point[] kpoints = new Point[] { new Point(7275, 180), new Point(12225, 600), new Point(5475, 250), };
		for (int i = 0; i < kpoints.length; i++) {
			k = new keys(tileMap);
			k.setPosition(kpoints[i].x, kpoints[i].y);
			key.add(k);
		}

	}
	
	private void populateHeart() {
		heart = new ArrayList<Heart>();

		Heart h;
		Point[] hpoints = new Point[] { new Point(6825, 525), new Point(12000,600), };
		for (int i = 0; i < hpoints.length; i++) {
			h = new Heart(tileMap);
			h.setPosition(hpoints[i].x, hpoints[i].y);
			heart.add(h);
		}

	}

	private void populateCoins() {
		coin = new ArrayList<Coins>();

		Coins c;
		Point[] cpoints = new Point[] { new Point(700, 500), new Point(775, 400), new Point(850, 400),
				new Point(925, 300), new Point(1000, 300), // first part over
				new Point(1220, 180), new Point(1270, 140), new Point(1320, 180), // upper
																					// platform
				new Point(1370, 140), new Point(1420, 180), new Point(1470, 140), new Point(1520, 230),
				new Point(1570, 180), // near snail
				new Point(1620, 230), new Point(1670, 180), new Point(1720, 230), new Point(1770, 180),
				new Point(1820, 230), new Point(1870, 180), new Point(1920, 130), new Point(1970, 180),
				new Point(2020, 130), new Point(2070, 180), new Point(2120, 130), new Point(2170, 180),
				new Point(2220, 130), new Point(1450, 400), new Point(1500, 400), new Point(1550, 400),
				new Point(1600, 400), // just below upper platform

				new Point(2450, 500), new Point(2500, 450), new Point(2550, 500), new Point(3180, 570), // SINGLE
																										// STEP
																										// NEAR
																										// DIVING
																										// PATH
				new Point(3400, 570), new Point(3400, 520), new Point(3400, 470), // downpath
				new Point(3450, 570), new Point(3450, 520), new Point(3450, 470), new Point(3500, 570),
				new Point(3500, 520), new Point(3500, 470), new Point(3550, 570), new Point(3550, 520),
				new Point(3550, 470), new Point(3850, 450), new Point(3850, 550), new Point(3900, 500),
				new Point(3900, 600), new Point(3950, 550), new Point(3950, 450), new Point(4000, 500),
				new Point(4000, 600), new Point(4050, 550), new Point(4050, 450), // 56
				new Point(5925, 550), new Point(5975, 500), new Point(6025, 550), new Point(6275, 500),
				new Point(6325, 475), new Point(6375, 500), new Point(6425, 475), new Point(6750, 500),
				new Point(6800, 475), new Point(6850, 500), new Point(6900, 475),
				// upward path
				new Point(4550, 210), new Point(4600, 210), new Point(4650, 210), new Point(4700, 210),

				new Point(6725, 250), new Point(5650, 150), new Point(5725, 100), new Point(5800, 150),
				new Point(5875, 100), new Point(5925, 150), new Point(6000, 100), new Point(6075, 150),
				new Point(6150, 100), new Point(6225, 150), new Point(6800, 180), new Point(6850, 130),
				new Point(6900, 180),

				new Point(8775, 550), new Point(8850, 400), new Point(8950, 400), new Point(9050, 400),

				new Point(9400, 250), new Point(9500, 250), new Point(9600, 250), new Point(9700, 250),
				new Point(9825, 300), new Point(9925, 300), new Point(10025, 300), new Point(10125, 300),
				new Point(10225, 300), new Point(10325, 300), new Point(11200, 250), new Point(11300, 250),

				new Point(12640, 220), new Point(12640, 220), new Point(12730, 100), new Point(12730, 100), };
		for (int i = 0; i < cpoints.length; i++) {
			c = new Coins(tileMap);
			c.setPosition(cpoints[i].x, cpoints[i].y);
			coin.add(c);
		}

	}

	@Override
	public void tick() {
		keyPressed();
		player.update();
		tileMap.setPosition(display.width / 2 - player.getx(), display.height / 2 - player.gety());
		// set background scroll
		bg.setPosition(tileMap.getx(), tileMap.gety());

		// check attack
		player.checkAttack(enemies);
		player.checkKey(key);
		player.checkCoin(coin);
		player.checkHeart(heart);
		if (player.gety() > 650 && player.getHealth() > 0) {
			player.setHealth();
			player.setPosition(200, 200);
		}
		if (player.getx() < 12750 && player.getx() > 12600 && player.gety() < 220 && player.gety() > 100
				&& player.getKeyCollection() == 3) {
			game.flag = 3;
			sfx.get("level").play();
			state.setState(Game.level3state);
		}

		// update all enemies
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if (e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(new Explosion(e.getx(), e.gety()));
			}
		}
		// update explosions
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).update();
			if (explosions.get(i).shouldRemove()) {
				explosions.remove(i);
				i--;
			}
		}
		for (int i = 0; i < key.size(); i++) {
			key.get(i).update();
			if (key.get(i).shouldRemove()) {
				key.remove(i);
				i--;
			}
		}
		for (int i = 0; i < heart.size(); i++) {
			heart.get(i).update();
			if (heart.get(i).shouldRemove()) {
				heart.remove(i);
				i--;
			}
		}
		for (int i = 0; i < coin.size(); i++) {
			coin.get(i).update();
			if (coin.get(i).shouldRemove()) {
				coin.remove(i);
				i--;
			}
		}
	}

	@Override
	public void render(Graphics g) {

		// draw background
		bg.draw(g);

		// draw tilemap
		tileMap.draw(g);

		// draw player
		player.draw(g);
		door.draw(g);

		// draw enemies
		for (int i = 0; i < enemies.size(); i++) {
			enemies.get(i).draw(g);
		}

		for (int i = 0; i < key.size(); i++) {
			key.get(i).draw(g);

		}
		for (int i = 0; i < coin.size(); i++) {
			coin.get(i).draw(g);

		}
		for (int i = 0; i < heart.size(); i++) {
			heart.get(i).draw(g);

		}
		// draw explosions
		for (int i = 0; i < explosions.size(); i++) {
			explosions.get(i).setMapPosition((int) tileMap.getx(), (int) tileMap.gety());
			explosions.get(i).draw(g);
		}

		// draw hud

		hud.draw(g);
	}

	public void keyPressed() {
		if (Game.getKeyManager().left) {
			player.setLeft(true);
		} else
			player.setLeft(false);

		if (Game.getKeyManager().right) {
			player.setRight(true);
		} else
			player.setRight(false);

		if (Game.getKeyManager().up) {
			player.setJumping(true);
		} else
			player.setJumping(false);
		if (Game.getKeyManager().down)
			player.setFiring();
		if (Game.getKeyManager().space)
			player.setShielding(true);
		else
			player.setShielding(false);

	}
}