
#include "Contacto.h"

Contacto::Contacto()
{
	nombre = "";
	telefono = "";
	email = "";
}

Contacto::Contacto(string miNombre)
{
	nombre = miNombre;
	telefono = "";
	email = "";
}

Contacto::Contacto(string miNombre, string miTelefono)
{
	nombre = miNombre;
	telefono = miTelefono;
	email = "";
}

Contacto::Contacto(string miNombre, string miTelefono, string miEmail)
{
	nombre = miNombre;
	telefono = miTelefono;
	email = miEmail;
}

void Contacto::ponNombre(string miNombre)
{
	nombre = miNombre;
}

void Contacto::ponTelefono(string miTelefono)
{
	telefono = miTelefono;
}

void Contacto::ponEmail(string miEmail)
{
	email = miEmail;
}

string Contacto::dameNombre()
{
	return nombre;
}

string Contacto::dameTelefono()
{
	return telefono;
}

string Contacto::dameEmail()
{
	return email;
}