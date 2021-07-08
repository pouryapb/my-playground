package objects;

import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.Random;

import basics.GameObject;
import basics.Handler;
import basics.ID;

public class Wind extends GameObject {

	Handler handler;
	double cycle = 1;
	ID effect;

	public Wind(float x, float y, ID id, Handler handler, ID effect) {
		super(x, y, id);

		this.effect = effect;
		this.handler = handler;
	}

	public void tick() {
			// for rain
			if (effect == ID.drop && (cycle / 300 >= 1)){
				float r = new Random().nextInt(6) - 3;
				for (int i = 0; i < handler.object.size(); i++) {
					GameObject temp = handler.object.get(i);
					if (temp.getId() == effect)
						temp.setVelX(r);
				}
				cycle = 1;
			}
		cycle++;
	}

	public void render(Graphics g) {

	}

	public Rectangle getBounds() {
		return null;
	}

}
