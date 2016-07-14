package FurryFiesta.modelo.pedido;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import FurryFiesta.modelo.bbdd.Conexion;
import FurryFiesta.modelo.productos.Producto;

/**
 * @author Sergio
 * 
 * Esta clase se encarga de gestionar todas las conexiones para
 * las sentencias SQL a la base de datos en el módulo Pedido
 *
 */
public class DAOPedidoImp implements DAOPedidos{

	private Statement _stm;
	private ResultSet _rs;
    private PreparedStatement _st;
    
    /**
     * Constructor de la clase
     * 
     */
    public DAOPedidoImp() {
    	
    	_stm = null;
    	_rs = null;
    	_st = null;
    }
	
    /* (non-Javadoc)
     * @see FurryFiesta.modelo.pedido.DAOPedidos#agregarPedido(FurryFiesta.modelo.pedido.Pedido, java.util.ArrayList, java.util.ArrayList)
     */
    @Override
    public void agregarPedido(Pedido pedido, ArrayList<Integer> idProductos, ArrayList<Integer> numProductos) throws Exception {
    	
    	Connection con = new Conexion().Conectar("", "");
    	
    	// Preparamos la sentencia SQL
    	String insertarPedido = "INSERT INTO pedidos (idPersonal, articulos, total, confirmado, fecha) VALUES (" 
    			+ pedido.getIdPersonal() +  "," + pedido.getNumArticulos() + "," + pedido.getPrecioTotal() + ", " + false + " ,'" 
    			+ pedido.getFecha() + "')";
    	
    	// SQL para recuperar el id del Pedido
		String recuperarIdPedido = "SELECT id FROM pedidos where idPersonal = "
				+ pedido.getIdPersonal() + " AND fecha = '" + pedido.getFecha() + "'";

        try {
        	// Establecemos conexión para la sentencia SQL 
        	_stm = con.createStatement();
            // Ejecutamos la sentencia SQL
            _stm.executeUpdate(insertarPedido);
            
            // Recuperamos el id de la Venta
            _st = con.prepareStatement(recuperarIdPedido);
			_rs = _st.executeQuery();

			// Obtenemos el primer registro
			_rs.next();
			int idPedido = _rs.getInt("id");

			String insertarProducto = "";
			for (int i = 0; i < idProductos.size(); i++) {

				insertarProducto = "INSERT INTO pedidosproductos (idPedido, idProducto, cantidad) VALUES ("
						+ idPedido + "," + idProductos.get(i) + "," + numProductos.get(i) + ")";

				// Ejecutamos la sentencia invertarProductos
				_stm.executeUpdate(insertarProducto);
			}
        }
        catch (SQLException ex) {
        	throw new Exception(ex);
        }
        finally {
            if(con != null) {
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
     * @see FurryFiesta.modelo.pedido.DAOPedidos#comprobarPedido(int)
     */
    @Override
	public boolean comprobarPedido(int id) throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		boolean confirmado = false;
        
        try
        {
            // Preparamos la sentencia sql
            String consultarPedido = "SELECT * FROM pedidos WHERE id = " + id;
            
            _st = con.prepareStatement(consultarPedido);
            _rs = _st.executeQuery();
            
            _rs.next();
            confirmado = _rs.getBoolean("confirmado");
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
        
        return confirmado;
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.DAOPedidos#confirmarPerdido(int, boolean)
	 */
	@Override
	public void confirmarPerdido(int id, boolean confirmado) throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		
		// Preparamos la sentencia sql
        String confirmarPedido = "UPDATE pedidos SET confirmado =  " + confirmado + "  WHERE id = " + id;
        
        try {
        	
           _stm = con.createStatement();
           _stm.executeUpdate(confirmarPedido);
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
	 * @see FurryFiesta.modelo.pedido.DAOPedidos#consultarPedidos()
	 */
	@Override
	public ArrayList<Object> consultarPedidos() throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		ArrayList<Object> listaPedidos = new ArrayList<Object>();
		
		// Preparamos la sentencia sql
        String consultarPedidos = "SELECT * FROM pedidos";
        
        try {
            _st = con.prepareStatement(consultarPedidos);
            _rs = _st.executeQuery();
            	
            while(_rs.next())  
            	listaPedidos.add(new Pedido(_rs.getInt("id"), _rs.getInt("idPersonal"), 
            		_rs.getInt("articulos"), _rs.getDouble("total"), _rs.getBoolean("confirmado"), _rs.getString("fecha")));
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
		
		return listaPedidos;
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.DAOPedidos#consultarPedido(java.lang.Integer)
	 */
	@Override
	public Pedido consultarPedido(Integer id) throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		Pedido pedido = null;
        
        try
        {
            // Preparamos la sentencia sql
            String consultarPedido = "SELECT * FROM pedidos WHERE id = " + id;
            
            _st = con.prepareStatement(consultarPedido);
            _rs = _st.executeQuery();
            
            _rs.next();
            pedido = new Pedido(_rs.getInt("id"), _rs.getInt("idPersonal"), 
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
        
        return pedido;
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.pedido.DAOPedidos#eliminarPedido(int)
	 */
	@Override
	public void eliminarPedido(int id) throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
        
        // Preparamos la sentencia sql
        String eliminarProducto = "DELETE FROM pedidos WHERE id = " + id;

        try {
           _stm = con.createStatement();
           _stm.executeUpdate(eliminarProducto);
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
	 * @see FurryFiesta.modelo.pedido.DAOPedidos#consultarProductosPedido(int)
	 */
	@Override
	public ArrayList<Object> consultarProductosPedido(int id) throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		ArrayList<Object> listaProductos = new ArrayList<Object>();
		
		// Preparamos la sentencia sql
        String consultarPedidos = "SELECT idProducto FROM pedidosproductos WHERE idPedido = " + id;
        
        try {
            _st = con.prepareStatement(consultarPedidos);
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
	 * @see FurryFiesta.modelo.pedido.DAOPedidos#actualizarStockPedido(int)
	 */
	@Override
	public ArrayList<ArrayList<Integer>> actualizarStockPedido(int idPedido) throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		ArrayList<ArrayList<Integer>> listaProductos = new ArrayList<ArrayList<Integer>>();
		
		// Preparamos la sentencia sql
        String consultarPedidos = "SELECT idProducto, cantidad FROM pedidosproductos WHERE idPedido = " + idPedido;
        
        try {
            _st = con.prepareStatement(consultarPedidos);
            _rs = _st.executeQuery();

        	ArrayList<Integer> listaAux = new ArrayList<Integer>();
            
            while(_rs.next()) {
            	
            	listaAux.add(_rs.getInt("idProducto"));
            	listaAux.add(_rs.getInt("cantidad"));
            	
            	listaProductos.add(listaAux);
            }
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
	 * @see FurryFiesta.modelo.pedido.DAOPedidos#consultarPersonal(java.lang.String)
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
	 * @see FurryFiesta.modelo.pedido.DAOPedidos#calcularTotalVentas()
	 */
	@Override
	public double calcularTotalPedidos() throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		double totalVentas = 0;
        
        try
        {
            // Preparamos la sentencia sql
            String consultarTotalPedidos = "SELECT total FROM pedidos WHERE confirmado = " + 1;
            
            _st = con.prepareStatement(consultarTotalPedidos);
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

