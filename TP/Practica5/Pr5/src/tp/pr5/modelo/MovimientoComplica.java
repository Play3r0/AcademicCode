package tp.pr5.modelo;

/**
 * @author Sergio
 * 
 * Clase que implementa el movimiento para el juego del Complica. 
 * Se deben implementar los métodos abstractos de la clase padre.
 */
public class MovimientoComplica extends Movimiento
{
	private Ficha ultima;	// Variable global para guardar la última ficha cuando la columna está llena
		
	/**
	 * Constructor del objeto.
	 * 
	 * @param _donde - Columna en la que se colocará la ficha
	 * 
	 * @param _color - Color de la ficha que se pondrá (o jugador que pone).
	 * @throws MovimientoInvalido 
	 */
	public MovimientoComplica(int donde, Ficha color)
	{
		super(donde, color);
		this.ultima = Ficha.VACIA;
	}

	/* (non-Javadoc)
	 * @see tp.pr3.logica.Movimiento#ejecutaMovimiento(tp.pr3.logica.Tablero)
	 */
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido
	{
		if(_donde > 0 && _donde <= tab.getAncho())
		{
			int fila = comprobarColumna(tab, _donde);
			
			// Si fila == -1 la columna está llena
			if(fila == -1)
			{
				// Guardamos el _color de la última casilla
				this.ultima = tab.getCasilla(_donde, tab.getAlto());
				
				// Bajamos una posición las fichas de la columna menos la última
				for(int i = tab.getAlto(); i > 1; i--)
					tab.setCasilla(_donde, i, tab.getCasilla(_donde, i - 1));
				
				// Ponemos fila a 1 para que coloque la nueva ficha en la primera fila
				fila = 1;
			}
			
			tab.setCasilla(_donde, fila, _color);
		}
		else throw new MovimientoInvalido("Columna incorrecta. Debe estar entre 1 y " + tab.getAncho() + ".");
	}

	/* (non-Javadoc)
	 * @see tp.pr3.logica.Movimiento#undo(tp.pr3.logica.Tablero)
	 */
	@Override
	public void undo(Tablero tab)
	{
		int fila = comprobarColumna(tab, _donde);
		
		// Si la columna está llena distinguimos dos casos
		if(fila == -1)
		{
			// Si el "movimiento" tiene un _color de ficha, es porque la columna estaba llena
			if(ultima != Ficha.VACIA)
			{
				// Subimos una posición las fichas de la columna menos la primera
				for(int i = 2 ; i <= tab.getAlto(); i++)
					tab.setCasilla(_donde, i - 1, tab.getCasilla(_donde, i));
				
				tab.setCasilla(_donde, tab.getAlto(), ultima);
			}
			else	// Sino simplemente hay que eliminar la primera ficha de la columna
			{
				fila++;
				
				tab.setCasilla(_donde, fila + 1, Ficha.VACIA);
			}
		}
		else tab.setCasilla(_donde, fila + 1, Ficha.VACIA);
	}
	
	/**
	 * Comprueba en que fila se puede colocar la ficha
	 * 
	 * @param tab - Tablero en _donde se comprueba la acción solicitada.
	 * 
	 * @param _donde - Columna de juego
	 * 
	 * @return la fila de la columna _donde se puede colocar la ficha
	 */
	private int comprobarColumna(Tablero tab, int donde)
	{
		int fila = tab.getAlto();
		
		// Buscamos la primera fila vacía empezando por la de más abajo
		while(tab.getCasilla(donde, fila) != Ficha.VACIA && fila - 1 > 0)
			fila--;
		
		// Si el bucle ha terminado y la columna está llena, devolvemos un -1
		if(tab.getCasilla(donde, 1) != Ficha.VACIA)
			fila = -1;
		
		return fila;
	}

}
