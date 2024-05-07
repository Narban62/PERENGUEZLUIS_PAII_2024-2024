package default_package;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import com.jogamp.opengl.*;
import com.jogamp.opengl.awt.GLCanvas;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import javax.swing.GroupLayout.Alignment;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

/**
 * 
 * @author Perenguez Bastidas Luis Esteban
 * Titulo: Proyecto_001
 *
 */

public class MainFrame extends JFrame {
    private JPanel panel;
    private RendererPanel rendererPanel;
    private JButton buttonTriangulo;
    private JButton buttonCuadrado;
    private JButton buttonCirculo;

    public MainFrame() {
        super("OpenGL Shapes");
        this.setSize(800, 600);
        this.setLocationRelativeTo(null);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        panel = new JPanel();
        rendererPanel = new RendererPanel();

        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);

        buttonTriangulo = new JButton("Mostrar Triángulo");
        buttonTriangulo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarTriangulo();
            }
        });

        buttonCuadrado = new JButton("Mostrar Cuadrado");
        buttonCuadrado.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCuadrado();
            }
        });

        buttonCirculo = new JButton("Mostrar Círculo");
        buttonCirculo.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                mostrarCirculo();
            }
        });

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(buttonTriangulo)
                    .addComponent(buttonCuadrado)
                    .addComponent(buttonCirculo)
                    .addComponent(rendererPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                )
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(buttonTriangulo)
                .addComponent(buttonCuadrado)
                .addComponent(buttonCirculo)
                .addComponent(rendererPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
        );

        getContentPane().add(panel, BorderLayout.CENTER);
    }

    private void mostrarTriangulo() {
        rendererPanel.mostrarTriangulo();
    }

    private void mostrarCuadrado() {
        rendererPanel.mostrarCuadrado();
    }

    private void mostrarCirculo() {
        rendererPanel.mostrarCirculo();
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                MainFrame frame = new MainFrame();
                frame.setVisible(true);
            }
        });
    }
}

class RendererPanel extends JPanel {
    private Renderer renderer;

    public RendererPanel() {
        setLayout(null);
        setPreferredSize(new Dimension(400, 400));
    }

    public void mostrarTriangulo() {
        renderer = new Renderer(Renderer.Shape.TRIANGLE);
        renderizar(renderer);
    }

    public void mostrarCuadrado() {
        renderer = new Renderer(Renderer.Shape.SQUARE);
        renderizar(renderer);
    }

    public void mostrarCirculo() {
        renderer = new Renderer(Renderer.Shape.CIRCLE);
        renderizar(renderer);
    }

    private void renderizar(Renderer renderer) {
        GLCanvas canvas = new GLCanvas(new GLCapabilities(GLProfile.get(GLProfile.GL2)));
        canvas.setBounds(0, 0, getWidth(), getHeight());
        canvas.addGLEventListener(renderer);
        removeAll();
        add(canvas);
        revalidate();
        repaint();
    }
}

class Renderer implements GLEventListener {
    public enum Shape {
        TRIANGLE,
        SQUARE,
        CIRCLE
    }

    private Shape shape;

    public Renderer(Shape shape) {
        this.shape = shape;
    }

    @Override
    public void display(GLAutoDrawable drawable) {
        GL2 gl = drawable.getGL().getGL2();
        gl.glClear(GL.GL_COLOR_BUFFER_BIT);
        gl.glLoadIdentity();

        switch (shape) {
            case TRIANGLE:
                // Renderiza un triángulo
                gl.glBegin(GL.GL_TRIANGLES);
                gl.glColor3f(1.0f, 0.0f, 0.0f); // Rojo
                gl.glVertex2f(-0.5f, -0.5f);
                gl.glColor3f(0.0f, 1.0f, 0.0f); // Verde
                gl.glVertex2f(0.5f, -0.5f);
                gl.glColor3f(0.0f, 0.0f, 1.0f); // Azul
                gl.glVertex2f(0.0f, 0.5f);
                gl.glEnd();
                break;
            case SQUARE:
                // Renderiza un cuadrado
                gl.glBegin(GL.GL_TRIANGLE_FAN);
                
                gl.glColor3f(1.0f, 0.0f, 0.0f); // Rojo
                gl.glVertex2f(-0.5f, -0.5f);
                gl.glColor3f(0.0f, 1.0f, 0.0f); // Verde
                gl.glVertex2f(0.5f, -0.5f);
                gl.glColor3f(0.0f, 0.0f, 1.0f); // Azul
                gl.glVertex2f(0.5f, 0.5f);
                gl.glColor3f(1.0f, 1.0f, 0.0f); // Amarillo
                gl.glVertex2f(-0.5f, 0.5f);
                gl.glEnd();
                break;
            case CIRCLE:
                // Renderiza un círculo
                float radius = 0.5f;
                int numSegments = 100;
                gl.glBegin(GL2.GL_TRIANGLE_FAN);
                gl.glColor3f(1.0f, 0.0f, 0.0f); // Rojo
                gl.glVertex2f(0.0f, 0.0f); // Centro del círculo
                for (int i = 0; i <= numSegments; i++) {
                    double angle = 2.0 * Math.PI * i / numSegments;
                    float x = (float) (radius * Math.cos(angle));
                    float y = (float) (radius * Math.sin(angle));
                    gl.glVertex2f(x, y);
                }
                gl.glEnd();
                break;
        }
        gl.glFlush();
    }

    @Override
    public void dispose(GLAutoDrawable drawable) {}

    @Override
    public void init(GLAutoDrawable drawable) {}

    @Override
    public void reshape(GLAutoDrawable drawable, int x, int y, int width, int height) {}
}
