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
import com.thecherno.rain.graphics.AnimatedSprite;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.graphics.SpriteSheet;

// The Dummy class. The "Dummy" is a mob that we use to essentially test out
// whether our mob infrastructure works properly, hence the name.
public class Dummy extends Mob {

	// Animated Sprites are created for each of the possible directions of the dummy.
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.dummy_down, 32, 32, 3);
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.dummy_up, 32, 32, 3);
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.dummy_left, 32, 32, 3);
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.dummy_right, 32, 32, 3);

	// The current sprite to render. Down is the default direction.
	private AnimatedSprite animSprite = down;

	protected int health = 10;
	
	private int time = 0;
	private	int xa = 0;
	private int ya = 0;

	// Creates a new Dummy at the given tile coordinates. The sprite is set
	// to be the "dummy" sprite.
	public Dummy(int x, int y) {
		this.x = x << 4;
		this.y = y << 4;
		sprite = Sprite.dummy;
	}

	// The Dummy's update() method, implemented from the Mob class' abstract
	// update() method. This works very similarly to the Player's update()
	// method, in the sense that the mob is moved based on the values of
	// xa and ya, being the amount the mob needs to move in the x- and y-axis.
	// The direction as well as correct sprite is also set. The sprite only
	// plays the animation if the "walking" boolean is true; if not only the
	// first frame of the animated sprite will show up.
	// (92) Basic random movement is added. Firstly, the time variable is
	// incremented every update. Then, if the current time is a multiple of
	// a specific random number in between 30 - 80, change the Dummy's direction.
	// The direction is set by setting the xa and ya variables to a random value
	// being either -1, 0, or 1. Further more, the Dummy has a 1 in 3 chance
	// of stopping, and not moving anywhere.
	public void update() {
		time++;
		if (time % (random.nextInt(50) + 30) == 0) {
			xa = random.nextInt(3) - 1;
			ya = random.nextInt(3) - 1;
			if (random.nextInt(3) == 0) {
				xa = 0;
				ya = 0;
			}
		}
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

		if (xa != 0 || ya != 0) {
			move(xa, ya);
			walking = true;
		} else {
			walking = false;
		}
		updateHealth((int)x,(int)y);
	}

	// Renders the Dummy onto the screen at its position.
	// (91) Set the sprite to be the current animated sprite.
	// (97) The x and y variables are cast to integers.
	public void render(Screen screen) {
		sprite = animSprite.getSprite();
		screen.renderMob((int) x, (int) y, sprite, 0);
	}

}
