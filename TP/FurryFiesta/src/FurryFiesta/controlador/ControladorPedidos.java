package FurryFiesta.controlador;

import java.util.ArrayList;

import FurryFiesta.modelo.pedido.FachadaPedidosImp;
import FurryFiesta.modelo.pedido.Pedido;
import FurryFiesta.modelo.productos.FachadaProductosImp;
import FurryFiesta.vista.Observador;

/**
 * @author Carlos José Mora Amigo
 *
 *Clase encargada de gestionar las peticiones solicitadas al módulo de funciones de pedidos
 *
 */

public class ControladorPedidos {
	
	/**
	 * Atributos de la clase
	 */
	private FachadaPedidosImp fachadaPedido;
	private FachadaProductosImp fachadaProducto;
	
	/**
	 * Constructor de la clase
	 */
	public ControladorPedidos(FachadaPedidosImp fachadaPedido, FachadaProductosImp fachadaProducto){
		this.fachadaPedido = fachadaPedido;
		this.fachadaProducto = fachadaProducto;
	}
	
	/**
	 * Funcion encargada de registrar el observador
	 * @param observador
	 */
	public void registerObserver(Observador observador){
		this.fachadaPedido.addObserver(observador);
	}
	
	/**
	 * Método encargado de gestionar la petición de agregar un pedido en la base de datos.
	 * @param p
	 * @param idProductos
	 * @param numProductos
	 * @throws Exception
	 */
	public void executeAgregarPedido(Pedido p, ArrayList<Integer> idProductos, ArrayList<Integer> numProductos) throws Exception{
		try{
			
			this.fachadaProducto.calcularPrecioPedido(p, idProductos, numProductos);
			this.fachadaPedido.agregarPedido(p, idProductos, numProductos);
		}
		catch (Exception y){
			throw y;
		}
	}
	
	/**
	 * Método encargado de gestionar la petición de cancelar un pedido
	 * @param id
	 * @throws Exception
	 */
	public void executeCancelarPedido(Integer id) throws Exception{
		try{
			
			this.fachadaPedido.cancelarPedido(id);
		}
		catch (Exception y){
			throw y;
		}
	}
	
	/**
	 * Método encargado de gestionar la petición de buscar un pedido
	 * @param f
	 * @throws Exception
	 */
	public void executeBuscarPedidoFiltro(String f) throws Exception{
		try{
			
			this.fachadaPedido.buscarFiltroPedido(f);
		}
		catch (Exception y){
			throw y;
		}

	}
	
	/**
	 * Método encargado de gestionar la petición de confirmar un pedido
	 * @param id
	 * @throws Exception
	 */
	public void executeConfirmarPedido(Integer id) throws Exception{
		try{
			
			this.fachadaPedido.confirmarPedido(id);
			this.fachadaProducto.actualizarStockPedido(id);
		}
		catch (Exception y){
			throw y;
		}
	}
	
	/**
	 * Método encargado de gestionar la petición de consultar un pedido
	 * @param id
	 * @throws Exception
	 */
	public void consultarPedido(Integer id) throws Exception {
		try{
			
			this.fachadaPedido.consultarPedido(id);
		}
		catch (Exception y){
			throw y;
		}
	}
	
	public void executeInicializa() throws Exception 
	{
		this.fachadaPedido.inicializa();
	}

	public void executeInicializaProductos() throws Exception {

		this.fachadaProducto.inicializa2();
	}

	public void registerObserverProductos(Observador observador) 
	{
		this.fachadaProducto.addObserver(observador);
	}
	
	/**
	 * Método encargado de gestionar la petición de consultar un producto
	 * @param id
	 * @throws Exception
	 */
	public void consultarProducto (Integer id) throws Exception
	{
		this.fachadaProducto.consultarProducto(id);
	}
	
	/**
	 * Método encargado de gestionar la petición de consultar el total de los gastos generados en realizar Pedidos
	 * @throws Exception
	 */
	public void calcularTotalPedidos() throws Exception {
		this.fachadaPedido.calcularTotalPedidos();
	}
}
