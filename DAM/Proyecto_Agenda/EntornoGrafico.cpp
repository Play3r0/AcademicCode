#include "EntornoGrafico.h"


EntornoGrafico::EntornoGrafico()
{
}

void EntornoGrafico::mostrarContacto(Contacto c)
{
	cout<<"\n\nNombre: "<<c.getNombre()<<"\nTelefono: "<<c.getTelefono()<<"\nEmail: "<<c.getEmail();
}

Contacto EntornoGrafico::pedirContacto()
{
	Contacto c;
	string cad;
	cout<<"Introduce el nombre del contacto(todo en minusculas): ";
	cin>>cad;
	c.setNombre(cad);
	cout<<"Introduce el telefono del contacto: ";
	cin>>cad;
	c.setTelefono(cad);
	cout<<"Introduce el email del contacto: ";
	cin>>cad;
	c.setEmail(cad);

	return c;

}

Contacto EntornoGrafico::pedirNombre()
{
	string cad;
	cout<<"Introduce el nombre del contacto(todo en minusculas): ";
	cin>>cad;
	Contacto c=Contacto(cad);
	return c;
}

Contacto EntornoGrafico::pedirModificacionContacto(Contacto c)
{
	string cad;
	int op;
	cout<<"¿Que desea modificar del contacto?\n 1.Nombre\n 2.Telefono\n 3.Email\n  Opcion elegida:  ";
	cin>>op;
	while(op<1 || op>3)
	{
		fflush(stdin);
		cout<<"Opcion incorrecta, por favor vuelva a introducirla: ";
		cin>>op;
	}
	switch(op)
	{
	case 1: cout<<"Introduce el nuevo nombre del contacto(todo en minusculas): ";
			cin>>cad;
			c.setNombre(cad);
			break;

	case 2: cout<<"Introduce el nuevo telefono del contacto: ";
			cin>>cad;
			c.setEmail(cad);

	case 3: cout<<"Introduce el nuevo email del contacto: ";
			cin>>cad;
			c.setEmail(cad);
	}

	return c;
}

string EntornoGrafico::pedirCadena()//Para pedir la cadena que despues se utiliza para buscar un contacto
{
	string cad;
	cout<<"Indique el nombre o cadena a buscar(todo en minusculas): ";
	cin>>cad;
	return cad;
}

void EntornoGrafico::mostrarVectorContactos(vector<Contacto> v)
{
	Contacto c;
	vector<Contacto>::iterator pos;
	for(pos=v.begin();pos!=v.end();pos++)
	{
		c=*pos;
		mostrarContacto(c);
	}
}

void EntornoGrafico::mostrarNombreContactos(vector<string> v)
{
	vector<string>::iterator pos;
	for(pos=v.begin();pos!=v.end();pos++)
	{
		cout<<"\n\nNombre: "<<*pos;
	}
}

int EntornoGrafico::menu()
{
	int op;
	cout<<"\n\tMENU\n1.Mostrar un contacto. \n2.Modificar un contacto. \n3.Añadir un contacto. \n4.Borrar un contacto. \n5.Mostrar todos los contactos que empiecen por una cadena. \n6.Mostrar agenda entera(solo los nombres).\n7.Salir\n  Opcion elegida:";
	cin>>op;
	while(op<1 || op>7)
	{
		fflush(stdin);
		cout<<"Opcion incorrecta, por favor vuelva a introducirla. ";
		cin>>op;
	}
	return op;
}

void EntornoGrafico::fallo(int n)
{
	if(n==1)
		cout<<"No se han encontrado los datos especificados.";
	if(n==2)
		cout<<"Ya existe un contacto con ese nombre.";
}

EntornoGrafico::~EntornoGrafico()
{
}
