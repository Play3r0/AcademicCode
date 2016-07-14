package FurryFiesta;

import javax.swing.JOptionPane;

import FurryFiesta.controlador.ControladorGUI;
import FurryFiesta.controlador.ControladorPedidos;
import FurryFiesta.controlador.ControladorPersonal;
import FurryFiesta.controlador.ControladorProductos;
import FurryFiesta.controlador.ControladorSesion;
import FurryFiesta.controlador.ControladorVentas;
import FurryFiesta.modelo.bbdd.Conexion;
import FurryFiesta.modelo.pedido.FachadaPedidosImp;
import FurryFiesta.modelo.personal.FachadaPersonalImp;
import FurryFiesta.modelo.productos.FachadaProductosImp;
import FurryFiesta.modelo.sesion.FachadaSesionImp;
import FurryFiesta.modelo.venta.FachadaVentasImp;
import FurryFiesta.vista.VistaGUI;

public class Main {

	public static void main(String[] args) 
	{	
		Conexion conexion = new Conexion();
		
		
		try {
			
			conexion.Conectar("", "");
			
			ControladorGUI controlador = new ControladorGUI(new ControladorPedidos(
					new FachadaPedidosImp(), new FachadaProductosImp()), 
					new ControladorPersonal(new FachadaPersonalImp()), 
					new ControladorProductos(new FachadaProductosImp()), 
					new ControladorVentas(new FachadaVentasImp(), new FachadaProductosImp()),
					new ControladorSesion(new FachadaSesionImp()));
			new VistaGUI(controlador);
			
		} catch (Exception e) {

			JOptionPane.showMessageDialog(null, "Error al conectar con la base de datos: " + e.getMessage().toString());
		}
		
			
	}

}
