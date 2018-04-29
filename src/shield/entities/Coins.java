package shield.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import shield.gfx.*;
import TileMap.TileMap;

public class Coins extends MapObject {

	private BufferedImage[] sprites;
	public boolean remove;

	public Coins(TileMap tm) {
		super(tm);
		width = 30;
		height = 30;
		cwidth = 40;
		cheight = 40;
		Assets.init();

		animation = new Animation();
		animation.setFrames(Assets.coin);
		animation.setDelay(100);
		right = facingRight = true;
	}

	public void update() {
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		animation.update();
	}

	public boolean shouldRemove() {
		return remove;
	}

	public void draw(Graphics g) {
		setMapPosition();
		super.draw(g);

	}
}
