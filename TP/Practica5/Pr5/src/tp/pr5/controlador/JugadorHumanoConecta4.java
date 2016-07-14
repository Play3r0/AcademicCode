package tp.pr5.controlador;

import java.util.Scanner;

import tp.pr5.modelo.Ficha;
import tp.pr5.modelo.Movimiento;
import tp.pr5.modelo.Tablero;

/**
 * Clase que implementa los métodos necesarios para crear un jugador
 * humano para Conecta4.
 * 
 * @author Sergio
 *
 */
public class JugadorHumanoConecta4 extends JugadorHumano
{
	/**
	 * Constructor con parámetros.
	 * 
	 * @param f - Factoría para identificar quien ha creado al jugador
	 * 
	 * @param in - Entrada para pedir datos por consola
	 */
	public JugadorHumanoConecta4(FactoriaTipoJuego f, Scanner in) {
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
