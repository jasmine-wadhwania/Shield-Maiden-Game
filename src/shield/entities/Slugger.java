package shield.entities;

import shield.entities.*;
import TileMap.TileMap;

import java.awt.image.BufferedImage;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.imageio.ImageIO;

public class Slugger extends Enemy {

	private BufferedImage[] sprites;

	public Slugger(TileMap tm) {

		super(tm);

		moveSpeed = 1.3;
		maxSpeed = 2.5;
		fallSpeed = 0.2;
		maxFallSpeed = 10.0;

		width = 110;
		height = 71;
		cwidth = 60;
		cheight = 20;

		health = maxHealth = 2;
		damage = 1;

		// load sprites
		try {

			BufferedImage spritesheet = ImageIO.read(getClass().getResourceAsStream("/textures/slugsprite.png"));

			sprites = new BufferedImage[3];
			for (int i = 0; i < sprites.length; i++) {
				sprites[i] = spritesheet.getSubimage(i * width, 0, width, height);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		animation = new Animation();
		animation.setFrames(sprites);
		animation.setDelay(300);

		right = true;
		facingRight = true;

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
		}

		// falling
		if (falling) {
			dy += fallSpeed;
		}

	}

	public void update() {

		// update position
		getNextPosition();
		checkTileMapCollision();
		setPosition(xtemp, ytemp);

		// check flinching
		if (flinching) {
			long elapsed = (System.nanoTime() - flinchTimer) / 1000000;
			if (elapsed > 400) {
				flinching = false;
			}
		}

		// if it hits a wall, go other direction
		if (right && dx == 0) {
			right = false;
			left = true;
			facingRight = false;
		} else if (left && dx == 0) {
			right = true;
			left = false;
			facingRight = true;
		}

		// update animation
		animation.update();

	}

	public void draw(Graphics g) {

		// if(notOnScreen()) return;

		setMapPosition();

		super.draw(g);

	}

}