#pragma once
#include"Contacto.h"
#include<vector>
#include<string>
using namespace std;
class InterfazAccesoDatos
{
public: virtual bool añadirContacto(Contacto c)=0;
		virtual bool buscarContacto(Contacto c)=0;
		virtual bool borrarContacto(Contacto c)=0;
		virtual bool modificarContacto(Contacto viejo, Contacto nuevo)=0;
		virtual vector<Contacto> buscarCadenaContacto(string Cadena)=0;
		virtual vector<string> buscarTodosContactos()=0;
};