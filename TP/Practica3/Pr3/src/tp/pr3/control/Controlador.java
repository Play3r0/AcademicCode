package tp.pr3.control;

import java.util.Scanner;

import tp.pr3.logica.Ficha;
import tp.pr3.logica.Movimiento;
import tp.pr3.logica.MovimientoInvalido;
import tp.pr3.logica.Partida;

/**
 * @author Sergio
 * 
 * Clase que controla la ejecución de la partida, 
 * pidiendo al usuario qué quiere ir haciendo, hasta que la partida termina.
 */
public class Controlador {
	
	private FactoriaTipoJuego f;
	private Partida p;
	private Scanner in;
	private Jugador jugador1;
	private Jugador jugador2;
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param p - Partida a la que se jugará, configurada con el juego al que hay que jugar en la invocación a run().
	 * @param in - Scanner que hay que utilizar para pedirle la información al usuario.
	 */
	public Controlador(FactoriaTipoJuego f, Partida p, java.util.Scanner in) {
		
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
		
		String comando = "";
		String[] comandoSeparado = null;
		Movimiento mov = null;
		
		do {
			
			System.out.println(p.toString().toString());
			if(p.getTurno() == Ficha.BLANCA)
				System.out.println("Juegan blancas");
			else System.out.println("Juegan negras");
			System.out.print("Qué quieres hacer? ");
			comando = in.nextLine();
			comando = comando.toLowerCase();
			
			comandoSeparado = comando.split(" ");
			
			// Evaluamos la opción seleccionada
			switch(comandoSeparado[0]) {
			
				case "deshacer": 
					
					if(!p.undo())
						System.err.println("Imposible deshacer.");
					
					break;
				
				case "reiniciar":
					
					reiniciarPartida();
					
					break;
				
				case "poner":
					
					if(p.getTurno() == Ficha.BLANCA)
						mov = p.dameMov(jugador1);						
					else {
						
						if(p.getTurno() == Ficha.NEGRA)
							mov = p.dameMov(jugador2);
					}
						
					try {
						
						// Ejecutamos el movimiento
						p.ejecutaMovimiento(mov);
						
						// Si la partida ha terminado mostramaos el tablero y si tenemos ganador o tablas
						if(p.isTerminada()) {
							System.out.println(p.toString().toString());
							
							if(p.getGanador() == Ficha.BLANCA)
								System.out.println("Ganan las blancas");
							else {
								
								if(p.getGanador() == Ficha.NEGRA)
									System.out.println("Ganan las negras");
								else System.out.println("Partida terminada en tablas.");
							}
							
							comando = "salir";
						}
						
					} catch (MovimientoInvalido e) {
						System.err.println(e.getMessage());
					}
						
					break;
					
				case "jugar":
					
					jugarJuego(comandoSeparado);
					
					break;
					
				case "jugador":
					
					cambiarJugador(comandoSeparado);
					
					break;
					
				case "ayuda":
					
					System.out.println("Los comandos disponibles son:\n\n"
					+ "PONER: utilízalo para poner la siguiente ficha.\n"
					+ "DESHACER: deshace el último movimiento hecho en la partida.\n"
					+ "REINICIAR: reinicia la partida.\n"
					+ "JUGAR [c4|co|gr] [tamX tamY]: cambia el tipo de juego.\n"
					+ "JUGADOR [blancas|negras] [humano|aleatorio]: cambia el tipo de jugador.\n"
					+ "SALIR: termina la aplicación.\n"
					+ "AYUDA: muestra esta ayuda.\n");
					
					break;
					
				case "salir":
					
					break;
					
				default:
					
					System.err.println("No te entiendo.");
					
					break;
			}
			
		}while(!comando.equals("salir"));
	}

	private void reiniciarPartida() {
		
		// Generamos una nueva partida
		p.reset(f.creaReglas());
		
		// Y restauramos los jugadores humanos
		this.jugador1 = f.creaJugadorHumanoConsola(this.in);
		this.jugador2 = f.creaJugadorHumanoConsola(this.in);
		
		System.out.println("Partida reiniciada.");
	}

	private void jugarJuego(String[] comandoSeparado) {
		
		// Calculamos el tamaño de los argumentos
		if(comandoSeparado.length >= 2) 
		{
			// Si tiene el tamaño indicado preguntamos por el modo de juego
			if(comandoSeparado[1].equals("c4")) {
				
				f = new FactoriaConecta4();
				reiniciarPartida();
				/*p = new Partida(f.creaReglas());
				
				this.jugador1 = f.creaJugadorHumanoConsola(this.in);
				this.jugador2 = f.creaJugadorHumanoConsola(this.in);
				
				System.out.println("Partida reiniciada.");*/
			}
			else {
				
				if(comandoSeparado[1].equals("co")) {
					
					f = new FactoriaComplica();
					reiniciarPartida();
					/*p = new Partida(f.creaReglas());
					
					this.jugador1 = f.creaJugadorHumanoConsola(this.in);
					this.jugador2 = f.creaJugadorHumanoConsola(this.in);

					System.out.println("Partida reiniciada.");*/
				}
				else {
					
					// Controlamos todos los posibles errores a la hora de crear
					// una nueva partida en Gravity
					if(comandoSeparado.length == 4 && comandoSeparado[1].equals("gr")) {
							
						try {
						
							f = new FactoriaGravity(Integer.parseInt(comandoSeparado[2]), Integer.parseInt(comandoSeparado[3]));
							reiniciarPartida();
							/*p = new Partida(f.creaReglas());
							
							this.jugador1 = f.creaJugadorHumanoConsola(this.in);
							this.jugador2 = f.creaJugadorHumanoConsola(this.in);

							System.out.println("Partida reiniciada.");*/
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
}
