package shield.entities;

import java.awt.Graphics;
import java.util.ArrayList;

import TileMap.TileMap;
import shield.game;
import shield.entities.creatures.Player;
import shield.gfx.Assets;

public class Witch extends Enemy {

	// player stuff
	private static int health;

	private int magic;
	private int maxMagic;
	private boolean dead;
	public boolean flinching;
	private long flinchTimer;
	private EnemyBall enemyBall;
	// fireball

	private boolean firing;
	private int magicCost;
	private int MAGICBALLDamage;
	// private ArrayList<FireBall> magicBalls;
	private ArrayList<EnemyBall> magicBalls;
	// scratch

	// gliding

	// animations

	// animation actions

	private static final int MAGICBALL = 5;

	public Witch(TileMap tm) {

		super(tm);

		width = 110;
		height = 82;
		cwidth = 20;
		cheight = 20;
		health = maxHealth = 10;
		moveSpeed = 3.4;
		maxSpeed = 6.0;
		stopSpeed = 0.4;
		fallSpeed = 0.15;
		maxFallSpeed = 4.0;
		jumpStart = -7.6;
		stopJumpSpeed = 0.3;

		facingRight = true;

		magic = maxMagic = 201;

		magicCost = 200;
		MAGICBALLDamage = 1;
		magicBalls = new ArrayList<EnemyBall>();

		damage = 1;

		Assets.init();

		animation = new Animation();
		if (game.flag == 1)
			animation.setFrames(Assets.witch);
		else if (game.flag == 2)
			animation.setFrames(Assets.medusa);
		else if (game.flag == 3)
			animation.setFrames(Assets.noface);
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

	public void setFiring() {
		firing = true;
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

	}

	public void update() {

		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		// check if attack has stopped

		if (animation.hasPlayedOnce()) {
			System.out.println("111111111111111111111111111");
		}

		// magicball attack
		magic += 2;
		if (magic > maxMagic)
			magic = maxMagic;
		if (magic < 0)
			magic = 0;
		if (firing) {
			if (magic > magicCost) {
				magic -= magicCost;
				EnemyBall mb = new EnemyBall(tileMap, facingRight);
				mb.setPosition(x, y - 20);
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
		firing = true;

		if (firing) {
			animation.update();
			setFiring();

		}
		animation.update();

		// set direction

	}

	public void draw(Graphics g) {

		setMapPosition();
		getNextPosition();
		// draw magicballs
		for (int i = 0; i < magicBalls.size(); i++) {
			magicBalls.get(i).draw(g);

		}

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

	public void checkAttack(Player player) {
		for (int j = 0; j < magicBalls.size(); j++)
			if (magicBalls.get(j).intersects(player) && !(player.shielding && player.facingRight)) {
				player.hit(MAGICBALLDamage);
				magicBalls.get(j).setHit();
				break;
			}

		// check enemy collision

	}
}