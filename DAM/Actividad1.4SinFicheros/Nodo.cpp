
#include "Nodo.h"

Nodo::Nodo()
{
	contact = Contacto();
	sig = NULL;
	ant = NULL;
}

Nodo::Nodo(Contacto c1)
{
	contact = c1;
	sig = NULL;
	ant = NULL;
}

Contacto Nodo::dameContacto()
{
	return contact;
}

Nodo *Nodo::dameSig()
{
	return sig;
}

Nodo *Nodo::dameAnt()
{
	return ant;
}

void Nodo::ponContacto(Contacto c1)
{
	contact = c1;
}

void Nodo::ponSig(Nodo *punt_nodo)
{
	sig = punt_nodo;
}

void Nodo::ponAnt(Nodo *punt_nodo)
{
	ant = punt_nodo;
}


