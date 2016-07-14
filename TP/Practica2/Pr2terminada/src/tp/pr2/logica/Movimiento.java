
package tp.pr2.logica;

/**
 * @author usuario_local
 *
 */
public abstract class Movimiento
{
	/**
	 * Ejecuta el movimiento sobre el tablero que se recibe como par�metro.
	 * 
	 * @param tab
	 * @return
	 */
	public abstract boolean ejecutaMovimiento(Tablero tab);
	
	/**
	 * Devuelve el color del jugador al que pertenece el movimiento. 
	 * Se puede dar por cierto que tablero recibido sigue las reglas del tipo 
	 * de juego al que pertenece el movimiento. En caso contrario, 
	 * el comportamiento es indeterminado.
	 * 
	 * @return
	 */
	public Ficha getJugador()
	{
		if(this.getClass() == MovimientoConecta4.class)
			return ((MovimientoConecta4) this).color;
		else
		{
			if(this.getClass() == MovimientoComplica.class)
				return ((MovimientoComplica) this).color;
		}
		
		return null;
	}
	
	/**
	 * Deshace el movimiento en el tablero recibido como par�metro.
	 * 
	 * @param tab
	 */
	public abstract void undo(Tablero tab);
}
