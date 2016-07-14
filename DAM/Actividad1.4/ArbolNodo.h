
#ifndef ArbolNodo_H
#define ArbolNodo_H

#include "Lista.h"

class ArbolNodo
{
	private: char letraArbol;
			 Lista *listaArbol;
			 ArbolNodo *ArbolNodoIzquierda;
			 ArbolNodo *ArbolNodoDerecha;
	
	public: ArbolNodo();
			ArbolNodo(char);
			
			char dameLetraArbol();
			ArbolNodo *dameIzquierda();
			ArbolNodo *dameDerecha();
			Lista *dameLista();
			void ponLetraArbol(char);
			void ponArbolNodoIzquierda(ArbolNodo *ArbolNodoMiIzquierda);
			void ponArbolNodoDerecha(ArbolNodo *ArbolNodoMiDerecha);

			bool salvarArbolNodo();
			bool cargarArbolNodo();
};
#endif

