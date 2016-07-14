
#include "Nodo.h"

Nodo::Nodo()
{
	ind = Indice();
	sig = NULL;
	ant = NULL;
}

Nodo::Nodo(Indice ind1)
{
	ind = ind1;
	sig = NULL;
	ant = NULL;
}

Indice Nodo::dameIndice()
{
	return ind;
}

Nodo *Nodo::dameSig()
{
	return sig;
}

Nodo *Nodo::dameAnt()
{
	return ant;
}

void Nodo::ponIndice(Indice c1)
{
	ind = c1;
}

void Nodo::ponSig(Nodo *punt_nodo)
{
	sig = punt_nodo;
}

void Nodo::ponAnt(Nodo *punt_nodo)
{
	ant = punt_nodo;
}


