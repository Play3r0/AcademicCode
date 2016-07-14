package tp.pr5;

import java.util.Scanner;

import org.apache.commons.cli.CommandLine;
import org.apache.commons.cli.CommandLineParser;
import org.apache.commons.cli.Options;
import org.apache.commons.cli.ParseException;
import org.apache.commons.cli.PosixParser;

import tp.pr5.controlador.ControladorConsola;
import tp.pr5.controlador.ControladorGUI;
import tp.pr5.controlador.FactoriaComplica;
import tp.pr5.controlador.FactoriaConecta4;
import tp.pr5.controlador.FactoriaGravity;
import tp.pr5.controlador.FactoriaReversi;
import tp.pr5.controlador.FactoriaTipoJuego;
import tp.pr5.modelo.Juego;
import tp.pr5.modelo.MovimientoInvalido;
import tp.pr5.modelo.Partida;
import tp.pr5.modelo.ReglasJuego;
import tp.pr5.vistas.VistaConsola;
import tp.pr5.vistas.VistaGUI;

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
	 * 
	 * @throws MovimientoInvalido 
	 */
	public static void main(String[] args) throws MovimientoInvalido {
		
		String tipoJuego = "", tipoInterfaz = "";
		FactoriaTipoJuego f = new FactoriaConecta4();
		Juego juego = Juego.CONECTA4;
		
		int columnas;
		int filas;
		
		// Tratamos el string de argumentos
		CommandLine cmd = argsParser(args);
		
		// Preguntamos por los argumentos inválidos
		String [] basura = cmd.getArgs();
		
		if(basura.length != 0) {
		
			String error = generarMensajeError(basura);
			mostrarMensaje("Argumentos no entendidos: " + error);
		}
		else
		{		
			// Recuperamos el tipo de juego
			tipoJuego = argumentosDeJuego(cmd, "tipoJuego", "c4");
			
			if(tipoJuego.equals("c4")) {
				
				f = new FactoriaConecta4();
				juego = Juego.CONECTA4;
			}
			else {
				if(tipoJuego.equals("co")) {
					
					f = new FactoriaComplica();
					juego = Juego.COMPLICA;
				}
				else {
					
					if(tipoJuego.equals("gr")) {
						
						// Necesitamos capturar la excepción en caso de que los argumentos
						// fila y columna sean incorrectos.
						try {
							
							// Si recibimos la columna, tenemos que comprobar si existe el argumento fila
							if(cmd.hasOption('x')) {
								
								columnas = Integer.parseInt(cmd.getOptionValue('x'));
								
								// Si tiene x pero no tiene y, mostramos error. Faltan argumentos
								if(cmd.hasOption('y')) {
									
									filas = Integer.parseInt(cmd.getOptionValue('y'));
									f = new FactoriaGravity(columnas, filas);
									juego = Juego.GRAVITY;
								}
								else mostrarMensaje("Argumentos no entendidos o faltan argumentos.");
							}
							else {
								
								// Si tiene y pero no tiene x, mostramos error. Faltan argumentos
								if(cmd.hasOption('y'))
									mostrarMensaje("Argumentos no entendidos o faltan argumentos.");
								else {
									
									f = new FactoriaGravity();
									juego = Juego.GRAVITY;
								}
							}
								
						}
						catch(NumberFormatException e) {
							mostrarMensaje("Argumentos no entendidos: " + e.getMessage());
						}
					}
					else {
						
						if(tipoJuego.equals("rv")) {
							
							f = new FactoriaReversi();
							juego = Juego.REVERSI;
						}
						else {
							
							mostrarMensaje("Juego '" + tipoJuego + "' incorrecto.");
						
							System.exit(1);
						}
					}
				}
			}
			
			ReglasJuego reglas = f.creaReglas();
			Partida p = new Partida(reglas);	// Modelo
			
			// Ventana Consola por defecto
			tipoInterfaz = argumentosDeJuego(cmd, "tipoInterfaz", "console");
			if(tipoInterfaz.equals("console")) {
				
				// Asociar partida al controlador
				ControladorConsola c = new ControladorConsola(f, p, new Scanner(System.in));
				//VistaConsola con = new VistaConsola(c);
				new VistaConsola(c);
				c.run();
			}
			else {
				
				if(tipoInterfaz.equals("window")) {
					
					ControladorGUI c = new ControladorGUI(f, p);
					//VistaGUI vista = new VistaGUI(c);
					
					new VistaGUI(c, juego);
				}
			}
			
		}
	}
	
	/**
	 * Método encargado de tratar los argumentos recibidos al ejecutar la aplicación
	 * 
	 * @param args - Cadena con los argumentos
	 * 
	 * @return - Devolvemos un objeto de tipo CommandLine con los argumentos tratados
	 */
	private static CommandLine argsParser(String[] args) {
		
		CommandLine	cmd = null;
		CommandLineParser parser = new PosixParser();
		
		Options options = new Options();
		
		// Comando - Comando largo - Tiene argumentos - Descripción
		options.addOption("g", "game", true, "Tipo de juego");
		options.addOption("x", "tamX", true, "Número de columnas");
		options.addOption("y", "tamY", true, "Número de filas");
		options.addOption("h", "help", false, "Muestra la ayuda");
		options.addOption("u", "ui", true, "Tipo de interfaz");
		
		try {
			
			cmd = parser.parse(options, args);
		
		} catch (ParseException e) {
			
			mostrarMensaje(e.getMessage());
			
			System.exit(1);
		}
		
		return cmd;
	}

	/**
	 * Método que analiza los argumentos para ejecutar la acción solicitada
	 * 
	 * @param cmd - Objeto que ya contiene los argumentos analizados
	 * 
	 * @param tam - Tamaño de la cadena de argumentos
	 * 
	 * @return Devuelve el tipo de juego seleccionado o muestra la ayuda
	 */
	private static String argumentosDeJuego(CommandLine cmd, String cmdArg, String argFinal) {
		
		if(cmd.hasOption('h')) {
			
			System.out.println("usage: tp.pr5.Main [-g <game>] [-h] [-x <columnNumber>] [-y <rowNumber>]\n"
				+ " -g,--game <game>           Tipo de juego (c4, co, gr, rv). Por defecto, c4.\n"
				+ " -h,--help                  Muestra esta ayuda.\n"
				+ " -u,--ui <tipo>             Tipo de interfaz (console, window). Por\n"
				+ " defecto, console."
				+ " -x,--tamX <columnNumber>   Número de columnas del tablero (sólo para\n"
				+ "                            Gravity). Por defecto, 10.\n"
				+ " -y,--tamY <rowNumber>      Número de filas del tablero (sólo para\n"
				+ "                            Gravity). Por defecto, 10.");					                            
				     
			// Al seleccionar la ayuda la aplicación termina sin errores
			System.exit(0);
		}
		else {
			
			if(cmdArg.equals("tipoJuego") && cmd.hasOption('g'))
				argFinal = cmd.getOptionValue('g');
			if(cmdArg.equals("tipoInterfaz") && cmd.hasOption('u'))
				argFinal = cmd.getOptionValue('u');
		}
		
		return argFinal;
	}

	/**
	 * Método genérico para mostrar los mensaje de error 
	 * y terminar la aplicación con código de error 1.
	 * 
	 * @param msg - Parámetro con el mensaje que se desea mostrar
	 */
	private static void mostrarMensaje(String msg) {
		
		System.err.println("Uso incorrecto: " + msg +"\n"
				+ "Use -h|--help para más detalles.");
		
		System.exit(1);
	}
	
	/**
	 * Método genérico para generar el mensaje de error a partir de los argumentos
	 * 
	 * @param args - Cadena con los argumentos
	 * 
	 * @param ini - Punto de inicio de los argumentos para generar el mensaje
	 * 
	 * @return Devuelve el mensaje de error generador
	 */
	private static String generarMensajeError(String[] args) {
		
		String mensaje = "";
		
		for(int i = 0; i < args.length; i++)
			mensaje = mensaje + " " + args[i];
		
		return mensaje;
	}
}
