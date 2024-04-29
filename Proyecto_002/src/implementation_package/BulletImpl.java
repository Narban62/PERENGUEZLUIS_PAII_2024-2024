package implementation_package;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;

import interface_package.IBullet;

import java.awt.Color;
import java.awt.Graphics2D;

public class BulletImpl implements IBullet {
    private int x; // Posición x de la bala
    private int y; // Posición y de la bala
    private int width = 10; // Ancho de la bala
    private int height = 20; // Altura de la bala
    private int speed = 3; // Velocidad de la bala

    public BulletImpl(int x, int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.WHITE);
        g.fillRect(x, y, width, height); // Dibujar un rectángulo blanco para representar la bala
    }

    @Override
    public void update() {
        y -= speed; // Mover la bala hacia arriba (hacia la parte superior de la pantalla)
    }

    @Override
    public boolean isAlive() {
        // Método para verificar si la bala está viva
        return y >= 0; // La bala sigue viva mientras esté dentro de la pantalla
    }

	@Override
	public void input(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void setAlive(boolean b) {
		// TODO Auto-generated method stub
		
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
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
	
}


