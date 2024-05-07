package implementation_package;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

import container_package.GameEngine;
import interface_package.IPlayer;

public class PlayerImpl implements IPlayer {
    private int x = 150; // Posición inicial x del jugador
    private int y = 550; // Posición y del jugador
    private int[] xPoints = {x, x + 25, x + 50}; // Coordenadas x de los vértices del triángulo
    private int[] yPoints = {y, y - 50, y}; // Coordenadas y de los vértices del triángulo
    private int nPoints = 3; // Número de puntos que componen el polígono (en este caso, 3 para un triángulo)
    private boolean movingLeft = false; // Indica si el jugador está moviéndose hacia la izquierda
    private boolean movingRight = false; // Indica si el jugador está moviéndose hacia la derecha
    private GameEngine gameEngine; // Referencia al GameEngine
    
    
    public PlayerImpl(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }

    @Override
    public void shoot() {
        // Crear una nueva instancia de BulletImpl en la posición del jugador
        BulletImpl bullet = new BulletImpl(x + 20, y);
        // Agregar la bala a la lista de balas en GameEngine
        gameEngine.addBullet(bullet);
    }

    @Override
    public boolean isAlive() {
        // Método para verificar si el jugador está vivo
        return false;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.red);
        g.fillPolygon(xPoints, yPoints, nPoints);
    }

    @Override
    public void update() {
        // Actualizar la posición del jugador basada en las teclas presionadas
        if (movingLeft) {
            x -= 6; // Decrementar la posición x para mover el jugador hacia la izquierda
        } else if (movingRight) {
            x += 6; // Incrementar la posición x para mover el jugador hacia la derecha
        }

        // Actualizar las coordenadas x de los vértices del jugador
        xPoints[0] = x;
        xPoints[1] = x + 25;
        xPoints[2] = x + 50;

        // Limitar el movimiento del jugador dentro de los límites de la pantalla
        if (x < 0) {
            x = 0;
        } else if (x + 150 > 875) { // 800 es el ancho de la ventana
            x = 725; // 650 es el ancho total del jugador
        }
    }

    @Override
    public void input(KeyEvent e) {
        int key = e.getKeyCode();
        if (key == KeyEvent.VK_LEFT) {
            movingLeft = true;
            movingRight = false;
        } else if (key == KeyEvent.VK_RIGHT) {
            movingRight = true;
            movingLeft = false;
        } else if (key == KeyEvent.VK_SPACE) {
            // Disparar al presionar la tecla de espacio
            System.out.println("funciona");
            shoot();
        }
    }
}

