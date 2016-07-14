package tp.pr2.logica;


/**
 * @author Sergio
 *
 * Almacena la información de un tablero rectangular. 
 * El tamaño se fija en el momento de la construcción, 
 * y contiene métodos para acceder a la información de cada celda 
 * y para colocar fichas.
 */
public class Tablero
{
	
	private Ficha [][] tablero;
	private int ancho; // columnas
	private int alto; // filas
	
	/**
	 * Construye un tablero vacío.
	 * 
	 * @param ancho - Tamaño en la coordenada x (o número de columnas).
	 * 
	 * @param alto - Tamaño en la coordenada y y (o número de filas).
	 */
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
	
	/**
	 * Método para obtener el ancho del tablero.
	 * 
	 * @return Número de columnas del tablero.
	 */
	public int getAncho()
	{
		return this.ancho;
	}
	
	/**
	 * Método para obtener el alto del tablero.
	 * 
	 * @return Número de filas del tablero.
	 */
	public int getAlto()
	{
		return this.alto;
	}
	
	/**
	 * Método para acceder a la información de una casilla o celda concreta.
	 * 
	 * @param col - Número de columna (1..ancho)
	 * 
	 * @param fila - Número de fila (1..alto)
	 * 
	 * @return Información de la casilla. Si la casilla no es válida, 
	 * devuelve Ficha.VACIA
	 */
	public Ficha getCasilla(int col, int fila)
	{
		Ficha result = Ficha.VACIA;
		
		if(col > 0 && col <= ancho && fila > 0 && fila <= alto)
			result = this.tablero[col - 1][fila - 1];
					
		return result;
	}
	
	/**
	 * Permite especificar el nuevo contenido de una casilla. 
	 * También puede utilizarse para quitar una ficha
	 * 
	 * @param col - Número de columna (1..ancho)
	 * @param fila - Número de fila (1..alto)
	 * @param color - Color de la casilla. Si se indica Ficha.VACIA, la celda quedará sin ficha.
	 */
	public void setCasilla(int col, int fila, Ficha color)
	{
		if(col > 0 && col <= ancho && fila > 0 && fila <= alto)
			tablero[col - 1][fila - 1] = color;
	}
	
	/**
	 * Reiniciar tablero (inicilizar)
	 */
	private void reset()
	{		
		// Recorremos el tablero entero para inciar los valeros a "VACIA"
		for(int i = 0; i < ancho; i++)
		{
			for(int j = 0; j <= alto; j++)
				tablero[i][j] = Ficha.VACIA;
		}
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
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
