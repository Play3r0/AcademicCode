package FurryFiesta.modelo.pedido;

import java.net.InetAddress;
import java.util.ArrayList;

import FurryFiesta.vista.Observador;

/**
 * @author Omar LLundo & Enrique Laguna.
 *
 */
public class SAPedidoImp implements SAPedidos{

	private DAOPedidoImp daop = new DAOPedidoImp();
	private ArrayList<Observador> _observador = new ArrayList<Observador>();
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.SAPedidos#agregarPedidoSA(FurryFiesta.modelo.pedido.Pedido, java.util.ArrayList, java.util.ArrayList)
	 */
	@Override
	public void agregarPedidoSA(Pedido pedido, ArrayList<Integer> idProductos, ArrayList<Integer> numProductos) throws Exception {
		
		// Obtener identificador único para el terminal
        InetAddress addr;
        addr = InetAddress.getLocalHost();
        String idTerminal = addr.getHostName();
        
		int idPersonal = daop.consultarPersonal(idTerminal);
		pedido.setIdPersonal(idPersonal);
		
		this.daop.agregarPedido(pedido, idProductos, numProductos);
		ArrayList<Object> array = this.daop.consultarPedidos();
		for (Observador o : this._observador)
			o.actualizarLista(array);
		
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.SAPedidos#confirmarPedidoSA(java.lang.Integer)
	 */
	@Override
	public void confirmarPedidoSA(Integer id) throws Exception 
	{
		this.daop.confirmarPerdido(id, true);
		buscarFiltroPedidoSA("NO_CONFIRMADO");
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.SAPedidos#cancelarPedidoSA(java.lang.Integer)
	 */
	@Override
	public void cancelarPedidoSA(Integer id) throws Exception {
		
		this.daop.eliminarPedido(id);
		ArrayList<Object> array = this.daop.consultarPedidos();
		for (Observador o : this._observador)
			o.actualizarLista(array);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.SAPedidos#consultarPedidoSA(java.lang.Integer)
	 */
	@Override
	public void consultarPedidoSA(Integer id) throws Exception {

		Pedido pedido = this.daop.consultarPedido(id);
		ArrayList<Object> array = this.daop.consultarProductosPedido(pedido.getId());
		for (Observador o : this._observador)
			o.actualizarListaSecundaria(array);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.SAPedidos#buscarFiltroPedidoSA(java.lang.String)
	 */
	@Override
	public void buscarFiltroPedidoSA(String f) throws Exception {
		
		ArrayList<Object> array = this.daop.consultarPedidos();
		ArrayList<Object> lista = new ArrayList<Object>();
		
		for (int i = 0; i < array.size(); i ++)
		{
			Pedido pedido = (Pedido) array.get(i);
			if (this.daop.comprobarPedido(pedido.getId()) && f.equals("CONFIRMADO"))
				lista.add(pedido);
			else if (!this.daop.comprobarPedido(pedido.getId()) && f.equals("NO_CONFIRMADO"))
				lista.add(pedido);
			else if (f.equals("TODOS"))
				lista.add(pedido);
		}
		for (Observador o : this._observador)
			o.actualizarLista(lista);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.SAPedidos#addObserver(FurryFiesta.vista.Observador)
	 */
	@Override
	public void addObserver(Observador observador) 
	{
		this._observador.add(observador);
	}
	
	/**Inicializa los la lista de pedidos.
	 * @throws Exception
	 */
	public void inicializa() throws Exception 
	{
		ArrayList<Object> lista = this.daop.consultarPedidos();
		for (Observador o : this._observador)
			o.actualizarLista(lista);
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.SAPedidos#calcularTotalPedidos()
	 */
	@Override
	public void calcularTotalPedidos() throws Exception {
		double cantidad = this.daop.calcularTotalPedidos();
		for (Observador o : this._observador)
			o.pedidosTotal(cantidad);;
	}
}
