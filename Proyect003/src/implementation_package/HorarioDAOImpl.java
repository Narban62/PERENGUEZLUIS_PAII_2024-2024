package implementation_package;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import interface_package.IHorarioDAO;
import model_package.Horario;

public class HorarioDAOImpl implements IHorarioDAO {

    private Connection conexion;

    public HorarioDAOImpl(Connection conexion) {
        this.conexion = conexion;
    }

    @Override
    public void create(Horario horario) {
        String query = "INSERT INTO horario (id_mat, id_alumno, id_profesor, hora_inicio, hora_fin, dia) VALUES (?, ?, ?, ?, ?, ?)";
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, horario.getIdMateria());
            statement.setInt(2, horario.getIdAlumno());
            statement.setInt(3, horario.getIdProfesor());
            statement.setInt(4, horario.getHoraInicio());
            statement.setInt(5, horario.getHoraFin());
            statement.setString(6, horario.getDia());
            
            int filasInsertadas = statement.executeUpdate();
            if (filasInsertadas > 0) {
                System.out.println("El horario fue creado exitosamente.");
            } else {
                System.out.println("No se pudo crear el horario.");
            }
        } catch (SQLException e) {
            System.out.println("Error al crear el horario: " + e.getMessage());
        }
    }

    @Override
    public List<Horario> read() {
        List<Horario> horarios = new ArrayList<>();
        String query = "SELECT id, id_mat, id_alumno, id_profesor, hora_inicio, hora_fin, dia FROM horario";

        try (PreparedStatement statement = conexion.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            while (resultSet.next()) {
                int id = resultSet.getInt("id");
                int idMat = resultSet.getInt("id_mat");
                int idAlumno = resultSet.getInt("id_alumno");
                int idProfesor = resultSet.getInt("id_profesor");
                int horaInicio = resultSet.getInt("hora_inicio");
                int horaFin = resultSet.getInt("hora_fin");
                String dia = resultSet.getString("dia");

                Horario horario = new Horario(id, idMat, idAlumno, idProfesor, horaInicio, horaFin, dia);
                horarios.add(horario);
            }
        } catch (SQLException e) {
            System.out.println("Error al leer los horarios: " + e.getMessage());
        }

        return horarios;
    }

    @Override
    public void update(Horario horario) {
        String query = "UPDATE horario SET id_mat = ?, id_alumno = ?, id_profesor = ?, hora_inicio = ?, hora_fin = ?, dia = ? WHERE id = ?";
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, horario.getIdMateria());
            statement.setInt(2, horario.getIdAlumno());
            statement.setInt(3, horario.getIdProfesor());
            statement.setInt(4, horario.getHoraInicio());
            statement.setInt(5, horario.getHoraFin());
            statement.setString(6, horario.getDia());
            statement.setInt(7, horario.getId());
            
            int filasActualizadas = statement.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("El horario fue actualizado exitosamente.");
            } else {
                System.out.println("No se pudo actualizar el horario.");
            }
        } catch (SQLException e) {
            System.out.println("Error al actualizar el horario: " + e.getMessage());
        }
    }

    @Override
    public void delete(Horario horario) {
        String query = "DELETE FROM horario WHERE id = ?";
        
        try (PreparedStatement statement = conexion.prepareStatement(query)) {
            statement.setInt(1, horario.getId());
            
            int filasEliminadas = statement.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("El horario fue eliminado exitosamente.");
            } else {
                System.out.println("No se pudo eliminar el horario.");
            }
        } catch (SQLException e) {
            System.out.println("Error al eliminar el horario: " + e.getMessage());
        }
    }
}
