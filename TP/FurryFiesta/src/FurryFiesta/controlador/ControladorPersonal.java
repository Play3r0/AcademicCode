package FurryFiesta.controlador;

import FurryFiesta.modelo.personal.FachadaPersonalImp;
import FurryFiesta.modelo.personal.Personal;
import FurryFiesta.vista.Observador;

/**
 * @author Carlos José Mora Amigo
 *
 *Clase encargada de gestionar las peticiones solicitadas al módulo de funciones de personal
 *
 */

public class ControladorPersonal {
	
	/**
	 * Atributos de la clase
	 */
	private FachadaPersonalImp fachadaPersonal;
	
	/**
	 * Constructor de la clase
	 * @param fachadaPersonal
	 */
	public ControladorPersonal(FachadaPersonalImp fachadaPersonal){
		this.fachadaPersonal = fachadaPersonal;
	}
	
	/**
	 * Método encargado de registrar el observador
	 * @param observador
	 */
	public void registerObserver(Observador observador){
		this.fachadaPersonal.addObserver(observador);
	}
	
	/**
	 * Método encargado de gestionar la petición de dar de alta a un empleado
	 * @param empleado
	 * @throws Exception
	 */
	public void executeAltaPersonal(Personal empleado) throws Exception{
		try{
			
			this.fachadaPersonal.altaPersonal(empleado);
		}
		catch (Exception y){
			throw y;
		}
		
	}
	
	/**
	 * Método encargado de gestionar la petición de dar de baja a un empleado
	 * @param id
	 * @throws Exception
	 */
	public void executeBajaPersonal(int id) throws Exception{
		try{
			
			this.fachadaPersonal.bajaPersonal(id);
		}
		catch (Exception y){
			throw y;
		}
	}
	
	/**
	 * Método encargado de gestionar la petición de modificar los datos de un empleado
	 * @param p
	 * @throws Exception
	 */
	public void executeModificarPersonal(Personal p) throws Exception{
		try{
		
			this.fachadaPersonal.modificarPersonal(p);
		}
		catch (Exception y){
			throw y;
		}
	}
	
	/**
	 * Método encargado de gestionar la petición de consultar los datos de un empleado
	 * @param id
	 * @throws Exception
	 */
	public void executeConsultarDatosPersonales(int id) throws Exception {
		try{
			
			this.fachadaPersonal.consultarDatosPersonales(id);
		}
		catch (Exception y){
			throw y;
		}
	}
	
	public void executeInicializa() throws Exception
	{
		this.fachadaPersonal.inicializa();
	}
	
	
}
