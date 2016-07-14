package FurryFiesta.modelo.venta;

import java.util.ArrayList;
import FurryFiesta.vista.Observador;

/**
 * clase intermedia encargada de la comunicacion entre la aplicacion y la base de datos
 * @author Carlos
 *
 */
public class FachadaVentasImp implements FachadaVentas {
	
	private SAVentasImp sai = new SAVentasImp();
	
	@Override
	public void agregarVenta(Venta venta, ArrayList<Integer> idProductos,
			ArrayList<Integer> listaStock) throws Exception {
		
		sai.agregarVentaSA(venta, idProductos, listaStock);
	}

	@Override
	public void consultarVenta(Integer id) throws Exception{
		
		sai.consultarVentaSA(id);
	}	

	@Override
	public void addObserver(Observador observador) 
	{
		sai.addObserver(observador);
	}

	@Override
	public void historicoVenta() throws Exception {
		
		sai.historicoVentasSA();
	}

	@Override
	public void exportarVenta(String direccion, String nombreArchivo) throws Exception {
		
		sai.exportarVentaSA(direccion, nombreArchivo);
	}

	@Override
	public void calcularTotalVentas() throws Exception {
		sai.calcularTotalVentas();		
	}
}
