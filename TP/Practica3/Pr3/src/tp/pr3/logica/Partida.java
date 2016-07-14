package tp.pr3.logica;

import tp.pr3.control.Jugador;

/**
 * @author Sergio
 * 
 * Clase para representar la información de una partida. 
 * Se encarga de almacenar el estado del tablero, 
 * a quién le toca, si ya hay un ganador, etc., 
 * así como la lista de movimientos que se han ido realizando para poder 
 * deshacerlos. La partida guarda al menos los 10 últimos movimientos.
 */
public class Partida
{
	private ReglasJuego reglas;
	private Tablero tab;
	private Ficha turno;
	private Ficha ganador;
	private boolean terminada;
	
	// Variable constante para fijar el numero de turnos para deshacer
	private final int MAXNUMUNDO = 10;
	
	private Movimiento[] undoStack;
	private int numUndo;
	
	/**
	 * Construye una partida nueva.
	 * 
	 * @param reglas - Reglas del juego que se utilizarán durante la partida 
	 * (al menos hasta que se ejecute reset).
	 */
	public Partida(ReglasJuego reglas) {
		
		this.reglas = reglas;
		this.tab = reglas.iniciaTablero();
		this.turno = reglas.jugadorInicial();
		this.ganador = Ficha.VACIA;
		this.terminada = false;

		this.undoStack = new Movimiento[this.MAXNUMUNDO];
		
		this.numUndo = 0;
	}

	/**
	 * Ejecuta el movimiento indicado.
	 * 
	 * @param mov - Movimiento a ejecutar. Se asume que el movimiento es compatible 
	 * con las reglas de la partida que se está jugando 
	 * (si se está jugando a Conecta 4, el tipo de movimiento es Conecta 4, etc.). 
	 * En caso contrario, el comportamiento es indeterminado.
	 * 
	 * @return true si se puede efectuar el movimiento. 
	 * Es un error intentar colocar una ficha del jugador que no tiene el turno, 
	 * cuando la partida está terminada, columna llena, ...
	 * @throws MovimientoInvalido 
	 */
	public void ejecutaMovimiento(Movimiento mov) throws MovimientoInvalido {
		
		// Ejecutar mov meintras la partida NO haya terminado y coincida el turno
		if(!(isTerminada()) && mov.getJugador() == this.turno) {
			
			mov.ejecutaMovimiento(this.tab);
			
			// Si se ha ejecutado el movimiento, actualizamos el array
			//if(movimiento)
			//{				
				undoPoner(mov);
				
				// Comprobamos si hay un ganador
				this.ganador = this.reglas.hayGanador(mov, this.tab);
				
				if(ganador != Ficha.VACIA)
					this.terminada = true;
				
				// NO HAY TABLAS EN EL MODO DE JUEGO COMPLICA
				if(this.reglas.tablas(mov.getJugador(), this.tab))
					this.terminada = true;
				
				this.turno = this.reglas.siguienteTurno(mov.getJugador(), this.tab);
			//}
		}
		else throw new MovimientoInvalido("Turno incorrecto.");
	}
	
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	public String toString() {
		return this.tab.toString();
	}
		
	/**
	 * Método de acceso al tablero para facilitar la implementación de 
	 * las pruebas de unidad. Es necesario implementarlo para 
	 * las pruebas pero no se debería usar en la solución. 
	 * 
	 * @return Estado del tablero actual.
	 */
	public Tablero getTablero() {
		return this.tab;
	}

	/**
	 * Devuelve el color del jugador al que le toca poner.
	 * 
	 * @return Color del jugador, o Ficha.VACIA si la partida ha terminado.
	 */
	public Ficha getTurno() {
		return this.turno;
	}

	/**
	 * Método para saber si la partida ha conluído ya o no.
	 * 
	 * @return true si la partida ha acabado.
	 */
	public boolean isTerminada() {
		return this.terminada;
	}

	/**
	 * Devuelve el color del ganador. Sólo válido si la partida 
	 * ya ha terminado (isTerminada() == true).
	 * 
	 * @return Color del ganador. Si la partida terminó en tablas, 
	 * Ficha.VACIA. Si la partida no ha terminado aún, 
	 * el resultado es indeterminado.
	 */
	public Ficha getGanador() {
		return this.ganador;
	}
	
	/**
	 * Reinicia la partida en curso. Esta operación no puede deshacerse. 
	 * Gracias al parámetro, permite cambiar el tipo de juego al que se juega.
	 * 
	 * @param reglas - Las reglas del juego a utilizar a partir de ahora.
	 */
	public void reset(ReglasJuego reglas) {
		
		this.reglas = reglas;
		this.tab = this.reglas.iniciaTablero();
		this.turno = this.reglas.jugadorInicial();
		this.numUndo = 0;
	}

	/**
	 * Deshace el último movimiento ejecutado.
	 * 
	 * @return true si se pudo deshacer.
	 */
	public boolean undo() {
		
		boolean deshacer = false;
		
		if(this.numUndo > 0)
		{
			undoQuitar();
			
			deshacer = true;
		}
		
		return deshacer;
	}
	
	/**
	 * Futura implementación como un clase? Por ejemplo una pila (stack)
	 * "El manejo de la pila de undo se podría haber abstraído en métodos privados (TAD)"
	 * 
	 * @param mov
	 */
	private void undoPoner(Movimiento mov) {
		
		if(this.numUndo == this.MAXNUMUNDO) {
			
			for(int i = 0; i < this.MAXNUMUNDO - 1; i++)
				this.undoStack[i] = this.undoStack[i + 1];
			
			this.undoStack[numUndo - 1] = mov;
		}
		else {
			
			this.undoStack[this.numUndo] = mov;
			this.numUndo++;
		}
		
	}
	
	/**
	 * Futura implementación como un clase? Por ejemplo una pila (stack)
	 * "El manejo de la pila de undo se podría haber abstraído en métodos privados (TAD)"
	 * 
	 * @param mov
	 */
	private void undoQuitar() {
		
		this.undoStack[this.numUndo - 1].undo(this.tab);
		this.numUndo--;
					
		// Despues de deshacer devolvemos el turno al jugador anterior
		this.turno = this.reglas.siguienteTurno(getTurno(), this.tab);
	}

	/**
	 * Método puente para crear un flujo de datos entre la partida y el controlador.
	 * 
	 * @param j - Jugador al que le toca realizar un movimiento
	 * 
	 * @return El movimiento del jugador
	 */
	public Movimiento dameMov(Jugador j) {
		return j.getMovimiento(tab, turno);
	}
}
