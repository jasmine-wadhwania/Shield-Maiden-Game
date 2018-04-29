package shield.entities;

import java.awt.Graphics;
import java.awt.image.BufferedImage;

import TileMap.TileMap;
import shield.gfx.Assets;

public class Heart extends MapObject {

	private BufferedImage[] sprites;
	public boolean remove;

	public Heart(TileMap tm) {
		super(tm);
		width = 33;
		height = 30;
		cwidth = 14;
		cheight = 14;
		Assets.init();

		animation = new Animation();
		animation.setFrames(Assets.heart);
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
