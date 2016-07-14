package FurryFiesta.vista;

import java.awt.BorderLayout;
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
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.SwingConstants;
import javax.swing.DefaultListCellRenderer.UIResource;
import javax.swing.border.BevelBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import FurryFiesta.controlador.ControladorPersonal;
import FurryFiesta.modelo.personal.Personal;
import FurryFiesta.modelo.personal.Rango;

/**
 * @author Alejandro Huertas Herrero
 *
 */
public class GUIPersonal extends JPanel implements Observador
{
	/**
	 * Constante para quitar el warning
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Etiquetas utilizadas en el panel para mostrar toda la información
	 * relacionada con el personal
	 */
	private JLabel _labelIdentificador, _labelPersonal, _labelNombre, _labelApellidos, _labelEdad, _labelDNI, 
						_labelTipoEmpleado, _labelLista, _labelPassword, _labelUsuario;
	
	/**
	 * Campos de texto utilizados para obtener los datos
	 * necesarios para crear el objeto transfer de cada empleado
	 */
	private JTextField _textoIdentificador, _textoNombre, _textoApellidos, _textoEdad, _textoDNI, 
							 _textoUsuario;
	
	/**
	 * Campo a rellenar para la password
	 */
	private JPasswordField _textoPassword;
	
	/**
	 * Botones que permiten realizar las diferentes
	 * acciones relaciondas con los empleados
	 */
	private JButton _botonAlta, _botonBaja, _botonModificar, _botonCancelar;
	
	/**
	 * Diferentes paneles a los que se añaden los componentes
	 * de la ventana para luego formar un único panel de la ventana
	 * del personal que será cargado en el frame principal
	 */
	private JPanel _panelIdentificador, _panelPersonal, _panelNombre, _panelApellidos, _panelEdad, 
						_panelDNI, _panelTipoEmpleado, _panelPassword, _panelAlta,	_panelCancelar,
							_panelLista, _panelOperaciones, _panelUsuario;
	
	/**
	 * Paneles de texto que se uitilizan para mostrar la informacion de cada empleado
	 */
	private JTextPane _textoInfo;
	
	/**
	 * Controlador que servirá para comunicarse con el modelo del personal
	 */
	private ControladorPersonal _controlador;
	
	/**
	 * Modelo que nos permiten mostrar en la lista los elementos que hay
	 * en la base de datos
	 */
	private DefaultListModel<Object> _modeloPersonal;
	
	/**
	 * Scroll usado para cuando los empleados sean muchos poder desplazarse
	 * entre todos ellos
	 */
	private JScrollPane _scrollPersonal;
	
	/**
	 * Lista utilizada para mostrar todos los empleados
	 */
	private JList<Object> _listaPersonal;
	
	/**
	 * Selector de elementos que permite elegir el rango del empleado a dar de alta
	 */
	private JComboBox<Rango> _comboRango;
	
	/**
	 * Lista que contiene los ids de los productos
	 */
	private ArrayList<Integer> _listaId;

	/**
	 * @param control controlador utilizado por la vista para comunicarse con el modelo
	 */
	public GUIPersonal(ControladorPersonal control) 
	{
		initPanelLista();
		initPanelOperaciones();
		initEventos();
		
		setLayout(new GridLayout(1, 2));
		
		add(_panelLista);
		add(_panelOperaciones);
		
		_controlador = control;
		_controlador.registerObserver(this);
		_listaId = new ArrayList<Integer>();
		_controlador = control;
		_controlador.registerObserver(this);
		try 
		{
			_controlador.executeInicializa();
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		}
	}

