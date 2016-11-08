
//Actividad 1.4 - Sergio López Jurado

#include "EntornoGrafico.h"
#include "GestorAD.h"
#include "Contacto.h"
#include "Arbol.h"


void recorridoPre(ArbolNodo *p)		// Recorrer el árbol en orden
{
	if(p!=NULL)
	{
		recorridoPre(p->dameIzquierda());
		cout << "Letra: " << p->dameLetraArbol() << endl;
		if(p->dameLista()->damePrimerNodo() != NULL)
			cout << "Lista: " << p->dameLista()->damePrimerNodo()->dameContacto().dameNombre() << endl;

		recorridoPre(p->dameDerecha());
	}

}

int main()
{
	/*if(l1.cargar("ListaDoble.dat"))
		cout << "Lista doble cargada correctamente" << endl;
	else cout << "Fichero no encontrado o la lista doble estaba vacía" << endl;*/

	Arbol arbol = Arbol();
	recorridoPre(arbol.dameRaiz());

	GestorAD gestor;
	EntornoGrafico entorno;
	Contacto c1;

	int menu = 0;
	bool estado = false;
	string nombre = "";
	char letra = ' ';

	
	do
	{
		cout << endl << "MENU AGENDA" << endl;
		cout << "1. Mostrar un contacto" << endl;
		cout << "2. Modificar un contacto" << endl;
		cout << "3. Añadir un contacto" << endl;
		cout << "4. Borrar un contacto" << endl;
		cout << "5. Mostrar todos los contactos que empiecen por una cadena" << endl;
		cout << "6. Mostrar todos los contactos" << endl;
		cout << "0. Salir" << endl;
		cout << "Introduce una opción: ";
		cin >> menu;
		while(menu < 0 || menu > 6)
		{
			cout << "ERROR, selecciona una opción correcta: ";
			cin >> menu;
		}

		switch(menu)
		{
			case 1:	c1 = entorno.pedirNombre();
					estado = gestor.buscarContacto(c1);

					if(estado)
						entorno.mostrarContacto(c1);
					else entorno.mostrarMensaje("ERROR, no hay contactos con ese nombre!");

					break;

			case 2: c1 = entorno.pedirNombre();
					
					if(gestor.buscarContacto(c1))
					{
						estado = gestor.modContacto(c1, entorno.pedirModContacto(c1));
						if(estado)
							entorno.mostrarMensaje("Modificación realizada con éxisto");
						else entorno.mostrarMensaje("ERROR, no se ha podido realizar la modificación!");
					}
					else entorno.mostrarMensaje("ERROR, no hay contacto con ese nombre!");

					break;

			case 3: estado = gestor.aniadirContacto(entorno.pedirContacto());			

					if(estado)
						entorno.mostrarMensaje("Contacto añadido correctamente");
					else entorno.mostrarMensaje("ERROR, no se ha podido añadir el contacto!"); 

					break;

			case 4: c1 = entorno.pedirNombre();

					estado = gestor.borrarContacto(c1);

					if(estado)
						entorno.mostrarMensaje("El contacto ha sido eliminado correctamente");
					else entorno.mostrarMensaje("ERROR, no hay contactos con ese nombre!");

					break;

			case 5: c1 = entorno.pedirNombre();
					
					nombre = c1.dameNombre();
					letra = nombre[0];

					letra = toupper(letra);
					
					entorno.mostrarMensaje("AGENDA - TODOS LOS NOMBRES QUE EMPIECEN POR UNA CADENA: ");
					
					entorno.mostrarVectorContactos(gestor.buscarCadenaContacto(nombre));

					break;

			case 6: entorno.mostrarMensaje("AGENDA - TODOS LOS NOMBRES DE LOS CONTACTOS");
					
					entorno.mostrarNombresContactos(gestor.buscarTodosContactos());

					break;

			default: break;
		}
		estado = false;

	}while(menu != 0);


	system("pause");
	return 0;
}

