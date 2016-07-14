#include "GestorDatos.h"
GestorDatos::GestorDatos()
{
	arbol.montarArbol();
	FILE *fi;	 //fichero de indices(arbol)
	fi=fopen("Indices.dat","rb");
	if(fi==NULL)//Para que no de error la primera vez que lo abrimos
	{
		fi=fopen("Indices.dat","wb");
		fclose(fi);
		fi=fopen("Indices.dat","rb");
	}
	else
	{
		//Se carga el fichero de indices en el arbol
		Nodo *raiz=new Nodo();
		fread(&raiz,sizeof(raiz),1,fi);
		arbol.ponRaiz(raiz);
	}
	fclose(fi);
}

bool GestorDatos::añadirContacto(Contacto c)
{
	FILE *fc;   //fichero contactos
	if(buscarContacto(c)==false)
	{
		list<Indices>::iterator pos;
		list<Indices> *lista;
		lista=arbol.buscarLetra(c.getNombre());
		pos=lista->begin();

		while(c.getNombre()>pos->dameNombre())
			pos++;

		int p=arbol.siguientePosicion(arbol.dameRaiz());
		Indices ind=Indices(c.getNombre(),p);
		lista->insert(pos,1,ind);

		fc=fopen("Contactos.dat","wb");
		fseek(fc,p*sizeof(c),0);
		fwrite(&c,sizeof(c),1,fc);
		fclose(fc);
		return true;
	}
	else return false;
}

bool GestorDatos::buscarContacto(Contacto c)
{
	list<Indices> *lista;
	lista=arbol.buscarLetra(c.getNombre());
	list<Indices>::iterator pos;
	
	int sw=0;
	for(pos=lista->begin();pos!=lista->end() && sw==0;pos++)
	{
		if(pos->dameNombre()==c.getNombre())
		{
			sw=1;
		}
	}
	if(sw==1)return true;
	else return false;
}

bool GestorDatos::borrarContacto(Contacto c)
{
	if(buscarContacto(c)==true)
	{
		list<Indices> *lista;
		lista=arbol.buscarLetra(c.getNombre());
		list<Indices>::iterator pos;
		int sw=0;
		for(pos=lista->begin();pos!=lista->end() && sw==0;pos++)
		{
			if(pos->dameNombre()==c.getNombre())
			{
				lista->erase(pos);
				sw=1;
			}
		}
		return true;
	}
	else return false;

}

bool GestorDatos::modificarContacto(Contacto viejo, Contacto nuevo)
{
	FILE *fc;   //fichero contactos
	list<Indices> *lista;
	lista=arbol.buscarLetra(viejo.getNombre());
	if(lista!=NULL)
	{
		list<Indices>::iterator pos;
		pos=lista->begin();
		int sw=0;
		while(pos!=lista->end() && sw==0)
		{
			if(pos->dameNombre()==viejo.getNombre())
			{
				fc=fopen("Contactos.dat","wb");
				fseek(fc,pos->damePosicion()*sizeof(nuevo),0);
				fwrite(&nuevo,sizeof(nuevo),1,fc);
				fclose(fc);	
				sw=1;
			}
		}
		return true;
	}
	else return false;
}

vector<Contacto> GestorDatos::buscarCadenaContacto(string cad)
{
	FILE *fc;   //fichero contactos
	fc=fopen("Contactos.dat","rb");
	Contacto c;

	int p;
	list<Indices> *l;
	vector<Contacto> v;
	l=arbol.buscarLetra(cad);
	if(l!=NULL)
	{
		list<Indices>::iterator pos;
		for(pos=l->begin();pos!=l->end();pos++)
		{
			if(pos->dameNombre().find(cad))
			{
				p=pos->damePosicion();
				fseek(fc,p*sizeof(c),0);
				fread(&c,sizeof(c),1,fc);
				v.push_back(c);
			}
		}
		fclose(fc);	
	}
	return v;
}

vector<string> GestorDatos::buscarTodosContactos()
{
	string letra="a";
	vector<string> v;
	list<Indices> *lista;
	list<Indices>::iterator pos;
	while(letra.at(0)<='x')
	{
		lista=arbol.buscarLetra(letra);
		if(lista!=NULL)
		{
			for(pos=lista->begin();pos!=lista->end();pos++)
			{
				v.push_back(pos->dameNombre());
			}
		}
		letra.at(0)++;
	}
	return v;
}

Contacto GestorDatos::buscarContactoParaMostrar(Contacto c)
{
	FILE *fc;   //fichero contactos
	fc=fopen("Contactos.dat","rb");

	int p,sw=0;
	list<Indices> *l;
	vector<Contacto> v;
	l=arbol.buscarLetra(c.getNombre());
	if(l!=NULL)
	{
		list<Indices>::iterator pos;
		for(pos=l->begin();pos!=l->end() && sw==0;pos++)
		{
			if(pos->dameNombre()==c.getNombre())
			{
				p=pos->damePosicion();
				fseek(fc,p*sizeof(c),0);
				fread(&c,sizeof(c),1,fc);
				sw=1;
			}
		}
		fclose(fc);	
	}
	return c;
}

GestorDatos::~GestorDatos()
{
	FILE *fi;	 //fichero de indices(arbol)
	fi=fopen("Indices.dat","wb");
	Nodo *raiz=new Nodo();           
	raiz=arbol.dameRaiz();
	fwrite(&raiz,sizeof(raiz),1,fi);
	fclose(fi);
}
