package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import basics.Game;
import basics.GameObject;
import basics.Handler;
import basics.ID;

public class Point extends GameObject{

	private int r;
	private Handler handler;
	private Color pColor = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
	private Color lColor = new Color(new Random().nextInt(255), new Random().nextInt(255), new Random().nextInt(255));
	
	public Point(float x, float y, ID id, int r, float velX, float velY, Handler handler) {
		super(x, y, id);
		
		this.r = r;
		
		this.velX = velX;
		this.velY = velY;
		
		this.handler = handler;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		if (x <= 0 || x >= Game.WIDTH - (r * 2)) {
			velX *= -1;
		}
		
		if (y <= 0 || y >= Game.HEIGHT - (r * 8)) {
			velY *= -1;
		}
	}

	public void render(Graphics g) {
		
		for (int i = 0; i < handler.object.size(); i++) {
			GameObject tempObject = handler.object.get(i);
			
			if (tempObject.getId() == ID.dot) {
				int distance = (int) Math.sqrt(((Math.pow((tempObject.getX() - x), 2)) + (Math.pow((tempObject.getY() - y), 2))));
				
				if (distance < 120) {
//					g.setColor(new Color(165, 165, 165, 200));
					g.setColor(lColor);
					g.drawLine((int)x, (int)y, (int)tempObject.getX(), (int)tempObject.getY());
				}
			}
		}
		
//		g.setColor(new Color(224, 224, 224, 200));
		g.setColor(pColor);
		g.fillOval((int)x - (r / 2), (int)y - (r / 2), r, r);
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int)x - (r / 2), (int)y - (r / 2), r, r);
	}
}
