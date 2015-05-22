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
package com.thecherno.rain.entity;

// Import all necessary classes for use in this class.
import java.util.Random;

import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.level.Level;

// The Entity class. This is an abstract class that defines an Entity in our game. An
// entity is essentially an "object" that does something in our game: whether that
// be a Non-Player Character (NPC) or Mob, a key to a door, the player, or even a
// lamp post. This class will act as the parent class to all entities.
public abstract class Entity {

	// The x and y coordinate of the entity in the world.
	// (94) These are now protected.
	// (97) Changed from int to double to support more precise positioning.
	protected double x, y;
	// The Entity's sprite.
	protected Sprite sprite;
	// Whether or not the entity still exists (has it been removed?).
	private boolean removed = false;
	// The Level in which the entity is in.
	protected Level level;
	// An instance of the Random class that can be used by all entities.
	protected final Random random = new Random();

	// The update() method that can be overidden in subclasses.
	public void update() {
	}

	// A render() method that can be called to render the entity onto the screen.
	// If sprite is not null, render it at the correct location.
	public void render(Screen screen) {
		if (sprite != null) screen.renderSprite((int) x, (int) y, sprite, true);
	}

	// A method that can be called to remove the entity from th level it's in.
	public void remove() {
		removed = true;
	}

	// Returns the x position of the entity.
	// (97) Cast to int before return, since field is now a double. 
	public int getX() {
		return (int) x;
	}

	// Returns the y position of the entity.
	// (97) Cast to int before return, since field is now a double. 
	public int getY() {
		return (int) y;
	}

	// Returns the Entity's sprite.
	public Sprite getSprite() {
		return sprite;
	}

	// This method returns whether or not the entity has been removed.
	public boolean isRemoved() {
		return removed;
	}

	// The init() method. This "initialises" the entity by setting its Level
	// object (called "level") equal to a level.
	public void init(Level level) {
		this.level = level;
	}
}

