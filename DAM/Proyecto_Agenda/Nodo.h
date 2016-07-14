#include"Indices.h"
#include<string.h>
#include<stdio.h>
#include<list>
using namespace std;
#pragma once
class Nodo
{
	private: string letra;
			 list<Indices> *datos;
			 Nodo *derecho;
			 Nodo *izquierdo;

	public: Nodo();
			Nodo(string miLetra);
			string dameLetra();
			void ponLetra(string miLetra);
			list<Indices> *dameLista();
			Nodo *dameDch();
			Nodo *dameIzq();
			void ponDch(Nodo *p_nodo);
			void ponIzq(Nodo *p_nodo);
			~Nodo();
};

