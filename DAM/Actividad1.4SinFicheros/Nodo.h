
#ifndef NODO_H
#define NODO_H

#include "Contacto.h"

class Nodo
{
	private: Contacto contact; //Indice ind
			 Nodo *sig;
			 Nodo *ant;

	public:	Nodo();
			Nodo(Contacto c1);
			Contacto dameContacto();
			Nodo *dameSig();
			Nodo *dameAnt();
			void ponContacto(Contacto);
			void ponSig(Nodo *punt_nodo);
			void ponAnt(Nodo *punt_nodo);
};
#endif