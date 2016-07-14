
package tp.pr4.modelo;

/**
 * @author Sergio
 * 
 * Clase que representa el movimiento de un jugador. 
 * Tiene un método para ejecutar el movimiento sobre la partida, 
 * y otro para deshacerlo. Es una clase abstracta; 
 * habrá una clase no abstracta por cada tipo de juego soportado.
 */
public abstract class Movimiento
{
	protected int donde;
	protected Ficha color;
	
	/**
	 * Construye un nuevo Movimiento en base a los parámetros recibidos.
	 * 
	 * @param donde - posición del tablero (hasta el momento solo representa la columna).
	 * @param color - Color del jugador que realiza el movimiento.
	 */
	protected Movimiento(int donde, Ficha color)
	{
		this.donde = donde;
		this.color = color;
	}
	
	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como parámetro. 
	 * Se puede dar por cierto que tablero recibido sigue las reglas del tipo de juego 
	 * al que pertenece el movimiento. En caso contrario, el comportamiento es indeterminado.
	 * 
	 * @param tab - Tablero sobre el que ejecutar el movimiento
	 * @return true si todo fue bien. Se devuelve false si el movimiento no puede ejecutarse sobre el tablero.
	 * @throws MovimientoInvalido 
	 */
	public abstract void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido;
	
	/**
	 * Devuelve el color del jugador al que pertenece el movimiento. 
	 * Se puede dar por cierto que tablero recibido sigue las reglas del tipo 
	 * de juego al que pertenece el movimiento. En caso contrario, 
	 * el comportamiento es indeterminado.
	 * 
	 * @return Color del jugador (coincide con el pasado al constructor).
	 */
	public Ficha getJugador()
	{		
		return this.color;
	}
	
	/**
	 * Deshace el movimiento en el tablero recibido como parámetro. 
	 * Se puede dar por cierto que el movimiento se ejecutó sobre ese tablero; 
	 * en caso contrario, el comportamiento es indeterminado. 
	 * Por lo tanto, es de suponer que el método siempre funcionará correctamente.
	 * 
	 * @param tab - Tablero de donde deshacer el movimiento.
	 */
	public abstract void undo(Tablero tab);
}
