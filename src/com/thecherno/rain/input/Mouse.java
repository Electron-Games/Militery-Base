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

// Import all the necessary classes to capture and deal with mouse events.
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

// The Mouse class. This class represents a mouse that can be used to interact with
// our game. This class implements the MouseListener and MouseMotionListener interfaces,
// which allow us to let our game window capture these mouse events.
public class Mouse implements MouseListener, MouseMotionListener {

	// These two static integers determine the x- and y-coordinate of the mouse inside
	// the game window. -1 means they have no position: thus something is wrong.
	private static int mouseX = -1;
	private static int mouseY = -1;
	// This integer shows which mouse button is currently being pressed. -1 means that
	// no button is currently pressed. 0 cannot be used for this, since 0 corresponds
	// to the left mouse button.
	private static int mouseB = -1;

	// Returns the x-coordinate of the mouse in the window.
	public static int getX() {
		return mouseX;
	}

	// Returns the y-coordinate of the mouse in the window.
	public static int getY() {
		return mouseY;
	}

	// Returns which mouse button is currently being pressed. -1 means none.
	public static int getButton() {
		return mouseB;
	}

	// Captures the event of the mouse being dragged.
	// (68) Added code to set mouse location variables when the mouse is
	// dragged.
	public void mouseDragged(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	// Captures the event of the mouse being moved. The x- and y-coordinates
	// of the mouse are set to the mouseX and mouseY static variables.
	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	}

	// Captures the event of the mouse being clicked (not pressed). Not
	// used currently.
	public void mouseClicked(MouseEvent e) {
	}

	// Captures the event of the mouse entering a component. This will not
	// be used in our game (but is required in the MouseEvent interface).
	public void mouseEntered(MouseEvent e) {
	}

	// Captures the event of the mouse exiting a component. This will not
	// be used in our game (but is required in the MouseEvent interface).
	public void mouseExited(MouseEvent e) {
	}

	// Captures the event of a mouse button being pressed. The button that
	// was pressed is the assigned to our static mouseB variable, which in
	// turn can be returned statically.
	public void mousePressed(MouseEvent e) {
		mouseB = e.getButton();
	}

	// Captures the event of a mouse button being released. mouseB is set
	// to -1, indicating that no mouse button is currently being pressed
	// (which is true since we just released it).
	public void mouseReleased(MouseEvent e) {
		mouseB = -1;
	}
}
