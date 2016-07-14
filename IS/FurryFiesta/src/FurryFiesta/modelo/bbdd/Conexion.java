package FurryFiesta.modelo.bbdd;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * @author Sergio
 *
 * Esta clase gestiona las conexiones a la base de datos
 * para el resto de módulos de la aplicación
 */
public class Conexion {
	
	private String _db; // Nombre de la base de datos
    private String _url; // Dirección de la base
    private Connection _con; // Conexión a la bbdd
    
    private String _usuario; // Nombre de usuario
    private String _password; // Password
	
	/**
	 * Constructor de la clase. Inicializa los 
	 * atributos para realizar la conexión
	 */
	public Conexion() {
		_db = "furryfiesta";
        _url = "jdbc:mysql://localhost/" + _db;
        _usuario = "root";
        _password = "";
       
        _con = null;
	}
	
	/**
	 * Este método se encarga de comprobar se conecta a la base de datos
	 * con los valores por defecto y devuelve una conexión.
	 * 
	 * @param user - Parámetro sin uso en esta versión
	 * @param pass - Parámetro sin uso en esta versión
	 * @return - Objeto Connection con la conexión establecida a la base de datos
	 */
	public Connection Conectar(String user, String pass) throws Exception {
		
		try {
            // Cargar driver
            Class.forName("com.mysql.jdbc.Driver");
           
            // Creamos un enlace hacia la BBDD
            _con = DriverManager.getConnection(this._url, this._usuario, this._password);
        }
        catch (SQLException e) {
                throw e;
        } catch (ClassNotFoundException e) {
			throw e;
        }
       
        return _con;
	}
}
