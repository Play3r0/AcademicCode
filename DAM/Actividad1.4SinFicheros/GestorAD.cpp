
#include "GestorAD.h"


// Funciones de la clase
void recorridoPre(ArbolNodo *p, vector<string> &vAux)
{
	if(p != NULL)
	{
		recorridoPre(p->dameIzquierda(), vAux);
		
		
		if(p->dameLista()->damePrimerNodo() != NULL)	// Para evitar Contacto = NULL. Primera ejecución. Error xstring
		{
			Nodo *n = p->dameLista()->damePrimerNodo();
			
			while(n != NULL)
			{
				vAux.push_back(n->dameContacto().dameNombre());
				n = n->dameSig();
			}
		}

		recorridoPre(p->dameDerecha(), vAux);
		
	}
}


void recorridoPre2(ArbolNodo *p)		// FUNCIÓN DE RECORRIDO AUXILIAR PARA MOSTRAR EL ÁRBOL Y SU CONTENIDO
{
	if(p != NULL)
	{
		recorridoPre2(p->dameIzquierda());
		cout << endl << "MOSTRAR LETRA: " << p->dameLetraArbol() << endl;
		if(p->dameLista()->damePrimerNodo() != NULL)
		{
			Nodo *n = p->dameLista()->damePrimerNodo();
			
			cout << "Lista: " << endl << endl;
			while(n != NULL)
			{
				cout << n->dameContacto().dameNombre() << endl;
				n = n->dameSig();
			}
			
		}

		recorridoPre2(p->dameDerecha());
	}

}



Lista *devolverLista(Arbol arbolAux, Contacto c2)
{
	char inicial = ' ';
	string nombre = "";
	Lista *miLista;

	nombre = c2.dameNombre();
	
	inicial = nombre[0];
	inicial = toupper(inicial);

	miLista = arbolAux.dameArbolNodo(inicial)->dameLista();

	return miLista;
}



vector<Contacto> recorrerLista(Lista *listaAux, vector<Contacto> &vAux, Contacto c2)
{
	string cad, cad3 = c2.dameNombre();
	string cad2(cad3);

	if(listaAux->damePrimerNodo() != NULL)	// Para evitar Contacto = NULL. Primera ejecución. Error xstring
	{
		Nodo *n = listaAux->damePrimerNodo();
		
		while(n != NULL)
		{
			cad = n->dameContacto().dameNombre();
			
			for(int i = 0; i < cad3.size(); i++)
				cad2[i] = cad[i];

			if(cad2 == cad3)
				vAux.push_back(n->dameContacto().dameNombre());
				
			n = n->dameSig();
		}
	}

	return vAux;
}
// Funciones de la clase


//-----------------------------------------------------------//


// Métodos de la clase
bool GestorAD::aniadirContacto(Contacto c1)
{	
	bool aniadido = false;
	Lista *listaLetra = devolverLista(arbolNucleo, c1);
	

	if(listaLetra->insertarContacto(c1))
		aniadido = true;


	return aniadido;
}

bool GestorAD::buscarContacto(Contacto &c1)
{
	bool encontrado = false;
	Lista *listaLetra = devolverLista(arbolNucleo, c1);


	if(listaLetra->buscarContacto(c1))
		encontrado = true;

	return encontrado;
}

bool GestorAD::borrarContacto(Contacto c1)
{
	bool borrado = false;
	Lista *listaLetra = devolverLista(arbolNucleo, c1);


	if(listaLetra->borrarContacto(c1))
		borrado = true;

	return borrado;
}

bool GestorAD::modContacto(Contacto viejo, Contacto nuevo)
{
	bool modificado = false;
	Lista *listaLetra;

	aniadirContacto(nuevo);

	return modificado;
}

vector<Contacto> GestorAD::buscarCadenaContacto(string cad1)
{
	vector<Contacto> v1;
	Arbol arbolAux = arbolNucleo;
	Lista *miLista;
	Contacto c1;

	c1.ponNombre(cad1);

	recorrerLista(devolverLista(arbolAux, c1), v1, c1);

	return v1;
}

vector<string> GestorAD::buscarTodosContactos()		// Busca todos los nombres y los introduce en un vector para enviarlos a la capa de E/S
{
	vector<string> v1;
	string nombre = "";
	

	Arbol arbolAux = arbolNucleo;

	recorridoPre(arbolAux.dameRaiz(), v1);

	recorridoPre2(arbolAux.dameRaiz());

	return v1;
}

// Métodos de la clase