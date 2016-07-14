package tp.pr4.controlador;

import tp.pr4.modelo.Ficha;
import tp.pr4.modelo.Movimiento;
import tp.pr4.modelo.MovimientoGravity;
import tp.pr4.modelo.Tablero;
import tp.pr4.utilidades.PosicionAleatoria;

/**
 * @author Sergio
 * 
 * Jugador que juega de forma aleatoria a Gravity. 
 * Simplemente elige una posición aleatoria en el tablero, 
 * comprobando antes que esa casilla está vacía (se podrá poner).
 *
 */
public class JugadorAleatorioGravity implements Jugador {

	/* (non-Javadoc)
	 * @see tp.pr3.control.Jugador#getMovimiento(tp.pr3.logica.Tablero, tp.pr3.logica.Ficha)
	 */
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {

		Movimiento mov;
		int col = PosicionAleatoria.posAleatoria(tab.getAncho());
		int fila = PosicionAleatoria.posAleatoria(tab.getAlto());
		
		// Mientras no se genere una posición vacía, sigue probando
		while(tab.getCasilla(col, fila) != Ficha.VACIA)
		{
			col = PosicionAleatoria.posAleatoria(tab.getAncho());
			fila = PosicionAleatoria.posAleatoria(tab.getAlto());
		}	
		
		mov = new MovimientoGravity(col, fila, color);
		
		return mov;
	}

}
