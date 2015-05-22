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
package com.thecherno.rain.graphics;

// The AnimatedSprite class. This class represents a sprite that has multiple frames
// which make up an animation. Animated Sprites are created from sprite sheets.
public class AnimatedSprite extends Sprite {

	// The frame number of the current frame of the animation.
	private int frame = 0;
	// The Sprite in the current frame.
	private Sprite sprite;
	// The rate at which the animation is played.
	private int rate = 5;
	// A time variable is used to playback the animation based on the rate.
	private int time = 0;
	// The length of the animation. -1 means it is not set.
	private int length = -1;

	// A constructor. This creates a new animated sprite from the given
	// sprite sheet, with the given width and height. If the length is
	// greater than the amount of sprites in the specified sprite sheet,
	// an error message is printed.
	public AnimatedSprite(SpriteSheet sheet, int width, int height, int length) {
		super(sheet, width, height);
		this.length = length;
		sprite = sheet.getSprites()[0];
		if (length > sheet.getSprites().length) System.err.println("Error! Length of animation is too long!");
	}

	// The update() method. This "plays" the animation by updating the
	// sprite field in this class. The current sprite of the animation
	// is returned by the sprite sheet, using the getSprites() method
	// and by referencing the sprite at the frame index.
	public void update() {
		time++;
		if (time % rate == 0) {
			if (frame >= length - 1) frame = 0;
			else frame ++;
			sprite = sheet.getSprites()[frame];
		}
	}

	// Returns the current frame's sprite.
	public Sprite getSprite() {
		return sprite;
	}

	// Sets the frame rate: how fast the animation will play. Note that
	// the higher the number, the slower the animation will play (the
	// higher the delay in between frames).
	public void setFrameRate(int frames) {
		rate = frames;
	}

	// Set the current frame of the animated sprite to be the one
	// specified in the parameter, as long as it exists. An error is
	// printed to the console if the specified frame is out of bounds.
	public void setFrame(int index) {
		if (index > sheet.getSprites().length - 1) {
			System.err.println("Index out of bounds in " + this);
			return;
		}
		sprite = sheet.getSprites()[index];
	}
}
