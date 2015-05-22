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

// Import all of the required classes.
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import com.thecherno.rain.entity.Entity;
import com.thecherno.rain.entity.mob.Player;
import com.thecherno.rain.entity.particle.Particle;
import com.thecherno.rain.entity.projectile.Projectile;
import com.thecherno.rain.graphics.Screen;
import com.thecherno.rain.level.tile.Tile;
import com.thecherno.rain.util.Vector2i;

// The Level class. This class represents a Level in our game. This is meant to be a
// a "template" for actual levels, meaning that subclasses should be created from this
// class. An integer array of tiles holds the layout of the level, and the width and
// height variables define the size.
public class Level {

	// The width and height of the level.
	protected int width, height;
	// An integer array of tiles. This defines which tiles get rendered where, since
	// tiles are what make up our level.
	protected int[] tilesInt;
	// An array of integers which represent tiles. These are the tiles that make up
	// the level.
	protected int[] tiles;
	// An array list which contains all of the entities that are inside this level.
	private List<Entity> entities = new ArrayList<Entity>();
	// An array list which contains all of the projectiles that exist in this level.
	private List<Projectile> projectiles = new ArrayList<Projectile>();
	// An array list which contains all of the particles that exist in this level.
	private List<Particle> particles = new ArrayList<Particle>();
	// An array list which contains all of the human controlled players in the level.
	private List<Player> players = new ArrayList<Player>();

	// This Comparator compares two nodes to each other, and returns an appropriate
	// value based on which node's fCost is greater. This will be used in conjuction
	// with the Collections.sort() method to sort the A*'s openList from lowest
	// fCost to highest fCost, which is what enables us to find the shortest path.
	private Comparator<Node> nodeSorter = new Comparator<Node>() {
		public int compare(Node n0, Node n1) {
			if (n1.fCost < n0.fCost) return +1;
			if (n1.fCost > n0.fCost) return -1;
			return 0;
		}
	};

	// A single instance of the spawn level is created.
	public static Level spawn = new SpawnLevel("/levels/Base.png");

	// A constructor. This takes in two parameters: width and height. It assigns them
	// to fields, and initializes the tiles array. The generateLevel() method is then
	// called.
	public Level(int width, int height) {
		this.width = width;
		this.height = height;
		tilesInt = new int[width * height];
		generateLevel();
	}

	// A constructor. This loads a level from a file, and thus takes in the file path
	// as the parameter. The loadLevel() method is then called to load the level from
	// the file.
	public Level(String path) {
		loadLevel(path);
		generateLevel();
	}

	// The generateLevel() method. This may be overidden in subclasses.
	protected void generateLevel() {
		for (int y = 0; y < 64; y++) {
			for (int x = 0; x < 64; x++) {
				getTile(x, y);
			}
		}
	}

	// The loadLevel() method. This may be overidden in subclasses.
	protected void loadLevel(String path) {
	}

	// The update() method. All of the level's entities are updated.
	// (72) All projectiles are now also updated, separately.
	// (78) Update all particles in the level.
	// (79) Run the remove() method to clear up entities.
	// (94) Update all players in the level.
	public void update() {
		for (int i = 0; i < entities.size(); i++) {			
			entities.get(i).update();
		}
		for (int i = 0; i < projectiles.size(); i++) {			
			projectiles.get(i).update();
		}
		for (int i = 0; i < particles.size(); i++) {			
			particles.get(i).update();
		}
		for (int i = 0; i < players.size(); i++) {			
			players.get(i).update();
		}
		remove();
	}

	// This method checks to see if any of the Entity array lists have
	// entities that are marked for removal. If so, those entities are
	// removed.
	private void remove() {
		for (int i = 0; i < entities.size(); i++) {			
			if (entities.get(i).isRemoved()) entities.remove(i);
		}
		for (int i = 0; i < projectiles.size(); i++) {			
			if (projectiles.get(i).isRemoved()) projectiles.remove(i);
		}
		for (int i = 0; i < particles.size(); i++) {			
			if (particles.get(i).isRemoved()) particles.remove(i);
		}
		for (int i = 0; i < players.size(); i++) {			
			if (players.get(i).isRemoved()) players.remove(i);
		}
	}

	// Returns the array list of projectiles.
	public List<Projectile> getProjectiles() {
		return projectiles;
	}

	// The time() method. Currently empty. This may be overidden in subclasses.
	private void time() {
	}

	// Checks to see whether a point on the map will step into a solid tile in the
	// next update. This result is returned. All four corners of adjacent tiles are
	// checked, just like the collision() method in the Mob class.
	public boolean tileCollision(int x, int y, int size, int xOffset, int yOffset) {
		boolean solid = false;
		for (int c = 0; c < 4; c++) {
			int xt = (x - c % 2 * size + xOffset) >> 4;
			int yt = (y - c / 2 * size + yOffset) >> 4;
			if (getTile(xt, yt).solid()) solid = true;
		}
		return solid;
	}

