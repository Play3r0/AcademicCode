package FurryFiesta.modelo.personal;

import java.util.ArrayList;

import FurryFiesta.vista.Observador;

/**
 * @author Jose Miguel y Carlos Basco
 *
 */
public class SAPersonalImp  implements SAPersonal{

	private DAOPersonalImp daop = new DAOPersonalImp();
	private Observador observador;
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.personal.SAPersonal#altaPersonal(FurryFiesta.modelo.personal.Personal)
	 */
	@Override
	public void altaPersonal(Personal p) throws Exception
	{
		Personal personal;
		
		try
		{
			personal = this.daop.buscarPersonalNombre(p.getNombre());
			
			if (!personal.equals(null))
				throw new Exception("Ya existe");
			else
			{
				this.daop.altaPersonal(p);
				ArrayList<Object> array = this.daop.consultarPersonal();
				this.observador.actualizarLista(array);
			}
		}
		catch (Exception e)
		{
			if (e.getMessage().equals("Ya existe"))
				throw new Exception("Empleado ya existente");
			else
			{
				this.daop.altaPersonal(p);
				ArrayList<Object> array = this.daop.consultarPersonal();
				this.observador.actualizarLista(array);
			}
		}
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.personal.SAPersonal#bajaPersonal(int)
	 */
	@Override
	public void bajaPersonal(int id) throws Exception
	{
		
		this.daop.bajaPersonal(id);
		ArrayList<Object> array = this.daop.consultarPersonal();
		this.observador.actualizarLista(array);

	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.personal.SAPersonal#modificarPersonal(FurryFiesta.modelo.personal.Personal)
	 */
	@Override
	public void modificarPersonal(Personal p) throws Exception
	{
		ArrayList<Object> lista;
			
		this.daop.modificarPersonal(p);
		lista =  this.daop.consultarPersonalRango(p.getTipoDeEmpleado());
		this.observador.actualizarLista(lista);
	}
	
	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.personal.SAPersonal#consultarDatosPersonales(java.lang.Integer)
	 */
	@Override
	public void consultarDatosPersonales(Integer id) throws Exception
	{
		Personal personal = this.daop.consultarDatosPersonales(id);
		this.observador.actualizarDatos(personal);
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.personal.SAPersonal#addObserver(FurryFiesta.vista.Observador)
	 */
	@Override
	public void addObserver(Observador observador) {
		this.observador = observador;
	}

	/* (non-Javadoc)
	 * @see FurryFiesta.modelo.personal.SAPersonal#inicializa()
	 */
	public void inicializa() throws Exception 
	{
		ArrayList<Object> lista = this.daop.consultarPersonal();
		this.observador.actualizarLista(lista);
	}
}
