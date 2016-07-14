
#include "GestorAD.h"


// Funciones de la clase
void recorridoPre(ArbolNodo *p, vector<string> &vAux)
{
	if(p != NULL)
	{
		recorridoPre(p->dameIzquierda(), vAux);
		
		
		if(p->dameLista()->damePrimerNodo() != NULL)	// Para evitar Contacto = NULL. Primera ejecuci�n. Error xstring
		{
			Nodo *n = p->dameLista()->damePrimerNodo();
			
			while(n != NULL)
			{
				vAux.push_back(n->dameIndice().dameNombre());
				n = n->dameSig();
			}
		}

		recorridoPre(p->dameDerecha(), vAux);
		
	}
}


void recorridoPre2(ArbolNodo *p)		// Funci�n de recorrido auxiliar para mostrar el �rbol y todo su contenido
{
	if(p != NULL)
	{
		recorridoPre2(p->dameIzquierda());
		cout << endl << "MOSTRAR LETRA: " << p->dameLetraArbol() << endl;
		if(p->dameLista()->damePrimerNodo() != NULL)
		{
			Nodo *n = p->dameLista()->damePrimerNodo();
			
			cout << "Lista: " << endl << endl;
			while(n != NULL)
			{
				cout << n->dameIndice().dameNombre() << endl;
				cout << n->dameIndice().damePos() << endl;
				n = n->dameSig();
			}
			
		}

		recorridoPre2(p->dameDerecha());
	}
}


Lista *devolverLista(Arbol arbolAux, Contacto c2)
{
	char inicial = ' ';
	string nombre = "";
	Lista *miLista;

	nombre = c2.dameNombre();
	
	inicial = nombre[0];
	inicial = toupper(inicial);

	miLista = arbolAux.dameArbolNodo(inicial)->dameLista();

	return miLista;
}


// Pasa una cadena a may�sculas en "caliente"
// Pj para comparar dos cadenas, independientemente de si est�n en may�sculas, min�sculas o una combinaci�n de ambas
string mayus2(string s1)
{ 
   string aux = s1; 
   
   for(int i = 0; i < s1.size(); i++) 
      aux.at(i) = toupper(aux.at(i)); 
   
   return aux; 
}


vector<Contacto> recorrerLista(Lista *listaAux, vector<Contacto> &vAux, Contacto c2)
{
	string cad, cad3 = c2.dameNombre();
	string cad2(cad3);

	if(listaAux->damePrimerNodo() != NULL)	// Para evitar Contacto = NULL. Primera ejecuci�n. Error xstring
	{
		Nodo *n = listaAux->damePrimerNodo();
		
		while(n != NULL)
		{
			cad = n->dameIndice().dameNombre();
			
			if (cad.size() != cad3.size())
			{
				n = n->dameSig();
				continue;
			}
			for(int i = 0; i < cad3.size(); i++)
				cad2[i] = cad[i];

			if(mayus2(cad2) == mayus2(cad3))
				vAux.push_back(n->dameIndice().dameNombre());
				
			n = n->dameSig();
		}
	}

	return vAux;
}


int buscarPosicionLibre(Arbol auxNucleo)
{
	int pos = 0;

	pos = auxNucleo.buscarPosLibre();

	return pos;
}
// Funciones de la clase


//-----------------------------------------------------------//


// M�todos de la clase
GestorAD::GestorAD()
{
	//arbolNucleo = Arbol();		// �Quiero inicializar el �rbol dos veces? Ya genera una vez el n�cleo y todos los nodos �?�?�?
}

Arbol GestorAD::dameArbol()
{
	return arbolNucleo;
}

bool GestorAD::aniadirContacto(Contacto c1)
{	
	bool aniadido = false;
	Lista *listaLetra = devolverLista(arbolNucleo, c1);
	int pos = 0;
	
	if(!buscarContacto(c1))		// Si el contacto no existe, lo insertamos
	{
		pos = buscarPosicionLibre(arbolNucleo);

		if(listaLetra->insertarContacto(c1, pos))
			aniadido = true;
	}

	return aniadido;
}

bool GestorAD::buscarContacto(Contacto &c1)
{
	bool encontrado = false;
	Lista *listaLetra = devolverLista(arbolNucleo, c1);


	if(listaLetra->buscarContacto(c1))
		encontrado = true;

	return encontrado;
}

bool GestorAD::borrarContacto(Contacto c1)
{
	bool borrado = false;
	Lista *listaLetra = devolverLista(arbolNucleo, c1);


	if(listaLetra->borrarContacto(c1))
		borrado = true;


	return borrado;
}

bool GestorAD::modContacto(Contacto viejo, Contacto nuevo)
{
	bool modificado = false;
	int pos = 0;
	Lista *listaLetra = devolverLista(arbolNucleo, viejo);
	Lista *listaLetra2 = devolverLista(arbolNucleo, nuevo);

	Nodo *posLibre = listaLetra->damePrimerNodo();

	while(posLibre != NULL && mayus2(posLibre->dameIndice().dameNombre()) != mayus2(viejo.dameNombre()))
		posLibre = posLibre->dameSig();

	pos = posLibre->dameIndice().damePos();

	listaLetra->borrarContacto(viejo);		// Eliminamos del �ndice el viejo contacto

	if(listaLetra2->insertarContacto(nuevo, pos))		// Utilizamos el mismo m�todo insertarContacto para insertar un contacto modificado en la lista
		modificado = true;

	return modificado;
}

vector<Contacto> GestorAD::buscarCadenaContacto(string cad1)
{
	vector<Contacto> v1;
	Arbol arbolAux = arbolNucleo;
	Lista *miLista;
	Contacto c1;

	c1.ponNombre(cad1);

	recorrerLista(devolverLista(arbolAux, c1), v1, c1);

	return v1;
}

vector<string> GestorAD::buscarTodosContactos()		// Busca todos los nombres y los introduce en un vector para enviarlos a la capa de E/S
{
	vector<string> v1;
	string nombre = "";
	

	Arbol arbolAux = arbolNucleo;

	recorridoPre(arbolAux.dameRaiz(), v1);

	//recorridoPre2(arbolAux.dameRaiz());		// Habilitar la funci�n para mostrar todos los contactos del �ndice. Nombre y posici�n en el fichero



	//-----------------------------------------------
	// Bloque auxiliar
	/*cout << endl << "DATOS DE CONTACTOS.DAT" << endl;		// Habilitar bloque para mostrar todos los datos del fichero Contactos.dat. (Desactivado por defecto)
	Contacto *c1 = new Contacto();		
	FILE *fichero;

	fichero = fopen("Contactos.dat", "rb");

	if(fichero != NULL)
	{
		fread(c1, sizeof(Contacto), 1, fichero);

		while(!feof(fichero))
		{
			cout << "Nombre: " << c1->dameNombre() << endl;
			if(c1->dameTelefono() != "")
				cout << "Tel�fono: " << c1->dameTelefono() << endl;
			if(c1->dameEmail() != "")
				cout << "Email: " << c1->dameEmail() << endl;
			cout << endl;

			fread(c1, sizeof(Contacto), 1, fichero);
		}

		fclose(fichero);
	}*/
	// Bloque auxiliar
	//-----------------------------------------------



	return v1;
}

bool GestorAD::salvarDatos()
{
	bool salvado = false;

	if(arbolNucleo.salvarArbol())
		salvado = true;

	return salvado;
}

bool GestorAD::cargarDatos()
{
	bool cargado = false;

	if(arbolNucleo.cargarArbol())
		cargado = true;

	return cargado;
}
// M�todos de la clase