	public void projectileCollision(){
	      boolean loseHealth = false;
	      
	         for(int i = 0; i < entities.size(); i++){
	            for(int j = 0; j < projectiles.size(); j++){
	               int pposX = (int)projectiles.get(j).getX();
	               int pposY = (int)projectiles.get(j).getY();
	            
	               int mposX = (int)entities.get(i).getX();
	               int mposY = (int)entities.get(i).getY();
	               int mwidth = entities.get(i).getSprite().getWidth();
	               int mheight = entities.get(i).getSprite().getHeight();
	         
	               for(int wm = 0; wm < mwidth; wm++){
	                  for(int hm = 0; hm < mheight; hm++){
	                     if(pposX == (mposX + wm-20) && pposY == (mposY + hm-25)) {
	                        
	                        loseHealth = true;
	                        projectiles.get(j).remove();
	                        
	                     }
	                  }
	               }
	            
	            }
	         }
	         
	         for(int i = 0; i < players.size(); i++){
	            for(int j = 0; j < projectiles.size(); j++){
	               int pposX = (int)projectiles.get(j).getX();
	               int pposY = (int)projectiles.get(j).getY();
	            
	               int mposX = (int)players.get(i).getX();
	               int mposY = (int)players.get(i).getY();
	               int mwidth = players.get(i).getSprite().getWidth();
	               int mheight = players.get(i).getSprite().getHeight();
	         
	               for(int wm = 0; wm < mwidth; wm++){
	                  for(int hm = 0; hm < mheight; hm++){
	                     if(pposX == (mposX + wm-20) && pposY == (mposY + hm-25)) {
	                        loseHealth = true;
	                        projectiles.get(j).remove();
	                     }
	                  }
	               }
	               if(loseHealth) players.get(i).doDamage(projectiles.get(j).getDamage());
	            }
	            
	         }
	   }
	
	// The render() method. This may be overidden in subclasses. Four variables are
	// created, two for each axis, which define the start and end point of tile rendering
	// on those axes. The basis for these are the parameters xScroll and yScroll, which
	// will essentially be the player's location in the level.
	// (34) The setOffset() method is called, which offsets the screen based on the player's
	// location, which is fed in through the xScroll and yScroll parameters.
	// (36) Tiles are now found using the two for loops and rendered, using the render()
	// method inside the Tile class.
	// (38) Added an extra tile to positive horizontal and vertical rendering (+ 16 for
	// x1 and y1) so that black areas are not shown.
	// (54) Get tiles from "tile" array instead of an integer array like before.
	// (69) All of the level's entities are rendered.
	// (72) Projectiles are now rendered separately.
	// (78) Render all particles in the level.
	// (94) Players are now rendered using the appropriate for loop.
	public void render(int xScroll, int yScroll, Screen screen) {
		screen.setOffset(xScroll, yScroll);
		int x0 = xScroll >> 4;
		int x1 = (xScroll + screen.width + 16) >> 4;
		int y0 = yScroll >> 4;
		int y1 = (yScroll + screen.height + 16) >> 4;

		for (int y = y0; y < y1; y++) {
			for (int x = x0; x < x1; x++) {
				getTile(x, y).render(x, y, screen);
			}
		}

		for (int i = 0; i < entities.size(); i++) {			
			entities.get(i).render(screen);
		}

		for (int i = 0; i < projectiles.size(); i++) {			
			projectiles.get(i).render(screen);
		}

		for (int i = 0; i < particles.size(); i++) {			
			particles.get(i).render(screen);
		}

		for (int i = 0; i < players.size(); i++) {			
			players.get(i).render(screen);
		}
	}

	// Adds an entity to the level, and initialises it. Based on what type
	// of entity it is, the entity is placed into the appropriate array list.
	public void add(Entity e) {
		e.init(this);
		if (e instanceof Particle) {
			particles.add((Particle) e);
		} else if (e instanceof Projectile) {
			projectiles.add((Projectile) e);
		} else if (e instanceof Player) {
			players.add((Player) e);
		} else {
			entities.add(e);
		}
	}

	// Returns the entire array list of all players in this level.
	public List<Player> getPlayers() {
		return players;
	}

	// Returns a player at a specific index in the array list.
	public Player getPlayerAt(int index) {
		return players.get(index);
	}

	// Returns the local player: the one you control.
	public Player getClientPlayer() {
		return players.get(0);
	}

