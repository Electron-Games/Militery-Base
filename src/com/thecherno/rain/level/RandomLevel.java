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

// Our very own Screen class is imported for use in this class.
import java.util.Random;

// The RandomLevel class. This
public class RandomLevel extends Level {

	// An instance of the Random class which gives us random numbers on command.
	// (36) Added the static keywork to ensure this instance always exists.
	private static final Random random = new Random();

	// A constructor. This takes in two parameters: width and height. It assigns them
	// to fields, and initializes the tiles array. The generateLevel() method is then
	// called.
	public RandomLevel(int width, int height) {
		super(width, height);
	}

	// The generateLevel() method, overidden from the Level class. This method assigns
	// a random number from 0 - 3 to each of the integers in the tiles array, which
	// is located in the Level class.
	protected void generateLevel() {
		for (int y = 0; y < height; y++) {
			for (int x = 0; x < width; x++) {
				tilesInt[x + y * width] = random.nextInt(4);
			}
		}
	}

}
