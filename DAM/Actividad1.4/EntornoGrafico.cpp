#include "EntornoGrafico.h"

void  EntornoGrafico::mostrarContacto(Contacto c1)
{
	cout << endl << "DATOS DEL CONTACTO" << endl;
	cout << "Nombre: " << c1.dameNombre() << endl;
	if(c1.dameTelefono() != "")
		cout << "Tel�fono: " << c1.dameTelefono() << endl;
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
	cout << "2. Nombre y tel�fono" << endl;
	cout << "3. Nombre, tel�fono y correo" << endl;
	cout << "Selecciona una opci�n: ";
	cin >> opcion;
	while(opcion < 1 || opcion > 3)
	{
		cout << "ERROR, selecciona una opci�n correcta: ";
		cin >> opcion;
	}

	switch(opcion)
	{
		case 1: c1 = pedirNombre();
				break;

		case 2: c1 = pedirNombre();

				cout << "Introduce un n�mero de tel�fono: ";
				cin >> telefono;
				c1.ponTelefono(telefono);

				break;

		case 3: c1 = pedirNombre();

				cout << "Introduce un n�mero de tel�fono: ";
				cin >> telefono;
				c1.ponTelefono(telefono);
				cout << "Introduce una dirreci�n de correo: ";
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
	Contacto cAux;

	cout << endl << "�Qu� deseas modificar?: " << endl;
	cout << "1. Nombre" << endl;
	cout << "2. Tel�fono" << endl;
	cout << "3. Correo" << endl;
	cout << "Selecciona una opci�n: ";
	cin >> opcion;
	while(opcion < 1 || opcion > 3)
	{
		cout << "ERROR, selecciona una opci�n correcta: ";
		cin >> opcion;
	}

	switch(opcion)
	{
		case 1: cAux = pedirNombre();
				c1.ponNombre(cAux.dameNombre());
				break;

		case 2: cout << "Introduce un n�mero de tel�fono: ";
				cin >> telefono;
				c1.ponTelefono(telefono);

				break;

		case 3: cout << "Introduce una dirreci�n de correo: ";
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
	bool vacio = true;
	
	while(i < v1.size())
	{
		c1 = v1.at(i);
		cout << endl << "Nombre: " << c1.dameNombre() << endl;
		if(c1.dameTelefono() != "")
			cout << "Tel�fono: " << c1.dameTelefono() << endl;
		if(c1.dameEmail() != "")
			cout << "Email: " << c1.dameEmail() << endl;

		vacio = false;

		i++;
	}

	if(vacio)
		cout << endl << "No se han encontrado coincidencias!!" << endl;
}

void EntornoGrafico::mostrarNombresContactos(vector<string> v1)
{
	int i = 0;
	bool vacio = true;
	
	while(i < v1.size())
	{
		cout << endl << "Contacto n�mero: " << i + 1 << endl;
		cout << "Nombre: " << v1.at(i) << endl;

		vacio = false;

		i++;
	}

	if(vacio)
		cout << endl << "AGENDA VAC�A!!" << endl;
}

int EntornoGrafico::mostrarMenu()
{
	int menu = 0;

	cout << endl << endl << "MENU AGENDA" << endl;
	cout << "1. Mostrar un contacto" << endl;
	cout << "2. Modificar un contacto" << endl;
	cout << "3. A�adir un contacto" << endl;
	cout << "4. Borrar un contacto" << endl;
	cout << "5. Mostrar todos los contactos que empiecen por una cadena" << endl;
	cout << "6. Mostrar todos los contactos" << endl;
	cout << "0. Salir" << endl;
	cout << "Introduce una opci�n: ";
	cin >> menu;
	while(menu < 0 || menu > 6)
	{
		cout << "ERROR, selecciona una opci�n correcta: ";
		cin >> menu;
	}

	return menu;
}

void EntornoGrafico::mostrarMensaje(string cad1)		// M�todo para visualizar los mensajes del main.cpp
{
	cout << endl << cad1 << endl;
}