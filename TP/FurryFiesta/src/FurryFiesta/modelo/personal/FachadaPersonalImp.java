package FurryFiesta.modelo.personal;

import FurryFiesta.vista.Observador;

/**
 * @author Jose Miguel y Carlos Basco
 *
 */
public class FachadaPersonalImp implements FachadaPersonal{
	
	private SAPersonalImp _sap = new SAPersonalImp();
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.personal.FachadaPersonal#altaPersonal(FurryFiesta.modelo.personal.Personal)
	 */
	@Override
	public void altaPersonal(Personal p) throws Exception {
		
		this._sap.altaPersonal(p);
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.personal.FachadaPersonal#bajaPersonal(int)
	 */
	@Override
	public void bajaPersonal(int id) throws Exception {
		
		this._sap.bajaPersonal(id);
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.personal.FachadaPersonal#modificarPersonal(FurryFiesta.modelo.personal.Personal)
	 */
	@Override
	public void modificarPersonal(Personal p) throws Exception {
		
		this._sap.modificarPersonal(p);
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.personal.FachadaPersonal#consultarDatosPersonales(int)
	 */
	@Override
	public void consultarDatosPersonales(int id) throws Exception {
		
		this._sap.consultarDatosPersonales(id);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.personal.FachadaPersonal#addObserver(FurryFiesta.vista.Observador)
	 */
	@Override
	public void addObserver(Observador observador) {
		this._sap.addObserver(observador);
		
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.personal.FachadaPersonal#inicializa()
	 */
	public void inicializa() throws Exception 
	{
		this._sap.inicializa();
	}
}
