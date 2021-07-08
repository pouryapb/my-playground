package objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.util.Random;

import basics.Game;
import basics.GameObject;
import basics.ID;

public class Drop extends GameObject {

	int stroke, alpha = new Random().nextInt(100) + 155;
	float startY, startVelY;
	
	public Drop(float x, float y, ID id, float velY, int stroke) {
		super(x, y, id);
		
		this.stroke = stroke;
		this.velY = velY;
		startY = y;
	}

	public void tick() {
		x += velX;
		y += velY;
		
		velY += 0.6;
		
		if (y > Game.HEIGHT) {
			y = startY;
			velY = startVelY;
			x = new Random().nextInt(Game.WIDTH); // i don't see difference... but anyway :/
		}
		
		if (x < 0 || x > Game.WIDTH) {
			x = Game.WIDTH - x;
		}
	}

	public void render(Graphics g) {
		
		((Graphics2D) g).setStroke(new BasicStroke(stroke));
		g.setColor(new Color(49, 151, 252, alpha));
		g.drawLine((int)x, (int)y, (int)x + (int)velX, (int)y + 5);
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int)x, (int)y, 2, 10);
	}

}
