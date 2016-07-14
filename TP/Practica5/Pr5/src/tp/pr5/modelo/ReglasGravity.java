package tp.pr5.modelo;

import tp.pr5.utilidades.Comprobar;

/**
 * @author Sergio
 *
 * Implementación de las reglas del Gravity. Se deben implementar 
 * todos los métodos del interfaz, además del constructor.
 */
public class ReglasGravity implements ReglasJuego {

	private Tablero tablero;
	
	private int maxancho;		   // COLUMNAS
	private int maxalto;		   // FILAS
	
	private final static int CONECTA = 4;
	
	/**
	 * Constructor de la clase, sin parámetros
	 */
	public ReglasGravity() {
		this.maxancho = 10;
		this.maxalto = 10;
	}
	
	/**
	 * Constructor de la clase, con parámetros
	 * 
	 * @param ancho - Ancho del tablero
	 * @param alto - Altura del tablero
	 */
	public ReglasGravity(int ancho, int alto) {
		
		this.maxancho = ancho;
		this.maxalto = alto;
	}
	
	/* (non-Javadoc)
	 * @see tp.pr5.logica.ReglasJuego#iniciaTablero()
	 */
	@Override
	public Tablero iniciaTablero() {
		
		this.tablero = new Tablero(this.maxancho, this.maxalto);
		
		return this.tablero;
	}

	/* (non-Javadoc)
	 * @see tp.pr5.logica.ReglasJuego#jugadorInicial()
	 */
	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

	/* (non-Javadoc)
	 * @see tp.pr5.logica.ReglasJuego#siguienteTurno(tp.pr5.logica.Ficha, tp.pr5.logica.Tablero)
	 */
	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
				
		return (ultimoEnPoner == Ficha.BLANCA)? Ficha.NEGRA : Ficha.BLANCA;
	}

	/* (non-Javadoc)
	 * @see tp.pr5.logica.ReglasJuego#hayGanador(tp.pr5.logica.Movimiento, tp.pr5.logica.Tablero)
	 */
	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t) {

		Ficha ganador;
		
		if(Comprobar.compruebaGanador(t, ultimoMovimiento.getJugador(), CONECTA))
			ganador = ultimoMovimiento.getJugador();
		else ganador = Ficha.VACIA;
		
		return ganador;
	}

	/* (non-Javadoc)
	 * @see tp.pr5.logica.ReglasJuego#tablas(tp.pr5.logica.Ficha, tp.pr5.logica.Tablero)
	 */
	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {

		boolean tablas = Comprobar.comprobarTablas(t);		
			
		return tablas;
	}
}
