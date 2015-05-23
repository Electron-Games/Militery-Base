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
import com.thecherno.rain.Game;
import com.thecherno.rain.entity.projectile.Projectile;
import com.thecherno.rain.entity.projectile.WizardProjectile;
import com.thecherno.rain.graphics.AnimatedSprite;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.graphics.Sprite;
import com.thecherno.rain.graphics.SpriteSheet;
import com.thecherno.rain.input.Keyboard;
import com.thecherno.rain.input.Mouse;
import com.thecherno.rain.level.tile.Tile;

// The Player class. This class is a subclass of Mob, and represents the actual
// human player that is playing this game.
public class Player extends Mob {

	// A keyboard object. This allows the class to access the keyboard, and respond
	// to key presses.
	private Keyboard input;
	// The player's sprite.
	private Sprite sprite;
	// An integer which keeps track of the
	private int anim = 0;
	// Whether or not the player is walking.
	private boolean walking = false;
	// The player's sprite for facing (and moving) down.
	private AnimatedSprite down = new AnimatedSprite(SpriteSheet.player_down, 32, 32, 3);
	// The player's sprite for facing (and moving) up.
	private AnimatedSprite up = new AnimatedSprite(SpriteSheet.player_up, 32, 32, 3);
	// The player's sprite for facing (and moving) left.
	private AnimatedSprite left = new AnimatedSprite(SpriteSheet.player_left, 32, 32, 3);
	// The player's sprite for facing (and moving) right.
	private AnimatedSprite right = new AnimatedSprite(SpriteSheet.player_right, 32, 32, 3);
	// The rate at which the player can fire projectiles. This is set in the constructor.
	private int fireRate = 0;
	private int ammo = 1000;
	// The current animated sprite. This will usually be set to one of the above animated
	// sprite objects, that have already been created.
	private AnimatedSprite animSprite = down;
	protected int health = 30;
	// A constructor for the Player class. This takes in the Keyboard class as the
	// parameter, enabling the Player class to use the keyboard.
	// (48) Set the sprite to be the forward facing player sprite by default.
	// (89) Set the default animated sprite to be down.
	public Player(Keyboard input) {
		this.input = input;
		sprite = Sprite.player_forward;
		animSprite = down;
	}

	// A constructor. This takes in two integer parameters, and assigns them to the
	// x and y position of this player entity. It also takes in the Keyboard class
	// as the parameter, enabling the Player class to use the keyboard.
	public Player(int x, int y, Keyboard input) {
		this.x = x;
		this.y = y;
		this.input = input;
		sprite = Sprite.player_forward;
		fireRate = WizardProjectile.FIRE_RATE;
	}

	// The update() method. Based on what key the player presses, the player is
	// moved by modifying the xa and ya variables, and then calling the move()
	// method inside the Mob class with those variables, which moves the player.
	// (67) Allow shooting when required, by updating the shooting (updateShooting()).
	// (89) Set animatedSprite to the appropriate Animated Sprite when a movement
	// key is pressed. Also, update the current animated sprite if we're walking,
	// otherwise set the frame to the first one (0).
	// (97) Added the speed variable to the method, changed xa and ya to be doubles,
	// and made sure that xa and ya are modified according to that speed variable.
	public void update() {
		System.out.println("X: " + x);
		System.out.println("Y: " + y);
		
		if (walking) animSprite.update();
		else animSprite.setFrame(0);
		if (fireRate > 0) fireRate--;
		double xa = 0, ya = 0;
		double speed = 1.0;
		if (anim < 7500) anim++;
		else anim = 0;
		if(input.run){
			speed += 3;
			System.out.println("Run pressed");
		}

		if (input.up) {
			animSprite = up;
			ya -= speed;
		} else if (input.down) {
			animSprite = down;
			ya += speed;
		}if (input.left) {
			animSprite = left;
			xa -= speed;
		} else if (input.right) {
			animSprite = right;
			xa += speed;
		} 

		// If the player needs to move, move the player by calling the move() method.
		if (xa != 0 || ya != 0) {
			move(xa, ya);	
			walking = true;
		} else {
			walking = false;
		}
		double tileX = Math.floor((float) x / 16f);
		double tileY = Math.floor((float) y / 16f);
//		if(level.getTile((int)tileX,(int) tileY) == Tile.Black_Rock){
//			ammo = ammo + 100;
//		}
		
		clear();
		updateHealth((int)x,(int)y);
		updateShooting();
	}

	private void clear() {
		for (int i = 0; i < level.getProjectiles().size(); i++) {
			Projectile p = level.getProjectiles().get(i);
			if (p.isRemoved()) level.getProjectiles().remove(i);
		}
	}

	// If the player presses the left mouse button, shoot a projectile from the centre
	// of the screen (the player) towards the direction of the mouse pointer!
	// (68) Fixed dx and dy calculations to use the actual window size, not our scaled
	// size.
	// (73) Added fire rate code. Only fire the projectile if the fire rate is 0 (or
	// less), and then reset the fire rate variable back to the projectile's fire rate.
	private void updateShooting() {
		if (Mouse.getButton() == 1 && fireRate <= 0) {
			if(!(ammo == 0)){
				ammo = ammo - 1;
				double dx = Mouse.getX() - Game.getWindowWidth() / 2;
				double dy = Mouse.getY() - Game.getWindowHeight() / 2;
				double dir = Math.atan2(dy, dx);
				shoot(getX(), getY(), dir);
				fireRate = WizardProjectile.FIRE_RATE;
			}
		}
	}
	
	public String GetAmmo(){
		return String.valueOf(ammo);
	}

	// The render() method. Calls the renderPlayer() method in the Screen class and
	// renders the player.
	// (47) The player is now rendered once, as a 32x32 size sprite, with an offset
	// of negative 16 to centre the player.
	// (87) The sprite is set to be the test player animated sprite, just before
	// rendering.
	// (97) Cast position to integers when calling the screen.renderPlayer() method.
	public void render(Screen screen) {
		int flip = 0;
		sprite = animSprite.getSprite();
		screen.renderMob((int) (x - 16), (int) (y - 16), sprite, flip);
	}

}

