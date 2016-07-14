
#include "Lista.h"
#include <string.h>


// Funciones de la clase

// Pasa una cadena a mayúsculas en "caliente"
// Pj para comparar dos cadenas, independientemente de si están en mayúsculas, minúsculas o una combinación de ambas
string mayus(string s1)
{ 
   string aux = s1; 
   
   for(int i = 0; i < s1.size(); i++) 
      aux.at(i) = toupper(aux.at(i)); 
   
   return aux; 
}
// Funciones de la clase



//-----------------------------------------------------------//



Lista::Lista()
{
	primero = NULL;
}

bool Lista::insertarContacto(Contacto c1, int pos)
{
	bool insertado = false, entrada = false;
	Indice miIndice;
	miIndice.ponNombre(c1.dameNombre());
	Nodo *nuevo = new Nodo();


	if(nuevo == NULL)
		cout << "No hay memoria" << endl;

	else
	{
		salvarContacto(c1, pos);		// Guardamos en el fichero y obtenemos la posición
		miIndice.ponPos(pos);		// Cargamos la posición dentro del índice
		nuevo = new Nodo(miIndice);		// Creamos el nodo con el nombre del contacto y su posición dentro del fichero

		if(primero == NULL)			// Si está vacía, asigna primero al nuevo elemento
		{
			primero = nuevo;
			insertado = true;
		}
		else
		{
			Nodo *p, *ant;
			p = primero;
			
			while(p->dameSig() != NULL && mayus(p->dameIndice().dameNombre()) < mayus(nuevo->dameIndice().dameNombre()))		// Bucle mientras p != null y listaNombre < nuevoNombre
			{
				entrada = true;
				ant = p;		// Guarda el puntero al nodo anterior
				p = p->dameSig();
			}

			if(mayus(p->dameIndice().dameNombre()) == mayus(nuevo->dameIndice().dameNombre()))
			{
				p->ponIndice(nuevo->dameIndice());
				insertado = true;
			}
			else
			{

				if(mayus(p->dameIndice().dameNombre()) > mayus(nuevo->dameIndice().dameNombre()) && entrada == true)		// Si listaNombre >= nuevoNombre y no se trata de la primera posición
				{
					nuevo->ponSig(p);	
					ant->ponSig(nuevo);

					insertado = true;
				}
				else 
				{
					if(mayus(p->dameIndice().dameNombre()) > mayus(nuevo->dameIndice().dameNombre()))		// Si num >= n en la primera posición
					{
						nuevo->ponSig(p);
						primero = nuevo;
						insertado = true;
					}
					else
					{
						p->ponSig(nuevo);	// Si llega al final de la lista, lo añade sin más
						insertado = true;
					}
				}
			}
		}
	}

	return insertado;
}

bool Lista::buscarContacto(Contacto &c1)
{
	Indice miIndice;
	miIndice.ponNombre(c1.dameNombre());
	Nodo *p = primero;
	bool encontrado = false;
	Contacto *aux;

	while(p != NULL && encontrado != true)
	{
		if(mayus(p->dameIndice().dameNombre()) == mayus(miIndice.dameNombre()))		// Avanza por la lista hasta que encuentra el nombre o hasta que llegue al final de la misma
		{
			miIndice = p->dameIndice();
			encontrado = true;
		}
		p = p->dameSig();
	}

	if(encontrado)		// Buscamos DENTRO DEL FICHERO A TRAVÉS DE miIndice.damePos() Y hacemos UNA LECTURA DEL CONTACTO SOBRE C1
	{
		aux = cargarContacto(miIndice.damePos());
		
		c1.ponNombre(aux->dameNombre());
		c1.ponTelefono(aux->dameTelefono());
		c1.ponEmail(aux->dameEmail());
	}

	return encontrado;
}

bool Lista::borrarContacto(Contacto c1)
{
	bool borrado = false;
	Nodo *p = primero;
	Nodo *ant = p;		// Aux para ir guardando el puntero anterior


	if(p != NULL)
	{
		if(mayus(p->dameIndice().dameNombre()) == mayus(c1.dameNombre()))
		{
			primero = p->dameSig();
			delete p;

			borrado = true;
		}
		else
		{
			while(p->dameSig() != NULL && mayus(p->dameIndice().dameNombre()) != mayus(c1.dameNombre()))
			{
				ant = p;
				p = p->dameSig();
			}

			if(mayus(p->dameIndice().dameNombre()) == mayus(c1.dameNombre()))
			{
				ant->ponSig(p->dameSig());
				delete p;

				borrado = true;
			}
		}
	}

	return borrado;
}

