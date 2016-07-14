package tp.pr1;

import tp.pr1.control.Controlador;
import tp.pr1.logica.Partida;

public class Main {

	public static void main(String[] args) {
		
		// Crear partida
		Partida partida = new Partida();
		// Asociar partida al controlador
		Controlador controlador = new Controlador(partida);
		controlador.run();
	}

}
