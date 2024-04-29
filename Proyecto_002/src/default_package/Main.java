package default_package;

import java.awt.Color;


import javax.swing.JFrame;

import container_package.GameEngine;


/**
 * 
 * @author Perenguez Bastidas Luis Esteban
 * Titulo: Proyecto_001
 *
 */

public class Main {
	public static void main(String[] args) {
        // Configurar la ventana del juego
        JFrame frame = new JFrame("Space Invaders");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(800, 600);
        frame.setLocationRelativeTo(null);

        // Crear e iniciar el juego
        GameEngine gameEngine = new GameEngine();
        frame.add(gameEngine);
        frame.setVisible(true);

        // Iniciar el bucle del juego
        gameEngine.startGameLoop();
    }

}
