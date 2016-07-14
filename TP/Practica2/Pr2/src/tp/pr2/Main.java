package tp.pr2;

import java.util.Scanner;

import tp.pr2.control.Controlador;
import tp.pr2.logica.Partida;
import tp.pr2.logica.ReglasConecta4;

/**
 * @author Sergio
 * 
 * Clase que contiene el punto de entrada a la aplicación.
 */
public class Main {

	/**
	 * Método principal de la aplicación.
	 * 
	 * @param args - Argumentos pasados a la aplicación. No se utilizan.
	 */
	public static void main(String[] args) {
		
		// Asociar partida al controlador
		Controlador controlador = new Controlador(new Partida(new ReglasConecta4()), new Scanner(System.in));
		controlador.run();
	}

}
