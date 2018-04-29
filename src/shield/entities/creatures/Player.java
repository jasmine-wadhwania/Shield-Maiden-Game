package shield.entities.creatures;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.HashMap;

import Audio.AudioPlayer;
import TileMap.TileMap;
import shield.entities.Animation;
import shield.entities.Coins;
import shield.entities.Enemy;
import shield.entities.FireBall;
import shield.entities.Heart;
import shield.entities.MapObject;
import shield.entities.keys;
import shield.gfx.Assets;

public class Player extends MapObject {
	private HashMap<String, AudioPlayer> sfx;
	// player stuff
	private static int health;
	private int maxHealth;
	private int magic;
	private int maxMagic;
	private boolean dead;
	private boolean flinching;
	private long flinchTimer;

	// fire ball
	private boolean firing;
	private int magicCost;
	private int MAGICBALLDamage;
	private ArrayList<FireBall> magicBalls;

	// scratch
	private boolean scratching;
	private int scratchDamage;
	private int scratchRange;

	// gliding
	private boolean gliding;

	// animations
	private ArrayList<BufferedImage[]> sprites;
	private final int[] numFrames = { 3, 4, 5, 1, 2, 2, 1 };
	private int Key_Collection = 0;
	private static int Coin_Collection = 0;
	private static int Heart_Collection = 0;

	// animation actions
	private static final int IDLE = 0;
	private static final int WALKING = 1;
	private static final int JUMPING = 2;
	private static final int FALLING = 3;
	private static final int GLIDING = 4;
	private static final int MAGICBALL = 5;
	private static final int SHIELDING = 6;

	public Player(TileMap tm) {

		super(tm);

		width = 71;
		height = 106;
		cwidth = 30;
		cheight = 20;

		moveSpeed = 3.4;
		maxSpeed = 6.0;
		stopSpeed = 0.9;
		fallSpeed = 0.15; //0.15
		maxFallSpeed = 4.0; //4.0
		jumpStart = -7.6; //-7.6
		stopJumpSpeed = 0.3; //0.3

		facingRight = true;

		health = maxHealth = 5;
		magic = maxMagic = 2500;

		magicCost = 200;
		MAGICBALLDamage = 5;
		magicBalls = new ArrayList<FireBall>();

		scratchDamage = 8;
		scratchRange = 40;
		sfx = new HashMap<String, AudioPlayer>();
		sfx.put("coin", new AudioPlayer("/worlds/coin.mp3"));
		sfx.put("jump", new AudioPlayer("/worlds/Cartoon Hop-SoundBible.com-553158131.mp3"));
		sfx.put("diamond", new AudioPlayer("/worlds/diamond.mp3"));
		sfx.put("heart", new AudioPlayer("/worlds/diamond.mp3"));
		sfx.put("attack", new AudioPlayer("/worlds/girlattack1.mp3"));
		// load sprites
		Assets.init();

		animation = new Animation();
		currentAction = IDLE;
		animation.setFrames(Assets.player_idle);
		animation.setDelay(400);

	}

	public int getHealth() {
		return health;
	}

	public int getMaxHealth() {
		return maxHealth;
	}

	public int getMagic() {
		return magic;
	}

	public int getMaxMagic() {
		return maxMagic;
	}

	public int getKeyCollection() {
		return Key_Collection;
	}

	public static int getCoinCollection() {
		return Coin_Collection;
	}

	public void setHealth() {
		health = health - 1;
	}

	public void setHealthh() {
		health = health + 1;
	}

	public void setFiring() {
		firing = true;
	}

	public void setScratching() {
		scratching = true;
	}

	public void setGliding(boolean b) {
		gliding = b;
	}

	private void getNextPosition() {

		// movement
		if (left) {
			dx -= moveSpeed;
			if (dx < -maxSpeed) {
				dx = -maxSpeed;
			}
		} else if (right) {
			dx += moveSpeed;
			if (dx > maxSpeed) {
				dx = maxSpeed;
			}
		} else {
			if (dx > 0) {
				dx -= stopSpeed;
				if (dx < 0) {
					dx = 0;
				}
			} else if (dx < 0) {
				dx += stopSpeed;
				if (dx > 0) {
					dx = 0;
				}
			}
		}

		// cannot move while attacking, except in air
		if ((currentAction == SHIELDING || currentAction == MAGICBALL) && !(jumping || falling)) {
			dx = 0;
		}

		// jumping
		if (jumping && !falling) {
			dy = jumpStart;
			sfx.get("jump").play();
			falling = true;
		}

		// falling
		if (falling) {

			if (dy > 0 && gliding)
				dy += fallSpeed * 0.1;
			else
				dy += fallSpeed;

			if (dy > 0)
				jumping = false;
			if (dy < 0 && !jumping)
				dy += stopJumpSpeed;

			if (dy > maxFallSpeed)
				dy = maxFallSpeed;

		}

	}

