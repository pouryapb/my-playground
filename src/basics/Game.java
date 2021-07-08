package basics;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.util.Random;
import objects.Clock;
import objects.Point;
import objects.Snow;
import objects.Drop;

public class Game extends Canvas implements Runnable{

	private static final long serialVersionUID = -5571995104017886412L;
	public static final int WIDTH = 1250, HEIGHT = WIDTH / 12 * 9;
	private Thread thread;
	private boolean running = false;
	private Handler handler;
	
	public Game() {
		
		handler = new Handler();
		
		// projects
		/////////////////////////////
		
		bgProject();
//		clock();
//		rain();
//		snow();
		
		/////////////////////////////
		
		new Window(WIDTH, HEIGHT, "bg project!", this);
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try {
			thread.join();
			running = false;
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		
	}
	
	public void run() {
		this.requestFocus();
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while(running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running)
				render();
			frames++;
			
			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}
	
	public void tick() {
		
		handler.tick();
	}
	
	public void render() {
		
		BufferStrategy bs = this.getBufferStrategy();
		if (bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		
		/////////////////////////////////////
		
		g.setColor(Color.decode("#010f26"));
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		handler.render(g);
		
		/////////////////////////////////////
		g.dispose();
		bs.show();
		
	}
	
	void bgProject() {
		Random r = new Random();

		for (int i = 0; i < 100; i++) {
			handler.addObject(new Point(r.nextInt(WIDTH - 10), r.nextInt(HEIGHT - 30), ID.dot, r.nextInt(2) + 3, r.nextFloat() * 3f, r.nextFloat() * 3f, handler));
		}
	}
	
	void clock() {
		handler.addObject(new Clock(200, 200, ID.clock, 100));
	}
	
	void rain() {
		for (int i = 0; i < 200; i++) {
			handler.addObject(new Drop(new Random().nextInt(WIDTH), -(new Random().nextInt(1200)), ID.drop, new Random().nextInt(5) + 50, new Random().nextInt(2) + 1));
		}
//		handler.addObject(new Wind(0, 0, ID.wind, handler, ID.drop));
	}
	
	void snow() {
		for(int i = 0; i < 400; i++) {
			handler.addObject(new Snow(new Random().nextInt(WIDTH), new Random().nextInt(HEIGHT), ID.snow, 0.7f, new Random().nextInt(5) + 2, handler));
		}
	}
	
	public static void main(String[] args) {
		new Game();
	}
}
