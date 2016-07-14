
#ifndef INDICE_H
#define INDICE_H

#include "Contacto.h"

class Indice
{
	private: string nombre; 
			 int pos;

	public: Indice();
			Indice(string, int);
			void ponNombre(string);
			void ponPos(int);
			string dameNombre();
			int damePos();
};

#endif