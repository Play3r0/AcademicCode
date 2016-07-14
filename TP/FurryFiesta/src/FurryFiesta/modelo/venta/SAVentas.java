package FurryFiesta.modelo.venta;

import java.util.ArrayList;
import FurryFiesta.vista.Observador;

public interface SAVentas {

	/**
	 * Metodo que agrega una venta al base de datos
	 * @param v - la venta a agragar
	 * @throws Exception - si hay error en la base de datos
	 */
	public void agregarVentaSA(Venta v, ArrayList<Integer> _listaProductosVenta,
			ArrayList<Integer> lista1) throws Exception;
		
	/**
	 * metodo que devuelve una venta de la base de datos
	 * @param id - la id de la venta
	 * @throws Exception - si hay error en la base de datos
	 */
	public void consultarVentaSA(Integer id) throws Exception;
	
	
	/**
	 * metodo que obtine una version resumida de las ventas, formato para un mes o un ano
	 * @throws Exception - si hay error con la base de datos
	 * @return
	 */
	public void historicoVentasSA() throws Exception;
	
	
	/**
	 *metodo que obtiene un historico y crea un txt de el 
	 * @throws Exception - si hay un error con la base de datos
	 */
	public void exportarVentaSA(String direccion, String nombreFichero) throws Exception;
	
	/**
	 * metodo para anadir el observador de ventas
	 * @param observador - el observador de ventas
	 */
	public void addObserver(Observador observador);
	
	/**
	 * metodo para inizializar los valores de la venta
	 * @throws Exception - si hay errores con la base de datos.
	 */
	public void inicializa() throws Exception;

	/**
	 * metodo para calcular el total de la venta
	 * @throws Exception - si hay errores con la base de datos
	 */
	public void calcularTotalVentas() throws Exception;
}
