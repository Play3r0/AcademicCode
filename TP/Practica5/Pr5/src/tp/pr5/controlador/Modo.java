package tp.pr5.controlador;

/**
 * @author Sergio
 * 
 * Interfaz que recoje los métodos que se han de implementar 
 * para el funcionamiento de los modo de jugador.
 */
public interface Modo {

	/**
	 * Este método contiene el código necesario para que
	 * el jugador comience a jugar en su turno de partida.
	 */
	public void comienzaJugar();
	
	/**
	 * Este método contiene el código necesario para que
	 * el jugador termine de jugar en su turno de partida.
	 */
	public void terminaJugar();
}
