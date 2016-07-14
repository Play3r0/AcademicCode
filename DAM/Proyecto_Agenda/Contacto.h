#include<string>
using namespace std;
#pragma once
class Contacto
{
private: string nombre;
		 string telefono;
		 string email;

public: Contacto();
		Contacto(string miNombre);
		Contacto(string miNombre, string miTelefono);
		Contacto(string miNombre, string miTelefono, string miEmail);
		void setNombre(string miNombre); 
		void setTelefono(string miTelefono);
		void setEmail(string miEmail);
		string getNombre();
		string getTelefono();
		string getEmail();
		~Contacto();
};

