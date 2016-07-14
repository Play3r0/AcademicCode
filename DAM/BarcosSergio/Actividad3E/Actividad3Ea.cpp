// Sergio López Jurado
#include <iostream>
using namespace std;
#include <stdlib.h>
#include <time.h>
#define TAM 10
#define BARCOS 15
//#define PEGADOS 3

void inicializarTablero(int miTablero[][TAM]);

int main()
{
	srand(time(NULL));
	int tablero[TAM][TAM];
	int fila = 0, columna = 0, pegado = 0, vivos = BARCOS;


	inicializarTablero(tablero);

	
	for(int i = 0; i < BARCOS; i++)  // BUCLE FOR QUE COLOCA 15 "BARCOS" EN EL TABLERO DE FORMA ALEATORIA SIN TOCARSE UNOS CON OTROS
	{
		fila = rand()%TAM;
		columna = rand()%TAM;
		pegado = 0;

		if(tablero[fila][columna] == 0)
		{
			if(columna == 0) // NO SE PUEDE COMPARAR CON LA PARTE IZQUIERDA DEL TABLERO
			{
				if(fila == 0) // NO SE PUEDE COMPARAR CON LA PARTE SUPERIOR DEL TABLERO
				{
					for(int fTam = fila; fTam < (fila)+2 && pegado != 1; fTam++)
					{
						if(tablero[fTam][columna+1] == 1)
							pegado = 1;

						if(tablero[fTam][columna] == 1)
							pegado = 1;
					}
					if(pegado == 1)
						i--;
					else tablero[fila][columna] = 1;
				}
				else
				{
					if(fila == TAM-1) // NO SE PUEDE COMPARAR CON LA PARTE INFERIOR DEL TABLERO
					{
						for(int fTam = fila; fTam < (fila)+2 && pegado != 1; fTam++)
						{
							if(tablero[fTam-1][columna+1] == 1)
								pegado = 1;

							if(tablero[fTam-1][columna] == 1)
								pegado = 1;
						}
						if(pegado == 1)
							i--;
						else tablero[fila][columna] = 1;
					}
					else
					{
						for(int fTam = fila; fTam < (fila)+3 && pegado != 1; fTam++)
						{
							if(tablero[fTam-1][columna+1] == 1)
								pegado = 1;

							if(tablero[fTam-1][columna] == 1)
								pegado = 1;
						}
						if(pegado == 1)
							i--;
						else tablero[fila][columna] = 1;
					}
				}
			}
			else
			{ 
				if(columna == TAM-1)  // NO SE PUEDE COMPARAR CON LA PARTE DERECHA DEL TABLERO
				{
					if(fila == 0)  // NO SE PUEDE COMPARAR CON LA PARTE SUPERIOR DEL TABLERO
					{
						for(int fTam = fila; fTam < (fila)+2 && pegado != 1; fTam++)
						{
							if(tablero[fTam][columna-1] == 1)
								pegado = 1;

							if(tablero[fTam][columna] == 1)
								pegado = 1;
						}
						if(pegado == 1)
							i--;
						else tablero[fila][columna] = 1;
					}
					else
					{
						if(fila == TAM-1)  // NO SE PUEDE COMPARAR CON LA PARTE INFERIOR DEL TABLERO
						{
							for(int fTam = fila; fTam < (fila)+2 && pegado != 1; fTam++)
							{
								if(tablero[fTam-1][columna-1] == 1)
									pegado = 1;

								if(tablero[fTam-1][columna] == 1)
									pegado = 1;
							}
							if(pegado == 1)
								i--;
							else tablero[fila][columna] = 1;
						}
						else
						{
							for(int fTam = fila; fTam < (fila)+3 && pegado != 1; fTam++)
							{
								if(tablero[fTam-1][columna-1] == 1)
									pegado = 1;

								if(tablero[fTam-1][columna] == 1)
									pegado = 1;
							}
							if(pegado == 1)
								i--;
							else tablero[fila][columna] = 1;
						}
					}
				}
				else  // EL RESTO DE CASOS, ES DECIR TODAS LAS POSICIONES QUE HAY ENTRE 1 Y TAM-2 TANTO PARA LAS FILAS COMO PARA LAS COLUMNAS
				{
					if(fila == 0)  // NO SE PUEDE COMPARAR CON LA PARTE SUPERIOR DEL TABLERO
					{
						for(int fTam = fila; fTam < (fila)+2 && pegado != 1; fTam++)
						{
							if(tablero[fTam][columna-1] == 1)
								pegado = 1;

							if(tablero[fTam][columna] == 1)
								pegado = 1;

							if(tablero[fTam][columna+1] == 1)
								pegado = 1;
						}
						if(pegado == 1)
							i--;
						else tablero[fila][columna] = 1;
					}
					else
					{
						if(fila == TAM-1)  // NO SE PUEDE COMPARAR CON LA PARTE INFERIOR DEL TABLERO
						{
							for(int fTam = fila; fTam < (fila)+2 && pegado != 1; fTam++)
							{
								if(tablero[fTam-1][columna-1] == 1)
									pegado = 1;

								if(tablero[fTam-1][columna] == 1)
									pegado = 1;
								
								if(tablero[fTam-1][columna+1] == 1)
									pegado = 1;
							}
							if(pegado == 1)
								i--;
							else tablero[fila][columna] = 1;
						}
						else
						{
							for(int fTam = fila; fTam < (fila)+3 && pegado != 1; fTam++)
							{
								if(tablero[fTam-1][columna-1] == 1)
									pegado = 1;

								if(tablero[fTam-1][columna] == 1)
									pegado = 1;
								
								if(tablero[fTam-1][columna+1] == 1)
									pegado = 1;
							}
							if(pegado == 1)
								i--;
							else tablero[fila][columna] = 1;
						}
					}
				}
			}
		}
		else i--;
	}


	while(vivos >= 1)
	{
		cout << endl;
		cout << "EL JUEGO DE LOS SUPER BARCOS" << endl;
	
		cout << endl << "    ";

		for(int j = 0; j < TAM; j++)
				cout << j+1 << " ";
		cout << "(Columnas)" << endl << endl;
		for(int i = 0; i < TAM; i++)  // MUESTRA EL TABLERO
		{
			if(i != 9)
				cout << " " << i+1 << "  ";
			else cout << i+1 << "  ";
			for(int j = 0; j < TAM; j++)
			{
				cout << tablero[i][j] << " ";
			}
			cout << endl;
		}

	cout << endl << "(Filas)" << endl << endl;

		cout << "Introduce las coordenadas sobre a las que quieres disparar" << endl;
		cout << "Fila: ";
		cin >> fila;
		cout << "Columna: ";
		cin >> columna;
		cout << endl;

		if(tablero[fila-1][columna-1] == 1)
		{
			cout << "BOOOOM, headshot!!" << endl;
			vivos--;
			cout << "Barcos restantes: " << vivos << endl;
		}
		else
		{
			cout << "AGUA, prueba otra vez" << endl;
			cout << "Barcos restantes: " << vivos << endl;
		}
	}


	system("pause");
	return 0;
}

void inicializarTablero(int miTablero[][TAM])
{
	for(int i = 0; i < TAM; i++)  // BUCLE FOR QUE INICIALIZA TODAS LAS CASILLAS DEL TABLERO A 0
	{
		for(int j = 0; j < TAM; j++)
			miTablero[i][j] = 0;
	}
}