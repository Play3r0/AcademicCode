#include "Contacto.h"


Contacto::Contacto()
{
	nombre="";
	telefono="";
	email="";
}

Contacto::Contacto(string miNombre)
{
	nombre=miNombre;
	telefono="";
	email="";
}

Contacto::Contacto(string miNombre, string miTelefono)
{
	nombre=miNombre;
	telefono=miTelefono;
	email="";
}

Contacto::Contacto(string miNombre, string miTelefono, string miEmail)
{
	nombre=miNombre;
	telefono=miTelefono;
	email=miEmail;
}

void Contacto::setNombre(string miNombre)
{
	nombre=miNombre;
}

void Contacto::setTelefono(string miTelefono)
{
	telefono=miTelefono;
}

void Contacto::setEmail(string miEmail)
{
	email=miEmail;
}

string Contacto::getNombre()
{
	return nombre;
}

string Contacto::getTelefono()
{
	return telefono;
}

string Contacto::getEmail()
{
	return email;
}

Contacto::~Contacto(void)
{
}
