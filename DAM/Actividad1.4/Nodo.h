
#ifndef NODO_H
#define NODO_H

#include "Indice.h"

class Nodo
{
	private: Indice ind; 
			 Nodo *sig;
			 Nodo *ant;

	public:	Nodo();
			Nodo(Indice);
			Indice dameIndice();
			Nodo *dameSig();
			Nodo *dameAnt();
			void ponIndice(Indice);
			void ponSig(Nodo *punt_nodo);
			void ponAnt(Nodo *punt_nodo);
};
#endif