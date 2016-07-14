#include"EntornoGrafico.h"
#include"GestorDatos.h"
#include"Contacto.h"
int main()
{
	/*No crea bien el arbol, los punteros en vez de apuntar a un objeto Nodo con la letra que la paso
	apuntan a un objeto Nodo vacio por lo que al intentar que me devuelva la letra salta un error, 
	como la funcion de buscar letra la utilizo en todos los casos no me deja hacer nada, esta todo el codigo hecho pero debido
	a ese fallo que no e podido solucionar no puedo comprobar si lo demas lo hace bien*/
	EntornoGrafico pantalla;
	GestorDatos datos;
	Contacto nuevo,viejo,c;
	int op;
	bool retorno;

	do{
		op=pantalla.menu();
		switch(op)
		{
			case 1:
				nuevo=pantalla.pedirNombre();
				retorno=datos.buscarContacto(nuevo);
				if(retorno==true)
				{
					c=datos.buscarContactoParaMostrar(nuevo);
					pantalla.mostrarContacto(c);
				}
				else pantalla.fallo(1);
				break;

			case 2:
				nuevo=pantalla.pedirNombre();
				retorno=datos.buscarContacto(nuevo);
				if(retorno==true)
				{
					viejo=datos.buscarContactoParaMostrar(nuevo);
					nuevo=pantalla.pedirModificacionContacto(viejo);
					datos.modificarContacto(viejo, nuevo);
				}
				else pantalla.fallo(1);
				break;

			case 3:
				nuevo=pantalla.pedirContacto();
				retorno=datos.buscarContacto(nuevo);
				if(retorno==false)
				{
					datos.añadirContacto(nuevo);
				}
				else pantalla.fallo(2);
				break;

			case 4:
				nuevo=pantalla.pedirContacto();
				retorno=datos.buscarContacto(nuevo);
				if(retorno==true)
				{
					datos.borrarContacto(nuevo);
				}
				else pantalla.fallo(1);
				break;

			case 5:
				pantalla.mostrarVectorContactos(datos.buscarCadenaContacto(pantalla.pedirCadena()));
				break;

			case 6:
				pantalla.mostrarNombreContactos(datos.buscarTodosContactos());
				break;
		}
	}while(op!=7);

	system("PAUSE");
	return 0;
}