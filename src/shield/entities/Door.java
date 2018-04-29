package shield.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import shield.gfx.*;
import TileMap.TileMap;

public class Door extends MapObject {

	private BufferedImage[] sprites;
	boolean remove;

	public Door(TileMap tm) {
		super(tm);
		width = 30;
		height = 30;
		cwidth = 14;
		cheight = 14;
		Assets.init();

		animation = new Animation();
		animation.setFrames(Assets.door);
		animation.setDelay(100);
		right = facingRight = true;
	}

	public void update() {
		checkTileMapCollision();
		setPosition(xtemp, ytemp);
		animation.update();
	}

	public void draw(Graphics g) {
		setMapPosition();
		super.draw(g);

	}
}