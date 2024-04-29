package container_package;

//GameEngine.java
import javax.swing.JPanel;
import javax.swing.Timer;

import implementation_package.BulletImpl;
import implementation_package.InvaderImpl;
import implementation_package.PlayerImpl;
import interface_package.IBullet;
import interface_package.IInvader;
import interface_package.IPlayer;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class GameEngine extends JPanel {
 // Definir e inicializar los componentes del juego (jugador, invasores, balas, etc.)
 private IPlayer player;
 private static final int FPS = 60; // Velocidad de fotogramas por segundo
 private List<BulletImpl> bullets = new ArrayList<>(); // Lista de balas
 private List<InvaderImpl> invaders; // Lista de naves invasoras
 private int invaderSpeed = 10; // Velocidad de movimiento de las naves invasoras

 

 public GameEngine() {
     // Inicializar los componentes del juego
     player = new PlayerImpl(this);
     invaders = new ArrayList<>();
     int startX = 50; // Posición inicial x de la primera nave
     int startY = 50; // Posición inicial y de las naves
     int spacing = 100; // Espacio entre las naves
     
     // Crear y agregar las naves invasoras a la lista
     for (int i = 0; i < 7; i++) {
         int[] xPoints = {startX + i * spacing, startX + 25 + i * spacing, startX + 50 + i * spacing,
                          startX + 25 + i * spacing, startX + 12 + i * spacing, startX + 37 + i * spacing}; 
         int[] yPoints = {startY, startY + 50, startY, startY + 25, startY + 25, startY + 25}; 
         
         InvaderImpl invader = new InvaderImpl(this, startX + i * spacing, startY, xPoints, yPoints, invaderSpeed);
         invaders.add(invader);
     }
     // Inicializar los invasores y las balas
     // ...
     // Agregar un KeyListener para manejar la entrada del jugador
     this.setFocusable(true);
     this.requestFocusInWindow(); // Asegurar que el panel tenga el foco para recibir eventos de teclado
     this.addKeyListener(new KeyAdapter() {
         @Override
         public void keyPressed(KeyEvent e) {
             player.input(e);
         }
         @Override
        public void keyReleased(KeyEvent e) {
             player.input(e);
        }
     });
 }

 // Método para iniciar el bucle del juego
 public void startGameLoop() {
	 // Implementar el bucle del juego
	    Timer timer = new Timer(1000 / FPS, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            updateGameLogic(); // Actualizar la lógica del juego
	            repaint(); // Volver a dibujar los componentes del juego
	        }
	    });
	    timer.start();// Iniciar el temporizador
 }

 // Método para actualizar la lógica del juego
 private void updateGameLogic() {
	    // Actualizar la posición y estado de los invasores
	    for (InvaderImpl invader : invaders) {
	        invader.move();
	    }
	    
	    // Actualizar la posición y estado de las balas
	    for (BulletImpl bullet : bullets) {
	        bullet.update();
	    }
	    
	    // Eliminar las balas que estén fuera de la pantalla
	    bullets.removeIf(bullet -> !bullet.isAlive());
	    
	    // Actualizar la posición y estado del jugador
	    player.update();
	    
	    // Repintar el componente para actualizar la pantalla
	    repaint();
	}


 // Método para dibujar los componentes del juego en pantalla
 private void render(Graphics2D g) {
     // Limpiar la pantalla
	 g.setBackground(Color.black);
     g.clearRect(0, 0, getWidth(), getHeight());

     // Dibujar los componentes del juego (jugador, invasores, balas, etc.)
     player.draw(g);
     for (InvaderImpl invader : invaders) {
         invader.draw(g);
     }
     
     // Dibujar los invasores y las balas
     for (BulletImpl bullet : bullets) {
         bullet.draw(g);
     }
     
 }
 public void addBullet(BulletImpl bullet) {
     bullets.add(bullet);
 }
 

 @Override
 protected void paintComponent(Graphics g) {
     super.paintComponent(g);
     g.fillRect(0, 0, getWidth(), getHeight());
     Graphics2D g2d = (Graphics2D) g.create();
     render(g2d);
     g2d.dispose();
 }
 
 
}
