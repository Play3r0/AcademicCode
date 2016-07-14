package tp.pr2.logica;

/**
 * Clase que implementa el movimiento para el juego del Conecta 4. 
 * Se deben implementar los m�todos abstractos de la clase padre.
 * 
 * @author usuario_local
 *
 */
public class MovimientoConecta4 extends Movimiento
{
	protected int donde;
	protected Ficha color;
	
	public MovimientoConecta4(int donde, Ficha color)
	{
		this.donde = donde;
		this.color = color;
	}

	@Override
	public boolean ejecutaMovimiento(Tablero tab)
	{
		boolean movimiento = false;
		
		if(donde > 0 && donde <= tab.getAncho())
		{
			int fila = comprobarColumna(tab, donde);
			
			// Si fila vac�a (fila == -1) colocamos la nueva casilla
			if(fila != -1)
			{
				tab.setCasilla(donde, fila, color);
				movimiento = true;
			}
		}
		
		return movimiento;
	}

	@Override
	public void undo(Tablero tab)
	{
		int fila = comprobarColumna(tab, donde);
		
		if(fila == -1)
			fila++;
		
		tab.setCasilla(donde, fila + 1, Ficha.VACIA);
	}
	
	private int comprobarColumna(Tablero tab, int donde)
	{
		int fila = tab.getAlto();
		
		// Buscamos la primera fila vac�a empezando por la de m�s abajo
		while(tab.getCasilla(donde, fila) != Ficha.VACIA && fila - 1 > 0)
			fila--;
		
		// Si el bucle ha terminado y la fila no est� vac�a, devolvemos un -1
		if(tab.getCasilla(donde, 1) != Ficha.VACIA)
			fila = -1;
		
		return fila;
	}

}
