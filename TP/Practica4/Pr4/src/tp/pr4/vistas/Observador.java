package tp.pr4.vistas;

import tp.pr4.modelo.Ficha;
import tp.pr4.modelo.MovimientoInvalido;
import tp.pr4.modelo.TableroInmutable;

/**
 * @author Sergio
 *
 * Interfaz Observador que contiene los métodos que se invocarán cuando 
 * la partida (que es el modelo de nuestra aplicación4) modifique su estado interno.
 */
public interface Observador {

	/**
	 * Método invocado por la clase Partida que permite notificar a sus observadores
	 * (las vistas) que se ha reiniciado la partida. Proporciona información del
	 * estado inicial del tablero y el turno (que será una ficha blanca o negra).
	 * 
	 * @param tablero - Tablero de la partida
	 * @param turno - Turno del jugador
	 */
	void onReset(TableroInmutable tablero, Ficha turno);
	
	/**
	 * La partida notifica a los observadores que ha terminado la partida llamando a
	 * este método. Además proporciona al observador una vista del tablero de sólo
     * lectura y el ganador.
     * 
	 * @param tablero - Tablero de la partida
	 * @param turno - Turno del jugador
	 */
	void onPartidaTerminada(TableroInmutable tablero, Ficha ganador);
	
	/**
	 * La partida notifica a los observadores que se ha cambiado el juego.
	 * Se proporciona el estado inicial del tablero y el turno5
	 * 
	 * @param tablero - Tablero de la partida
	 * @param turno - Turno del jugador
	 */
	void onCambioJuego(TableroInmutable tablero, Ficha turno);
	
	
	// Gesti�n de movimientos
	/**
	 * La partida notifica a los observadores que una operación 
	 * deshacer no ha tenido éxito porque no se puede deshacer.
	 * 
	 * @param tablero - Tablero de la partida
	 * @param turno - Turno del jugador
	 */
	void onUndoNotPossible(TableroInmutable tablero, Ficha turno);
	
	/**
	 * La partida notifica a los observadores que se ha deshecho un movimiento. Además,
     * proporciona el estado final del tablero, el turno del siguiente jugador y si
     * hay más movimientos a deshacer o no.
     * 
	 * @param tablero - Tablero de la partida
	 * @param turno - Turno del jugador
	 * @param hayMas
	 */
	void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas);
	
	/**
	 * La partida notifica a los observadores que se ha terminado de realizar un movimiento.
	 * Se proporciona además una vista del tablero de sólo lectura, el jugador
	 * que ha jugado, y el turno del siguiente jugador.
	 * 
	 * @param tablero - Tablero de la partida
	 * @param jugador - Jugador que ha jugado
	 * @param turno - Turno del jugador actual
	 */
	void onMovimientoEnd(TableroInmutable tablero, Ficha jugador, Ficha turno);
	
	/**
	 * La partida notifica que se ha producido un movimiento incorrecto proporcionando
	 * el objeto MovimientoInvalido con una explicación del problema que se
	 * ha producido.
	 * 
	 * @param movimientoException - Objeto que contiene excepción
	 */
	void onMovimientoIncorrecto(MovimientoInvalido movimientoException);
	
}
