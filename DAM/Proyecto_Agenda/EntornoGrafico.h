#pragma once
#include "interfazentradasalida.h"
class EntornoGrafico : public InterfazEntradaSalida
{
public: EntornoGrafico();
		virtual void mostrarContacto(Contacto c);
		virtual Contacto pedirContacto();
		virtual Contacto pedirNombre();
		virtual Contacto pedirModificacionContacto(Contacto c);
		virtual string pedirCadena();
		virtual void mostrarVectorContactos(vector<Contacto> v);
		virtual void mostrarNombreContactos(vector<string> v);
		~EntornoGrafico();
		int menu();
		void fallo(int n);
};

