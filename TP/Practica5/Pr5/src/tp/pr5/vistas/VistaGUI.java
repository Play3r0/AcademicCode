package tp.pr5.vistas;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;
import javax.swing.border.EmptyBorder;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;

import tp.pr5.controlador.ControladorGUI;
import tp.pr5.modelo.Ficha;
import tp.pr5.modelo.Juego;
import tp.pr5.modelo.MovimientoInvalido;
import tp.pr5.modelo.TableroInmutable;
import tp.pr5.modelo.TipoTurno;

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
	
	private JLabel _etiquetaInfo;
	
	private ControladorGUI _c;
	
	private Juego _juego;

	/**
	 * Constructor de la case
	 * 
	 * @param control - Objeto ControladorGUI
	 */
	public VistaGUI(ControladorGUI control, Juego juego) {
		
		super("Práctica 5 - TP");
		_c = control;
		_c.addObservador(this);
		_juego = juego;
		
		initGUI();    //a�ado todos los componentes
		confEventos();
		
		//_c.inicarPartida();
		_c.reiniciar();
	}
	
	/**
	 * Método privado para iniciar los componentes 
	 */
	private void initGUI() {
		
		setBounds(100, 100, 600, 450);
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
		
		_panelJuego = new PanelJuego(_c, 1, 1, _juego);
		_panelPrincipal.add(_panelJuego, BorderLayout.CENTER);
	}
	
	/**
	 * Método privado que genera el panel derecha
	 */
	private void panel_derecha() {
		
		_panelControles = new PanelControles(_c, _juego);
		_panelPrincipal.add(_panelControles, BorderLayout.EAST);
	}
	
	/**
	 * Método privado que genera el panel inferior
	 */
	private void panel_inferior() {
		
		String url;

		JPanel panelControlesAux = new 		JPanel();
		panelControlesAux.setLayout(new GridLayout(1, 3));
		JPanel panelAux = new JPanel();
		
		panelAux.add(_botonAleatorio = new JButton("Poner Aleatorio"));
		url = "imagenes/aleatorio.png";
		if(getClass().getResource(url) != null)
			_botonAleatorio.setIcon(new ImageIcon(getClass().getResource(url)));
		panelControlesAux.add(panelAux);
		
		JPanel panelAux1 = new JPanel();
		
		panelAux1.add(_etiquetaInfo = new JLabel(""));
		_etiquetaInfo.setForeground(Color.RED);
		panelControlesAux.add(panelAux1);
		
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
	 * @see tp.pr5.vistas.Observador#onReset(tp.pr5.modelo.TableroInmutable, tp.pr5.modelo.Ficha)
	 */
	public void onReset(TableroInmutable tablero, Ficha turno) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_botonAleatorio.setEnabled(true);
				_etiquetaInfo.setText("");
			}
		});
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onPartiaTerminada(tp.pr5.modelo.TableroInmutable, tp.pr5.modelo.Ficha)
	 */
	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_botonAleatorio.setEnabled(false);
			}
		});
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onCambioJuego(tp.pr5.modelo.TableroInmutable, tp.pr5.modelo.Ficha)
	 */
	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_etiquetaInfo.setText("");
			}
		});
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onUndoNotPossible(tp.pr5.modelo.TableroInmutable, tp.pr5.modelo.Ficha)
	 */
	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
			}
		});
		//JOptionPane.showConfirmDialog(null, "Imposible deshacer.", "Deshacer último movimiento", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onUndo(tp.pr5.modelo.TableroInmutable, tp.pr5.modelo.Ficha, boolean)
	 */
	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_etiquetaInfo.setText("");
			}
		});
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onMovimientoEnd(tp.pr5.modelo.TableroInmutable, tp.pr5.modelo.Ficha, tp.pr5.modelo.Ficha)
	 */
	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_etiquetaInfo.setText("");
			}
		});
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onMovimientoIncorrecto(tp.pr5.modelo.MovimientoInvalido)
	 */
	@Override
	public void onMovimientoIncorrecto(final MovimientoInvalido movimientoException) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				_etiquetaInfo.setText("MOVIMIENTO INVÁLIDO");
			}
		});	
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onMovimientoStart(tp.pr5.modelo.Ficha)
	 */
	@Override
	public void onMovimientoStart(final Ficha turno) {
		
		SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				if(turno.getTipoTurno() == TipoTurno.AUTOMATICO)
					_botonAleatorio.setEnabled(false);
				else _botonAleatorio.setEnabled(true);
			}
		});
	}
}
