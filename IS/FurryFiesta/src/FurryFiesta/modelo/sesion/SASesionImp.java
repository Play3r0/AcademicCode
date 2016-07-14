package FurryFiesta.modelo.sesion;

import java.net.InetAddress;
import java.util.ArrayList;

import FurryFiesta.modelo.personal.Personal;
import FurryFiesta.modelo.personal.Rango;
import FurryFiesta.vista.Observador;

/**
 * @author Jose Miguel y Carlos Basco
 *
 */
public class SASesionImp implements SASesion{

	DAOSesionImp _daos = new DAOSesionImp();
	private ArrayList<Observador> _observador = new ArrayList<Observador>();
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.sesion.SASesion#iniciarSesion(FurryFiesta.modelo.personal.Personal)
	 */
	@Override
	public void iniciarSesion(Personal personal) throws Exception {

		// Obtener identificador único para el terminal
        InetAddress addr;
        addr = InetAddress.getLocalHost();
        String idTerminal = addr.getHostName();
        
		Personal per = _daos.iniciarSesion(personal, idTerminal);
		
		if (per.getPassword().equals(personal.getPassword()))
		{
			for (Observador o : this._observador)
				o.actualizarMenu(per.getTipoDeEmpleado());
		}
		else
			throw new Exception("Contraseña inválida");
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.sesion.SASesion#cambiarSesion(FurryFiesta.modelo.personal.Personal)
	 */
	@Override
	public void cambiarSesion(Personal personal) throws Exception {
		
		// Obtener identificador único para el terminal
        InetAddress addr;
        addr = InetAddress.getLocalHost();
        String idTerminal = addr.getHostName();		
		
        Personal per = _daos.cambiarSesion(personal, idTerminal);
        
        if (per.getPassword().equals(personal.getPassword()))
		{
			for (Observador o : this._observador)
				o.actualizarMenu(per.getTipoDeEmpleado());
		}
		else
			throw new Exception("Contraseña inválida");
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.sesion.SASesion#desconectar(FurryFiesta.modelo.personal.Personal)
	 */
	@Override
	public void desconectar() throws Exception {
		
		// Obtener identificador único para el terminal
        InetAddress addr;
        addr = InetAddress.getLocalHost();
        String idTerminal = addr.getHostName();
		
		_daos.desconectarSesion(idTerminal);
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.sesion.SASesion#addObserver(FurryFiesta.vista.Observador)
	 */
	public void addObserver(Observador observador) {

		_observador.add(observador);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.sesion.SASesion#cliente()
	 */
	@Override
	public void cliente() 
	{
		for (Observador o : this._observador)
			o.actualizarMenu(Rango.CLIENTE);
	}
}
