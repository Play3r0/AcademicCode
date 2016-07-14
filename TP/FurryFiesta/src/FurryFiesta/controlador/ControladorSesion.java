package FurryFiesta.controlador;

import FurryFiesta.modelo.personal.Personal;
import FurryFiesta.modelo.sesion.FachadaSesionImp;
import FurryFiesta.vista.Observador;

/**
 * @author Carlos José Mora Amigo
 *
 *Clase encargada de gestionar las peticiones solicitadas al módulo de funciones de sesion
 *
 */

public class ControladorSesion {
	
	/**
	 * Atributos de la clase
	 */
	private FachadaSesionImp fachadaSesion;
	
	/**
	 * Constructor de la clase
	 * @param fachadaSesion
	 */
	public ControladorSesion(FachadaSesionImp fachadaSesion){
		this.fachadaSesion = fachadaSesion;
	}
	
	/**
	 * Funcion encargada de registrar el observador
	 * @param observador
	 */
	public void registerObserver(Observador observador){
		this.fachadaSesion.addObserver(observador);
	}
	
	/**
	 * Método encargado de gestionar la petición de iniciar sesión
	 * @param empleado
	 * @throws Exception
	 */
	public void executeIniciarSesion(Personal empleado) throws Exception{
		try{
			
			this.fachadaSesion.iniciarSesion(empleado);
		}
		catch (Exception y){
			throw y;
		}
		
	}
	
	/**
	 * Método encargado de gestionar la petición de cambiar de sesión
	 * @param empleado
	 * @throws Exception
	 */
	public void executeCambiarSesion(Personal empleado) throws Exception{
		try{
			
			this.fachadaSesion.cambiarSesion(empleado);
		}
		catch (Exception y){
			throw y;
		}
	}
	
	/**
	 * Método encargado de gestionar la petición de desconectar de la sesion actual
	 * @throws Exception
	 */
	public void executeDesconectar() throws Exception {

		try{
			this.fachadaSesion.desconectarSesion();
		}
		catch (Exception ex){
			throw ex;
		}
	}

	public void executeCliente() {
		
		this.fachadaSesion.cliente();
	}
}
