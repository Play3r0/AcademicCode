#pragma once
#include"Contacto.h"
#include<iostream>
#include<string>
#include<list>
#include<vector>
using namespace std;
class InterfazEntradaSalida
{
public: virtual void mostrarContacto(Contacto c)=0;
		virtual Contacto pedirContacto()=0;
		virtual Contacto pedirNombre()=0;
		virtual Contacto pedirModificacionContacto(Contacto c)=0;
		virtual string pedirCadena()=0;
		virtual void mostrarVectorContactos(vector<Contacto> v)=0;
		virtual void mostrarNombreContactos(vector<string> v)=0; 

};