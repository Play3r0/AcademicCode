package FurryFiesta.modelo.personal;

import FurryFiesta.vista.Observador;

/**
 * @author Jose Miguel y Carlos Basco
 *
 */
public interface FachadaPersonal {
	
	/**
	 * Metodo que agrega un elemento personal a la base de datos
	 * @param p - el personal a agregar
	 * @throws Exception - si ya existe el personal
	 */
	public void altaPersonal(Personal p) throws Exception;
	
	/**
	 * Metodo que desactiva un elemento personal en la base de datos
	 * @param id - el id del personal a dar de baja
	 * @throws Exception - si el id no esta asociado a ningun personal
	 */
	public void bajaPersonal(int id) throws Exception;
	
	/**
	 * Metodo que cambia la informacion de un elemento personal en la base de datos
	 * @param p - el personal a modificar
	 * @throws Exception - si el personal no existe en la base de datos
	 */
	public void modificarPersonal(Personal p) throws Exception;
	
	/**
	 * Metodo que proporciona a la vista informacion sobre un elemento Personal de la base de datos
	 * @param id - el id del personal a consultar
	 * @throws Exception - si el id no esta asociado a ningun personal
	 */
	public void consultarDatosPersonales(int id) throws Exception;
	
	/**
	 * Metodo para agregar el observador de Personal
	 * @param observador - el observador a agregar
	 */
	public void addObserver(Observador observador);
	
	/**
	 * Metodo para iniciarlizar los valores de Personal
	 * @throws Exception - comunica un posible error en la base de datos
	 */
	public void inicializa() throws Exception;
}
