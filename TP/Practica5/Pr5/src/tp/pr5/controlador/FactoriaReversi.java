package tp.pr5.controlador;

import java.util.Scanner;

import tp.pr5.modelo.Ficha;
import tp.pr5.modelo.Movimiento;
import tp.pr5.modelo.MovimientoReversi;
import tp.pr5.modelo.ReglasJuego;
import tp.pr5.modelo.ReglasReversi;

/**
 * Implementación de la factoría para el juego del Reversi. 
 * Los métodos devuelven los objetos capaces de jugar a ese juego.
 * 
 * @author Sergio
 */
public class FactoriaReversi implements FactoriaTipoJuego {
	
	/* (non-Javadoc)
	 * @see tp.pr5.controlador.FactoriaTipoJuego#creaReglas()
	 */
	@Override
	public ReglasJuego creaReglas() {
		return new ReglasReversi();
	}

	/* (non-Javadoc)
	 * @see tp.pr5.controlador.FactoriaTipoJuego#creaMovimiento(int, int, tp.pr5.modelo.Ficha)
	 */
	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoReversi(col, fila, color);
	}

	/* (non-Javadoc)
	 * @see tp.pr5.controlador.FactoriaTipoJuego#creaJugadorHumanoConsola(java.util.Scanner)
	 */
	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {
		return new JugadorHumanoReversi(this, in);
	}

	/* (non-Javadoc)
	 * @see tp.pr5.controlador.FactoriaTipoJuego#creaJugadorAleatorio()
	 */
	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioReversi();
	}

}
