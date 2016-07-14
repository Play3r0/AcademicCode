package tp.pr4.controlador;

import java.util.Scanner;

import tp.pr4.modelo.Ficha;
import tp.pr4.modelo.Movimiento;
import tp.pr4.modelo.MovimientoGravity;
import tp.pr4.modelo.ReglasGravity;
import tp.pr4.modelo.ReglasJuego;

/**
 * @author Sergio
 *
 * Implementación de la factoría para el juego del Gravity. 
 * Los métodos devuelven los objetos capaces de jugar a ese juego. 
 * Dado que el tamaño del tablero de Gravity no está fijo, 
 * sino que se puede cambiar en cada partida, 
 * la factoría puede configurarse con el tamaño del tablero que se quiere utilizar.
 */
public class FactoriaGravity implements FactoriaTipoJuego {

	private int columnas;
	private int filas;
	
	/**
	 * Constructor sin parámetros
	 */
	public FactoriaGravity() {

		this.columnas = 10;
		this.filas = 10;
	}
	
	/**
	 * Constructor con parámetros
	 * 
	 * @param columnas - Número de columnas
	 * 
	 * @param filas - Número de filas
	 */
	public FactoriaGravity(int columnas, int filas) {

		this.columnas = columnas;
		this.filas = filas;
	}

	/* (non-Javadoc)
	 * @see tp.pr3.control.FactoriaTipoJuego#creaReglas()
	 */
	@Override
	public ReglasJuego creaReglas() {
		return new ReglasGravity(this.columnas, this.filas);
	}

	/* (non-Javadoc)
	 * @see tp.pr3.control.FactoriaTipoJuego#creaMovimiento(int, int, tp.pr3.logica.Ficha)
	 */
	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color) {
		return new MovimientoGravity(col, fila, color);
	}

	/* (non-Javadoc)
	 * @see tp.pr3.control.FactoriaTipoJuego#creaJugadorHumanoConsola(java.util.Scanner)
	 */
	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in) {

		// Mandamos al constructor del jugador la factoría que lo ha creado
		return new JugadorHumanoGravity(this, in);
	}

	/* (non-Javadoc)
	 * @see tp.pr3.control.FactoriaTipoJuego#creaJugadorAleatorio()
	 */
	@Override
	public Jugador creaJugadorAleatorio() {
		return new JugadorAleatorioGravity();
	}

}
