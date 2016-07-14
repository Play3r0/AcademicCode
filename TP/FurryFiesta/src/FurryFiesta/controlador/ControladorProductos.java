package FurryFiesta.controlador;

import FurryFiesta.modelo.productos.FachadaProductosImp;
import FurryFiesta.modelo.productos.Producto;
import FurryFiesta.vista.Observador;

/**
 * @author Carlos José Mora Amigo
 *
 *Clase encargada de gestionar las peticiones solicitadas al módulo de funciones de productos
 *
 */

public class ControladorProductos {
	
	/**
	 * Atributos de la clase
	 */
	private FachadaProductosImp fachadaProducto;
	
	/**
	 * Constructor de la clase
	 * @param fachadaProducto
	 */
	public ControladorProductos(FachadaProductosImp fachadaProducto){
		this.fachadaProducto = fachadaProducto;
	}
	
	/**
	 * Método encargado de registrar el observador
	 * @param observador
	 */
	public void registerObserver(Observador observador){
		this.fachadaProducto.addObserver(observador);
	}
	
	/**
	 * Método encargado de gestionar la petición de consultar los datos de un producto
	 * @param id
	 * @throws Exception
	 */
	public void executeConsultarProducto(Integer id) throws Exception{
		try{
		
			this.fachadaProducto.consultarProducto(id);
		}
		catch (Exception y){
			throw y;
		}
	}
	
	/**
	 * Método encargado de gestionar la petición de eliminar un producto
	 * @param id
	 * @throws Exception
	 */
	public void executeEliminarProducto(Integer id) throws Exception{
		try{
			
			this.fachadaProducto.eliminarProducto(id);
		}
		catch(Exception y){
			throw y;
		}
		
	}
	
	/**
	 * Método encargado de gestionar la petición de agregar un producto
	 * @param p
	 * @throws Exception
	 */
	public void executeAGregarProducto(Producto p) throws Exception{
		try{
			
			this.fachadaProducto.agregarProducto(p);
		}
		catch(Exception y){
			throw y;
		}
	}
	
	/**
	 * Método encargado de gestionar la petición de modifcar los datos de un producto
	 * @param p
	 * @throws Exception
	 */
	public void executeModificarProducto(Producto p) throws Exception{
		try{
			this.fachadaProducto.modificarProducto(p);
		}
		catch(Exception y){
			throw y;
		}
	}
	
	/**
	 * Método encargado de gestionar la petición de busqueda de un producto por filtros
	 * @param f
	 * @throws Exception
	 */
	public void executeBuscarFiltroProducto(String f) throws Exception{
		try{
		
			this.fachadaProducto.buscarFiltroProducto(f);
		}
		catch (Exception y){
			throw y;
		}
	}
	
	public void executeInicializa() throws Exception
	{
		this.fachadaProducto.inicializa();
	}
	
	/**
	 * Método encargado de gestionar la petición de buscar un producto
	 * @param nombre
	 * @throws Exception
	 */
	public void executeBuscarProductoNombre (String nombre) throws Exception
	{
		this.fachadaProducto.buscarProductoNombre(nombre);
	}
}
