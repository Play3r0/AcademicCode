
#include "Arbol.h"

Arbol::Arbol()
{
	raiz = new ArbolNodo('M');
	int cont = 0 ;
	bool insertado = false;

	arbolNodos[0] = (ArbolNodo('G'));
	arbolNodos[1] = (ArbolNodo('D'));
	arbolNodos[2] = (ArbolNodo('B'));
	arbolNodos[3] = (ArbolNodo('A'));
	arbolNodos[4] = (ArbolNodo('C'));
	arbolNodos[5] = (ArbolNodo('E'));
	arbolNodos[6] = (ArbolNodo('F'));
	arbolNodos[7] = (ArbolNodo('J'));
	arbolNodos[8] = (ArbolNodo('H'));
	arbolNodos[9] = (ArbolNodo('I'));
	arbolNodos[10] = (ArbolNodo('K'));
	arbolNodos[11] = (ArbolNodo('L'));
	arbolNodos[12] = (ArbolNodo('S'));
	arbolNodos[13] = (ArbolNodo('P'));
	arbolNodos[14] = (ArbolNodo('O'));
	arbolNodos[15] = (ArbolNodo('N'));
	arbolNodos[16] = (ArbolNodo('Q'));
	arbolNodos[17] = (ArbolNodo('R'));
	arbolNodos[18] = (ArbolNodo('W'));
	arbolNodos[19] = (ArbolNodo('U'));
	arbolNodos[20] = (ArbolNodo('T'));
	arbolNodos[21] = (ArbolNodo('V'));
	arbolNodos[22] = (ArbolNodo('Y'));
	arbolNodos[23] = (ArbolNodo('X'));
	arbolNodos[24] = (ArbolNodo('Z'));
	
	while (cont < 25)
	{
		ArbolNodo *raizAux = raiz;
		ArbolNodo *nuevo = new ArbolNodo(arbolNodos[cont]);

		insertado = false;
		while(raizAux != NULL && insertado != true)
		{
			if (nuevo->dameLetraArbol() < raizAux->dameLetraArbol())
			{
				if(raizAux->dameIzquierda() == NULL)
				{
					raizAux->ponArbolNodoIzquierda(nuevo);
					insertado = true;
				}
				else raizAux= raizAux->dameIzquierda();
			}
			else 
			{
				if(raizAux->dameDerecha() == NULL)
				{
					raizAux->ponArbolNodoDerecha(nuevo);
					insertado = true;
				}
				else raizAux= raizAux->dameDerecha();
			}
		}
		cont++;
	}
}

ArbolNodo *Arbol::dameRaiz()
{
	return raiz;
}

ArbolNodo *Arbol::dameArbolNodo(char miLetraArbol)
{
	bool encontrado = false;
	ArbolNodo *raizAux = raiz;
	ArbolNodo *buscarLetra = new ArbolNodo(miLetraArbol);

	while(raizAux != NULL && encontrado != true)		// ELIMINAR CONDICIÓN BOOLEANA
	{
		if (buscarLetra->dameLetraArbol() < raizAux->dameLetraArbol())
			raizAux = raizAux->dameIzquierda();
		else 
		{
			if(buscarLetra->dameLetraArbol() > raizAux->dameLetraArbol())
				raizAux = raizAux->dameDerecha();
			else
			{
				if(buscarLetra->dameLetraArbol() == raizAux->dameLetraArbol())
					encontrado = true;
			}
		}
	}

	return raizAux;
}