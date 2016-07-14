package FurryFiesta.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.BorderFactory;
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
import javax.swing.DefaultListCellRenderer.UIResource;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import FurryFiesta.Categoria;
import FurryFiesta.controlador.ControladorProductos;
import FurryFiesta.modelo.personal.Rango;
import FurryFiesta.modelo.productos.Producto;

/**
 * @author Alejandro Huertas Herrero
 *
 */
public class GUIProductos extends JPanel implements Observador
{
	/**
	 * Constante para quitar el warning
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Etiquetas utilizadas en el panel para mostrar toda la información
	 * relacionada con los productos
	 */
	private JLabel _labelProducto, _labelIdentificador, _labelNombre, _labelCategoria, _labelTipo, 
						_labelPrecio, _labelLista;
	
	/**
	 * Campos de texto utilizados para obtener los datos
	 * necesarios para crear el objeto transfer de cada producto
	 */
	private JTextField _textoIdentificador, _textoNombre, _textoTipo, _textoPrecio;
	
	/**
	 * Selector de elementos que permite elegir
	 * la categoria por la que queremos filtrar los productos
	 */
	private JComboBox<Categoria> _comboCategoria, _comboCategoriaFiltro; 
	
	/**
	 * Botones que permiten realizar las diferentes
	 * acciones relaciondas con los productos
	 */
	private JButton _botonAgregar, _botonEliminar, _botonModificar, _botonCancelar, _botonBuscar;
	
	/**
	 * Diferentes paneles a los que se añaden los componentes
	 * de la ventana para luego formar un único panel de la ventana
	 * de los productos que será cargado en el frame principal
	 */
	private JPanel _panelProducto, _panelIdentificador, _panelNombre, _panelCombo, _panelTipo, _panelPrecio, 
						_panelAceptar, _panelCancelar, _panelLista, _panelOperaciones;
	
	/**
	 * Paneles de texto que se uitilizan para mostrar la informacion de cada producto
	 */
	private JTextPane _textoDescripcion, _textoInfo;

	/**
	 * Controlador que servirá para comunicarse con el modelo de productos
	 */
	private ControladorProductos _controlador;
	
	/**
	 * Lista utilizada para mostrar todos los productos
	 */
	private JList<Object> _listaProductos;
	
	/**
	 * Scroll usado para cuando los productos sean muchos poder desplazarse
	 * entre todos ellos
	 */
	private JScrollPane _scrollProductos;
	
	/**
	 * Modelos que nos permiten mostrar en las listas los elementos que hay
	 * en la base de datos
	 */
	private DefaultListModel<Object> _modeloProductos;
	
	/**
	 * Lista que contiene los ids de los productos
	 */
	private ArrayList<Integer> _listaId;
	
	/**
	 * @param control controlador utilizado por la vista para comunicarse con el modelo
	 */
	public GUIProductos(ControladorProductos control) 
	{
		initPanelLista();
		initPanelOperaciones();
		initEventos();
		
		setLayout(new GridLayout(1, 2));
		add(_panelLista);
		add(_panelOperaciones);
		
		_listaId = new ArrayList<Integer>();
		_controlador = control;
		_controlador.registerObserver(this);
	}

	/**
	 * Función que inicializa el panel de la lista de productos junto
	 * con la descrpción de cada producto
	 */
	private void initPanelLista() 
	{
		//Posición utilizada para centrar los elementos dentro de las listas
		UIResource posicion = new UIResource();
		posicion.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Crea el panel que contiene la lista de productos
		_panelLista = new JPanel(new BorderLayout());
		
		//Etiqueta de la lista de productos
		_labelLista = new JLabel("Lista de productos");
		_labelLista.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Modelo y lista de los productos
		_modeloProductos = new DefaultListModel<Object>();
		_listaProductos = new JList<Object>(_modeloProductos);
 		_listaProductos.setCellRenderer(posicion);
 		_listaProductos.addListSelectionListener(new ListSelectionListener()
 		{
			public void valueChanged(ListSelectionEvent e) 
			{
				try 
				{
					int posicion =	_listaProductos.getSelectedIndex(), id;
					Integer i = new Integer(posicion);
					
					if (!i.equals(-1))
					{
						id = _listaId.get(posicion);
						_controlador.executeConsultarProducto(id);
					}
				} 
				catch (Exception e1){}
			}
		});
		_scrollProductos = new JScrollPane();
		_scrollProductos.setViewportView(_listaProductos);
		
		//Panel de la descripocion de cada producto
		_textoDescripcion = new JTextPane();
		_textoDescripcion.setBackground(Color.DARK_GRAY);
		_textoDescripcion.setEditable(true);
		_textoDescripcion.setPreferredSize(new Dimension (200, 50));
		_textoDescripcion.setForeground(Color.WHITE);
		TitledBorder titulo = BorderFactory.createTitledBorder("Descripción");
		titulo.setTitleColor(Color.BLUE);
		_textoDescripcion.setBorder(titulo);
		
		//Añadir al panel de la lista todos sus componentes
		_panelLista.add(_labelLista, BorderLayout.NORTH);
		_panelLista.add(_scrollProductos, BorderLayout.CENTER);
		_panelLista.add(_textoDescripcion, BorderLayout.SOUTH);
		_panelLista.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
	}
	
