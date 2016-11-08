
#ifndef INTERFAZES_H
#define INTERFAZES_H

#include "Contacto.h"

class InterfazES
{
	public:	virtual void mostrarContacto(Contacto) = 0;
			virtual Contacto pedirContacto() = 0;
			virtual Contacto pedirNombre() = 0;
			virtual Contacto pedirModContacto(Contacto) = 0;
			virtual void mostrarVectorContactos(vector<Contacto>) = 0;
			virtual void mostrarNombresContactos(vector<string>) = 0;
			virtual void mostrarMensaje(string) = 0;

};
#endif