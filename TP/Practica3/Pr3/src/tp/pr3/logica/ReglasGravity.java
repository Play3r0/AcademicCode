package tp.pr3.logica;

import tp.pr3.utilidades.Comprobar;

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
	private int maxMovimientos;	   // Número máximo de movimientos
	private int movimientosTablas; // Para saber si hay tablas llevamos la cuenta de los movimientos
	
	final static int CONECTA = 4;
	
	/**
	 * Constructor de la clase, sin parámetros
	 */
	public ReglasGravity() {
		this.maxancho = 10;
		this.maxalto = 10;
		this.maxMovimientos = 10 * 10;
		this.movimientosTablas = 0;
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
		// Si los tamaños son negativos, iniciamos los maxMovimientos para "Tablas" a 1
		if(ancho < 1 || alto < 1)
			this.maxMovimientos = 1;
		else this.maxMovimientos = ancho * alto;
		this.movimientosTablas = 0;
	}
	
	/* (non-Javadoc)
	 * @see tp.pr3.logica.ReglasJuego#iniciaTablero()
	 */
	@Override
	public Tablero iniciaTablero() {
		
		this.tablero = new Tablero(this.maxancho, this.maxalto);
		
		return this.tablero;
	}

	/* (non-Javadoc)
	 * @see tp.pr3.logica.ReglasJuego#jugadorInicial()
	 */
	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

	/* (non-Javadoc)
	 * @see tp.pr3.logica.ReglasJuego#siguienteTurno(tp.pr3.logica.Ficha, tp.pr3.logica.Tablero)
	 */
	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t) {
		
		// Al cambiar el turno incrementamos el número de movimientos
		movimientosTablas++;
		
		return (ultimoEnPoner == Ficha.BLANCA)? Ficha.NEGRA : Ficha.BLANCA;
	}

	/* (non-Javadoc)
	 * @see tp.pr3.logica.ReglasJuego#hayGanador(tp.pr3.logica.Movimiento, tp.pr3.logica.Tablero)
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
	 * @see tp.pr3.logica.ReglasJuego#tablas(tp.pr3.logica.Ficha, tp.pr3.logica.Tablero)
	 */
	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t) {

		boolean tablas = false;
		
		if(this.movimientosTablas == this.maxMovimientos - 1)
			tablas = true;			
			
		return tablas;
	}

}
