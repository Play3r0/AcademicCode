
//Actividad 1.4 - Sergio L�pez Jurado

#include "EntornoGrafico.h"
#include "GestorAD.h"
#include "Contacto.h"


int main()
{
	GestorAD gestor;
	EntornoGrafico entorno;
	Contacto c1;

	int menu = 0;
	bool estado = false;
	string nombre = "";
	char letra = ' ';
	
	if(gestor.cargarDatos())		// Llamada al GestorAD para cargar el sistema de �ndices en "Indices.dat"
		entorno.mostrarMensaje("EL sistema de �ndices ha sido cargado correctamente");
	else entorno.mostrarMensaje("AVISO, no se ha encontrado ning�n sistema de �ndices previo");

	do
	{
		menu = entorno.mostrarMenu();

		switch(menu)
		{
			case 1:	c1 = entorno.pedirNombre();		// Opci�n para mostrar un contacto
					estado = gestor.buscarContacto(c1);

					if(estado)
						entorno.mostrarContacto(c1);
					else entorno.mostrarMensaje("ERROR, no hay contactos con ese nombre!");

					break;

			case 2: c1 = entorno.pedirNombre();		// Opci�n para modificar un contacto
					
					if(gestor.buscarContacto(c1))
					{
						estado = gestor.modContacto(c1, entorno.pedirModContacto(c1));
						if(estado)
							entorno.mostrarMensaje("Modificaci�n realizada con �xisto");
						else entorno.mostrarMensaje("ERROR, no se ha podido realizar la modificaci�n!");
					}
					else entorno.mostrarMensaje("ERROR, no hay contactos con ese nombre!");

					break;

			case 3: estado = gestor.aniadirContacto(entorno.pedirContacto());		// Opci�n para a�adir un contacto				

					if(estado)
						entorno.mostrarMensaje("Contacto a�adido correctamente");
					else entorno.mostrarMensaje("ERROR, no se ha podido a�adir el contacto!"); 

					break;

			case 4: c1 = entorno.pedirNombre();		// Opci�n para borrar un contacto

					estado = gestor.borrarContacto(c1);

					if(estado)
						entorno.mostrarMensaje("El contacto ha sido eliminado correctamente");
					else entorno.mostrarMensaje("ERROR, no hay contactos con ese nombre!");

					break;

			case 5: c1 = entorno.pedirNombre();		// Opci�n para mostrar todos los nombres de los contactos que empiecen por una cadena
					
					nombre = c1.dameNombre();
					letra = nombre[0];

					letra = toupper(letra);
					
					entorno.mostrarVectorContactos(gestor.buscarCadenaContacto(nombre));

					break;

			case 6: entorno.mostrarNombresContactos(gestor.buscarTodosContactos());		// Opci�n para mostrar todos los nombres de los contactos

					break;

			default: break;
		}
		estado = false;

	}while(menu != 0);

	if(gestor.salvarDatos())		// Llamada al GestorAD para salvar el sistema de �ndices en "Indices.dat"
		entorno.mostrarMensaje("Sistema de �ndices guardado correctamente");
	else entorno.mostrarMensaje("AVISO, no se ha salvado el sistema de �ndices (vac�o)");
	cout << endl;

	system("pause");
	return 0;
}

