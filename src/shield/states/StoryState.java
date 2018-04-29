package shield.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import TileMap.Background;
import shield.game;

public class StoryState extends state {
	public StoryState(game Game) {
		super(Game);
		try {
			bg = new Background("/textures/cp4.jpg", 1);

			font = new Font("Arial", Font.BOLD, 40);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	private Background bg;
	private Font font;

	@Override
	public void tick() {
		getInput();

	}

	public void getInput() {
		if (Game.getKeyManager().r)
		{	game.flag = 1;
			state.setState(Game.level1state);
	}
	}
	@Override
	public void render(Graphics g) {
		bg.draw(g);
		g.setFont(font);
		g.setColor(Color.CYAN);
		g.drawString("Press R to continue", 550, 550);
	}
}