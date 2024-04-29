package implementation_package;

import java.awt.Color;
import java.awt.Graphics2D;

import container_package.GameEngine;
import interface_package.IInvader;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.Color;
import java.awt.Graphics2D;

import java.awt.Color;
import java.awt.Graphics2D;

public class InvaderImpl implements IInvader {
    private int x; // Posición x de la nave
    private int y; // Posición y de la nave
    private int[] xPoints; // Coordenadas x de los vértices
    private int[] yPoints; // Coordenadas y de los vértices
    private int nPoints; // Número de puntos que componen el polígono
    private int speed; // Velocidad de movimiento de la nave
    private GameEngine gameEngine; // Referencia al GameEngine
    
    public InvaderImpl(GameEngine gameEngine, int x, int y, int[] xPoints, int[] yPoints, int speed) {
        this.gameEngine = gameEngine;
        this.x = x;
        this.y = y;
        this.xPoints = xPoints;
        this.yPoints = yPoints;
        this.nPoints = xPoints.length;
        this.speed = speed;
    }

    @Override
    public void move() {
        y += speed; // Mover la nave hacia abajo
    }

    @Override
    public boolean isAlive() {
        // Método para verificar si la nave está viva
        return false;
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.green);
        g.fillPolygon(xPoints, yPoints, nPoints);
    }

    @Override
    public void update() {
        // Método para actualizar la posición de la nave
    }
}
