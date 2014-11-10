package com.knight.path;

public class Vector3i {
	
	public int x, y, z;
	
	public Vector3i(int x, int y, int z) {
		this.x = y;
		this.y = y;
		this.z = z;
	}
	
	public boolean equals(Vector3i comp) {
		return comp.x == x && comp.y == y && comp.z == z;
	}

}
