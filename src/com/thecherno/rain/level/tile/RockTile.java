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

// Import our Sprite class for use in this class.
import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.graphics.Screen;

// The RockTile class. This class is a subclass of the Tile class which represents
// a rock tile.
public class RockTile extends Tile {

	// A constructor. This does nothing special; it simply runs the Tile class'
	// constructor.
	public RockTile(Sprite sprite) {
		super(sprite);
	}

	// Overides the Tile class' render method. The renderTile() method in the screen
	// class is called with the appropriate parameters, rendering the tile.
	// (36) the x and y variables are now multiplied by 16 (the size of each tile)
	// so that they render at the correct location (pixel precision, not tile precision
	// for rendering). Note that "<< 4" is the same as "* 16".
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}

	// Return true in the solid() boolean, so that when we do collision detection we
	// can detect this rock as being impassable; solid.
	public boolean solid() {
		return true;
	}
}
