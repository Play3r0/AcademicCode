package tp.pr3.logica;

import tp.pr3.utilidades.Comprobar;

/**
 * @author Sergio
 *
 * Implementación de las reglas del Conecta 4. Se deben implementar 
 * todos los métodos del interfaz, además del constructor.
 */
public class ReglasConecta4 implements ReglasJuego
{
	private Tablero tablero;
	
	private int maxancho;	// COLUMNAS
	private int maxalto;	// FILAS
	
	final static int CONECTA = 4;
	
	/**
	 * Constructor de la clase, sin parámetros
	 */
	public ReglasConecta4()
	{
		this.maxancho = 7;
		this.maxalto = 6;
	}
	
	/**
	 * Constructor de la clase, con parámetros
	 */
	public ReglasConecta4(int ancho, int alto)
	{
		this.maxancho = ancho;
		this.maxalto = alto;
	}

	/* (non-Javadoc)
	 * @see tp.pr3.logica.ReglasJuego#iniciaTablero()
	 */
	@Override
	public Tablero iniciaTablero() {
		this.tablero = new Tablero(this.maxancho, this.maxalto);
		
		return tablero;
	}

	/* (non-Javadoc)
	 * @see tp.pr3.logica.ReglasJuego#jugadorInicial()
	 */
	@Override
	public Ficha jugadorInicial() {
		return Ficha.BLANCA;
	}

	// POR QUE PASAR TABLERO SI NO HACE FALTA PARA CAMBIAR EL TURNO??!?!??!?!?!!????????????????????????????????
	// De momento no se utiliza (método genérico, futura implementación)
	/* (non-Javadoc)
	 * @see tp.pr3.logica.ReglasJuego#siguienteTurno(tp.pr3.logica.Ficha, tp.pr3.logica.Tablero)
	 */
	@Override
	public Ficha siguienteTurno(Ficha ultimoEnPoner, Tablero t)
	{
		return (ultimoEnPoner == Ficha.BLANCA)? Ficha.NEGRA : Ficha.BLANCA;
	}

	/* (non-Javadoc)
	 * @see tp.pr3.logica.ReglasJuego#hayGanador(tp.pr3.logica.Movimiento, tp.pr3.logica.Tablero)
	 */
	@Override
	public Ficha hayGanador(Movimiento ultimoMovimiento, Tablero t)
	{
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
	public boolean tablas(Ficha ultimoEnPoner, Tablero t)
	{
		boolean tablas = true;
		
		int col = 1;
		
		while(col <= t.getAncho() && tablas != false)
		{
			if(t.getCasilla(col, 1) == Ficha.VACIA)
				tablas = false;
			
			col++;
		}
		
		return tablas;
	}

}
