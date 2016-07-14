package tp.pr2.logica;

public class Tablero {
	
	private Ficha [][] tablero;
	private int ancho; // columnas
	private int alto; // filas
	
	// Constructor
	public Tablero(int ancho, int alto)
	{
		if(ancho < 1 || alto < 1)
		{
			ancho = 1;
			alto = 1;
		}
		this.tablero = new Ficha[ancho + 1][alto + 1];
		this.ancho = ancho;
		this.alto = alto;
		reset();
	}
	
	// Devuelve el ancho del tablero
	public int getAncho()
	{
		return this.ancho;
	}
	
	// Devuelve el alto del tablero
	public int getAlto()
	{
		return this.alto;
	}
	
	// Devuelve la ficha de una posicion
	public Ficha getCasilla(int col, int fila)
	{
		Ficha result = Ficha.VACIA;
		
		if(col > 0 && col <= ancho && fila > 0 && fila <= alto)
			result = this.tablero[col - 1][fila - 1];
					
		return result;
	}
	
	// Colocar ficha en posicion
	public void setCasilla(int col, int fila, Ficha ficha)
	{
		if(col > 0 && col <= ancho && fila > 0 && fila <= alto)
			tablero[col - 1][fila - 1] = ficha;
	}
	
	// Reiniciar tablero (inicilizar)
	public void reset()
	{		
		// Recorremos el tablero entero para inciar los valeros a "VACIA"
		for(int i = 0; i < ancho; i++)
		{
			for(int j = 0; j <= alto; j++)
				tablero[i][j] = Ficha.VACIA;
		}
	}	
	
	public String toString()
	{
		String tablero = "";
		
		for(int i = 0; i < alto; i++)
		{
			for(int j = 0; j < ancho; j++)
			{
				if(j == 0)
					tablero = tablero + "|";
				
				if(this.tablero[j][i] == Ficha.VACIA)
					tablero = tablero + " ";
				else
				{
					if(this.tablero[j][i] == Ficha.BLANCA)
						tablero = tablero + "O";
					else
					{
						if(this.tablero[j][i] == Ficha.NEGRA)
							tablero = tablero + "X";
					}
				}
			
				if(j == ancho - 1)
					tablero = tablero + "|";
			}
			
			tablero = tablero + "\n";
		}
		
		// Linea final " +-------+ "
		tablero = tablero + "+";
		for(int i = 0; i < ancho; i++)
			tablero = tablero + "-";
		tablero = tablero + "+";
		
		tablero = tablero + "\n";
		
		// Linea final " 1234567 "
		tablero = tablero + " ";
		for(int i = 1; i < ancho + 1; i++)
			tablero = tablero + i;
		
		tablero = tablero + "\n";
		
		return tablero;
	}
}
