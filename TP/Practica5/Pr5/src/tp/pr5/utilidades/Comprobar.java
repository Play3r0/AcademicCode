package tp.pr5.utilidades;

import java.util.ArrayList;

import tp.pr5.modelo.Ficha;
import tp.pr5.modelo.Tablero;
import tp.pr5.modelo.TableroInmutable;

/**
 * @author Sergio
 * 
 * Clase. Tipo de Datos Abstractos
 *
 */
public class Comprobar
{
	private static int CONECTA;
	
	/** 
	 * Comprueba si un jugador ha ganado la partida
	 * 
	 * @param t - Tablero sobre el que se va a hacer la comprobación
	 * 
	 * @param _color - Color de jugador para identificarle
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
	 * @param fila - Fila _donde vamos a realizar la comprobación
	 * 
	 * @param col - Columna _donde vamos a realizar la comprobación
	 * 
	 * @param t - Tablero sobre el que se va a hacer la comprobación
	 * 
	 * @param _color - Color de jugador para identificarle
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
	 * @param fila - Fila _donde vamos a realizar la comprobación
	 * 
	 * @param col - Columna _donde vamos a realizar la comprobación
	 * 
	 * @param t - Tablero sobre el que se va a hacer la comprobación
	 * 
	 * @param _color - Color de jugador para identificarle
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
	 * @param fila - Fila _donde vamos a realizar la comprobación
	 * 
	 * @param col - Columna _donde vamos a realizar la comprobación
	 * 
	 * @param t - Tablero sobre el que se va a hacer la comprobación
	 * 
	 * @param _color - Color de jugador para identificarle
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
	 * @param fila - Fila _donde vamos a realizar la comprobación
	 * 
	 * @param col - Columna _donde vamos a realizar la comprobación
	 * 
	 * @param t - Tablero sobre el que se va a hacer la comprobación
	 * 
	 * @param _color - Color de jugador para identificarle
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
	
	/**
	 * Recoge todos los movimientos posibles para un jugador.
	 * 
	 * @param tab - Tablero donde se comprueba si quedan movimiento
	 * @param color - Color del jugador que se quiere comprobar
	 * @return Devuelve una lista con los movimientos disponibles.
	 */
	public static ArrayList<ArrayList<Integer>> reversiMovsValidos(TableroInmutable tab, Ficha color) {
		
		// Es un monton de código de mierda. Se puede hacer más corto y más fácil
		ArrayList<ArrayList<Integer>> movsValidos = new ArrayList<ArrayList<Integer>>();
		ArrayList<Integer> listaAux;
		
		Ficha colorContrario = Ficha.BLANCA;
		
		if(color == Ficha.BLANCA)
			colorContrario = Ficha.NEGRA;
		
		for(int c = 1; c < tab.getAncho() + 1; c++) {
			for(int f = 1; f < tab.getAlto() + 1; f++) {
				
				if(tab.getCasilla(c, f) == Ficha.VACIA) {
					
					int col = c;
					int fila =  f;
					int recorrido = 0;
					
					// Comprobamos norte
					fila--;	// Cambiamos el valor para no empezar desde nuestra casilla
					while(fila > 0 && tab.getCasilla(col, fila) == colorContrario)
						fila--;
					
					if(tab.getCasilla(col, fila) != Ficha.VACIA && tab.getCasilla(col, fila) == color) {
					
						recorrido = f - fila - 1;
						
						listaAux = new ArrayList<Integer>();
						if(recorrido > 0 && fila + 1 < f) {
							
							listaAux.add(c);
							listaAux.add(f);
							movsValidos.add(listaAux);
						}
					}
					
					col = c;
					fila =  f;
					
					// Comprobamos noreste
					col++;	// Cambiamos el valor para no empezar desde nuestra casilla
					fila--;
					while(col < tab.getAncho() && fila > 0 && tab.getCasilla(col, fila) == colorContrario) {
						
						col++;
						fila--;
					}
					
					if(tab.getCasilla(col, fila) != Ficha.VACIA && tab.getCasilla(col, fila) == color) {

						recorrido = col - c - 1;
						fila = f - recorrido;
						
						listaAux = new ArrayList<Integer>();
						if(recorrido > 0 && c + 1 < col) {
							
							listaAux.add(c);
							listaAux.add(f);
							movsValidos.add(listaAux);
						}
					}
					
					col = c;
					fila =  f;
					
					// Comprobamos este
					col++;	// Cambiamos el valor para no empezar desde nuestra casilla
					while(col < tab.getAncho() && tab.getCasilla(col, fila) == colorContrario)
						col++;
					
					if(tab.getCasilla(col, fila) != Ficha.VACIA && tab.getCasilla(col, fila) == color) {

						recorrido = col - c - 1;
						
						listaAux = new ArrayList<Integer>();
						if(recorrido > 0 && c + 1 < col) {
							
							listaAux.add(c);
							listaAux.add(f);
							movsValidos.add(listaAux);
						}
					}
					
					col = c;
					fila =  f;
					
					// Comprobamos sureste
					col++;	// Cambiamos el valor para no empezar desde nuestra casilla
					fila++;
					while(col < tab.getAncho() && fila < tab.getAlto() && tab.getCasilla(col, fila) == colorContrario) {
						
						col++;
						fila++;
					}
					
					if(tab.getCasilla(col, fila) != Ficha.VACIA && tab.getCasilla(col, fila) == color) {

						recorrido = col - c - 1;
						fila = f + recorrido;
						
						listaAux = new ArrayList<Integer>();
						if(recorrido > 0 && c + 1 < col) {
							
							listaAux.add(c);
							listaAux.add(f);
							movsValidos.add(listaAux);
						}
					}
					
					col = c;
					fila =  f;
					
					// Comprobamos sur
					fila++;	// Cambiamos el valor para no empezar desde nuestra casilla
					while(fila < tab.getAlto() && tab.getCasilla(col, fila) == colorContrario)
						fila++;
					
					if(tab.getCasilla(col, fila) != Ficha.VACIA && tab.getCasilla(col, fila) == color) {

						recorrido = fila - f - 1;
						
						listaAux = new ArrayList<Integer>();
						if(recorrido > 0 && f + 1 < fila) {
							
							listaAux.add(c);
							listaAux.add(f);
							movsValidos.add(listaAux);
						}
					}
					
					col = c;
					fila =  f;
					
					// Comprobamos suroeste
					col--;	// Cambiamos el valor para no empezar desde nuestra casilla
					fila++;
					while(col > 0 && fila < tab.getAlto() && tab.getCasilla(col, fila) == colorContrario) {
						
						col--;
						fila++;
					}
					
					if(tab.getCasilla(col, fila) != Ficha.VACIA && tab.getCasilla(col, fila) == color) {

						recorrido = c - col - 1;
						fila = f + recorrido;
						
						listaAux = new ArrayList<Integer>();
						if(recorrido > 0 && col + 1 < c) {
							
							listaAux.add(c);
							listaAux.add(f);
							movsValidos.add(listaAux);
						}
					}
					
					col = c;
					fila =  f;
					
					// Comprobamos oeste
					col--;	// Cambiamos el valor para no empezar desde nuestra casilla
					while(col > 0 && tab.getCasilla(col, fila) == colorContrario)
						col--;
					
					if(tab.getCasilla(col, fila) != Ficha.VACIA && tab.getCasilla(col, fila) == color) {

						recorrido = c - col - 1;
						
						listaAux = new ArrayList<Integer>();
						if(recorrido > 0 && col + 1 < c) {
							
							listaAux.add(c);
							listaAux.add(f);
							movsValidos.add(listaAux);
						}
					}
					
					col = c;
					fila =  f;
					
					// Comprobamos noroeste
					col--;	// Cambiamos el valor para no empezar desde nuestra casilla
					fila--;
					while(col > 0 && fila > 0 && tab.getCasilla(col, fila) == colorContrario) {
						
						col--;
						fila--;
					}
					
					if(tab.getCasilla(col, fila) != Ficha.VACIA && tab.getCasilla(col, fila) == color) {

						recorrido = c - col - 1;
						fila = f - recorrido;
						
						listaAux = new ArrayList<Integer>();
						if(recorrido > 0 && col + 1 < c) {
							
							listaAux.add(c);
							listaAux.add(f);
							movsValidos.add(listaAux);
						}
					}
				}
			}
		}
		
		return movsValidos;
	}

	/**
	 * Recorre el tablero para saber si todos 
	 * las casillas han sido rellenadas.
	 * 
	 * @param t - Tablero que se recorre
	 * @return Si el tablero ha sido completado
	 */
	public static boolean comprobarTablas(Tablero t) {
		
		boolean tablas = true;
		
		// Recorremos el tablero para saber si está completo
		for(int c = 1; c < t.getAncho() + 1 && tablas == true; c++) {
			for(int f = 1; f < t.getAlto() + 1 && tablas == true; f++) {
				if(t.getCasilla(c, f) == Ficha.VACIA)
					tablas = false;
			}
		}
		
		return tablas;
	}
}
