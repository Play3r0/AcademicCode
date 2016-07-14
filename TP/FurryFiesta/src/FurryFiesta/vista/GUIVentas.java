package FurryFiesta.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultListCellRenderer.UIResource;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import FurryFiesta.controlador.ControladorVentas;
import FurryFiesta.modelo.personal.Rango;
import FurryFiesta.modelo.productos.Producto;
import FurryFiesta.modelo.venta.Venta;

/**
 * @author Alejandro Huertas Herrero
 *
 */
public class GUIVentas extends JPanel implements Observador
{
	/**
	 * Constante para quitar el warning
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Etiquetas utilizadas en el panel para mostrar toda la información
	 * relacionada con las ventas
	 */
	private JLabel _labelVentas, _labelIdentificador, _labelNombre, _labelTipo, 
						_labelPrecio, _labelListaVentas, _labelProductosVentas, _labelUnidades, _labelListaProductos, _labelProductos, _labelStock;
	
	/**
	 * Campos de texto utilizados para obtener los datos
	 * necesarios para crear el objeto transfer de cada venta
	 */
	private JTextField _textoIdentificador, _textoNombre, _textoTipo, _textoPrecio, _textoUnidades, _textoStock;
	
	/**
	 * Botones que permiten realizar las diferentes
	 * acciones relaciondas con las ventas
	 */
	private JButton _botonAgregar, _botonCancelar, _botonConfirmar;
	
	/**
	 * Controlador que servirá para comunicarse con el modelo de ventas
	 */
	private ControladorVentas _controlador;
	
	/**
	 * Diferentes paneles a los que se añaden los componentes
	 * de la ventana para luego formar un único panel de la ventana
	 * de las ventanas que será cargado en el frame principal
	 */
	private JPanel _panelListaVentas, _panelOperaciones, _panelVenta, _panelIdentificador, _panelNombre, _panelTipo,
					_panelPrecio, _panelBotones, _panelUnidades, _panelProductos, _panelListaProductos, _panelStock;
	
	/**
	 * Paneles de texto que se uitilizan para mostrar la informacion de cada venta
	 */
	private JTextPane _textoDescripcionVentas, _textoInfo, _textoDescripcion;

	/**
	 * Modelos de las diferentes listas de la vista
	 */
	private DefaultListModel<Object> _modeloVentas,  _modeloProductosVentas, _modeloProductosGUI, _modeloProductos;

	/**
	 * Lista utilizada para mostrar todos las ventas y para mostrar todos
	 * los productos de una venta
	 */
	private JList<Object> _listaVentas, _listaProductosVenta, _listaProductosGUI, _listaProductosVentaHechas;

	/**
	 * Scroll usado para cuando las ventas sean muchas poder desplazarse
	 * entre todas ellas y para desplazarse en la lista de productos de una venta
	 */
	private JScrollPane _scrollVentas, _scrollProductosVentas, _scrollProductosGUI, _scrollProductos;
	
	/**
	 * Lista que contiene las unidades cada producto de la venta
	 */
	private ArrayList<Integer> _listaId, _listaNumProductos, _listaIdProductos, _listaProductos, _listaIdProductosGUI;
	
	/**
	 * @param control controlador utilizado por la vista para comunicarse con el modelo
	 */
	public GUIVentas (ControladorVentas control)
	{
		initPanelListaVentas();
		initPanelListaProductos();
		initPanelOperaciones();
		initEventos();
		
		setLayout(new GridLayout(1, 2));
		
		_listaId = new ArrayList<Integer>();
		_listaIdProductos = new ArrayList<Integer>();
		_listaNumProductos = new ArrayList<Integer>();
		_listaIdProductosGUI = new ArrayList<Integer>();
		_listaProductos = new ArrayList<Integer>();
		
		_controlador = control;
		_controlador.registerObserver(this);
		_controlador.registerObserverProductos(this);
	}
	
