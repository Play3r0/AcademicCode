package tp.pr1.control;

import java.util.Scanner;

import tp.pr1.logica.Ficha;
import tp.pr1.logica.Partida;

public class Controlador {

	private Partida partida;
	private Scanner in;
	
	public Controlador(Partida partida)
	{
		this.partida = partida;
		this.in = new Scanner(System.in);
	}
	
	public void run()
	{
		String comando = "";
		
		do
		{
			System.out.println(partida.toString().toString());
			if(partida.getTurno() == Ficha.BLANCA)
				System.out.println("Juegan blancas");
			else System.out.println("Juegan negras");
			System.out.print("Que quieres hacer? ");
			comando = in.nextLine();
			comando = comando.toLowerCase();
			
			switch(comando)
			{
				case "deshacer": 
					
					if(!partida.undo())
						System.err.println("Imposible deshacer.");
					
					break;
				
				case "reiniciar":
					
					partida = new Partida();
					System.out.println("Partida reiniciada.");
					
					break;
				
				case "poner":
					
					System.out.print("Introduce la columna: ");
					comando = in.nextLine();
					
					int col = Integer.valueOf(comando);
					
					if(!partida.ejecutaMovimiento(partida.getTurno(), col))
						System.err.println("Movimiento incorrecto");
					else
					{
						// Si devuelve true, la partida ha terminado
						if(partida.isTerminada())
						{
							System.out.println(partida.toString().toString());
							
							if(partida.getGanador() == Ficha.BLANCA)
								System.out.println("Ganan las blancas");
							else
							{
								if(partida.getGanador() == Ficha.NEGRA)
									System.out.println("Ganan las negras");
								else System.out.println("TABLAS!");
							}
							
							comando = "salir";
						}
					}
						
					break;
					
				case "salir":
					
					break;
					
				default:
					
					System.err.println("No te entiendo.");
					
					break;
			}
			
		}while(!comando.equals("salir"));
	}
}
