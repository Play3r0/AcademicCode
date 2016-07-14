#include "Nodo.h"

Nodo::Nodo()
{
	letra="";
	datos=NULL;
	derecho=NULL;
	izquierdo=NULL;
}

Nodo::Nodo(string miLetra)
{
	letra=miLetra;
	datos=NULL;
	derecho=NULL;
	izquierdo=NULL;
}

string Nodo::dameLetra()
{
	return letra;
}

void Nodo::ponLetra(string miLetra)
{
	letra=miLetra;
}

list<Indices> *Nodo::dameLista()
{
	return datos;
}

Nodo *Nodo::dameDch()
{
	return derecho;
}

Nodo *Nodo::dameIzq()
{
	return izquierdo;
}

void Nodo::ponDch(Nodo *p_nodo)
{
	derecho=p_nodo;
}

void Nodo::ponIzq(Nodo *p_nodo)
{
	izquierdo=p_nodo;
}

Nodo::~Nodo(void)
{
}
