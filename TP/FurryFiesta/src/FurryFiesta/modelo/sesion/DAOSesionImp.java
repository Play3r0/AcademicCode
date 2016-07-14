package FurryFiesta.modelo.sesion;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;

import FurryFiesta.modelo.bbdd.Conexion;
import FurryFiesta.modelo.personal.Personal;
import FurryFiesta.modelo.personal.Rango;

/**
 * @author Sergio
 * 
 * Esta clase se encarga de gestionar todas las conexiones 
 * las sentencias a la base de datos para el módulo Personal
 *
 */
public class DAOSesionImp implements DAOSesion {

	private Statement _stm;
	private ResultSet _rs;
    private PreparedStatement _st;
    
    /**
     * Constructor de la clase
     * 
     */
    public DAOSesionImp() {
    	
    	_stm = null;
    	_rs = null;
    	_st = null;
    }
	
    /* (non-Javadoc)
     * @see FurryFiesta.modelo.sesion.DAOSesion#iniciarSesion(FurryFiesta.modelo.personal.Personal)
     */
    @Override
	public Personal iniciarSesion(Personal personal, String idTerminal) throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
        
        try
        {        	
            // Preparamos la sentencia sql
            String consultarUsuario = "SELECT * FROM personal WHERE usuario = '" + personal.getUsuario() + "' AND password = '" + personal.getPassword() + "'";
            
            _st = con.prepareStatement(consultarUsuario);
            _rs = _st.executeQuery();
            
            // Obtengo el primer registro	
            _rs.next();
            personal = new Personal(_rs.getInt("id"), _rs.getString("nombre"), _rs.getString("apellidos"), _rs.getInt("edad"),
            		_rs.getString("dni"), Rango.toRango(_rs.getString("tipo")), _rs.getString("usuario"), _rs.getString("password"));
        }
        catch (SQLException ex) {
        	throw new Exception(ex);
        }
        try
        {	        
	        // Obtenemos la fecha actual
	        Date dt = new Date();
	    	SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	    	String currentTime = sdf.format(dt);
	        
	        String insertarSesion = "INSERT INTO conexiones (idPersonal, idTerminal, conectado, fecha) VALUES (" + _rs.getInt("id") 
	        		+ ", '" + idTerminal + "', " + true + ", '" + currentTime + "')";
	        
	        // Establecemos conexión para la sentencia SQL 
	    	_stm = con.createStatement();
	        // Ejecutamos la sentencia SQL
	    	_stm.executeUpdate(insertarSesion);
        }
        catch (SQLException ex) {
        	throw new Exception(ex);
        }
        finally {
        	// Si la conexión todavía está abierta, la cerramos
            if(con != null) {
                try {
                    _stm.close();
                    con.close();
                }
                catch(Exception e) {
                	throw new Exception(e);
                }
            }                                         
        }
        
		return personal;
	}

    /* (non-Javadoc)
     * @see FurryFiesta.modelo.sesion.DAOSesion#cambiarSesion(FurryFiesta.modelo.personal.Personal)
     */
    @Override
	public Personal cambiarSesion(Personal personal, String idTerminal) throws Exception {
    	
    	desconectarSesion(idTerminal);
    	
		return iniciarSesion(personal, idTerminal);
	}

    /* (non-Javadoc)
     * @see FurryFiesta.modelo.sesion.DAOSesion#desconectarSesion(FurryFiesta.modelo.personal.Personal)
     */
    @Override
	public void desconectarSesion(String idTerminal) throws Exception {
		
    	Connection con = new Conexion().Conectar("", "");
    	
    	String nuevoIdTerminal = idTerminal +  " DES.";
    	
    	// Preparamos la sentencia sql
        String desconectar = "UPDATE conexiones SET idTerminal = '" + nuevoIdTerminal + "', "
        		+ " conectado = " + false + " WHERE idTerminal = '" + idTerminal + "'";

        try {
        	
           _stm = con.createStatement();
           _stm.executeUpdate(desconectar);
        }
        catch (SQLException ex) {
        	throw new Exception(ex);
        }
        finally {
            if(con != null) {
            	try {
                    _stm.close();
                    con.close();
                }
                catch(Exception e) {
                	throw new Exception(e);
                }
            }                                         
        }
	}
}
