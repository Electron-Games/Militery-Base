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

// Import everything we need.
import java.util.Random;

import com.thecherno.rain.entity.Entity;
import com.thecherno.rain.graphics.Sprite;

public abstract class Projectile extends Entity {

	// These two constants hold the x- and y-position of the projectile when
	// it was first created: the origin.
	// (97) These are now doubles.
	protected final double xOrigin, yOrigin;
	// This double holds the angle at which the projectile is fired.
	protected double angle;
	// The projectile's sprite.
	protected Sprite sprite;
	// Overide Entity's x and y variables, making them doubles, for more
	// precision when animating.
	protected double x, y;
	// The amount of translation in the separate x- and y-axis, subject
	// to a calculation from the angle.
	protected double nx, ny;
	// The attributes / statistics of the projectile.
	protected double speed, range, damage;
	// A constant instance of the Random class, which we can use for
	// generating random numbers throughout all of our projectiles.
	protected final Random random = new Random();

	// The constructor. All required fields are set based on the parameters.
	// (97) Changed x and y parameters to be doubles.
	public Projectile(double x, double y, double dir) {
		xOrigin = x;
		yOrigin = y;
		angle = dir;
		this.x = x;
		this.y = y;
	}

	public Sprite getSprite() {
		return sprite;
	}

	public int getSpriteSize() {
		return sprite.SIZE;
	}
	
	public int getDamage(){
		return (int) damage;
	}

	// An empty move method which is meant to be overidden.
	protected void move() {
	}
}
