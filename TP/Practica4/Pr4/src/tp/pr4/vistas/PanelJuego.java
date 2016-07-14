package tp.pr4.vistas;

import java.awt.Color;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.border.BevelBorder;

import java.awt.GridLayout;

import javax.swing.BoxLayout;

import tp.pr4.controlador.ControladorGUI;
import tp.pr4.modelo.Ficha;
import tp.pr4.modelo.MovimientoInvalido;
import tp.pr4.modelo.TableroInmutable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
	
	// Atributo privado para controlar las acciones sobre la matriz de botones
	private ActionListener _tableroActionListener;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param control - Objeto de la clase ControladorGUI
	 * @param cols - Número de columnas
	 * @param filas - Número de filas
	 */
	public PanelJuego(ControladorGUI control, int cols, int filas) {
		
		this._c = control;
		this._c.addObservador(this);
		
		this._cols = cols;
		this._filas = filas;
		
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
	 * @see tp.pr4.vistas.Observador#onReset(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha)
	 */
	@Override
	public void onReset(TableroInmutable tablero, Ficha turno) {
		
		reiniciarTablero(tablero, turno);
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onPartidaTerminada(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha)
	 */
	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onCambioJuego(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha)
	 */
	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno) {
		
		_cols = tablero.getAncho();
		_filas = tablero.getAlto();
		
		reiniciarTablero(tablero, turno);
	}

	/**
	 * Método privado para reiniciar el tablero
	 * 
	 * @param tablero - Tablero de juego
	 * @param turno - Turno del jugador actual
	 */
	private void reiniciarTablero(TableroInmutable tablero, Ficha turno) {
		
		_cols = tablero.getAncho();
		_filas = tablero.getAlto();
		
		_panelJuego.remove(_panelTablero);
		_panelJuego.remove(_panelTurno);
		panelTablero();
		_panelJuego.add(_panelTurno);
		confEventos();
		
		pintarTablero(tablero);
		
		_etiquetaTurno.setText("JUEGA " + turno.toString());
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onUndoNotPossible(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha)
	 */
	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {
		// TODO Auto-generated method stub
		
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onUndo(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha, boolean)
	 */
	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		
		pintarTablero(tablero);
		
		_etiquetaTurno.setText("JUEGA " + turno.toString());
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onMovimientoEnd(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha, tp.pr4.modelo.Ficha)
	 */
	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador,
			Ficha turno) {
		
		/*for(int f = 1; f <= _filas; f++) {
			
			for(int c = 1; c <= _cols; c++) {
				System.out.print(tablero.getCasilla(c, f).toString() + " ");
			}
			System.out.println();
		}*/
		
		pintarTablero(tablero);
		
		_etiquetaTurno.setText("JUEGA " + turno.toString());
		
	}

	/**
	 * Método privado para pintar el tablero de juego
	 * 
	 * @param tablero - Tablero de juego
	 */
	private void pintarTablero(TableroInmutable tablero) {
		
		for(int f = 1; f <= _filas; f++) {
			
			for(int c = 1; c <= _cols; c++) {
				
				if(tablero.getCasilla(c, f) == Ficha.BLANCA)
					_tabBotones[c][f].setBackground(Color.white);
				else {
					if(tablero.getCasilla(c, f) == Ficha.NEGRA)
						_tabBotones[c][f].setBackground(Color.black);
					else _tabBotones[c][f].setBackground(Color.green);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onMovimientoIncorrecto(tp.pr4.modelo.MovimientoInvalido)
	 */
	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		
		
	}
}
