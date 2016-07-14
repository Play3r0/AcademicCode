package FurryFiesta.controlador;

import java.util.ArrayList;

import FurryFiesta.modelo.productos.FachadaProductosImp;
import FurryFiesta.modelo.venta.FachadaVentasImp;
import FurryFiesta.modelo.venta.Venta;
import FurryFiesta.vista.Observador;

/**
 * @author Carlos José Mora Amigo
 *
 *Clase encargada de gestionar las peticiones solicitadas al módulo de funciones de ventas
 *
 */

public class ControladorVentas {
	
	/**
	 * Atributos de la clase
	 */
	private FachadaVentasImp fachadaVenta;
	private FachadaProductosImp fachadaProducto;
	
	/**
	 * Constructor de la clase
	 * @param fachadaVenta
	 * @param fachadaProducto
	 */
	public ControladorVentas(FachadaVentasImp fachadaVenta, FachadaProductosImp fachadaProducto){
		this.fachadaVenta = fachadaVenta;
		this.fachadaProducto = fachadaProducto;
	}
	
	/**
	 * Funcion encargada de registrar el observador
	 * @param observador
	 */
	public void registerObserver(Observador observador){
		this.fachadaVenta.addObserver(observador);
	}
	
	/**
	 * Método encargado de gestionar la petición de realizar una venta
	 * @param venta
	 * @param idProductos
	 * @param numProductos
	 * @throws Exception
	 */
	public void executeNuevaVEnta(Venta venta, ArrayList<Integer> idProductos, ArrayList<Integer> numProductos) throws Exception{
		try{
			
			this.fachadaProducto.comprobarStockProductos(idProductos, numProductos);
			this.fachadaProducto.calcularPrecioVenta(venta, idProductos, numProductos);
			this.fachadaVenta.agregarVenta(venta, idProductos, numProductos);
			this.fachadaProducto.actualizarStockVenta(venta.getId());
		}
		catch (Exception y){
			throw y;
		}
	}
	
	/**
	 * Método encargado de gestionar la petición de consultar todas las ventas realizadas
	 * @throws Exception
	 */
	public void executeHistoricoDeVentas() throws Exception{
		try{
			
			this.fachadaVenta.historicoVenta();
		}
		catch (Exception y){
			throw y;
		}
	}
	
	/**
	 * Método encargado de gestionar la petición de exportar a un archivo la información de una venta
	 * @param direccion
	 * @param nombreArchivo
	 * @throws Exception
	 */
	public void executeExportarVentas(String direccion, String nombreArchivo) throws Exception{
		try{
			
			this.fachadaVenta.exportarVenta(direccion, nombreArchivo);
		}
		catch (Exception y){
			throw y;
		}
	}
	
	/**
	 * Método encargado de gestionar la petición de consultar los datos de una venta
	 * @param id
	 * @throws Exception
	 */
	public void executeConsultarVenta(Integer id) throws Exception{
		try{
			
			this.fachadaVenta.consultarVenta(id);
		}
		catch (Exception y){
			throw y;
		}
	}
	
	/**
	 * Método encargado de gestionar la petición de consultar los datos de un producto
	 * @param id
	 * @throws Exception
	 */
	public void consultarProducto (Integer id) throws Exception
	{
		this.fachadaProducto.consultarProducto(id);
	}
	
	public void executeInicializaProductos() throws Exception {

		this.fachadaProducto.inicializa2();
	}

	public void registerObserverProductos(Observador observador) 
	{
		this.fachadaProducto.addObserver(observador);
	}
	
	/**
	 * Método encargado de gestionar la petición de consultar el total de los gastos generados en realizar Pedidos
	 * @throws Exception
	 */
	public void calcularTotalVentas() throws Exception {
		this.fachadaVenta.calcularTotalVentas();
	}
}
