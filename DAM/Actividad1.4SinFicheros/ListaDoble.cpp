
#include "ListaDoble.h"

ListaDoble::ListaDoble()
{
	primero = NULL;
	ultimo = NULL;
}

bool ListaDoble::insertarContacto(Contacto c1)		// NO INSERTA ORDENADAMENTE. MODIFICIAR!!
{
	bool insertado = false;
	Contacto miContacto = c1;
	Nodo *nuevo = new Nodo(miContacto);

	if(nuevo == NULL)
		cout << "ERROR, no hay memoria" << endl;
	else
	{
		if(primero == NULL)		//IF PRIMERO == NULL, LISTA VACÍA
		{
			primero = nuevo;
			ultimo = primero;
			insertado = true;
		}
		else
		{
			ultimo->ponSig(nuevo);
			nuevo->ponAnt(ultimo);
			ultimo = nuevo;
			insertado = true;
		}
	}

	return insertado;
}

bool ListaDoble::buscarContacto(Contacto &c1)
{
	Nodo *p = primero;
	bool encontrado = false;

	while(p != NULL && encontrado != true)
	{
		if(p->dameContacto().dameNombre() == c1.dameNombre())	// Avanza por la lista hasta que encuentra el nombre o hasta que llegue al final
		{
			c1 = p->dameContacto();
			encontrado = true;
		}
		p = p->dameSig();
	}

	return encontrado;
}

bool ListaDoble::borrarContacto(Contacto c1)
{
	bool borrado = false;
	Nodo *p = primero;
	Nodo *ant = p;		//AUX PARA IR GUARDANDO EL PUNTERO ANTERIOR A P

	while(p != NULL && borrado != true)
	{
		if(p->dameContacto().dameNombre() == c1.dameNombre())		//CUANDO HA ENCONTRADO EL "PUNTERO" QUE SE DESEA BORRAR, SE ACTUALIZAN LOS PUNTEROS *ant y *sig DEL PUNTERO ANTERIOR Y EL SIGUIENTE
		{
			if(p == primero)		//SI EL NODO A BORRAR ES EL PRIMERO
			{
				if(primero->dameSig() != NULL)		//SI HAY MÁS NODOS
				{
					primero = primero->dameSig();
					primero->ponAnt(NULL);
				}
				else 
				{
					primero = NULL;
					ultimo = NULL;
				}
				delete p;
			}

			else 
			{
				if(p == ultimo)		//SI EL NODO A BORRAR ES EL ULTIMO
				{
					ant->ponSig(NULL);
					ultimo = ant;
				}
				else		//SI SE ENCUENTRA EN EL MEDIO
				{
					ant->ponSig(p->dameSig());
					p->dameSig()->ponAnt(ant);
				}
				delete p;
			}

			borrado = true;
		}
		
		ant = p;
		p = p->dameSig();
	}

	return borrado;
}


Nodo *ListaDoble::damePrimerNodo()
{
	return primero;
}