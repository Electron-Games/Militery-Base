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
import java.util.List;

import com.thecherno.rain.graphics.AnimatedSprite;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.graphics.SpriteSheet;

// The Chaser class. The "Chaser" is a mob that will chase the player in a rather
// dumb way, ignoring walls and obstacles in between. This will demonstrate
// basic AI.
public class Chaser extends Mob {

	// Animated Sprites are created for each of the possible directions of the dummy.
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);

	// The current sprite to render. Down is the default direction.
	private AnimatedSprite animSprite = down;
	
	protected int health = 10;

	// The amount of x- and y-axis movement that needs to occur each update.
	// These variables simply hold that amount.
	// (97) Changed these to be doubles, so that the mob can move slower.
	private	double xa = 0;
	private double ya = 0;

	// The speed at which the Chaser will move (how many pixels per update).
	private double speed = 0.8;

	// Creates a new Chaser at the given tile coordinates. The sprite is set
	// to be the "dummy" sprite.
	public Chaser(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}

	// The move() method for the Chaser mob. This determines the location of
	// the local player, and attempts to move the mob to the player's location.
	// (95) Only chase the first (local) player if that player is within 50
	// pixels of the Chaser.
	// (97) Changed xa and ya to be set according to the speed variable.
	private void move() {
		xa = 0;
		ya = 0;
		List<Player> players = level.getPlayers(this, 50);
		if (players.size() > 0) {
			Player player = players.get(0);
			if (x < player.getX()) xa += speed;
			if (x > player.getX()) xa -= speed;
			if (y < player.getY()) ya += speed;
			if (y > player.getY()) ya -= speed;
			
		}
		

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
	}

	// The Chaser's update() method, implemented from the Mob class' abstract
	// update() method.
	// (94) The move() method is called to move the mob.
	public void update() {
		move();
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		if (ya < 0) {
			animSprite = up;
			dir = Direction.UP;
		} else if (ya > 0) {
			animSprite = down;
			dir = Direction.DOWN;
		}
		if (xa < 0) {
			animSprite = left;
			dir = Direction.LEFT;
		} else if (xa > 0) {
			animSprite = right;
			dir = Direction.RIGHT;
		}

		updateHealth((int)x,(int)y);
	}

	// Renders the Chaser onto the screen at its position.
	// The sprite is also set  to be the current animated sprite.
	// (94) Offset the x and y rendering position by -16.
	// (97) Cast the position to integers.
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int) (y - 16), this);
	}

}
