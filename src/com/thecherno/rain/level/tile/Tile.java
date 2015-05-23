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
package com.thecherno.rain.level.tile;

// Import all required classes for this class.
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;

// The Tile class. This class represents a tile in our level. This class is meant
// to be used as a "template" for different types of tiles, by creating subclasses
// of this class.
public class Tile {
	
	// The x and y location of the tile.
	public int x, y;
	// The sprite which represents this tile graphically.
	public Sprite sprite;
	// The void tile.
	public static Tile voidTile = new VoidTile(Sprite.voidSprite);

	// All of our Spawn Level tiles are defined here, created from their relevant
	// sprites.
	public static Tile Barbdwire_h = new GrassTile(Sprite.Barbdwire_h);
	public static Tile Barbdwire_v = new GrassTile(Sprite.Barbdwire_v);
	public static Tile Sniper_hut = new GrassTile(Sprite.Sniper_hut);
	public static Tile Road_Arrow = new GrassTile(Sprite.Road_Arrow);
	public static Tile Road = new GrassTile(Sprite.Road);
	public static Tile Bomb = new GrassTile(Sprite.Bomb);
//	public static Tile Orange_Grass = new GrassTile(Sprite.Orange_Grass);
//	public static Tile Black_Rock = new GrassTile(Sprite.Black_Rock);
//	public static Tile Brick = new GrassTile(Sprite.Brick);
//	public static Tile Leaves = new GrassTile(Sprite.Leaves);
//	public static Tile White_Wood = new GrassTile(Sprite.White_Wood);
//	public static Tile Clear_water = new GrassTile(Sprite.Clear_water);
	public static Tile Light_Green_Grass = new GrassTile(Sprite.Grass);
//	public static Tile Mossy_Black_Rock = new GrassTile(Sprite.Mossy_Black_Rock);
//	public static Tile Orange_Brick = new GrassTile(Sprite.Orange_Brick);
//	public static Tile Orange_Leaves = new GrassTile(Sprite.Orange_Leaves);
//	public static Tile Red_Wood = new GrassTile(Sprite.Red_Wood);
//	public static Tile Mid_Water = new GrassTile(Sprite.Mid_Water);
//	public static Tile Light_Orange_Leaves = new GrassTile(Sprite.Light_Orange_Leaves);
//	public static Tile Grey_Brick = new GrassTile(Sprite.Grey_Brick);
//	public static Tile Blue_Brick = new GrassTile(Sprite.Blue_Brick);
//	public static Tile Brown_Wood = new GrassTile(Sprite.Brown_Wood);
//	public static Tile Water = new GrassTile(Sprite.Water);
//	public static Tile Dark_Green_Wood = new GrassTile(Sprite.Dark_Green_Wood);
//	public static Tile Cracked_Brick = new GrassTile(Sprite.Cracked_Brick);
//	public static Tile Colored_Brick = new GrassTile(Sprite.Colored_Brick);
	public static Tile Wooden_Planks = new GrassTile(Sprite.Wooden_Planks);
//	public static Tile Light_Green = new GrassTile(Sprite.Light_Green);
//	public static Tile Brown_Brick = new GrassTile(Sprite.Brown_Brick);

	// Colours are defined for every spawn level tile. These colours refer to
	// the colour of the pixels in the level file which determine which tile
	// should get rendered where. These variables are used in the Level class.
	public static final int col_spawn_hedge = 0; // unused
	public static final int col_Road = 0x606060; // unused
	public static final int col_Mine = 0x4CFF00; // unused
	public static final int col_Bomb = 0xFF006E; // unused
	public static final int col_Tower = 0x4CFF00; // unused
	public static final int col_Guard_Post = 0x00FF90; // unused
	public static final int col_Floor = 0xFF6A00; // unused
	public static final int col_Barbdwire_h = 0x0094FF; // unused
	public static final int col_Barbdwire_v = 0x0026FF; // unused
	

	// A constructor. Creates a new tile with the given sprite.
	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

	// Renders the tile; currently empty. This will later be overidden.
	public void render(int x, int y, Screen screen) {
	}

	// Returns whether the tile is solid or not; whether the player (or other mob)
	// can pass through it or not. This will later be used with collision detection,
	// and is meant to be overidden in subclasses where required.
	public boolean solid() {
		return false;
	}

}
