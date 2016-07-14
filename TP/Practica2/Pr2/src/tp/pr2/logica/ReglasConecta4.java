package tp.pr2.logica;

/**
 * @author Sergio
 *
 * Implementación de las reglas del Conecta 4. Se deben implementar 
 * todos los métodos del interfaz, además del constructor.
 */
public class ReglasConecta4 implements ReglasJuego
{
	private Tablero tablero;
	
	final int MAXANCHO = 7;	// COLUMNAS
	final int MAXALTO = 6;	// FILAS
	
	final static int CONECTA = 4;
	
	/**
	 * Constructor de la clase, sin parámetros
	 */
	public ReglasConecta4()
	{
		
	}

	/* (non-Javadoc)
	 * @see tp.pr2.logica.ReglasJuego#iniciaTablero()
	 */
	@Override
	public Tablero iniciaTablero()
	{
		this.tablero = new Tablero(MAXANCHO, MAXALTO);
		
		return tablero;
	}

	/* (non-Javadoc)
	 * @see tp.pr2.logica.ReglasJuego#jugadorInicial()
	 */
	@Override
	public Ficha jugadorInicial()
	{
		return Ficha.BLANCA;
	}

	// POR QUE PASAR TABLERO SI NO HACE FALTA PARA CAMBIAR EL TURNO??!?!??!?!?!!????????????????????????????????
	// De momento no se utiliza (método genérico, futura implementación)
	/* (non-Javadoc)
	 * @see tp.pr2.logica.ReglasJuego#siguienteTurno(tp.pr2.logica.Ficha, tp.pr2.logica.Tablero)
	 */
	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t)
	{
		return (ultimoEnPoner == Ficha.BLANCA)? Ficha.NEGRA : Ficha.BLANCA;
	}

	/* (non-Javadoc)
	 * @see tp.pr2.logica.ReglasJuego#hayGanador(tp.pr2.logica.Movimiento, tp.pr2.logica.Tablero)
	 */
	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t)
	{
		Ficha ganador;
		
		if(compruebaGanador(t, ultimoMovimiento.getJugador()))
			ganador = ultimoMovimiento.getJugador();
		else ganador = Ficha.VACIA;
		
		return ganador;
	}

	/**
	 * Comprueba si un jugador ha ganado la partida
	 * 
	 * @param t - Tablero sobre el que se va a hacer la comprobación
	 * 
	 * @param color - Color de jugador para identificarle
	 * 
	 * @return true si el jugador ha ganado, false si no ha conseguido hacer 4 en raya
	 */
	private boolean compruebaGanador(Tablero t, Ficha color)
	{
boolean ganador = false;
		
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
	 * "Se podría utilizar un método genérico para la detección de 4 en raya en cualquier dirección"
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
	private boolean comprobarTodo(int fila, int col, Tablero t, Ficha color)
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
	private boolean compruebaFila(int fila, int col, Tablero tab, Ficha color)
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
		
//		if(cont == CONECTA)
//			terminada = true;
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
	private boolean compruebaColumna(int fila, int col, Tablero tab, Ficha color)
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
	private boolean compruebaDiagonal(int fila, int col, Tablero tab, Ficha color)
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

	/* (non-Javadoc)
	 * @see tp.pr2.logica.ReglasJuego#tablas(tp.pr2.logica.Ficha, tp.pr2.logica.Tablero)
	 */
	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t)
	{
		boolean tablas = true;
		
		int col = 1;
		
		while(col <= t.getAncho() && tablas != false)
		{
			if(t.getCasilla(col, 1) == Ficha.VACIA)
				tablas = false;
			
			col++;
		}
		
		return tablas;
	}

}