	/**
	 * Función que inicializa el panel de la lista de pedidos junto
	 * con la información de cada empleado
	 */
	private void initPanelLista() 
	{
		//Posición utilizada para centrar los elementos dentro de la lista
		UIResource posicion = new UIResource();
		posicion.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Panel que contiene todos los elementos de la lista del personal
		_panelLista = new JPanel(new BorderLayout());
		
		//Etiqueta de la lista de personal
		_labelLista = new JLabel("Lista de Personal");
		_labelLista.setHorizontalAlignment(SwingConstants.CENTER);
		
		//Modelo que contiene la lista del personal
		_modeloPersonal = new DefaultListModel<Object>();
		_listaPersonal = new JList<Object>(_modeloPersonal);
		_listaPersonal.setCellRenderer(posicion);
		_listaPersonal.addListSelectionListener(new ListSelectionListener()
 		{
			public void valueChanged(ListSelectionEvent e) 
			{
				try 
				{
					int posicion =	_listaPersonal.getSelectedIndex(), id;
					Integer i = new Integer(posicion);
					
					if (!i.equals(-1))
					{
						id = _listaId.get(posicion);
						_controlador.executeConsultarDatosPersonales(id);
					}
				} catch (Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		_scrollPersonal = new JScrollPane();
		_scrollPersonal.setViewportView(_listaPersonal);
		
		//Añadir todos los componentes al panel de la lista
		_panelLista.add(_labelLista, BorderLayout.NORTH);
		_panelLista.add(_scrollPersonal, BorderLayout.CENTER);
		_panelLista.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.RAISED));
	}
	
	/**
	 * Función que inicializa cada uno de los paneles que permiten
	 * realizar operaciones con el personal
	 */
	private void initPanelOperaciones() 
	{
		//Crea todos los paneles y el panel de las operaciones
		_panelOperaciones = new JPanel(new GridLayout (12, 1));
		_panelIdentificador = new JPanel(new FlowLayout());
		_panelPersonal = new JPanel(new FlowLayout());
		_panelNombre = new JPanel(new FlowLayout());
		_panelTipoEmpleado = new JPanel(new FlowLayout());
		_panelApellidos = new JPanel(new FlowLayout());
		_panelEdad = new JPanel(new FlowLayout());
		_panelDNI = new JPanel(new FlowLayout());
		_panelPassword = new JPanel(new FlowLayout());
		_panelUsuario = new JPanel(new FlowLayout());
		_panelAlta = new JPanel(new FlowLayout());
		_panelCancelar = new JPanel(new FlowLayout());
		
		//Etiqueta del personal
		_labelPersonal = new JLabel("PERSONAL");
		_labelPersonal.setHorizontalAlignment(SwingConstants.CENTER);
		_labelPersonal.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED));
		_panelPersonal.add(_labelPersonal);
		
		//Etiqueta del identificador y campo a rellenar
		_labelIdentificador= new JLabel("Identificador:");
		_textoIdentificador = new JTextField();
		_textoIdentificador.setPreferredSize(new Dimension(166,25));
		_panelIdentificador.add(_labelIdentificador);
		_panelIdentificador.add(_textoIdentificador);
		
		//Etiqueta del nombre y campo a rellenar
		_labelNombre = new JLabel("Nombre:");
		_textoNombre = new JTextField();
		_textoNombre.setPreferredSize(new Dimension(192,25));
		_panelNombre.add(_labelNombre);
		_panelNombre.add(_textoNombre);
		
		//Etiqueta de los apellidos y campo a rellenar
		_labelApellidos = new JLabel("Apellidos:");
		_textoApellidos = new JTextField();
		_textoApellidos.setPreferredSize(new Dimension(186,25));
		_panelApellidos.add(_labelApellidos);
		_panelApellidos.add(_textoApellidos);
		
		//Etiqueta de la edad y campo a rellenar
		_labelEdad= new JLabel("Edad:");
		_textoEdad = new JTextField();
		_textoEdad.setPreferredSize(new Dimension(208,25));
		_panelEdad.add(_labelEdad);
		_panelEdad.add(_textoEdad);
		
		//Etiqueta del DNI y campo a rellenar
		_labelDNI = new JLabel("DNI: ");
		_textoDNI = new JTextField();
		_textoDNI.setPreferredSize(new Dimension(212, 25));
		_panelDNI.add(_labelDNI);
		_panelDNI.add(_textoDNI);
		
		//Etiqueta del tipo de empleado y campo a rellenar
		_labelTipoEmpleado = new JLabel("Tipo de empleado: ");
		_comboRango = new JComboBox<Rango>();
		_comboRango.addItem(Rango.ADMINISTRADOR);
		_comboRango.addItem(Rango.EMPLEADO);
		_comboRango.setPreferredSize(new Dimension(130, 25));
		_panelTipoEmpleado.add(_labelTipoEmpleado);
		_panelTipoEmpleado.add(_comboRango);
		
		//Etiqueta del usuario y campo a rellenar
		_labelUsuario = new JLabel("Usuario: ");
		_textoUsuario = new JTextField();
		_textoUsuario.setPreferredSize(new Dimension(188, 25));
		_panelUsuario.add(_labelUsuario);
		_panelUsuario.add(_textoUsuario);
		
		//Etiqueta de la password y campo a rellenar
		_labelPassword= new JLabel("password: ");
		_textoPassword = new JPasswordField();
		_textoPassword.setPreferredSize(new Dimension(164, 25));
		_panelPassword.add(_labelPassword);
		_panelPassword.add(_textoPassword);
		
		//Botones de alta y baja
		_botonAlta = new JButton("Alta");
		_botonAlta.setPreferredSize(new Dimension(80, 25));
		_botonBaja = new JButton("Baja");
		_botonBaja.setPreferredSize(new Dimension(80, 25));
		_panelAlta.add(_botonAlta);
		_panelAlta.add(_botonBaja);
		
		//Botones de cancelar y modificar
		_botonCancelar = new JButton("Cancelar");
		_botonCancelar.setPreferredSize(new Dimension(100, 25));
		_botonModificar = new JButton("Modificar");
		_botonModificar.setPreferredSize(new Dimension (100, 25));
		_panelCancelar.add(_botonModificar);
		_panelCancelar.add(_botonCancelar);
		
		//Texto con la informacion
		_textoInfo = new JTextPane();
		_textoInfo.setEditable(false);
		_textoInfo.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED));
		
		//Añadir al panel de operaciones todos los componentes
		_panelOperaciones.add(_panelPersonal);
		_panelOperaciones.add(_panelIdentificador);
		_panelOperaciones.add(_panelNombre);
		_panelOperaciones.add(_panelApellidos);
		_panelOperaciones.add(_panelEdad);
		_panelOperaciones.add(_panelDNI);
		_panelOperaciones.add(_panelTipoEmpleado);
		_panelOperaciones.add(_panelUsuario);
		_panelOperaciones.add(_panelPassword);
		_panelOperaciones.add(_panelAlta);
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
		
		//Botón para dar de baja
		_botonBaja.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent e)
			{
				int posicion =	_listaPersonal.getSelectedIndex();
				Integer x = new Integer(posicion);	
				
				if(x.equals(-1))
					JOptionPane.showConfirmDialog(null, "Debes seleccionar el empleado que quieras dar de baja", "Error", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				else
				{
					Integer id = _listaId.get(posicion);
					
					 try 
					 {
						_controlador.executeBajaPersonal(id);
						//Si se ha podido dar de baja el empleado
						JOptionPane.showConfirmDialog(null, "Empleado dado de baja con éxito", "Éxito", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
					} 
					 catch (Exception e1) 
					 {
						///Si no se ha podido dar de baja el empleado
						 JOptionPane.showConfirmDialog(null, "Fallo al dar de baja el empleado: " + e1.getMessage(), "Error", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
					 
					ponerEnBlanco();
				}
			}
		});
		
		//Botón de dar de alta
		_botonAlta.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e)
			{
				if (_textoNombre.getText().equals("") || _textoApellidos.getText().equals("") || 
						_textoEdad.getText().equals("") || _textoDNI.getText().equals("") || 
							_textoPassword.getText().equals("") || _textoUsuario.equals("") ||_comboRango.getSelectedItem().equals(null))
					JOptionPane.showConfirmDialog(null, "Debes rellenar todos los campos solicitados", "Error", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				else
				{
					String nombre, apellidos, edad, DNI, password, usuario;
					Rango tipo;
					int edad_int;
					
					nombre = _textoNombre.getText();
					apellidos = _textoApellidos.getText();
					edad = _textoEdad.getText();
					DNI = _textoDNI.getText();
					tipo = (Rango) _comboRango.getSelectedItem();
					password = _textoPassword.getText();
					usuario = _textoUsuario.getText();
					
					try
					{
						edad_int = Integer.parseInt(edad);
						
						try
						{
							Personal empleado = new Personal(nombre, apellidos, edad_int, DNI, tipo, usuario, password);
							
							_controlador.executeAltaPersonal(empleado);
							
							//Si se ha podido realizar el alta
							JOptionPane.showConfirmDialog(null, "Alta realizada con éxito", "Éxito", 
									JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
							
							ponerEnBlanco();
							
						}
						catch (Exception y)
						{
							//Si no se ha podido realizar el alta
							JOptionPane.showConfirmDialog(null, "Fallo al realizar el alta: " + y.getMessage(), "Error", 
									JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
							
							ponerEnBlanco();
						}
					}
					catch (Exception x)
					{
						JOptionPane.showConfirmDialog(null, "Edad no válida", "Error", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
					
					
				}
			}
		});
		
		//Botón para modificar
		_botonModificar.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) 
			{
				if (_textoNombre.getText().equals("") || _textoApellidos.getText().equals("") || 
						_textoEdad.getText().equals("") || _textoDNI.getText().equals("") || 
							_textoPassword.getText().equals("") || _textoUsuario.equals(""))
					JOptionPane.showConfirmDialog(null, "Debes rellenar todos los campos solicitados", "Error", 
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				else
				{
					String nombre, apellidos, edad, DNI, password, usuario, id;
					Rango tipo;
					int edad_int;
					
					nombre = _textoNombre.getText();
					apellidos = _textoApellidos.getText();
					edad = _textoEdad.getText();
					DNI = _textoDNI.getText();
					tipo = (Rango) _comboRango.getSelectedItem();
					password = _textoPassword.getText();
					usuario = _textoUsuario.getText();
					id = _textoIdentificador.getText();
					
					try
					{
						edad_int = Integer.parseInt(edad);
						
						Personal empleado = new Personal(Integer.parseInt(id), nombre, apellidos, edad_int, DNI, tipo, usuario, password);
						
						try
						{
							_controlador.executeModificarPersonal(empleado);
							
							//Si se ha podido realizar la modificación
							JOptionPane.showConfirmDialog(null, "Datos personales modificados con éxito", "Éxito", 
									JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
							
							ponerEnBlanco();
							
						}
						catch (Exception y)
						{
							//Si no se han podido modificar los datos persoanles
							JOptionPane.showConfirmDialog(null, "Fallo al modificar datos personales: " + y.getMessage(), "Error", 
									JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
							
							ponerEnBlanco();
						}
					}
					catch (Exception x)
					{
						JOptionPane.showConfirmDialog(null, "Edad no válida", "Error", 
								JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					}
				}
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#altaPersonal()
	 */
	@Override
	public void altaPersonal()
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
		_labelPersonal.setText("PERSONAL: Alta");
		_textoIdentificador.setEditable(false);
		_textoNombre.setEditable(true);
		_textoDNI.setEditable(true);
		_textoApellidos.setEditable(true);
		_textoEdad.setEditable(true);
		_comboRango.setEnabled(true);
		_textoUsuario.setEditable(true);
		_textoPassword.setEditable(true);
		_botonAlta.setEnabled(true);
		_botonBaja.setEnabled(false);
		_botonModificar.setEnabled(false);
		_botonCancelar.setEnabled(true);
		_textoInfo.setText("Rellena todos los campos disponibles y pulsa alta, para realizar el alta.");
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#bajaPersonal()
	 */
	@Override
	public void bajaPersonal() 
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
		_labelPersonal.setText("PERSONAL: Baja");
		_textoIdentificador.setEditable(false);
		_textoNombre.setEditable(false);
		_textoDNI.setEditable(false);
		_textoApellidos.setEditable(false);
		_textoEdad.setEditable(false);
		_comboRango.setEnabled(false);
		_textoUsuario.setEditable(false);
		_textoPassword.setEditable(false);
		_botonAlta.setEnabled(false);
		_botonBaja.setEnabled(true);
		_botonModificar.setEnabled(false);
		_botonCancelar.setEnabled(true);
		_textoInfo.setText("Selecciona el empleado que deseas dar de baja y pulsa baja, para realizarla.");
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#modificarPersonal()
	 */
	@Override
	public void modificarPersonal() 
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
		_labelPersonal.setText("PERSONAL: Modificar");
		_textoIdentificador.setEditable(false);
		_textoNombre.setEditable(true);
		_textoDNI.setEditable(true);
		_textoApellidos.setEditable(true);
		_textoEdad.setEditable(true);
		_comboRango.setEnabled(true);
		_textoUsuario.setEditable(true);
		_textoPassword.setEditable(true);
		_botonAlta.setEnabled(false);
		_botonBaja.setEnabled(false);
		_botonModificar.setEnabled(true);
		_botonCancelar.setEnabled(true);
		_textoInfo.setText("Rellena todos los campos disponibles y pulsa modificar, para modificar los datos.");
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#consultarPersonal()
	 */
	@Override
	public void consultarPersonal() 
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
		_labelPersonal.setText("PERSONAL: Consultar Datos Personales");
		_textoIdentificador.setEditable(false);
		_textoNombre.setEditable(false);
		_textoDNI.setEditable(false);
		_textoApellidos.setEditable(false);
		_textoEdad.setEditable(false);
		_comboRango.setEnabled(false);
		_textoUsuario.setEditable(false);
		_textoPassword.setEditable(false);
		_botonAlta.setEnabled(false);
		_botonBaja.setEnabled(false);
		_botonModificar.setEnabled(false);
		_botonCancelar.setEnabled(false);
		_textoInfo.setText("Selecciona el empleado del que deseas consultar sus datos personales.");
	}
	
	/**
	 * Función utilizada para resetear los JTextField a blanco
	 */
	private void ponerEnBlanco()
	{
		_textoIdentificador.setText("");
		_textoNombre.setText("");
		_textoApellidos.setText("");
		_textoEdad.setText("");
		_textoDNI.setText("");
		_textoPassword.setText("");
		_textoUsuario.setText("");
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#actulizarLista(java.util.ArrayList)
	 */
	@Override
	public void actualizarLista(ArrayList<Object> lista) 
	{
		ponerEnBlanco();
		_listaPersonal.removeAll();
		_modeloPersonal.clear();
		_listaId.clear();
		
		for (int i = 0; i < lista.size(); i ++)
		{
			if (((Personal) lista.get(i)).getDisponible())
			{
				_modeloPersonal.addElement(((Personal) lista.get(i)).getNombre() + " " + ((Personal) lista.get(i)).getApellidos());
				_listaId.add(((Personal) lista.get(i)).getId());
			}
		}
		
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#actualizarDatos(java.lang.Object)
	 */
	@Override
	public void actualizarDatos(Object objeto)
	{
		Personal personal = (Personal) objeto;
		Integer id = new Integer(personal.getId()), edad = new Integer(personal.getEdad());
		
		_textoIdentificador.setText(id.toString());
		_textoNombre.setText(personal.getNombre());
		_textoApellidos.setText(personal.getApellidos());
		_textoEdad.setText(edad.toString());
		_textoDNI.setText(personal.getDni());
		_comboRango.setSelectedIndex(personal.getTipoDeEmpleado().ordinal());
		_textoUsuario.setText(personal.getUsuario());
		_textoPassword.setText(personal.getPassword());
	}

	//ESTAS FUNCIONES NO SE IMPLEMENTAN EN ESTA CLASE
	public void historicoVentas() {}

	public void nuevaVenta() {}

	public void buscarPedido() {}

	public void nuevoPedido() {}

	public void eliminarPedido() {}

	public void buscarProducto() {}

	public void nuevoProducto() {}

	public void modificarProducto() {}

	public void eliminarProducto() {}

	public void actulizarLista() {}

	public void actualizarListaSecundaria(ArrayList<Object> lista) {}

	public void actualizarMenu(Rango rango) {}

	public void devolverVenta() {}

	public void cambioSesion() {}

	public void actualizarListaGUI(ArrayList<Object> lista) {}

	public void pedidosTotal(double cantidad) {}

	public void ventasTotal(double cantidad) {}
}