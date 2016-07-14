package tp.pr5.controlador;

import java.util.Scanner;

import tp.pr5.modelo.Ficha;
import tp.pr5.modelo.Movimiento;
import tp.pr5.modelo.MovimientoConecta4;
import tp.pr5.modelo.ReglasConecta4;
import tp.pr5.modelo.ReglasJuego;

/**
 * Implementación de la factoría para el juego del Conecta 4. 
 * Los métodos devuelven los objetos capaces de jugar a ese juego.
 * 
 * @author Sergio
 */
public class FactoriaConecta4 implements FactoriaTipoJuego
{
	/* (non-Javadoc)
	 * @see tp.pr3.control.FactoriaTipoJuego#creaReglas()
	 */
	public ReglasJuego creaReglas()
	{
		return new ReglasConecta4();
	}

	/* (non-Javadoc)
	 * @see tp.pr3.control.FactoriaTipoJuego#creaMovimiento(int, int, tp.pr3.logica.Ficha)
	 */
	@Override
	public Movimiento creaMovimiento(int col, int fila, Ficha color)
	{
		// La fila no se utilizar en Conecta4
		return new MovimientoConecta4(col, color);
	}

	/* (non-Javadoc)
	 * @see tp.pr3.control.FactoriaTipoJuego#creaJugadorHumanoConsola(java.util.Scanner)
	 */
	@Override
	public Jugador creaJugadorHumanoConsola(Scanner in)
	{
		// Mandamos al constructor del jugador la factoría que lo ha creado
		return new JugadorHumanoConecta4(this, in);
	}

	/* (non-Javadoc)
	 * @see tp.pr3.control.FactoriaTipoJuego#creaJugadorAleatorio()
	 */
	@Override
	public Jugador creaJugadorAleatorio()
	{
		return new JugadorAleatorioConecta4();
	}
}
