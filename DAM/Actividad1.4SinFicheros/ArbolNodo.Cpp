
#include "ArbolNodo.h"

ArbolNodo::ArbolNodo(void)
{
	letraArbol = ' ';
	listaArbol = new Lista();
	ArbolNodoIzquierda = NULL;
	ArbolNodoDerecha = NULL;
}

ArbolNodo::ArbolNodo(char miLetraArbol)
{
	letraArbol = miLetraArbol;
	listaArbol = new Lista();
	ArbolNodoIzquierda = NULL;
	ArbolNodoDerecha = NULL;
}

char ArbolNodo::dameLetraArbol()
{
	return letraArbol;
}

ArbolNodo *ArbolNodo::dameIzquierda()
{
	return ArbolNodoIzquierda;
}

ArbolNodo *ArbolNodo::dameDerecha()
{
	return ArbolNodoDerecha;
}

Lista *ArbolNodo::dameLista()
{
	return listaArbol;
}

void ArbolNodo::ponLetraArbol(char miLetraArbol)
{
	letraArbol = miLetraArbol;
}

void ArbolNodo::ponArbolNodoIzquierda(ArbolNodo *miArbolNodoIzquierda)
{
	ArbolNodoIzquierda = miArbolNodoIzquierda;
}

void ArbolNodo::ponArbolNodoDerecha(ArbolNodo *miArbolNodoDerecha)
{
	ArbolNodoDerecha = miArbolNodoDerecha;
}