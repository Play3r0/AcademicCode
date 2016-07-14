package tp.pr5.controlador;

import java.util.Scanner;

import tp.pr5.modelo.Ficha;
import tp.pr5.modelo.Movimiento;
import tp.pr5.modelo.Tablero;

/**
 * Clase que implementa los métodos necesarios para crear un jugador
 * humano para Reversi.
 * 
 * @author Sergio
 *
 */
public class JugadorHumanoReversi extends JugadorHumano {

	/**
	 * Constructor de la clase
	 * 
	 * @param f - Factoría del tipo de juego actual
	 * @param in - Objeto de tipo Scanner para permitir la lectura de datos
	 */
	public JugadorHumanoReversi(FactoriaTipoJuego f, Scanner in) {
		super(f, in);
	}

	/* (non-Javadoc)
	 * @see tp.pr5.controlador.Jugador#getMovimiento(tp.pr5.modelo.Tablero, tp.pr5.modelo.Ficha)
	 */
	@Override
	public Movimiento getMovimiento(Tablero tab, Ficha color) {
		
		String comando = "";
		
		System.out.print("Introduce la columna: ");
		comando = in.nextLine();
		
		int col = Integer.valueOf(comando.replaceAll("\\s",""));
		
		System.out.print("Introduce la fila: ");
		comando = in.nextLine();
		
		int fila = Integer.valueOf(comando.replaceAll("\\s",""));
		
		return factoria.creaMovimiento(col, fila, color);
	}
	
	
}