	/**
	 * Función que inicializa el panel de la lista de ventas junto
	 * con la descrpción de cada venta
	 */
	private void initPanelListaVentas()
	{
		//Posición utilizada para centrar los elementos dentro de las listas
		UIResource posicion = new UIResource();
		posicion.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Crea los dos paneles que forman el panel general de las listas
		_panelListaVentas  = new JPanel(new BorderLayout());
		_panelProductos = new JPanel(new BorderLayout());
		
		//Etiqueta de la lista de pedidos
		_labelListaVentas = new JLabel("Lista de ventas");
		_labelListaVentas.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Modelo y lista de los pedidos
		_modeloVentas = new DefaultListModel<Object>();
		_listaVentas = new JList<Object>(_modeloVentas);
		_listaVentas.setCellRenderer(posicion);
		_listaVentas.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				try 
				{
					ponerEnBlanco();
					int posicion =	_listaVentas.getSelectedIndex();;
					Integer i = new Integer(posicion);
					
					if (!i.equals(-1))
					{
						Integer id = _listaId.get(posicion);
						_controlador.executeConsultarVenta(id);
						_textoIdentificador.setText(id.toString());
					}
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		_scrollVentas = new JScrollPane();
		_scrollVentas.setViewportView(_listaVentas);
		
		//Etiqueta de los productos que hay en un pedido
		_labelProductosVentas = new JLabel("Productos de la venta");
		_labelProductosVentas.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Modelo y lista de los productos de un pedido
		_modeloProductosVentas = new DefaultListModel<Object>();
		_listaProductosVentaHechas = new JList<Object>(_modeloProductosVentas);
 		_listaProductosVentaHechas.setCellRenderer(posicion);
 		_listaProductosVentaHechas.addListSelectionListener(new ListSelectionListener()
 		{
			public void valueChanged(ListSelectionEvent e) 
			{
				try 
				{
					int posicion =	_listaProductosVentaHechas.getSelectedIndex(), id;
					Integer i = new Integer(posicion);
					
					if (!i.equals(-1))
					{
						id = _listaIdProductos.get(posicion);
						_controlador.consultarProducto(id);
					}
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		_scrollProductosVentas = new JScrollPane();
		_scrollProductosVentas.setViewportView(_listaProductosVentaHechas);
		
		//Panel de la descripocion de cada pedido
		_textoDescripcionVentas = new JTextPane();
		_textoDescripcionVentas.setBackground(Color.DARK_GRAY);
		_textoDescripcionVentas.setEditable(false);
		_textoDescripcionVentas.setPreferredSize(new Dimension (200, 50));
		_textoDescripcionVentas.setForeground(Color.WHITE);
		TitledBorder titulo = BorderFactory.createTitledBorder("Descripción");
		titulo.setTitleColor(Color.BLUE);
		_textoDescripcionVentas.setBorder(titulo);
		
		//Añadir al panel de la lista de productos del pedido sus componentes
		_panelProductos.add(_labelProductosVentas, BorderLayout.NORTH);
		_panelProductos.add(_scrollProductosVentas, BorderLayout.CENTER);
		_panelProductos.add(_textoDescripcionVentas, BorderLayout.SOUTH);
		
		//Añadir al panel de la lista todos sus componentes
		_panelListaVentas.add(_labelListaVentas, BorderLayout.NORTH);
		_panelListaVentas.add(_scrollVentas, BorderLayout.CENTER);
		_panelListaVentas.add(_panelProductos, BorderLayout.SOUTH);
		_panelListaVentas.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
	}
	
	/**
	 * Función que inicializa el panel de la lista de productos junto
	 * con la descrpción de cada producto.
	 */
	private void initPanelListaProductos() 
	{
		//Posición utilizada para centrar los elementos dentro de las listas
		UIResource posicion = new UIResource();
		posicion.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Crea el panel que contiene la lista de productos
		_panelListaProductos = new JPanel(new BorderLayout());
		_panelProductos = new JPanel(new BorderLayout());
		
		//Etiqueta de la lista de productos
		_labelListaProductos = new JLabel("Lista de productos");
		_labelListaProductos.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Modelo y lista de los productos
		_modeloProductosGUI = new DefaultListModel<Object>();
		_listaProductosGUI = new JList<Object>(_modeloProductosGUI);
 		_listaProductosGUI.setCellRenderer(posicion);
 		_listaProductosGUI.addListSelectionListener(new ListSelectionListener()
 		{
			public void valueChanged(ListSelectionEvent e) 
			{
				try 
				{
					int posicion =	_listaProductosGUI.getSelectedIndex(), id;
					Integer i = new Integer(posicion);
					
					if (!i.equals(-1))
					{
						id = _listaIdProductosGUI.get(posicion);
						_controlador.consultarProducto(id);
						_textoUnidades.setText("");
					}
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
 	
		_scrollProductosGUI = new JScrollPane();
		_scrollProductosGUI.setViewportView(_listaProductosGUI);
		
		//Etiqueta de los productos que hay en un pedido
		_labelProductos = new JLabel("Productos de la venta");
		_labelProductos.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Modelo y lista de los productos de un pedido
		_modeloProductos = new DefaultListModel<Object>();
		_listaProductosVenta = new JList<Object>(_modeloProductos);
 		_listaProductosVenta.setCellRenderer(posicion);
 		_listaProductosVenta.setEnabled(false);
		_scrollProductos = new JScrollPane();
		_scrollProductos.setViewportView(_listaProductosVenta);
		
		//Panel de la descripocion de cada pedido
		_textoDescripcion = new JTextPane();
		_textoDescripcion.setBackground(Color.DARK_GRAY);
		_textoDescripcion.setEditable(false);
		_textoDescripcion.setPreferredSize(new Dimension (200, 50));
		_textoDescripcion.setForeground(Color.WHITE);
		TitledBorder titulo = BorderFactory.createTitledBorder("Descripción");
		titulo.setTitleColor(Color.BLUE);
		_textoDescripcion.setBorder(titulo);
		
		//Añadir al panel de la lista de productos del pedido sus componentes
		_panelProductos.add(_labelProductos, BorderLayout.NORTH);
		_panelProductos.add(_scrollProductos, BorderLayout.CENTER);
		_panelProductos.add(_textoDescripcion, BorderLayout.SOUTH);
		
		//Añadir al panel de la lista todos sus componentes
		_panelListaProductos.add(_labelListaProductos, BorderLayout.NORTH);
		_panelListaProductos.add(_scrollProductosGUI, BorderLayout.CENTER);
		_panelListaProductos.add(_panelProductos, BorderLayout.SOUTH);
		_panelListaProductos.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
	}
	
	/**
	 * Función que inicializa cada uno de los paneles que permiten
	 * realizar operaciones con las ventas
	 */
	private void initPanelOperaciones()
	{
		//Crea todos los paneles y el panel de las operaciones
		_panelOperaciones = new JPanel(new GridLayout (9, 1));
		_panelVenta = new JPanel(new FlowLayout());
		_panelIdentificador = new JPanel(new FlowLayout());
		_panelNombre = new JPanel(new FlowLayout());
		_panelTipo = new JPanel(new FlowLayout());
		_panelPrecio = new JPanel(new FlowLayout());
		_panelStock = new JPanel(new FlowLayout());
		_panelUnidades = new JPanel(new FlowLayout());
		_panelBotones = new JPanel(new FlowLayout());
			
		//Etiqueta con el título
		_labelVentas = new JLabel("VENTAS");
		_labelVentas.setHorizontalAlignment(SwingConstants.CENTER);
		_labelVentas.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED));
		_panelVenta.add(_labelVentas);
		
		//Etiqueta del identificador y campo para rellenar 
		_labelIdentificador = new JLabel("Identificador venta:");
		_textoIdentificador = new JTextField();
		_textoIdentificador.setPreferredSize(new Dimension(108,25));
		_textoIdentificador.setEditable(false);
		_panelIdentificador.add(_labelIdentificador);
		_panelIdentificador.add(_textoIdentificador);
		
		//Etiqueta del nombre y campo para rellenar
		_labelNombre = new JLabel("Nombre:");
		_textoNombre = new JTextField();
		_textoNombre.setPreferredSize(new Dimension(166,25));
		_textoNombre.setEditable(false);
		_textoNombre.setEditable(false);
		_panelNombre.add(_labelNombre);
		_panelNombre.add(_textoNombre);
		
		//Etiqueta del tipo y campo para rellenar
		_labelTipo = new JLabel("Tipo: ");
		_textoTipo = new JTextField();
		_textoTipo.setPreferredSize(new Dimension(182, 25));
		_textoTipo.setEditable(false);
		_panelTipo.add(_labelTipo);
		_panelTipo.add(_textoTipo);
		
		//Etiqueta del precio y campo para rellenar
		_labelPrecio = new JLabel("Precio: ");
		_textoPrecio = new JTextField();
		_textoPrecio.setPreferredSize(new Dimension(168, 25));
		_textoPrecio.setEditable(false);
		_panelPrecio.add(_labelPrecio);
		_panelPrecio.add(_textoPrecio);
		
		//Etiqueta del stock y campo para rellenar
		_labelStock = new JLabel("Stock disponible: ");
		_textoStock = new JTextField();
		_textoStock.setPreferredSize(new Dimension(110, 25));
		_textoStock.setEditable(false);
		_panelStock.add(_labelStock);
		_panelStock.add(_textoStock);
		
		//Etiqueta de las unidades y campo para rellenar
		_labelUnidades = new JLabel("Unidades: ");
		_textoUnidades = new JTextField();
		_textoUnidades.setText("1");
		_textoUnidades.setPreferredSize(new Dimension(152, 25));
		_panelUnidades.add(_labelUnidades);
		_panelUnidades.add(_textoUnidades);
		
		//Botones de agregar, cancelar y confirmar
		_botonAgregar = new JButton("Agregar Producto");
		_botonAgregar.setPreferredSize(new Dimension(140, 25));
		_botonCancelar = new JButton("Cancelar");
		_botonCancelar.setPreferredSize(new Dimension(100, 25));
		_botonConfirmar = new JButton("Confirmar");
		_botonConfirmar.setPreferredSize(new Dimension (100, 25));
		_panelBotones.add(_botonConfirmar);
		_panelBotones.add(_botonAgregar);
		_panelBotones.add(_botonCancelar);
		
		//Panel de texto con la información
		_textoInfo = new JTextPane();
		_textoInfo.setText("Utilizar el identificador para eliminar una venta y los " + '\n' + "demás campos para agregar"
				+ "/modificar una venta.");
		_textoInfo.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED));
		_textoInfo.setEditable(false);
		
		//Añadir cada panel al panel de operaciones principal
		_panelOperaciones.add(_panelVenta);
		_panelOperaciones.add(_panelIdentificador);
		_panelOperaciones.add(_panelNombre);
		_panelOperaciones.add(_panelTipo);
		_panelOperaciones.add(_panelPrecio);
		_panelOperaciones.add(_panelStock);
		_panelOperaciones.add(_panelUnidades);
		_panelOperaciones.add(_panelBotones);
		_panelOperaciones.add(_textoInfo);
		_panelOperaciones.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
	}

	/**
	 * Función que inicializa todos los listenners de cada botón
	 */
	private void initEventos()
	{
		//Botón para cancelar
		_botonCancelar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea cancelar la operación?", "Cancelar", 
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (respuesta == JOptionPane.YES_OPTION)
					ponerEnBlanco();
			}
		});
		
		//Botón para agregar un producto a la venta
		_botonAgregar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				int posicion =	_listaProductosGUI.getSelectedIndex();
				Integer x = new Integer(posicion);	
				
				if(x.equals(-1))
					JOptionPane.showConfirmDialog(null, "Debes seleccionar el producto que quieras añadir", "Error", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				else
				{
					Integer id = _listaIdProductosGUI.get(posicion);
					
					String unidades = _textoUnidades.getText();
					
					try
					{
						int numero_unidades = Integer.parseInt(unidades);
						
						if (numero_unidades <= 0)
							JOptionPane.showConfirmDialog(null, "Dato de las unidades no válido", "Error", 
									JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						else
						{
							if (!_modeloProductos.contains(_modeloProductosGUI.getElementAt(x)))
							{
								_modeloProductos.addElement(_modeloProductosGUI.getElementAt(x));
								_listaNumProductos.add(numero_unidades);
								_listaProductos.add(id);
							}
							else
							{
								int indiceProducto = _modeloProductos.indexOf(_modeloProductosGUI.getElementAt(x));
								int unidadesVendidas = _listaNumProductos.get(_modeloProductos.indexOf(_modeloProductosGUI.getElementAt(x)));
								_listaNumProductos.remove(indiceProducto);
								_listaNumProductos.add(indiceProducto, unidadesVendidas - numero_unidades);
								
							}
								
							
							//JOptionPane.showConfirmDialog(null, "Producto agregado a la venta con éxito", "Éxito", 
								//	JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
							
							ponerEnBlanco();
						}
					}
					catch (Exception w)
					{
						JOptionPane.showConfirmDialog(null, "Dato de las unidades no válido", "Error", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
				
		//Botón confirmar venta
		_botonConfirmar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int nArti = 0;
				
				for (int i = 0; i < _listaNumProductos.size(); i ++)
					nArti += _listaNumProductos.get(i);
				
				Date dt = new Date();
		    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		    	String currentTime = sdf.format(dt);
		    	
				Venta venta = new Venta(0, nArti, 0, currentTime);
				
				try 
				{
					_controlador.executeNuevaVEnta(venta, _listaProductos, _listaNumProductos);
					
					//SI SE HE PODIDO CONFIRMAR
					JOptionPane.showConfirmDialog(null, "Venta agregada con éxito", "Éxito", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				} catch (Exception e1) 
				{
					//SI NO SE HA PODIDO CONFIRMAR
					JOptionPane.showConfirmDialog(null, "Fallo al agregar la venta: " + e1.getMessage(), "Fallo", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					
					
				}
				
				_modeloProductos.clear();
				_listaProductosVenta.removeAll();
				_listaProductos.clear();
				_listaNumProductos.clear();
				
				ponerEnBlanco();
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#historicoVentas()
	 */
	@Override
	public void historicoVentas()
	{
		try 
		{
			_controlador.executeHistoricoDeVentas();
			_controlador.executeInicializaProductos();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		ponerEnBlanco();
		removeAll();;
		add(_panelListaVentas);
		add(_panelOperaciones);
		_labelVentas.setText("VENTAS: HISTÓRICO");
		_textoUnidades.setEditable(false);
		_botonAgregar.setEnabled(false);
		_botonCancelar.setEnabled(false);
		_botonConfirmar.setEnabled(false);
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#nuevaVenta()
	 */
	@Override
	public void nuevaVenta()
	{
		try 
		{
			_controlador.executeHistoricoDeVentas();
			_controlador.executeInicializaProductos();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		ponerEnBlanco();
		removeAll();;
		add(_panelListaProductos);
		add(_panelOperaciones);
		_labelVentas.setText("VENTAS: AGREGAR");
		_textoUnidades.setEditable(true);
		_botonAgregar.setEnabled(true);
		_botonCancelar.setEnabled(true);
		_botonConfirmar.setEnabled(true);
	}

	/**
	 * Función utilizada para resetear los JTextField a blanco
	 */
	private void ponerEnBlanco()
	{
		_textoIdentificador.setText("");
		_textoNombre.setText("");
		_textoTipo.setText("");
		_textoPrecio.setText("");
		_textoUnidades.setText("");
		_textoStock.setText("");
		_textoDescripcion.setText("");
		_textoDescripcionVentas.setText("");
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#actulizarLista(java.util.ArrayList)
	 */
	@Override
	public void actualizarLista(ArrayList<Object> lista) 
	{
		ponerEnBlanco();
		_listaVentas.removeAll();
		_modeloVentas.clear();
		_listaProductosVenta.removeAll();
		_modeloProductosVentas.clear();
		_listaId.clear();
		_listaIdProductos.clear();
		
		for (int i = 0; i < lista.size(); i ++)
		{
			_modeloVentas.addElement(((Venta) lista.get(i)).getIdPersonal() + " "  + ((Venta) lista.get(i)).getFecha());
			_listaId.add(((Venta) lista.get(i)).getId());
		}
		
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#actualizarDatos(java.lang.Object)
	 */
	@Override
	public void actualizarDatos(Object objeto) 
	{
		Producto producto = (Producto) objeto;
		Double precio = new Double(producto.getPrecio());

		_textoNombre.setText(producto.getNombre());
		_textoTipo.setText(producto.getTipo());
		_textoPrecio.setText(precio.toString() + " €");
		_textoStock.setText(new Integer(producto.getStock()).toString());
		_textoDescripcionVentas.setText(producto.getDescripcion());
		_textoDescripcion.setText(producto.getDescripcion());
	}

	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#actualizarListaSecundaria(java.util.ArrayList)
	 */
	@Override
	public void actualizarListaSecundaria(ArrayList<Object> lista) 
	{
		ponerEnBlanco();
		_modeloProductosVentas.clear();
		_listaIdProductos.clear();
		_listaProductosVenta.removeAll();
		
		for (int i = 0; i < lista.size(); i ++)
		{
			_modeloProductosVentas.addElement(((Producto) lista.get(i)).getNombre());
			_listaIdProductos.add(((Producto) lista.get(i)).getId());
		}
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#actualizarListaGUI(java.util.ArrayList)
	 */
	@Override
	public void actualizarListaGUI(ArrayList<Object> lista) 
	{
		ponerEnBlanco();
		_listaProductosGUI.removeAll();
		_modeloProductosGUI.clear();
		_listaIdProductosGUI.clear();
		
		for (int i = 0; i < lista.size(); i ++)
		{
			if (((Producto) lista.get(i)).getDisponible())
			{
				_modeloProductosGUI.addElement(((Producto) lista.get(i)).getNombre());
				_listaIdProductosGUI.add(((Producto) lista.get(i)).getId());
			}
		}
	}
	
	//FUNCIONES QUE NO NECESITAN IMPLEMENTACIÓN EN ESTA CLASE
	public void buscarProducto(){}

	public void nuevoProducto(){}

	public void modificarProducto(){}

	public void eliminarProducto(){}

	public void buscarPedido() {}

	public void nuevoPedido() {}

	public void eliminarPedido() {}

	public void altaPersonal() {}

	public void bajaPersonal() {}
	
	public void modificarPersonal() {}
	
	public void consultarPersonal() {}
	
	public void actualizarMenu(Rango rango) {}

	public void cambioSesion() {}

	public void pedidosTotal(double cantidad) {}

	public void ventasTotal(double cantidad) {}
}