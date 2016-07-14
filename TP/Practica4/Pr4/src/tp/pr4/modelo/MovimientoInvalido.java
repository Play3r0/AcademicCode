package tp.pr4.modelo;

/**
 * Excepción generada cuando se intenta ejecutar un movimiento incorrecto.
 * 
 * @author Sergio
 *
 */
public class MovimientoInvalido extends Exception {

	/**
	 * Ni idea de lo que hace esto ni para que sirve
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Constructor sin parámetros.
	 */
	MovimientoInvalido() {
		
	}
	
	/**
	 * Constructor con un parámetro para el mensaje.
	 * 
	 * @param msg
	 */
	MovimientoInvalido(java.lang.String msg) {
		super(msg);
	}
	
	/**
	 * Constructor con un parámetro para el mensaje y otro para la causa.
	 * 
	 * @param msg
	 * 
	 * @param arg
	 */
	MovimientoInvalido(java.lang.String msg, java.lang.Throwable arg) {
		super(msg, arg);
	}
	
	/**
	 * Constructor con un parámetro para la causa inicial que provocó la excepción.
	 * 
	 * @param arg
	 */
	MovimientoInvalido(java.lang.Throwable arg) {
		super(arg);
	}
}