	/**
	 * Función que inicializa cada uno de los paneles que permiten
	 * realizar operaciones con los productos
	 */
	private void initPanelOperaciones() 
	{
		//Crea todos los paneles y el panel de las operaciones
		_panelOperaciones = new JPanel(new GridLayout (9, 1));
		_panelProducto = new JPanel(new FlowLayout());
		_panelIdentificador = new JPanel(new FlowLayout());
		_panelNombre = new JPanel(new FlowLayout());
		_panelCombo = new JPanel(new FlowLayout());
		_panelTipo = new JPanel(new FlowLayout());
		_panelPrecio = new JPanel(new FlowLayout());
		_panelAceptar = new JPanel(new FlowLayout());
		_panelCancelar = new JPanel(new FlowLayout());
		
		//Etiqueta con el título
		_labelProducto = new JLabel("PRODUCTOS");
		_labelProducto.setHorizontalAlignment(SwingConstants.CENTER);
		_labelProducto.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED));
		_panelProducto.add(_labelProducto);
		
		//Etiqueta del identificador y campo para rellenar 
		_labelIdentificador = new JLabel("Identificador:");
		_textoIdentificador = new JTextField();
		_textoIdentificador.setPreferredSize(new Dimension(140,25));
		_panelIdentificador.add(_labelIdentificador);
		_panelIdentificador.add(_textoIdentificador);
		
		//Etiqueta del nombre y campo para rellenar
		_labelNombre = new JLabel("Nombre:");
		_textoNombre = new JTextField();
		_textoNombre.setPreferredSize(new Dimension(166,25));
		_panelNombre.add(_labelNombre);
		_panelNombre.add(_textoNombre);
		
		//Etiqueta de la categoría y combo para seleccionar
		_labelCategoria = new JLabel("Categoría/Filtro");
		_comboCategoria = new JComboBox<Categoria>();
		_comboCategoria.addItem(Categoria.ANIMAL);
		_comboCategoria.addItem(Categoria.COMIDA);
		_comboCategoria.addItem(Categoria.BEBIDA);
		_comboCategoria.addItem(Categoria.ACCESORIO);
		_comboCategoria.addItem(Categoria.TODOS);
		_comboCategoria.setSelectedIndex(Categoria.TODOS.ordinal());
		_comboCategoria.setPreferredSize(new Dimension(126, 25));
		_panelCombo.add(_labelCategoria);
		_panelCombo.add(_comboCategoria);
		
		//Etiqueta de la categoría y combo para seleccionar
		_labelCategoria = new JLabel("Categoría/Filtro");
		_comboCategoriaFiltro = new JComboBox<Categoria>();
		_comboCategoriaFiltro.addItem(Categoria.ANIMAL);
		_comboCategoriaFiltro.addItem(Categoria.COMIDA);
		_comboCategoriaFiltro.addItem(Categoria.BEBIDA);
		_comboCategoriaFiltro.addItem(Categoria.ACCESORIO);
		_comboCategoriaFiltro.addItem(Categoria.TODOS);
		_comboCategoriaFiltro.setSelectedIndex(Categoria.TODOS.ordinal());
		_comboCategoriaFiltro.setPreferredSize(new Dimension(126, 25));
		
		//Etiqueta del tipo y campo para rellenar
		_labelTipo = new JLabel("Tipo: ");
		_textoTipo = new JTextField();
		_textoTipo.setPreferredSize(new Dimension(182, 25));
		_panelTipo.add(_labelTipo);
		_panelTipo.add(_textoTipo);
		
		//Etiqueta del precio y campo para rellenar
		_labelPrecio = new JLabel("Precio: ");
		_textoPrecio = new JTextField();
		_textoPrecio.setPreferredSize(new Dimension(170, 25));
		_panelPrecio.add(_labelPrecio);
		_panelPrecio.add(_textoPrecio);
		
		//Botones de agregar y eliminar
		_botonAgregar = new JButton("Agregar");
		_botonAgregar.setPreferredSize(new Dimension(80, 25));
		_botonEliminar = new JButton("Eliminar");
		_botonEliminar.setPreferredSize(new Dimension(80, 25));
		_panelAceptar.add(_botonAgregar);
		_panelAceptar.add(_botonEliminar);
		
		//Botones cancelar, modificar y buscar
		_botonCancelar = new JButton("Cancelar");
		_botonCancelar.setPreferredSize(new Dimension(100, 25));
		_botonModificar = new JButton("Modificar");
		_botonModificar.setPreferredSize(new Dimension (100, 25));
		_botonBuscar = new JButton("Buscar");
		_botonBuscar.setPreferredSize(new Dimension (100, 25));
		_panelCancelar.add(_botonModificar);
		_panelCancelar.add(_botonBuscar);
		_panelCancelar.add(_botonCancelar);
		
		//Panel de texto con la información
		_textoInfo = new JTextPane();
		_textoInfo.setEditable(false);
		_textoInfo.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED));
		
		//Añadir cada panel al panel de operaciones principal
		_panelOperaciones.add(_panelProducto);
		_panelOperaciones.add(_panelIdentificador);
		_panelOperaciones.add(_panelNombre);
		_panelOperaciones.add(_panelCombo);
		_panelOperaciones.add(_panelTipo);
		_panelOperaciones.add(_panelPrecio);
		_panelOperaciones.add(_panelAceptar);
		_panelOperaciones.add(_panelCancelar);
		_panelOperaciones.add(_textoInfo);
		_panelOperaciones.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
	}

	/**
	 *  Función que inicializa todos los listenners de cada botón
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
				int posicion =	_listaProductos.getSelectedIndex();
				Integer x = new Integer(posicion);	
				
				if(x.equals(-1))
					JOptionPane.showConfirmDialog(null, "Debes seleccionar el producto que quieras eliminar", "Error", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				else
				{
					Integer id = _listaId.get(posicion);
					
					 try 
					 {
						_controlador.executeEliminarProducto(id);
						//Si se ha podido eliminar el producto
						JOptionPane.showConfirmDialog(null, "Producto eliminado con éxito", "Éxito", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					} 
					 catch (Exception e1) 
					 {
						//Si no se ha podido eliminar el producto
						 JOptionPane.showConfirmDialog(null, "Fallo al eliminar el producto: " + e1.getMessage(), "Error", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
					 
					ponerEnBlanco();
				}
			}
		});
		
		//Botón agregar
		_botonAgregar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e)
			{
				if (_textoNombre.getText().equals("") || _textoTipo.getText().equals("") || _textoPrecio.getText().equals("") 
						|| _textoDescripcion.getText().equals(""))
					JOptionPane.showConfirmDialog(null, "Debes rellenar todos los campos solicitados", "Error", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				else if (_comboCategoria.getSelectedItem().equals(Categoria.TODOS))
					JOptionPane.showConfirmDialog(null, "La categoría seleccionada no puede ser TODOS", "Error", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				else
				{
					String nombre, tipo, precio, categoria, descripcion;
					Double precio_double;
	
					nombre = _textoNombre.getText();
					tipo = _textoTipo.getText();
					precio = _textoPrecio.getText().replace("€", "");
					categoria = ((Categoria) _comboCategoria.getSelectedItem()).getTexto();
					descripcion = _textoDescripcion.getText();
					
					try
					{
						precio_double = Double.parseDouble(precio);
						
						if (precio_double <= 0)
							JOptionPane.showConfirmDialog(null, "Dato del precio no válido", "Error", 
									JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						else
						{
							Producto producto = new Producto (nombre, categoria, tipo, descripcion, precio_double);
							
							try
							{
								_controlador.executeAGregarProducto(producto);
								//Si se ha podido agregar el producto
								JOptionPane.showConfirmDialog(null, "Producto agregado con éxito", "Éxito", 
										JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
								
								ponerEnBlanco();
							}
							catch (Exception e1)
							{
								//Si no se ha podido agregar el producto
								JOptionPane.showConfirmDialog(null, "Fallo al agregar el producto: " + e1.getMessage(), "Error", 
										JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
								
								ponerEnBlanco();
							}
						}
					}
					catch (Exception x)
					{
						JOptionPane.showConfirmDialog(null, "Dato del precio no válido", "Error", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		//Botón modificar
		_botonModificar.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (_textoNombre.getText().equals("") || _textoTipo.getText().equals("") || _textoPrecio.getText().equals(""))
					JOptionPane.showConfirmDialog(null, "Debes rellenar todos los campos solicitados", "Error", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				else
				{
					String nombre, tipo, precio, id, descripcion, categoria;
					Double precio_double;
	
					nombre = _textoNombre.getText();
					tipo = _textoTipo.getText();
					precio = _textoPrecio.getText().replace("€", "");
					descripcion = _textoDescripcion.getText();
					categoria = ((Categoria) _comboCategoria.getSelectedItem()).getTexto();
					id = _textoIdentificador.getText();
					
					try
					{
						precio_double = Double.parseDouble(precio);
						
						Producto producto = new Producto (Integer.parseInt(id), nombre, categoria, tipo, descripcion, precio_double);
						
						try
						{
							_controlador.executeModificarProducto(producto);
							//Si se ha podido modificar el producto
							JOptionPane.showConfirmDialog(null, "Producto modificado con éxito", "Éxito", 
									JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
							
							ponerEnBlanco();
						}
						catch (Exception e1)
						{
							//Si no se ha podido mpodificar el producto
							JOptionPane.showConfirmDialog(null, "Fallo al modificar el producto" + e1.getMessage(), "Error", 
									JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
							
							ponerEnBlanco();
						}
					}
					catch (Exception x)
					{
						JOptionPane.showConfirmDialog(null, "Dato del precio no válido", "Error", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
		
		//Botón buscar
		_botonBuscar.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (_textoNombre.getText().equals(""))
					JOptionPane.showConfirmDialog(null, "Debes rellenar todos los campos solicitados", "Error", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				else
				{
					String nombre;
					
					nombre = _textoNombre.getText();
					
					try
					{
						_controlador.executeBuscarProductoNombre(nombre);
					}
					catch (Exception e1)
					{
						JOptionPane.showConfirmDialog(null, "Ningún producto disponible", "Error", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
						
						ponerEnBlanco();
					}
				}
			}
		});
		
		//Comobo de la categoria
		_comboCategoriaFiltro.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				if (_comboCategoriaFiltro.getSelectedItem().equals(null))
					JOptionPane.showConfirmDialog(null, "Debes rellenar todos los campos solicitados", "Error", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				else
				{
					String categoria;
					
					categoria = ((Categoria) _comboCategoriaFiltro.getSelectedItem()).getTexto();
					
					try
					{
						_controlador.executeBuscarFiltroProducto(categoria);
					}
					catch (Exception e1)
					{
						JOptionPane.showConfirmDialog(null, "Ningún producto disponible", "Error", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
					ponerEnBlanco();
				}
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#buscarProducto()
	 */
	@Override
	public void buscarProducto()
	{
		try 
		{
			_controlador.executeInicializa();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		ponerEnBlanco();
		_panelCombo.remove(_comboCategoria);
		_panelCombo.add(_comboCategoriaFiltro);
		_labelProducto.setText("PRODUCTOS: BUSCAR");
		_textoIdentificador.setEditable(false);
		_textoNombre.setEditable(true);
		_textoTipo.setEditable(false);
		_textoPrecio.setEditable(false);
		_textoDescripcion.setEditable(false);
		_comboCategoria.setEnabled(true);
		_botonAgregar.setEnabled(false);
		_botonEliminar.setEnabled(false);
		_botonBuscar.setEnabled(true);
		_botonModificar.setEnabled(false);
		_botonCancelar.setEnabled(false);
		_textoInfo.setText("Selecciona el prodcto del que deseas consultar sus datos." + '\n'
									+ "O introduce el nombre del producto que estás buscando.");
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#nuevoProducto()
	 */
	@Override
	public void nuevoProducto()
	{
		try 
		{
			_controlador.executeInicializa();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		ponerEnBlanco();
		_panelCombo.remove(_comboCategoriaFiltro);
		_panelCombo.add(_comboCategoria);
		_labelProducto.setText("PRODUCTOS: AGREGAR");
		_textoIdentificador.setEditable(false);
		_textoNombre.setEditable(true);
		_textoTipo.setEditable(true);
		_textoPrecio.setEditable(true);
		_textoDescripcion.setEditable(true);
		_comboCategoria.setEnabled(true);
		_botonAgregar.setEnabled(true);
		_botonEliminar.setEnabled(false);
		_botonBuscar.setEnabled(false);
		_botonModificar.setEnabled(false);
		_botonCancelar.setEnabled(true);
		_textoInfo.setText("Rellena todos los campos disponibles y pulsa agregar, para agregar el producto.");
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#eliminarProducto()
	 */
	@Override
	public void eliminarProducto() 
	{
		try 
		{
			_controlador.executeInicializa();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		ponerEnBlanco();
		_labelProducto.setText("PRODUCTOS: ELIMINAR");
		_textoIdentificador.setEditable(false);
		_textoNombre.setEditable(false);
		_textoTipo.setEditable(false);
		_textoPrecio.setEditable(false);
		_textoDescripcion.setEditable(false);
		_comboCategoria.setEnabled(false);
		_botonAgregar.setEnabled(false);
		_botonBuscar.setEnabled(false);
		_botonEliminar.setEnabled(true);
		_botonModificar.setEnabled(false);
		_botonCancelar.setEnabled(true);
		_textoInfo.setText("Selecciona el producto que deseas eliminar y pulsa eliminar, para realizarla.");
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#modificarProducto()
	 */
	@Override
	public void modificarProducto() 
	{
		try 
		{
			_controlador.executeInicializa();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
		ponerEnBlanco();
		_panelCombo.remove(_comboCategoriaFiltro);
		_panelCombo.add(_comboCategoria);
		_labelProducto.setText("PRODUCTOS: MODIFICAR");
		_textoIdentificador.setEditable(false);
		_textoNombre.setEditable(true);
		_textoTipo.setEditable(true);
		_textoPrecio.setEditable(true);
		_textoDescripcion.setEditable(false);
		_comboCategoria.setEnabled(true);
		_botonAgregar.setEnabled(false);
		_botonBuscar.setEnabled(false);
		_botonEliminar.setEnabled(false);
		_botonModificar.setEnabled(true);
		_botonCancelar.setEnabled(true);
		_textoInfo.setText("Rellena todos los campos disponibles y pulsa modificar, para modificar los datos.");
	}
	
	/**
	 * Función utilizada para resetear los JTextField a blanco
	 */
	private void ponerEnBlanco()
	{
		_textoNombre.setText("");
		_textoTipo.setText("");
		_textoPrecio.setText("");
		_textoIdentificador.setText("");
		_textoDescripcion.setText("");
		_comboCategoria.setSelectedIndex(Categoria.TODOS.ordinal());
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#actulizarLista(java.util.ArrayList)
	 */
	@Override
	public void actualizarLista(ArrayList<Object> lista) 
	{
		ponerEnBlanco();
		_listaProductos.removeAll();
		_modeloProductos.clear();
		_listaId.clear();
		
		for (int i = 0; i < lista.size(); i ++)
		{
			if (((Producto) lista.get(i)).getDisponible())
			{
				_modeloProductos.addElement(((Producto) lista.get(i)).getNombre());
				_listaId.add(((Producto) lista.get(i)).getId());
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#actualizarDatos(java.lang.Object)
	 */
	@Override
	public void actualizarDatos(Object objeto) 
	{
		Producto producto = (Producto) objeto;
		Integer id = new Integer(producto.getId());
		Double precio = new Double(producto.getPrecio());
		Categoria c = Categoria.getCategoria(producto.getCategoria());

		_textoIdentificador.setText(id.toString());
		_textoNombre.setText(producto.getNombre());
		_comboCategoria.setSelectedIndex(c.ordinal());
		_textoTipo.setText(producto.getTipo());
		_textoPrecio.setText(precio.toString() + "€");
		_textoDescripcion.setText(producto.getDescripcion());
	}

	//FUNCIONES QUE NO NECESITAN IMPLEMENTACIÓN EN ESTA CLASE
	public void historicoVentas() {}

	public void nuevaVenta() {}

	public void buscarPedido() {}

	public void nuevoPedido() {}

	public void eliminarPedido() {}

	public void altaPersonal() {}

	public void bajaPersonal() {}

	public void modificarPersonal() {}

	public void actualizarListaSecundaria(ArrayList<Object> lista) {}

	public void consultarPersonal() {}

	public void actualizarMenu(Rango rango) {}

	public void devolverVenta() {}

	public void cambioSesion() {}

	public void actualizarListaGUI(ArrayList<Object> lista) {}

	public void pedidosTotal(double cantidad) {}

	public void ventasTotal(double cantidad) {}
}