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
package com.thecherno.rain.entity.projectile;

// Import all of the necessary classes, so we can use them in this class.
import com.thecherno.rain.entity.spawner.ParticleSpawner;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;

// The WizardProject class. This is a type of projectile that can be used
// by the player.
public class WizardProjectile extends Projectile {

	// The rate at which this projectile can be fired.
	public static final int FIRE_RATE = 1; // Higher is slower!

	// The constructor. A new Wizard Projectile is created at the given location,
	// and with the given direction. The range, speed, damage, and rate at which
	// it can be fired is also set here. The nx and ny variables hold the amount
	// of movement on the individual x- and y-axis, taken from the direction.
	// (97) Changed x and y parameters to be doubles.
	public WizardProjectile(double x, double y, double dir) {
		super(x, y, dir);
		range = 200;
		speed = 4;
		damage = 20;
		sprite = Sprite.projectile_wizard;
		nx = speed * Math.cos(angle);
		ny = speed * Math.sin(angle);
	}

	// Updates the projectile by calling the move() method.
	// (79) Add a particle spawner where the projectile collides!
	public void update() {
		if (level.tileCollision((int) (x + nx), (int) (y + ny), 7, 5, 4)) {
			level.add(new ParticleSpawner((int) x, (int) y, 44, 50, level));
			remove();
		}
		move();
	}

	// Moves the projectile by changing its position based on the two vectors.
	// If the distance the projectile has travelled is greater that its range,
	// remove it.
	protected void move() {
		x += nx;
		y += ny;
		if (distance() > range) remove();
	}

	// Calculates the distance that the projectile has travelled, from its origin.
	// That distance is then returned.
	private double distance() {
		double dist = 0;
		dist = Math.sqrt(Math.abs((xOrigin - x) * (xOrigin - x) + (yOrigin - y) * (yOrigin - y)));
		return dist;
	}

	// Renders the projectile onto the screen.
	public void render(Screen screen) {
		screen.renderProjectile((int) x - 12, (int) y - 2, this);
	}
	
}
