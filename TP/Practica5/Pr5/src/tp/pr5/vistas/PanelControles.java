package tp.pr5.vistas;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;

import tp.pr5.controlador.ControladorGUI;
import tp.pr5.modelo.Ficha;
import tp.pr5.modelo.Juego;
import tp.pr5.modelo.TipoTurno;
import tp.pr5.modelo.MovimientoInvalido;
import tp.pr5.modelo.TableroInmutable;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;

/**
 * @author Sergio
 *
 * Clase JPanel que contiene los controles de la partida
 */
public class PanelControles extends JPanel implements Observador {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private JPanel _panelPartida; // Operaciones de partida
	private JPanel _panelJugadores;
	private JPanel _panelCambiar; // Operaciones para cambiar
	
	private JButton _botonDeshacer;
	private JButton _botonReiniciar;
	private JButton _botonCambiar;

	private JComboBox<TipoTurno> _comboJugBlancas;
	private JComboBox<TipoTurno> _comboJugNegras;
	private JComboBox<Juego> _comboCambiar;
	
	private JLabel _etiquetaBlancas;
	private JLabel _etiquetaNegras;
	private JLabel _etiquetaCol;
	private JLabel _etiquetaFila;

	private JTextField _cajaCol;
	private JTextField _cajaFila;
	
	private ControladorGUI _c;
	
	private Juego _juego;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param control - Objecto ControladorGUI para asociarlo a la clase 
	 * @param _juego 
	 */
	public PanelControles(ControladorGUI control, Juego juego) {
		
		_c = control;
		_c.addObservador(this);
		
		_juego = juego;
		
		initGUI();
		confEventos();
	}
	
	/**
	 * Método privado para iniciar los componentes 
	 */
	private void initGUI() {
		
		String url;
		
		setBorder(new BevelBorder(BevelBorder.RAISED, null, null, null, null));
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		
		// Operaciones de partida
		_panelPartida = new JPanel();
		_panelPartida.setBorder(BorderFactory.createTitledBorder("Partida"));
		
		_panelPartida.add(_botonDeshacer = new JButton("Deshacer"));
		url = "imagenes/deshacer.png";
		if(getClass().getResource(url) != null)
			_botonDeshacer.setIcon(new ImageIcon(getClass().getResource(url)));
		// Por defecto deshabilitamos el botón para deshacer movimientos
		_botonDeshacer.setEnabled(false);
		
		_panelPartida.add(_botonReiniciar = new JButton("Reiniciar"));
		url = "imagenes/reiniciar.png";
		if(getClass().getResource(url) != null)
			_botonReiniciar.setIcon(new ImageIcon(getClass().getResource(url)));
		
		add(_panelPartida);
		
		// Operaciones panel de jugadores
		_panelJugadores = new JPanel(new BorderLayout());
		_panelJugadores.setBorder(BorderFactory.createTitledBorder("Gestión de jugadores"));
		
		TipoTurno[] tiposJugador = new TipoTurno[2];
		tiposJugador[0] = TipoTurno.HUMANO;
		tiposJugador[1] = TipoTurno.AUTOMATICO;
		
		JPanel panelAuxBlancas = new JPanel();
		panelAuxBlancas.add(_etiquetaBlancas = new JLabel("Jugador de blancas"));
		_etiquetaBlancas.setVisible(true);
		panelAuxBlancas.add(_comboJugBlancas = new JComboBox<TipoTurno>(tiposJugador));
		_comboJugBlancas.setSelectedItem(TipoTurno.HUMANO);
		_panelJugadores.add(panelAuxBlancas, BorderLayout.NORTH);
		JPanel panelAuxNegras = new JPanel();
		panelAuxNegras.add(_etiquetaNegras = new JLabel("J. Negras"));
		_etiquetaNegras.setVisible(true);
		panelAuxNegras.add(_comboJugNegras = new JComboBox<TipoTurno>(tiposJugador));
		_comboJugNegras.setSelectedItem(TipoTurno.HUMANO);
		_panelJugadores.add(panelAuxNegras, BorderLayout.CENTER);
		
		add(_panelJugadores);
		
		// Operaciones para cambiar
		_panelCambiar = new JPanel();
		_panelCambiar.setLayout(new BoxLayout(_panelCambiar, BoxLayout.Y_AXIS));
		
		_panelCambiar.setBorder(BorderFactory.createTitledBorder("Cambiar de juego"));
		Juego[] tiposDeJuego = new Juego[4];
		tiposDeJuego[0] = Juego.CONECTA4;
		tiposDeJuego[1] = Juego.COMPLICA;
		tiposDeJuego[2] = Juego.GRAVITY;
		tiposDeJuego[3] = Juego.REVERSI;
		_comboCambiar = new JComboBox<Juego>(tiposDeJuego);
		JPanel panelCombo = new JPanel();
		panelCombo.add(_comboCambiar);
		// Seleccionamos CONECTA4 como opción por defecto
		_comboCambiar.setSelectedItem(_juego);
		_panelCambiar.add(panelCombo);
		
		JPanel panelAux = new JPanel();
		panelAux.add(_etiquetaCol = new JLabel("Columna"));
		_etiquetaCol.setVisible(false);
		panelAux.add(_cajaCol = new JTextField(3));
		_cajaCol.setVisible(false);
		panelAux.add(_etiquetaFila = new JLabel("Fila"));
		_etiquetaFila.setVisible(false);
		panelAux.add(_cajaFila = new JTextField(3));
		_cajaFila.setVisible(false);
		_panelCambiar.add(panelAux);
		_panelCambiar.add(_botonCambiar = new JButton("Cambiar"));
		url = "imagenes/cambiar.png";
		if(getClass().getResource(url) != null)
			_botonCambiar.setIcon(new ImageIcon(getClass().getResource(url)));
		add(_panelCambiar);
		
		add(new JPanel());
	}

