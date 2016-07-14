package tp.pr4.controlador;

import java.util.Scanner;

import tp.pr4.modelo.Ficha;
import tp.pr4.modelo.Movimiento;
import tp.pr4.modelo.MovimientoComplica;
import tp.pr4.modelo.ReglasComplica;
import tp.pr4.modelo.ReglasJuego;

/**
 * @author Sergio
 * 
 * Implementación de la factoría para el juego del Complica. 
 * Los métodos devuelven los objetos capaces de jugar a ese juego.
 *
 */
public class FactoriaComplica implements FactoriaTipoJuego {

	/* (non-Javadoc)
	 * @see tp.pr3.control.FactoriaTipoJuego#creaReglas()
	 */
	@Override
	public ReglasJuego creaReglas() {

		return new ReglasComplica();
	}

	/* (non-Javadoc)
	 * @see tp.pr3.control.FactoriaTipoJuego#creaMovimiento(int, int, tp.pr3.logica.Ficha)
	 */
	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {

		// La fila no se utilizar en Complica
		return new MovimientoComplica(col, color);
	}

	/* (non-Javadoc)
	 * @see tp.pr3.control.FactoriaTipoJuego#creaJugadorHumanoConsola(java.util.Scanner)
	 */
	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {

		// Mandamos al constructor del jugador la factoría que lo ha creado
		return new JugadorHumanoComplica(this, in);
	}

	/* (non-Javadoc)
	 * @see tp.pr3.control.FactoriaTipoJuego#creaJugadorAleatorio()
	 */
	@Override
	public Jugador creaJugadorAleatorio() {
		
		return new JugadorAleatorioComplica();
	}

}
