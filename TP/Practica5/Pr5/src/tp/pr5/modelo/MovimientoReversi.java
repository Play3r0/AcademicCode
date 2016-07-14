package tp.pr5.modelo;

import java.util.ArrayList;

import tp.pr5.utilidades.Comprobar;

/**
 * @author Sergio
 * 
 * Clase que implementa el movimiento para el juego del Reversi. 
 * Se deben implementar los métodos abstractos de la clase padre.
 *
 */
public class MovimientoReversi extends Movimiento {
	
	private int _fila;
	// Primera dimensión para las X. Segunda para las Y
	private ArrayList<ArrayList<Integer>> listaDeFichas;

	/**
	 * Constructor del objeto.
	 * 
	 * @param _donde - Columna en la que se colocará la ficha.
	 * @param _fila - Fila en la que se colocará la ficha.
	 * @param _color - Color de la ficha que se pondrá (o jugador que pone).
	 * @throws MovimientoInvalido
	 */
	public MovimientoReversi(int donde, int fila, Ficha color) {
		super(donde, color);

		this._fila = fila;
		listaDeFichas = new  ArrayList<ArrayList<Integer>>();
	}

	/* (non-Javadoc)
	 * @see tp.pr5.modelo.Movimiento#ejecutaMovimiento(tp.pr5.modelo.Tablero)
	 */
	@Override
	public void ejecutaMovimiento(Tablero tab) throws MovimientoInvalido {
		
		/*System.out.println("\nTurno: " + _color);
		System.out.println("Col: " + _donde);
		System.out.println("Fila: " + _fila);*/
		
		// Si la _fila y la columna del movimiento están dentro de los límites, comprobamos
		if(this._donde > 0 && this._donde <= tab.getAncho() 
				&& this._fila > 0 && this._fila <= tab.getAlto()) {
			
			// Comprobamos si la casilla está libre
			if(tab.getCasilla(this._donde, this._fila) == Ficha.VACIA) {
								
				// Si la posición es válida, realizamos las operaciones correspondientes
				if(buscarFichas(tab)) {
				
					// Colocamos la ficha en el tablero con los atributos actualizados
					tab.setCasilla(this._donde, this._fila, this._color);
					
				} else throw new MovimientoInvalido("Movimiento inválido.");
			}
			else throw new MovimientoInvalido("Casilla ocupada.");
		}
		else throw new MovimientoInvalido("Posición incorrecta.");
	}

	/* (non-Javadoc)
	 * @see tp.pr5.modelo.Movimiento#undo(tp.pr5.modelo.Tablero)
	 */
	@Override
	public void undo(Tablero tab) {
		
		Ficha colorContrario = Ficha.BLANCA;
		
		if(_color == Ficha.BLANCA)
			colorContrario = Ficha.NEGRA;
		
		for(int i = 0; i < listaDeFichas.size(); i++)
			tab.setCasilla(listaDeFichas.get(i).get(0), listaDeFichas.get(i).get(1), colorContrario);
		
		tab.setCasilla(_donde, this._fila, Ficha.VACIA);
	}

