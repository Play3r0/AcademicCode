package FurryFiesta.modelo.pedido;

/**
 * @author Omar LLundo & Enrique Laguna.
 *
 */
public class Pedido {
	
	
	private int id;
	private int idPersonal;
	private int numArticulos;
	private double precioTotal;
	private boolean confirmado;
	private String fecha;
	
	/**Constructor 1 de la clase pedido.
	 * @param idP del personal quien lo ha realizado
	 * @param nArti numero de articulos
	 * @param pTotal precio total del pedido
	 * @param conf confirma el pedido
	 * @param fec fecha del pedido
	 */
	public Pedido(int idP, int nArti, double pTotal, boolean conf, String fec){
		
		this.idPersonal = idP;
		this.numArticulos = nArti;
		this.precioTotal = pTotal;
		this.confirmado = conf;
		this.fecha = fec;
	}
	
	/**
	 * Constructor 2 de la clase pedido
	 * @param idPed ide del pedido.
	 * @param idP id del personal quien lo ha realizado.
	 * @param nArti numero de articulos.
	 * @param pTotal precio total del pedido.
	 * @param conf confirma el pedido.
	 * @param fec fecha del pedido.

	
	 */
	public Pedido(int idPed, int idP, int nArti, double pTotal, boolean conf, String fec){
		
		this.id = idPed;
		this.idPersonal = idP;
		this.numArticulos = nArti;
		this.precioTotal = pTotal;
		this.confirmado = conf;
		this.fecha = fec;
	}
	
	/** Constructor 3 de la clase pedido.
	 * @param idPed id del pedido.
	 * @param idP ide del personal quien lo ha realizado.
	 * @param nArti numero de articulos.
	 * @param pTotal precio total del pedido.
	 * @param fec fecha del pedido.
	 */
	public Pedido(int idPed, int idP, int nArti, double pTotal, String fec){
		
		this.id = idPed;
		this.idPersonal = idP;
		this.numArticulos = nArti;
		this.precioTotal = pTotal;
		this.fecha = fec;
	}
	
	
	/** 
	 * @return el id del pedido
	 */
	public int getId() {
		return id;
	}
	/** Guarda un ide del pedido
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}
	/**
	 * @return id del personal
	 */
	public int getIdPersonal() {
		return idPersonal;
	}
	/** Guarda un id del personal
	 * @param idPersonal
	 */
	public void setIdPersonal(int idPersonal) {
		this.idPersonal = idPersonal;
	}
	/**
	 * @return numero de articulos del pedido
	 */
	public int getNumArticulos() {
		return numArticulos;
	}
	/** Guarda el numero de articulos.
	 * @param numArticulos 
	 */
	public void setNumoArticulos(int numArticulos) {
		this.numArticulos = numArticulos;
	}
	/**
	 * @return precio total del pedido
	 */
	public double getPrecioTotal() {
		return precioTotal;
	}
	/**
	 *Guarda el precio total del pedido.
	 * @param precioTotal
	 */
	public void setPrecioTotal(double precioTotal) {
		this.precioTotal = precioTotal;
	}
	/** 
	 * @return si esta el pedido confirmado
	 */
	public boolean isConfirmado() {
		return confirmado;
	}
	/** Guarda la confirmacion del pedido
	 * @param confirmado
	 */
	public void setConfirmado(boolean confirmado) {
		this.confirmado = confirmado;
	}
	/**
	 * @return la fecha del pedido
	 */
	public String getFecha() {
		return fecha;
	}
	/**Guarda la fecha del pedido.
	 * @param fecha
	 */
	public void setFecha(String fecha) {
		this.fecha = fecha;
	}
	

}
