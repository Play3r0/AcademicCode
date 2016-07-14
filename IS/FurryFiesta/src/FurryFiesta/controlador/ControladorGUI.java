package FurryFiesta.controlador;


/**
 * @author Carlos José Mora Amigo
 *
 *Clase encargada de gestionar el acceso a los demas controladores 
 *
 */


public class ControladorGUI 
{
	
	/**
	 * Atributos de la clase
	 */
	private ControladorPedidos controladorPed;
	private ControladorPersonal controladorEmp;
	private ControladorProductos controladorProd;
	private ControladorVentas controladorVentas;
	private ControladorSesion controladorSesion;

	/**
	 * Construtuctor de la clase
	 * @param controladorPed
	 * @param controladorEmp
	 * @param controladorProd
	 * @param controladorVentas
	 * @param controladorSesion
	 */
	public ControladorGUI(ControladorPedidos controladorPed,
			ControladorPersonal controladorEmp,
			ControladorProductos controladorProd,
			ControladorVentas controladorVentas, ControladorSesion controladorSesion) {
		this.controladorPed = controladorPed;
		this.controladorEmp = controladorEmp;
		this.controladorProd = controladorProd;
		this.controladorVentas = controladorVentas;
		this.controladorSesion = controladorSesion;
	}

	
	public ControladorPedidos getControladorPed() {
		return controladorPed;
	}

	public ControladorPersonal getControladorEmp() {
		return controladorEmp;
	}

	public ControladorProductos getControladorProd() {
		return controladorProd;
	}

	public ControladorVentas getControladorVentas() {
		return controladorVentas;
	}
	
	public ControladorSesion getControladorSesion() {
		return controladorSesion;
	}	
}
