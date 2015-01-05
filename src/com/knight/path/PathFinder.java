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
		/*for(int i = 0; i < grid.getWidth(); i++) {
			for(int o = 0; o < grid.getWidth(); o++) {
				if(!(grid.getGrid()[i][o] instanceof TileWall)) {
					open.add(new Vector3i(i, o, Math.abs((i) - targX) + Math.abs((o) - targY)));
				}
			}
		}*/
		while(!reached) {
			//open.addAll(getAdjacent(xx,yy,targX,targY, open));
			Vector3i lowestF = null;//open.get(0);
			int lowestFVal = Integer.MAX_VALUE;
			/*for(int xMod = -1; xMod < 2; xMod++) {
				for(int yMod = -1; yMod < 2; yMod++) {
					Vector3i vec = new Vector3i(x + xMod, y + yMod, Math.abs((x + xMod) - targX) + Math.abs((y + yMod) - targY));
					if(!isClosed(closed, vec)) {//open.contains(new Vector3i(x + xMod, y + yMod, Math.abs((x + xMod) - targX) + Math.abs((y + yMod) - targY)))) {
						if(lowestF == null || mannDist(x + xMod, y+yMod, targX, targY) < lowestF.z) {
							lowestF = vec;//new Vector3i(x + xMod, y + yMod, Math.abs((x + xMod) - targX) + Math.abs((y + yMod) - targY));
							closed.add(vec);
						}
					} else if((x + xMod >=0 && y + yMod >= 0 && x + xMod < grid.getWidth() && 
							y + yMod < grid.getHeight()) && (Math.abs(xMod) != Math.abs(yMod) && 
							!(grid.getGrid()[x + xMod][y + yMod] instanceof TileWall))) {
						open.add(new Vector3i(x + xMod, y + yMod, Math.abs((x + xMod) - targX) + Math.abs((y + yMod) - targY)));
					}
				}
			}*/
			/*for(Vector3i v : open) {
				if((lowestF != null && v.z < lowestF.z && !isClosed(closed, v)) || (lowestF == null && !isClosed(closed, v))) {
					lowestF = v;
				}
			}*/
			
			for(int xMod = -1; xMod < 2; xMod++) {
				for(int yMod = -1; yMod < 2; yMod++) {
					Vector3i vec = new Vector3i(xx + xMod, yy + yMod, Math.abs((xx + xMod) - targX) + Math.abs((yy + yMod) - targY));
					if((xx + xMod >=0 && yy + yMod >= 0 && xx + xMod < grid.getWidth() && 
							yy + yMod < grid.getHeight()) && (Math.abs(xMod) != Math.abs(yMod) && 
							!(grid.getGrid()[xx + xMod][yy + yMod] instanceof TileWall)) && !isClosed(closed, vec)) {
						open.add(vec);
						if(lowestF == null || vec.z <= lowestF.z) {
							lowestF = vec;
						}
					}
				}
			}
			
			open.remove(lowestF);
			closed.add(lowestF);
			//open.clear();//remove(lowestF);
			xx = lowestF.x;
			yy = lowestF.y;
			steps++;
			grid.getGrid()[lowestF.x][lowestF.y] = new TilePath();
			System.out.println(steps + " steps");
			grid.getGrid()[lowestF.x][lowestF.y].setColor(new Color(50 + 5 * (steps < 40 ? steps : 40), 50, 50));
			if(xx == targX && yy == targY || steps > 20) reached = true;
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
	
	int mannDist(int x, int y, int targX, int targY) {
		return Math.abs(x - targX) + Math.abs(y - targY);
	}

}
