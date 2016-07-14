package FurryFiesta.modelo.personal;

/**
 * @author Jose Miguel y Carlos Basco
 *
 */
public class Personal {

	private int _id;
	private String _nombre;
	private String _apellidos;
	private int _edad;
	private String _dni;
	private Rango _tipoDeEmpleado;
	private String _usuario;
	private String _password;
	private boolean _disponible;

	
	
	/**
	 * Método para crear un Personal incluyendo los parametros habituales
	 * utilizado para el alta y el inicio y cambio de sesión
	 * @param nombre nombre del Personal
	 * @param apellidos apellidos del Personal
	 * @param edad edad del Personal
	 * @param dni dni del Personal
	 * @param tipoDeEmpleado Rango del Personal
	 * @param usuario nombre de usuario del Personal
	 * @param password contraseña del Personal
	 */
	public Personal(String nombre, String apellidos, int edad, String dni, Rango tipoDeEmpleado, String usuario, String password)
	{
		_nombre = nombre;
		_apellidos = apellidos;
		_edad = edad;
		_dni = dni;
		_tipoDeEmpleado = tipoDeEmpleado;
		_usuario = usuario;
		_password = password;
		_disponible = true;
	}
	
	/**
	 * Método para crear un Personal incluyendo ademas de los parametros habituales el de id y un booleano con la disponibilidad 
	 * utilizado para la busqueda y la consulta de personal en DAOPersonal
	 * @param id id del Personal
	 * @param nombre nombre del Personal
	 * @param apellidos apellidos del Personal
	 * @param edad edad del Personal
	 * @param dni dni del Personal
	 * @param tipoDeEmpleado Rango del Personal
	 * @param usuario nombre de usuario del Personal
	 * @param password contraseña del Personal
	 * @param disponible disponibilidad del Personal
	 */
	public Personal(int id, String nombre, String apellidos, int edad, String dni, Rango tipoDeEmpleado, String usuario, String password, boolean disponible)
	{
		_id = id;
		_nombre = nombre;
		_apellidos = apellidos;
		_edad = edad;
		_dni = dni;
		_tipoDeEmpleado = tipoDeEmpleado;
		_usuario = usuario;
		_password = password;
		_disponible = disponible;
	}
	
	/**
	 * Método para crear un Personal incluyendo ademas de los parametros habituales el de id
	 * @param id id del Personal
	 * @param nombre nombre del Personal
	 * @param apellidos apellidos del Personal
	 * @param edad edad del Personal
	 * @param dni dni del Personal
	 * @param tipoDeEmpleado Rango del Personal
	 * @param usuario nombre de usuario del Personal
	 * @param password contraseña del Personal
	 */
	public Personal(int id, String nombre, String apellidos, int edad, String dni, Rango tipoDeEmpleado, String usuario, String password)
	{
		_id = id;
		_nombre = nombre;
		_apellidos = apellidos;
		_edad = edad;
		_dni = dni;
		_tipoDeEmpleado = tipoDeEmpleado;
		_usuario = usuario;
		_password = password;
	}
	
	/**
	 * Método para crear un Personal con los datos básicos.
	 * Utilizado a la hora de mostrar una lista de todos los usuarios.
	 * 
	 * @param id id del Personal
	 * @param nombre nombre del Personal
	 * @param apellidos apellidos del Personal
	 * @param rango Rango del Personal
	 */
	public Personal(int id, String nombre, String apellidos, Rango rango) {
		
		_id = id;
		_nombre = nombre;
		_apellidos = apellidos;
		_tipoDeEmpleado = rango;
	}

	/**
	 * Devuelve el id del personal
	 * @return id
	 */
	public int getId() {
		return _id;
	}

	/**
	 * Establece el id del personal
	 * @param id
	 */
	public void setId(int id) {
		this._id = id;
	}

	/**
	 * Devuelve el nombre del personal
	 * @return nombre
	 */
	public String getNombre() {
		return _nombre;
	}

	/**
	 * Establece el nombre del personal
	 * @param nombre
	 */
	public void setNombre(String nombre) {
		this._nombre = nombre;
	}

	/**
	 * Devuelve los apellidos del personal
	 * @return apellidos
	 */
	public String getApellidos() {
		return _apellidos;
	}

	/**
	 * Establece los apellidos del personal
	 * @param apellidos
	 */
	public void setApellidos(String apellidos) {
		this._apellidos = apellidos;
	}

	/**
	 * Devuelve la edad del personal
	 * @return edad
	 */
	public int getEdad() {
		return _edad;
	}

	/**
	 * Establece la edad del personal
	 * @param edad
	 */
	public void setEdad(int edad) {
		this._edad = edad;
	}

	/**
	 * Devuelve el dni del personal
	 * @return dni
	 */
	public String getDni() {
		return _dni;
	}

	/**
	 * Establece el dni del personal
	 * @param dni
	 */
	public void setDni(String dni) {
		this._dni = dni;
	}

	/**
	 * Devuelve el tipo de empleado del personal
	 * @return tipoDeEmpleado
	 */
	public Rango getTipoDeEmpleado() {
		return _tipoDeEmpleado;
	}

	/**
	 * Establece el tipo de empleado del personal
	 * @param tipoDeEmpleado
	 */
	public void setTipoDeEmpleado(Rango tipoDeEmpleado) {
		this._tipoDeEmpleado = tipoDeEmpleado;
	}

	/**
	 * Devuelve el usuario del personal
	 * @return usuario
	 */
	public String getUsuario() {
		return _usuario;
	}

	/**
	 * Establece el usuario de personal
	 * @param usuario
	 */
	public void setUsuario(String usuario) {
		this._usuario = usuario;
	}

	/**
	 * Devuelve la contraseña del personal
	 * @return password
	 */
	public String getPassword() {
		return _password;
	}

	/**
	 * Establece la contraseña
	 * @param password
	 */
	public void setPassword(String password) {
		this._password = password;
	}
	
	/**
	 * Establece la disponibilidad del empleado
	 * @param disponible
	 */
	public void setDisponible(boolean disponible)
	{
		this._disponible = disponible;
	}
	
	/**
	 * Devuelve la disponibilidad del personal
	 * @return disponible
	 */
	public boolean getDisponible()
	{
		return this._disponible;
	}
}
