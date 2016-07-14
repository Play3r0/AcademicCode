package FurryFiesta.modelo.sesion;

import FurryFiesta.modelo.personal.Personal;
import FurryFiesta.vista.Observador;

/**
 * @author Jose Miguel y Carlos Basco
 *
 */
public class FachadaSesionImp implements FachadaSesion {

	private SASesionImp sas = new SASesionImp();
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.sesion.FachadaSesion#iniciarSesion(FurryFiesta.modelo.personal.Personal)
	 */
	@Override
	public void iniciarSesion(Personal personal) throws Exception {
		
		sas.iniciarSesion(personal);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.sesion.FachadaSesion#cambiarSesion(FurryFiesta.modelo.personal.Personal)
	 */
	@Override
	public void cambiarSesion(Personal personal) throws Exception {
		
		sas.cambiarSesion(personal);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.sesion.FachadaSesion#desconectarSesion(FurryFiesta.modelo.personal.Personal)
	 */
	@Override
	public void desconectarSesion() throws Exception {
	
		sas.desconectar();
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.sesion.FachadaSesion#addObserver(FurryFiesta.vista.Observador)
	 */
	@Override
	public void addObserver(Observador observador) {
		
		sas.addObserver(observador);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.sesion.FachadaSesion#cliente()
	 */
	@Override
	public void cliente() {
		
		this.sas.cliente();
	}

}
