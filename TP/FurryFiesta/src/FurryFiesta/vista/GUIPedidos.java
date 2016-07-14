package FurryFiesta.vista;

import java.text.SimpleDateFormat;
import java.util.*;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.DefaultListCellRenderer.UIResource;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import FurryFiesta.EstadoPedido;
import FurryFiesta.controlador.ControladorPedidos;
import FurryFiesta.modelo.pedido.Pedido;
import FurryFiesta.modelo.personal.Rango;
import FurryFiesta.modelo.productos.Producto;

/**
 * @author Alejandro Huertas Herrero
 *
 */
public class GUIPedidos extends JPanel implements Observador
{
	/**
	 * Constante para quitar el warning
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Etiquetas utilizadas en el panel para mostrar toda la información
	 * relacionada con los pedidos
	 */
	private JLabel _labelPedido, _labelIdentificador, _labelNombre, _labelCategoria, _labelTipo, _labelStock, 
						_labelListaPedidos, _labelProductos, _labelProductosPedidos, _labelListaProductos;
	
	/**
	 * Campos de texto utilizados para obtener los datos
	 * necesarios para crear el objeto transfer de cada pedido
	 */
	private JTextField _textoIdentificador, _textoNombre, _textoTipo, _textoStock;
	
	/**
	 * Selector de elementos que permite elegir
	 * la categoria por la que queremos filtrar los pedidos
	 */
	private JComboBox<EstadoPedido> _comboCategoria;
	
	/**
	 * Botones que permiten realizar las diferentes
	 * acciones relaciondas con los pedidos
	 */
	private JButton _botonAgregarPedido, _botonEliminar, _botonCancelar, _botonAgregar, _botonConfirmarPedido;
	
	/**
	 * Diferentes paneles a los que se añaden los componentes
	 * de la ventana para luego formar un único panel de la ventana
	 * de los pedidos que será cargado en el frame principal
	 */
	private JPanel _panelPedido, _panelIdentificador, _panelNombre, _panelCombo, _panelTipo, _panelStock, _panelAceptar,
						_panelCancelar, _panelListaPedidos, _panelOperaciones, _panelProductosPedidos, _panelProductos, _panelListaProductos;
	
	/**
	 * Paneles de texto que se uitilizan para mostrar la informacion de cada pedido
	 */
	private JTextPane _textoDescripcion, _textoInfo, _textoDescripcionPedido;
	
	/**
	 * Controlador que servirá para comunicarse con el modelo de pedidos
	 */
	private ControladorPedidos _controlador;
	
	/**
	 * Lista utilizada para mostrar todos los pedidos y para mostrar todos
	 * los productos de un pedido
	 */
	private JList<Object> _listaPedidos, _listaProductosPedido, _listaProductosGUI, _listaProductosPedidoHecho;
	
	/**
	 * Scroll usado para cuando los pedidos sean muchos poder desplazarse
	 * entre todos ellos y para desplazarse en la lista de productos de un pedido
	 */
	private JScrollPane _scrollPedidos, _scrollProductos, _scrollProductosGUI, _scrollProductosPedidos;
	
	/**
	 * Modelos que nos permiten mostrar en las listas los elementos que hay
	 * en la base de datos
	 */
	private DefaultListModel<Object> _modeloPedidos, _modeloProductos, _modeloProductosGUI, _modeloProductosPedidos;
	
	/**
	 * Lista que contiene los ids de los productos y los numeros de productos comprados
	 */
	private ArrayList<Integer> _listaId, _listaNumProductos, _listaIdProductos, _listaIdProductosGUI, _listaProductos;
	