	public void update() {

		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		// check if attack has stopped
		if (currentAction == MAGICBALL) {
			if (animation.hasPlayedOnce())
				firing = false;
		}
		// magicball attack
		magic += 1;
		if (magic > maxMagic)
			magic = maxMagic;
		if (magic < 0)
			magic = 0;
		if (firing && currentAction != MAGICBALL) {
			if (magic > magicCost) {
				magic -= magicCost;
				FireBall mb = new FireBall(tileMap, facingRight);
				mb.setPosition(x, y);
				// sfx.get("attack").play();
				magicBalls.add(mb);
			}
		}
		// update magicballs
		for (int i = 0; i < magicBalls.size(); i++) {
			magicBalls.get(i).update();
			if (magicBalls.get(i).shouldRemove()) {
				magicBalls.remove(i);
				i--;
			}
		}
		// check if flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 100000;
			if (elapsed > 1000)
				flinching = false;
		}
		// set animation
		if (shielding) {
			if (currentAction != SHIELDING) {
				currentAction = SHIELDING;
				animation.setFrames(Assets.player_shield);
				animation.setDelay(50);
				width = 71;
			}
		} else if (firing) {
			if (currentAction != MAGICBALL) {
				currentAction = MAGICBALL;
				animation.setFrames(Assets.player_attack);
				animation.setDelay(100);
				width = 71;
			}
		} else if (dy > 0) {
			if (gliding) {
				if (currentAction != GLIDING) {
					currentAction = GLIDING;
					animation.setFrames(Assets.player_jump);
					animation.setDelay(100);
					width = 71;
				}
			} else if (currentAction != FALLING) {
				currentAction = FALLING;
				animation.setFrames(Assets.player_fall);
				animation.setDelay(50);
				width = 71;
			}
		} else if (dy < 0) {
			if (currentAction != JUMPING) {
				currentAction = JUMPING;
				animation.setFrames(Assets.player_jump);
				animation.setDelay(300);
				width = 71;
			}
		} else if (left || right) {
			if (currentAction != WALKING) {
				currentAction = WALKING;
				animation.setFrames(Assets.player_rightwalk);
				animation.setDelay(80);
				width = 71;
			}
		} else {
			if (currentAction != IDLE) {
				currentAction = IDLE;
				animation.setFrames(Assets.player_idle);
				animation.setDelay(400);
				width = 71;
			}
		}

		animation.update();

		// set direction
		if (currentAction != MAGICBALL) {
			if (right)
				facingRight = true;
			if (left)
				facingRight = false;
		}

	}

	public void draw(Graphics g) {

		setMapPosition();
		// draw magicballs
		for (int i = 0; i < magicBalls.size(); i++)
			magicBalls.get(i).draw(g);

		// draw player
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed / 100 % 2 == 0) {
				return;
			}
		}

		if (facingRight) {
			g.drawImage(animation.getImage(), (int) (x + xmap - width / 2), (int) (y + ymap - height / 2), null);
		} else {
			g.drawImage(animation.getImage(), (int) (x + xmap - width / 2 + width), (int) (y + ymap - height / 2),
					-width, height, null);

		}

	}

	public void hit(int damage) {

		if (flinching) {
			return;
		}
		health -= damage;
		if (health < 0)
			health = 0;
		if (health == 0)
			dead = true;
		flinching = true;
		flinchTimer = System.nanoTime();
	}

	public void checkAttack(ArrayList<Enemy> enemies) {
		// loop through enemeies
		for (int i = 0; i < enemies.size(); i++) {
			Enemy e = enemies.get(i);
			for (int j = 0; j < magicBalls.size(); j++)
				if (magicBalls.get(j).intersects(e)) {
					e.hit(MAGICBALLDamage);
					magicBalls.get(j).setHit();
					break;
				}

			// check enemy collision
			if (intersects(e))
				if (!((e.facingRight && !facingRight && shielding) || (!e.facingRight && facingRight && shielding))) {
					hit(e.damage);
					setPosition(100, 200);
				}

		}
	}

	public void checkKey(ArrayList<keys> key) {
		for (int i = 0; i < key.size(); i++) {
			keys k = key.get(i);
			if (intersects(k)) {
				Key_Collection += 1;
				sfx.get("diamond").play();
				k.remove = true;
			}

		}
	}

	public void checkHeart(ArrayList<Heart> heart) {
		for (int i = 0; i < heart.size(); i++) {
			Heart h = heart.get(i);
			if (intersects(h)) {
				if (getHealth() != 5) {
					setHealthh();
				}
				Heart_Collection += 1;
				sfx.get("diamond").play();
				h.remove = true;
			}

		}
	}

	public void checkCoin(ArrayList<Coins> coin) {
		for (int i = 0; i < coin.size(); i++) {
			Coins c = coin.get(i);
			if (intersects(c)) {
				Coin_Collection += 1;
				sfx.get("coin").play();
				c.remove = true;
			}

		}
	}

}