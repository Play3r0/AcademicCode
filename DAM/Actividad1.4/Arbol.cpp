
#include "Arbol.h"


// Funciones de la clase
void recorridoSalvar(ArbolNodo *p, bool &save)		// Función de auxiliar de recorrido para salvar el contenido del árbol
{
	if(p != NULL)
	{
		recorridoSalvar(p->dameIzquierda(), save);
		if(p->salvarArbolNodo())
			save = true;
		recorridoSalvar(p->dameDerecha(), save);
	}
}

bool recorridoCargar(ArbolNodo *p)		// Función de auxiliar de recorrido para cargar el contenido del árbol
{
	bool cargado = false;

	if(p != NULL)
	{
		recorridoCargar(p->dameIzquierda());
		if(p->cargarArbolNodo())
			cargado = true;
		recorridoCargar(p->dameDerecha());
	}

	return cargado;
}

void recorridoPosLibre(ArbolNodo *auxRaiz, ArbolNodo *p, bool &libre, int &i)		// Pepino de función para buscar la posición libre dentro del árbol
{
	if(p != NULL)
	{
		if(libre == false)
		{
			recorridoPosLibre(auxRaiz, p->dameIzquierda(), libre, i);
			
			Nodo *punteroPos = p->dameLista()->damePrimerNodo();
				
			while(punteroPos != NULL && punteroPos->dameIndice().damePos() != i)
				punteroPos = punteroPos->dameSig();

			if(punteroPos != NULL)
			{
				//cout << endl << "Posición: " << punteroPos->dameIndice().damePos() << endl << endl;
				//cout << endl << "Nombre: " << punteroPos->dameIndice().dameNombre() << endl << endl;
			
				if(punteroPos->dameIndice().damePos() == i)
				{
					p = auxRaiz;
					i++;
					recorridoPosLibre(auxRaiz, p, libre, i);
				}
				else punteroPos = punteroPos->dameSig();
			}			

			if(libre == false)
				recorridoPosLibre(auxRaiz, p->dameDerecha(), libre, i);	
		}
	}

}
// Funciones de la clase



//-----------------------------------------------------------//



Arbol::Arbol()
{
	raiz = new ArbolNodo('M');
	int cont = 0 ;
	bool insertado = false;


	char nodosLetras[25] = {'G', 'D', 'B', 'A', 'C', 'E', 'F', 'J', 'H', 'I', 'K', 'L', 'S', 'P', 'O', 'N', 'Q', 'R', 'W', 'U', 'T', 'V', 'Y', 'X', 'Z'};

	for(int i = 0; i < 25; i++)		// Asignamos una letra a cada nodo del árbol
		arbolNodos[i] = ArbolNodo(nodosLetras[i]);

	
	while(cont < 25)
	{
		ArbolNodo *raizAux = raiz;
		ArbolNodo *nuevo = new ArbolNodo(arbolNodos[cont]);		// Generamos un nuevo nodo del árbol con su lista y letra correspondiente

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
				else raizAux = raizAux->dameIzquierda();
			}
			else 
			{
				if(raizAux->dameDerecha() == NULL)
				{
					raizAux->ponArbolNodoDerecha(nuevo);
					insertado = true;
				}
				else raizAux = raizAux->dameDerecha();
			}
		}
		cont++;
	}

	//cargarArbol(); DESHABILITADO		// Llamada al método cargarArbol desde el constructor para que cargue automáticamente los datos almacenados en el fichero Indices.dat
}

ArbolNodo *Arbol::dameRaiz()
{
	return raiz;
}

ArbolNodo *Arbol::dameArbolNodo(char miLetraArbol)		//Devuelve un puntero al nodo del árbol según la letra que recibe como parámetro
{
	ArbolNodo *raizAux = raiz;
	ArbolNodo *buscarLetra = new ArbolNodo(miLetraArbol);

	while(raizAux != NULL && (buscarLetra->dameLetraArbol() != raizAux->dameLetraArbol()))
	{
		if (buscarLetra->dameLetraArbol() < raizAux->dameLetraArbol())
			raizAux = raizAux->dameIzquierda();
		else 
		{
			if(buscarLetra->dameLetraArbol() > raizAux->dameLetraArbol())
				raizAux = raizAux->dameDerecha();
		}
	}

	return raizAux;
}

int Arbol::buscarPosLibre()
{
	int pos = 0;
	bool libre = false;
	ArbolNodo *auxRaiz = raiz;
	ArbolNodo *posLibre = auxRaiz;
	

	recorridoPosLibre(auxRaiz, posLibre, libre, pos);

	return pos;
}


bool Arbol::salvarArbol()
{
	bool salvado = false;

	//-----------------------------------
	FILE *fichero;

	fichero = fopen("Indices.dat", "wb");	// Reiniciar/Borrar el fichero de índices para evitar duplicados
	fclose(fichero);
	//-----------------------------------

	recorridoSalvar(raiz, salvado);

	return salvado;
}
			
bool Arbol::cargarArbol()
{
	bool cargado = false;

	if(recorridoCargar(raiz))
		cargado = true;

	return cargado;
}