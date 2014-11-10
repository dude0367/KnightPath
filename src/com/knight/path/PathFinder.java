package com.knight.path;

import java.util.ArrayList;

public class PathFinder {
	
	public Grid grid;
	
	public PathFinder(Grid grid) {
		this.grid = grid;
	}
	
	public void FindPath(int x, int y, int targX, int targY) {
		boolean reached = false;
		ArrayList<Vector3i> open = new ArrayList<Vector3i>();
		ArrayList<Vector3i> closed = new ArrayList<Vector3i>();
		closed.add(new Vector3i(x,y,0));
		int xx = x;
		int yy = y;
		while(!reached) {
			open.addAll(getAdjacent(xx,yy,targX,targY));
			Vector3i lowestF = open.get(0);
			for(Vector3i v : open) {
				if(v.z < lowestF.z && !closed.contains(v)) {
					lowestF = v;
				}
			}
			closed.add(lowestF);
			open.clear();//remove(lowestF);
			xx = lowestF.x;
			yy = lowestF.y;
			if(xx == targX && yy == targY) reached = true;
		}
		for(Vector3i v : closed) {
			grid.getGrid()[v.x][v.z] = new TilePath(); 
		}
	}
	
	private ArrayList<Vector3i> getAdjacent(int x, int y, int targX, int targY) {
		ArrayList<Vector3i> open = new ArrayList<Vector3i>();
		for(int xMod = -1; xMod < 2; xMod++) {
			for(int yMod = -1; yMod < 2; yMod++) {
				if(Math.abs(xMod) != Math.abs(yMod) && !(grid.getGrid()[x + xMod][y + yMod] instanceof TileWall)) {
					//System.out.println("Found tile @ " + (x + xMod) + "," + (y + yMod));
					open.add(new Vector3i(x + xMod, y + yMod, Math.abs((x + xMod) - targX) + Math.abs((y + yMod) - targY)));
				}
			}
		}
		return open;//WHY IS IT ALWAYS 2 ELEMENTS OF 5,5?
	}
	

}
