
#ifndef LISTA_H
#define LISTA_H

#include "Nodo.h"

class Lista
{
	private: Nodo *primero;

	public: Lista();
			bool insertarContacto(Contacto);
			bool buscarContacto(Contacto &);
			bool borrarContacto(Contacto);
			Nodo *damePrimerNodo();
			//bool salvarContacto(char *, Contacto);
};
#endif