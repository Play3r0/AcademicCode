package FurryFiesta.modelo.venta;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.net.InetAddress;
import java.util.ArrayList;
import FurryFiesta.vista.Observador;


public class SAVentasImp implements SAVentas{

	private DAOVentaImp daov = new DAOVentaImp();
	private ArrayList<Observador> _observador = new ArrayList<Observador>();
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.venta.SAVentas#agregarVentaSA(FurryFiesta.modelo.venta.Venta, java.util.ArrayList, java.util.ArrayList)
	 */
	@Override
	public void agregarVentaSA(Venta v, ArrayList<Integer> idProductos, ArrayList<Integer> lista1) throws Exception {
		
		// Obtener identificador único para el terminal
        InetAddress addr;
        addr = InetAddress.getLocalHost();
        String idTerminal = addr.getHostName();
        
		int idPersonal = daov.consultarPersonal(idTerminal);
		
		v.setIdPersonal(idPersonal);
		this.daov.insertarVenta(v, idProductos, lista1);
		ArrayList<Object> array = this.daov.historicoVentas();
		for (Observador o : this._observador)
			o.actualizarLista(array);
		
	}	
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.venta.SAVentas#consultarVentaSA(java.lang.Integer)
	 */
	@Override
	public void consultarVentaSA(Integer id) throws Exception {

		Venta venta = this.daov.consultarVenta(id);
		ArrayList<Object> array = this.daov.consultarProductosVenta(venta.getId());
		for (Observador o : this._observador)
			o.actualizarListaSecundaria(array);
	}	
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.venta.SAVentas#exportarVentaSA(java.lang.String, java.lang.String)
	 */
	@Override
	public void exportarVentaSA(String direccion, String nombreFichero) throws Exception {
		
		
		BufferedWriter bfWriter = null;
		File archivo = null;
		try{
			
			//inizializo los strings
			ArrayList<Object> hist = daov.historicoVentas();			
			String nomFichero,direccionAux;
	        
			//compruebo los directorios
	        if (nombreFichero== "" || nombreFichero == null)
	        	nomFichero = "Historico";
	        else
	        	nomFichero=nombreFichero;
	        
	        if (direccion == null || direccion == "")
	        	direccionAux = "C:\\";
	        else
	        	direccionAux = direccion;
	        
	        
	        //crea la direccion del fichero
	        archivo = new File(direccionAux + "\\" + nomFichero + ".txt");	        
	        archivo.createNewFile();
	        
	        
	        //escribo en el fichero
	        String histaux = "";
	        for (int i = 0; i<hist.size();i++){
	        	histaux = hist.get(i).toString() + System.lineSeparator(); 
	        }
	        bfWriter = new BufferedWriter(new FileWriter(archivo));
	        bfWriter.write(histaux);
	        	        
	       
	       
		}catch(IOException e){
			throw new Exception("Error al escribir en el archivo");
			
		}catch(Exception e){
			throw new Exception("Error con la base de datos");
			
		}finally {
			try {          
				if(bfWriter != null)
					bfWriter.close();
				
			}catch (Exception e2) {
				throw new Exception("Error al cerrar el buffer de escritura");
		    }
		}		
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.venta.SAVentas#historicoVentasSA()
	 */
	@Override
	public void historicoVentasSA() throws Exception {
		
		ArrayList<Object> listaVentas = this.daov.historicoVentas();
		for (Observador o : this._observador)
			o.actualizarLista(listaVentas);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.venta.SAVentas#addObserver(FurryFiesta.vista.Observador)
	 */
	@Override
	public void addObserver(Observador observador) {
		this._observador.add(observador);
		
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.venta.SAVentas#inicializa()
	 * 
	 */
	@Override
	public void inicializa() throws Exception {
		ArrayList<Object> lista = this.daov.historicoVentas();
		for (Observador o : this._observador)
			o.actualizarLista(lista);
		
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.venta.SAVentas#calcularTotalVentas()
	 */
	@Override
	public void calcularTotalVentas() throws Exception {
		double cantidad = this.daov.calcularTotalVentas();
		for (Observador o : this._observador)
			o.ventasTotal(cantidad);
	}	
}