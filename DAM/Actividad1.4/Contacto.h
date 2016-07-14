
#ifndef CONTACTO_H
#define CONTACTO_H

#include <string>
#include <vector>
#include <iostream>
using namespace std;

class Contacto
{
	private: string nombre;
			 string telefono;
			 string email;

	public: Contacto();
			Contacto(string);
			Contacto(string, string);
			Contacto(string, string, string);
			void ponNombre(string);
			void ponTelefono(string);
			void ponEmail(string);
			string dameNombre();
			string dameTelefono();
			string dameEmail();

};
#endif