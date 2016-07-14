package FurryFiesta.modelo.sesion;

import FurryFiesta.modelo.personal.Personal;

/**
 * @author Sergio
 * 
 * Esta interfaz se encarga de enumerar todos los métodos necesarios 
 * para la comunicación con la base de datos en el módulo Sesion.
 *
 */
public interface DAOSesion {

	/**
	 * Este método comprueba si el Personal está registrado y
	 * a continuación crea un registro para almacenar su sesión
	 * junto al identificador del Terminal.
	 * 
	 * @param personal - Usuario y password con las que se quiere iniciar sesión
	 * @param idTerminal - Identificador del terminal desde el que se quiere acceder al sistema
	 * @return El Personal con todos sus datos, incluyendo su identificador.
	 * @throws Exception - En caso de que no se haya podido iniciar sesión
	 */
	public Personal iniciarSesion(Personal personal, String idTerminal) throws Exception;
	
	/**
	 * Primero realizar una llamada para cerrar la sesión, 
	 * y continuación hace una segunda llamada para iniciar la nueva sesión.
	 * 
	 * @param personal - Usuario y password con las que se quiere iniciar sesión
	 * @param idTerminal - Identificador del terminal desde el que se quiere acceder al sistema
	 * @return El Personal con todos sus datos, incluyendo su identificador.
	 * @throws Exception - Si falla la desconexión o el inicio de sesión
	 */
	public Personal cambiarSesion(Personal personal, String idTerminal) throws Exception;
	
	/**
	 * Este método desconecta a un usuario de la base de datos.
	 * 
	 * @param idTerminal - Identificador del terminal desde el que se quiere acceder al sistema
	 * @throws Exception - Si no se ha podido desconectar al usuario
	 */
	public void desconectarSesion(String idTerminal) throws Exception;
}
