
#ifndef INTERFACESAD_H
#define INTERFACESAD_H

#include "Contacto.h"
#include "Arbol.h"
#include "ArbolNodo.h"
#include "Lista.h"
#include "Nodo.h"

class InterfacesAD
{
	public: virtual bool aniadirContacto(Contacto) = 0;
			virtual bool buscarContacto(Contacto &) = 0;
			virtual bool borrarContacto(Contacto) = 0;
			virtual bool modContacto(Contacto, Contacto) = 0;
			virtual vector<Contacto> buscarCadenaContacto(string) = 0;
			virtual vector<string> buscarTodosContactos() = 0;

};
#endif