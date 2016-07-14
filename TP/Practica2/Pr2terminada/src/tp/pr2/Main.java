package tp.pr2;

import java.util.Scanner;

import tp.pr2.control.Controlador;
import tp.pr2.logica.Partida;
import tp.pr2.logica.ReglasConecta4;

public class Main {

	public static void main(String[] args) {
		
		// Asociar partida al controlador
		Controlador controlador = new Controlador(new Partida(new ReglasConecta4()), new Scanner(System.in));
		controlador.run();
	}

}
