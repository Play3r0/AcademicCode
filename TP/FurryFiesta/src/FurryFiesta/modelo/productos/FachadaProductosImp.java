package FurryFiesta.modelo.productos;

import java.util.ArrayList;

import FurryFiesta.modelo.pedido.Pedido;
import FurryFiesta.modelo.venta.Venta;
import FurryFiesta.vista.Observador;

/**
 * @author Omar LLundo & Enrique Laguna.
 *
 */
public class FachadaProductosImp implements FachadaProductos{

	private SAProductosImp sap = new SAProductosImp();
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.FachadaProductos#agregarProducto(FurryFiesta.modelo.productos.Producto)
	 */
	@Override
	public void agregarProducto(Producto p) throws Exception {
		
		this.sap.agregarProductoSA(p);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.FachadaProductos#eliminarProducto(java.lang.Integer)
	 */
	@Override
	public void eliminarProducto(Integer id) throws Exception {
		
		this.sap.eliminarProductoSA(id);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.FachadaProductos#modificarProducto(FurryFiesta.modelo.productos.Producto)
	 */
	@Override
	public void modificarProducto(Producto p) throws Exception {
		
		this.sap.modificarProductoSA(p);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.FachadaProductos#consultarProducto(java.lang.Integer)
	 */
	@Override
	public void consultarProducto(Integer id) throws Exception{
		
		this.sap.consultarProductoSA(id);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.FachadaProductos#buscarFiltroProducto(java.lang.String)
	 */
	@Override
	public void buscarFiltroProducto(String f) throws Exception{
	
		this.sap.buscarFiltroProductoSA(f);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.FachadaProductos#addObserver(FurryFiesta.vista.Observador)
	 */
	@Override
	public void addObserver(Observador observador) 
	{
		this.sap.addObserver(observador);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.FachadaProductos#inicializa()
	 */
	public void inicializa() throws Exception 
	{
		this.sap.inicializa();
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.FachadaProductos#actualizarStockPedido(int)
	 */
	@Override
	public void actualizarStockPedido(int idPedido) throws Exception {
		
		this.sap.actualizarStockPedido(idPedido);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.FachadaProductos#actualizarStockVenta(int)
	 */
	@Override
	public void actualizarStockVenta(int idVenta) throws Exception {
		
		this.sap.actualizarStockVenta(idVenta);
	}
	
	/**
	 * Metodo que comprueba el stock de los productos.
	 * @param listaProductos - listado de productos.
	 * @param listaStock - lista de productos en stock
	 * @throws Exception
	 */
	public void comprobarStockProductos(ArrayList<Integer> listaProductos,
			ArrayList<Integer> listaStock) throws Exception {

		this.sap.comprobarStockProductos(listaProductos, listaStock);
	}

	public void inicializa2() throws Exception 
	{
		this.sap.inicializa2();
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.FachadaProductos#calcularPrecioPedido(FurryFiesta.modelo.pedido.Pedido, java.util.ArrayList, java.util.ArrayList)
	 */
	@Override
	public void calcularPrecioPedido(Pedido p, ArrayList<Integer> idProductos, ArrayList<Integer> numProductos) throws Exception {
		
		sap.calcularPrecioPedido(p, idProductos, numProductos);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.FachadaProductos#calcularPrecioVenta(FurryFiesta.modelo.venta.Venta, java.util.ArrayList, java.util.ArrayList)
	 */
	@Override
	public void calcularPrecioVenta(Venta venta,
			ArrayList<Integer> idProductos, ArrayList<Integer> numProductos) throws Exception {
		
		sap.calcularPrecioVenta(venta, idProductos, numProductos);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.FachadaProductos#buscarProductoNombre(java.lang.String)
	 */
	@Override
	public void buscarProductoNombre(String nombre) throws Exception {
		
		this.sap.buscarProductoNombre(nombre);
	}
}
