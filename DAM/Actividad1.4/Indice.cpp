
#include "Indice.h"

Indice::Indice()
{
	nombre = ""; 
	pos = 0;
}

Indice::Indice(string n1, int p1)
{
	nombre = n1; 
	pos = p1;
}

void Indice::ponNombre(string n1)
{
	nombre = n1;
}

void Indice::ponPos(int p1)
{
	pos = p1;
}

string Indice::dameNombre()
{
	return nombre;
}

int Indice::damePos()
{
	return pos;
}
