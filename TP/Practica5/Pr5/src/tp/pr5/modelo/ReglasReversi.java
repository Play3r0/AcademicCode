package tp.pr5.modelo;

import java.util.ArrayList;

import tp.pr5.utilidades.Comprobar;

/**
 * @author Sergio
 *
 * Implementación de las reglas del Reversi. Se deben implementar 
 * todos los métodos del interfaz, además del constructor.
 */
public class ReglasReversi implements ReglasJuego {

private Tablero tablero;
	
	private int maxancho;		   // COLUMNAS
	private int maxalto;		   // FILAS
	
	private int _fichasBlancas;
	private int _fichasNegras;
	
	private boolean _blancasTienenMovs;
	private boolean _negrasTienenMovs;
	
	/**
	 * Constructor de la clase, sin parámetros
	 */
	public ReglasReversi() {
		
		this.maxancho = 8;
		this.maxalto = 8;
		
		_fichasBlancas = 0;
		_fichasNegras = 0;
		
		_blancasTienenMovs = true;
		_negrasTienenMovs = true;
	}
	
	/* (non-Javadoc)
	 * @see tp.pr5.logica.ReglasJuego#iniciaTablero()
	 */
	@Override
	public Tablero iniciaTablero() {
		
		this.tablero = new Tablero(this.maxancho, this.maxalto);
		
		// Colocamos las fichas cuatro iniciales en tablero
		this.tablero.setCasilla(4, 4, Ficha.BLANCA);
		this.tablero.setCasilla(5, 4, Ficha.NEGRA);
		this.tablero.setCasilla(5, 5, Ficha.BLANCA);
		this.tablero.setCasilla(4, 5, Ficha.NEGRA);
		
		return this.tablero;
	}

	/* (non-Javadoc)
	 * @see tp.pr5.logica.ReglasJuego#jugadorInicial()
	 */
	@Override
	public Ficha jugadorInicial() {
		return Ficha.NEGRA;
	}

	/* (non-Javadoc)
	 * @see tp.pr5.logica.ReglasJuego#siguienteTurno(tp.pr5.logica.Ficha, tp.pr5.logica.Tablero)
	 */
	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		
		// Obtenemos el color contrario al del jugador actual
		Ficha colorContrario = (ultimoEnPoner == Ficha.BLANCA)? Ficha.NEGRA : Ficha.BLANCA;
		
		// Comprobamos si puede realizar algún movimiento
		ArrayList<ArrayList<Integer>> reversiMovsValidos = Comprobar.reversiMovsValidos(t, colorContrario);
		
		// Si no tiene movimientos disponibles, vuelve a jugar el último en poner
		if(reversiMovsValidos.size() == 0) {
		
			if(colorContrario == Ficha.BLANCA)
				_blancasTienenMovs = false;
			else _negrasTienenMovs = false;
			
			// Comprobamos si puede realizar algún movimiento el último en poner
			reversiMovsValidos = Comprobar.reversiMovsValidos(t, ultimoEnPoner);
			
			if(reversiMovsValidos.size() == 0) {
				
				if(ultimoEnPoner == Ficha.BLANCA)
					_blancasTienenMovs = false;
				else _negrasTienenMovs = false;
				
				return colorContrario;
			}
			else return ultimoEnPoner;
		}
		else {
		
			if(colorContrario == Ficha.BLANCA)
				_blancasTienenMovs = true;
			else _negrasTienenMovs = true;
			
			return colorContrario;
		}
	}

	/* (non-Javadoc)
	 * @see tp.pr5.logica.ReglasJuego#hayGanador(tp.pr5.logica.Movimiento, tp.pr5.logica.Tablero)
	 */
	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {

		Ficha ganador = Ficha.VACIA;
		
		// NECESITAMOS SABER SI EL JUGADOR TIENE MOVIMIENTOS VÁLIDOS
		
		if(_blancasTienenMovs == false && _negrasTienenMovs == false) {
		
			for(int i = 1; i < t.getAncho() + 1; i++) {
				for(int j = 1; j <= t.getAlto() + 1; j++) {
					
					if(t.getCasilla(i, j) == Ficha.BLANCA)
						_fichasBlancas++;
					else {
						if(t.getCasilla(i, j) == Ficha.NEGRA)
							_fichasNegras++;
					}
				}
			}
			
			// Comprobamos que jugador tiene más fichas en el tablero
			if(_fichasBlancas > _fichasNegras)
				ganador = Ficha.BLANCA;
			else
			{
				if(_fichasBlancas < _fichasNegras)
					ganador = Ficha.NEGRA;
				else ganador = Ficha.VACIA;
			}
		}		
		
		return ganador;
	}

	/* (non-Javadoc)
	 * @see tp.pr5.logica.ReglasJuego#tablas(tp.pr5.logica.Ficha, tp.pr5.logica.Tablero)
	 */
	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {

		boolean tablas = false;
		
		// Si entra en este método es porque blancas y negras tienen el mismo número de fichas
		if(_blancasTienenMovs == false && _negrasTienenMovs == false)
			tablas = true;			
			
		return tablas;
	}
}
