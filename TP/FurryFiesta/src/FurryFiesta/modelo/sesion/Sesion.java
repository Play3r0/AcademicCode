package FurryFiesta.modelo.sesion;

import FurryFiesta.modelo.personal.Personal;

/**
 * @author Jose Miguel y Carlos Basco
 *
 */
public class Sesion
{
	private Personal _personal;
	
	/**
	 * Método para crear una Sesion
	 * @param personal
	 */
	public Sesion(Personal personal) {
		_personal = personal;
	}
	
	/**
	 * Metodo que devuelve el Personal asociado a una Sesion
	 * @return Personal asociado a la Sesion
	 */
	public Personal getPersonal() {
		return _personal;
	}
}
