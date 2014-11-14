package com.knight.path;

public class Vector3i {
	
	public int x;
	public int y;
	public int z;
	
	public Vector3i(int x, int y, int z) {
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public boolean equals(Vector3i comp) {
		return comp.x == x && comp.y == y;//NOTE: NO Z COMPARISON
	}

}
