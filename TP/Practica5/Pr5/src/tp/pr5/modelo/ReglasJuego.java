
package tp.pr5.modelo;

/**
 * @author Sergio
 * 
 * Interfaz que representa las reglas de un juego concreto. 
 * La partida delega en un objeto de este interfaz para 
 * hacer avanzar la partida.
 */
public interface ReglasJuego
{
	/**
	 * Construye el tablero que hay que utilizar para la partida, 
	 * según las reglas del juego.
	 * 
	 * @return Tablero a utilizar. El estado del tablero será el de inicio de la partida.
	 */
	Tablero iniciaTablero();
	
	/**
	 * Devuelve el _color del jugador que comienza la partida.
	 * 
	 * @return Color del primer jugador en colocar ficha.
	 */
	Ficha jugadorInicial();
	
	/**
	 * Devuelve el _color del jugador al que le toca poner.
	 * 
	 * @param ultimoEnPoner - Último jugador en poner ficha
	 * 
	 * @param t - Estado del tablero.
	 * 
	 * @return Siguiente jugador que debe poner ficha.
	 */
	Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t);
	
	/**
	 * Permite averiguar si en la partida ya tenemos un ganador o no. 
	 * Devuelve el _color del ganador (si lo hay).
	 * 
	 * @param ultimoMovimiento - Ultimo movimiento realizado. 
	 * Las distintas implementaciones pueden utilizar este parámetro 
	 * para optimizar la búsqueda del ganador.
	 * 
	 * @param t - Estado del tablero.
	 * 
	 * @return _color del ganador, si lo hay. Si no lo hay, devuelve Ficha.VACIA 
	 * (eso NO significa necesariamente que la partida haya terminado en tablas).
	 */
	Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t);
	
	/**
	 * Devuelve true si, con el estado del tablero dado, 
	 * la partida ha terminado en tablas.
	 * 
	 * @param ultimoEnPoner - Jugador que acaba de poner ficha
	 * 
	 * @param t - Estado del tablero
	 * 
	 * @return true si la partida ha terminado sin ganador.
	 */
	boolean tablas(Ficha ultimoEnPoner, Tablero t);
	
}
