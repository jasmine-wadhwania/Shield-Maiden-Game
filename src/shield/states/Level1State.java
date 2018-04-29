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
import shield.entities.Slugger;
import shield.entities.Witch;
import shield.entities.keys;
import shield.entities.creatures.Player;

public class Level1State extends state {
	private ArrayList<keys> key;
	private ArrayList<Coins> coin;
	private Door door;
	TileMap tileMap;
	private Background bg;
	private Player player;
	private HashMap<String, AudioPlayer> sfx;
	private ArrayList<Enemy> enemies;
	private ArrayList<Enemy> bossEnemies;
	private ArrayList<Explosion> explosions;
	private HUD hud;
	private AudioPlayer bgMusic;

	public Level1State(game Game) {
		super(Game);
		init();
	}

	public void init() {
		tileMap = new TileMap(30);
		tileMap.loadTiles("/textures/entoeejeevithamwith27.png");
		tileMap.loadMap("/textures/world1");
		tileMap.setPosition(0, 0);
		tileMap.setTween(1);
		bg = new Background("/textures/level 1 with witch lava.jpg", 0);
		player = new Player(tileMap);
		door = new Door(tileMap);
		player.setPosition(200, 200);
		door.setPosition(13400, 470);
		populateEnemies();
		populateKeys();
		populateCoins();
		populatebossEnemies();
		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("level", new AudioPlayer("/worlds/Winning-sound-effect.mp3"));
		sfx.put("end", new AudioPlayer("/worlds/Monster Growl-SoundBible.com-344645592.mp3"));
		explosions = new ArrayList<Explosion>();
		hud = new HUD(player);
		bgMusic = new AudioPlayer("/textures/spookybgfrl3.mp3");
		bgMusic.play();
	}

	private void populateEnemies() {

		enemies = new ArrayList<Enemy>();

		Slugger s;
		Point[] points = new Point[] { new Point(900, 600), new Point(1300, 600), new Point(3400, 600),
				new Point(4300, 600), new Point(5900, 550), };
		for (int i = 0; i < points.length; i++) {
			s = new Slugger(tileMap);
			s.setPosition(points[i].x, points[i].y);
			enemies.add(s);
		}
	}

	private void populatebossEnemies() {
		bossEnemies = new ArrayList<Enemy>();

		Witch w;
		Point[] wpoints = new Point[] { new Point(700, 500),

		};
		for (int i = 0; i < wpoints.length; i++) {
			w = new Witch(tileMap);
			w.setPosition(wpoints[i].x, wpoints[i].y);
			bossEnemies.add(w);
		}

	}

	private void populateKeys() {
		key = new ArrayList<keys>();

		keys k;
		Point[] kpoints = new Point[] { new Point(11650, 630), new Point(7880, 450), new Point(5150, 630) };
		for (int i = 0; i < kpoints.length; i++) {
			k = new keys(tileMap);
			k.setPosition(kpoints[i].x, kpoints[i].y);
			key.add(k);
		}

	}

	private void populateCoins() {
		coin = new ArrayList<Coins>();

		Coins c;
		Point[] cpoints = new Point[] { new Point(700, 500), new Point(750, 500), new Point(800, 500),
				new Point(850, 420), new Point(900, 420), new Point(950, 420), new Point(1000, 420),
				new Point(1050, 420), new Point(1100, 420), new Point(1300, 500), new Point(1350, 500),
				new Point(1400, 500), new Point(1450, 420), new Point(1500, 380), new Point(1550, 420),
				new Point(1600, 420), new Point(1650, 380), new Point(1700, 420), new Point(2000, 380),
				new Point(2050, 380), new Point(2100, 380), new Point(2500, 280), new Point(2550, 300),
				new Point(3000, 280), new Point(3050, 280), new Point(2950, 350), new Point(2990, 350),
				new Point(3500, 350), new Point(3550, 300), new Point(4000, 280), new Point(4500, 350),
				new Point(4550, 300), new Point(4600, 280), new Point(4650, 280), new Point(5000, 280),
				new Point(5050, 270), new Point(6000, 350), new Point(6050, 300), new Point(6100, 280),
				new Point(6200, 350), new Point(6250, 300), new Point(6300, 280), new Point(6850, 350),
				new Point(6900, 300), new Point(6950, 280), new Point(7400, 270), new Point(7450, 250),
				new Point(7500, 280), new Point(7550, 350), new Point(7600, 300), new Point(8000, 280),
				new Point(8050, 350), new Point(8100, 300), new Point(8200, 280), new Point(8700, 350),
				new Point(8750, 300), new Point(8800, 280), new Point(9200, 350), new Point(9250, 300),
				new Point(9300, 280), new Point(10000, 600), new Point(10050, 600), new Point(10100, 620),
				new Point(10000, 280), new Point(10050, 280), new Point(10100, 280), new Point(10950, 500),
				new Point(11150, 450), new Point(11200, 450), new Point(11250, 300), new Point(11300, 300),
				new Point(11350, 280), new Point(12250, 250), new Point(12300, 250), new Point(12350, 250),
				new Point(13250, 620), new Point(13300, 530), new Point(13350, 530), };
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
		player.checkAttack(bossEnemies);
		if (player.getx() < 13500 && player.getx() > 13390 && player.gety() < 550 && player.gety() > 460
				&& player.getKeyCollection() == 3) {
			game.flag = 2;
			sfx.get("level").play();
			state.setState(Game.level2state);
		}

		if (player.gety() > 650 && player.getHealth() > 0) {
			player.setHealth();
			player.setPosition(200, 200);
		}
		if (player.getHealth() == 0) {
			sfx.get("end").play();
			state.setState(Game.gameoverstate);

		} // update all enemies
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			e.update();
			if (e.isDead()) {
				enemies.remove(i);
				i--;
				explosions.add(new Explosion(e.getx(), e.gety()));
			}
		}
		
		for (int i = 0; i < bossEnemies.size(); i++) {
			Enemy e = bossEnemies.get(i);
			e.update();
			if (e.isDead()) {
				bossEnemies.remove(i);
				i--;
				explosions.add(new Explosion(e.getx(), e.gety()));
			}
			else
				bossEnemies.get(i).checkAttack(player);
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
		
		for (int i = 0; i < bossEnemies.size(); i++) {
			bossEnemies.get(i).draw(g);
		}

		for (int i = 0; i < key.size(); i++) {
			key.get(i).draw(g);

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