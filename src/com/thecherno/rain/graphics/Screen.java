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
// The Random class is imported for use in this class.
import java.util.Random;

import com.thecherno.rain.entity.mob.Chaser;
import com.thecherno.rain.entity.mob.Mob;
import com.thecherno.rain.entity.projectile.Projectile;
import com.thecherno.rain.level.tile.Tile;

// The Screen class. As the name may suggest, this class is responsible for actually
// maintaining our screen. More specifically, this class will contain the methods
// required for drawing pixels to the screen.
public class Screen {

	// The width and height of the screen. These are primarily created for clipping
	// purposes: the screen has to know the area of pixels to render, so that extra
	// pixels are not rendered, and array bounds are not exceeded.
	public int width, height;
	// An array of pixels, stored as integers.
	public int[] pixels;

	public final int MAP_SIZE = 64;
	public final int MAP_SIZE_MASK = MAP_SIZE - 1;

	// Two integers which define the x and y offset of the map (from the player).
	public int xOffset, yOffset;

	// An array of tiles is created. This is essentially an array of integers, that
	// will store the colour of the tile.
	public int[] tiles = new int[MAP_SIZE * MAP_SIZE];

	// An instance of Java's Random class is created for use within this class. It will
	// be used to generate random numbers.
	private Random random = new Random();

	// The constructor for the Screen class. Two integers, width and height, are taken
	// in as parameters. This class's width and height variables are then assigned the
	// values of the parameters, and our pixel array is instantiated with one integer
	// per pixel.
	// (15) The array of tiles is filled.
	public Screen(int width, int height) {
		this.width = width;
		this.height = height;
		pixels = new int[width * height];

		// The array of tiles is filled with random RGB colours.
		for (int i = 0; i < MAP_SIZE * MAP_SIZE; i++) {
			tiles[i] = random.nextInt(0xffffff);
		}
	}

	// Clears the screen to black (colour 0) by looping through the entire pixels array
	// and setting every integer inside that array to 0.
	public void clear() {
		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = 0;
		}
	}

	// The render() method.
	// (10) Example: a pixel is animated diagonally down-right by incrementing its x and
	// y positions simultaneously (the time variable does this; it's used instead of x
	// and y).
	// (12) Clipping is implemented for all bounds, by breaking from the loop if any of
	// the screen bunds are exceeded by the pixel.
	// (16) The "map" is repeated every 8 tiles, by using & MAP_SIZE_MASK.
	// (22) This method now renders our grass sprite, tiling it infinitely.
	// (23) Removed unused lines of code.
	public void render(int xOffset, int yOffset) {
		for (int y = 0; y < height; y++) {
			int yp = y + yOffset;
			if (yp < 0 || yp >= height) continue;
			for (int x = 0; x < width; x++) {
				int xp = x + xOffset;
				if (xp < 0 || xp >= width) continue;
				pixels[xp + yp * width] = Sprite.Grass.pixels[(x & 15) + (y & 15) * Sprite.Grass.SIZE];
			}
		}
	}

	// Renders a Sprite Sheet at a particular location on the screen. This is only used
	// for debug purposes, as displaying entire sprite sheets is not generally used.
	public void renderSheet(int xp, int yp, SpriteSheet sheet, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sheet.HEIGHT; y++) {
			int ya = y + yp;
			for (int x = 0; x < sheet.WIDTH; x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = sheet.pixels[x + y * sheet.WIDTH];
			}
		}
	}

	// Renders a sprite at a particular location on the screen. The "fixed" boolean parameter
	// corresponds to whether or not the sprite should be fixed onto the map, or floating. Fixed
	// means it will be part of the level, and thus will move with the level when the camera
	// does, whereas floating means that it will always be in the same position on the screen,
	// independant of the level. See Episode 75 for a good demonstration of this.
	public void renderSprite(int xp, int yp, Sprite sprite, boolean fixed) {
		if (fixed) {
			xp -= xOffset;
			yp -= yOffset;
		}
		for (int y = 0; y < sprite.getHeight(); y++) {
			int ya = y + yp;
			for (int x = 0; x < sprite.getWidth(); x++) {
				int xa = x + xp;
				if (xa < 0 || xa >= width || ya < 0 || ya >= height) continue;
				pixels[xa + ya * width] = sprite.pixels[x + y * sprite.getWidth()];
			}
		}
	}

	// The renderTile() method. This method is used to render a single tile onto the screen.
	// Offsets are set using the xp and yp parameters, which are then realised in the xa and
	// ya variables. Note that only an area of tile.sprite.SIZE * tile.sprite.SIZE is affected
	// in the pixels array (for example 16x16 pixels), since the x and y for loops only go
	// up to those numbers.
	// (34) xOffset and yOffset is now subtracted from xp and yp (respectively), which tells
	// the tiles to render at an offset, based on the player's position in the level.
	// (38) Changed clipping to show an extra tile in the negative x direction, to prevent
	// black areas from being shown.
	public void renderTile(int xp, int yp, Tile tile) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < tile.sprite.SIZE; y++) {
			int ya = y + yp;
			for (int x = 0; x < tile.sprite.SIZE; x++) {
				int xa = x + xp;
				if (xa < -tile.sprite.SIZE || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				pixels[xa + ya * width] = tile.sprite.pixels[x + y * tile.sprite.SIZE];
			}
		}
	}

	// Renders the specified projectile at the specified position.
	public void renderProjectile(int xp, int yp, Projectile p) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < p.getSpriteSize(); y++) {
			int ya = y + yp;
			for (int x = 0; x < p.getSpriteSize(); x++) {
				int xa = x + xp;
				if (xa < -p.getSpriteSize() || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = p.getSprite().pixels[x + y * p.getSpriteSize()];
				if (col != 0xffff00ff)	pixels[xa + ya * width] = col;
			}
		}
	}

	// Renders the specified mob at the specified position. If the mob happens to be
	// a Chaser, change the colour of the purple robe to a dark, desaturated red.
	public void renderMob(int xp, int yp, Mob mob) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			int ys = y;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				int xs = x;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = mob.getSprite().pixels[xs + ys * 32];
				if ((mob instanceof Chaser) && col == 0xff472BBF) col = 0xffBA0015;
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}

	// The renderPlayer() method. This is much like the renderTile() method, except it is
	// modified to specifically render the player.
	// (46) Alpha support is added by not rendering the (ff)ff00ff colour.
	// (47) Changed rendering size to 32x32 pixels intead of 16x16.
	// (90) Changed name to "renderMob" from "renderPlayer", since this method will now be
	// used to render mobs as well as players.
	public void renderMob(int xp, int yp, Sprite sprite, int flip) {
		xp -= xOffset;
		yp -= yOffset;
		for (int y = 0; y < 32; y++) {
			int ya = y + yp;
			int ys = y;
			if (flip == 2 || flip == 3) ys = 31 - y;
			for (int x = 0; x < 32; x++) {
				int xa = x + xp;
				int xs = x;
				if (flip == 1 || flip == 3) xs = 31 - x;
				if (xa < -32 || xa >= width || ya < 0 || ya >= height) break;
				if (xa < 0) xa = 0;
				int col = sprite.pixels[xs + ys * 32];
				if (col != 0xffff00ff) pixels[xa + ya * width] = col;
			}
		}
	}

	// This method sets the x and y offset of the map, from the player's perspective. This is
	// what essentially "moves" the map so that the player appears to move.
	public void setOffset(int xOffset, int yOffset) {
		this.xOffset = xOffset;
		this.yOffset = yOffset;
	}
}
