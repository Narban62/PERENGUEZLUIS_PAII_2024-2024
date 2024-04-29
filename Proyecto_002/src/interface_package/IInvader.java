package interface_package;

import java.awt.Graphics2D;

public interface IInvader {
	void move();
	void draw(Graphics2D g);
	void update();
    boolean isAlive();
}
