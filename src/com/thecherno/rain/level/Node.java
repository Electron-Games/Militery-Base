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
package com.thecherno.rain.level;

// Import all of our necessary classes.
import com.thecherno.rain.util.Vector2i;
	
// The Node class. This represents a single node in the A* search algorithm.
// Nodes in A* are used to represent areas which should be considered when
// searching for a path. In this game, a node is equal to a tile, since tiles
// are what make up the level.
public class Node {

	// A Vector2i that represents the tile which this node is placed on.
	public Vector2i tile;
	// The parent node of this node, for use by A* to determine the actual
	// path once (if) one has been found (trace back, kind of like bread
	// crumbs in Hansel and Gretel). If there is no parent (the node is
	// the first one), this will be null.
	public Node parent;
	// The three cost variables. These are calculated by A* and are used
	// to determine which path is the shortest and most cost efficient.
	// Basically these are what determine what the best path is.
	public double fCost, gCost, hCost;

	// The Node constructor. This creates a new Node with the given parameters,
	// which basically just set all the fields of the class to the parameters.
	// Note that fCost is simply a sum of gCost and hCost, thus it is calculated
	// and does not need to be a parameter.
	public Node(Vector2i tile, Node parent, double gCost, double hCost) {
		this.tile = tile;
		this.parent = parent;
		this.gCost = gCost;
		this.hCost = hCost;
		this.fCost = this.gCost + this.hCost;
	}

}
