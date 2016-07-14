package FurryFiesta.modelo.sesion;

import FurryFiesta.modelo.personal.Personal;
import FurryFiesta.vista.Observador;

/**
 * @author Jose Miguel y Carlos Basco
 *
 */
public interface SASesion 
{
	/**
	 * Intenta iniciar sesion con la informacion de un Personal de la base de datos
	 * @param personal - Personal que contiene la informacion para el intento de inicio de sesion
	 * @throws Exception - Si no existe un Personal que coincida con la informacion introducida
	 */
	public void iniciarSesion(Personal personal) throws Exception;
	
	/**
	 * Sustituye la conexion creada por otro inicio de sesion por una nueva conexion para otro Personal
	 * @param personal - Personal que contiene la informacion para el nuevo intento de inicio de sesion
	 * @throws Exception - Si no existe un Personal que coincida con la informacion introducida
	 */
	public void cambiarSesion(Personal personal) throws Exception;
	
	/**
	 * Termina la conexion establecida en un inicio de sesion anterior
	 * @throws Exception - Si no hay ninguna conexion o por fallo de la base de datos
	 */
	public void desconectar() throws Exception;
	
	/**
	 * Activa la aplicacion para el Rango cliente
	 */
	public void cliente();
	
	/**
	 * Metodo para agregar el observador de Personal
	 * @param observador - el observador a agregar
	 */
	void addObserver(Observador observador);
}
