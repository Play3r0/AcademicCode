
#ifndef LISTADOBLE_H
#define LISTADOBLE_H

#include "Nodo.h"

class ListaDoble
{
	private: Nodo *primero;
			 Nodo *ultimo;

	public: ListaDoble();
			bool insertarContacto(Contacto);
			bool buscarContacto(Contacto &);
			bool borrarContacto(Contacto);
			Nodo *damePrimerNodo();

};
#endif