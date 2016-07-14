#pragma once
#include<vector>
#include"interfazaccesodatos.h"
#include"Arbol.h"
#include"Nodo.h"
using namespace std;
class GestorDatos : public InterfazAccesoDatos
{
private: Arbol arbol;
		 
public: GestorDatos();
		virtual bool añadirContacto(Contacto c);
		virtual bool buscarContacto(Contacto c);
		virtual bool borrarContacto(Contacto c);
		virtual bool modificarContacto(Contacto viejo, Contacto nuevo);
		virtual vector<Contacto> buscarCadenaContacto(string Cadena);
		virtual vector<string> buscarTodosContactos();
		Contacto buscarContactoParaMostrar(Contacto c);
		~GestorDatos(void);
};

