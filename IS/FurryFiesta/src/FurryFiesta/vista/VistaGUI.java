 package FurryFiesta.vista;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.event.MenuEvent;
import javax.swing.event.MenuListener;

import FurryFiesta.controlador.ControladorGUI;
import FurryFiesta.modelo.personal.Rango;

/**
 * @author Alejandro Huertas Herrero
 *
 */
public class VistaGUI extends JFrame implements Observador
{
	/**
	 * Constante para quitar el warning de la clase
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Controlador del frame principal que permite pasar los demas
	 * controladores a los demás paneles
	 */
	private ControladorGUI _controladorGUI;
	
	/**
	 * Container de la ventana principal utilizado para ir cargando
	 * los diferentes paneles en él
	 */
	@SuppressWarnings("unused")
	private Container _containerPrincipal;
	
	/**
	 * Distintos paneles que se corresponden con todas las funciones
	 * que se pueden realizar en la aplciación
	 */
	private JPanel _panelSesion, _panelVentas, _panelProductos, _panelPedidos, 
						_panelPersonal, _panelInformacion;
	
	/**
	 * Barra de menú que permite ir navegando por las diferentes
	 * ventanas de la aplciación
	 */
	private JMenuBar _barraMenu;
	
	/**
	 * Diferentes menús que componen la barra de menú y que tienen los botones
	 * para ir cambiando de pestaña
	 */
	private JMenu _menuSistema, _menuProductos, _menuUsuarios, _menuVentas, 
						_menuPedidos, _menuPersonal, _menuResultados;
	
	/**
	 * Variables utilizadas para mostrar los resultados
	 */
	private double _totalVentas, _totalPedidos;

	
	/**
	 * @param controlador utilizado para poder pasar los demás controladores a las vistas
	 */
	public VistaGUI(ControladorGUI controlador) 
	{
		super ("Furry Fiesta");
		_controladorGUI = controlador;
		_controladorGUI.getControladorSesion().registerObserver(this);
		_controladorGUI.getControladorPed().registerObserver(this);
		_controladorGUI.getControladorVentas().registerObserver(this);
		
		initGUI();
	}

	/**
	 * Función que se encarga de iniciar la ventana principal de la aplicación
	 */
	private void initGUI()
	{
		//Container Principal Aplicación
		_containerPrincipal = getContentPane();

		//Barra de menú
		_barraMenu = new JMenuBar();
		
		//Funciones que inician los diferentes menús con sus listeners
		initMenuSistema ();
		
		initMenuProductos();
		
		initMenuUsuarios();
		
		initMenuVentas();
		
		initMenuPedidos();
		
		initMenuResultados();
		
		initMenuPersonal();
				
		//Añadir a la barra de menú todos los submenús
		_barraMenu.add(_menuSistema);
		_barraMenu.add(_menuProductos);
		_barraMenu.add(_menuUsuarios);
		_barraMenu.add(_menuVentas);
		_barraMenu.add(_menuPedidos);
		_barraMenu.add(_menuResultados);
		_barraMenu.add(_menuPersonal);
		
		//Se crean todos los paneles de la aplicación
		_panelSesion = new GUISesion(_controladorGUI.getControladorSesion());
		_panelVentas = new GUIVentas(_controladorGUI.getControladorVentas());
		_panelProductos = new GUIProductos(_controladorGUI.getControladorProd());
		_panelPedidos = new GUIPedidos(_controladorGUI.getControladorPed());
		_panelPersonal = new GUIPersonal(_controladorGUI.getControladorEmp());
		_panelInformacion = new PanelGeneral();

		setMinimumSize(new Dimension(450, 600));
		add(_panelSesion);
		setLocationRelativeTo(null);
		setVisible(true);
		pack();
		addWindowListener(new WindowAdapter() 
		{
			public void windowClosing(WindowEvent e)
			{
				try 
				{
					_controladorGUI.getControladorSesion().executeDesconectar();
					System.exit(0);
				}
				catch (Exception e1){}
				
			}
		});
	}

	/**
	 * Inicializa la pestaña del menú, sistema
	 */
	private void initMenuSistema ()
	{
		_menuSistema = new JMenu("Sistema");
		_menuSistema.addMenuListener(new MenuListener() 
		{
			public void menuSelected(MenuEvent e) 
			{
				getContentPane().removeAll();
				repaint();
				add(_panelInformacion);
				pack();				
			}
			
			public void menuDeselected(MenuEvent e) {}
			
			public void menuCanceled(MenuEvent e) {}
		});
	}	
		
	/**
	 * Inicializa la pestaña del menú, productos
	 */
	private void initMenuProductos()
	{
		_menuProductos = new JMenu("Productos");
	
		_menuProductos.add("Buscar producto").addActionListener(new ActionListener() {
			
			
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				repaint();
				((Observador) _panelProductos).buscarProducto();
				add(_panelProductos);
				pack();
			}
		});
		
