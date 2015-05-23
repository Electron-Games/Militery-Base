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

// Import all necessary classes.
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

import com.thecherno.rain.entity.mob.Dummy;

// The SpawnLevel class. This class represents a level where the player first spawns.
// The class contains everything necessary to load tiles from a file.
public class SpawnLevel extends Level {

	// A constructor. This currently simply runs the matching constructor in the
	// Level class.
	public SpawnLevel(String path) {
		super(path);		
	}

	// Loads an image file into an array of pixels.
	// (53) The tiles array is instantiated with the size of the image before it is filled.
	// (55) The levelPixels array is instantiated with the size of the image.
	// (57) Set the width and height variables in the Level class equal to the width and
	// height of our level image file.
	// (90) One Dummy mob is added to the level at tile coordinates 19, 55.
	protected void loadLevel(String path) {
		try {
			BufferedImage image = ImageIO.read(SpawnLevel.class.getResource(path));
			int w = width = image.getWidth();
			int h = height = image.getHeight();
			tiles = new int[w * h];
			image.getRGB(0, 0, w, h, tiles, 0, w);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println("Exception! Could not load level file! Generating Random Level");
		}
		// Add a Chaser mob and 5 Dummy mobs into the level!
		//add(new Chaser(20, 55));
		for (int i = 0; i < 1; i++) {
			add(new Dummy(20, 55));
		}
	}

	// The generateLevel() method. This goes through every pixel of our level file image,
	// and based on its colour sets an appropriate tile in the tiles array.
	// (55) The colours are prefixed with ff (255) to signify a full alpha channel, since
	// BufferedImage loads pixels with alpha data.
	// The colour codes are as follows:
	// Grass = 0xFF00FF00
	// Flower = 0xFFFFFF00
	// Rock = 0xFF7F7F00
	protected void generateLevel() {
	}
}
