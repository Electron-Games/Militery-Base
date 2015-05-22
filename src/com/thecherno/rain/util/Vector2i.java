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
package com.thecherno.rain.util;

// The Vector2i class. This class stores a 2-Dimensional integer vector.
// 2D Vectors are used to represent and calculate points in 2D space.
public class Vector2i {

	// The x and y integers of the vector. This is the entire
	// data that the Vector2i contains.
	private int x, y;

	// Creates a new 0, 0 vector.
	public Vector2i() {
		set(0, 0);
	}

	// Creates a new vector from an existing vector.
	public Vector2i(Vector2i vector) {
		set(vector.x, vector.y);
	}

	// Creates a new vector with the given values.
	public Vector2i(int x, int y) {
		set(x, y);
	}

	// Sets the vector to be the x and y parameters.
	public void set(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// Returns the x component of the vector.
	public int getX() {
		return x;
	}

	// Returns the y component of the vector.
	public int getY() {
		return y;
	}

	// Adds a vector to the current vector.
	public Vector2i add(Vector2i vector) {
		this.x += vector.x;
		this.y += vector.y;
		return this;
	}

	// Adds a vector to the current vector.
	public Vector2i subtract(Vector2i vector) {
		this.x -= vector.x;
		this.y -= vector.y;
		return this;
	}

	// Sets the x component of the vector to the specified value.
	public Vector2i setX(int x) {
		this.x = x;
		return this;
	}

	// Sets the y component of the vector to the specified value.
	public Vector2i setY(int y) {
		this.y = y;
		return this;
	}

	// Override the Object class' equals method. This method checks to see if a
	// Vector2i is equal to another Vector2i, by simply comparing the x and y
	// components of the vector to see if they're the same, since the x and y
	// components of the vector are what define it. The result of this comparison
	// is then returned.
	public boolean equals(Object object) {
		if (!(object instanceof Vector2i)) return false;
		Vector2i vec = (Vector2i) object;
		if (vec.getX() == this.getX() && vec.getY() == this.getY()) return true;
		return false;
	}

}
