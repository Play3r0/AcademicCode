package tp.pr2.control;

import java.util.Scanner;

import tp.pr2.logica.Ficha;
import tp.pr2.logica.Movimiento;
import tp.pr2.logica.MovimientoComplica;
import tp.pr2.logica.MovimientoConecta4;
import tp.pr2.logica.Partida;
import tp.pr2.logica.ReglasComplica;
import tp.pr2.logica.ReglasConecta4;

public class Controlador {

	private Partida p;
	private Scanner in;
	
	public Controlador(Partida p, java.util.Scanner in)
	{
		this.p = p;
		this.in = in;
	}
	
	public void run()
	{
		String comando = "";
		Movimiento mov = new MovimientoConecta4(-1, null);
		
		do
		{
			System.out.println(p.toString().toString());
			if(p.getTurno() == Ficha.BLANCA)
				System.out.println("Juegan blancas");
			else System.out.println("Juegan negras");
			System.out.print("Qué quieres hacer? ");
			comando = in.nextLine();
			comando = comando.toLowerCase();
			
			switch(comando)
			{
				case "deshacer": 
					
					if(!p.undo())
						System.err.println("Imposible deshacer.");
					
					break;
				
				case "reiniciar":
					
					if(mov.getClass() == MovimientoConecta4.class)
						p.reset(new ReglasConecta4());
					else
					{
						if(mov.getClass() == MovimientoComplica.class)
							p.reset(new ReglasComplica());
						else p = null;
					}
					
					System.out.println("Partida reiniciada.");
					
					break;
				
				case "poner":
					
					System.out.print("Introduce la columna: ");
					comando = in.nextLine();
					
					int col = Integer.valueOf(comando.replaceAll("\\s",""));
					
					if(mov.getClass() == MovimientoConecta4.class)
						mov = new MovimientoConecta4(col, p.getTurno());
					else
					{
						if(mov.getClass() == MovimientoComplica.class)
							mov = new MovimientoComplica(col, p.getTurno());
						else mov = null;
					}
						
					if(!p.ejecutaMovimiento(mov))
						System.err.println("Movimiento incorrecto");
					else
					{
						// Si devuelve true, la partida ha terminado
						if(p.isTerminada())
						{
							System.out.println(p.toString().toString());
							
							if(p.getGanador() == Ficha.BLANCA)
								System.out.println("Ganan las blancas");
							else
							{
								if(p.getGanador() == Ficha.NEGRA)
									System.out.println("Ganan las negras");
								else System.out.println("Partida terminada en tablas.");
							}
							
							comando = "salir";
						}
					}
						
					break;
					
				case "jugar co":
					
					p = new Partida(new ReglasComplica());
					
					mov = new MovimientoComplica(-1, null);
					
					System.out.println("Partida reiniciada.");
					
					break;
					
				case "jugar c4":
					
					p = new Partida(new ReglasConecta4());
					
					mov = new MovimientoConecta4(-1, null);
					
					System.out.println("Partida reiniciada.");
					
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
