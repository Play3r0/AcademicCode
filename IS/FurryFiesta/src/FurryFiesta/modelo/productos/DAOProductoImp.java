package FurryFiesta.modelo.productos;

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
 * las sentencias SQL a la base de datos en el módulo Producto
 *
 */
public class DAOProductoImp implements DAOProductos {
	
	private Statement _stm;
	private ResultSet _rs;
    private PreparedStatement _st;
    
    /**
     * Constructor de la clase
     * 
     */
    public DAOProductoImp() {
    	
    	_stm = null;
    	_rs = null;
    	_st = null;
    }
	
    /* (non-Javadoc)
     * @see FurryFiesta.modelo.productos.DAOProductos#insertaProducto(FurryFiesta.modelo.productos.Producto)
     */
    @Override
	public void insertaProducto(Producto producto) throws Exception {
    	
    	Connection con = new Conexion().Conectar("", "");
    	
        // Preparamos la sentencia SQL
    	String insertarProducto = "INSERT INTO productos (nombre, categoria, tipo, descripcion, precio, stock, disponible) VALUES ('" 
        + producto.getNombre() +  "','" + producto.getCategoria() + "','" + producto.getTipo() + "','" + producto.getDescripcion() + "'," 
    			+ producto.getPrecio() + ", " + producto.getStock() + ", " + producto.getDisponible() + ")";

        try {
        	// Establecemos conexión para la sentencia SQL 
        	_stm = con.createStatement();
            // Ejecutamos la sentencia SQL
        	_stm.executeUpdate(insertarProducto);
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
     * @see FurryFiesta.modelo.productos.DAOProductos#eliminaProducto(java.lang.Integer)
     */
    @Override
	public void eliminaProducto(Integer id) throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
        
        // Preparamos la sentencia sql
        String eliminarProducto = "UPDATE productos SET disponible = " + false + " WHERE id = " + id;

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
     * @see FurryFiesta.modelo.productos.DAOProductos#buscaProducto(java.lang.Integer)
     */
    @Override
	public Producto buscaProducto(Integer id) throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		Producto producto = null;
        
        try
        {
            // Preparamos la sentencia sql
            String buscarProducto = "SELECT * FROM productos WHERE id = " + id;
            
            _st = con.prepareStatement(buscarProducto);
            _rs = _st.executeQuery();
            
            // Obtengo el primer registro	
            _rs.next();
            producto = new Producto(_rs.getInt("id"), _rs.getString("nombre"), _rs.getString("categoria"), 
            		_rs.getString("tipo"), _rs.getString("descripcion"), _rs.getDouble("precio"), _rs.getInt("stock"));
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
        
        return producto;
	}
	
    /* (non-Javadoc)
     * @see FurryFiesta.modelo.productos.DAOProductos#buscaProductoNombre(java.lang.String)
     */
    @Override
	public Producto buscaProductoNombre(String nombre) throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		Producto producto = null;
        
        try
        {
            // Preparamos la sentencia sql
            String buscarProducto = "SELECT * FROM productos WHERE nombre = '" + nombre + "'";
            
            _st = con.prepareStatement(buscarProducto);
            _rs = _st.executeQuery();
            
            // Obtengo el primer registro	
            _rs.next();
            producto = new Producto(_rs.getInt("id"), nombre, _rs.getString("categoria"), 
            		_rs.getString("tipo"), _rs.getString("descripcion"), _rs.getDouble("precio"), _rs.getInt("stock"), _rs.getBoolean("disponible"));
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
        
        return producto;
	}
	
    /* (non-Javadoc)
     * @see FurryFiesta.modelo.productos.DAOProductos#consultarProductosCategoria(java.lang.String)
     */
    @Override
	public ArrayList<Object> consultarProductosCategoria(String categoria) throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		ArrayList<Object> listaProductos = new ArrayList<Object>();
		
		// Preparamos la sentencia sql
        String consultarProductos = "SELECT * FROM productos WHERE categoria = '" + categoria + "'";
        
        try {
            _st = con.prepareStatement(consultarProductos);
            _rs = _st.executeQuery();
            
            // Obtengo el primer registro	
            while(_rs.next())  listaProductos.add(new Producto(_rs.getInt("id"), _rs.getString("nombre"), categoria, 
            		_rs.getString("tipo"), _rs.getString("descripcion"), _rs.getDouble("precio"), _rs.getInt("stock"), _rs.getBoolean("disponible")));
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
        
        return listaProductos;
	}

    /* (non-Javadoc)
     * @see FurryFiesta.modelo.productos.DAOProductos#consultarProductos()
     */
    @Override
	public ArrayList<Object> consultarProductos() throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		ArrayList<Object> listaProductos = new ArrayList<Object>();
		
		// Preparamos la sentencia sql
        String consultarProductos = "SELECT * FROM productos";
        
        try {
            _st = con.prepareStatement(consultarProductos);
            _rs = _st.executeQuery();
            
            // Obtengo el primer registro	
            while(_rs.next())  listaProductos.add(new Producto(_rs.getInt("id"), _rs.getString("nombre"), _rs.getString("categoria"), 
            		_rs.getString("tipo"), _rs.getString("descripcion"), _rs.getDouble("precio"), _rs.getInt("stock"), _rs.getBoolean("disponible")));
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
        
        return listaProductos;
	}
	
    /* (non-Javadoc)
     * @see FurryFiesta.modelo.productos.DAOProductos#modificaProducto(FurryFiesta.modelo.productos.Producto)
     */
    @Override
	public void modificaProducto(Producto producto) throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
		
		// Preparamos la sentencia sql
        String actualizarProducto = "UPDATE productos SET nombre = '" + producto.getNombre() + "', categoria = '" + producto.getCategoria() 
                + "', tipo = '" + producto.getTipo() + "', descripcion = '" + producto.getDescripcion()
                + "', precio = '" + producto.getPrecio() + "' WHERE id = " + producto.getId();

        try {
        	
           _stm = con.createStatement();
           _stm.executeUpdate(actualizarProducto);
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
     * @see FurryFiesta.modelo.productos.DAOProductos#actualizarStockPedido(int)
     */
    @Override
    public void actualizarStockPedido(int idPedido) throws Exception {
    	
		// Preparamos la sentencia sql
        String consultarPedidos = "SELECT idProducto, cantidad FROM pedidosproductos WHERE idPedido = " + idPedido;
        String operacion = "UPDATE productos SET stock = stock + ";
        
        actualizarStock(consultarPedidos, operacion);
	}
    
    /* (non-Javadoc)
     * @see FurryFiesta.modelo.productos.DAOProductos#actualizarStockVenta(int)
     */
    @Override
    public void actualizarStockVenta(int idVenta) throws Exception {
    	
		// Preparamos la sentencia sql
        String consultarPedidos = "SELECT idProducto, cantidad FROM ventasproductos WHERE idVenta = " + idVenta;
        String operacion = "UPDATE productos SET stock = stock - ";
        
        actualizarStock(consultarPedidos, operacion);
	}

    private void actualizarStock(String consultarPedidos, String operacion) throws Exception {
		
		Connection con = new Conexion().Conectar("", "");
        
        try {
            _st = con.prepareStatement(consultarPedidos);
            _rs = _st.executeQuery();
            	
            while(_rs.next()) {
            	
            	String actualizarProducto = operacion + _rs.getInt("cantidad") + " WHERE id = " + _rs.getInt("idProducto");

                try {
                	
                   _stm = con.createStatement();
                   _stm.executeUpdate(actualizarProducto);
                }
                catch (SQLException ex) {
                	throw new Exception(ex);
                }
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
	}
    
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.productos.DAOProductos#comprobarStockProductos(int, int)
	 */
	@Override
	public void comprobarStockProductos(int idProducto, int cantidad) throws Exception {

		Connection con = new Conexion().Conectar("", "");
		int stock = 0;
        
        try
        {
            // Preparamos la sentencia sql
            String buscarProducto = "SELECT stock FROM productos WHERE id = " + idProducto;
            
            _st = con.prepareStatement(buscarProducto);
            _rs = _st.executeQuery();
            
            // Obtengo el primer registro	
            _rs.next();
            stock = _rs.getInt("stock");
            
            if(stock < cantidad)
            	throw new Exception("El stock es insuficiente. Stock en el almacen: " + stock);
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
	}
}
