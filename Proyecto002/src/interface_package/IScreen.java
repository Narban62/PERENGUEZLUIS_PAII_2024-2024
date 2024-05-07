package interface_package;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

public interface IScreen {
	void draw(Graphics2D g);
    void update();
    void input(KeyEvent e);
}
