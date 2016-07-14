package FurryFiesta.modelo.venta;

/**
 * 
 * Clase Principal del modulo de ventas, encargada final de las funciones sobre estas
 * 
 * @author Carlos Lozano
 *
 */
public class Venta {

	private int _id;
	private int _idPersonal;
	private int _articulos;
	private double _total;
	private String _fecha; // ("yyyy-MM-dd HH:mm:ss")
	

		
	/**
	 * Constructor por defecto la venta, la ID se la dara la base de datos.
	 * @param idPersonal - La ID del empleado que realiza la venta.
	 * @param articulos - la lista de articulos.
	 * @param numArticulos - la lista complementaria con cuantos articulos se han vendido.
	 * @param fecha - la fecha actual.
	 */
	public Venta(int idPersonal, int articulos,int total, String fecha) {	
		
		_idPersonal = idPersonal;	
		_articulos = articulos;
		_total = total;
		_fecha = fecha;
	}

	/**
	 * constructor de la base de datos, ya que ya tiene ID, y  el total.
	 * @param id - La ID de la venta.
	 * @param idPersonal - La ID del vendedor
	 * @param articulos	- La lista de articulos.
	 * @param numArticulos - La lista complementaria con la cantidad de articulos.
	 * @param total	- El valor de la venta.
	 * @param fecha - La fecha de la venta.
	 */
	public Venta(int id,int idPersonal, int articulos, double total, String fecha) {	
		
		_id = id;
		_idPersonal = idPersonal;	
		_articulos = articulos;
		_total = total;
		_fecha = fecha;
	}
	


	
	
	// Geteres y seters de cada atributo
	/**
	 * get del ID de la venta.
	 * @return - La ID de la venta.
	 */
	public int getId() {
		return _id;
	}

	/**
	 * Cambia la ID de la venta.
	 * @param id - la nueva ID.
	 */
	public void setId(int id) {
		this._id = id;
	}

	
	/**
	 * Get del ID del vendedor responsable de la venta.
	 * @return la ID del vendedor.
	 */
	public int getIdPersonal() {
		return _idPersonal;
	}

	/**
	 * cambia el vendedor. Se presupone que la ID introducida es valida
	 * @param idPersonal - la ID del nuevo vendedor(sustituye al anterior)
	 */
	public void setIdPersonal(int idPersonal) {
		this._idPersonal = idPersonal;
	}

	/**
	 * Geter de la lista de productos vendidos en forma de String
	 * @return - la lista de productos.
	 */
	public int getArticulos() {
		return _articulos;
	}

	
	
	/**
	 * Cambia la lista de productos vendidos por otra nueva
	 * @param articulos - la nueva lista de articulos(no se compruba si esta vacia)
	 */
	public void setArticulos(int articulos) {
		this._articulos = articulos;
	}

	/**
	 * devuelve el valor de la venta
	 * @return el valor de la venta
	 */
	public double getTotal() {
		return _total;
	}

	/**
	 * Cambia el valor de la venta
	 * (este metodo me parece raro, seria mejor calcularlo en funcion de los articulos)
	 * @param total - el nuevo valor
	 */
	public void setTotal(double total) {
		this._total = total;
	}

	/**
	 * la fecha de la venta.
	 * @return - la fecha de la venta.
	 */
	public String getFecha() {
		return _fecha;
	}

	/**
	 * Cambia la fecha de la venta. No se comprueba si la fecha es valida
	 * @param fecha - la nueva fecha.
	 */
	public void setFecha(String fecha) {
		this._fecha = fecha;
	}


	
	
	@Override
	public String toString() {
		return "Venta Id Venta=" + _id + ", ID Vendedor=" + _idPersonal + System.lineSeparator()
				+ "precio total=" + _total + ", realizada el=" + _fecha;
	}

	public String getNombreProducto() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getCantidad() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getIdProducto() {
		// TODO Auto-generated method stub
		return null;
	}


	public String getCategoria() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
