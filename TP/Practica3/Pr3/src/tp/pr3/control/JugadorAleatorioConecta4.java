package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoConecta4;
import tp.pr3.logica.Tablero;
import tp.pr3.utilidades.PosicionAleatoria;

/**
 * Jugador que juega de forma aleatoria a Conecta 4. 
 * Simplemente elige una columna aleatoria en el tablero, 
 * comprobando antes que se podrá colocar en ella (no está llena).
 * 
 * @author Sergio
 */
public class JugadorAleatorioConecta4 implements Jugador
{
	/* (non-Javadoc)
	 * @see tp.pr3.control.Jugador#getMovimiento(tp.pr3.logica.Tablero, tp.pr3.logica.Ficha)
	 */
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color)
	{
		Movimiento mov;
		
		// Generamos una posición aleatorio para crear el movimiento
		int col = PosicionAleatoria.posAleatoria(tab.getAncho());
		
		// Mientras no se genere una posición vacía, sigue probando
		while(tab.getCasilla(col, 1) != Ficha.VACIA)
			col = PosicionAleatoria.posAleatoria(tab.getAncho());
			
		mov = new MovimientoConecta4(col, color);
		
		return mov;
	}

}
