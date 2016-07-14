package FurryFiesta.modelo.personal;

import java.util.ArrayList;

/**
 * @author Sergio
 * 
 * Esta interfaz se encarga de enumerar todos los métodos necesarios 
 * para la comunicación con la base de datos en el módulo Personal.
 *
 */
public interface DAOPersonal {

	/**
	 * Da de alta un nuevo personal recibido por parámetros
	 * 
	 * @param personal - Personal para dar de alta
	 * @throws Exception - En caso de que no se haya podido dar de alta el Personal
	 */
	public void altaPersonal(Personal personal) throws Exception;
	
	/**
	 * Este método da de baja un Personal a través de su identificador
	 * 
	 * @param id - Identificador del Personal
	 * @throws Exception - Si no se puede dar de baja en la base de datos
	 */
	public void bajaPersonal(int id) throws Exception;
	
	/**
	 * Este método se encarga de buscar un Personal
	 * en la base de datos a través de su identifcador.
	 * 
	 * @param id - Identificaro del Personal
	 * @return - Devuelve el Personal asociado al identificador
	 * @throws Exception - Si no encuentra al Personal en la base de datos
	 */
	public Personal buscaPersonal(Integer id) throws Exception;
	
	/**
	 * Recoge la información del Personal
	 * 
	 * @param nombre - Nombre del personal para realizar la consulta
	 * @return - Los datos completos del Personal
	 * @throws Exception - Si no se han podido recoger los datos
	 */
	public Personal buscarPersonalNombre(String nombre) throws Exception;
	
	/**
	 * Modifica un Personal en la base de datos
	 * 
	 * @param personal - Personal con los nuevos datos para actualizar en la base de datos
	 * @throws Exception - En caso de que no se pueda realizar la modificación
	 */
	public void modificarPersonal(Personal personal) throws Exception;
	
	/**
	 * Genera una lista con el Personal de un rango especifico
	 * 
	 * @param tipoDeEmpleado - Rango solicitado
	 * @return - Devuelve una lista con el personal
	 * @throws Exception - Si no se han podido obtener los datos
	 */
	public ArrayList<Object> consultarPersonalRango(Rango tipoDeEmpleado) throws Exception;
	
	/**
	 * Consulta los datos de un Personal
	 * 
	 * @param id - Identificador del Personal
	 * @return - Devuelve los datos de Personal asociado al identificador
	 * @throws Exception - Si no se pueden recuperar los datos del Personal
	 */
	public Personal consultarDatosPersonales(int id) throws Exception;
	
	/**
	 * Genera una lista con todos los usuarios registrados.
	 * 
	 * @return - Una lista con los usuarios
	 * @throws Exception - Si no se puede generar la lista
	 */
	public ArrayList<Object> consultarPersonal() throws Exception;
	
}
