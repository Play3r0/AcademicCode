package tp.pr5.modelo;

/**
 * @author Sergio
 * 
 * Interfaz con el que podemos proporcionar un tablero de solo lectura
 */
public interface TableroInmutable {
	
	/**
	 * Método para obtener el alto del tablero.
	 * 
	 * @return Número de filas del tablero.
	 */
	int getAlto(); // Filas
	
	/**
	 * Método para obtener el ancho del tablero.
	 * 
	 * @return Número de columnas del tablero.
	 */
	int getAncho(); // Columnas
	
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
	Ficha getCasilla(int fila, int col);
}
