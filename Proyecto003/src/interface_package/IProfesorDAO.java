package interface_package;

import java.util.List;

import model_package.Profesor;

public interface IProfesorDAO {
	
	public void create(Profesor profesor);
		
	List<Profesor> read();
	
	public void update(Profesor profesor);
	
	public void delete(Profesor profesor);

}
