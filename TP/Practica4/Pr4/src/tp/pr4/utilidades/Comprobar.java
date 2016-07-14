package tp.pr4.utilidades;

import tp.pr4.modelo.Ficha;
import tp.pr4.modelo.Tablero;

/**
 * @author Sergio
 * 
 * Clase. Tipo de Datos Abstractos
 *
 */
public class Comprobar
{
	static int CONECTA;
	
	/** 
	 * Comprueba si un jugador ha ganado la partida
	 * 
	 * @param t - Tablero sobre el que se va a hacer la comprobación
	 * 
	 * @param color - Color de jugador para identificarle
	 * 
	 * @param conecta - número de fichas que hay que alinear para ganar
	 * 
	 * @return true si el jugador ha ganado, false si no ha conseguido hacer 4 en raya
	 */
	public static boolean compruebaGanador(Tablero t, Ficha color, int conecta)
	{
		boolean ganador = false;
		
		CONECTA = conecta;
		
		int fila = 0, col = 0;
		
		while(fila <= t.getAlto() && ganador == false)
		{
			col = 0;
			while(col <= t.getAncho() && ganador == false)
			{
				ganador = comprobarTodo(fila, col, t, color);
				
				col++;
			}
			
			fila++;
		}
			
		return ganador;
	}
	
	/** 
	 * Método genérico para comprobar todos los posibles 4 en raya?
	 * 
	 * @param fila - Fila donde vamos a realizar la comprobación
	 * 
	 * @param col - Columna donde vamos a realizar la comprobación
	 * 
	 * @param t - Tablero sobre el que se va a hacer la comprobación
	 * 
	 * @param color - Color de jugador para identificarle
	 * 
	 * @return true si el jugador ha ganado, false si no ha conseguido hacer 4 en raya
	 */
	public static boolean comprobarTodo(int fila, int col, Tablero t, Ficha color)
	{
		return compruebaFila(fila, col, t, color) || compruebaColumna(fila, col, t, color) || compruebaDiagonal(fila, col, t, color);
	}
	
	/**
	 * Comprueba si el jugado ha hecho 4 en raya en una fila
	 * 
	 * @param fila - Fila donde vamos a realizar la comprobación
	 * 
	 * @param col - Columna donde vamos a realizar la comprobación
	 * 
	 * @param t - Tablero sobre el que se va a hacer la comprobación
	 * 
	 * @param color - Color de jugador para identificarle
	 * 
	 * @return true si el jugador ha ganado, false si no ha conseguido hacer 4 en raya
	 */
	private static boolean compruebaFila(int fila, int col, Tablero tab, Ficha color)
	{
		boolean ganador = false;
		
		int cont = 0, rangoMin = 0, rangoMax = tab.getAncho();
		
		// Rango minimo para no salirse del tab
		rangoMin = Math.max(rangoMin, col - (CONECTA - 1));
		// Rango maximo para no salirse del tab
		rangoMax = Math.min(rangoMax, col + (CONECTA - 1));
		
		while(rangoMin <= rangoMax && cont != CONECTA)
		{
			if(tab.getCasilla(rangoMin, fila) == color)
				cont++;
			else cont = 0;
			
			rangoMin++;
		}
		
//				if(cont == CONECTA)
//					terminada = true;
		ganador = (cont == CONECTA)? true : false ;
		
		return ganador;
	}

	/**
	 * Comprueba si el jugado ha hecho 4 en raya en una columna
	 * 
	 * @param fila - Fila donde vamos a realizar la comprobación
	 * 
	 * @param col - Columna donde vamos a realizar la comprobación
	 * 
	 * @param t - Tablero sobre el que se va a hacer la comprobación
	 * 
	 * @param color - Color de jugador para identificarle
	 * 
	 * @return true si el jugador ha ganado, false si no ha conseguido hacer 4 en raya
	 */
	private static boolean compruebaColumna(int fila, int col, Tablero tab, Ficha color)
	{
		boolean ganador = false;
		
		int cont = 0, rangoMin = 0, rangoMax = tab.getAlto();
		
		rangoMin = Math.max(rangoMin, fila - (CONECTA - 1));
		rangoMax = Math.min(rangoMax, fila + (CONECTA - 1));
		
		while(rangoMin <= rangoMax && cont != CONECTA)
		{
			if(tab.getCasilla(col, rangoMin) == color)
				cont++;
			else cont = 0;
			
			rangoMin++;
		}
		
		if(cont == CONECTA)
			ganador = true;

		return ganador;
	}

	/**
	 * Comprueba si el jugado ha hecho 4 en raya en una diagonal
	 * 
	 * @param fila - Fila donde vamos a realizar la comprobación
	 * 
	 * @param col - Columna donde vamos a realizar la comprobación
	 * 
	 * @param t - Tablero sobre el que se va a hacer la comprobación
	 * 
	 * @param color - Color de jugador para identificarle
	 * 
	 * @return true si el jugador ha ganado, false si no ha conseguido hacer 4 en raya
	 */
	private static boolean compruebaDiagonal(int fila, int col, Tablero tab, Ficha color)
	{
		boolean ganador = false;
		
		int cont = 0, rangoMinCol = col, rangoMinFila = fila, rangoMaxCol = col, rangoMaxFila = fila;

		// Derecha a izquierda - Arriba a abajo
		while((rangoMinCol > 0 && rangoMaxFila < tab.getAlto()) || (rangoMaxCol < tab.getAncho() && rangoMinFila > 0) && cont < 4)
		{
			if(rangoMinCol > 0 && rangoMaxFila < tab.getAlto())
			{
				rangoMinCol--;
				rangoMaxFila++;
			}
			
			if(rangoMaxCol < tab.getAncho() && rangoMinFila > 0)
			{
				rangoMaxCol++;
				rangoMinFila--;
			}
			
			cont++;
		}
		
		cont = 0;
		
		// Comprobamos si el jugador ha hecho 4 en raya dentro del rango
		while(rangoMaxCol >= rangoMinCol && rangoMinFila <= rangoMaxFila && cont != CONECTA)
		{
			if(tab.getCasilla(rangoMaxCol, rangoMinFila) == color)
				cont++;
			else cont = 0;
			
			rangoMaxCol--;
			rangoMinFila++;
		}
			
		if(cont == CONECTA)
			ganador = true;
		else
		{
			cont = 0;
			rangoMinCol = col;
			rangoMinFila = fila;
			rangoMaxCol = col;
			rangoMaxFila = fila;
			
			// Izquierda a derecha - Arriba a abajo
			while((rangoMinCol > 0 && rangoMinFila > 0) || (rangoMaxCol < tab.getAncho() && rangoMaxFila < tab.getAlto()) && cont < 4)
			{
				if(rangoMinCol > 0 && rangoMinFila > 0)
				{
					rangoMinCol--;
					rangoMinFila--;
				}
				
				if(rangoMaxCol < tab.getAncho() && rangoMaxFila < tab.getAlto())
				{
					rangoMaxCol++;
					rangoMaxFila++;
				}
				
				cont++;
			}
			
			cont = 0;
			
			// Comprobamos si el jugador ha hecho 4 en raya dentro del rango
			while(rangoMinCol <= rangoMaxCol && rangoMinFila <= rangoMaxFila && cont != CONECTA)
			{
				if(tab.getCasilla(rangoMinCol, rangoMinFila) == color)
					cont++;
				else cont = 0;
				
				rangoMinCol++;
				rangoMinFila++;
			}
			
			if(cont == CONECTA)
				ganador = true;
		}
		
		return ganador;
	}
}
