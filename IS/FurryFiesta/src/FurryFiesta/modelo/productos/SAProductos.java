package FurryFiesta.modelo.productos;

import java.util.ArrayList;

import FurryFiesta.modelo.pedido.Pedido;
import FurryFiesta.modelo.venta.Venta;
import FurryFiesta.vista.Observador;

/**
 * @author Omar LLundo & Enrique Laguna.
 *
 */
public interface SAProductos {
	
	/**
	 * Metodo que agrega un producto a la base de datos.
	 * @param p producto a agregar.
	 * @throws Exception
	 */
	public void agregarProductoSA(Producto p) throws Exception;
	
	/**
	 * Metodo que elimina un prodcuto de la base de datos
	 * @param id del producto a eliminar.
	 * @throws Exception
	 */
	public void eliminarProductoSA(Integer id) throws Exception;
	
	/**
	 * Metodo que modifica un producto de la base de datos
	 * @param p producto a modificar.
	 * @throws Exception
	 */
	public void modificarProductoSA(Producto p) throws Exception;
	
	/**
	 * Metodo que consulta un producto de la base de datos
	 * @param id del producto a consultar
	 * @throws Exception
	 */
	public void consultarProductoSA(Integer id) throws Exception;
	
	/**
	 * Metodo que busca por filtro un producto
	 * @param f filtro por el que buscar
	 * @throws Exception
	 */
	public void buscarFiltroProductoSA(String f) throws Exception;
	
	/** Metodo que añade el observador de prodcutos.
	 * @param observador a añadir
	 */
	public void addObserver(Observador observador);
	
	/**
	 * Metodo que inicializa los productos.
	 * @throws Exception
	 */
	public void inicializa() throws Exception;
	
	/**
	 * Metodo que actualiza el stock de los pedidos.
	 * @param idPedido del pedido a actualizar.
	 * @throws Exception
	 */
	public void actualizarStockPedido(int idPedido) throws Exception;
	
	/**
	 * Metodo que actualiza el stock de las ventas realizadas.
	 * @param idVenta id de venta a actualizar.
	 * @throws Exception
	 */
	public void actualizarStockVenta(int idVenta) throws Exception;
	
	/**Metodo que inicializa los productos 
	 * @throws Exception
	 */
	public void inicializa2() throws Exception;

	/** Metodo que calcula el precio del pedido.
	 * @param p pedido, para calcular el precio total.
	 * @param idProductos lisa de productos del pedido.
	 * @param numProductos lista del numero de productos de ese pedido
	 * @throws Exception
	 */
	void calcularPrecioPedido(Pedido p, ArrayList<Integer> idProductos,
			ArrayList<Integer> numProductos) throws Exception;
	/**
	 * Metodo que calcula el precio de una venta realizada.
	 * @param venta - venta a calcular el precio.
	 * @param idProductos - lista de los productos de la venta.
	 * @param numProductos - lista del numero de productos de la venta.
	 * @throws Exception
	 */
	void calcularPrecioVenta(Venta venta, ArrayList<Integer> idProductos,
			ArrayList<Integer> numProductos) throws Exception;
	
	/**
	 * Metodo que busca un producto por su nombre.
	 * @param nombre -  nombre del producto a buscar.
	 * @throws Exception
	 */
	void buscarProductoNombre(String nombre)throws Exception;

	/**Comprueba el stock de los productos.
	 * @param idProductos - lista de los id de los productos del stock
	 * @param numStock - lista del numero de productos del stock
	 * @throws Exception
	 */
	void comprobarStockProductos(ArrayList<Integer> idProductos,
			ArrayList<Integer> numStock) throws Exception;

}
