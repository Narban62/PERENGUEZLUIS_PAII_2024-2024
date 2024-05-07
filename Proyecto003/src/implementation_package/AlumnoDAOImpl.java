package implementation_package;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import interface_package.IAlumnoDAO;
import model_package.Alumno;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class AlumnoDAOImpl implements IAlumnoDAO {

    private Connection conexion;

    public AlumnoDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(Alumno alumno) {
        String query = "INSERT INTO alumno (name, lastname, age) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, alumno.getName());
            statement.setString(2, alumno.getLastname());
            statement.setInt(3, alumno.getAge());
            
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("El alumno fue creado exitosamente.");
            } else {
                System.out.println("No se pudo crear el alumno.");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear el alumno: " + e.getMessage());
        }
    }

    @Override
    public List<Alumno> read() {
        List<Alumno> alumnos = new ArrayList<>();
        String query = "SELECT id, name, lastname, age FROM alumno";

        try (PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                int age = resultSet.getInt("age");

                Alumno alumno = new Alumno(id, name, lastname, age);
                alumnos.add(alumno);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer los alumnos: " + e.getMessage());
        }

        return alumnos;
    }

    @Override
    public void update(Alumno alumno) {
        String query = "UPDATE alumno SET name = ?, lastname = ?, age = ? WHERE id = ?";
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, alumno.getName());
            statement.setString(2, alumno.getLastname());
            statement.setInt(3, alumno.getAge());
            statement.setInt(4, alumno.getId());
            
            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("El alumno fue actualizado exitosamente.");
            } else {
                System.out.println("No se pudo actualizar el alumno.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el alumno: " + e.getMessage());
        }
    }

    @Override
    public void delete(Alumno alumno) {
        String query = "DELETE FROM alumno WHERE id = ?";
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, alumno.getId());
            
            int filasEliminadas = statement.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("El alumno fue eliminado exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el alumno.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el alumno: " + e.getMessage());
        }
    }
}

