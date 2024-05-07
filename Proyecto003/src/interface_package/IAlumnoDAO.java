package interface_package;

import java.util.List;

import model_package.Alumno;

public interface IAlumnoDAO {
	public void create(Alumno alumno);
	
	List<Alumno> read();
	
	public void update(Alumno alumno);
	
	public void delete(Alumno alumno);

}
