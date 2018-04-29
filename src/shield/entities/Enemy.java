package shield.entities;

import TileMap.TileMap;
import shield.entities.creatures.Player;

public class Enemy extends MapObject {

	protected int health;
	protected int maxHealth;
	protected boolean dead;
	public int damage;

	protected boolean flinching;
	protected long flinchTimer;
	protected long shootTimer;

	public Enemy(TileMap tm) {
		super(tm);
	}

	public boolean isDead() {
		return dead;
	}

	public int getDamage() {
		return damage;
	}

	public void hit(int damage) {
		if (dead || flinching)
			return;
		health -= damage;
		if (health < 0)
			health = 0;
		if (health == 0)
			dead = true;
		flinching = true;
		flinchTimer = System.nanoTime();
		shootTimer = System.nanoTime();
	}

	public void update() {
	}

	public void checkAttack(Player player) {
	
	}

}