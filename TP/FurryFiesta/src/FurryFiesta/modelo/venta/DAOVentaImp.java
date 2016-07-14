package FurryFiesta.modelo.venta;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import FurryFiesta.modelo.bbdd.Conexion;
import FurryFiesta.modelo.productos.Producto;
import FurryFiesta.modelo.venta.Venta;

/**
 * @author Sergio
 * 
 * Esta clase se encarga de gestionar todas las conexiones 
 * las sentencias a la base de datos para el módulo Venta
 *
 */
public class DAOVentaImp implements DAOVentas {
	
	private Statement _stm;
	private ResultSet _rs;
	private PreparedStatement _st;

	/**
	 * Constructor de la clase
	 * 
	 */
	public DAOVentaImp() {
		
		_stm = null;
		_rs = null;
		_st = null;
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.venta.DAOVentas#insertarVenta(FurryFiesta.modelo.venta.Venta)
	 */
	@Override
	public void insertarVenta(Venta venta, ArrayList<Integer> idProductos, ArrayList<Integer> numProductos) throws Exception {

		Connection con = new Conexion().Conectar("", "");
		
		// Preparamos la sentencia SQL para insertar la Venta
		String insertarVenta = "INSERT INTO ventas (idPersonal, articulos, total, fecha) VALUES ("
				+ venta.getIdPersonal() + ","	+ venta.getArticulos() + ","
				+ venta.getTotal() + ",'" + venta.getFecha() + "')";

		// SQL para recuperar el id de la Venta
		String recuperarIdVenta = "SELECT id FROM ventas where idPersonal = "
				+ venta.getIdPersonal() + " AND fecha = '" + venta.getFecha() + "'";

		try {
			// Establecemos conexión para la sentencia SQL
			_stm = con.createStatement();
			// Ejecutamos la sentencia insertarVenta
			_stm.executeUpdate(insertarVenta);

			// Recuperamos el id de la Venta
			_st = con.prepareStatement(recuperarIdVenta);
			_rs = _st.executeQuery();

			// Obtenemos el primer registro
			_rs.next();
			venta.setId(_rs.getInt("id"));
			
			String insertarProducto = "";
			for (int i = 0; i < idProductos.size(); i++) {

				insertarProducto = "INSERT INTO ventasproductos (idVenta, idProducto, cantidad) VALUES ("
						+ venta.getId() + "," + idProductos.get(i) + "," + numProductos.get(i) + ")";

				// Ejecutamos la sentencia invertarProductos
				_stm.executeUpdate(insertarProducto);
			}
		} catch (SQLException ex) {
			throw new Exception(ex);
		} finally {
			if (con != null) {
				try {
					// Cerramos las conexiones con la base de datos
					_stm.close();
					_st.close();
					con.close();
				} catch (Exception e) {
					throw new Exception(e);
				}
			}
		}
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.venta.DAOVentas#consultarVenta(int)
	 */
	@Override
	public Venta consultarVenta(int id) throws Exception {

		Connection con = new Conexion().Conectar("", "");
		Venta venta = null;
        
        try
        {
            // Preparamos la sentencia sql
            String consultarVenta = "SELECT * FROM ventas WHERE id = " + id;
            
            _st = con.prepareStatement(consultarVenta);
            _rs = _st.executeQuery();
            
            _rs.next();
            venta = new Venta(_rs.getInt("id"), _rs.getInt("idPersonal"), 
            		_rs.getInt("articulos"), _rs.getDouble("total"), _rs.getString("fecha"));
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
        
        return venta;
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.venta.DAOVentas#consultarProductosVenta(int)
	 */
	@Override
	public ArrayList<Object> consultarProductosVenta(int id) throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		ArrayList<Object> listaProductos = new ArrayList<Object>();
		
		// Preparamos la sentencia sql
        String consultarVentas = "SELECT idProducto FROM ventasproductos WHERE idVenta = " + id;
        
        try {
            _st = con.prepareStatement(consultarVentas);
            _rs = _st.executeQuery();

        	ResultSet rs = null;
            PreparedStatement st = null;
            	
            while(_rs.next()) {
            	
                st = con.prepareStatement("SELECT * FROM productos WHERE id = " + _rs.getInt("idProducto"));
            	rs = st.executeQuery();
            	
            	rs.next();
            	
            	listaProductos.add(new Producto(_rs.getInt("idProducto"), rs.getString("nombre"), rs.getString("categoria"), 
            			rs.getString("tipo"), rs.getString("descripcion"), rs.getDouble("precio"), rs.getInt("stock")));
            }
            
            st.close();
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
		
		return listaProductos;
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.venta.DAOVentas#historicoVentas()
	 */
	@Override
	public ArrayList<Object> historicoVentas() throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		
		ArrayList<Object> listaVentas = new ArrayList<Object>();
		
		// Preparamos la sentencia sql
        String consultarVentas = "SELECT * FROM ventas";
        
        try {
            _st = con.prepareStatement(consultarVentas);
            _rs = _st.executeQuery();
            	
            while(_rs.next()) 
            	listaVentas.add(new Venta(_rs.getInt("id"), _rs.getInt("idPersonal"), 
            		_rs.getInt("articulos"), _rs.getDouble("total"), _rs.getString("fecha")));
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
		
		return listaVentas;
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.venta.DAOVentas#consultarPersonal(String)
	 */
	@Override
	public int consultarPersonal(String idTerminal) throws Exception {

		Connection con = new Conexion().Conectar("", "");
		int idPersonal;
		
		// Preparamos la sentencia sql
        String consultarId = "SELECT idPersonal FROM conexiones WHERE idTerminal = '" + idTerminal + "'";
        
        try {
            _st = con.prepareStatement(consultarId);
            _rs = _st.executeQuery();

        	_rs.next();
            	
        	idPersonal = _rs.getInt("idPersonal");
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
		
		return idPersonal;	
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.venta.DAOVentas#calcularTotalVentas()
	 */
	@Override
	public double calcularTotalVentas() throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		double totalVentas = 0;
        
        try
        {
            // Preparamos la sentencia sql
            String consultarTotalVentas = "SELECT total FROM ventas";
            
            _st = con.prepareStatement(consultarTotalVentas);
            _rs = _st.executeQuery();
            
            while(_rs.next())
            	totalVentas = totalVentas + _rs.getDouble("total");
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
        
        return totalVentas;
	}
}
