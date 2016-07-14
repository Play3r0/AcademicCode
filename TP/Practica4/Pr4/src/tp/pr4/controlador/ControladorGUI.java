package tp.pr4.controlador;

import tp.pr4.modelo.Juego;
import tp.pr4.modelo.Movimiento;
import tp.pr4.modelo.Partida;
import tp.pr4.vistas.Observador;

/**
 * @author Carlos
 *
 * Clase que controla la ejecución de la partida, 
 * esperando a que el usuario realice acciones.
 */
public class ControladorGUI {

	private FactoriaTipoJuego f;
	private Partida p;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param f - Factoría con la que se configura el tipo de juego
	 * @param p - Partida a la que se jugará, configurada con el juego al que hay que jugar en la invocación a run().
	 */
	public ControladorGUI(FactoriaTipoJuego f, Partida p) {
		
		this.f = f;		
		this.p = p;
		//this.jugador1 = f.creaJugadorAleatorio();
		//this.jugador2 = f.creaJugadorAleatorio();
	}
	
	/**
	 * Añade un Observador a la partida
	 * 
	 * @param o - Objeto de la clase Observador
	 */
	public void addObservador(Observador o) {
		p.addObserver(o);
	}

	/**
	 * Elimina un Observador de la Partida
	 * 
	 * @param o - Objeto observador
	 */
	public void removeObservador(Observador o) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Método privado para cambiar de jugador
	 * 
	 * @param juego - String con el modo de juego
	 * @param cols - Número de columnas
	 * @param filas - Número de filas
	 */
	public void cambiarJuego(String juego, int cols, int filas) {
		
		if(juego.equals(Juego.CONECTA4.toString()))
			reset(new FactoriaConecta4());
		else {
			if(juego.equals(Juego.COMPLICA.toString()))
				reset(new FactoriaComplica());
			else reset(new FactoriaGravity(cols, filas));
		}
		
		//p.crearPartida();
	}
	
	/**
	 * Resetea la partida
	 * 
	 * @param f - Objeto Factoria para reiniciar el juego
	 */
	public void reset(FactoriaTipoJuego f) {
		
		this.f = f;
		reiniciar();
	}

	/**
	 * Método para deshacer el último movimiento
	 */
	public void undo() {
		
		// Llamada para deshacer el último movimiento válido
		p.undo();	
	}
	
	/**
	 * Método privado para reiniciar la partida
	 */
	public void reiniciar() {
		
		// Generamos una nueva partida
		p.reset(f.creaReglas());
	}

	/**
	 * Método para poner una ficha
	 */
	public void poner(int col, int fila) {
		
		// Creamos un movimiento a partir de la columna, la fila y el turno
		Movimiento mov = f.creaMovimiento(col, fila, p.getTurno());
			
		// Ejecutamos el movimiento
		p.ejecutaMovimiento(mov);
			
		finalizar();
	}
	
	/**
	 * Método para colocar una ficha de forma aleatoria
	 */
	public void ponerAleatorio() {
		
		Movimiento mov = p.dameMov(f.creaJugadorAleatorio());
				
		// Ejecutamos el movimiento
		p.ejecutaMovimiento(mov);
			
		finalizar();
	}

	/**
	 * Método comprueba si la partida ha terminado
	 */
	public void finalizar() {
		
		// Comprobamos si la partida ha terminado
		p.isTerminada();
	}
	
	/**
	 * Método para iniciar la partida
	 */
	public void inicarPartida() {
		
		p.crearPartida();
	}
}
