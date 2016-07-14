package tp.pr4.controlador;

import java.util.Scanner;

/**
 * Clase encargada de generar jugadores humanos para la partida
 * 
 * @author Sergio
 *
 */
public abstract class JugadorHumano implements Jugador {

	protected FactoriaTipoJuego factoria;
	protected Scanner in;
	
	/**
	 * Constructor con parámetros
	 * 
	 * @param factoria - Factoría para identificar quien ha creado al jugador
	 * 
	 * @param in - Entrada para pedir datos por consola
	 */
	public JugadorHumano(FactoriaTipoJuego factoria, Scanner in)
	{
		this.factoria = factoria;
		this.in = in;
	}
}
