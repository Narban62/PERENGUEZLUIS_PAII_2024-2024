package implementation_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import interface_package.IMateriaDAO;
import model_package.Materia;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

public class MateriaDAOImpl implements IMateriaDAO {

    private Connection conexion;

    public MateriaDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(Materia materia) {
        String query = "INSERT INTO materia (name, description, level) VALUES (?, ?, ?)";
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, materia.getName());
            statement.setString(2, materia.getDescription());
            statement.setInt(3, materia.getLevel());
            
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("La materia fue creada exitosamente.");
            } else {
                System.out.println("No se pudo crear la materia.");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear la materia: " + e.getMessage());
        }
    }

    @Override
    public List<Materia> read() {
        List<Materia> materias = new ArrayList<>();
        String query = "SELECT id, name, description, level FROM materia";

        try (PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                String name = resultSet.getString("name");
                String description = resultSet.getString("description");
                int level = resultSet.getInt("level");

                Materia materia = new Materia(id, name, description, level);
                materias.add(materia);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer las materias: " + e.getMessage());
        }

        return materias;
    }

    @Override
    public void update(Materia materia) {
        String query = "UPDATE materia SET name = ?, description = ?, level = ? WHERE id = ?";
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setString(1, materia.getName());
            statement.setString(2, materia.getDescription());
            statement.setInt(3, materia.getLevel());
            statement.setInt(4, materia.getId());
            
            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("La materia fue actualizada exitosamente.");
            } else {
                System.out.println("No se pudo actualizar la materia.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar la materia: " + e.getMessage());
        }
    }

    @Override
    public void delete(Materia materia) {
        String query = "DELETE FROM materia WHERE id = ?";
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, materia.getId());
            
            int filasEliminadas = statement.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("La materia fue eliminada exitosamente.");
            } else {
                System.out.println("No se pudo eliminar la materia.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar la materia: " + e.getMessage());
        }
    }
}
