//
// =========================================================================
//  Rain - A Project Copyright (C) 2012 - 2014 by Yan Chernikov, and      
//  TheCherno.com. Distribution of these Java source files "as is" is
//  prohibited, subject to law. Compiled versions and modified  source code
//  may be distributed, provided all comments are removed and fair use is
// justified. Full license can be found at http://code.thecherno.com/terms.
// =========================================================================

// =========================================================================
//  This source file refers to Episode 100 of Game Programming, which can be
//  found at http://youtu.be/1OpLi7wWvyY
// =========================================================================
//

// The package (or folder hierarchy) of where this class is located in our structure.
package com.thecherno.rain;

// Import all of the external classes that we are using, so that we can actually
// use them in this class. IDE's like Eclipse do this automatically.
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.JFrame;


// Import all of our necessary classes.
import com.thecherno.rain.entity.mob.Player;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.input.Keyboard;
import com.thecherno.rain.input.Mouse;
import com.thecherno.rain.level.Level;
import com.thecherno.rain.level.TileCoordinate;


// The Game Class. This class contains our main method, which will be the entry
// point of our game (where it starts). This class will be responsible for controlling
// the game loop, and the overall control flow of our game.
public class Game extends Canvas implements Runnable {
	// A serial version is set because the Canvas class implements Serializable,
	// a technique in Java which represents a class as a sequence of bytes. In
	// this case, because we have no need to send this class over Object Streams,
	// we use the default serial version, which is 1 (1L because it's a long, not
	// an integer; the L stands for Long).
	private static final long serialVersionUID = 1L;
	
	// A static integer is defined, which contains the width of our game display.
	private static int width = 300;
	// This integer contains the height of our game window. It is obtained by
	// conforming and evaluating the width with a 16:9 aspect ratio.
	private static int height = width / 16 * 9;
	// The scale of our display. We scale our game up by 300% for both height
	// and width, which greatly improves performance and gives a more retro feel.
	private static int scale = 3;
	// A title for the window is created, as a String variable.
	public static String title = "Rain";

	// A private Thread is created, but not instantiated. This Thread will allow
	// us to run the game loop concurrently with any other Java threads and processes.
	private Thread thread;

	// A JFrame object. This is the class that actually allows us to create a window
	// and set its parameters.
	private JFrame frame;

	// Our Keyboard class, which will handle and parse all keyboard input events.
	private Keyboard key;

	// Our Level class object, which will define the current level of the game.
	private Level level;

	// This is the Player object: a representation of the actual human player playing
	// this game.
	private Player player;

	// A private boolean which stores whether our game is currently running or not.
	// By default, we set this to false, since the start() method is what starts
	// our game.
	private boolean running = false;

	// Create a private Screen object for use within this Game class.
	private Screen screen;

	// A BufferedImage is created, with the width and height of our display (not window).
	// The type is set to be RGB, since that's the colour model we'll be using, without
	// and Alpha channel.
	private BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
	// A array of integers is created, which points to the buffered image that we created
	// in the previous line of code. By rasterising the image and mapping each of its pixels
	// as an integer in this array (the index of the integer is the location, the value of the
	// integer is the colour), we can control each individual pixel on our screen.
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	// A constructor for this class. The code here is executed whenever a new instance
	// of this class is created (new Game()), or if it's called explicitly of course
	// (this()). The code here creates a new Dimension object that stores the size
	// of our game window, not our display (note the * scale). setPreferredSize() is
	// then called, referring to the Canvas method (since we extend the Canvas class),
	// which sets the preferred size for our window to be the size Dimension object
	// we created. A new JFrame object is also created, by instantiating frame.
	public Game() {
		Dimension size = new Dimension(width * scale, height * scale);
		setPreferredSize(size);

		screen = new Screen(width, height);
		frame = new JFrame();
		key = new Keyboard();
		// Set the current level to be the spawn level.
		level = Level.spawn;
		// Creates a tile coordinate at 19, 62, which will translate to the player's
		// spawn location, in pixels (see the TileCoordinate class).
		TileCoordinate playerSpawn = new TileCoordinate(4, 13);
		// Creates a new Player instance that will represent the player.
		// The Keyboard instance is passed through so that the Player
		// can use the keyboard.
		// (62) Use the tile coordinate class to give the player a spawn on a certain
		// tile, based on its coordinate.
		player = new Player(playerSpawn.x(), playerSpawn.y(), key);
		// Add the player into the level.
		level.add(player);
		// Add our Keyboard class as a Key Listener to the game window.
		addKeyListener(key);
		// Create a new mouse instance and let the window listen to both mouse button
		// presses (MouseListener), and mouse movements (MouseMotionListener).
		Mouse mouse = new Mouse();
		addMouseListener(mouse);
		addMouseMotionListener(mouse);
		
		
	}

	// Returns the width of the window in pixels.
	public static int getWindowWidth() {
		return width * scale;
	}

	// Returns the height of the window in pixels.
	public static int getWindowHeight() {
		return height * scale;
	}

