package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import basics.Game;
import basics.GameObject;
import basics.Handler;
import basics.ID;

public class Snow extends GameObject {

	float startVelY;
	int r, alpha = new Random().nextInt(100) + 155;
	Handler handler;
	double deg = new Random().nextDouble() * Math.PI;
	final double speed = new Random().nextDouble() * 0.06;
	public boolean down = false;
	
	public Snow(float x, float y, ID id, float velY, int r, Handler handler) {
		super(x, y, id);
		
		this.r = r;
		this.velY = velY;
		this.handler = handler;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		velX = Math.sin(deg);
		deg += speed;
		
		if (y > Game.HEIGHT) {
			y = 0;
//			x = new Random().nextInt(Game.WIDTH); // i don't see difference... but anyways :/
		}
		
		if (x < 0 || x > Game.WIDTH) {
			x = Game.WIDTH - x;
		}
		
//		if (y > Game.HEIGHT - 32) {
//			velY = 0;
//			down = true;
//		}
		
//		for (int i = 0; i < handler.object.size(); i++) {
//			GameObject temp = handler.object.get(i);
//			
//			if(temp.getId() == ID.snow && ((Snow)temp).down) {
//				if (getBounds().intersects(temp.getBounds()) && temp.getVelY() == 0) {
//					velY = 0;
//				}
//			}
//		}
	}

	public void render(Graphics g) {
		
		g.setColor(new Color(255, 255, 255, alpha));
		g.fillOval((int)x, (int)y, r, r);
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, r, r);
	}

}
