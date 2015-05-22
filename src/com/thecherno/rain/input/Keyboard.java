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
package com.thecherno.rain.input;

// Java's key classes for use in this Key handler class.
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

// The Keyboard class. This class is responsible for polling key events, and finding
// out which keys are being pressed and released. This is then applied to booleans
// to create a nice, clean interface for checking whether the "up" keys are pressed
// or not, for example. KeyListener is implemented, which includes the keyPressed(),
// keyReleased(), and keyTyped() methods in this class.
public class Keyboard implements KeyListener {

	// The boolean array of keys. This array contains the state (pressed / not pressed)
	// of the first 120 keys (key codes) on the keyboard. 
	private boolean[] keys = new boolean[120];
	// These booleans store whether the up, down, left, or right keys have been pressed.
	public boolean up, down, left, right, run;

	// The update() method. This method continually checks the state of the relevant keys
	// (pressed / not pressed), every time it is called, and updates the relevant booleans.
	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		run = keys[KeyEvent.VK_SHIFT];
	}

	// The keyPressed() method from the KeyListener interface. This method is called whenever
	// a key is pressed, with the key being pressed passed in as the KeyEvent parameter. The
	// key that has been pressed is set to true in the keys boolean array.
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	// The keyReleased() method from the KeyListener interface. This method is called whenever
	// a key is released, with the key being released passed in as the KeyEvent parameter. The
	// key that has been released is set to false in the keys boolean array.
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	// KeyListener's keyTyped() method. It is useless to us (at least for now), as we make
	// use of the keyPressed() and keyReleased() methods.
	public void keyTyped(KeyEvent e) {
	}

}
