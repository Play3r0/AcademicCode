package tp.pr5.modelo;

import tp.pr5.controlador.Modo;
import tp.pr5.controlador.ModoHumano;

/**
 * @author Sergio
 * 
 * Enumerado de tipo Ficha para dar atributos a las fichas
 */
public enum Ficha{
	
		   VACIA("VACÍA", "VACÍA", new ModoHumano(), TipoTurno.HUMANO), 
		   BLANCA("BLANCAS", "BLANCA", new ModoHumano(), TipoTurno.HUMANO), 
		   NEGRA("NEGRAS", "NEGRA", new ModoHumano(), TipoTurno.HUMANO);
		  
		   private String _descripcion;
		   private String _color;
		   private Modo _modoJugador;
		   private TipoTurno _tipoTurno;
		   
		   /**
		    * Constructo del enumerado Ficha
		    * 
		 * @param descripcion - Descripción para la ficha
		 * @param color - Color asignado a la ficha
		 * @param modoJugador - Modo de jugaro. Por defecto Humano
		 * @param turno - Coincide con el color de la ficha
		 */
		Ficha(String descripcion, String color, Modo modoJugador, TipoTurno turno) {
			   
			   _descripcion = descripcion;
			   _color = color;
			   _modoJugador = modoJugador;
			   _tipoTurno = turno;
		   }
		   
		   /**
		 * @return Devuelve la descripción
		 */
		public String getDescripcion() {
			   return _descripcion;
		   }
		   
		   /**
		 * @param descripcion
		 */
		public void setDescripcion(String descripcion) {
			   _descripcion = descripcion;
		   }
		   
		   /**
		 * @return
		 */
		public String getColor() {
			   return _color;
		   }
		   
		   /**
		 * @param color
		 */
		public void setColor(String color) {
			   _color = color;
		   }
		   
		   /**
		 * @return
		 */
		public Modo getModoJuego() {
			   return _modoJugador;
		   }
		   
		   /**
		 * @param modo
		 */
		public void setModoJuego(Modo modo) {
			   _modoJugador = modo;
		   }
		   
		   /**
		 * @return
		 */
		public TipoTurno getTipoTurno() {
			   return _tipoTurno;
		   }
		   
		   /**
		 * @param tipoTurno
		 */
		public void setTipoTurno(TipoTurno tipoTurno) {
			   _tipoTurno = tipoTurno;
		   }
}