Nodo *Lista::damePrimerNodo()
{
	return primero;
}

void Lista::salvarContacto(Contacto cAux,  int &miPos)
{
	FILE *fichero;
	Contacto *cAux2 = new Contacto();		// Puntero auxiliar de contacto para guardar porque da error con objetos

	cAux2->ponNombre(cAux.dameNombre());
	cAux2->ponTelefono(cAux.dameTelefono());
	cAux2->ponEmail(cAux.dameEmail());

	fichero = fopen("Contactos.dat", "rb+");

	if(fichero != NULL)
	{
		fseek(fichero, (miPos)*sizeof(Contacto), SEEK_SET);
		fwrite(cAux2, sizeof(Contacto), 1, fichero);

		/*miPos = ftell(fichero);		// Recojo la posicion
		miPos = (miPos/sizeof(Contacto)) - 1;		// Tengo que retroceder una posición, ya que al hacer una escritura/lectura el puntero avanza solo
		*/

		fclose(fichero);
	}
	else
	{
		fichero = fopen("Contactos.dat", "wb");

		fwrite(cAux2, sizeof(Contacto), 1, fichero);

		fclose(fichero);
	}
}

Contacto *Lista::cargarContacto(int miPos)
{
	FILE *fichero;
	Contacto *c1 = new Contacto();

	fichero = fopen("Contactos.dat", "rb");

	if(fichero != NULL)
	{						
		fseek(fichero, (miPos)*sizeof(Contacto), SEEK_SET);
		fread(c1, sizeof(Contacto), 1, fichero);

		fclose(fichero);
	}	

	return c1;
}


bool Lista::salvarLista()
{
	Nodo *p = primero;
	bool salvado = false;
	FILE *fichero;

	fichero = fopen("Indices.dat", "ab");

	while(p != NULL)		// Bucle mientras p != null
	{
		fwrite(p, sizeof(Indice), 1, fichero);
		
		p = p->dameSig();
		salvado = true;
	}
		
	fclose(fichero);

	return salvado;
}

bool Lista::cargarLista(char miLetra)
{
	Nodo *nodoAux = new Nodo(), *p = primero;
	Indice nuevoIndice;
	Indice *auxIndice = new Indice();	// La lectura solo funciona con punteros ¿?¿?¿?
	
	FILE *fichero;
	string auxNombre;
	char auxLetra;

	bool cargado = false, primeraVez = false;


	fichero = fopen("Indices.dat", "rb");

	if(fichero != NULL)
	{

		auxIndice = new Indice();
		fread(auxIndice, sizeof(Indice), 1, fichero);

		if(!feof(fichero))
		{
			nuevoIndice.ponNombre(auxIndice->dameNombre());
			nuevoIndice.ponPos(auxIndice->damePos());

			auxNombre = nuevoIndice.dameNombre();
		
			auxLetra = auxNombre[0];
			auxLetra = toupper(auxLetra);

			if(auxLetra == miLetra)
			{
				primero = new Nodo(nuevoIndice);
				p = primero;
				primeraVez = true;
			}

			auxIndice = new Indice();
			fread(auxIndice, sizeof(Indice), 1, fichero);

			
			while(!feof(fichero))		// Bucle mientras no llegue al final del fichero
			{
				nuevoIndice.ponNombre(auxIndice->dameNombre());
				nuevoIndice.ponPos(auxIndice->damePos());

				nodoAux = new Nodo(nuevoIndice);

				auxNombre = nuevoIndice.dameNombre();

				auxLetra = auxNombre[0];
				auxLetra = toupper(auxLetra);

				if(auxLetra == miLetra)
				{
					if(primeraVez == false)
					{
						primero = new Nodo(nuevoIndice);
						p = primero;
						primeraVez = true;
					}
					else
					{
						p->ponSig(nodoAux);
						p = p->dameSig();
					}
				}

				auxIndice = new Indice();
				fread(auxIndice, sizeof(Indice), 1, fichero);
			}

			cargado = true;

			fclose(fichero);
		}
	}

	return cargado;
}