package tp.pr4.vistas;

import tp.pr4.controlador.ControladorConsola;
import tp.pr4.modelo.Ficha;
import tp.pr4.modelo.MovimientoInvalido;
import tp.pr4.modelo.TableroInmutable;

/**
 * @author Sergio y Carlos <3
 *
 * Clase que contiene la Vista de la consola de juego
 */
public class VistaConsola implements Observador {
	
	ControladorConsola c;

	/**
	 * Constructor de la clase
	 * 
	 * @param control - Objeto de la clase ControladorConsola
	 */
	public VistaConsola(ControladorConsola control) {

		this.c = control;
		this.c.addObservador(this);
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onReset(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha)
	 */
	@Override
	public void onReset(TableroInmutable tab, Ficha turno) {
		
		System.out.println("Partida iniciada.");
		jugar(tab, turno);
		
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onPartidaTerminada(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha)
	 */
	@Override
	public void onPartidaTerminada(TableroInmutable tablero, Ficha ganador) {
		
		System.out.println(toString(tablero).toString());
		
		if(ganador == Ficha.BLANCA)
			System.out.println("Ganan las blancas");
		else {
			
			if(ganador == Ficha.NEGRA)
				System.out.println("Ganan las negras");
			else System.out.println("Partida terminada en tablas.");
		}
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onCambioJuego(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha)
	 */
	@Override
	public void onCambioJuego(TableroInmutable tablero, Ficha turno) {
		jugar(tablero, turno);
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onUndoNotPossible(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha)
	 */
	@Override
	public void onUndoNotPossible(TableroInmutable tablero, Ficha turno) {

		System.err.println("Imposible deshacer.");
		jugar(tablero, turno);
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onUndo(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha, boolean)
	 */
	@Override
	public void onUndo(TableroInmutable tablero, Ficha turno, boolean hayMas) {
		jugar(tablero, turno);
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onMovimientoEnd(tp.pr4.modelo.TableroInmutable, tp.pr4.modelo.Ficha, tp.pr4.modelo.Ficha)
	 */
	@Override
	public void onMovimientoEnd(TableroInmutable tablero, Ficha jugador, Ficha turno) {
		jugar(tablero, turno);
	}

	/* (non-Javadoc)
	 * @see tp.pr4.vistas.Observador#onMovimientoIncorrecto(tp.pr4.modelo.MovimientoInvalido)
	 */
	@Override
	public void onMovimientoIncorrecto(MovimientoInvalido movimientoException) {
		
		System.err.println(movimientoException.getMessage().toString());
	}

	/**
	 * Método que muestra el inicio de la aplicación
	 * 
	 * @param tablero - Tablero de la partida
	 * @param turno - Turno del jugador
	 */
	public void jugar(TableroInmutable tablero, Ficha turno) {
	
		System.out.println(toString(tablero).toString());
		if(turno == Ficha.BLANCA)
			System.out.println("Juegan blancas");
		else System.out.println("Juegan negras");
		System.out.print("Qué quieres hacer? ");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString(TableroInmutable tab) {
		
		String tablero = "";
		
		for(int i = 1; i <= tab.getAlto(); i++) {
			
			for(int j = 1; j <= tab.getAncho(); j++) {
				if(j == 1)
					tablero = tablero + "|";
				
				if(tab.getCasilla(j, i) == Ficha.VACIA)
					tablero = tablero + " ";
				else {
					if(tab.getCasilla(j, i) == Ficha.BLANCA)
						tablero = tablero + "O";
					else {
						if(tab.getCasilla(j, i) == Ficha.NEGRA)
							tablero = tablero + "X";
					}
				}
			
				if(j == tab.getAncho())
					tablero = tablero + "|";
			}
			
			tablero = tablero + "\n";
		}
		
		// Linea final " +-------+ "
		tablero = tablero + "+";
		for(int i = 1; i <= tab.getAncho(); i++)
			tablero = tablero + "-";
		tablero = tablero + "+";
		
		tablero = tablero + "\n";
		
		// Linea final " 1234567 "
		tablero = tablero + " ";
		int j = 1;
		for(int i = 1; i < tab.getAncho() + 1; i++) {
			if(j > 9)
				j = 0;
			tablero = tablero + j;
			j++;
		}
		
		tablero = tablero + "\n";
		
		return tablero;
	}
}
