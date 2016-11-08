#include "EntornoGrafico.h"

void  EntornoGrafico::mostrarContacto(Contacto c1)
{
	cout << endl << "DATOS DEL CONTACTO" << endl;
	cout << "Nombre: " << c1.dameNombre() << endl;
	if(c1.dameTelefono() != "")
		cout << "Teléfono: " << c1.dameTelefono() << endl;
	if(c1.dameEmail() != "")
		cout << "Email: " << c1.dameEmail() << endl;
}

Contacto EntornoGrafico::pedirContacto()
{
	int opcion = 0;
	string nombre = "";
	string telefono = "";
	string correo = "";
	Contacto c1;

	cout << endl << "CONTACTO" << endl;
	cout << "1. Nombre" << endl;
	cout << "2. Nombre y teléfono" << endl;
	cout << "3. Nombre, teléfono y correo" << endl;
	cout << "Selecciona una opción: ";
	cin >> opcion;
	while(opcion < 1 || opcion > 3)
	{
		cout << "ERROR, selecciona una opción correcta: ";
		cin >> opcion;
	}

	switch(opcion)
	{
		case 1: c1 = pedirNombre();
				break;

		case 2: c1 = pedirNombre();

				cout << "Introduce un número de teléfono: ";
				cin >> telefono;
				c1.ponTelefono(telefono);

				break;

		case 3: c1 = pedirNombre();

				cout << "Introduce un número de teléfono: ";
				cin >> telefono;
				c1.ponTelefono(telefono);
				cout << "Introduce una dirreción de correo: ";
				cin >> correo;
				c1.ponEmail(correo);

				break;

		default: break;
	}	


	return c1;
}

Contacto EntornoGrafico::pedirNombre()
{
	string nombre;

	cout << "Introduce el nombre del contacto: ";
	cin >> nombre;
	while(nombre[0] < 'A' || nombre[0] > 'z')
	{
		cout << "ERROR, el nombre debe empezar por una letra: ";
		cin >> nombre;
	}

	Contacto c1(nombre);

	return c1;
}

Contacto EntornoGrafico::pedirModContacto(Contacto c1)
{
	int opcion = 0;
	string nombre = "";
	string telefono = "";
	string correo = "";

	cout << endl << "¿Qué deseas modificar?: " << endl;
	cout << "1. Nombre" << endl;
	cout << "2. Teléfono" << endl;
	cout << "3. Correo" << endl;
	cout << "Selecciona una opción: ";
	cin >> opcion;
	while(opcion < 1 || opcion > 3)
	{
		cout << "ERROR, selecciona una opción correcta: ";
		cin >> opcion;
	}

	switch(opcion)
	{
		case 1: c1 = pedirNombre();
				break;

		case 2: cout << "Introduce un número de teléfono: ";
				cin >> telefono;
				c1.ponTelefono(telefono);

				break;

		case 3: cout << "Introduce una dirreción de correo: ";
				cin >> correo;
				c1.ponEmail(correo);

				break;

		default: break;
	}	

	return c1;
}

void EntornoGrafico::mostrarVectorContactos(vector<Contacto> v1)
{
	int i = 0;
	Contacto c1;
	
	while(i < v1.size())
	{
		c1 = v1.at(i);
		cout << endl << "Nombre: " << c1.dameNombre() << endl;
		if(c1.dameTelefono() != "")
			cout << "Teléfono: " << c1.dameTelefono() << endl;
		if(c1.dameEmail() != "")
			cout << "Email: " << c1.dameEmail() << endl;

		i++;
	}
}

void EntornoGrafico::mostrarNombresContactos(vector<string> v1)
{
	int i = 0;
	
	while(i < v1.size())
	{
		cout << endl << "Contacto número: " << i + 1 << endl;
		cout << "Nombre: " << v1.at(i) << endl;

		i++;
	}
}

void EntornoGrafico::mostrarMensaje(string cad1)
{
	cout << cad1 << endl;
}