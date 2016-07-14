package FurryFiesta.vista;

import java.util.ArrayList;

import FurryFiesta.modelo.personal.Rango;

/**
 * @author Alejandro Huertas Herrero
 *
 */
public interface Observador {

	/**
	 * Función que prepara el panel de ventas con los botones y campos de texto
	 * necesarios para poder ver todas las ventas realizadas en la tienda
	 */
	void historicoVentas();
	
	/**
	 * Función que prepara el panel de ventas con los botones y campos de texto
	 * necesarios para poder realizar una nueva venta
	 */
	void nuevaVenta();
	
	/**
	 * Función que prepara el panel de productos con los botones y campos de texto
	 * necesarios para poder buscar un producto
	 */
	void buscarProducto();
	
	/**
	 * Función que prepara el panel de productos con los botones y campos de texto
	 * necesarios para poder agregar un nuevo producto a la bbdd
	 */
	void nuevoProducto();
	
	/**
	 * Función que prepara el panel de productos con los botones y campos de texto
	 * necesarios para poder modificar un producto existente en la bbdd
	 */
	void modificarProducto();
	
	/**
	 * Función que prepara el panel de productos con los botones y campos de texto
	 * necesarios para poder eliminar un producto de la bbdd
	 */
	void eliminarProducto();
	
	/**
	 * Función que prepara el panel de pedidos con los botones y campos de texto
	 * necesarios para poder buscar un pedido
	 */
	void buscarPedido();
	
	/**
	 * Función que prepara el panel de pedidos con los botones y campos de texto
	 * necesarios para poder realizar un nuevo pedido
	 */
	void nuevoPedido();
	
	/**
	 * Función que prepara el panel de pedidos con los botones y campos de texto
	 * necesarios para poder eliminar un pedido de la bbdd
	 */
	void eliminarPedido();
	
	/**
	 * Función que prepara el panel de personal con los botones y campos de texto
	 * necesarios para poder consultar todo el personal
	 */
	void consultarPersonal();
	
	/**
	 * Función que prepara el panel de personal con los botones y campos de texto
	 * necesarios para poder dar de alta a un nuevo empleado
	 */
	void altaPersonal();
	
	/**
	 * Función que prepara el panel de personal con los botones y campos de texto
	 * necesarios para poder dar de baja a un empleado
	 */
	void bajaPersonal();
	
	/**
	 * Función que prepara el panel de personal con los botones y campos de texto
	 * necesarios para poder modificar los datos de un empleado
	 */
	void modificarPersonal();
	
	/**
	 * Función que se encarga de actualizar las diferentes listas de cada panel
	 * @param lista - lista con los datos a mostrar
	 */
	void actualizarLista(ArrayList<Object> lista);
	
	/**
	 * Función que se encarga de actualizar las diferentes listas de cada panel
	 * @param lista - lista con los datos a mostrar
	 */
	void actualizarListaSecundaria(ArrayList<Object> lista);
	
	/**
	 * Muestra los datos conretos de un producto, empleado....
	 * @param objeto - Objeto con los datos a mostrar
	 */
	void actualizarDatos (Object objeto);
	
	/**
	 * Prepara los menus de la vista según el que ha iniciado sesión.
	 * @param rango - rango del empleado conectado.
	 */
	void actualizarMenu (Rango rango);
	
	/**
	 * Función que prepara el panel de SESION con los botones y campos de texto
	 * necesarios para poder cambiar de usuario.
	 */
	void cambioSesion();

	/**
	 * Función que se encarga de actualizar las diferentes listas de cada panel
	 * @param lista - lista con los datos a mostrar
	 */
	void actualizarListaGUI(ArrayList<Object> lista);
	
	/**
	 * @param cantidad - cantidad total de los costes de los pedidos
	 */
	void pedidosTotal (double cantidad);
	
	/**
	 * @param cantidad - cantidad total de los ingresos de las ventas
	 */
	void ventasTotal (double cantidad);
}