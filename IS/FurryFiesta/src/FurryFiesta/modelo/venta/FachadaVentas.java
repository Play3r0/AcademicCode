package FurryFiesta.modelo.venta;

import java.util.ArrayList;
import FurryFiesta.vista.Observador;

/**
 * interface para la clase intermedia encargada de la comunicacion entre la base de datos  y la aplicacion 
 * @author Carlos
 *
 */
public interface FachadaVentas {
	
	/**
	 * metodo para agregar una venta
	 * @param venta - la venta a añadir
	 * @param idProductos - un array con los productos
	 * @param listaStock - un array con la cantidad de productos
	 * @throws Exception si hay una error al añadir la venta
	 */
	void agregarVenta(Venta venta, ArrayList<Integer> idProductos,
			ArrayList<Integer> listaStock) throws Exception;
	
	/**
	 * metodo que devuelve una venta de la base de datos
	 * @param id - la id de la venta
	 * @throws Exception - si hay error en la base de datos
	 */
	public void consultarVenta(Integer id) throws Exception;
	
	/**
	 * metodo para anadir el observador de ventas
	 * @param observador - el observador de ventas
	 */
	public void addObserver(Observador observador);
	
	/**
	 * metodo para crear un archivo con la informacion de la venta
	 * @param direccion - la direccion donde se quiere guardar el archivo
	 * @param nombreArchivo - el nombre del archivo
	 * @throws Exception - si hay un error con la base de datos o al escribir el fichero
	 */
	public void exportarVenta(String direccion, String nombreArchivo) throws Exception;

	/**
	 * metodo que coge la infomacion basica de las ventas
	 * @throws Exception si hay problemas con la base de datos
	 */
	public void historicoVenta() throws Exception;

	/**
	 * metodo para obtener la informacion del valor total de la venta
	 * @throws Exception - si hay un error en la base de datos
	 */
	public void calcularTotalVentas() throws Exception;

}
