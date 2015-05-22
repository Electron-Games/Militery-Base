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
package com.thecherno.rain.entity.spawner;

// Import all the necessary classes.
import com.thecherno.rain.entity.Entity;
import com.thecherno.rain.level.Level;

// The Spawner class. This class defines an entity that is used to spawn more
// entities into the level. It is an entity itself.
public abstract class Spawner extends Entity {

	// An enumeration which defines the two (for now) different types of possible
	// entities to spawn.
	public enum Type {
		MOB, PARTICLE;
	}

	// An instance of the Type enumeration.
	protected Type type;

	// The constructor. This simply assigns parameters to fields.
	public Spawner(int x, int y, Type type, int amount, Level level) {
		init(level);
		this.x = x;
		this.y = y;
		this.type = type;
	}

}
