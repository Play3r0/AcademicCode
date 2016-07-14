
#ifndef LISTA_H
#define LISTA_H

#include "Nodo.h"

class Lista
{
	private: Nodo *primero;

	public: Lista();
			bool insertarContacto(Contacto, int);
			bool buscarContacto(Contacto &);
			bool borrarContacto(Contacto);
			Nodo *damePrimerNodo();
			void salvarContacto(Contacto, int &);
			Contacto *cargarContacto(int);
			
			bool salvarLista();
			bool cargarLista(char);
};
#endif