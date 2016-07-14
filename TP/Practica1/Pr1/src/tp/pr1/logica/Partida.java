package tp.pr1.logica;

public class Partida {

	private Tablero tablero;
	private Ficha turno;
	private boolean terminada;
	private Ficha ganador;
	private int[] undoStack;
	private int numUndo;
	private int numTurnos;
	private int maxTurnos;
	
	// Variable constante para fijar el número de turnos para deshacer
	final int MAXNUMUNDO = 10;
	
	final int MAXANCHO = 7;
	final int MAXALTO = 6;
	
	final int CONECTA = 4;
	
	private int posPrimera = 0; // Determina la primera posici�n del array
	
	public Partida()
	{
		// Provisional. Tam tablero y turnos
		this.tablero = new Tablero(MAXANCHO, MAXALTO);
		this.turno = Ficha.BLANCA;
		this.undoStack = new int[MAXNUMUNDO];
		for(int i = 0; i < MAXNUMUNDO; i++)
			undoStack[i] = -1;
		this.numUndo = 0;
		this.numTurnos = 0;
		this.maxTurnos = MAXANCHO * MAXALTO;
	}

	public boolean ejecutaMovimiento(Ficha color, int col)
	{
		boolean movimiento = false;
		
		// Si el turno es distinto devuelve false
		if(color == turno && (col > 0 && col <= MAXANCHO) && terminada == false)
		{
			int fila = tablero.filaVacia(col);
			
			if(fila != -1)
			{
				tablero.setCasilla(col, fila, color);
				numTurnos++;
				movimiento = true;
				
				if(numUndo == MAXNUMUNDO)
				{
					for(int i = 0; i < MAXNUMUNDO - 1; i++)
						undoStack[i] = undoStack[i + 1];
					
					undoStack[numUndo - 1] = col;
				}
				else
				{
					undoStack[numUndo] = col;
					numUndo++;
				}
			}
			
			//isTerminada?
			//---------
			this.terminada = compruebaFila(col, fila) || compruebaColumna(col, fila) || compruebaDiagonal(col, fila);
			
			if(terminada)
				ganador = turno;
			else
			{
				if(numTurnos == maxTurnos)
				{
					ganador = Ficha.VACIA;
					terminada = true;
				}
			}		
			//---------
			
			if(!isTerminada()) // Si se cumple alguna condicion para terminar la 
			{
				// Si la partida no ha terminado, cambiamos el turno
				if(turno == Ficha.BLANCA)
					turno = Ficha.NEGRA;
				else turno = Ficha.BLANCA;
			}
		}
			
		return movimiento;
	}
	
	public String toString() {
		return tablero.toString();
	}
	
	public Tablero getTablero()
	{
		return tablero;
	}

	public Ficha getTurno() {
		return turno;
	}

	public void setTurno(Ficha turno) {
		this.turno = turno;
	}

	public boolean isTerminada()
	{
		return terminada;
	}

	private boolean compruebaFila(int col, int fila)
	{
		int cont = 0, rangoMin = posPrimera, rangoMax = MAXANCHO;
		
		// Rango mínimo para no salirse del tablero
		rangoMin = Math.max(rangoMin, col - (CONECTA - 1));
		// Rango máximo para no salirse del tablero
		rangoMax = Math.min(rangoMax, col + (CONECTA - 1));
		
		while(rangoMin <= rangoMax && cont != CONECTA)
		{
			if(this.tablero.getCasilla(rangoMin, fila) == this.turno)
				cont++;
			else cont = 0;
			
			rangoMin++;
		}
		
//		if(cont == CONECTA)
//			terminada = true;
		terminada = (cont == CONECTA)? true : false ;
		
		return this.terminada;
	}

	private boolean compruebaColumna(int col, int fila)
	{
		int cont = 0, rangoMin = posPrimera, rangoMax = MAXALTO;
		
		rangoMin = Math.max(rangoMin, fila - (CONECTA - 1));
		rangoMax = Math.min(rangoMax, fila + (CONECTA - 1));
		
		while(rangoMin <= rangoMax && cont != CONECTA)
		{
			if(this.tablero.getCasilla(col, rangoMin) == this.turno)
				cont++;
			else cont = 0;
			
			rangoMin++;
		}
		
		if(cont == CONECTA)
			terminada = true;

		return this.terminada;
	}

