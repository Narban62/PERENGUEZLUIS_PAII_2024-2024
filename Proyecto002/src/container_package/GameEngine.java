package container_package;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import implementation_package.BulletImpl;
import implementation_package.InvaderImpl;
import implementation_package.PlayerImpl;
import interface_package.IPlayer;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Polygon;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;


public class GameEngine extends JPanel {
	
 // Definir e inicializar los componentes del juego (jugador, invasores, balas, etc.)
	
 private IPlayer player;
 private static final int FPS = 20; // Velocidad de fotogramas por segundo
 private List<BulletImpl> bullets = new ArrayList<>(); // Lista de balas
 private List<InvaderImpl> invaders; // Lista de naves invasoras
 private int invaderSpeed = 51; // Velocidad de movimiento de las naves invasoras
 private JLabel scoreLabel; // Nuevo JLabel para mostrar el puntaje
 private JLabel usernameLabel; // JLabel para mostrar el nombre de usuario
 private String username; // Variable para almacenar el nombre de usuario
 

 public GameEngine() {
     // Inicializar los componentes del juego
     player = new PlayerImpl(this);
     invaders = new ArrayList<>();
     int startX = 50; // Posición inicial x de la primera nave
     int startY = 50; // Posición inicial y de las naves
     int spacing = 100; // Espacio entre las naves
     usernameLabel = new JLabel("Usuario: ");


     // Agregar etiqueta para el nombre de usuario
     
     usernameLabel.setForeground(Color.WHITE);
     usernameLabel.setBounds(10, 30, 200, 20); // Posición y tamaño de la etiqueta
     this.add(usernameLabel); // Agregar la etiqueta al panel
     
     scoreLabel = new JLabel("Score: 0");
     scoreLabel.setForeground(Color.WHITE); // Color del texto
     scoreLabel.setFont(new Font("Arial", Font.BOLD, 16)); // Establecer la fuente
     scoreLabel.setBounds(10, 10, 100, 30); // Establecer la posición y tamaño
     this.add(scoreLabel); // Agregar scoreLabel al panel
     
     // Crear y agregar las naves invasoras a la lista
     for (int i = 0; i < 7; i++) {
         int[] xPoints = {startX + i * spacing, startX + 25 + i * spacing, startX + 50 + i * spacing,
                          startX + 25 + i * spacing, startX + 12 + i * spacing, startX + 37 + i * spacing}; 
         int[] yPoints = {startY, startY + 50, startY, startY + 25, startY + 25, startY + 25}; 
         
         InvaderImpl invader = new InvaderImpl(this, startX + i * spacing, startY, xPoints, yPoints, invaderSpeed);
         invaders.add(invader);
     }
     // Inicializar los invasores y las balas
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
	 
	 getUsername();
	 
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
 
 private void getUsername() {
     username = JOptionPane.showInputDialog(null, "Por favor, ingrese su nombre de usuario:", "Nombre de Usuario", JOptionPane.PLAIN_MESSAGE);
     if (username != null && !username.isEmpty()) { // Verificar si se ingresó un nombre
         usernameLabel.setText("Usuario: " + username); // Actualizar el texto del JLabel con el nombre ingresado
     }
 }

 // Método para actualizar la lógica del juego

 private void updateGameLogic() {
	    // Verificar si alguna de las naves invasoras ha comenzado a moverse hacia abajo
	    boolean startedMovingDown = false;
	    for (InvaderImpl invader : invaders) {
	        if (invader.getSpeed() > 0) {
	            startedMovingDown = true;
	            break;
	        }
	    }
	    
	    // Si alguna nave invasora ha comenzado a moverse hacia abajo, agregar una nueva fila de naves
	    if (startedMovingDown && invaders.get(0).getY() >= invaders.get(0).getHeight()) {
	        createNewRow();
	    }
	    
	    // Actualizar la posición y estado de los invasores
	    for (InvaderImpl invader : invaders) {
	        invader.move();
	    }
	    
	    // Actualizar la posición y estado de las balas
	    for (BulletImpl bullet : bullets) {
	        bullet.update();
	    }
	    
	    // Detección de colisiones entre balas y naves invasoras
	    for (BulletImpl bullet : bullets) {
            for (InvaderImpl invader : invaders) {
                if (collisionDetected(bullet, invader)) {
                    // Si hay una colisión, eliminar la bala y establecer la nave invasora como muerta
                    bullet.setAlive(false);
                    invader.setAlive(false);
                    System.out.println("Collision detected! Bullet and invader marked as dead.");

                    // Actualizar el puntaje
                    updateScore();
                    
                    break;
                }
            }
        }
	    
	    // Eliminar las balas y las naves invasoras que han sido marcadas como muertas
	    bullets.removeIf(bullet -> !bullet.isAlive());
	    invaders.removeIf(invader -> !invader.isAlive());

	    // Actualizar la posición y estado del jugador
	    player.update();
	    
	    // Repintar el componente para actualizar la pantalla
	    repaint();
	}


	// Método para detectar colisiones entre balas y naves invasoras
 private boolean collisionDetected(BulletImpl bullet, InvaderImpl invader) {
	    Rectangle bulletBounds = new Rectangle(bullet.getX(), bullet.getY(), bullet.getWidth(), bullet.getHeight());
	    Polygon invaderPolygon = new Polygon(invader.getXPoints(), invader.getYPoints(), invader.getNPoints());
	    boolean collision = bulletBounds.intersects(invaderPolygon.getBounds());
	    System.out.println("Collision: " + collision);
	    return collision;
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
 private void createNewRow() {
	    // Obtener la posición y y la altura de la primera fila de naves invasoras
	    InvaderImpl firstInvader = invaders.get(0);
	    int newY = firstInvader.getY() - firstInvader.getHeight() - 50; // Espacio entre filas
	    
	    // Eliminar las naves invasoras de la fila anterior
	    invaders.removeIf(invader -> invader.getY() <= firstInvader.getY());
	    
	    // Crear y agregar las nuevas naves invasoras a la lista
	    int startX = 50; // Posición inicial x de la primera nave
	    int spacing = 100; // Espacio entre las naves
	    for (int i = 0; i < 7; i++) {
	        int[] xPoints = {startX + i * spacing, startX + 25 + i * spacing, startX + 50 + i * spacing,
	                         startX + 25 + i * spacing, startX + 12 + i * spacing, startX + 37 + i * spacing}; 
	        int[] yPoints = {newY, newY + 50, newY, newY + 25, newY + 25, newY + 25}; 
	        
	        InvaderImpl invader = new InvaderImpl(this, startX + i * spacing, newY, xPoints, yPoints, invaderSpeed);
	        invaders.add(invader);
	    }
	    repaint();
	}
 
 private void updateScore() {
     int score = Integer.parseInt(scoreLabel.getText().substring(7)); // Obtener el puntaje actual
     score++; // Incrementar el puntaje en 1
     scoreLabel.setText("Score: " + score); // Actualizar el texto del JLabel con el nuevo puntaje
 }


 }
