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
import com.thecherno.rain.entity.particle.Particle;
import com.thecherno.rain.level.Level;

// The ParticleSpawner class. This class is a subclass of Spawner, and essentially
// is responsible for spawning particles. 
public class ParticleSpawner extends Spawner {

	// This integer defines how long the particles will be alive for before they
	// are removed.
	private int life;

	// The constructor. This creates a new Particle Spawner at the given location,
	// with the given life, amount of particles to spawn, and in the given level.
	// New particles are added straight away into the level.
	public ParticleSpawner(int x, int y, int life, int amount, Level level) {
		super(x, y, Type.PARTICLE, amount, level);
		this.life = life;
		for (int i = 0; i < amount; i++) {
			if (type == Type.PARTICLE) {
				level.add(new Particle(x, y, life));
			}
		}

	}

}
