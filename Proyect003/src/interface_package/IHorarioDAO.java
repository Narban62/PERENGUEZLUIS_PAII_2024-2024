package interface_package;

import java.util.List;

import model_package.Horario;

public interface IHorarioDAO {
	public void create(Horario horario);
	
	List<Horario> read();
	
	public void update(Horario horario);
	
	public void delete(Horario horario);

}
