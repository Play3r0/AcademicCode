package tp.pr2.logica;

/**
 * Implementaci�n de las reglas del Conecta 4. 
 * Se deben implementar todos los m�todos del interfaz, adem�s del constructor.
 * 
 * @author usuario_local
 *
 */
public class ReglasConecta4 implements ReglasJuego
{
	private Tablero tablero;
	
	final int MAXANCHO = 7;	// COLUMNAS
	final int MAXALTO = 6;	// FILAS
	
	final static int CONECTA = 4;
	
	/**
	 * Constructor de la clase, sin par�metros.
	 */
	public ReglasConecta4()
	{
		
	}

	@Override
	public Tablero iniciaTablero()
	{
		this.tablero = new Tablero(MAXANCHO, MAXALTO);
		
		return tablero;
	}

	@Override
	public Ficha jugadorInicial()
	{
		return Ficha.BLANCA;
	}

	// POR QUE PASAR TABLERO SI NO HACE FALTA PARA CAMBIAR EL TURNO??�?�??�?�?��????????????????????????????????
	// De momento no se utiliza (m�todo gen�rico, futura implementaci�n)
	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t)
	{
		return (ultimoEnPoner == Ficha.BLANCA)? Ficha.NEGRA : Ficha.BLANCA;
	}

	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t)
	{
		Ficha ganador;
		
		if(compruebaGanador(t, ultimoMovimiento.getJugador()))
			ganador = ultimoMovimiento.getJugador();
		else ganador = Ficha.VACIA;
		
		return ganador;
	}
	
	private boolean compruebaGanador(Tablero t, Ficha color)
	{
		boolean ganador = false;
		
		int fila = 0, col = 0;
		
		while(fila <= t.getAlto() && ganador == false)
		{
			col = 0;
			while(col <= t.getAncho() && ganador == false)
			{
				ganador = compruebaFila(fila, col, t, color) || compruebaColumna(fila, col, t, color) || compruebaDiagonal(fila, col, t, color);
				
				col++;
			}
			
			fila++;
		}
		
		return ganador;
	}
	
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
