package shield;

import java.awt.Graphics;
import java.awt.image.BufferStrategy;

import shield.gfx.Assets;
import shield.input.Keymanager;
import shield.states.GameOverState;
import shield.states.HappilyEverAfterState;
import shield.states.Level1State;
import shield.states.Level2State;
import shield.states.Level3State;
import shield.states.MenuState;
import shield.states.StoryState;
import shield.states.state;

//uses thread for independent running 
public class game implements Runnable {

	private display Display;
	public int width, height;
	public String title;
	private Thread thread;

	private boolean running = false;
	private BufferStrategy bs;
	private Graphics g;
	
	public static int flag = 0;
	// states

	public state[] gamestate;
	private state menustate;
	public state level1state;
	public state level2state;
	public state level3state;
	public state gameoverstate;
	public state storystate;
	private state currentState;
	public state happilyeverafterstate;

	// input
	private Keymanager keymanager;

	public game(String title, int width, int height) {
		this.width = width;
		this.height = height;
		this.title = title;
		keymanager = new Keymanager();

	}

	private void init()// initialize method
	{
		Display = new display(title, width, height);
		Display.getFrame().addKeyListener(keymanager);
		Assets.init();

		menustate = new MenuState(this);
		level1state = new Level1State(this);
		level2state = new Level2State(this);
		level3state = new Level3State(this);
		gameoverstate = new GameOverState(this);
		storystate = new StoryState(this);
		happilyeverafterstate = new HappilyEverAfterState(this);

		state.setState(menustate);
	}

	private void tick() {
		keymanager.tick();
		if (state.getstate() != null)
			state.getstate().tick();
	}

	private void render()// draws on our canvas
	{// buffers draw on a virtual hidden screen which is then shifted to actual
		// screen to prevent flickering
		bs = Display.getCanvas().getBufferStrategy();// tells computer how to
														// draw things on screen
		if (bs == null) {
			Display.getCanvas().createBufferStrategy(3);// usual max arg=3
			return;
		}
		g = bs.getDrawGraphics();// g is like a paint brush
		g.clearRect(0, 0, width, height);// clears a portion of the screen
		// Draw Here
		if (state.getstate() != null)
			state.getstate().render(g);

		// end drawing
		bs.show();// tell java that we r done drawing
		g.dispose();// proper closing
	}

	public void run() {
		init();
		int fps = 60; // times per second i.e, frames per second
		double timepertick = 1000000000 / fps; // 1 billion nanoseconds in 1
												// second for being specific
		double delta = 0;// time until we got to do tick and render
		long now;
		long lasttime = System.nanoTime();// nanoseconds that our system is
											// running at..
		long timer = 0;// visual time of how many times we have called tick and
						// render
		int ticks = 0;
		while (running)// loop for updating the variables,positions of objects
						// etc and then displaying it on screen over and over
						// again
		{// to make the speed equal for all speeds of computer
			now = System.nanoTime();// current time
			delta += (now - lasttime) / timepertick;// passed time by max amount
													// of time to call tick and
													// render
			// and add how much more time we hv to execute tick and render
			// method
			timer += now - lasttime;
			lasttime = now;

			if (delta >= 1) {
				tick();
				render();
				ticks++;
				delta--;
			}
			if (timer >= 1000000000) {
				System.out.println("ticks and frames" + ticks);
				ticks = 0;
				timer = 0;
			}
		}

		stop();
	}

	public Keymanager getKeyManager() {
		return keymanager;
	}

	public synchronized void start() {
		if (running)// already running-reinitializes and messes up hence if loop
			return;
		running = true;// to make running true when we start game for the first
						// time
		thread = new Thread(this);// since it implements game class it passes
									// that in thread
		thread.start();// calls run method
	}

	public synchronized void stop() {
		if (!running)// already stopped then no need to stop again
			return;
		running = false;
		try {
			thread.join();// for safe stopping of our game

		} catch (InterruptedException e) {
			System.out.println("abcdef");
			e.printStackTrace();
		}
	}
}