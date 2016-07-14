package tp.pr3.logica;

import tp.pr3.utilidades.Comprobar;

/**
 * @author Sergio
 *
 * Implementación de las reglas del Complica. Se deben implementar 
 * todos los métodos del interfaz, además del constructor.
 */
public class ReglasComplica implements ReglasJuego
{
	private Tablero tablero;
	
	private int maxancho;	// COLUMNAS
	private int maxalto;	// FILAS
	
	final static int CONECTA = 4;
	
	/**
	 * Constructor de la clase, sin parámetros
	 */
	public ReglasComplica()
	{
		maxancho = 4;
		maxalto = 7;
	}
	
	/**
	 * Constructor de la clase, con parámetros
	 */
	public ReglasComplica(int ancho, int alto)
	{
		maxancho = ancho;
		maxalto = alto;
	}

	/* (non-Javadoc)
	 * @see tp.pr3.logica.ReglasJuego#iniciaTablero()
	 */
	@Override
	public Tablero iniciaTablero()
	{
		this.tablero = new Tablero(maxancho, maxalto);
		
		return tablero;
	}

	/* (non-Javadoc)
	 * @see tp.pr3.logica.ReglasJuego#jugadorInicial()
	 */
	@Override
	public Ficha jugadorInicial()
	{
		return Ficha.BLANCA;
	}

	// POR QUE PASAR TABLERO SI NO HACE FALTA PARA CAMBIAR EL TURNO??!?!??!?!?!!????????????????????????????????
	// De momento no se utiliza (metodo generico, futura implementacion)
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
		Ficha ganador = Ficha.VACIA;
		
		Ficha jugador = ultimoMovimiento.getJugador();
		
		if(Comprobar.compruebaGanador(t, jugador, CONECTA))
		{
			// Cambiar el turno para comprobar si el segundo jugador también ha ganado con el nuevo movimiento
			jugador = siguienteTurno(jugador, t);
			
			// Si el segundo jugador también ha ganado, ganador = Ficha.VACIA y la partida continua
			if(Comprobar.compruebaGanador(t, jugador, CONECTA))
				ganador = Ficha.VACIA;
			
			// Si el segundo jugador no ha ganado, cambiamos el turno y el primero gana la partida
			else ganador = siguienteTurno(jugador, t);
		}
		else 
		{
			// Cambiar el turno para comprobar si el segundo jugador ha ganado con el nuevo movimiento
			jugador = siguienteTurno(jugador, t);
			
			if(Comprobar.compruebaGanador(t, jugador, CONECTA))
				ganador = jugador;
			else ganador = Ficha.VACIA;
		}
		
		
		return ganador;
	}

	/* (non-Javadoc)
	 * @see tp.pr3.logica.ReglasJuego#tablas(tp.pr3.logica.Ficha, tp.pr3.logica.Tablero)
	 */
	@Override
	public boolean tablas(Ficha ultimoEnPoner, Tablero t)
	{
		// NO HAY TABLAS EN EL MODO DE JUEGO COMPLICA
		/*boolean tablas = true;
		
		int col = 1;
		
		while(col <= t.getAncho() && tablas != false)
		{
			if(t.getCasilla(col, 1) == Ficha.VACIA)
				tablas = false;
			
			col++;
		}*/
		
		// NO HAY TABLAS EN EL MODO DE JUEGO COMPLICA
		return false;
	}

}
