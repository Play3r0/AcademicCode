package FurryFiesta.modelo.venta;

import java.util.ArrayList;

/**
 * @author Sergio
 * 
 * Esta interfaz se encarga de enumerar todos los métodos necesarios 
 * para la comunicación con la base de datos en el módulo Venta.
 *
 */
public interface DAOVentas {

	/**
	 * metodo para comunicar a la base de datos que debe agregar una nueva venta
	 * @param venta - la venta a agregar
	 * @param _listaProductosPedido - la lista con los productos
	 * @param lista1 - la lista con la cantidad de productos
	 * @throws Exception - si hay error con la base de datos
	 */
	void insertarVenta(Venta venta, ArrayList<Integer> _listaProductosPedido, 
			ArrayList<Integer> lista1) throws Exception;
	
	/**
	 * metodo para pedir una consulta a la base de datos sobre una venta
	 * @param id - la id de la venta
	 * @return La venta con la id suministrada
	 * @throws Exception - si la venta no existe o hay un error en la base de datos
	 */
	public Venta consultarVenta(int id) throws Exception;
	
	/**
	 * metodo para consultar los productos asociados a una venta
	 * @param id - la id de la venta
	 * @return un array list con productos pedidos
	 * @throws Exception si no existe o hay error con la base de datos
	 */
	public ArrayList<Object> consultarProductosVenta(int id) throws Exception;
	
	/**
	 * metodo que manda una consulta a la base de datos con todas las ventas relizadas
	 * @return - un array list con las ventas
	 * @throws Exception - si hay error con la base de datos
	 */
	public ArrayList<Object> historicoVentas() throws Exception;
	
	/**
	 * Metodo que devuelve el id de un personal
	 * @param idTerminal - la id del terminal que se conecto
	 * @return la id del personal
	 * @throws Exception - si hay error con la base de datos o la terminal no existe
	 */
	public int consultarPersonal(String idTerminal) throws Exception;

	/**
	 * Este método cálcula el total de dinero generado por las ventas.
	 * 
	 * @return - Un double con el total de las ventas
	 * @throws Exception - Si no se puede generar el total de las ventas
	 */
	double calcularTotalVentas() throws Exception;
}