	private boolean buscarFichas(Tablero tab) {
		
		boolean movimiento = false;
		ArrayList<ArrayList<Integer>> listaMovs = Comprobar.reversiMovsValidos(tab, _color);
		
		for(int i = 0; i < listaMovs.size(); i++) {
			
			if(listaMovs.get(i).get(0) == _donde && listaMovs.get(i).get(1) == _fila)
				movimiento = true;
		}
		
		ArrayList<Integer> listaAux;
		
		Ficha colorContrario = Ficha.BLANCA;
		
		if(_color == Ficha.BLANCA)
			colorContrario = Ficha.NEGRA;
		
		int posCol = _donde;
		int posFila = _fila;
		
		if(movimiento) {
			
			int col = posCol;
			int fila =  posFila;
			int recorrido = 0;
			
			// Comprobamos norte
			fila--;	// Cambiamos el valor para no empezar desde nuestra casilla
			while(fila > 0 && tab.getCasilla(col, fila) == colorContrario) {
				
				listaAux = new ArrayList<Integer>();
				listaAux.add(col);
				listaAux.add(fila);
				listaDeFichas.add(listaAux);
				
				fila--;
			}
			
			if(tab.getCasilla(col, fila) == Ficha.VACIA && tab.getCasilla(col, fila) != _color) {
			
				recorrido = posFila - fila - 1;
				
				listaAux = new ArrayList<Integer>();
				if(recorrido > 0 && fila + 1 < posFila) {

					for(int i = recorrido; i > 0; i--)
						listaDeFichas.remove(listaDeFichas.size() - 1);
				}
			}
			
			col = posCol;
			fila =  posFila;
			
			// Comprobamos noreste
			col++;	// Cambiamos el valor para no empezar desde nuestra casilla
			fila--;
			while(col < tab.getAncho() && fila > 0 && tab.getCasilla(col, fila) == colorContrario) {
				
				listaAux = new ArrayList<Integer>();
				listaAux.add(col);
				listaAux.add(fila);
				listaDeFichas.add(listaAux);
				
				col++;
				fila--;
			}
			
			if(tab.getCasilla(col, fila) == Ficha.VACIA && tab.getCasilla(col, fila) != _color) {
	
				recorrido = col - posCol - 1;
				fila = posFila - recorrido;
				
				listaAux = new ArrayList<Integer>();
				if(recorrido > 0 && posCol + 1 < col) {

					for(int i = recorrido; i > 0; i--)
						listaDeFichas.remove(listaDeFichas.size() - 1);
				}
			}
			
			col = posCol;
			fila =  posFila;
			
			// Comprobamos este
			col++;	// Cambiamos el valor para no empezar desde nuestra casilla
			while(col < tab.getAncho() && tab.getCasilla(col, fila) == colorContrario) {
				
				listaAux = new ArrayList<Integer>();
				listaAux.add(col);
				listaAux.add(fila);
				listaDeFichas.add(listaAux);
				
				col++;
			}
				
			if(tab.getCasilla(col, fila) == Ficha.VACIA && tab.getCasilla(col, fila) != _color) {
	
				recorrido = col - posCol - 1;
				
				listaAux = new ArrayList<Integer>();
				if(recorrido > 0 && posCol + 1 < col) {

					for(int i = recorrido; i > 0; i--)
						listaDeFichas.remove(listaDeFichas.size() - 1);
				}
			}
			
			col = posCol;
			fila =  posFila;
			
			// Comprobamos sureste
			col++;	// Cambiamos el valor para no empezar desde nuestra casilla
			fila++;
			while(col < tab.getAncho() && fila < tab.getAlto() && tab.getCasilla(col, fila) == colorContrario) {
				
				listaAux = new ArrayList<Integer>();
				listaAux.add(col);
				listaAux.add(fila);
				listaDeFichas.add(listaAux);
				
				col++;
				fila++;
			}
			
			if(tab.getCasilla(col, fila) == Ficha.VACIA && tab.getCasilla(col, fila) != _color) {
	
				recorrido = col - posCol - 1;
				fila = posFila + recorrido;
				
				listaAux = new ArrayList<Integer>();
				if(recorrido > 0 && posCol + 1 < col) {

					for(int i = recorrido; i > 0; i--)
						listaDeFichas.remove(listaDeFichas.size() - 1);
				}
			}
			
			col = posCol;
			fila =  posFila;
			
			// Comprobamos sur
			fila++;	// Cambiamos el valor para no empezar desde nuestra casilla
			while(fila < tab.getAlto() && tab.getCasilla(col, fila) == colorContrario) {
				
				listaAux = new ArrayList<Integer>();
				listaAux.add(col);
				listaAux.add(fila);
				listaDeFichas.add(listaAux);
				
				fila++;
			}
			
			if(tab.getCasilla(col, fila) == Ficha.VACIA && tab.getCasilla(col, fila) != _color) {
	
				recorrido = fila - posFila - 1;
				
				listaAux = new ArrayList<Integer>();
				if(recorrido > 0 && posFila + 1 < fila) {

					for(int i = recorrido; i > 0; i--)
						listaDeFichas.remove(listaDeFichas.size() - 1);
				}
			}
			
			col = posCol;
			fila =  posFila;
			
			// Comprobamos suroeste
			col--;	// Cambiamos el valor para no empezar desde nuestra casilla
			fila++;
			while(col > 0 && fila < tab.getAlto() && tab.getCasilla(col, fila) == colorContrario) {
				
				listaAux = new ArrayList<Integer>();
				listaAux.add(col);
				listaAux.add(fila);
				listaDeFichas.add(listaAux);
				
				col--;
				fila++;
			}
			
			if(tab.getCasilla(col, fila) == Ficha.VACIA && tab.getCasilla(col, fila) != _color) {
	
				recorrido = posCol - col - 1;
				fila = posFila + recorrido;
				
				listaAux = new ArrayList<Integer>();
				if(recorrido > 0 && col + 1 < posCol) {

					for(int i = recorrido; i > 0; i--)
						listaDeFichas.remove(listaDeFichas.size() - 1);
				}
			}
			
			col = posCol;
			fila =  posFila;
			
			// Comprobamos oeste
			col--;	// Cambiamos el valor para no empezar desde nuestra casilla
			while(col > 0 && tab.getCasilla(col, fila) == colorContrario) {
			
				listaAux = new ArrayList<Integer>();
				listaAux.add(col);
				listaAux.add(fila);
				listaDeFichas.add(listaAux);
				
				col--;
			}
			
			if(tab.getCasilla(col, fila) == Ficha.VACIA && tab.getCasilla(col, fila) != _color) {
	
				recorrido = posCol - col - 1;
				
				listaAux = new ArrayList<Integer>();
				if(recorrido > 0 && col + 1 < posCol) {
					
					for(int i = recorrido; i > 0; i--)
						listaDeFichas.remove(listaDeFichas.size() - 1);
				}
			}
			
			col = posCol;
			fila =  posFila;
			
			// Comprobamos noroeste
			col--;	// Cambiamos el valor para no empezar desde nuestra casilla
			fila--;
			while(col > 0 && fila > 0 && tab.getCasilla(col, fila) == colorContrario) {
				
				listaAux = new ArrayList<Integer>();
				listaAux.add(col);
				listaAux.add(fila);
				listaDeFichas.add(listaAux);
				
				col--;
				fila--;
			}
			
			if(tab.getCasilla(col, fila) == Ficha.VACIA && tab.getCasilla(col, fila) != _color) {
	
				recorrido = posCol - col - 1;
				fila = posFila - recorrido;
				
				listaAux = new ArrayList<Integer>();
				if(recorrido > 0 && col + 1 < posCol) {

					for(int i = recorrido; i > 0; i--)
						listaDeFichas.remove(listaDeFichas.size() - 1);
				}
			}
		}
		
		for(int i = 0; i < listaDeFichas.size(); i++)
			tab.setCasilla(listaDeFichas.get(i).get(0), listaDeFichas.get(i).get(1), _color);
		
		return movimiento;
	}
}
