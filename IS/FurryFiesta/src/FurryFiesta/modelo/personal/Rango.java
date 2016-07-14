package FurryFiesta.modelo.personal;

/**
 * @author Jose Miguel y Carlos Basco
 *
 */
public enum Rango{
		   ADMINISTRADOR, EMPLEADO, CLIENTE;
	

		/**
		 * Metodo que devuelve el rango correspondiente al String introducido
		 * @param entrada - string del que se desea obtener su Rango
		 * @return rango correspondiente
		 */
		public static Rango toRango (String entrada)
		{
			   Rango salida;
			   
			   entrada = entrada.toLowerCase();
			   
			   if (entrada.equals("administrador")) 
				   salida = Rango.ADMINISTRADOR;
			   else if (entrada.equals("empleado")) 
				   salida = Rango.EMPLEADO;
			   else 
				   salida = Rango.CLIENTE;
			   
			   return salida;
		}
		
		/**
		 * Metodo que devuelve el String correspondiente al Rango introducido
		 * @param entrada - string del que se desea obtener su Rango
		 * @return string correspondiente
		 */
		public static String toString (Rango entrada)				//No es necesaria
		{
			   String salida;
			   
			   if (entrada.equals(Rango.ADMINISTRADOR)) 
				   salida = "ADMINISTRADOR";
			   else  if (entrada.equals(Rango.EMPLEADO)) 
				   salida = "EMPLEADO";
			   else salida = "CLIENTE";
			   
			   return salida;
		}
	
	
}
