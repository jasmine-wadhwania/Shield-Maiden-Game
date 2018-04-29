package shield.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keymanager implements KeyListener {

	private boolean[] keys;
	public boolean up, down, left, right, enter;
	public boolean space, r;
	public boolean one;

	public Keymanager() {
		keys = new boolean[256];
	}

	public void tick() {

		up = keys[KeyEvent.VK_UP];// any key starts with vk and _keyname wsad controls
		down = keys[KeyEvent.VK_DOWN];
		left = keys[KeyEvent.VK_LEFT];
		right = keys[KeyEvent.VK_RIGHT];
		enter = keys[KeyEvent.VK_ENTER];
		space = keys[KeyEvent.VK_SPACE];
		one = keys[KeyEvent.VK_1];
		r = keys[KeyEvent.VK_R];
	}

	public void keyPressed(KeyEvent e) {

		keys[e.getKeyCode()] = true;

	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;

	}

	public void keyTyped(KeyEvent e) {

	}

}