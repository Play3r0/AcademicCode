package tp.pr3.utilidades;

import java.util.Random;

/**
 * @author Sergio
 *
 * Clase encargada de generar números aleatorios
 * para los jugadores aleatorios de la partida.
 */
public class PosicionAleatoria {
	
	static int posInicio = 1;

	/**
	 * Calcula el valor de una fila o de una columna mediante unos rangos.
	 * 
	 * @param min - Posición mínima
	 * 
	 * @param max - Posición máxima
	 * 
	 * @return El valor aleatorio obtenido entre los rangos indicados
	 */
	public static int numeroAleatorio(int min, int max)
	{
		Random rand = new Random();
		
		int num = rand.nextInt((max - min) + 1) + min;
		
		return num;
	}
	
	/**
	 * Calcula números aleatorios mediante un tamaño máximo
	 * 
	 * @param tam - Tamaño máximo para generar el número
	 * 
	 * @return Devuelve el valor aleatorio generado
	 */
	public static int posAleatoria(int tam)
	{
		int num = numeroAleatorio(posInicio, tam);
		
		return num;
	}
}
