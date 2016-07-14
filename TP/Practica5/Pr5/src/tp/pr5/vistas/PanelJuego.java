package tp.pr5.vistas;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import tp.pr5.controlador.ControladorGUI;
import tp.pr5.modelo.Ficha;
import tp.pr5.modelo.Juego;
import tp.pr5.modelo.MovimientoInvalido;
import tp.pr5.modelo.TableroInmutable;
import tp.pr5.modelo.TipoTurno;
import tp.pr5.utilidades.Comprobar;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

/**
 * @author Sergio
 *
 * Clase que contiene el panel de juego con el tablero
 */
public class PanelJuego extends JPanel implements Observador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel _panelJuego; // Panel de juego
	private JPanel _panelTablero; // Panel del tablero
	private JPanel _panelTurno; // Panel del turno
	
	private JButton [][]_tabBotones; // Matriz de botones
	
	private JLabel _etiquetaTurno; // Etiqueta para el turno
	
	private ControladorGUI _c;
	
	private int _cols;
	private int _filas;
	
	private Juego _juego;
	
	// Atributo privado para controlar las acciones sobre la matriz de botones
	private ActionListener _tableroActionListener;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param control - Objeto de la clase ControladorGUI
	 * @param cols - Número de columnas
	 * @param filas - Número de filas
	 * @param _juego 
	 */
	public PanelJuego(ControladorGUI control, int cols, int filas, Juego juego) {
		
		_c = control;
		_c.addObservador(this);
		
		_cols = cols;
		_filas = filas;
		
		_juego = juego;
		
		initGUI();
		confEventos();
	}

	/**
	 * Método privado para iniciar los componentes 
	 */
	private void initGUI() {

		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setLayout(new BorderLayout());
		
		_panelJuego = new JPanel();
		_panelJuego.setLayout(new BoxLayout(_panelJuego, 3));
		_panelJuego.setBorder(BorderFactory.createLineBorder(Color.black));
		add(_panelJuego);
		
		panelTablero();
		panelTurno();
	}

	/**
	 * Configura los eventos asociados a esta clase
	 */
	private void confEventos() {
		
		_tableroActionListener = new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				for(int f = 1; f <= _filas; f++) {
					for(int c = 1; c <= _cols; c++) {
						
						if(_tabBotones[c][f] == e.getSource()) {
							_c.poner(c, f);
							
							//JOptionPane.showMessageDialog(null, c + " " + f);
						}
					}
				}
			}
		};
		
		// Añadimos el evento a todos los botones del tablero de juego
		for(int f = 1; f <= _filas; f++)
			for(int c = 1; c <= _cols; c++)
				_tabBotones[c][f].addActionListener(_tableroActionListener);
	}
	
	/**
	 * Método privado que genera el panel del tablero
	 */
	private void panelTablero() {
		
		_panelTablero = new JPanel();
		_panelTablero.setLayout(new GridLayout(0, _cols)); // filas x columnas
		_panelJuego.add(_panelTablero);
		
		// Generamos la matriz de botones
		_tabBotones = new JButton[_cols + 1][_filas + 1];
		for(int f = 1; f <= _filas; f++) {
			
			for(int c = 1; c <= _cols; c++) {
			
				_tabBotones[c][f] = new JButton("");
				_tabBotones[c][f].setBackground(Color.green);
				_panelTablero.add(_tabBotones[c][f]);
			}
		}
	}
	
	/**
//	 * Método privado que genera el panel del turno
	 */
	private void panelTurno() {
		
		_panelTurno = new JPanel();
		_panelTurno.setBackground(Color.white);
		_panelTurno.setLayout(new BoxLayout(_panelTurno, 0));
		_etiquetaTurno = new JLabel("TURNO DE JUGADOR");
		_panelTurno.add(_etiquetaTurno, BorderLayout.CENTER);
		_panelJuego.add(_panelTurno);
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onReset(tp.pr5.modelo.TableroInmutable, tp.pr5.modelo.Ficha)
	 */
	@Override
	public void onReset(final TableroInmutable tablero, final Ficha turno) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				reiniciarTablero(tablero, turno);
			}
		});
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onPartidaTerminada(tp.pr5.modelo.TableroInmutable, tp.pr5.modelo.Ficha)
	 */
	@Override
	public void onPartidaTerminada(TableroInmutable tablero, final Ficha ganador) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				String mensaje;
				
				if(ganador == Ficha.BLANCA) {
					mensaje = "GANAN LAS BLANCAS";
					_etiquetaTurno.setText(mensaje);
				}
				else {
					if(ganador == Ficha.NEGRA) {
						mensaje = "GANAN LAS NEGRAS";
						_etiquetaTurno.setText(mensaje);
					}
					else {
						mensaje = "PARTIDA TERMINADA EN TABLAS";
						_etiquetaTurno.setText(mensaje);
					}
				}
				
				for(int f = 1; f <= _filas; f++) {
					for(int c = 1; c <= _cols; c++)
						_tabBotones[c][f].setEnabled(false);
				}
			}
		});
			
		/*int respuesta = JOptionPane.showConfirmDialog(null, mensaje + " " + "¿Quieres reiniciar la partida?",  "Reiniciar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
			
		if(respuesta == JOptionPane.YES_OPTION)
			_c.reiniciar();
		else System.exit(0);
		*/
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onCambioJuego(tp.pr5.modelo.TableroInmutable, tp.pr5.modelo.Ficha)
	 */
	@Override
	public void onCambioJuego(final TableroInmutable tablero, final Ficha turno) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				_cols = tablero.getAncho();
				_filas = tablero.getAlto();
				
				reiniciarTablero(tablero, turno);
			}
		});
	}

	/**
	 * Método privado para reiniciar el tablero
	 * 
	 * @param tablero - Tablero de juego
	 * @param turno - Turno del jugador actual
	 */
	private void reiniciarTablero(final TableroInmutable tablero, final Ficha turno) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {

				_cols = tablero.getAncho();
				_filas = tablero.getAlto();
				
				_panelJuego.remove(_panelTablero);
				_panelJuego.remove(_panelTurno);
				panelTablero();
				_panelJuego.add(_panelTablero);
				_panelJuego.add(_panelTurno);
				confEventos();
				
				pintarTablero(tablero, turno);
				
				revalidate();
				
				_etiquetaTurno.setText("JUEGAN " + turno.getDescripcion());
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
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onUndo(tp.pr5.modelo.TableroInmutable, tp.pr5.modelo.Ficha, boolean)
	 */
	@Override
	public void onUndo(final TableroInmutable tablero, final Ficha turno, boolean hayMas) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				pintarTablero(tablero, turno);
				
				_etiquetaTurno.setText("JUEGAN " + turno.getDescripcion());
			}
		});
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onMovimientoEnd(tp.pr5.modelo.TableroInmutable, tp.pr5.modelo.Ficha, tp.pr5.modelo.Ficha)
	 */
	@Override
	public void onMovimientoEnd(final TableroInmutable tablero, Ficha jugador,
			final Ficha turno) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				pintarTablero(tablero, turno);
				
				_etiquetaTurno.setText("JUEGAN " + turno.getDescripcion());
			}
		});
	}

	/**
	 * Método privado para pintar el tablero de juego
	 * 
	 * @param tablero - Tablero de juego
	 * @param turno - Turno del jugador actual
	 */
	private void pintarTablero(TableroInmutable tablero, Ficha turno) {
		
		for(int f = 1; f <= _filas; f++) {
			
			for(int c = 1; c <= _cols; c++) {
				
				if(tablero.getCasilla(c, f) == Ficha.BLANCA)
					_tabBotones[c][f].setBackground(Color.white);
				else {
					if(tablero.getCasilla(c, f) == Ficha.NEGRA)
						_tabBotones[c][f].setBackground(Color.black);
					else _tabBotones[c][f].setBackground(Color.green);
				}
				
				_tabBotones[c][f].setEnabled(true);
			}
		}
		
		if(_juego == Juego.REVERSI) {
			
			ArrayList<ArrayList<Integer>> listaMovs = Comprobar.reversiMovsValidos(tablero, turno);
			
			for(int i = 0; i < listaMovs.size(); i++)
				_tabBotones[listaMovs.get(i).get(0)][listaMovs.get(i).get(1)].setBackground(Color.yellow);
		}
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onMovimientoIncorrecto(tp.pr5.modelo.MovimientoInvalido)
	 */
	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
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
				
				if(turno.getTipoTurno() == TipoTurno.AUTOMATICO) {
					
					for(int f = 1; f <= _filas; f++) {
						for(int c = 1; c <= _cols; c++)
							_tabBotones[c][f].setEnabled(false);
					}
				}
				else {
					
					for(int f = 1; f <= _filas; f++) {
						for(int c = 1; c <= _cols; c++)
							_tabBotones[c][f].setEnabled(true);
					}
				}
			}
		});
	}
}
