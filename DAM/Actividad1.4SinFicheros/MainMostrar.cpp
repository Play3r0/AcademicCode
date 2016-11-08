#include "Arbol.h"

void recorridoPre(ArbolNodo *p);


int main()
{

	Arbol arbol= Arbol();


	recorridoPre(arbol.dameRaiz());


	system("PAUSE");

}

void recorridoPre(ArbolNodo *p)
{
	if(p!=NULL)
	{
		recorridoPre(p->dameIzquierda());
		cout << "Letra: " << p->dameLetraArbol() << endl;
		if(p->dameLista()->damePrimerNodo() != NULL)
			cout << "Lista: " << p->dameLista()->damePrimerNodo()->dameContacto().dameNombre() << endl;

		recorridoPre(p->dameDerecha());
	}

}