	/**
	 * Configura los eventos asociados a esta clase
	 */
	private void confEventos() {
		
		_botonDeshacer.addActionListener(new java.awt.event.ActionListener() {
	        /* (non-Javadoc)
	         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	         */
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	botonDeshacerActionPerformed(evt);
	        }
	        
	        /**
	         * Método para deshacer
	         * 
	         * @param evt - Evento para el método deshacer
	         */
	        private void botonDeshacerActionPerformed(ActionEvent evt) {
	    		
	        	_c.undo();	    		
	    	}
	    });
		
		_botonReiniciar.addActionListener(new java.awt.event.ActionListener() {
	        /* (non-Javadoc)
	         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	         */
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	botonReiniciarActionPerformed(evt);
	        }

			/**
			 * Método para reiniciar la partida
			 * 
			 * @param evt - Evento para el método reiniciar
			 */
			private void botonReiniciarActionPerformed(ActionEvent evt) {
				
				//int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea reiniciar la partida?", "Reiniciar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				//if(respuesta == JOptionPane.YES_OPTION)
					_c.reiniciar();
			}
	    });
		
		_comboCambiar.addActionListener(new java.awt.event.ActionListener() {
	        /* (non-Javadoc)
	         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	         */
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	comboCambiarActionPerformed(evt);
	        }
	        
	        /**
	         * Método para cambiar el comboBox
	         * 
	         * @param evt - Evento para el método comboBox
	         */
	        private void comboCambiarActionPerformed(java.awt.event.ActionEvent evt) {
	            
	            Juego juego = (Juego) _comboCambiar.getModel().getSelectedItem();
	            
	            switch(juego)
	            {	            	
	            	case GRAVITY:
	            		
	            		_etiquetaCol.setVisible(true);
	            		_etiquetaFila.setVisible(true);
	            		_cajaCol.setVisible(true);
	            		_cajaFila.setVisible(true);
	            		
	            		break;
	            	
	            	default: 
	            		
	            		_etiquetaCol.setVisible(false);
	            		_etiquetaFila.setVisible(false);
	            		_cajaCol.setVisible(false);
	            		_cajaFila.setVisible(false);
	            		
	            		break;
	            }
	    	}
	    });
		
		_botonCambiar.addActionListener(new java.awt.event.ActionListener() {
	        /* (non-Javadoc)
	         * @see java.awt.event.ActionListener#actionPerformed(java.awt.event.ActionEvent)
	         */
	        public void actionPerformed(java.awt.event.ActionEvent evt) {
	        	botonCambiarActionPerformed(evt);
	        }
	        
	        /**
	         * Método para cambiar de juego
	         * 
	         * @param evt - Evento para el método cambiar de juego
	         */
	        private void botonCambiarActionPerformed(ActionEvent evt) {
	        	
	        	if(!(_comboCambiar.getSelectedItem().toString().equals(Juego.GRAVITY.toString())))
	    			_c.cambiarJuego(_comboCambiar.getSelectedItem().toString(), 0, 0);
	    		else {
	    			
	    			if(!(_cajaCol.getText().equals("")) && !(_cajaFila.getText().equals(""))) {
	    				
	    				try { // Si es un número
	    					
	    					Integer.parseInt(_cajaCol.getText().toString());
	    					Integer.parseInt(_cajaFila.getText().toString());
	    					
	    					_c.cambiarJuego(_comboCambiar.getSelectedItem().toString(), 
	    							Integer.valueOf(_cajaCol.getText().toString()), 
	    							Integer.valueOf(_cajaFila.getText().toString()));
	    					
	    				} catch (NumberFormatException e) {
	    					JOptionPane.showConfirmDialog(null, "Solo se puede dar tamaño con números. Excepción: " + e.getMessage(), 
	    							"Tamaño del tablero", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
	    				}
	    			}
	    			else JOptionPane.showConfirmDialog(null, "Para jugar a Gravity necesitas introducir el número de columnas y filas.", 
	    					"Tamaño del tablero", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
	    		}
	    	}
	    });
		
		_comboJugBlancas.addActionListener(new java.awt.event.ActionListener() {
		
			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboJugBlancasActionPerformed(evt);
			}

			private void comboJugBlancasActionPerformed(ActionEvent evt) {
				
				TipoTurno tipoTurno = (TipoTurno) _comboJugBlancas.getModel().getSelectedItem();
				
				_c.cambiarJugador(Ficha.BLANCA, tipoTurno);
			}
			
		});
		
		_comboJugNegras.addActionListener(new java.awt.event.ActionListener() {

			@Override
			public void actionPerformed(java.awt.event.ActionEvent evt) {
				comboJugNegrasActionPerformed(evt);
			}

			private void comboJugNegrasActionPerformed(ActionEvent evt) {
				
				TipoTurno tipoTurno = (TipoTurno) _comboJugNegras.getModel().getSelectedItem();
				
				_c.cambiarJugador(Ficha.NEGRA, tipoTurno);
			}
			
		});
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onReset(tp.pr5.modelo.TableroInmutable, tp.pr5.modelo.Ficha)
	 */
	@Override
	public void onReset(TableroInmutable tablero, Ficha turno) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				resetCajasTexto();
				
				_c.cambiarJugador(Ficha.BLANCA, TipoTurno.HUMANO);
				_c.cambiarJugador(Ficha.NEGRA, TipoTurno.HUMANO);

				_botonDeshacer.setEnabled(false);
				_comboJugBlancas.setEnabled(true);
				_comboJugNegras.setEnabled(true);
			}
		});
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onPartidaTerminada(tp.pr5.modelo.TableroInmutable, tp.pr5.modelo.Ficha)
	 */
	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {

		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				_comboJugBlancas.setEnabled(false);
				_comboJugNegras.setEnabled(false);
				_botonDeshacer.setEnabled(false);
			}
		});
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onCambioJuego(tp.pr5.modelo.TableroInmutable, tp.pr5.modelo.Ficha)
	 */
	@Override
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				resetCajasTexto();
				
				_botonDeshacer.setEnabled(false);
			}
		});
	}

	/**
	 * Vacía las cajas de texto de la vista
	 */
	private void resetCajasTexto() {

		_comboJugBlancas.setSelectedItem(TipoTurno.HUMANO);
		_comboJugNegras.setSelectedItem(TipoTurno.HUMANO);
		
		_cajaCol.setText("");
		_cajaFila.setText("");
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
	public void onUndo(TableroInmutable tablero, Ficha turno, final boolean hayMas) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				if(hayMas == true)
					_botonDeshacer.setEnabled(true);
				else _botonDeshacer.setEnabled(false);
			}
		});
	}

	/* (non-Javadoc)
	 * @see tp.pr5.vistas.Observador#onMovimientoEnd(tp.pr5.modelo.TableroInmutable, tp.pr5.modelo.Ficha, tp.pr5.modelo.Ficha)
	 */
	@Override
	public void onMovimientoEnd(TableroInmutable tablero, final Ficha jugador,
			final Ficha turno) {
		
		SwingUtilities.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				if(turno.getTipoTurno() == TipoTurno.HUMANO)
					_botonDeshacer.setEnabled(true);
				else _botonDeshacer.setEnabled(false);
			}
		});
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

	@Override
	public void onMovimientoStart(final Ficha turno) {
		
		// SI LO UTILIZO ENTOCNES AL PRINCIPIO DE LA PARTIDA EL BOTÓN DESHACER ESTARÁ HABILITADO
		/*SwingUtilities.invokeLater(new Runnable() {
			
			@Override
			public void run() {
				
				if(turno.getTipoTurno() == TipoTurno.AUTOMATICO)
					_botonDeshacer.setEnabled(false);
				else _botonDeshacer.setEnabled(true);
			}
		});*/
	}
}
