package tp.pr5.controlador;

import tp.pr5.modelo.Ficha;
import tp.pr5.modelo.Movimiento;
import tp.pr5.modelo.MovimientoComplica;
import tp.pr5.modelo.Tablero;
import tp.pr5.utilidades.PosicionAleatoria;

public class JugadorAleatorioComplica implements Jugador {
	
	/* (non-Javadoc)
	 * @see tp.pr3.control.Jugador#getMovimiento(tp.pr3.logica.Tablero, tp.pr3.logica.Ficha)
	 */
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		
		Movimiento mov;
		
		// Generamos una posición aleatorio para crear el movimiento
		int col = PosicionAleatoria.posAleatoria(1, tab.getAncho());
			
		mov = new MovimientoComplica(col, color);
		
		return mov;
	}

}
