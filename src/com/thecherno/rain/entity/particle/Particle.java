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
package com.thecherno.rain.entity.particle;

// Import everything we need for this class.
import com.thecherno.rain.entity.Entity;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;

// The Particle class. This class represents a particle in our game. Particles
// are entities, and this class is meant to be subclasses for every different
// type of particle, just like projectiles, mobs, etc.
public class Particle extends Entity {

	// This Particle's sprite.
	private Sprite sprite;

	// How long the particle should be "alive" for before it is removed.
	private int life;
	// A time integer to keep track of how long the particle has been alive.
	private int time;

	// The xx and yy variables are double versions of Entity's x and y, whilst
	// zz is a sort of "fake" third-dimension variable that we use to simulate
	// the particle moving up and down in 3D space.
	protected double xx, yy, zz;
	// These variables define the amount that the particle has to move. They
	// are used to modify the above variables.
	protected double xa, ya, za;

	// A constructor. This constructor creates a single particle at the given
	// location, with the given life span. The normal particle sprite is used.
	// (77) Set the xx and yy variables / fields to the x and y parameters
	// respectively, and set xa and ya to a randomized normal (gaussian) number.
	// (80) Set the zz variable.
	public Particle(int x, int y, int life) {
		this.x = x;
		this.y = y;
		this.xx = x;
		this.yy = y;
		this.life = life + (random.nextInt(20) - 10);
		sprite = Sprite.particle_normal;

		this.xa = random.nextGaussian();
		this.ya = random.nextGaussian();
		this.zz = random.nextFloat() + 2.0;
	}

	// Updates the particle.
	// (79) If the time that the particle has been alive for is greater than
	// its life, remove it.
	// (80) Change the zz variable based on the amount of motion in that axis.
	// Particle physics were also added, displacing the position of the particle
	// based on a virtual floor, along with bouncing. See Episode 80 for more
	// details.
	// (82) Added the move() method.
	public void update() {
		time++;
		if (time >= 7400) time = 0;
		if (time > life) remove();
		za -= 0.1;

		if (zz < 0) {
			zz = 0;
			za *= -0.55;
			xa *= 0.4;
			ya *= 0.4;
		}
		move(xx + xa, (yy + ya) + (zz + za));
	}

	private void move(double x, double y) {
		if (collision(x, y)) {
			this.xa *= -0.5;			
			this.ya *= -0.5;			
			this.za *= -0.5;			
		}
		this.xx += xa;
		this.yy += ya;
		this.zz += za;
	}

	public boolean collision(double x, double y) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = (x - c % 2 * 16) / 16;
			double yt = (y - c / 2 * 16) /16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (c % 2 == 0) ix = (int) Math.floor(xt);
			if (c / 2 == 0) iy = (int) Math.floor(yt);
			if (level.getTile(ix, iy).solid()) solid = true;
		}
		return solid;
	}

	// Renders the particle onto the screen.
	// (80) Offset the particle -12 on the x-axis.
	public void render(Screen screen) {
		screen.renderSprite((int) xx, (int) yy - (int) zz - 2, sprite, true);
	}

}
