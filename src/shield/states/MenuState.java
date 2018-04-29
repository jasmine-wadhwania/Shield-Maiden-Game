package shield.states;

import java.awt.color.*;
import java.awt.font.*;

import TileMap.Background;
import shield.game;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;

public class MenuState extends state {
	private Background bg;
	private int currentChoice = 0;
	private String[] options = { "Start", "Help", "Quit" };

	private Font font;

	public MenuState(game Game) {
		super(Game);

		try {
			bg = new Background("/textures/Panelimage final.png", 1);

			font = new Font("Arial", Font.PLAIN, 40);

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void init() {
	}

	@Override
	public void tick() {
		getInput();

	}

	public void getInput() {

		if (Game.getKeyManager().enter) {
			select();
		}
		if (Game.getKeyManager().up) {

			currentChoice--;
			if (currentChoice == -1)
				currentChoice = options.length - 1;
		}
		if (Game.getKeyManager().down) {
			currentChoice++;
			if (currentChoice == options.length)
				currentChoice = 0;

		}
	}

	@Override
	public void render(Graphics g) {

		bg.draw(g);

		// draw menu options
		g.setFont(font);

		for (int i = 0; i < options.length; i++) {
			if (i == currentChoice)
				g.setColor(Color.magenta);
			else {
				g.setColor(Color.CYAN);
			}
			g.drawString(options[i], 120, 400 + i * 55);
		}
	}

	public void select() {
		if (currentChoice == 0) {
			state.setState(Game.level1state);          //* storystate*//
		} else if (currentChoice == 1) {
			// help

		}
		if (currentChoice == 2) {
			// quit
			System.exit(0);
		}

	}
}