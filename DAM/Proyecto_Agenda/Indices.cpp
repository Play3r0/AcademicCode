#include"Indices.h"

Indices::Indices()
{
	nombre="";
	posicion=0;
}

Indices::Indices(string miNombre, int miPosicion)
{
	nombre=miNombre;
	posicion=miPosicion;
}

string Indices::dameNombre()
{
	return nombre;
}

int Indices::damePosicion()
{
	return posicion;
}

void Indices::ponNombre(string miNombre)
{
	nombre=miNombre;
}

void Indices::ponPosicion(int miPosicion)
{
	posicion=miPosicion;
}

Indices::~Indices()
{}