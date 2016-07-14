
#ifndef GESTORAD_H
#define GESTORAD_H

#include "InterfacesAD.h"

class GestorAD: public InterfacesAD
{
	private: Arbol arbolNucleo;

	public: GestorAD();
			Arbol dameArbol();
			bool aniadirContacto(Contacto);
			bool buscarContacto(Contacto &);
			bool borrarContacto(Contacto);
			bool modContacto(Contacto, Contacto);
			vector<Contacto> buscarCadenaContacto(string);
			vector<string> buscarTodosContactos();

			bool salvarDatos();
			bool cargarDatos();

};
#endif