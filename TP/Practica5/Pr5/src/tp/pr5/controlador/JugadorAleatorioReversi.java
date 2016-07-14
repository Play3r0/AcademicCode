package tp.pr5.controlador;

import java.util.ArrayList;

import tp.pr5.modelo.Ficha;
import tp.pr5.modelo.Movimiento;
import tp.pr5.modelo.MovimientoReversi;
import tp.pr5.modelo.Tablero;
import tp.pr5.utilidades.Comprobar;
import tp.pr5.utilidades.PosicionAleatoria;

/**
 * @author Sergio
 * 
 * Jugador que juega de forma aleatoria a Reversi. 
 * Simplemente elige una columna aleatoria en el tablero, 
 * comprobando antes que se podrá colocar en ella (no está llena).
 * 
 */
public class JugadorAleatorioReversi implements Jugador {

	/* (non-Javadoc)
	 * @see tp.pr5.controlador.Jugador#getMovimiento(tp.pr5.modelo.Tablero, tp.pr5.modelo.Ficha)
	 */
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		
		Movimiento mov;
		
		// Generamos una lista con las posibles casillas válidas
		ArrayList<ArrayList<Integer>> listaMovs = Comprobar.reversiMovsValidos(tab, color);
		
		if(listaMovs.size() > 0) {
			
			// Seleccionamos una de esas casillas de forma aleatoria
			int pos = PosicionAleatoria.posAleatoria(0, listaMovs.size() - 1);
			
			mov = new MovimientoReversi(listaMovs.get(pos).get(0),
					listaMovs.get(pos).get(1), color);
			
		} else mov = new MovimientoReversi(-1, -1, Ficha.VACIA);
		
		return mov;
	}
}
