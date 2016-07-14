package FurryFiesta.modelo.productos;

import java.util.ArrayList;

/**
 * @author Sergio
 * 
 * Esta interfaz se encarga de enumerar todos los métodos necesarios 
 * para la comunicación con la base de datos en el módulo Producto.
 *
 */
public interface DAOProductos {

	/**
	 * Inserta un Producto pasado por Parámetros
	 * 
	 * @param producto - Producto que se va a insertar en la base de datos
	 * @throws Exception - Si no se ha podido realizar la inserción
	 */
	public void insertaProducto(Producto producto) throws Exception;
	
	/**
	 * Elimina un producto de la base de datos poniendo a 0 su campo disponible
	 * 
	 * @param id - Identificador del producto
	 * @throws Exception - En caso de que no se pueda eliminar
	 */
	public void eliminaProducto(Integer id) throws Exception;
	
	/**
	 * Este método busca un Producto utilizando su identificador
	 * 
	 * @param id - Identificador para buscar el Producto
	 * @return - El Producto con todos sus datos
	 * @throws Exception - Si no se ha podido encontrar el Producto
	 */
	public Producto buscaProducto(Integer id) throws Exception;
	
	/**
	 * Este método busca un Producto por el nombre
	 * 
	 * @param nombre - Nombre para buscar el Producto
	 * @return - El Producto con todos los datos
	 * @throws Exception - Si no se ha podido encontrar el Producto
	 */
	public Producto buscaProductoNombre(String nombre) throws Exception;
	
	/**
	 * Realiza una consulta de los Productos filtrados por categoría
	 * 
	 * @param categoria - Categoría de los productos para filtrar la consulta
	 * @return - Devuelve una lista con los Productos filtrados por categoría
	 * @throws Exception
	 */
	public ArrayList<Object> consultarProductosCategoria(String categoria) throws Exception;
	
	/**
	 * Realiza una consulta de todo los Productos de la base de datos
	 * 
	 * @return - Devuelve una lista con los Productos
	 * @throws Exception - En caso de que no pueda generarse la lista
	 */
	public ArrayList<Object> consultarProductos() throws Exception;
	
	/**
	 * Este método modifica en la base de datos el Producto que llega por parámetros
	 * 
	 * @param producto - Producto con las modificaciones actualizdas
	 * @throws Exception - En caso de que no se puedan guardar las modificaciones
	 */
	public void modificaProducto(Producto producto) throws Exception;
	
	/**
	 * Actualiza el stock de los productos asociados a una Pedido
	 * 
	 * @param idPedido - Identificador de la Pedido
	 * @throws Exception - En caso de que no pueda realizarse la actualización
	 */
	public void actualizarStockPedido(int idPedido) throws Exception;
	
	/**
	 * Actualiza el stock de los productos asociados a una Venta
	 * 
	 * @param idVenta - Identificador de la Venta
	 * @throws Exception - En caso de que no pueda realizarse la actualización
	 */
	public void actualizarStockVenta(int idVenta) throws Exception;
	
	/**
	 * Este método comprueba si hay stock suficiente
	 * 
	 * @param idProducto - Identificador del Producto
	 * @param cantidad - Cantidad de artículos de un Producto
	 * @throws Exception - Si falla la comprobación
	 */
	public void comprobarStockProductos(int idProducto, int cantidad) throws Exception;
}
