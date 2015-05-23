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

// The Sprite class. Creating a new instance of this Sprite corresponds to creating a
// new "sprite" (sprites are in-game 2D images that are used as graphics). Currently,
// Sprites are created from SpriteSheets (see Episode 19).
public class Sprite {

	// A constant integer which defines the width AND height of the sprite (since sprites
	// are currently square).
	// (22) Changed to public from private.
	public final int SIZE;
	// The x and y location of the sprite inside the sprite sheet.
	private int x, y;

	private int width, height;
	// An integer array which stores the actual sprite once extracted from the sprite sheet.
	public int[] pixels;
	// The Sprite Sheet from which the Sprite comes from.
	// (87) This is now protected instead of private, so that it can be accessed in the
	// Animated Sprite class.
	protected SpriteSheet sheet;

	// A Sprite is created. This Sprite is called "grass", and references cell coorinate
	// (0, 0) of the "tiles" Sprite Sheet that we created in Episode 22.
	// A new 16x16 Sprite is created with a Blakc colour.
	public static Sprite voidSprite = new Sprite(16, 0x000000);

	// Spawn Level Sprites here:
	public static Sprite Barbdwire_h = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
	public static Sprite Barbdwire_v = new Sprite(16, 1, 0, SpriteSheet.spawn_level);
	public static Sprite Sniper_hut = new Sprite(16, 2, 0, SpriteSheet.spawn_level);
	public static Sprite Road_Arrow = new Sprite(16, 3, 0, SpriteSheet.spawn_level);
	public static Sprite Road = new Sprite(16, 4, 0, SpriteSheet.spawn_level);
	public static Sprite Bomb = new Sprite(16, 5, 0, SpriteSheet.spawn_level);
	public static Sprite Wooden_Planks = new Sprite(16, 6, 0, SpriteSheet.spawn_level);
	
//	public static Sprite Orange_Grass = new Sprite(16, 0, 0, SpriteSheet.spawn_level);
//	public static Sprite Black_Rock = new Sprite(16, 1, 1, SpriteSheet.spawn_level);
//	public static Sprite Brick = new Sprite(16, 2, 1, SpriteSheet.spawn_level);
//	public static Sprite Leaves = new Sprite(16, 3, 1, SpriteSheet.spawn_level);
//	public static Sprite White_Wood = new Sprite(16, 4, 1, SpriteSheet.spawn_level);
//	public static Sprite Clear_water = new Sprite(16, 5, 1, SpriteSheet.spawn_level);
	public static Sprite Grass = new Sprite(16, 0, 2, SpriteSheet.spawn_level);
//	public static Sprite Mossy_Black_Rock = new Sprite(16, 1, 2, SpriteSheet.spawn_level);
//	public static Sprite Orange_Brick = new Sprite(16, 2, 2, SpriteSheet.spawn_level);
//	public static Sprite Orange_Leaves = new Sprite(16, 3, 2, SpriteSheet.spawn_level);
//	public static Sprite Red_Wood = new Sprite(16, 4, 2, SpriteSheet.spawn_level);
//	public static Sprite Mid_Water = new Sprite(16, 5, 2, SpriteSheet.spawn_level);
//	public static Sprite Light_Orange_Leaves = new Sprite(16, 0, 3, SpriteSheet.spawn_level);
//	public static Sprite Grey_Brick = new Sprite(16, 1, 3, SpriteSheet.spawn_level);
//	public static Sprite Blue_Brick = new Sprite(16, 2, 3, SpriteSheet.spawn_level);
//	public static Sprite Brown_Wood = new Sprite(16, 4, 3, SpriteSheet.spawn_level);
//	public static Sprite Water = new Sprite(16, 5, 3, SpriteSheet.spawn_level);
//	public static Sprite Dark_Green_Wood = new Sprite(16, 0, 4, SpriteSheet.spawn_level);
//	public static Sprite Cracked_Brick = new Sprite(16, 1, 4, SpriteSheet.spawn_level);
//	public static Sprite Colored_Brick = new Sprite(16, 2, 4, SpriteSheet.spawn_level);
//	public static Sprite Wooden_Planks = new Sprite(16, 4, 4, SpriteSheet.spawn_level);
//	public static Sprite Light_Green = new Sprite(16, 6, 4, SpriteSheet.spawn_level);
//	public static Sprite Brown_Brick = new Sprite(16, 0, 5, SpriteSheet.spawn_level);
	