		_menuProductos.add("Agregar producto").addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				repaint();
				((Observador) _panelProductos).nuevoProducto();
				add(_panelProductos);
				pack();				
			}
		});
		
		_menuProductos.add("Modificar producto").addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				repaint();
				((Observador) _panelProductos).modificarProducto();
				add(_panelProductos);
				pack();
			}
		});
		
		_menuProductos.add("Eliminar producto").addActionListener(new ActionListener() 
		{
		
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				repaint();
				((Observador) _panelProductos).eliminarProducto();
				add(_panelProductos);
				pack();				
			}
		});
	}

	/**
	 * Inicializa la pestaña del menú, ventas
	 */
	private void initMenuVentas()
	{
		_menuVentas = new JMenu("Ventas");
		
		_menuVentas.add("Nueva venta").addActionListener(new ActionListener() 
		{
	
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				repaint();
				((Observador) _panelVentas).nuevaVenta();
				add(_panelVentas);
				pack();
			}
		});
		
		_menuVentas.add("Histórico de ventas").addActionListener(new ActionListener() 
		{
		
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				repaint();
				((Observador) _panelVentas).historicoVentas();
				add(_panelVentas);
				pack();
			}
		});
		
		_menuVentas.add("Exportar ventas").addActionListener(new ActionListener() 
		{
		
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{	
					String ruta = JOptionPane.showInputDialog(null, "Introduce la ruta y el archivo (ruta&archivo)", "Exportar ventas", JOptionPane.DEFAULT_OPTION);
					
					if (!ruta.contains("&"))
						JOptionPane.showConfirmDialog(null, "Error tu mensaje debe llevar &", "Error",
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					else
					{
					
						String [] partes = ruta.split("&");
						String direccion = partes[0], archivo = partes[1];
						
						_controladorGUI.getControladorVentas().executeExportarVentas(direccion, archivo);
						
						JOptionPane.showConfirmDialog(null, "Ventas exportadas correctamente", "Éxito",
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					} 
				}
				catch (Exception e1) 
				{
					JOptionPane.showConfirmDialog(null, "Error al exportar las ventas: " + e1.getMessage(), "Error",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				}
			}
		});
	}
	
	/**
	 * Inicializa la pestaña del menú, usuarios
	 */
	private void initMenuUsuarios()
	{
		_menuUsuarios = new JMenu("Usuarios");
		
		_menuUsuarios.add("Desconectar").addActionListener(new ActionListener() 
		{
	
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					_controladorGUI.getControladorSesion().executeDesconectar();
				} 
				catch (Exception e1) 
				{

					e1.printStackTrace();
				}
				getContentPane().removeAll();
				repaint();
				setMinimumSize(new Dimension(450, 600));
				add(_panelSesion);
				_barraMenu.setVisible(false);
				pack();
			}
		});
		
		_menuUsuarios.add("Cambiar").addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				((Observador) _panelSesion).cambioSesion();
			}
		});
	}

	/**
	 * Inicializa la pestaña del menú, pedidos
	 */
	private void initMenuPedidos()
	{
		_menuPedidos = new JMenu("Pedidos");
		
		_menuPedidos.add("Buscar pedido").addActionListener(new ActionListener() 
		{
	
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				repaint();
				((Observador) _panelPedidos).buscarPedido();
				add(_panelPedidos);
				pack();
			}
		});
		
		_menuPedidos.add("Realizar pedido").addActionListener(new ActionListener() 
		{
	
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				repaint();
				((Observador) _panelPedidos).nuevoPedido();
				add(_panelPedidos);
				pack();
			}
		});
		
		_menuPedidos.add("Cancelar pedido").addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				repaint();
				((Observador) _panelPedidos).eliminarPedido();
				add(_panelPedidos);
				pack();
			}
		});
	}

	/**
	 * Inicializa la pestaña del menú, resultados
	 */
	private void initMenuResultados()
	{
		_menuResultados = new JMenu("Resultados");
		
		_menuResultados.add("Resultados ventas").addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					_controladorGUI.getControladorVentas().calcularTotalVentas();
					JOptionPane.showConfirmDialog(null, "El total de las ventas hechas es: " + _totalVentas + " €", "Infomacion",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					
				} 
				catch (Exception e1){}
			}
		});
		
		_menuResultados.add("Resultados pedidos").addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				try 
				{
					_controladorGUI.getControladorPed().calcularTotalPedidos();
					JOptionPane.showConfirmDialog(null, "El total de los pedidos hechos es: " + _totalPedidos + " €", "Infomacion",
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				} 
				catch (Exception e1){}
			}
		});
	}

	/**
	 * Inicializa la pestaña del menú, personal
	 */
	private void initMenuPersonal()
	{
		_menuPersonal = new JMenu("Personal");
		
		_menuPersonal.add("Consultar Personal").addActionListener(new ActionListener() 
		{
			
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				repaint();
				((Observador) _panelPersonal).consultarPersonal();
				add(_panelPersonal);
				pack();
			}
		});
		
		_menuPersonal.add("Alta personal").addActionListener(new ActionListener() 
		{
	
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				repaint();
				((Observador) _panelPersonal).altaPersonal();
				add(_panelPersonal);
				pack();
			}
		});
		
		_menuPersonal.add("Baja personal").addActionListener(new ActionListener() 
		{
	
			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				repaint();
				((Observador) _panelPersonal).bajaPersonal();
				add(_panelPersonal);
				pack();				
			}
		});
		
		_menuPersonal.add("Modificar datos personal").addActionListener(new ActionListener() 
		{

			public void actionPerformed(ActionEvent e) 
			{
				getContentPane().removeAll();
				repaint();
				((Observador) _panelPersonal).modificarPersonal();
				add(_panelPersonal);
				pack();
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#actualizarMenu(FurryFiesta.modelo.personal.Rango)
	 */
	@Override
	public void actualizarMenu(Rango rango) 
	{
		if (rango.equals(Rango.EMPLEADO))
		{
			getContentPane().removeAll();
			repaint();
			_menuSistema.setEnabled(true);
			_menuProductos.setEnabled(true);
			_menuProductos.getItem(1).setVisible(true);
			_menuProductos.getItem(2).setVisible(true);
			_menuProductos.getItem(3).setVisible(true);
			_menuUsuarios.setEnabled(true);
			_menuUsuarios.getItem(1).setVisible(true);
			_menuVentas.setEnabled(true);
			_menuUsuarios.getItem(1).setVisible(true);
			_menuPedidos.setEnabled(true);
			_menuPedidos.getItem(2).setVisible(false);
			_menuResultados.setEnabled(true);
			_menuPersonal.setEnabled(true);
			_menuPersonal.getItem(0).setVisible(true);
			_menuPersonal.getItem(1).setVisible(false);
			_menuPersonal.getItem(2).setVisible(false);
			_menuPersonal.getItem(3).setVisible(false);
			_barraMenu.setVisible(true);
			_barraMenu.setVisible(true);
			setJMenuBar(_barraMenu);
			add(_panelInformacion);
			revalidate();
			pack();	
		}
		else if (rango.equals(Rango.ADMINISTRADOR))
		{
			getContentPane().removeAll();
			repaint();
			_menuSistema.setEnabled(true);
			_menuProductos.setEnabled(true);
			_menuProductos.getItem(1).setVisible(true);
			_menuProductos.getItem(2).setVisible(true);
			_menuProductos.getItem(3).setVisible(true);
			_menuUsuarios.setEnabled(true);
			_menuUsuarios.getItem(1).setVisible(true);
			_menuVentas.setEnabled(true);
			_menuUsuarios.getItem(1).setVisible(true);
			_menuPedidos.setEnabled(true);
			_menuPedidos.getItem(2).setVisible(true);
			_menuResultados.setEnabled(true);
			_menuPersonal.setEnabled(true);
			_menuPersonal.getItem(0).setVisible(true);
			_menuPersonal.getItem(1).setVisible(true);
			_menuPersonal.getItem(2).setVisible(true);
			_menuPersonal.getItem(3).setVisible(true);
			_barraMenu.setVisible(true);
			_barraMenu.setVisible(true);
			setJMenuBar(_barraMenu);
			add(_panelInformacion);
			revalidate();
			pack();	
		}
		else
		{
			getContentPane().removeAll();
			repaint();
			_menuSistema.setEnabled(true);
			_menuProductos.setEnabled(true);
			_menuProductos.getItem(1).setVisible(false);
			_menuProductos.getItem(2).setVisible(false);
			_menuProductos.getItem(3).setVisible(false);
			_menuUsuarios.setEnabled(true);
			_menuVentas.setEnabled(false);
			_menuPedidos.setEnabled(false);
			_menuResultados.setEnabled(false);
			_menuPersonal.setEnabled(false);
			_barraMenu.setVisible(true);
			setJMenuBar(_barraMenu);
			add(_panelInformacion);
			revalidate();
			pack();	
		}
		
		setMinimumSize(new Dimension(800, 600));
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#pedidosTotal(double)
	 */
	@Override
	public void pedidosTotal(double cantidad) 
	{
		_totalPedidos = cantidad;
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#ventasTotal(double)
	 */
	@Override
	public void ventasTotal(double cantidad) 
	{
		_totalVentas = cantidad;
	}

	public void historicoVentas() {}

	public void nuevaVenta() {}

	public void buscarProducto() {}
	
	public void nuevoProducto() {}

	public void modificarProducto() {}

	public void eliminarProducto() {}

	public void buscarPedido() {}

	public void nuevoPedido() {}

	public void eliminarPedido() {}

	public void consultarPersonal() {}

	public void altaPersonal() {}

	public void bajaPersonal() {}

	public void modificarPersonal() {}

	public void actualizarLista(ArrayList<Object> lista) {}

	public void actualizarListaSecundaria(ArrayList<Object> lista) {}
	
	public void actualizarDatos(Object objeto) {}

	public void devolverVenta() {}

	public void cambioSesion() {}

	public void actualizarListaGUI(ArrayList<Object> lista) {}
}