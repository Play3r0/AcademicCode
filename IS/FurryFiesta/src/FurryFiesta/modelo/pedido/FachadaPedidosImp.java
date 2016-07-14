package FurryFiesta.modelo.pedido;

import java.util.ArrayList;

import FurryFiesta.vista.Observador;

/**
 * @author Omar LLundo & Enrique Laguna.
 *
 */
public class FachadaPedidosImp implements FachadaPedidos{

	private SAPedidoImp sap = new SAPedidoImp();
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.FachadaPedidos#agregarPedido(FurryFiesta.modelo.pedido.Pedido, java.util.ArrayList, java.util.ArrayList)
	 */
	@Override
	public void agregarPedido(Pedido p, ArrayList<Integer> idProductos, ArrayList<Integer> numProductos) throws Exception {
		
		this.sap.agregarPedidoSA(p, idProductos, numProductos);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.FachadaPedidos#confirmarPedido(java.lang.Integer)
	 */
	@Override
	public void confirmarPedido(Integer id) throws Exception {
		
		this.sap.confirmarPedidoSA(id);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.FachadaPedidos#cancelarPedido(java.lang.Integer)
	 */
	@Override
	public void cancelarPedido(Integer id) throws Exception {
		
		this.sap.cancelarPedidoSA(id);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.FachadaPedidos#consultarPedido(java.lang.Integer)
	 */
	@Override
	public void consultarPedido(Integer id) throws Exception {
		
		this.sap.consultarPedidoSA(id);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.FachadaPedidos#buscarFiltroPedido(java.lang.String)
	 */
	@Override
	public void buscarFiltroPedido(String f) throws Exception {
		
		this.sap.buscarFiltroPedidoSA(f);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.FachadaPedidos#addObserver(FurryFiesta.vista.Observador)
	 */
	@Override
	public void addObserver(Observador observador) 
	{
		this.sap.addObserver(observador);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.FachadaPedidos#inicializa()
	 */
	@Override
	public void inicializa() throws Exception 
	{
		this.sap.inicializa();
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.FachadaPedidos#calcularTotalPedidos()
	 */
	@Override
	public void calcularTotalPedidos() throws Exception {
		this.sap.calcularTotalPedidos();
	}
}
