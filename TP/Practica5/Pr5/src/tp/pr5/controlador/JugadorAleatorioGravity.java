package tp.pr5.controlador;

import tp.pr5.modelo.Ficha;
import tp.pr5.modelo.Movimiento;
import tp.pr5.modelo.MovimientoGravity;
import tp.pr5.modelo.Tablero;
import tp.pr5.utilidades.PosicionAleatoria;

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
		int col = PosicionAleatoria.posAleatoria(1, tab.getAncho());
		int fila = PosicionAleatoria.posAleatoria(1, tab.getAlto());
		
		// Mientras no se genere una posición vacía, sigue probando
		while(tab.getCasilla(col, fila) != Ficha.VACIA) {
			
			col = PosicionAleatoria.posAleatoria(1, tab.getAncho());
			fila = PosicionAleatoria.posAleatoria(1, tab.getAlto());
		}	
		
		mov = new MovimientoGravity(col, fila, color);
		
		return mov;
	}

}
