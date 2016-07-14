package tp.pr4.vistas;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import tp.pr4.controlador.ControladorGUI;
import tp.pr4.modelo.Ficha;
import tp.pr4.modelo.MovimientoInvalido;
import tp.pr4.modelo.TableroInmutable;

/**
 * @author Sergio
 *
 * Clase que contiene la Vista general de la interfaz de juego
 */
public class VistaGUI extends JFrame implements Observador{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JPanel _panelPrincipal;
	
	private PanelJuego _panelJuego;
	private PanelControles _panelControles;
	
	private JButton _botonAleatorio;
	private JButton _botonSalir;
	
	private ControladorGUI _c;

	/**
	 * Constructor de la case
	 * 
	 * @param control - Objeto ControladorGUI
	 */
	public VistaGUI(ControladorGUI control) {
		
		super("Pr�ctica 4 - TP");
		this._c = control;
		this._c.addObservador(this);
		
		initGUI();    //a�ado todos los componentes
		confEventos();
		
		_c.inicarPartida();
		//_c.reiniciar();
	}
	
	/**
	 * Método privado para iniciar los componentes 
	 */
	private void initGUI() {
		
		setBounds(100, 100, 600, 400);
		_panelPrincipal = new JPanel();
		_panelPrincipal.setBorder(new EmptyBorder(5, 5, 5, 5));
		_panelPrincipal.setLayout(new BorderLayout());
		setContentPane(_panelPrincipal);
		
		panel_izquierdo(); 
		panel_derecha();
		panel_inferior();
		
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setVisible(true);
	}
	
	/**
	 * Configura los eventos asociados a esta clase
	 */
	void confEventos() {
				
		_botonAleatorio.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	botonAleatorioActionPerformed(evt);
	        }

			private void botonAleatorioActionPerformed(ActionEvent evt) {
				
				_c.ponerAleatorio();
			}			
	    });	
		
		_botonSalir.addActionListener(new java.awt.event.ActionListener() {
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	botonSalirActionPerformed(evt);
	        }

			private void botonSalirActionPerformed(ActionEvent evt) {
				
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Deseas salir de la aplicación?", "Salir", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
        		
        		if(respuesta == JOptionPane.YES_OPTION)
        			System.exit(0);	
			}
	    });	
	}
	
	/**
	 * Método privado que genera el panel izquierdo
	 */
	private void panel_izquierdo() {
		
		_panelJuego = new PanelJuego(_c, 1, 1);
		_panelPrincipal.add(_panelJuego, BorderLayout.CENTER);
	}
	
	/**
	 * Método privado que genera el panel derecha
	 */
	private void panel_derecha() {
		
		_panelControles = new PanelControles(this._c);
		_panelPrincipal.add(_panelControles, BorderLayout.EAST);
	}
	
	/**
	 * Método privado que genera el panel inferior
	 */
	private void panel_inferior() {
		
		String url;

		JPanel panelControlesAux = new 		JPanel();
		panelControlesAux.setLayout(new GridLayout(1, 2));
		JPanel panelAux = new JPanel();
		
		panelAux.add(_botonAleatorio = new JButton("Poner Aleatorio"));
		url = "imagenes/aleatorio.png";
		if(getClass().getResource(url) != null)
			_botonAleatorio.setIcon(new ImageIcon(getClass().getResource(url)));
		panelControlesAux.add(panelAux);
		
		// Operaciones para salir
		JPanel panelAux2 = new JPanel();
		
		panelAux2.add(_botonSalir = new JButton("Salir"));
		url = "imagenes/salir.png";
		if(getClass().getResource(url) != null)
			_botonSalir.setIcon(new ImageIcon(getClass().getResource(url)));
		panelControlesAux.add(panelAux2);
		_panelPrincipal.add(panelControlesAux, BorderLayout.SOUTH);
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onReset(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha)
	 */
	public void onReset(TableroInmutable tablero, Ficha turno) {
		
		
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onPartidaTerminada(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha)
	 */
	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		
		String mensaje;
		
		if(ganador == Ficha.BLANCA)
			mensaje = "Ganan las blancas.";
		else {
			if(ganador == Ficha.NEGRA)
				mensaje = "Ganan las negras.";
			else mensaje = "Partida terminada en tablas.";
		}
			
		int respuesta = JOptionPane.showConfirmDialog(null, mensaje + " " + "¿Quieres reiniciar la partida?",  "Reiniciar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			
		if(respuesta == JOptionPane.YES_OPTION)
			_c.reiniciar();
		else System.exit(0);
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onCambioJuego(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha)
	 */
	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onUndoNotPossible(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha)
	 */
	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		
		JOptionPane.showConfirmDialog(null, "Imposible deshacer.", "Deshacer último movimiento", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onUndo(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha, boolean)
	 */
	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onMovimientoEnd(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha, tp.pr4.modelo.Ficha)
	 */
	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onMovimientoIncorrecto(tp.pr4.modelo.MovimientoInvalido)
	 */
	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		
		JOptionPane.showConfirmDialog(null, movimientoException.getMessage(), "Movimiento invalido", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
	}
}
