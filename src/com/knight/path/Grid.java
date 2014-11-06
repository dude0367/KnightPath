package com.knight.path;

public class Grid {
	
	private TileEntity[][] grid;
	private int width, height;
	
	public Grid(int width, int height) {
		setGrid(new TileEntity[width][height]);
		setWidth(width);
		setHeight(height);
	}

	public int getWidth() {
		return width;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public int getHeight() {
		return height;
	}

	public void setHeight(int height) {
		this.height = height;
	}

	public TileEntity[][] getGrid() {
		return grid;
	}

	public void setGrid(TileEntity[][] grid) {
		this.grid = grid;
	}

}
