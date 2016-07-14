
//Actividad 1.4 - Sergio López Jurado

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
	
	if(gestor.cargarDatos())		// Llamada al GestorAD para cargar el sistema de índices en "Indices.dat"
		entorno.mostrarMensaje("EL sistema de Índices ha sido cargado correctamente");
	else entorno.mostrarMensaje("AVISO, no se ha encontrado ningún sistema de índices previo");

	do
	{
		menu = entorno.mostrarMenu();

		switch(menu)
		{
			case 1:	c1 = entorno.pedirNombre();		// Opción para mostrar un contacto
					estado = gestor.buscarContacto(c1);

					if(estado)
						entorno.mostrarContacto(c1);
					else entorno.mostrarMensaje("ERROR, no hay contactos con ese nombre!");

					break;

			case 2: c1 = entorno.pedirNombre();		// Opción para modificar un contacto
					
					if(gestor.buscarContacto(c1))
					{
						estado = gestor.modContacto(c1, entorno.pedirModContacto(c1));
						if(estado)
							entorno.mostrarMensaje("Modificación realizada con éxisto");
						else entorno.mostrarMensaje("ERROR, no se ha podido realizar la modificación!");
					}
					else entorno.mostrarMensaje("ERROR, no hay contactos con ese nombre!");

					break;

			case 3: estado = gestor.aniadirContacto(entorno.pedirContacto());		// Opción para añadir un contacto				

					if(estado)
						entorno.mostrarMensaje("Contacto añadido correctamente");
					else entorno.mostrarMensaje("ERROR, no se ha podido añadir el contacto!"); 

					break;

			case 4: c1 = entorno.pedirNombre();		// Opción para borrar un contacto

					estado = gestor.borrarContacto(c1);

					if(estado)
						entorno.mostrarMensaje("El contacto ha sido eliminado correctamente");
					else entorno.mostrarMensaje("ERROR, no hay contactos con ese nombre!");

					break;

			case 5: c1 = entorno.pedirNombre();		// Opción para mostrar todos los nombres de los contactos que empiecen por una cadena
					
					nombre = c1.dameNombre();
					letra = nombre[0];

					letra = toupper(letra);
					
					entorno.mostrarVectorContactos(gestor.buscarCadenaContacto(nombre));

					break;

			case 6: entorno.mostrarNombresContactos(gestor.buscarTodosContactos());		// Opción para mostrar todos los nombres de los contactos

					break;

			default: break;
		}
		estado = false;

	}while(menu != 0);

	if(gestor.salvarDatos())		// Llamada al GestorAD para salvar el sistema de índices en "Indices.dat"
		entorno.mostrarMensaje("Sistema de índices guardado correctamente");
	else entorno.mostrarMensaje("AVISO, no se ha salvado el sistema de índices (vacío)");
	cout << endl;

	system("pause");
	return 0;
}

