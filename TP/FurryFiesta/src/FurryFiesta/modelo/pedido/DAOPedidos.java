package FurryFiesta.modelo.pedido;

import java.util.ArrayList;

/**
 * @author Sergio
 * 
 * Esta interfaz se encarga de enumerar todos los métodos necesarios 
 * para la comunicación con la base de datos en el módulo Pedido.
 *
 */
public interface DAOPedidos {

	/**
	 * Esta método se encarga de insertar Pedidos en la base de datos.
	 * 
	 * @param pedido - Pedido que se quiere insertar.
	 * @param idProductos - Array con todos los productos asociados al pedido
	 * @param numProductos - Array con el número de artículos para cada producto
	 * @throws Exception - Si no se puede realizar la inserción.
	 */
	public void agregarPedido(Pedido pedido, ArrayList<Integer> idProductos, ArrayList<Integer> numProductos) throws Exception;
	
	/**
	 * Comprueba el estado de un pedido
	 * 
	 * @param id - Identificador del pedido
	 * @return True si está confirmado, False si no lo está
	 * @throws Exception - Si no se puede comprobar el estado de un pedido
	 */
	public boolean comprobarPedido(int id) throws Exception;
	
	/**
	 * Confirma un pedido
	 * 
	 * @param id - Identificador del pedido que queremos confirmar
	 * @param confirmado - True para confirmar el pedido
	 * @throws Exception - Si no se ha podido confirmar
	 */
	public void confirmarPerdido(int id, boolean confirmado) throws Exception;
	
	/**
	 * Este método eliminar un pedido NO CONFIRMADO de la base de datos.
	 * 
	 * @param id - Identidificador del pedido.
	 * @throws Exception - En caso de que no haya podido eliminarse el pedido
	 */
	public void eliminarPedido(int id) throws Exception;
	
	/**
	 * Hace una consulta a la base de datos para recoger todos los pedidos
	 * 
	 * @return - Devuelve una lista con todos los pedidos.
	 * @throws Exception - En caso de que no se haya podido generar la lista.
	 */
	public ArrayList<Object> consultarPedidos() throws Exception;
	
	/**
	 * Realiza una consultar de un Pedido a través de su identificador
	 * 
	 * @param id - Identifcador del pedido
	 * @return - El pedido completo asociado a ese identificador
	 * @throws Exception - Si no ha podido recoger los datos del pedido
	 */
	public Pedido consultarPedido(Integer id) throws Exception;
	
	/**
	 * Este método genera una lista con los identificadores de los productos asociados al Pedido
	 * 
	 * @param idPedido - Identificador del pedido
	 * @return - Una lista con los identificadores
	 * @throws Exception - Si no ha podido generar la lista de identificadores
	 */
	public ArrayList<Object> consultarProductosPedido(int id) throws Exception;
	
	/**
	 * Este método recoge el identificador de producto y la cantidad de cada Producto asociado a un Pedido.
	 * 
	 * @param idPedido - Identificador del pedido
	 * @return - Una lista con los identificadores y las cantidades de cada producto
	 * @throws Exception - Si no puede generar la lista.
	 */
	public ArrayList<ArrayList<Integer>> actualizarStockPedido(int idPedido) throws Exception;
	
	/**
	 * Consula a la base de datos para conocer qué usuario ha iniciado sesión en el terminal
	 * 
	 * @param idTerminal - Identificador del terminal en el que se ha iniciado sesión
	 * @return - El identificador del usuario que ha iniciado sesión
	 * @throws Exception - Si no se puede recoger el identificador
	 */
	public int consultarPersonal(String idTerminal) throws Exception;
	
	/**
	 * Calcula el total de los gastos generados en realizar Pedidos
	 * 
	 * @return - El total de los gastos en Pedidos
	 * @throws Exception - Si no se ha podido realizar el cálculo
	 */
	double calcularTotalPedidos() throws Exception;
}
