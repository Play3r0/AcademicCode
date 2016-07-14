package FurryFiesta.modelo.productos;

import java.util.ArrayList;

import FurryFiesta.Categoria;
import FurryFiesta.modelo.pedido.Pedido;
import FurryFiesta.modelo.venta.Venta;
import FurryFiesta.vista.Observador;

/**
 * @author Omar LLundo & Enrique Laguna.
 *
 */
public class SAProductosImp implements SAProductos{
	
	private DAOProductoImp daop = new DAOProductoImp();
	private ArrayList<Observador> _observador = new ArrayList<Observador>();
	
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.SAProductos#agregarProductoSA(FurryFiesta.modelo.productos.Producto)
	 */
	@Override
	public void agregarProductoSA(Producto p) throws Exception {
		
		Producto producto;
		
		try
		{
			producto = this.daop.buscaProductoNombre(p.getNombre());
			
			if (!producto.equals(null) && producto.getDisponible())
				throw new Exception("Ya existe");
			else if (producto.equals(null))
			{
				this.daop.insertaProducto(p);
				ArrayList<Object> array = this.daop.consultarProductos();
				for (Observador o : this._observador)
					o.actualizarLista(array);
			}
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("Ya existe"))
				throw new Exception("Producto ya existente");
			else
			{
				this.daop.insertaProducto(p);
				ArrayList<Object> array = this.daop.consultarProductos();
				for (Observador o : this._observador)
					o.actualizarLista(array);
			}
		}
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.SAProductos#eliminarProductoSA(java.lang.Integer)
	 */
	@Override
	public void eliminarProductoSA(Integer id) throws Exception{
		
		this.daop.eliminaProducto(id);
		ArrayList<Object> array = this.daop.consultarProductos();
		for (Observador o : this._observador)
			o.actualizarLista(array);
		
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.SAProductos#modificarProductoSA(FurryFiesta.modelo.productos.Producto)
	 */
	@Override
	public void modificarProductoSA(Producto p) throws Exception{
		
		ArrayList<Object> lista;
			
		this.daop.modificaProducto(p);
		lista = this.daop.consultarProductosCategoria(p.getCategoria());
		for (Observador o : this._observador)
				o.actualizarLista(lista);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.SAProductos#consultarProductoSA(java.lang.Integer)
	 */
	@Override
	public void consultarProductoSA(Integer id) throws Exception{
		
		Producto producto = this.daop.buscaProducto(id);
		for (Observador o : this._observador)
			o.actualizarDatos(producto);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.SAProductos#buscarFiltroProductoSA(java.lang.String)
	 */
	@Override
	public void buscarFiltroProductoSA(String f) throws Exception{
		
		ArrayList<Object> array;
		
		if (!f.equals(Categoria.TODOS.getTexto()))
			array = this.daop.consultarProductosCategoria(f);
		else
			array = this.daop.consultarProductos();
		
		for (Observador o : this._observador)
			o.actualizarLista(array);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.SAProductos#addObserver(FurryFiesta.vista.Observador)
	 */
	@Override
	public void addObserver(Observador observador){
		this._observador.add(observador);
	}

	public void inicializa() throws Exception 
	{
		ArrayList<Object> lista = this.daop.consultarProductos();
		for (Observador o : this._observador)
			o.actualizarLista(lista);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.SAProductos#actualizarStockPedido(int)
	 */
	@Override
	public void actualizarStockPedido(int idPedido) throws Exception {
		
		this.daop.actualizarStockPedido(idPedido);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.SAProductos#actualizarStockVenta(int)
	 */
	@Override
	public void actualizarStockVenta(int idVenta) throws Exception {
		this.daop.actualizarStockVenta(idVenta);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.SAProductos#comprobarStockProductos(java.util.ArrayList, java.util.ArrayList)
	 */
	@Override
	public void comprobarStockProductos(ArrayList<Integer> idProductos,
			ArrayList<Integer> numStock) throws Exception {
		
		for(int i = 0; i < idProductos.size(); i++)
			daop.comprobarStockProductos(idProductos.get(i), numStock.get(i));
		}

	public void inicializa2() throws Exception {

		ArrayList<Object> lista = this.daop.consultarProductos();
		for (Observador o : this._observador)
			o.actualizarListaGUI(lista);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.SAProductos#calcularPrecioPedido(FurryFiesta.modelo.pedido.Pedido, java.util.ArrayList, java.util.ArrayList)
	 */
	@Override
	public void calcularPrecioPedido(Pedido pedido, ArrayList<Integer> idProductos, ArrayList<Integer> numProductos) throws Exception {
		
		double totales = 0;
		
		for (int i = 0; i < idProductos.size(); i ++)
		{
			Producto a = this.daop.buscaProducto((Integer) idProductos.get(i));
			totales += a.getPrecio() * numProductos.get(i);
		}
	
		pedido.setPrecioTotal(totales);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.SAProductos#calcularPrecioVenta(FurryFiesta.modelo.venta.Venta, java.util.ArrayList, java.util.ArrayList)
	 */
	@Override
	public void calcularPrecioVenta(Venta venta,
			ArrayList<Integer> idProductos, ArrayList<Integer> numProductos) throws Exception {
		
		double totales = 0;
		
		for (int i = 0; i < idProductos.size(); i ++)
		{
			Producto a = this.daop.buscaProducto((Integer) idProductos.get(i));
			totales += a.getPrecio() * numProductos.get(i);
		}
	
		venta.setTotal(totales);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.SAProductos#buscarProductoNombre(java.lang.String)
	 */
	@Override
	public void buscarProductoNombre(String nombre) throws Exception {
		Producto producto = this.daop.buscaProductoNombre(nombre);
		for (Observador o : this._observador)
			o.actualizarDatos(producto);
	}
}
