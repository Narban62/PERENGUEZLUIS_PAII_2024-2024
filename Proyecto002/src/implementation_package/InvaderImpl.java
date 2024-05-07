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
    private int panelHeight; // Altura del panel del juego
    
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
        // En una implementación real, este método podría tener lógica para determinar si la nave sigue viva
        return true; // Devuelve siempre verdadero por ahora
    }

    @Override
    public void draw(Graphics2D g) {
        g.setColor(Color.green);
        g.fillPolygon(xPoints, yPoints, nPoints);
    }

    @Override
    public void update() {
        // Método para actualizar la posición de la nave
        // En una implementación real, podría contener lógica para actualizar la posición basada en el movimiento
    }

    // Getters y Setters
    
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

    public int[] getXPoints() {
        return xPoints;
    }

    public void setXPoints(int[] xPoints) {
        this.xPoints = xPoints;
    }

    public int[] getYPoints() {
        return yPoints;
    }

    public void setYPoints(int[] yPoints) {
        this.yPoints = yPoints;
    }

    public int getNPoints() {
        return nPoints;
    }

    public void setNPoints(int nPoints) {
        this.nPoints = nPoints;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public GameEngine getGameEngine() {
        return gameEngine;
    }

    public void setGameEngine(GameEngine gameEngine) {
        this.gameEngine = gameEngine;
    }
    

	public int getHeight() {
		return panelHeight;
	}
	public void setPanelHeight(int panelHeight) {
        this.panelHeight = panelHeight;
    }

	public void setAlive(boolean b) {
		// TODO Auto-generated method stub
		
	}

}
