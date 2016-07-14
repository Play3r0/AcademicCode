package FurryFiesta.modelo.pedido;

import java.util.ArrayList;

import FurryFiesta.vista.Observador;

/**
 * @author Omar LLundo & Enrique Laguna.
 *
 */
public interface SAPedidos {

	/**
	 * Metodo que agrega un pedido a la base de datos.
	 * @param p nombre del producto
	 * @param idProductos id de los productos del pedido p
	 * @param numProductos numero de productos del pedido p
	 * @throws Exception si ya existe el pedido.
	 */
	public void agregarPedidoSA(Pedido pedido, ArrayList<Integer> idProductos, ArrayList<Integer> numProductos) throws Exception;

	/**
	 * MEtodo que confirma un pedido en la base de datos.
	 * @param id identificador del pedido a confirmar.
	 * @throws Exception
	 */
	public void confirmarPedidoSA(Integer id) throws Exception;
	
	/**
	 * Metodo que cancela un pedido.
	 * @param id identificador del pedido a cancelar.
	 * @throws Exception 
	 */
	public void cancelarPedidoSA(Integer id) throws Exception;

	/** Metodo que consulta un pedido.
	 * @param id identificador de pedido a consultar.
	 * @throws Exception
	 */
	public void consultarPedidoSA(Integer id) throws Exception;

	/** Metodo para buscar pedidos asociados a un filtro
	 * @param f filtro por el que buscar
	 * @throws Exception
	 */
	public void buscarFiltroPedidoSA(String f) throws Exception;
	
	/**Metodo para añadir el observador de pedidos
	 * @param observador a añadir
	 */
	public void addObserver(Observador observador);

	/**Metodoque calcula el total de pedidos en nuestra base de datos.
	 * @throws Exception
	 */
	void calcularTotalPedidos() throws Exception;
}
