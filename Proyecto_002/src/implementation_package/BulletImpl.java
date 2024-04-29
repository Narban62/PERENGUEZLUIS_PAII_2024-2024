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
    private int speed = 8; // Velocidad de la bala

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
}


