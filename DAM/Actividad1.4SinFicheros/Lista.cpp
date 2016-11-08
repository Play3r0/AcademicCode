
#include "Lista.h"

Lista::Lista()
{
	primero = NULL;
}

bool Lista::insertarContacto(Contacto c1)
{
	bool insertado = false, entrada = false;
	Contacto miContacto = c1;
	Nodo *nuevo = new Nodo(miContacto);


	if(nuevo == NULL)
		cout << "No hay memoria" << endl;

	else
	{
		if(primero == NULL)			//SI ESTÁ VACÍA, ASIGNA primero AL NUEVO ELEMENTO
		{
			primero = nuevo;
			insertado = true;
		}
		else
		{
			Nodo *p, *ant;
			p = primero;

			while(p->dameSig() != NULL && p->dameContacto().dameNombre() < nuevo->dameContacto().dameNombre())		//BUCLE MIENTRAS p != NULL Y listaNombre < nuevoNombre
			{
				entrada = true;
				ant = p;		//GUARDA EL PUNTERO AL NODO ANTERIOR
				p = p->dameSig();
			}

			if(p->dameContacto().dameNombre() == nuevo->dameContacto().dameNombre())
			{
				p->ponContacto(nuevo->dameContacto());
				insertado = true;
			}
			else
			{

				if(p->dameContacto().dameNombre() > nuevo->dameContacto().dameNombre() && entrada == true)		//SI listaNombre >= nuevoNombre Y NO SE TRATA DE LA PRIMERA POSICIÓN
				{
					nuevo->ponSig(p);	
					ant->ponSig(nuevo);

					insertado = true;
				}
				else 
				{
					if(p->dameContacto().dameNombre() > nuevo->dameContacto().dameNombre())		//SI num >= n EN LA PRIMERA POSICIÓN
					{
						nuevo->ponSig(p);
						primero = nuevo;
						insertado = true;
					}
					else
					{
						p->ponSig(nuevo);	//SI LLEGA AL FINAL DE LA LISTA LO AÑADE SIN MÁS
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

bool Lista::borrarContacto(Contacto c1)
{
	bool borrado = false;
	Nodo *p = primero;
	Nodo *ant = p;		//AUX PARA IR GUARDANDO EL PUNTERO ANTERIOR A P


	if(p != NULL)
	{
		if(p->dameContacto().dameNombre() == c1.dameNombre())
		{
			primero = p->dameSig();
			delete p;
			borrado = true;
		}
		else
		{
			Nodo *ant;
			while(p->dameSig() != NULL && p->dameContacto().dameNombre() != c1.dameNombre())
			{
				ant = p;
				p = p->dameSig();
			}

			if(p->dameContacto().dameNombre() == c1.dameNombre())
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

/*bool Lista::salvarContacto(char *cad, Contacto cAux)
{
	FILE *fichero = fopen(cad, "rb+");

	if(fichero == NULL)
	{
		cout << "<--FICHERO Contactos.dat VACÍO-->" << endl;
		fichero = fopen("Contactos.dat", "wb");

		fseek(fichero, sizeof(Contacto), 0);
		fwrite(&cAux, sizeof(Contacto), 1, fichero);

		fclose(fichero);
	}
	else 
	{
		fichero = fopen(cad, "rb+");

		fseek(fichero, sizeof(Contacto), 0);
		fwrite(&cAux, sizeof(Contacto), 1, fichero);

		fclose(fichero);
	}
}*/