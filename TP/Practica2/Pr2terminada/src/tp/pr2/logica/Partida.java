package tp.pr2.logica;

public class Partida
{
	private ReglasJuego reglas;
	private Tablero tab;
	private Ficha turno;
	private Ficha ganador;
	private boolean terminada;
	
	// Variable constante para fijar el numero de turnos para deshacer
	final int MAXNUMUNDO = 10;
	
	private Movimiento[] undoStack;
	private int numUndo;
	
	public Partida(ReglasJuego reglas)
	{
		this.reglas = reglas;
		this.tab = reglas.iniciaTablero();
		this.turno = Ficha.BLANCA;
		this.ganador = Ficha.VACIA;
		this.numUndo = 0;
		this.terminada = false;

		this.undoStack = new Movimiento[this.MAXNUMUNDO];
		
		// Creamos el array del movimientos seguin el tipo de juego
		if(reglas.getClass() == ReglasConecta4.class)
		{
			for(int i = 0; i < this.MAXNUMUNDO; i++)
				this.undoStack[i] = new MovimientoConecta4(-1, Ficha.VACIA);
		}
		else
		{
			if(reglas.getClass() == ReglasComplica.class)
			{
				for(int i = 0; i < this.MAXNUMUNDO; i++)
					this.undoStack[i] = new MovimientoComplica(-1, Ficha.VACIA);
			}
		}
		
		this.numUndo = 0;
	}

	public boolean ejecutaMovimiento(Movimiento mov)
	{
		boolean movimiento = false;
		
		// Ejecutar mov meintras la partida NO haya terminado y coincida el turno
		if(!(isTerminada()) && mov.getJugador() == this.turno)
		{
			movimiento = mov.ejecutaMovimiento(this.tab);
			
			// Si se ha ejecutado el movimiento, actualizamos el array
			if(movimiento)
			{
				if(this.numUndo == this.MAXNUMUNDO)
				{
					for(int i = 0; i < this.MAXNUMUNDO - 1; i++)
						this.undoStack[i] = this.undoStack[i + 1];
					
					this.undoStack[numUndo - 1] = mov;
				}
				else
				{
					this.undoStack[this.numUndo] = mov;
					this.numUndo++;
				}
			
				// Comprobamos si hay un ganador
				this.ganador = this.reglas.hayGanador(mov, this.tab);
				
				if(ganador != Ficha.VACIA)
					this.terminada = true;
				
				// NO HAY TABLAS EN EL MODO DE JUEGO COMPLICA
				{
					if(this.reglas.tablas(mov.getJugador(), this.tab))
						this.terminada = true;
				}
				
				this.turno = this.reglas.siguienteTurno(mov.getJugador(), this.tab);
			}
		}
		
		return movimiento;
	}
	
	public String toString() {
		return this.tab.toString();
	}
		
	public Tablero getTablero()
	{
		return this.tab;
	}

	public Ficha getTurno()
	{
		return this.turno;
	}

	public boolean isTerminada()
	{
		return this.terminada;
	}

	public Ficha getGanador()
	{
		return this.ganador;
	}
	
	// Reiniciar tab (inicilizar)
	public void reset(ReglasJuego reglas)
	{		
		this.tab.reset();
		this.turno = Ficha.BLANCA;
		this.numUndo = 0;
	}

	public boolean undo()
	{
		boolean deshacer = false;
		
		if(this.numUndo > 0)
		{
			this.undoStack[this.numUndo - 1].undo(this.tab);
			this.numUndo--;
						
			// Despues de deshacer devolvemos el turno al jugador anterior
			this.turno = this.reglas.siguienteTurno(getTurno(), this.tab);
			
			deshacer = true;
		}
		
		return deshacer;
	}
}
