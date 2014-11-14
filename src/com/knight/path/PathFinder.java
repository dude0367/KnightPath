package com.knight.path;

import java.awt.Color;
import java.util.ArrayList;

public class PathFinder {
	
	public Grid grid;
	
	public PathFinder(Grid grid) {
		this.grid = grid;
	}
	
	public void FindPath(int x, int y, int targX, int targY) {
		int steps = 0;
		boolean reached = false;
		ArrayList<Vector3i> open = new ArrayList<Vector3i>();
		ArrayList<Vector3i> closed = new ArrayList<Vector3i>();
		closed.add(new Vector3i(x,y,0));
		int xx = x;
		int yy = y;
		while(!reached) {
			//open.addAll(getAdjacent(xx,yy,targX,targY, open));
			Vector3i lowestF = null;//open.get(0);
			for(int xMod = -1; xMod < 2; xMod++) {
				for(int yMod = -1; yMod < 2; yMod++) {
					if(open.contains(new Vector3i(x + xMod, y + yMod, Math.abs((x + xMod) - targX) + Math.abs((y + yMod) - targY)))) {
						
					} else if((x + xMod >=0 && y + yMod >= 0 && x + xMod < grid.getWidth() && 
							y + yMod < grid.getHeight()) && (Math.abs(xMod) != Math.abs(yMod) && 
							!(grid.getGrid()[x + xMod][y + yMod] instanceof TileWall))) {
						open.add(new Vector3i(x + xMod, y + yMod, Math.abs((x + xMod) - targX) + Math.abs((y + yMod) - targY)));
					}
				}
			}
			/*for(Vector3i v : open) {
				if((lowestF != null && v.z < lowestF.z && !isClosed(closed, v)) || (lowestF == null && !isClosed(closed, v))) {
					lowestF = v;
				}
			}*/
			closed.add(lowestF);
			//open.clear();//remove(lowestF);
			xx = lowestF.x;
			yy = lowestF.y;
			steps++;
			grid.getGrid()[lowestF.x][lowestF.y] = new TilePath();
			System.out.println(steps + " steps");
			grid.getGrid()[lowestF.x][lowestF.y].setColor(new Color(50 + 5 * (steps < 40 ? steps : 40), 50, 50));
			if(xx == targX && yy == targY) reached = true;
		}
		/*for(Vector3i v : closed) {
			
		}*/
	}
	
	/*private ArrayList<Vector3i> getAdjacent(int x, int y, int targX, int targY, ArrayList<Vector3i> opened) {
		ArrayList<Vector3i> open = new ArrayList<Vector3i>();
		for(int xMod = -1; xMod < 2; xMod++) {
			for(int yMod = -1; yMod < 2; yMod++) {
				if((x + xMod >=0 && y + yMod >= 0 && x + xMod < grid.getWidth() && y + yMod < grid.getHeight()) && (Math.abs(xMod) != Math.abs(yMod) && !(grid.getGrid()[x + xMod][y + yMod] instanceof TileWall)) && opened.contains(new Vector3i(x + xMod, y + yMod, Math.abs((x + xMod) - targX) + Math.abs((y + yMod) - targY)))) {
					//System.out.println("Found tile @ " + (x + xMod) + "," + (y + yMod));
					open.add(new Vector3i(x + xMod, y + yMod, Math.abs((x + xMod) - targX) + Math.abs((y + yMod) - targY)));
				}
			}
		}
		return open;//WHY IS IT ALWAYS 2 ELEMENTS OF 5,5?
	}*/
	
	boolean isClosed(ArrayList<Vector3i> closed, Vector3i vec) {
		for(Vector3i v : closed) if(vec.equals(v)) return true;
		return false;
	}

}
