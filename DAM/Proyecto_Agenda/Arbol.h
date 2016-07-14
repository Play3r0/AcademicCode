#pragma once
#include"Contacto.h"
#include"Indices.h"
#include"Nodo.h"
#include<string.h>
#include<stdio.h>
#include<list>
using namespace std;
class Arbol
{
	private: Nodo *raiz;

	public:	Arbol();
			void montarArbol();
			void ponRaiz(Nodo *miRaiz);
			Nodo *dameRaiz();
			list<Indices> *buscarLetra(string miCadena);
			int siguientePosicion(Nodo *n);
			~Arbol();
};

