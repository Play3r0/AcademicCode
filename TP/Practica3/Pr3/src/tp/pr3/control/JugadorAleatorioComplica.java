package tp.pr3.control;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoComplica;
import tp.pr3.logica.Tablero;
import tp.pr3.utilidades.PosicionAleatoria;

public class JugadorAleatorioComplica implements Jugador {
	
	/* (non-Javadoc)
	 * @see tp.pr3.control.Jugador#getMovimiento(tp.pr3.logica.Tablero, tp.pr3.logica.Ficha)
	 */
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		
		Movimiento mov;
		
		// Generamos una posición aleatorio para crear el movimiento
		int col = PosicionAleatoria.posAleatoria(tab.getAncho());
			
		mov = new MovimientoComplica(col, color);
		
		return mov;
	}

}
