package tp.pr5.controlador;

import tp.pr5.modelo.Ficha;
import tp.pr5.modelo.Juego;
import tp.pr5.modelo.Movimiento;
import tp.pr5.modelo.Partida;
import tp.pr5.modelo.TipoTurno;
import tp.pr5.vistas.Observador;

/**
 * @author Carlos
 *
 * Clase que controla la ejecución de la partida, 
 * esperando a que el usuario realice acciones.
 */
public class ControladorGUI {

	private FactoriaTipoJuego f;
	private Partida p;
	private Modo _modoJugador1;
	private Modo _modoJugador2;	
	
	/**
	 * Constructor de la clase.
	 * 
	 * @param f - Factoría con la que se configura el tipo de juego
	 * @param p - Partida a la que se jugará, configurada con el juego al que hay que jugar en la invocación a run().
	 */
	public ControladorGUI(FactoriaTipoJuego f, Partida p) {
		
		this.f = f;		
		this.p = p;
		
		initModoJugador();
	}
	
	/**
	 * Este método inicializa el modo de jugador para cada atributo 
	 * jugador y le asigna un color de ficha concreto
	 */
	public void initModoJugador() {
		
		// Iniciamos los jugadores con los modos de jugador por defecto
		_modoJugador1 = new ModoHumano();
		_modoJugador2 = new ModoHumano();
		
		Ficha.BLANCA.setModoJuego(_modoJugador1);
		Ficha.BLANCA.setTipoTurno(TipoTurno.HUMANO);
		
		Ficha.NEGRA.setModoJuego(_modoJugador2);
		Ficha.NEGRA.setTipoTurno(TipoTurno.HUMANO);
	}
	
	/**
	 * Añade un Observador a la partida
	 * 
	 * @param o - Objeto de la clase Observador
	 */
	public void addObservador(Observador o) {
		p.addObserver(o);
	}

	/**
	 * Elimina un Observador de la Partida
	 * 
	 * @param o - Objeto observador
	 */
	public void removeObservador(Observador o) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Método privado para cambiar de jugador
	 * 
	 * @param juego - String con el modo de juego
	 * @param cols - Número de columnas
	 * @param filas - Número de filas
	 */
	public void cambiarJuego(String juego, int cols, int filas) {
		
		if(juego.equals(Juego.CONECTA4.toString()))
			reset(new FactoriaConecta4());
		else {
			if(juego.equals(Juego.COMPLICA.toString()))
				reset(new FactoriaComplica());
			else {
				if(juego.equals(Juego.REVERSI.toString()))
					reset(new FactoriaReversi());
				else reset(new FactoriaGravity(cols, filas));
			}
		}
		
		//p.crearPartida();
	}
	
	/**
	 * Resetea la partida
	 * 
	 * @param f - Objeto Factoria para reiniciar el juego
	 */
	public void reset(FactoriaTipoJuego f) {
		
		this.f = f;
		reiniciar();
	}

	/**
	 * Método para deshacer el último movimiento
	 */
	public void undo() {
		
		p.detenerPartida();
		
		// Llamada para deshacer el último movimiento válido
		p.undo();
		
		if(Ficha.BLANCA.getTipoTurno() == TipoTurno.AUTOMATICO || Ficha.NEGRA.getTipoTurno() == TipoTurno.AUTOMATICO)
			p.undo();
		
		p.continuarPartida();
	}
	
	/**
	 * Método privado para reiniciar la partida
	 */
	public void reiniciar() {
		
		p.detenerPartida();
		
		// Generamos una nueva partida
		p.reset(f.creaReglas());
		
		initModoJugador();
		
		p.continuarPartida();
	}

	/**
	 * Método para poner una ficha
	 */
	public void poner(int col, int fila) {
		
		// Creamos un movimiento a partir de la columna, la fila y el turno
		Movimiento mov = f.creaMovimiento(col, fila, p.getTurno());
			
		// Ejecutamos el movimiento
		p.ejecutaMovimiento(mov);
			
		finalizar();
	}
	
	/**
	 * Método para colocar una ficha de forma aleatoria
	 */
	public void ponerAleatorio() {
		
		Movimiento mov = p.dameMov(f.creaJugadorAleatorio());
				
		// Ejecutamos el movimiento
		p.ejecutaMovimiento(mov);
			
		finalizar();
	}

	/**
	 * Método comprueba si la partida ha terminado
	 */
	public void finalizar() {
		
		// Comprobamos si la partida ha terminado
		p.isTerminada();
		
		// SI PONGO ESTA LLAMA EN EL ejecutaMovimiento DE PARTDA, ENTONCES EN EL PRIMER MOV BLANCAS PONEN DOS VECES EN AUTO
		// LA PRIMERA VEZ PORQUE HAS CAMBIADO EL JUGADOR Y AL PONER EN AUTO Y SER SU TURNO GENERA UN MOVIMIENTO
		// Y LA SEGUNDA VEZ PORQUE AL TERMINAR DE HACER SU MOV AUTO, VUELVE A LLAMAR A continuarPartida()
		p.continuarPartida();
	}
	
	/**
	 * Método para iniciar la partida
	 */
	public void inicarPartida() {
		
		p.crearPartida();
	}
	
	/**
	 * Este método cambia el tipo de jugador 
	 * seleccionado desde la venta de juego
	 * 
	 * @param f
	 * @param t
	 */
	public void cambiarJugador(Ficha f, TipoTurno t) {
		
		p.detenerPartida();
		
		// Preguntamos por el color del turno para conocer al jguador
		if(f == Ficha.BLANCA) 
			_modoJugador1 = cambiarJugadorAux(_modoJugador1, f, t);
		else _modoJugador2 = cambiarJugadorAux(_modoJugador2, f, t);
		
		p.continuarPartida();
	}

	private Modo cambiarJugadorAux(Modo modoJugador, Ficha f, TipoTurno t) {
		
		// Preguntamos por el modo de juego del jugador y lo cambiamos
		if(t == TipoTurno.HUMANO) {
			modoJugador = new ModoHumano();

			f.setTipoTurno(TipoTurno.HUMANO);
		}
		else {
		
			modoJugador = new ModoAuto(this);
			f.setTipoTurno(TipoTurno.AUTOMATICO);
		}

		f.setModoJuego(modoJugador);
		
		return modoJugador;
	}
}