	//public static Sprite itemStick = new Sprite(16, 1, 1, SpriteSheet.Item);
	


	// The 32 size player. Instead of having four quarters, as seen in the code above,
	// we can have just one sprite which is 4 times larger: 32x32 instead of 16x16.
	// (49) Renamed "player_right" to "player_side".

	// Player Sprites here:
	public static Sprite player_forward = new Sprite(32, 0, 5, SpriteSheet.tiles);
	public static Sprite player_back = new Sprite(32, 2, 5, SpriteSheet.tiles);
	public static Sprite player_side = new Sprite(32, 1, 5, SpriteSheet.tiles);

	public static Sprite player_forward_1 = new Sprite(32, 0, 6, SpriteSheet.tiles);
	public static Sprite player_forward_2 = new Sprite(32, 0, 7, SpriteSheet.tiles);

	public static Sprite player_side_1 = new Sprite(32, 1, 6, SpriteSheet.tiles);
	public static Sprite player_side_2 = new Sprite(32, 1, 7, SpriteSheet.tiles);

	public static Sprite player_back_1 = new Sprite(32, 2, 6, SpriteSheet.tiles);
	public static Sprite player_back_2 = new Sprite(32, 2, 7, SpriteSheet.tiles);

	// Create a sprite for the Dummy mob, from the dummy_down Sprite Sheet which
	// represents the animation for the dummy facing down.
	public static Sprite dummy = new Sprite(32, 0, 0, SpriteSheet.dummy_down);

	// Projectile Sprites here:
	public static Sprite projectile_wizard = new Sprite(16, 0, 0, SpriteSheet.projectile_wizard);

	// Particle Sprites here:
	public static Sprite particle_normal = new Sprite(3, 0xAAAAAA);
	public static Sprite square = new Sprite(2, 0xFF0000);

	// Creates a new Sprite from the given sheet, with the specified width and
	// height. If width is equal to height, set SIZE equal to that, otherwise
	// set SIZE equal to -1, indicating that the sprite is not square.
	protected Sprite(SpriteSheet sheet, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.sheet = sheet;
	}

	// A constructor for the Sprite class. The parameters include the size of the sprite,
	// the x and y coordinate of where the desired sprite is inside the sprite sheet, and
	// of course the actual sprite sheet where the sprite is located. Note that the
	// coordinates aren't the actual pixels, but rather the cell references, as the x and
	// y locations are multiplied by the size.
	// (75) Set the width and height fields to size.
	public Sprite(int size, int x, int y, SpriteSheet sheet) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		this.x = x * size;
		this.y = y * size;
		this.sheet = sheet;
		load();
	}

	// This constructor creates a solid colour sprite with the specified size.
	public Sprite(int width, int height, int colour) {
		SIZE = -1;
		this.width = width;
		this.height = height;
		pixels = new int[width * height];
		setColour(colour);
	}

	// A constructor for the Sprite class. This constructor takes in a size and a colour,
	// and creates a Sprite with that size and solid colour.
	// (75) Set the width and height fields to size.
	public Sprite(int size, int colour) {
		SIZE = size;
		this.width = size;
		this.height = size;
		pixels = new int[SIZE * SIZE];
		setColour(colour);
	}

	public Sprite(int[] pixels, int width, int height) {
		SIZE = (width == height) ? width : -1;
		this.width = width;
		this.height = height;
		this.pixels = pixels;
	}

	// A private method which sets the the sprite to a single colour specified by the
	// parameter.
	// (75) Use width * height for the for loop, instead of SIZE * SIZE, since SIZE
	// is -1 when the sprite is not square.
	private void setColour(int colour) {
		for (int i = 0; i < width * height; i++) {
			pixels[i] = colour;
		}
	}

	// Returns the width of the sprite.
	public int getWidth() {
		return width;
	}

	// Returns the height of the sprite.
	public int getHeight() {
		return height;
	}

	// A private method which loads the sprite into the integer array. This loops through
	// every pixel of the desired sprite inside the sprite sheet, and copies that data into
	// the pixels array in this class. This effectively "extracts" the sprite from the sprite
	// sheet into this class, for use.
	// (91) Changed the for loops and pixel array to use width and height instead of the
	// "SIZE" variable, permitting sprites to be non-square.
	private void load() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				pixels[x + y * width] = sheet.pixels[(x + this.x) + (y + this.y) * sheet.WIDTH];
			}
		}
	}
}