	/**
	 * @param col
	 * @param fila
	 * @return
	 */
	private boolean compruebaDiagonal(int col, int fila)
	{
		int cont = 0, rangoMinCol = col, rangoMinFila = fila, rangoMaxCol = col, rangoMaxFila = fila;

		// Derecha a izquierda - Arriba a abajo
		while((rangoMinCol > posPrimera && rangoMaxFila < MAXALTO) || (rangoMaxCol < MAXANCHO && rangoMinFila > posPrimera) && cont < 4)
		{
			if(rangoMinCol > posPrimera && rangoMaxFila < MAXALTO)
			{
				rangoMinCol--;
				rangoMaxFila++;
			}
			
			if(rangoMaxCol < MAXANCHO && rangoMinFila > posPrimera)
			{
				rangoMaxCol++;
				rangoMinFila--;
			}
			
			cont++;
		}
		
		cont = 0;
		
		while(rangoMaxCol >= rangoMinCol && rangoMinFila <= rangoMaxFila && cont != CONECTA)
		{
			if(this.tablero.getCasilla(rangoMaxCol, rangoMinFila) == this.turno)
				cont++;
			else cont = 0;
			
			rangoMaxCol--;
			rangoMinFila++;
		}
			
		if(cont == CONECTA)
			terminada = true;
		else
		{
			cont = 0;
			rangoMinCol = col;
			rangoMinFila = fila;
			rangoMaxCol = col;
			rangoMaxFila = fila;
			
			// Izquierda a derecha - Arriba a abajo
			while((rangoMinCol > posPrimera && rangoMinFila > posPrimera) || (rangoMaxCol < MAXANCHO && rangoMaxFila < MAXALTO) && cont < 4)
			{
				if(rangoMinCol > posPrimera && rangoMinFila > posPrimera)
				{
					rangoMinCol--;
					rangoMinFila--;
				}
				
				if(rangoMaxCol < MAXANCHO && rangoMaxFila < MAXALTO)
				{
					rangoMaxCol++;
					rangoMaxFila++;
				}
				
				cont++;
			}
			
			cont = 0;
			
			while(rangoMinCol <= rangoMaxCol && rangoMinFila <= rangoMaxFila && cont != CONECTA)
			{
				if(this.tablero.getCasilla(rangoMinCol, rangoMinFila) == this.turno)
					cont++;
				else cont = 0;
				
				rangoMinCol++;
				rangoMinFila++;
			}
			
			if(cont == CONECTA)
				terminada = true;
		}
		
		/*
		rangoMinCol = Math.max(rangoMinCol, col - (CONECTA - 1));
		rangoMaxCol = Math.min(rangoMaxCol, col + (CONECTA - 1));
		
		rangoMinFila = Math.max(rangoMinFila, fila - (CONECTA - 1));
		rangoMaxFila = Math.min(rangoMaxFila, fila + (CONECTA - 1));
		
		auxMinCol = rangoMinCol;
		auxMinFila = rangoMinFila;
		
		if(auxMinCol == posPrimera || auxMinFila == posPrimera)
		{
			if(col / CONECTA != 1)
			{
				aux = auxMinCol - auxMinFila;
				
				if(aux > 0)
					auxMinCol = auxMinCol + aux;
				else
				{
					if(aux < 0)
						auxMinFila = auxMinFila - aux;
				}
			}
			else
			{
				if(fila / CONECTA != 1)
				{
					aux = auxMinCol - auxMinFila;
					if(aux > 0)
						auxMinCol = auxMinCol + aux;
					else
					{
						if(aux < 0)
							auxMinFila = auxMinFila - aux;
					}
				}
			}
		}
		
		while(auxMinCol <= rangoMaxCol && auxMinFila <= rangoMaxFila && cont != CONECTA)
		{
			if(this.tablero.getCasilla(auxMinCol, auxMinFila) == this.turno)
				cont++;
			else cont = 0;
			
			auxMinCol++;
			auxMinFila++;
		}
		
		if(cont == CONECTA)
			terminada = true;
		else
		{
			auxMaxCol = 0;
			
			auxMaxCol = rangoMaxCol;
			auxMinFila = rangoMinFila;
			
			cont = 0;
			
			while(auxMaxCol >= rangoMinCol && auxMinFila <= rangoMaxFila && cont != CONECTA)
			{
				if(this.tablero.getCasilla(auxMaxCol, auxMinFila) == this.turno)
					cont++;
				else cont = 0;
				
				auxMaxCol--;
				auxMinFila++;
			}
			
			if(cont == CONECTA)
				terminada = true;
		}
		*/
		
		return this.terminada;
	}

	public void setTerminada(boolean terminada) {
		this.terminada = terminada;
	}

	public Ficha getGanador() {
		return ganador;
	}
	
	// Reiniciar tablero (inicilizar)
	public void reset()
	{		
		tablero.reset();
		turno = Ficha.BLANCA;
		
		numUndo = 0;
	}

	public boolean undo() {
		
		boolean deshacer = false;
		
		if(numUndo > 0)
		{
			int fila = tablero.filaVacia(undoStack[numUndo - 1]);
			
			tablero.setCasilla(undoStack[numUndo - 1], fila + 1, Ficha.VACIA);
			undoStack[numUndo - 1] = -1;
			numUndo--;
			numTurnos--;
			
			if(turno == Ficha.BLANCA)
				turno = Ficha.NEGRA;
			else
			{
				if(turno == Ficha.NEGRA)
					turno = Ficha.BLANCA;
			}
						
			deshacer = true;
		}
		
		return deshacer;
	}

	public int getNumUndo() {
		return numUndo;
	}

	public void setNumUndo(int numUndo) {
		this.numUndo = numUndo;
	}
	
	public int getTurnos() {
		return maxTurnos;
	}
}
