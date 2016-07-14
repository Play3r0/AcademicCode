#include "Arbol.h"

Arbol::Arbol()
{
	raiz=NULL;
}

void Arbol::montarArbol()
{
	//raiz
	raiz=new Nodo("m");

	//1 nivel

	Nodo *nuevoDchR=new Nodo("r");
	Nodo *nuevoIzqG=new Nodo("g");
	raiz->ponDch(nuevoDchR);
	raiz->ponIzq(nuevoIzqG);

	//2 nivel

	Nodo *nuevoDchU=new Nodo("u");
	Nodo *nuevoIzqO=new Nodo("o");
	raiz->dameDch()->ponDch(nuevoDchU);
	raiz->dameDch()->ponIzq(nuevoIzqO);

	Nodo *nuevoDchJ=new Nodo("j");
	Nodo *nuevoIzqD=new Nodo("d");
	raiz->dameIzq()->ponDch(nuevoDchJ);
	raiz->dameIzq()->ponIzq(nuevoIzqD);

	//3 nivel

	Nodo *nuevoDchW=new Nodo("w");
	Nodo *nuevoIzqT=new Nodo("t");
	raiz->dameDch()->dameDch()->ponDch(nuevoDchW);
	raiz->dameDch()->dameDch()->ponIzq(nuevoIzqT);

	Nodo *nuevoDchQ=new Nodo("q");
	Nodo *nuevoIzqÑ=new Nodo("ñ");
	raiz->dameDch()->dameIzq()->ponDch(nuevoDchQ);
	raiz->dameDch()->dameIzq()->ponIzq(nuevoIzqÑ);

	Nodo *nuevoDchL=new Nodo("l");
	Nodo *nuevoIzqI=new Nodo("i");
	raiz->dameIzq()->dameDch()->ponDch(nuevoDchL);
	raiz->dameIzq()->dameDch()->ponIzq(nuevoIzqI); 

	Nodo *nuevoDchF=new Nodo("f");
	Nodo *nuevoIzqB=new Nodo("b");
	raiz->dameIzq()->dameIzq()->ponDch(nuevoDchF);
	raiz->dameIzq()->dameIzq()->ponIzq(nuevoIzqB);

	//4 nivel

	Nodo *nuevoDchXYZ=new Nodo("xyz");
	Nodo *nuevoIzqV=new Nodo("v");
	raiz->dameDch()->dameDch()->dameDch()->ponDch(nuevoDchXYZ);
	raiz->dameDch()->dameDch()->dameDch()->ponIzq(nuevoIzqV);

	Nodo *nuevoIzqS=new Nodo("s");
	raiz->dameDch()->dameDch()->dameIzq()->ponIzq(nuevoIzqS);

	Nodo *nuevoIzqP=new Nodo("p");
	raiz->dameDch()->dameIzq()->dameDch()->ponIzq(nuevoIzqP);
	
	Nodo *nuevoIzqN=new Nodo("n");
	raiz->dameDch()->dameIzq()->dameIzq()->ponIzq(nuevoIzqN);

	Nodo *nuevoIzqK=new Nodo("k");
	raiz->dameIzq()->dameDch()->dameDch()->ponIzq(nuevoIzqK);

	Nodo *nuevoIzqH=new Nodo("h");
	raiz->dameIzq()->dameDch()->dameIzq()->ponIzq(nuevoIzqH);

	Nodo *nuevoIzqE=new Nodo("e");
	raiz->dameIzq()->dameIzq()->dameDch()->ponIzq(nuevoIzqE);

	Nodo *nuevoDchC=new Nodo("c");
	Nodo *nuevoIzqA=new Nodo("a");
	raiz->dameIzq()->dameIzq()->dameIzq()->ponDch(nuevoDchC);
	raiz->dameIzq()->dameIzq()->dameIzq()->ponIzq(nuevoIzqA);
}

list<Indices> *Arbol::buscarLetra(string miCadena)
{
	Nodo *p=new Nodo();
	p=raiz;
	while(miCadena.at(0)!=p->dameLetra().at(0))
	{
		if(miCadena.at(0)>p->dameLetra().at(0))
			p=p->dameDch();
		else p=p->dameIzq();
	}
	if(miCadena.at(0)==p->dameLetra().at(0))
		return p->dameLista();

	else return raiz->dameDch()->dameDch()->dameDch()->dameDch()->dameLista();
}

void Arbol::ponRaiz(Nodo *miRaiz)
{
	raiz=miRaiz;
}

Nodo *Arbol::dameRaiz()
{
	return raiz;
}

int Arbol::siguientePosicion(Nodo *n)
{
	int p=1,sw=0;
	while(sw==0)
	{
		siguientePosicion(n->dameIzq());
		list<Indices> *lista;
		lista=n->dameLista();
		if(lista!=NULL)
		{
			list<Indices>::iterator pos;
			pos=lista->begin();
			int sw2=0;
			while(sw2==0 && pos!=lista->end())
			{
				if(pos->damePosicion()==p)
				{
					sw2=1;;
				}
				pos++;
			}
			if(sw2==0) sw=1;
			else p++;
		}
		siguientePosicion(n->dameDch());
	}
	return p;
}

Arbol::~Arbol()
{
}
