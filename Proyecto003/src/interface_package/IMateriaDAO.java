package interface_package;

import java.util.List;

import model_package.Alumno;
import model_package.Materia;

public interface IMateriaDAO {
	
	public void create(Materia materia);
	
	List<Materia> read();
	
	public void update(Materia materia);
	
	public void delete(Materia materia);

}
