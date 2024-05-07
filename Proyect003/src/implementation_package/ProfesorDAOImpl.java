package implementation_package;
import interface_package.IProfesorDAO;
import model_package.Profesor;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProfesorDAOImpl implements IProfesorDAO {

    private Connection conexion;

    public ProfesorDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(Profesor profesor) {
        String query = "INSERT INTO profesor (name, lastname, age) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, profesor.getName());
            statement.setString(2, profesor.getLastname());
            statement.setInt(3, profesor.getAge());
            
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("El profesor fue creado exitosamente.");
            } else {
                System.out.println("No se pudo crear el profesor.");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear el profesor: " + e.getMessage());
        }
    }

    @Override
    public List<Profesor> read() {
        List<Profesor> profesores = new ArrayList<>();
        String query = "SELECT id, name, lastname, age FROM profesor";

        try (PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String lastname = resultSet.getString("lastname");
                int age = resultSet.getInt("age");

                Profesor profesor = new Profesor(id, name, lastname, age);
                profesores.add(profesor);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer los profesores: " + e.getMessage());
        }

        return profesores;
    }

    @Override
    public void update(Profesor profesor) {
        String query = "UPDATE profesor SET name = ?, lastname = ?, age = ? WHERE id = ?";
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, profesor.getName());
            statement.setString(2, profesor.getLastname());
            statement.setInt(3, profesor.getAge());
            statement.setInt(4, profesor.getId());
            
            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("El profesor fue actualizado exitosamente.");
            } else {
                System.out.println("No se pudo actualizar el profesor.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el profesor: " + e.getMessage());
        }
    }

    @Override
    public void delete(Profesor profesor) {
        String query = "DELETE FROM profesor WHERE id = ?";
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, profesor.getId());
            
            int filasEliminadas = statement.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("El profesor fue eliminado exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el profesor.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el profesor: " + e.getMessage());
        }
    }
}