	// The start() method. This method will be called once, when we want our game to
	// start. It instantiates our Thread with this class, and the name "Display". The
	// thread is then then started, causing it to call the run() method in this class,
	// which is created in Episode 3 of Game Programming.
	// (3) running = true is added, which sets our running boolean equal to true. This
	// is done so that the while (running) loop in our run() method begins execution.
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	// The stop() method. This method is called when we want to stop our game, although
	// its use is created primarily for applets. It will "join" the threads together,
	// essentially waiting for the thread we created to run out of code to execute, and
	// will then join it to another existing Java thread.
	public synchronized void stop() {
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	// The run() method. This is called when thread.start() is executed in the start()
	// method, because this class implements Runnable, and this class is passed as a
	// parameter in thread = new Thread(this, "Display") in the start() method. This
	// method contains a while (running) loop, which is constantly and consistently
	// executed as long as the running variable is equal to true.
	// (13) A timer is created to ensure that the update() method runs exactly 60 times
	// per second (where possible). The render() method continues to run as fast as the
	// system / hardware allows.
	// (14) An FPS (frames per second) and UPS (updates per second) counter is implemented,
	// printing the result out to the console, and to the title of the window. This helps
	// ensure that our update() method really does run no more than 60 times per second,
	// as well as keep track of our game's performance.
	// (23) Added requestFocus(), which brings the window into focus when it opens.
	public void run() {
		long lastTime = System.nanoTime();
		long timer = System.currentTimeMillis();
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		int frames = 0;
		int updates = 0;
		requestFocus();
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;
			}
			render();
			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println(updates + " ups, " + frames + " fps");
				System.out.println(player.GetAmmo());
				frame.setTitle(title + "  |  " + updates +" ups, " + frames + " fps");
				updates = 0;
				frames = 0;
			}
		}
		stop();
	}

	// Temporary x and y integers are created, to demonstrate the map animation / camera
	// movement.
	int x = 0, y = 0;

	// The update() method.
	// (17) The Keyboard class is updated, by calling key.update(), updating which keys
	// are pressed and which are not.
	public void update() {
		key.update();
		level.update();
	}

	// The render() method. First, the BufferStrategy, which allows us to update and
	// render graphics, will be obtained. If one doesn't exist, usually when we first
	// launch our game, we create one with triple buffering.
	// (6) A black rectangle, which is the size of our window, is rendered and shown
	// on the screen.
	// (9) The BufferedImage is rendered to the screen.
	// (10) The screen is cleared before rendering again.
	// (16) The "map" is animated by setting the xOffset of screen.render() to be the
	// x variable, which is constantly incremented in the update() method above.
	// (36) Changed screen.render() to level.render(), since we're using Level class
	// to render now (the Level class calls the Screen class).
	// (45) The player is rendered in the centre of the screen.
	public void render() {
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;
		}

		screen.clear();

		// The x and y location of our player inside our window, on the screen. This
		// is what keeps the player in the centre of the screen.
		// (97) These are now doubles.
		double xScroll = player.getX() - screen.width / 2;
		double yScroll = player.getY() - screen.height / 2;
		// (45) The level is rendered based on the two offets above (xScroll and yScroll).
		// (97) xScroll and yScroll are cast to integers.
		level.render((int) xScroll, (int) yScroll, screen);
		
		// Renders the player_down sub-spritesheet onto the screen, to test out sub-spritesheets.
		// screen.renderSheet(40, 40, SpriteSheet.player_down, false);
		
		// Copy the pixels in the screen class to our real pixels, which will get rendered.
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];
		}

		Graphics g = bs.getDrawGraphics();
		g.setColor(Color.BLACK);
		g.fillRect(0, 0, getWidth(), getHeight());
		// Draw our BufferedImage (pixel array) onto the screen.
		g.drawImage(image, 0, 0, getWidth(), getHeight(), null);
		// Draw a 64x64 pixel square where the mouse is.
		// g.fillRect(Mouse.getX(), Mouse.getY(), 64, 64);
		// Draw text onto the screen which shows which mouse button is pressed (-1 means none).
		// if (Mouse.getButton() != -1) g.drawString("Button: " + Mouse.getButton(), 80, 80);
		
		g.fillRect(0,(height - (height/3)) * 3, width * 3 , height * 3);
		g.setColor(Color.WHITE);
		g.drawString("Hello", 0, getHeight() - (height-10));
		g.drawString(player.GetAmmo(), 0, (height * 3) - (height-20));
		
		g.dispose();
		bs.show();
		
	}

	// The main method. This is the entry point of our game; what gets called first
	// when it runs. A new Game object is created, an instance of this class. A window
	// is also created through JFrame, and setup with parameters.
	public static void main(String[] args) {
		Game game = new Game();
		game.frame.setResizable(false);
		game.frame.setTitle("Rain");
		game.frame.add(game);
		// "Packs" the frame, essentially conforming it to the smallest size if can.
		// Because we set our preferred size for Game() to be width and height, and
		// because the previous line of code added our game into the frame, the
		// size
		// is set to (width, height).
		game.frame.pack();
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		// Centres our frame in the middle of your monitor.
		game.frame.setLocationRelativeTo(null);
		game.frame.setVisible(true);

		// The game is started by calling the start() method in this class.
		game.start();
	}
}
