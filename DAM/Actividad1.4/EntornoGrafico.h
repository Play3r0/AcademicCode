
#ifndef ENTORNOGRAFICO_H
#define ENTORNOGRAFICO_H

#include "InterfazES.h"

class EntornoGrafico: public InterfazES
{
	public:	void  mostrarContacto(Contacto);
			Contacto pedirContacto();
			Contacto pedirNombre();
			Contacto pedirModContacto(Contacto);
			void mostrarVectorContactos(vector<Contacto>);
			void mostrarNombresContactos(vector<string>);
			int mostrarMenu();
			void mostrarMensaje(string);

};
#endif