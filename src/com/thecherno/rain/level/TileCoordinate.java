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
package com.thecherno.rain.level;

// Tile TileCoordinate class. This class simply takes in two values, a tile coordinate,
// and converts them to pixel coordinates inside a level, where they can then be returned
// for use. This happens by simply multiplying the tile coordinate by the size of the
// tiles.
public class TileCoordinate {

	// The x and y tile coordinate.
	private int x, y;
	// The size of the square tiles.
	private final int TILE_SIZE = 16;

	// The constructor. This takes in two parameters: the x and y coordinate of the tile.
	// It then multiplies that by the size of the tile, and assigns it to the x and y
	// fields of this class. This converts the tile measurement into pixel measurement.
	public TileCoordinate(int x, int y) {
		this.x = x * TILE_SIZE;
		this.y = y * TILE_SIZE;
	}

	// Returns the x-coordinate of the tile.
	public int x() {
		return x;
	}

	// Returns the y-coordinate of the tile.
	public int y() {
		return y;
	}

	// Returns both the x- and y-coordinate of the tile, inside an array.
	public int[] xy() {
		int[] r = new int[2];
		r[0] = x;
		r[1] = y;
		return r;
	}

}
