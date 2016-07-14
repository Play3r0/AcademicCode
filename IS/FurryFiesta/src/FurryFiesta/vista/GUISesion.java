package FurryFiesta.vista;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import javax.swing.border.TitledBorder;

import FurryFiesta.controlador.ControladorSesion;
import FurryFiesta.modelo.personal.Personal;
import FurryFiesta.modelo.personal.Rango;

/**
 * @author Alejandro Huertas Herrero
 *
 */
public class GUISesion extends JPanel implements Observador
{
	/**
	 * Constante para quitar el warning
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * Etiquetas utilizadas en el panel para mostrar toda la información
	 * relacionada con la sesión
	 */
	private JLabel _labelInformacion, _labelUsuario, _labelPassword;
	
	/**
	 * Campos de texto utilizados para obtener los datos
	 * necesarios para iniciar la sesión
	 */
	private JTextField _textoUsuario;
	
	/**
	 * Texto para introducir la contraseña
	 */
	private JPasswordField _textoPassword;
	
	/**
	 * Botones que permiten realizar las diferentes
	 * acciones relaciondas con el inicio de sesión
	 */
	private JButton _botonConectar, _botonSalir, _botonCliente;
	
	/**
	 * Diferentes paneles a los que se añaden los componentes
	 * de la ventana para luego formar un único panel de la ventana
	 * de la sesión que será cargado en el frame principal
	 */
	private JPanel _panelTitulo, _panelCampos, _panelBotones, _panelInterno, _panelUsuario, _panelPassword, _panelCambioSesion;
	
	/**
	 * Controlador que servirá para comunicarse con el modelo de sesión
	 */
	private ControladorSesion _controlador;
	
	/**
	 * @param control controlador utilizado por la vista para comunicarse con el modelo
	 */
	public GUISesion(ControladorSesion control)
	{
		initPanelTitulo();
		initPanelCampos();
		initEventos();
		initPanelInterno();
		
		add(_panelInterno);
		
		_controlador = control;
		_controlador.registerObserver(this);
	}
	
	/**
	 * Función que inicializa el panel que contiene el título del panel
	 */
	private void initPanelTitulo()
	{
		_panelTitulo = new JPanel();
		
		URL logo = VistaGUI.class.getResource("imagenes/logo.png");
		ImageIcon icon = (logo != null) ? new ImageIcon (logo) : null;
		
		_labelInformacion = new JLabel(icon);
		
		TitledBorder titulo = BorderFactory.createTitledBorder("");
		titulo.setTitleColor(Color.BLUE);
		_labelInformacion.setBorder(titulo);
		_labelInformacion.setHorizontalAlignment(SwingConstants.CENTER);
		
		_panelTitulo.add(_labelInformacion);
	}
	
	/**
	 * Función que inicializa el panel que contiene los campos a rellenar
	 */
	private void initPanelCampos()
	{
		_panelCampos = new JPanel(new BorderLayout());
		_panelUsuario = new JPanel(new FlowLayout());
		_panelPassword = new JPanel(new FlowLayout());
		
		_labelUsuario = new JLabel("Usuario: ");
		_textoUsuario = new JTextField();
		_textoUsuario.setPreferredSize(new Dimension(200, 20));
		_panelUsuario.add(_labelUsuario);
		_panelUsuario.add(_textoUsuario);
		
		_labelPassword = new JLabel("Contraseña: ");
		_textoPassword = new JPasswordField();
		_textoPassword.setPreferredSize(new Dimension(178, 20));
		_panelPassword.add(_labelPassword);
		_panelPassword.add(_textoPassword);
		
		_panelBotones = new JPanel();
		_panelBotones.setLayout(new FlowLayout());
		
		_botonConectar = new JButton("Conectar");
		_botonConectar.setPreferredSize(new Dimension(100, 30));
		_botonConectar.setBackground(Color.GREEN);
		_botonCliente = new JButton("Cliente");
		_botonCliente.setPreferredSize(new Dimension(100, 30));
		_botonCliente.setBackground(Color.GREEN);
		_botonSalir = new JButton("Salir");
		_botonSalir.setPreferredSize(new Dimension(100, 30));
		_botonSalir.setBackground(Color.RED);
		
		_panelBotones.add(_botonConectar);
		_panelBotones.add(_botonCliente);
		_panelBotones.add(_botonSalir);
		
		_panelCampos.add(_panelUsuario, BorderLayout.NORTH);
		_panelCampos.add(_panelPassword, BorderLayout.CENTER);
		_panelCampos.add(_panelBotones, BorderLayout.SOUTH);
	}
	
