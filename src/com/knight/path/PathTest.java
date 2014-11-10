package com.knight.path;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;

import com.knight.input.InputHandler;

//http://www.policyalmanac.org/games/aStarTutorial.htm

public class PathTest extends JFrame implements Runnable {

	private static final long serialVersionUID = 1L;
	public static PathTest test;
	public Thread thread;
	BufferedImage backbuffer;
	static int FPS = 30;
	int framesThisSecond = 0;
	int lastFPS = 0;
	int inputDelay = 0;
	int ticks = 0;
	int generations = 1;
	InputHandler input;
	boolean running = true;
	Grid grid;

	public static void main(String[] args) {
		test = new PathTest();
	}

	public PathTest() {
		this.setSize(800, 600);
		this.setTitle("Knight Pathing");
		this.setVisible(true);
		thread = new Thread(this);
		input = new InputHandler(this);
		thread.start();
	}

	@Override
	public void run() {
		
		grid = new Grid(10,10);
		
		for(int y =  3; y < 7; y++) {
			grid.getGrid()[5][y] = new TileWall();
		}
		
		grid.getGrid()[2][5] = new TileBot();
		grid.getGrid()[8][5] = new TileTarget();
		
		PathFinder p = new PathFinder(grid);
		p.FindPath(2, 5, 8, 5);
		
		long lastTime = System.currentTimeMillis();
		long delta = System.currentTimeMillis() - lastTime;
		long time = System.currentTimeMillis();
		long lastSecond = time / 1000;
		while(running) {
			delta = System.currentTimeMillis() - lastTime;
			tick(delta);
			draw();
			framesThisSecond++;
			time = (long) ((1000 / FPS) - (System.currentTimeMillis() - lastTime));
			lastTime = System.currentTimeMillis();
			if(lastSecond - lastTime / 1000 < 0) {
				lastSecond = lastTime / 1000;
				lastFPS = framesThisSecond;
				framesThisSecond = 0;
			}
			if (time > 0) { 
				try {
					Thread.sleep(time); 
				} 
				catch(Exception e){} 
			}
		}
	}

	private void tick(long delta) {
		if(delta == 0) delta = 1;
		ticks++;
	}

	private void draw() {
		if(backbuffer == null) backbuffer = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
		if(backbuffer.getWidth() != getWidth() || backbuffer.getHeight() != getHeight()) backbuffer = new BufferedImage(getWidth(), getHeight(), BufferedImage.TYPE_INT_RGB);
		Graphics g = getGraphics();
		Graphics bbg = backbuffer.getGraphics();
		bbg.clearRect(0, 0, getWidth(), getHeight());
		
		for(int x = 0; x < grid.getWidth(); x++) {
			for(int y = 0; y < grid.getHeight(); y++) {
				if(grid.getGrid()[x][y] != null) bbg.setColor(grid.getGrid()[x][y].getColor());
				else bbg.setColor(Color.WHITE);
				bbg.fillRect(x * 50, y * 50, 50, 50);
			}
		}

		g.drawImage(backbuffer, 0, 0, this);
	}

}
