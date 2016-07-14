package FurryFiesta.modelo.personal;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import FurryFiesta.modelo.bbdd.Conexion;

/**
 * @author Sergio
 * 
 * Esta clase se encarga de gestionar todas las conexiones para
 * las sentencias SQL a la base de datos en el módulo Personal
 *
 */
public class DAOPersonalImp implements DAOPersonal {
	
	private Statement _stm;
	private ResultSet _rs;
    private PreparedStatement _st;
    
    /**
     * Constructor de la clase
     * 
     */
    public DAOPersonalImp() {
    	
    	_stm = null;
    	_rs = null;
        _st = null;
    }
    
    /* (non-Javadoc)
     * @see FurryFiesta.modelo.personal.DAOPersonal#altaPersonal(FurryFiesta.modelo.personal.Personal)
     */
    @Override
    public void altaPersonal(Personal personal) throws Exception {
        
    	Connection con = new Conexion().Conectar("", "");
    	
    	// Preparamos la sentencia SQL
    	String insertarpersonal = "INSERT INTO personal (nombre, apellidos, edad, dni, tipo, usuario, password, disponible) VALUES ('" 
    			+ personal.getNombre() +  "','" + personal.getApellidos() + "'," + personal.getEdad() + ",'" + personal.getDni() + "','" 
    			+ personal.getTipoDeEmpleado().toString() + "','" + personal.getUsuario() + "','" + personal.getPassword() + "'," + personal.getDisponible() + ")";

        try {
        	// Establecemos conexión para la sentencia SQL 
        	_stm = con.createStatement();
            // Ejecutamos la sentencia SQL
            _stm.executeUpdate(insertarpersonal);
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
                    e.printStackTrace();
                }
            }                                         
        }
    }
	
    /* (non-Javadoc)
     * @see FurryFiesta.modelo.personal.DAOPersonal#bajaPersonal(int)
     */
    @Override
	public void bajaPersonal(int id) throws Exception
	{
		Connection con = new Conexion().Conectar("", "");
        
        // Preparamos la sentencia sql
        String bajaPersonal = "UPDATE personal SET disponible = " + false + " WHERE id = " + id;

        try {
           _stm = con.createStatement();
           _stm.executeUpdate(bajaPersonal);
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
	}
	
    /* (non-Javadoc)
     * @see FurryFiesta.modelo.personal.DAOPersonal#buscaPersonal(java.lang.Integer)
     */
    @Override
	public Personal buscaPersonal(Integer id) throws Exception
	{
		Connection con = new Conexion().Conectar("", "");
		Personal personal = null;
		
		try
		{
            // Preparamos la sentencia sql
            String buscarPersonal = "SELECT * FROM personal WHERE id = " + id;
            
            _st = con.prepareStatement(buscarPersonal);
            _rs = _st.executeQuery();
            
            // Obtengo el primer registro	
            _rs.next();
           personal = new Personal(_rs.getInt("id"), _rs.getString("nombre"), _rs.getString("apellidos"), _rs.getInt("edad"), _rs.getString("dni"), 
        		   Rango.toRango(_rs.getString("tipo")), _rs.getString("usuario"), _rs.getString("password"));
        }

        catch (SQLException ex) {
        	throw new Exception(ex);
        	}
        finally {
        	// Si la conexión todavía está abierta, la cerramos
            if(con != null) {
                try {
                    _st.close();
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
     * @see FurryFiesta.modelo.personal.DAOPersonal#modificarPersonal(FurryFiesta.modelo.personal.Personal)
     */
    @Override
	public void modificarPersonal(Personal personal) throws Exception
	{
		Connection con = new Conexion().Conectar("", "");
		
		// Preparamos la sentencia sql
        String modificaPersonal = "UPDATE personal SET nombre = '" + personal.getNombre() + "', apellidos = '" + personal.getApellidos() 
                + "', edad = " + personal.getEdad() + ", dni = '" + personal.getDni() + "', tipo = '" + personal.getTipoDeEmpleado() 
                + "', usuario = '" + personal.getUsuario() + "', password = '" + personal.getPassword() + "' WHERE id = " + personal.getId();
        
        try {
        	
           _stm = con.createStatement();
           _stm.executeUpdate(modificaPersonal);
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
	
    /* (non-Javadoc)
     * @see FurryFiesta.modelo.personal.DAOPersonal#buscarPersonalNombre(java.lang.String)
     */
    @Override
	public Personal buscarPersonalNombre(String nombre) throws Exception
	{
		Connection con = new Conexion().Conectar("", "");
		Personal personal = null;
        
        try
        {
            // Preparamos la sentencia sql
            String buscarPersonal = "SELECT * FROM personal WHERE nombre = '" + nombre + "'";
            
            _st = con.prepareStatement(buscarPersonal);
            _rs = _st.executeQuery();
            
            // Obtengo el primer registro	
            _rs.next();
            personal = new Personal(_rs.getInt("id"), _rs.getString("nombre"), _rs.getString("apellidos"), _rs.getInt("edad"), _rs.getString("dni"), 
         		   Rango.toRango(_rs.getString("tipo")), _rs.getString("usuario"), _rs.getString("password"), _rs.getBoolean("disponible"));
        }

        catch (SQLException ex) {
        	throw new Exception(ex);
        	}
        finally {
        	// Si la conexión todavía está abierta, la cerramos
            if(con != null) {
                try {
                    _st.close();
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
     * @see FurryFiesta.modelo.personal.DAOPersonal#consultarPersonalRango(FurryFiesta.modelo.personal.Rango)
     */
    @Override
	public ArrayList<Object> consultarPersonalRango(Rango tipoDeEmpleado) throws Exception
	{
		Connection con = new Conexion().Conectar("", "");
		ArrayList<Object> listaPersonal = new ArrayList<Object>();
		
		// Preparamos la sentencia sql
        String consultaPersonal = "SELECT * FROM personal WHERE tipo = '" + tipoDeEmpleado + "'" + "AND disponible = " + true + "";
        
        try {
            _st = con.prepareStatement(consultaPersonal);
            _rs = _st.executeQuery();
            	
            while(_rs.next())listaPersonal.add(new Personal(_rs.getInt("id"), _rs.getString("nombre"), _rs.getString("apellidos"), _rs.getInt("edad"), _rs.getString("dni"), 
              		   Rango.toRango(_rs.getString("tipo")), _rs.getString("usuario"), _rs.getString("password"), _rs.getBoolean("disponible")));
        }
        catch (SQLException ex) {
        	throw new Exception(ex);
        }
        finally {
            if(con != null) {
                try {
                    _st.close();
                    con.close();
                }
                catch(Exception e) {
                	throw new Exception(e);
                }
            }                                         
        }
        
        return listaPersonal;
	}
	
    /* (non-Javadoc)
     * @see FurryFiesta.modelo.personal.DAOPersonal#consultarPersonal()
     */
    @Override
	public ArrayList<Object> consultarPersonal() throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		ArrayList<Object> listaPersonal = new ArrayList<Object>();
		
		// Preparamos la sentencia sql
        String consultarPersonal = "SELECT * FROM personal";
        
        try {
            _st = con.prepareStatement(consultarPersonal);
            _rs = _st.executeQuery();
            
            // Obtengo el primer registro	
            while(_rs.next())  listaPersonal.add(new Personal(_rs.getInt("id"), _rs.getString("nombre"), _rs.getString("apellidos"), _rs.getInt("edad"), _rs.getString("dni"), 
          		   Rango.toRango(_rs.getString("tipo")), _rs.getString("usuario"), _rs.getString("password"), _rs.getBoolean("disponible")));
        }
        catch (SQLException ex) {
        	throw new Exception(ex);
        }
        finally {
        	// Si la conexión todavía está abierta, la cerramos
            if(con != null) {
                try {
                    _st.close();
                    con.close();
                }
                catch(Exception e) {
                	throw new Exception(e);
                }
            }                                         
        }
        
        return listaPersonal;
	}
	
    /* (non-Javadoc)
     * @see FurryFiesta.modelo.personal.DAOPersonal#consultarDatosPersonales(int)
     */
    @Override
	public Personal consultarDatosPersonales(int id) throws Exception
	{
		Connection con = new Conexion().Conectar("", "");
		Personal personal = null;
        
        try
        {
            // Preparamos la sentencia sql
            String consultarDatos = "SELECT * FROM personal WHERE id = " + id + " AND disponible = " + true;
            
            _st = con.prepareStatement(consultarDatos);
            _rs = _st.executeQuery();
            
            _rs.next();
            personal = new Personal(_rs.getInt("id"), _rs.getString("nombre"), _rs.getString("apellidos"), 
            		_rs.getInt("edad"), _rs.getString("dni"), Rango.toRango(_rs.getString("tipo")), _rs.getString("usuario"), _rs.getString("password"));
        }

        catch (SQLException ex) {
        	throw new Exception(ex);
        }
        finally {
        	// Si la conexión todavía está abierta, la cerramos
            if(con != null) {
                try {
                    _st.close();
                    con.close();
                }
                catch(Exception e) {
                	throw new Exception(e);
                }
            }                                         
        }
        
        return personal;
	}
}
