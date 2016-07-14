package tp.pr4.controlador;

import java.util.Scanner;

import tp.pr4.modelo.Ficha;
import tp.pr4.modelo.Movimiento;
import tp.pr4.modelo.Partida;
import tp.pr4.vistas.Observador;

/**
 * @author Sergio
 * 
 * Clase que controla la ejecución de la partida, 
 * pidiendo al usuario qué quiere ir haciendo, hasta que la partida termina.
 */
public class ControladorConsola {
	
	private FactoriaTipoJuego f;
	private Partida p;
	private Scanner in;
	private Jugador jugador1;
	private Jugador jugador2;
	
	private String comando;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param p - Partida a la que se jugará, configurada con el juego al que hay que jugar en la invocación a run().
	 * @param in - Scanner que hay que utilizar para pedirle la información al usuario.
	 */
	public ControladorConsola(FactoriaTipoJuego f, Partida p, java.util.Scanner in) {
		
		this.f = f;		
		this.p = p;
		this.in = in;
		this.jugador1 = f.creaJugadorHumanoConsola(this.in);
		this.jugador2 = f.creaJugadorHumanoConsola(this.in);
		//this.jugador1 = f.creaJugadorAleatorio();
		//this.jugador2 = f.creaJugadorAleatorio();
	}
	
	/**
	 * Ejecuta la partida hasta que ésta termina. 
	 * Se supone que se hará una única invocación a este método; 
	 * tras ella, si se vuelve a llamar a run() el comportamiento será indeterminado.
	 */
	public void run() {
		
		String[] comandoSeparado = null;
		comando = "";
		
		p.crearPartida();
		
		do {
			// Esperamos a que el usuario introduzca una opción
			comando = in.nextLine();
			comando = comando.toLowerCase();
			
			comandoSeparado = comando.split(" ");
			
			// Evaluamos la opción seleccionada
			switch(comandoSeparado[0]) {
			
				case "deshacer": 
					
					undo();
					
					break;
				
				case "reiniciar":
					
					reiniciar();
					
					break;
				
				case "poner":
					
					poner();
						
					break;
					
				case "jugar":
					
					jugarJuego(comandoSeparado);
					
					break;
					
				case "jugador":
					
					cambiarJugador(comandoSeparado);
					
					break;
					
				case "ayuda":
					
					ayuda();
					
					break;
					
				case "salir":
					
					break;
					
				default:
					
					p.exceptionMovInv("No te entiendo.");
					
					break;
			}
			
		}while(!comando.equals("salir"));
	}

	/**
	 * Añade un Observador a la partida
	 * 
	 * @param o - Objeto de la clase Observador
	 */
	public void addObservador(Observador o) {
		p.addObserver(o);
	}

	/**
	 * Elimina un Observador de la Partida
	 * 
	 * @param o - Objeto observador
	 */
	public void removeObservador(Observador o) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Resetea la partida
	 * 
	 * @param f - Objeto Factoria para reiniciar el juego
	 */
	private void reset(FactoriaTipoJuego f) {
		
		this.f = f;
		reiniciar();
	}
	
	/**
	 * Método para deshacer el último movimiento
	 */
	private void undo() {
		
		// Llamada para deshacer el último movimiento válido
		p.undo();	
	}

	/**
	 * Método privado para reiniciar la partida
	 */
	private void reiniciar() {
		
		// Generamos una nueva partida
		p.reset(f.creaReglas());
		
		// Generamos los nuevos jugadores
		this.jugador1 = f.creaJugadorHumanoConsola(this.in);
		this.jugador2 = f.creaJugadorHumanoConsola(this.in);
		
	}

	/**
	 * Método para poner una ficha
	 */
	private void poner() {
		
		Movimiento mov = null;
		
		// Preguntamos por el turno para seleccionar jugador
		if(p.getTurno() == Ficha.BLANCA)
			mov = p.dameMov(jugador1);						
		else {
			
			if(p.getTurno() == Ficha.NEGRA)
				mov = p.dameMov(jugador2);
		}
			
		// Ejecutamos el movimiento
		p.ejecutaMovimiento(mov);
			
		finalizar();
	}
	
	/**
	 * Método para cambiar de juego
	 * 
	 * @param comandoSeparado - Array de string con los datos introducidos por el usuario
	 */
	private void jugarJuego(String[] comandoSeparado) {
		
		// Calculamos el tamaño de los argumentos
		if(comandoSeparado.length >= 2) 
		{
			// Si tiene el tamaño indicado preguntamos por el modo de juego
			if(comandoSeparado[1].equals("c4"))
				reset(new FactoriaConecta4());
			else {
				
				if(comandoSeparado[1].equals("co"))
					reset(new FactoriaComplica());
				else {
					
					// Controlamos todos los posibles errores a la hora de crear
					// una nueva partida en Gravity
					if(comandoSeparado.length == 4 && comandoSeparado[1].equals("gr")) {
							
						try {
						
							reset(new FactoriaGravity(Integer.parseInt(comandoSeparado[2]), Integer.parseInt(comandoSeparado[3])));
						}
						catch(NumberFormatException e) {
							
							System.err.println("No te entiendo.");
						}
					}
					else System.err.println("No te entiendo.");
				}
			}
		}
		else System.err.println("No te entiendo.");
	}
	
	/**
	 * Método privado para cambiar de jugador
	 * 
	 * @param comandoSeparado - Array de string con el cambio de jugador
	 */
	private void cambiarJugador(String[] comandoSeparado) {
		
		// Identificamos el jugador con el color
		if(comandoSeparado[1].equals("blancas"))
		{
			// Creamos un jugador humano
			if(comandoSeparado[2].equals("humano"))
				this.jugador1 = f.creaJugadorHumanoConsola(this.in);
			else
			{
				// Creamos un jugador aleatorio
				if(comandoSeparado[2].equals("aleatorio"))
					this.jugador1 = f.creaJugadorAleatorio();
			}
		}
		else
		{
			if(comandoSeparado[1].equals("negras"))
			{
				if(comandoSeparado[2].equals("humano"))
					this.jugador2 = f.creaJugadorHumanoConsola(this.in);
				else
				{
					if(comandoSeparado[2].equals("aleatorio"))
						this.jugador2 = f.creaJugadorAleatorio();									
				}
			}
		}
	}
	
	/**
	 * Método privado que muestra la ayuda
	 */
	private void ayuda() {
		System.out.println("Los comandos disponibles son:\n\n"
		+ "PONER: utilízalo para poner la siguiente ficha.\n"
		+ "DESHACER: deshace el último movimiento hecho en la partida.\n"
		+ "REINICIAR: reinicia la partida.\n"
		+ "JUGAR [c4|co|gr] [tamX tamY]: cambia el tipo de juego.\n"
		+ "JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.\n"
		+ "SALIR: termina la aplicación.\n"
		+ "AYUDA: muestra esta ayuda.\n");
	}
	
	/**
	 * Método privado que comprueba si la partida ha terminado
	 */
	private void finalizar() {
		
		// Si la partida ha terminado mostramaos el tablero y si tenemos ganador o tablas
		if(p.isTerminada())
			comando = "salir";
	}
}