	// This method uses the A* search algorithm to find a path between two vectors: two
	// locations in the level. This method is explained in-depth in Episode 100 of 
	// Game Programming.
	public List<Node> findPath(Vector2i start, Vector2i goal) {
		List<Node> openList = new ArrayList<Node>();
		List<Node> closedList = new ArrayList<Node>();
		Node current = new Node(start, null, 0, getDistance(start, goal));
		openList.add(current);
		while (openList.size() > 0) {
			Collections.sort(openList, nodeSorter);
			current = openList.get(0);
			if (current.tile.equals(goal)) {
				List<Node> path = new ArrayList<Node>();
				while (current.parent != null) {
					path.add(current);
					current = current.parent;
				}
				openList.clear();
				closedList.clear();
				return path;
			}
			openList.remove(current);
			closedList.add(current);
			for (int i = 0; i < 9; i++) {
				if (i == 4) continue;
				int x = current.tile.getX();
				int y = current.tile.getY();
				int xi = (i % 3) - 1;
				int yi = (i / 3) - 1;
				Tile at = getTile(x + xi, y + yi);
				if (at == null) continue;
				if (at.solid()) continue;
				Vector2i a = new Vector2i(x + xi, y + yi);
				double gCost = current.gCost + getDistance(current.tile, a);
				double hCost =  getDistance(a, goal);
				Node node = new Node(a, current, gCost, hCost);
				if (vecInList(closedList, a) && gCost >= node.gCost) continue;
				if (!vecInList(openList, a) || gCost < node.gCost) openList.add(node);
			}
		}
		return null;
	}

	// This method checks to see if the specified vector is contained within the specified
	// array list of nodes, and returns that result.
	private boolean vecInList(List<Node> list, Vector2i vector) {
		for (Node n : list) {
			if (n.tile.equals(vector)) return true;
		}
		return false;
	}

	// This method calculates the direct (as the crow flies) distance between two vectors
	// and returns it. Pythagoras theorem is in used to work out that distance.
	private double getDistance(Vector2i tile, Vector2i goal) {
		double dx = tile.getX() - goal.getX();
		double dy = tile.getY() - goal.getY();
		return Math.sqrt(dx * dx + dy * dy);
	}

	// This method returns an array list of all entities that are inside a certain radius
	// of the specified entity.
	// (97) Cast the ex, ey, x, and y variables to be integers.
	public List<Entity> getEntities(Entity e, int radius) {
		List<Entity> result = new ArrayList<Entity>();
		int ex = (int) e.getX();
		int ey = (int) e.getY();
		for (int i = 0; i < entities.size(); i++) {
			Entity entity = entities.get(i);
			int x = (int) entity.getX();
			int y = (int) entity.getY();

			// Use pythagoras theorem to work out whether or not the current entity in the
			// array list is within the given radius of the specified entity.
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result.add(entity);
		}
		return result;
	}

	// This method returns an array list of all players that are inside a certain radius
	// of the specified entity.
	// (97) Cast the ex, ey, x, and y variables to be integers.
	public List<Player> getPlayers(Entity e, int radius) {
		List<Player> result = new ArrayList<Player>();
		int ex = e.getX();
		int ey = e.getY();
		for (int i = 0; i < players.size(); i++) {
			Player player = players.get(i);
			int x = player.getX();
			int y = player.getY();

			// Use pythagoras theorem to work out whether or not the current entity in the
			// array list is within the given radius of the specified entity.
			int dx = Math.abs(x - ex);
			int dy = Math.abs(y - ey);
			double distance = Math.sqrt((dx * dx) + (dy * dy));
			if (distance <= radius) result.add(player);
		}
		return result;
	}

	// The getTile() method. This method takes in two parameters: an x and y coordinate on
	// the level, and checks to see what tile is at that coordinate. It then returns the
	// tile. If no tile exists at the specified point, null is returned.
	// (37) Added the clipping code. This checks to see if the parameters are out of the
	// bounds ot the "tiles" array, and if so, returns a void tile.
	// (56) Colours are now looked up in the tiles array directly. The tile array contains
	// every pixel in our level image file.
	// (61) Colours are now checked using the constants found in the Tile class, and spawn
	// level tiles are returned based on the outcome of that.
	public Tile getTile(int x, int y) {
		if (x < 0 || y < 0 || x >= width || y >= height) return Tile.voidTile;
		if (tiles[x + y * width] == 0xffffff00) return Tile.Light_Green_Grass;
		if (tiles[x + y * width] == Tile.col_Road) return Tile.Road;
		//if (tiles[x + y * width] == Tile.col_Mine) return Tile.;
		if (tiles[x + y * width] == Tile.col_Tower) return Tile.Sniper_hut;
		if (tiles[x + y * width] == Tile.col_Guard_Post) return Tile.Sniper_hut;
		if (tiles[x + y * width] == Tile.col_Floor) return Tile.Wooden_Planks;
		return Tile.voidTile;
	}

}
