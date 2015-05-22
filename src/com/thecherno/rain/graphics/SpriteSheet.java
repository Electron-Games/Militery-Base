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
package com.thecherno.rain.graphics;

// Import all of the external classes that we are using, so that we can actually
// use them in this class. IDE's like Eclipse do this automatically.
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

// The SpriteSheet class. This class represents a Sprite Sheet in our game. A Sprite
// Sheet is an image file which contains multiple sprites that we wish to use in our
// game. Sprite Sheet's are convenient for storage, since we can pack many sprites
// into just one image file. This class essentially converts and stores the image file
// into an array of pixels for us to use.
public class SpriteSheet {

	// The file path of the Sprite Sheet image file.
	private String path;
	// The size of the Sprite Sheet. They must currently be square, thus width = height.
	public final int SIZE;
	// The width and height of the Sprite Sheet, in pixels.
	public final int WIDTH, HEIGHT;
	// An array of integers which represents each pixel in our Sprite Sheet image.
	public int[] pixels;

	// A new Sprite Sheet is created from the specified file path, with a size of 256x256.
	public static SpriteSheet tiles = new SpriteSheet("/textures/spritesheet.png", 256);
	// The Spawn Level sprite sheet is created.
	// (88) Changed file from "spawn_level.png" to "spawn_lvl.png" to use the new sprites.
	public static SpriteSheet spawn_level = new SpriteSheet("/textures/sheets/spawn_lvl.png", 48);
	// A sprite sheet for the wizard projectile.
	public static SpriteSheet projectile_wizard = new SpriteSheet("/textures/sheets/projectiles/wizard.png", 48);
	// The player's sprite sheet, loaded from an image file.
	public static SpriteSheet player = new SpriteSheet("/textures/sheets/player_sheet.png", 128, 96);
	// A sub-spritesheet created for the down sprite of the player. This is loaded from the sprite sheet above.
	public static SpriteSheet player_down = new SpriteSheet(player, 0, 0, 1, 3, 32);
	// A sub-spritesheet created for the up sprite of the player. This is loaded from the "player" sprite sheet.
	public static SpriteSheet player_up = new SpriteSheet(player, 1, 0, 1, 3, 32);
	// A sub-spritesheet created for the left sprite of the player. This is loaded from the "player" sprite sheet.
	public static SpriteSheet player_left = new SpriteSheet(player, 2, 0, 1, 3, 32);
	// A sub-spritesheet created for the right sprite of the player. This is loaded from the "player" sprite sheet.
	public static SpriteSheet player_right = new SpriteSheet(player, 3, 0, 1, 3, 32);

	// The dummy sprite sheet. This is currently the "King Cherno" sprite sheet, as seen by the image path.
	public static SpriteSheet dummy = new SpriteSheet("/textures/sheets/king_cherno.png", 128, 96);
	// A sub-spritesheet created for the down sprite of the dummy. This is loaded from the sprite sheet above.
	public static SpriteSheet dummy_down = new SpriteSheet(dummy, 0, 0, 1, 3, 32);
	// A sub-spritesheet created for the up sprite of the dummy. This is loaded from the "dummy" sprite sheet.
	public static SpriteSheet dummy_up = new SpriteSheet(dummy, 1, 0, 1, 3, 32);
	// A sub-spritesheet created for the left sprite of the dummy. This is loaded from the "dummy" sprite sheet.
	public static SpriteSheet dummy_left = new SpriteSheet(dummy, 2, 0, 1, 3, 32);
	// A sub-spritesheet created for the right sprite of the dummy. This is loaded from the "dummy" sprite sheet.
	public static SpriteSheet dummy_right = new SpriteSheet(dummy, 3, 0, 1, 3, 32);
	
	//public static SpriteSheet Item = new SpriteSheet("/textures/sheets/Items.png", 128, 96);

	// The array os Sprites that this sprite sheet contains.
	private Sprite[] sprites;


	// A constructor. This creates a sub-spritesheet: it creates a sprite sheet from an already
	// existing sprite sheet. The x and y specify the coordinate of the desired top-left corner
	// sprite that the sub-spritesheet should begin with (from the existing sprite sheet), and
	// the width and height (in sprites) is how many sprites the new sheet should engulf. The
	// spriteSize parameter is important, as it defines the size of every sprite in the original
	// (and new) sprite sheet.
	public SpriteSheet(SpriteSheet sheet, int x, int y, int width, int height, int spriteSize) {
		int xx = x * spriteSize;
		int yy = y * spriteSize;
		int w = width * spriteSize;
		int h = height * spriteSize;
		if (width == height) SIZE = width;
		else SIZE = -1;
		WIDTH = w;
		HEIGHT = h;
		pixels = new int[w * h];
		for (int y0 = 0; y0 < h; y0++) {
			int yp = yy + y0;
			for (int x0 = 0; x0 < w; x0++) {
				int xp = xx + x0;
				pixels[x0 + y0 * w] = sheet.pixels[xp + yp * sheet.WIDTH];
			}
		}
		int frame = 0;
		sprites = new Sprite[width * height];
		for (int ya = 0; ya < height; ya++) {
			for (int xa = 0; xa < width; xa++) {
				int[] spritePixels = new int[spriteSize * spriteSize];
				for (int y0 = 0; y0 < spriteSize; y0++) {
					for (int x0 = 0; x0 < spriteSize; x0++) {
						spritePixels[x0 + y0 * spriteSize] = pixels[(x0 + xa * spriteSize) + (y0 + ya * spriteSize) * WIDTH];
					}
				}
				Sprite sprite = new Sprite(spritePixels, spriteSize, spriteSize);
				sprites[frame++] = sprite;
			}			
		}
	}

	// A constructor. This creates a new Sprite Sheet from the specified file path, and
	// of the specified path. The fields of the class are set, and the load() method is
	// called.
	public SpriteSheet(String path, int size) {
		this.path = path;
		SIZE = size;
		WIDTH = size;
		HEIGHT = size;
		pixels = new int[SIZE * SIZE];
		load();
	}

	// A constructor. This creates a new sprite sheet from the given image file, with the
	// given width and height.
	public SpriteSheet(String path, int width, int height) {
		this.path = path;
		SIZE = -1;
		WIDTH = width;
		HEIGHT = height;
		pixels = new int[WIDTH * HEIGHT];
		load();
	}

	public Sprite[] getSprites() {
		return sprites;
	}

	// The load() method. This method loads the actual image from the file path, and
	// places its pixels into the integer array field called "pixels".
	private void load() {
		try {
			BufferedImage image = ImageIO.read(SpriteSheet.class.getResource(path));
			int w = image.getWidth();
			int h = image.getHeight();
			image.getRGB(0, 0, w, h, pixels, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
