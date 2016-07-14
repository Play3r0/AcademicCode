#pragma once
#include<string>
using namespace std;
class Indices
{
	private: string nombre;
			 int posicion;
						
	public: Indices();
			Indices(string miNombre, int miPosicion);
			void ponNombre(string miNombre);
			void ponPosicion(int miPosicion);
			string dameNombre();
			int damePosicion();
			~Indices();
};