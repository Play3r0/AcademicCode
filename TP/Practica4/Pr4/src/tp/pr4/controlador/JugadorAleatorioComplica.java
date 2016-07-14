package tp.pr4.controlador;

import tp.pr4.modelo.Ficha;
import tp.pr4.modelo.Movimiento;
import tp.pr4.modelo.MovimientoComplica;
import tp.pr4.modelo.Tablero;
import tp.pr4.utilidades.PosicionAleatoria;

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
