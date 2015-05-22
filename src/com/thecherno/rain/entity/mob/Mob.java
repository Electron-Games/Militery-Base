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
package com.thecherno.rain.entity.mob;

// Import all necessary classes.
import com.thecherno.rain.entity.Entity;
import com.thecherno.rain.entity.projectile.Projectile;
import com.thecherno.rain.entity.projectile.WizardProjectile;
import com.thecherno.rain.graphics.Screen;

// The Mob class. This is an abstract class that represents a Mob. A Mob (Mobile) is
// an entity that has the ability to move, and as such it commonly represents a
// creature, the player, or a Non-Player Character (NPC). Note the move() method:
// this is essentially what makes a mob a mob.
public abstract class Mob extends Entity {

	// Whether or not the Mob is currently moving.
	protected boolean moving = false;
	// Whether or not the Mob is currently walking.
	protected boolean walking = false;
	protected int health = 100;

	// An enumeration to represent direction. Instead of using integers to
	// represent
	// direction, which can be confusing, we can assign names to these integers,
	// such
	// as up, down, left and right. This allows us to refer to direction using
	// English
	// rather than numbers.
	protected enum Direction {
		UP, DOWN, LEFT, RIGHT
	}

	// A variable is created to keep track of the Mob's current direction, as
	// set
	// in the move() method.
	protected Direction dir;

	// The move() method. This gets called to actually move the mob.
	// (42) Two parameters, xa and ya, are added. This is the amount
	// that the Mob's x and y position should change. The direction
	// of the mob is changed based on the values of xa and ya. The
	// Mob's x and y variables are also modified based on the xa and
	// ya parameters, as long as the Mob is not colliding with
	// anything. This effectively moves the Mob.
	// (64) If the player moves diagonally, separate the axes and run
	// the move method twice: first with just the x-movement, and then
	// with just the y-movement. This allows the player to "slide" along
	// walls, since each axes' collision is checked independantly.
	// (91) Update the "dir" variable to work with the new enumeration.
	// (96) Changed movement and collision to support faster movement.
	public void move(double xa, double ya) {
		if (xa != 0 && ya != 0) {
			move(xa, 0);
			move(0, ya);
			return;
		}
		if (xa > 0)
			dir = Direction.RIGHT;
		if (xa < 0)
			dir = Direction.LEFT;
		if (ya > 0)
			dir = Direction.DOWN;
		if (ya < 0)
			dir = Direction.UP;

		// Instead of checking collision and moving the desired amount
		// straight away, we check x and y collision for each step of
		// the way, ensuring that if we are to run into a wall or something
		// solid the movement will stop exactly there.
		// These two while loops will make sure that collision is checked
		// every pixel of the way, ensuring that mobs can move precise
		// amounts per update, such as 0.5 or 1.4 pixels per update.
		// Please see Episode 97 for a detailed explanation of how these
		// two while loops work.
		while (xa != 0) {
			if (Math.abs(xa) > 1) {
				if (!collision(abs(xa), (int) ya)) {
					this.x += abs(xa);
				}
				xa -= abs(xa);
			} else {
				if (!collision(abs(xa), (int) ya)) {
					this.x += xa;
				}
				xa = 0;
			}
		}

		while (ya != 0) {
			if (Math.abs(ya) > 1) {
				if (!collision((int) xa, abs(ya))) {
					this.y += abs(ya);
				}
				ya -= abs(ya);
			} else {
				if (!collision((int) xa, abs(ya))) {
					this.y += ya;
				}
				ya = 0;
			}
		}

	}

	// This method returns positive 1 if the parameter is a positive number,
	// and negative 1 if the parameter is a negative number.
	private int abs(double value) {
		if (value < 0)
			return -1;
		return 1;
	}

	// The abstract update() method that needs to be implemented by an actual
	// mob.
	public abstract void update();

	// The abstract render() method that needs to be implemented by an actual
	// mob, so that the mob will show up on the screen and in the level.
	public abstract void render(Screen screen);

	// Shoot a projectile from the given coordinates, int the give direction.
	// Add it to the level.
	protected void shoot(int x, int y, double dir) {
		Projectile p = new WizardProjectile(x, y, dir);
		level.add(p);
	}

	// This method returns whether or not the mob is colliding with anything.
	// (65) All four corners of a tile are examined, using the for loop. If
	// any of those corners have a solid tile, then this method will return
	// true. This ensures that every side of a solid tile is deemed solid,
	// not just the top left corner, which was the case before.
	private boolean collision(int xa, int ya) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			double xt = ((x + xa) - c % 2 * 16) / 16;
			double yt = ((y + ya) - c / 2 * 16) / 16;
			int ix = (int) Math.ceil(xt);
			int iy = (int) Math.ceil(yt);
			if (level.getTile(ix, iy).solid())
				solid = true;
		}
		return solid;
	}

	protected void updateHealth(int x, int y) {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			if (level.getProjectiles().get(i).getX() == x) {
				if (level.getProjectiles().get(i).getY() == y) {
					level.getProjectiles().get(i).remove();
				}
			}
		}

		if (health == 0) {
			this.remove();
		}
	}

	public int getHealth() {
		return health;
	}
	
	public void doDamage(int damage){
		health = health - damage;
	}

}
