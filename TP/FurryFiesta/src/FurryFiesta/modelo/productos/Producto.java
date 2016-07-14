package FurryFiesta.modelo.productos;

/**
 * @author Omar LLundo & Enrique Laguna.
 *
 */
public class Producto {

	private int id;
	private String nombre;
	private String categoria;
	private String tipo;
	private String descripcion;
	private double precio;
	private int stock;
	private boolean disponible;

	/**Constructor 1 de la clase producto.
	 * @param nombre - nombre del producto.
	 * @param categoria - categoria del producto.
	 * @param tipo - tipo del producto.
	 * @param descripcion - descripcion del producto.
	 * @param precio - precio del producto.
	 */
	public Producto(String nombre, String categoria, String tipo, String descripcion, double precio) {

		this.stock = 0;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.tipo = tipo;
		this.precio = precio;
		this.disponible = true;
	}
	
	/**
	 * Constructor 2 de la clase producto.
	 * @param idProducto - identificador del producto.
	 * @param nombre - nombre del producto.
	 * @param categoria - categoria del producto.
	 * @param tipo - tipo del producto.
	 * @param descripcion - descripcion del producto.
	 * @param precio - precio del producto.
	 * @param stockP - cantidad del producto para stock.
	 */
	public Producto(int idProducto, String nombre, String categoria, String tipo, String descripcion,
			double precio, int stockP) {

		this.stock = stockP;
		this.id = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.tipo = tipo;
		this.precio = precio;
		this.disponible = true;
	}

	/**
	 * Constructor 3 de la clase producto.
	 * @param idProducto - identificador del producto.
	 * @param nombre - nombre del producto.
	 * @param categoria - categoria del producto.
	 * @param tipo - tipo del producto.
	 * @param descripcion - descripcion del producto.
	 * @param precio - precio del producto.
	 * @param stockP - cantidad del producto para stock.
	 * @param disponible - disponibilidad del producto.
	 */
	public Producto(int idProducto, String nombre, String categoria, String tipo, String descripcion,
			double precio, int stockP, boolean disponible) {

		this.stock = stockP;
		this.id = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.tipo = tipo;
		this.precio = precio;
		this.disponible = disponible;
	}
	
	/**
	 * Constructor 4 de la clase producto.
	 * @param idProducto - identificador del producto.
	 * @param nombre - nombre del producto.
	 * @param categoria - categoria del producto.
	 * @param tipo - tipo del producto.
	 * @param descripcion - descripcion del producto.
	 * @param precio - precio del producto.
	 */
	public Producto(int idProducto, String nombre, String categoria, String tipo, String descripcion,
			double precio) {

		this.id = idProducto;
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.categoria = categoria;
		this.tipo = tipo;
		this.precio = precio;
		this.disponible = true;
	}

	/**
	 * @return el id del producto
	 */
	public int getId() {
		return id;
	}
	
	/**
	 * @return el nombre del producto.
	 */
	public String getNombre() {

		return this.nombre;
	}

	/** guarda un nombre de producto.
	 * @param nombre 
	 */
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	/**
	 * @return la descripcion del producto.
	 */
	public String getDescripcion() {

		return this.descripcion;
	}

	/** guarda una descripcion del producto.
	 * @param descripcion
	 */
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}

	/** 
	 * @return la categoria del producto.
	 */
	public String getCategoria() {
		return this.categoria;
	}

	/** Guarda la categoria del producto.
	 * @param categoria
	 */
	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	/**
	 * @return el tipo del producto.
	 */
	public String getTipo() {

		return this.tipo;
	}

	/** Guarda el tipo del producto.
	 * @param tipo
	 */
	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	/**
	 * @return el precio del producto.
	 */
	public double getPrecio() {

		return this.precio;
	}

	/** Guarda el precio del producto.
	 * @param precio
	 */
	public void setPrecio(double precio) {
		this.precio = precio;
	}

	/**
	 * @return numero de productos en stock
	 */
	public int getStock() {
		return stock;
	}

	/**
	 * Guarda un numero de stock del producto.
	 * @param stock
	 */
	public void setStock(int stock) {
		this.stock = stock;
	}

	/**
	 * @return disponibilidad del producto.
	 */
	public boolean getDisponible() {
		return this.disponible;
	}
	
	/**
	 * Guarda la disponibilidad del producto.
	 * @param disponible
	 */
	public void setDisponible(boolean disponible) {
		this.disponible = disponible;
	}
}