	/**
	 * Función que inicializa el panel interno que contiene todos los demás paneles
	 * y forma el panel sesión que se cargará en el frame principal
	 */
	private void initPanelInterno()
	{
		_panelInterno = new JPanel(new BorderLayout());
		
		_panelInterno.add(_panelTitulo, BorderLayout.NORTH);
		_panelInterno.add(_panelCampos, BorderLayout.CENTER);
		_panelInterno.setBorder(BorderFactory.createSoftBevelBorder(BevelBorder.LOWERED));
	}
	
	/**
	 *  Función que inicializa todos los listenners de cada botón
	 */
	private void initEventos()
	{
		//Botón conectar
		_botonConectar.addActionListener(new ActionListener() 
		{
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) 
			{
				String user, pass;
				
				user = _textoUsuario.getText();
				pass = _textoPassword.getText();
				
				Personal personal = new Personal(null, null, 0, null, null, user, pass);
				
				try 
				{
					_controlador.executeIniciarSesion(personal);
					
					_textoUsuario.setText("");
					_textoPassword.setText("");
				}
				catch (Exception e1) 
				{
					JOptionPane.showConfirmDialog(null, "Error al iniciar sesión password o user incorrectos: " + e1.getMessage(), "Error",
							JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
					
					_textoUsuario.setText("");
					_textoPassword.setText("");
				}
			}
		});
		
		//Botón salir
		_botonSalir.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea salir de la aplicación?", "Salir", 
						JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if (respuesta == JOptionPane.YES_OPTION)
				{
					try 
					{
						_controlador.executeDesconectar();
						System.exit(0);
					} 
					catch (Exception e1){}
				}
			}
		});
		
		//Boton cliente
		_botonCliente.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				_controlador.executeCliente();
			}
		});
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.vista.Observador#cambioSesion()
	 */
	@SuppressWarnings("deprecation")
	@Override
	public void cambioSesion() 
	{
		_panelCambioSesion = new JPanel(new BorderLayout());
		_panelCambioSesion.add(_panelUsuario, BorderLayout.NORTH);
		_panelCambioSesion.add(_panelPassword, BorderLayout.CENTER);
		
		int opcion = JOptionPane.showConfirmDialog(null, _panelCambioSesion, "Introduce tus datos", JOptionPane.OK_CANCEL_OPTION);
		
		if (opcion == JOptionPane.OK_OPTION)
		{
			String user, pass;
			
			user = _textoUsuario.getText();
			pass = _textoPassword.getText();
			
			Personal personal = new Personal(null, null, 0, null, null, user, pass);
			
			try 
			{
				_controlador.executeCambiarSesion(personal);;
				
				JOptionPane.showConfirmDialog(null, "Sesión cambiada con éxito como: " + personal.getUsuario(), "Ëxito",
						JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE);
				
				_textoUsuario.setText("");
				_textoPassword.setText("");
			}
			catch (Exception e1) 
			{
				JOptionPane.showConfirmDialog(null, "Error al cambiar de sesión password o user incorrectos: " + e1.getMessage(), "Error",
						JOptionPane.DEFAULT_OPTION, JOptionPane.ERROR_MESSAGE);
				
				_textoUsuario.setText("");
				_textoPassword.setText("");
			}
		}
		
		_panelCampos.add(_panelUsuario, BorderLayout.NORTH);
		_panelCampos.add(_panelPassword, BorderLayout.CENTER);
	}

	//FUNCIONES QUE NO NECESITAN IMPLEMENTACIÓN EN ESTA CLASE
	public void historicoVentas() {}

	public void nuevaVenta() {}

	public void eliminarVenta() {}

	public void buscarProducto() {}

	public void nuevoProducto() {}

	public void modificarProducto() {}

	public void eliminarProducto() {}

	public void buscarPedido() {}

	public void nuevoPedido() {}

	public void eliminarPedido() {}

	public void altaPersonal() {}

	public void bajaPersonal() {}

	public void modificarPersonal() {}

	public void actualizarLista(ArrayList<Object> lista) {}

	public void actualizarDatos(Object objeto) {}
	
	public void actualizarListaSecundaria(ArrayList<Object> lista) {}

	public void consultarPersonal() {}

	public void actualizarMenu(Rango rango) {}

	public void devolverVenta() {}

	public void actualizarListaGUI(ArrayList<Object> lista){}

	public void pedidosTotal(double cantidad) {}

	public void ventasTotal(double cantidad) {}
}