package tp.pr5.modelo;

import java.util.Vector;

/**
 * @author Sergio
 * 
 * Clase que implementa el movimiento para el juego del Gravity. 
 * Se deben implementar los métodos abstractos de la clase padre.
 *
 */
public class MovimientoGravity extends Movimiento {

	private int fila;
	
	/**
	 * Constructor del objeto.
	 * 
	 * @param _donde - Columna en la que se colocará la ficha.
	 * @param fila - Fila en la que se colocará la ficha.
	 * @param _color - Color de la ficha que se pondrá (o jugador que pone).
	 * @throws MovimientoInvalido 
	 */
	public MovimientoGravity(int donde, int fila, Ficha color) {
		super(donde, color);

		this.fila = fila;
	}

	/* (non-Javadoc)
	 * @see tp.pr3.logica.Movimiento#ejecutaMovimiento(tp.pr3.logica.Tablero)
	 */
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		
		// Si la fila y la columna del movimiento están dentro de los límites, comprobamos
		if(this._donde > 0 && this._donde <= tab.getAncho() 
				&& this.fila > 0 && this.fila <= tab.getAlto()) {
			
			// Si la posición está libre colocamos la ficha en el tablero según la gravedad
			if(tab.getCasilla(this._donde, this.fila) == Ficha.VACIA) {

				// Actualiza los atributos con la posición de la ficha según la gravedad
				buscarPosGravedad(tab);
				// Colocamos la ficha en el tablero con los atributos actualizados
				tab.setCasilla(this._donde, this.fila, this._color);	
			}
			else throw new MovimientoInvalido("Casilla ocupada.");
		}
		else throw new MovimientoInvalido("Posición incorrecta.");
	}

	/* (non-Javadoc)
	 * @see tp.pr3.logica.Movimiento#undo(tp.pr3.logica.Tablero)
	 */
	@Override
	public void undo(Tablero tab) {
		tab.setCasilla(this._donde, this.fila, Ficha.VACIA);
	}
	
	/**
	 * Método encargado de buscar la posición de la ficha según la gravedad
	 * 
	 * @param tab - Tablero para generar la posición de la ficha
	 */
	private void buscarPosGravedad(Tablero tab) {

		int movX = 0, movY = 0, siguienteX, siguienteY;
		
		// Calculamos el centro del tablero para saber en que dirección puede ser atraida la ficha
		double centroX = (tab.getAncho() + 1) / 2; 
		double centroY = (tab.getAlto() + 1) / 2;
		
		// En caso de que la ficha esté en el centro, no se tiene que calcular nada
		boolean centro = tab.getAncho()%2 == 1 && tab.getAlto()%2 == 1 && this._donde == centroX && this.fila == centroY;
		
		// Si no está en el centro y está dentro de los rangos, aplicamos la gravedad
		if(!centro && this._donde != 1 && this._donde != tab.getAncho() 
				&& this.fila != 1 && this.fila != tab.getAlto())
		{
			// 0 Norte - 1 Sur - 2 Oeste - 3 Este
			Vector<Integer> direccion = new Vector<Integer>();
			
			// Añadimos las distancias de cada eje en un vector
			direccion.add(this.fila - 1);
			direccion.add(tab.getAlto() - this.fila);
			direccion.add(this._donde - 1);
			direccion.add(tab.getAncho() - this._donde);
			
			//distH es la distancia al lado mas cercano y dirH es -1 si es al izq o 1 al derecho
			int distH = tab.getAncho(); int dirH = 0;
			
			//Cogemos la distancia menor
			if(direccion.get(2) > direccion.get(3)) {
				
				distH = direccion.get(3);
				
				// Y seleccionamos uno de los lados
				dirH = 1;
			}
			else if(direccion.get(2) < direccion.get(3)) {
				
				distH = direccion.get(2);
				dirH = -1;
			}
				
			int distV = tab.getAlto(); int dirV = 0;
			if(direccion.get(0) > direccion.get(1)) {
				
				distV = direccion.get(1);
				dirV = 1;
			}
			else if(direccion.get(0) < direccion.get(1)){
				distV = direccion.get(0);
				dirV = -1;
			}
			
			// La ficha puede estar más cerca del norte que del este
			if(distV < distH)
				// Marcamos si la ficha se mueve al lado más cercano seleccionado anteriormente pero no en horizontal
				movY = dirV;
			else if(distV > distH)
				movX = dirH;
			
			// Si la distancia en horizontal es la misma que en vertical
			else {
				movX = dirH;
				movY = dirV;
			}
			
			// Calculamos el movimiento que tiene que seguir la ficha por la gravedad
			siguienteX = this._donde + movX;
			siguienteY = this.fila + movY;
			
			// Mientra la ficha pueda avanzar por el tablero aplicamos el siguiente paso
			while (tab.getCasilla(siguienteX, siguienteY) == Ficha.VACIA && siguienteX >= 1  
					&& siguienteY >= 1 && siguienteX <= tab.getAncho() && siguienteY <= tab.getAlto()) { 
				
				this._donde = siguienteX;
				this.fila =	siguienteY;
				
				siguienteX = this._donde + movX;
				siguienteY = this.fila + movY;	
			}
		}
	}
}
