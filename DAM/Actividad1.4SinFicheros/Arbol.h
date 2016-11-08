
#ifndef ARBOL_H
#define ARBOL_H

#include "ArbolNodo.h"

class Arbol :public ArbolNodo
{
	private: ArbolNodo *raiz;
			 ArbolNodo arbolNodos[25];
	
	public: Arbol();
			ArbolNodo *dameRaiz();
			ArbolNodo *dameArbolNodo(char);

};
#endif
