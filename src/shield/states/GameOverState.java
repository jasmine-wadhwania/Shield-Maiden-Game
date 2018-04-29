package shield.states;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.util.HashMap;

import Audio.AudioPlayer;
import shield.entities.creatures.Player;
import TileMap.Background;

import shield.game;

public class GameOverState  extends state{
	private Font font; 
	private HashMap<String,AudioPlayer> sfx;
public GameOverState(game Game) {
		super(Game);
		font = new Font("Arial", Font.PLAIN, 32);
		sfx=new HashMap<String,AudioPlayer>();
	    
	}

Background bg=new Background("/worlds/Game Over.jpg",0);


@Override
public void tick() {
	// TODO Auto-generated method stub
	
}

@Override
public void render(Graphics g) {
	
	bg.draw(g);
	g.setFont(font);
	g.setColor(Color.WHITE);
	g.drawString("Your Score:"+ Player.getCoinCollection(),550 ,550 );
}
}
