
#ifndef ARBOL_H
#define ARBOL_H

#include "ArbolNodo.h"

class Arbol
{
	private: ArbolNodo *raiz;
			 ArbolNodo arbolNodos[25];
	
	public: Arbol();
			ArbolNodo *dameRaiz();
			ArbolNodo *dameArbolNodo(char);
			int buscarPosLibre();

			bool salvarArbol();
			bool cargarArbol();
};
#endif
