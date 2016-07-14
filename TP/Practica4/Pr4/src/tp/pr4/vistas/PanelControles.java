package tp.pr4.vistas;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.BevelBorder;
import javax.swing.BoxLayout;

import tp.pr4.controlador.ControladorGUI;
import tp.pr4.modelo.Ficha;
import tp.pr4.modelo.Juego;
import tp.pr4.modelo.MovimientoInvalido;
import tp.pr4.modelo.TableroInmutable;

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
	private JPanel _panelCambiar; // Operaciones para cambiar
	
	private JButton _botonDeshacer;
	private JButton _botonReiniciar;
	private JButton _botonCambiar;
	
	private JComboBox<Juego> _comboCambiar;
	
	private JLabel _etiquetaCol;
	private JLabel _etiquetaFila;

	private JTextField _cajaCol;
	private JTextField _cajaFila;
	
	private ControladorGUI _c;
	
	/**
	 * Constructor de la clase
	 * 
	 * @param control - Objecto ControladorGUI para asociarlo a la clase 
	 */
	public PanelControles(ControladorGUI control) {
		
		this._c = control;
		this._c.addObservador(this);
		
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
		
		_panelPartida.add(_botonReiniciar = new JButton("Reiniciar"));
		url = "imagenes/reiniciar.png";
		if(getClass().getResource(url) != null)
			_botonReiniciar.setIcon(new ImageIcon(getClass().getResource(url)));
		
		add(_panelPartida);
		
		// Operaciones para cambiar
		_panelCambiar = new JPanel();
		_panelCambiar.setLayout(new BoxLayout(_panelCambiar, BoxLayout.Y_AXIS));
		
		_panelCambiar.setBorder(BorderFactory.createTitledBorder("Cambiar de juego"));
		Juego[] tiposDeJuego = new Juego[3];
		tiposDeJuego[0] = Juego.CONECTA4;
		tiposDeJuego[1] = Juego.COMPLICA;
		tiposDeJuego[2] = Juego.GRAVITY;
		_comboCambiar = new JComboBox<Juego>(tiposDeJuego);
		JPanel panelCombo = new JPanel();
		panelCombo.add(_comboCambiar);
		// Seleccionamos CONECTA4 como opción por defecto
		_comboCambiar.setSelectedItem(Juego.CONECTA4);
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
				
				int respuesta = JOptionPane.showConfirmDialog(null, "¿Desea reiniciar la partida?", "Reiniciar", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
				
				if(respuesta == JOptionPane.YES_OPTION)
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
	            	case CONECTA4:
	            		
	            		_etiquetaCol.setVisible(false);
	            		_etiquetaFila.setVisible(false);
	            		_cajaCol.setVisible(false);
	            		_cajaFila.setVisible(false);
	            		
	            		break;
	            		
	            	case COMPLICA:
	            		
	            		_etiquetaCol.setVisible(false);
	            		_etiquetaFila.setVisible(false);
	            		_cajaCol.setVisible(false);
	            		_cajaFila.setVisible(false);
	            		
	            		break;
	            	
	            	case GRAVITY:
	            		
	            		_etiquetaCol.setVisible(true);
	            		_etiquetaFila.setVisible(true);
	            		_cajaCol.setVisible(true);
	            		_cajaFila.setVisible(true);
	            		
	            		break;
	            	
	            	default: break;
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
	    			
	    			if(!(_cajaCol.getText().equals("")) && !(_cajaFila.getText().equals("")))
	    				_c.cambiarJuego(_comboCambiar.getSelectedItem().toString(), Integer.valueOf(_cajaCol.getText().toString()), Integer.valueOf(_cajaFila.getText().toString()));
	    			else JOptionPane.showConfirmDialog(null, "Para jugar a Gravity necesitas introducir el número de columnas y filas.", "Tamaño del tablero", JOptionPane.CLOSED_OPTION, JOptionPane.ERROR_MESSAGE);
	    		}
	    	}
	    });
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onReset(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha)
	 */
	@Override
	public void onReset(TableroInmutable tablero, Ficha turno) {
		
		vaciarCajasTexto();
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
	public void onCambioJuego(TableroInmutable tab, Ficha turno) {
		
		vaciarCajasTexto();
	}

	/**
	 * Vacía las cajas de texto de la vista
	 */
	private void vaciarCajasTexto() {
		
		_cajaCol.setText("");
		_cajaFila.setText("");
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
		// TODO Auto-generated method stub
		
	}
}
