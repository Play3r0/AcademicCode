package tp.pr4.controlador;

import java.util.Scanner;

import tp.pr4.modelo.Ficha;
import tp.pr4.modelo.Movimiento;
import tp.pr4.modelo.Tablero;

/**
 * Clase que implementa los métodos necesarios para crear un jugador
 * humano para Complica.
 * 
 * @author Sergio
 *
 */
public class JugadorHumanoComplica extends JugadorHumano {

	/**
	 * Constructor con parámetros.
	 * 
	 * @param f - Factoría para identificar quien ha creado al jugador
	 * 
	 * @param in - Entrada para pedir datos por consola
	 */
	public JugadorHumanoComplica(FactoriaTipoJuego f, Scanner in) {
		
		super(f, in);
	}

	/* (non-Javadoc)
	 * @see tp.pr3.control.Jugador#getMovimiento(tp.pr3.logica.Tablero, tp.pr3.logica.Ficha)
	 */
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		
		String comando = "";
		
		System.out.print("Introduce la columna: ");
		comando = in.nextLine();
		
		int col = Integer.valueOf(comando.replaceAll("\\s",""));
		
		return factoria.creaMovimiento(col, 1, color);
	}

}
