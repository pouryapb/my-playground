package objects;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.geom.Arc2D;
import java.util.Calendar;

import basics.GameObject;
import basics.ID;

public class Clock extends GameObject{

	private float r;
	private float secX, secY, minX, minY, hourX, hourY;
	
	public Clock(float x, float y, ID id, float r) {
		super(x, y, id);
		
		this.r = r;
	}

	public void tick() {
		float thetaSec = (360 / 60) * Calendar.getInstance().get(Calendar.SECOND);
		float thetaMin = (360 / 60) * Calendar.getInstance().get(Calendar.MINUTE) + (Calendar.getInstance().get(Calendar.SECOND) * 0.1f);
		float thetaHour = (360 / 12) * Calendar.getInstance().get(Calendar.HOUR) + (Calendar.getInstance().get(Calendar.MINUTE) * 0.5f);

		secX = (float) (x + (r - 10) * Math.cos(Math.toRadians(thetaSec - 90)));
		secY = (float) (y + (r - 10) * Math.sin(Math.toRadians(thetaSec - 90)));
		
		minX = (float) (x + (r - 15) * Math.cos(Math.toRadians(thetaMin - 90)));
		minY = (float) (y + (r - 15) * Math.sin(Math.toRadians(thetaMin - 90)));
		
		hourX = (float) (x + (r - 25) * Math.cos(Math.toRadians(thetaHour - 90)));
		hourY = (float) (y + (r - 25) * Math.sin(Math.toRadians(thetaHour - 90)));
	}

	public void render(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;
		
//		g2d.setColor(Color.decode("#3d3d3d"));
//		g2d.fillOval((int)(x - r), (int)(y - r), (int)r * 2, (int)r * 2);
//		
//		g2d.setColor(Color.decode("#bc440d"));
//		g2d.setStroke(new BasicStroke(2));
//		
//		g2d.drawOval((int)(x - r), (int)(y - r), (int)r * 2, (int)r * 2);
		
//		g.setColor(new Color(206, 206, 206, 170));
//		for (int i = 12; i > 0; i--) {
//			g.drawString(String.valueOf(i), (int)(x - 4 + (r - 10) * Math.cos(Math.toRadians(((360 / 12) * i) - 90))), (int)(y + 5 + (r - 10) * Math.sin(Math.toRadians(((360 / 12) * i) - 90))));
//		}

		g2d.setStroke(new BasicStroke(2));
		
		g2d.setColor(Color.lightGray);
		g2d.drawLine((int) x, (int) y, (int) hourX, (int) hourY);
				
		g2d.setColor(Color.lightGray);
		g2d.drawLine((int) x, (int) y, (int) minX, (int) minY);
		
		g2d.setColor(Color.red);
		g2d.drawLine((int) x, (int) y, (int) secX, (int) secY);
		
		g2d.setStroke(new BasicStroke(3));
		
		g2d.draw(new Arc2D.Double(x - r - 8, y - r - 8, (x - r + 8) * 2, (y - r + 8) * 2, 90, -(360 / 60) * Calendar.getInstance().get(Calendar.SECOND), Arc2D.OPEN));
		
		g2d.setColor(new Color(52, 242, 33));
		g2d.draw(new Arc2D.Double(x - r - 4, y - r - 4, (x - r + 4) * 2, (y - r + 4) * 2, 90, -((360 / 60) * Calendar.getInstance().get(Calendar.MINUTE) + (Calendar.getInstance().get(Calendar.SECOND) * 0.1f)), Arc2D.OPEN));
		
		g2d.setColor(new Color(252, 50, 242));
		g2d.draw(new Arc2D.Double(x - r, y - r, (x - r) * 2, (y - r) * 2, 90, -((360 / 12) * Calendar.getInstance().get(Calendar.HOUR) + (Calendar.getInstance().get(Calendar.MINUTE) * 0.5f)), Arc2D.OPEN));
		
	}

	public Rectangle getBounds() {
		
		return new Rectangle((int)(x - r - 8), (int)(y - r - 8), (int)(r + 8) * 2, (int)(r + 8) * 2);
	}

}
