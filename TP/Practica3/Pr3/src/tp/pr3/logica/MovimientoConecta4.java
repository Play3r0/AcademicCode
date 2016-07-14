package tp.pr3.logica;

/**
 * @author Sergio
 * 
 * Clase que implementa el movimiento para el juego del Conecta 4. 
 * Se deben implementar los métodos abstractos de la clase padre.
 */
public class MovimientoConecta4 extends Movimiento
{
	/**
	 * Constructor del objeto.
	 * 
	 * @param donde - Columna en la que se colocará la ficha
	 * 
	 * @param color - Color de la ficha que se pondrá (o jugador que pone).
	 */
	public MovimientoConecta4(int donde, Ficha color) {
		super(donde, color);
	}

	/* (non-Javadoc)
	 * @see tp.pr3.logica.Movimiento#ejecutaMovimiento(tp.pr3.logica.Tablero)
	 */
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		
		if(this.donde > 0 && this.donde <= tab.getAncho())
		{
			int fila = comprobarColumna(tab, this.donde);
			
			// Si fila vacía (fila == -1) colocamos la nueva casilla
			if(fila != -1)
				tab.setCasilla(this.donde, fila, this.color);
			else throw new MovimientoInvalido("Columna llena.");
		}
		else throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y " + tab.getAncho() + ".");
	}

	/* (non-Javadoc)
	 * @see tp.pr3.logica.Movimiento#undo(tp.pr3.logica.Tablero)
	 */
	@Override
	public void undo(Tablero tab)
	{
		int fila = comprobarColumna(tab, this.donde);
		
		if(fila == -1)
			fila++;
		
		tab.setCasilla(this.donde, fila + 1, Ficha.VACIA);
	}
	
	/**
	 * Comprueba en que fila se puede colocar la ficha
	 * 
	 * @param tab - Tablero en donde se comprueba la acción solicitada.
	 * 
	 * @param donde - Columna de juego
	 * 
	 * @return la fila de la columna donde se puede colocar la ficha
	 */
	private int comprobarColumna(Tablero tab, int donde)
	{
		int fila = tab.getAlto();
		
		// Buscamos la primera fila vacía empezando por la de más abajo
		while(tab.getCasilla(donde, fila) != Ficha.VACIA && fila - 1 > 0)
			fila--;
		
		// Si el bucle ha terminado y la fila no está vacía, devolvemos un -1
		if(tab.getCasilla(donde, 1) != Ficha.VACIA)
			fila = -1;
		
		return fila;
	}

}
