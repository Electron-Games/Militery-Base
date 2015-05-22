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

// Import all of the classes we need for this class..
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;

// The Void Tile class. This class represents a Tile that is rendered where no tile
// should be, kind of like a "filler" tile. 
public class VoidTile extends Tile {

	// The constructor. This simply runs the Tile class' constructor.
	public VoidTile(Sprite sprite) {
		super(sprite);
	}

	// The render() method. This renders the tile onto the screen at the given location.
	// (36) the x and y variables are now multiplied by 16 (the size of each tile)
	// so that they render at the correct location (pixel precision, not tile precision
	// for rendering). Note that "<< 4" is the same as "* 16".
	public void render(int x, int y, Screen screen) {
		screen.renderTile(x << 4, y << 4, this);
	}
}
