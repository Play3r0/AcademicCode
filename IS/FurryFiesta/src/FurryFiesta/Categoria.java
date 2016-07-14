package FurryFiesta;

public enum Categoria 
{
	ANIMAL("animal"), 
	COMIDA("comida"), 
	BEBIDA("bebida"), 
	ACCESORIO("accesorio"),
	TODOS("todos");
	
	private String nombre;

	private Categoria (String t)
	{
		this.nombre = t;
	}
	
	public String getTexto()
	{
		return this.nombre;
	}
	
	public static Categoria getCategoria(String f)
	{
		Categoria ret = null;
		
		if (f.equals("animal"))
			ret = ANIMAL;
		if (f.equals("comida"))
			ret = COMIDA;
		if (f.equals("bebida"))
			ret = BEBIDA;
		if (f.equals("accesorio"))
			ret = ACCESORIO;
		return ret;
	}
	
}
