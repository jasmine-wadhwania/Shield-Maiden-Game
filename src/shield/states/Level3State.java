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

public class Level3State extends state {
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

	public Level3State(game Game) {
		super(Game);
		init();
	}

	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/textures/icandoit.png");
		tileMap.loadMap("/textures/world3"); // world3
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		bg = new Background("/textures/level 3 with no face.jpg", 0);
		player = new Player(tileMap);
		player.setPosition(200, 200);
		door = new Door(tileMap);
		door.setPosition(13350, 270);
		populateEnemies();
		populateKeys();
		populateCoins();
		populateHeart();
		hud = new HUD(player);
		explosions = new ArrayList<Explosion>();
		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("end", new AudioPlayer("/worlds/Monster Growl-SoundBible.com-344645592.mp3"));
		sfx.put("happy", new AudioPlayer("/worlds/Rabba Ve cut.mp3"));
		bgMusic = new AudioPlayer("/textures/spookybgfrl3.mp3");
		bgMusic.play();
	}

	private void populateEnemies() { // nf at new Point(5550, 100), new
										// Point(5800, 400), new Point(6600,
										// 400),
		// new Point(7050, 70), new Point(7650, 500), new Point(7950, 400), new
		// Point(11650, 500),
		enemies = new ArrayList<Enemy>();

		Slugger s;
		Witch w;
		Point[] points = new Point[] { new Point(800, 300), new Point(2450, 400), new Point(2800, 400),
				new Point(2650, 400), new Point(3925, 200), new Point(4100, 210), new Point(6300, 70),
				new Point(6600, 70), new Point(5100, 500), new Point(5250, 500), new Point(6950, 90),
				new Point(7700, 390), new Point(7850, 360), new Point(7600, 120), new Point(8800, 120),
				new Point(9025, 120), new Point(8975, 120), new Point(11400, 550), new Point(11550, 550),

		};
		for (int i = 0; i < points.length; i++) {
			s = new Slugger(tileMap);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		} // meno face (
	}

	private void populateKeys() {
		key = new ArrayList<keys>();

		keys k;
		Point[] kpoints = new Point[] { new Point(7625, 575), new Point(11300, 250), new Point(9215, 50),

		};
		for (int i = 0; i < kpoints.length; i++) {
			k = new keys(tileMap);
			k.setPosition(kpoints[i].x, kpoints[i].y);
			key.add(k);
		}
	}

	private void populateHeart() {
		heart = new ArrayList<Heart>();

		Heart h;
		Point[] hpoints = new Point[] { new Point(5125, 600), };
		for (int i = 0; i < hpoints.length; i++) {
			h = new Heart(tileMap);
			h.setPosition(hpoints[i].x, hpoints[i].y);
			heart.add(h);
		}

	}

	private void populateCoins() {
		coin = new ArrayList<Coins>();

		Coins c;
		Point[] cpoints = new Point[] { new Point(700, 500), new Point(775, 500), new Point(850, 500),
				new Point(925, 500), new Point(1025, 400), // first part over
				// stepping stones
				/* lower step */ new Point(1220, 400), /* middle step */ new Point(1400, 230),
				/* top step */ new Point(1600, 50), new Point(1650, 50),
				/* getting down */new Point(1820, 230), new Point(2050, 400), new Point(1600, 400),
				new Point(1650, 400), new Point(1400, 600), new Point(1820, 600),

				new Point(2300, 500), new Point(2350, 500), new Point(3150, 500), new Point(3200, 500),
				new Point(3400, 350),

				new Point(3600, 230), new Point(3675, 230), new Point(3750, 230), new Point(3825, 230), // 21
				new Point(3925, 60), new Point(4000, 60), new Point(4075, 60), new Point(4150, 60), new Point(4225, 60),
				new Point(3925, 180), new Point(4000, 180), new Point(4075, 180), new Point(4150, 180),
				new Point(4225, 180), new Point(4350, 230), new Point(4425, 230), new Point(4500, 230),
				new Point(4575, 230), // 30

				new Point(4950, 150), new Point(5000, 150), new Point(5650, 150), new Point(5700, 150),

				new Point(5925, 150), // single step

				new Point(5050, 550), new Point(5625, 550), new Point(5575, 590), // lower
																					// platform
																					// dhaggara
																					// step
																					// type
																					// partition

				new Point(6000, 425), // single step down

				new Point(6150, 100), new Point(6100, 100), new Point(6800, 100), new Point(6850, 70),

				new Point(9025, 150), new Point(9100, 150), new Point(9175, 150), new Point(9215, 100),

				new Point(6375, 440), new Point(6300, 440), // middle layer
				new Point(6450, 440), new Point(6525, 440), // 50
				new Point(6600, 440), new Point(6675, 440), new Point(6750, 440), new Point(6825, 440),

				new Point(7675, 575), new Point(7625, 625), new Point(7675, 625),

				new Point(7125, 390), new Point(7200, 390), new Point(7275, 390), new Point(7350, 390),

				new Point(8700, 400), new Point(9000, 500), new Point(9240, 380),

				new Point(10455, 60), new Point(10020, 60), new Point(9525, 380), new Point(9725, 520),
				new Point(9925, 600), new Point(10410, 600), new Point(10580, 500), new Point(10690, 200),
				new Point(10425, 350), new Point(10975, 600), new Point(11200, 250),

				new Point(12550, 350), new Point(12600, 350), new Point(12720, 630), new Point(12720, 580),
				new Point(12900, 300), new Point(13150, 250), new Point(13200, 250),
				new Point(13100, 250), new Point(13150, 250), new Point(13050, 250), new Point(13000, 250),
				new Point(12900, 250), new Point(12950, 250), new Point(13350, 200), new Point(13300, 200),
				new Point(13250, 200), new Point(13200, 200), };
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
		player.checkAttack(enemies);
		player.checkKey(key);
		player.checkCoin(coin);
		player.checkHeart(heart);
		if (player.gety() > 650 && player.getHealth() > 0) {
			player.setHealth();
			player.setPosition(200, 200);
		}
		
		if (player.getx() < 13500 && player.getx() > 13300 && player.gety() > 260 && player.gety() < 350 /*&& player.getKeyCollection() == 3 */) {
			sfx.get("happy").play();
			state.setState(Game.happilyeverafterstate);  
		}

		if (player.getHealth() == 0) {
			sfx.get("end").play();
			state.setState(Game.gameoverstate);
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

		for (int i = 0; i < heart.size(); i++) {
			heart.get(i).draw(g);

		}

		for (int i = 0; i < coin.size(); i++) {
			coin.get(i).draw(g);

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