	/**
	 * @param control controlador utilizado por la vista para comunicarse con el modelo
	 */
	public GUIPedidos(ControladorPedidos control) 
	{
		initPanelListaPedidos();
		initPanelListaProductos();
		initPanelOperaciones();
		initEventos();
		
		setLayout(new GridLayout(1, 2));
		
		//Diferentes arrays para recoger los datos del pedido
		_listaId = new ArrayList<Integer>();
		_listaIdProductos = new ArrayList<Integer>();
		_listaNumProductos = new ArrayList<Integer>();
		_listaIdProductosGUI = new ArrayList<Integer>();
		_listaProductos = new ArrayList<Integer>();
			
		//Controlador y observadores de la vista
		_controlador = control;
		_controlador.registerObserver(this);
		_controlador.registerObserverProductos(this);
	} 

	/**
	 * Función que inicializa el panel de la lista de pedidos junto
	 * con la descrpción de cada pedido
	 */
	private void initPanelListaPedidos() 
	{
		//Posición utilizada para centrar los elementos dentro de las listas
		UIResource posicion = new UIResource();
		posicion.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Crea los dos paneles que forman el panel general de las listas
		_panelListaPedidos = new JPanel(new BorderLayout());
		_panelProductosPedidos = new JPanel(new BorderLayout());
		
		//Etiqueta de la lista de pedidos
		_labelListaPedidos = new JLabel("Lista de pedidos");
		_labelListaPedidos.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Modelo y lista de los pedidos
		_modeloPedidos = new DefaultListModel<Object>();
		_listaPedidos = new JList<Object>(_modeloPedidos);
		_listaPedidos.setCellRenderer(posicion);
		_listaPedidos.addListSelectionListener(new ListSelectionListener() 
		{
			public void valueChanged(ListSelectionEvent e) 
			{
				try 
				{
					ponerEnBlanco();
					int posicion =	_listaPedidos.getSelectedIndex();;
					Integer i = new Integer(posicion);
					
					if (!i.equals(-1))
					{
						Integer id = _listaId.get(posicion);
						_controlador.consultarPedido(id);
						_textoIdentificador.setText(id.toString());
					}
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		_scrollPedidos = new JScrollPane();
		_scrollPedidos.setViewportView(_listaPedidos);
		
		//Etiqueta de los productos que hay en un pedido
		_labelProductosPedidos = new JLabel("Productos del pedido");
		_labelProductosPedidos.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Modelo y lista de los productos de un pedido
		_modeloProductosPedidos = new DefaultListModel<Object>();
		_listaProductosPedidoHecho = new JList<Object>(_modeloProductosPedidos);
 		_listaProductosPedidoHecho.setCellRenderer(posicion);
 		_listaProductosPedidoHecho.addListSelectionListener(new ListSelectionListener()
 		{
			public void valueChanged(ListSelectionEvent e) 
			{
				try 
				{
					int posicion =	_listaProductosPedidoHecho.getSelectedIndex(), id;
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
		_scrollProductosPedidos = new JScrollPane();
		_scrollProductosPedidos.setViewportView(_listaProductosPedidoHecho);
		
		//Panel de la descripocion de cada pedido
		_textoDescripcionPedido = new JTextPane();
		_textoDescripcionPedido.setBackground(Color.DARK_GRAY);
		_textoDescripcionPedido.setEditable(false);
		_textoDescripcionPedido.setPreferredSize(new Dimension (200, 50));
		_textoDescripcionPedido.setForeground(Color.WHITE);
		TitledBorder titulo = BorderFactory.createTitledBorder("Descripción");
		titulo.setTitleColor(Color.BLUE);
		_textoDescripcionPedido.setBorder(titulo);
		
		//Añadir al panel de la lista de productos del pedido sus componentes
		_panelProductosPedidos.add(_labelProductosPedidos, BorderLayout.NORTH);
		_panelProductosPedidos.add(_scrollProductosPedidos, BorderLayout.CENTER);
		_panelProductosPedidos.add(_textoDescripcionPedido, BorderLayout.SOUTH);
		
		//Añadir al panel de la lista todos sus componentes
		_panelListaPedidos.add(_labelListaPedidos, BorderLayout.NORTH);
		_panelListaPedidos.add(_scrollPedidos, BorderLayout.CENTER);
		_panelListaPedidos.add(_panelProductosPedidos, BorderLayout.SOUTH);
		_panelListaPedidos.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
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
						_textoStock.setText("");
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
		_labelProductos = new JLabel("Productos del pedido");
		_labelProductos.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Modelo y lista de los productos de un pedido
		_modeloProductos = new DefaultListModel<Object>();
		_listaProductosPedido = new JList<Object>(_modeloProductos);
 		_listaProductosPedido.setCellRenderer(posicion);
 		_listaProductosPedido.setEnabled(false);
		_scrollProductos = new JScrollPane();
		_scrollProductos.setViewportView(_listaProductosPedido);
		
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
	 * realizar operaciones con los pedidos
	 */
	private void initPanelOperaciones() 
	{
		//Crea todos los paneles y el panel de las operaciones
		_panelOperaciones = new JPanel(new GridLayout (9, 1));
		_panelPedido = new JPanel(new FlowLayout());
		_panelIdentificador = new JPanel(new FlowLayout());
		_panelNombre = new JPanel(new FlowLayout());
		_panelCombo = new JPanel(new FlowLayout());
		_panelTipo = new JPanel(new FlowLayout());
		_panelStock = new JPanel(new FlowLayout());
		_panelAceptar = new JPanel(new FlowLayout());
		_panelCancelar = new JPanel(new FlowLayout());
		
		//Etiqueta con el título
		_labelPedido = new JLabel("PEDIDOS");
		_labelPedido.setHorizontalAlignment(SwingConstants.CENTER);
		_labelPedido.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED));
		_panelPedido.add(_labelPedido);
		
		//Etiqueta del identificador y campo para rellenar 
		_labelIdentificador = new JLabel("Identificador pedido:");
		_textoIdentificador = new JTextField();
		_textoIdentificador.setPreferredSize(new Dimension(100,25));
		_panelIdentificador.add(_labelIdentificador);
		_panelIdentificador.add(_textoIdentificador);
		
		//Etiqueta del nombre y campo para rellenar
		_labelNombre = new JLabel("Nombre:");
		_textoNombre = new JTextField();
		_textoNombre.setPreferredSize(new Dimension(166,25));
		_panelNombre.add(_labelNombre);
		_panelNombre.add(_textoNombre);
		
		//Etiqueta de la categoría y combo para seleccionar
		_labelCategoria = new JLabel("Estado/Filtro");
		_comboCategoria = new JComboBox<EstadoPedido>();
		_comboCategoria.addItem(EstadoPedido.TODOS);
		_comboCategoria.addItem(EstadoPedido.CONFIRMADO);
		_comboCategoria.addItem(EstadoPedido.NO_CONFIRMADO);
		_comboCategoria.setSelectedIndex(EstadoPedido.TODOS.ordinal());
		_comboCategoria.setPreferredSize(new Dimension(126, 25));
		_panelCombo.add(_labelCategoria);
		_panelCombo.add(_comboCategoria);
		 
		//Etiqueta del tipo y campo para rellenar
		_labelTipo = new JLabel("Tipo: ");
		_textoTipo = new JTextField();
		_textoTipo.setPreferredSize(new Dimension(182, 25));
		_panelTipo.add(_labelTipo);
		_panelTipo.add(_textoTipo);
		
		//Etiqueta del sttock y campo para rellenar
		_labelStock = new JLabel("Stock: ");
		_textoStock = new JTextField();
		_textoStock.setPreferredSize(new Dimension(170, 25));
		_panelStock.add(_labelStock);
		_panelStock.add(_textoStock);
		
		//Botones de agregar y eliminar
		_botonAgregar = new JButton("Agregar producto");
		_botonAgregar.setPreferredSize(new Dimension(140, 25));
		_botonAgregarPedido = new JButton("Agregar");
		_botonAgregarPedido.setPreferredSize(new Dimension(80, 25));
		_botonEliminar = new JButton("Cancelar Pedido");
		_botonEliminar.setPreferredSize(new Dimension(140, 25));
		_panelAceptar.add(_botonAgregarPedido);
		_panelAceptar.add(_botonAgregar);
		_panelAceptar.add(_botonEliminar);
		
		//Botón de confirmar
		_botonConfirmarPedido = new JButton("Confirmar");
		_botonConfirmarPedido.setPreferredSize(new Dimension(100, 25));
		_panelCancelar.add(_botonConfirmarPedido);
		
		//Botón de cancelar
		_botonCancelar = new JButton("Cancelar");
		_botonCancelar.setPreferredSize(new Dimension(100, 25));
		_panelCancelar.add(_botonCancelar);
		
		//Panel de texto con la información
		_textoInfo = new JTextPane();
		_textoInfo.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED));
		_textoInfo.setEditable(false);
	
		
		//Añadir cada panel al panel de operaciones principal
		_panelOperaciones.add(_panelPedido);
		_panelOperaciones.add(_panelIdentificador);
		_panelOperaciones.add(_panelCombo);
		_panelOperaciones.add(_panelNombre);
		_panelOperaciones.add(_panelTipo);
		_panelOperaciones.add(_panelStock);
		_panelOperaciones.add(_panelAceptar);
		_panelOperaciones.add(_panelCancelar);
		_panelOperaciones.add(_textoInfo);
		_panelOperaciones.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
	}
	
	/**
	 * Función que inicializa todos los listenners de cada botón
	 */
	private void initEventos()
	{
		//Botón cancelar
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
		
		//Botón eliminar
		_botonEliminar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int posicion =	_listaPedidos.getSelectedIndex();
				Integer x = new Integer(posicion);	
				
				if(x.equals(-1))
					JOptionPane.showConfirmDialog(null, "Debes seleccionar el pedido que quieras cancelar", "Error", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				else
				{
					Integer id = _listaId.get(posicion);
					
					 try 
					 {
						_controlador.executeCancelarPedido(id);
						//Si se ha podido eliminar el producto
						JOptionPane.showConfirmDialog(null, "Pedido cancelado con éxito", "Éxito", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					} 
					 catch (Exception e1) 
					 {
						//Si no se ha podido eliminar el producto
						 JOptionPane.showConfirmDialog(null, "Fallo al cancelar el pedido: " + e1.getMessage(), "Error", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
					 
					ponerEnBlanco();
				}
			}
		});
		
		//Botón solicitar un pedido
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
					
					String stock = _textoStock.getText();
					
					try
					{
						int stockP = Integer.parseInt(stock);
						
						if (!_modeloProductos.contains(_modeloProductosGUI.getElementAt(x)))
						{
							_modeloProductos.addElement(_modeloProductosGUI.getElementAt(x));
							_listaNumProductos.add(stockP);
							_listaProductos.add(id);
						}
						else
						{
							int i = _modeloProductos.indexOf(_modeloProductosGUI.getElementAt(x));
							int a = _listaNumProductos.get(_modeloProductos.indexOf(_modeloProductosGUI.getElementAt(x)));
							_listaNumProductos.remove(i);
							_listaNumProductos.add(i, a + stockP);
							
						}
							
						
						//JOptionPane.showConfirmDialog(null, "Producto agregado al pedido con éxito", "Éxito", 
							//	JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
						
						ponerEnBlanco();
					}
					catch (Exception e1)
					{
						JOptionPane.showConfirmDialog(null, "Dato de stock no válido", "Fallo", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		//Botón agregar el pedido
		_botonAgregarPedido.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (_modeloProductos.isEmpty())
					JOptionPane.showConfirmDialog(null, "Debes agregar algún producto al pedido", "Error", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				else
				{
					int nArti = 0;
					
					for (int i = 0; i < _listaNumProductos.size(); i ++)
						nArti += _listaNumProductos.get(i);
					
					Date dt = new Date();
			    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			    	String currentTime = sdf.format(dt);
			    	
					Pedido pedido = new Pedido(0, nArti, 0, false, currentTime);
					
					try 
					{
						_controlador.executeAgregarPedido(pedido, _listaProductos, _listaNumProductos);
						
						//SI SE HE PODIDO CONFIRMAR
						JOptionPane.showConfirmDialog(null, "Pedido agregado con éxito", "Éxito", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					} 
					catch (Exception e1) 
					{
						//SI NO SE HA PODIDO CONFIRMAR
						JOptionPane.showConfirmDialog(null, "Fallo al agregar el pedido: " + e1.getMessage(), "Fallo", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
					
					_modeloProductos.clear();
					_listaProductosPedido.removeAll();
					_listaProductos.clear();
					_listaNumProductos.clear();
					ponerEnBlanco();
				}
			}
		});
		
		//Botón buscar
		_comboCategoria.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				String categoria;
				
				categoria = _comboCategoria.getSelectedItem().toString();
				
				try 
				{
					_controlador.executeBuscarPedidoFiltro(categoria);
					
					if (categoria.equals("NO_CONFIRMADO"))
						_botonConfirmarPedido.setEnabled(true);
					else if (categoria.equals("CONFIRMADO"))
						_botonConfirmarPedido.setEnabled(false);
				} 
				catch (Exception e1) 
				{
					 JOptionPane.showConfirmDialog(null, "Ningún pedido encontrado: " + e1.getMessage(), "Error", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					
				}
			}
		});
		
		//Botón confirmar
		_botonConfirmarPedido.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int posicion =	_listaPedidos.getSelectedIndex();
				Integer x = new Integer(posicion);	
				
				if(x.equals(-1))
					JOptionPane.showConfirmDialog(null, "Debes seleccionar el pedido que quieras confirmar", "Error", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				else
				{
					Integer id = _listaId.get(posicion);
					
					 try 
					 {
						 
						 _controlador.executeConfirmarPedido(id);
						//Si se ha podido eliminar el producto
						JOptionPane.showConfirmDialog(null, "Pedido confirmado con éxito", "Éxito", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					} 
					 catch (Exception e1) 
					 {
						//Si no se ha podido eliminar el producto
						 JOptionPane.showConfirmDialog(null, "Fallo al confirmar el pedido: " + e1.getMessage(), "Error", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
					 
					ponerEnBlanco();
				}
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#buscarPedido()
	 */
	@Override
	public void buscarPedido()
	{
		try 
		{
			_controlador.executeInicializa();
			_controlador.executeInicializaProductos();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		ponerEnBlanco();
		removeAll();;
		add(_panelListaPedidos);
		add(_panelOperaciones);
		_labelPedido.setText("PEDIDOS: BUSCAR");
		_textoIdentificador.setEditable(false);
		_textoNombre.setEditable(false);
		_textoTipo.setEditable(false);
		_comboCategoria.setEnabled(true);
		_textoStock.setEditable(false);
		_botonAgregar.setEnabled(false);
		_botonAgregarPedido.setEnabled(false);
		_botonEliminar.setEnabled(false);
		_botonCancelar.setEnabled(false);
		_botonConfirmarPedido.setEnabled(false);
		_textoInfo.setText("Utiliza los filtros y pulsa buscar para que te aparezcan los pedidos " + '\n'
				+ "con el filtro. Pulsa en el pedido y te saldrán todos sus datos.");
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#nuevoPedido()
	 */
	@Override
	public void nuevoPedido()
	{
		try 
		{
			_controlador.executeInicializa();
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
		_labelPedido.setText("PEDIDOS: REALIZAR NUEVO");
		_textoIdentificador.setEditable(false);
		_textoNombre.setEditable(false);
		_textoTipo.setEditable(false);
		_textoStock.setEditable(true);
		_comboCategoria.setEnabled(false);
		_botonAgregar.setEnabled(true);
		_botonAgregarPedido.setEnabled(true);
		_botonEliminar.setEnabled(false);
		_botonCancelar.setEnabled(true);
		_botonConfirmarPedido.setEnabled(false);
		_textoInfo.setText("Añade el nombre del producto que deseas añadir al pedido y pulsa " + '\n'
				+ "agregar producto para añadirlo al pedido. Pulsa agregar para agregar");
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#eliminarPedido()
	 */
	@Override
	public void eliminarPedido() 
	{
		try 
		{
			_controlador.executeBuscarPedidoFiltro("NO_CONFIRMADO");
			_controlador.executeInicializaProductos();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}		
		ponerEnBlanco();
		removeAll();;
		add(_panelListaPedidos);
		add(_panelOperaciones);
		_listaNumProductos.clear();
		_labelPedido.setText("PEDIDOS: CANCELAR");
		_textoIdentificador.setEditable(false);
		_textoNombre.setEditable(false);
		_textoTipo.setEditable(false);
		_comboCategoria.setEnabled(false);
		_textoStock.setEditable(false);
		_botonAgregar.setEnabled(false);
		_botonAgregarPedido.setEnabled(false);
		_botonEliminar.setEnabled(true);
		_botonCancelar.setEnabled(true);
		_botonConfirmarPedido.setEnabled(false);
		_textoInfo.setText("Selecciona de la lista de pedidos aquel que desees eliminar.");
	}
	
	/**
	 * Función utilizada para resetear los JTextField a blanco
	 */
	private void ponerEnBlanco()
	{
		_textoIdentificador.setText("");
		_textoNombre.setText("");
		_textoTipo.setText("");
		_textoStock.setText("");
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#actulizarLista(java.util.ArrayList)
	 */
	@Override
	public void actualizarLista(ArrayList<Object> lista) 
	{
		ponerEnBlanco();
		_listaPedidos.removeAll();
		_modeloPedidos.clear();
		_listaProductosPedido.removeAll();
		_modeloProductosPedidos.clear();
		_listaId.clear();
		_listaIdProductos.clear();
		
		
		for (int i = 0; i < lista.size(); i ++)
		{
			_modeloPedidos.addElement(((Pedido) lista.get(i)).getIdPersonal() + " "  + ((Pedido) lista.get(i)).getFecha());
			_listaId.add(((Pedido) lista.get(i)).getId());
		}
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#actualizarDatos(java.lang.Object)
	 */
	@Override
	public void actualizarDatos(Object objeto) 
	{
		Producto producto = (Producto) objeto;
		Double stock = new Double(producto.getStock());

		_textoNombre.setText(producto.getNombre());
		_textoTipo.setText(producto.getTipo());
		_textoStock.setText(stock.toString());
		_textoDescripcionPedido.setText(producto.getDescripcion());
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#actualizarListaSecundaria(java.util.ArrayList)
	 */
	@Override
	public void actualizarListaSecundaria(ArrayList<Object> lista) 
	{
		ponerEnBlanco();
		_modeloProductosPedidos.clear();
		_listaIdProductos.clear();
		_listaProductosPedido.removeAll();
		
		for (int i = 0; i < lista.size(); i ++)
		{
			_modeloProductosPedidos.addElement(((Producto) lista.get(i)).getNombre());
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
	public void historicoVentas() {}

	public void nuevaVenta() {}

	public void eliminarVenta() {}

	public void buscarProducto() {}

	public void nuevoProducto() {}

	public void modificarProducto() {}

	public void eliminarProducto() {}
	
	public void altaPersonal() {}
	
	public void bajaPersonal() {}

	public void modificarPersonal() {}

	public void actulizarLista() {}
	
	public void consultarPersonal() {}

	public void actualizarMenu (Rango rango) {}

	public void devolverVenta() {}

	public void cambioSesion() {}

	public void pedidosTotal(double cantidad) {}

	public void ventasTotal(double cantidad) {}
}