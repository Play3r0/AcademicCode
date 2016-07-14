// Sergio López Jurado
#include <iostream>
using namespace std;
#include <stdlib.h>
#include <time.h>
#include <string.h>
#define BARCOS 10
#define TAM 10
#define SINO 5



void inicializarTablero(int miTablero[][TAM], int miTableroJuego[][TAM]);
void generarBarcos(int miTablero[][TAM]);
void dibujarTableroSolucion(int miTablero[][TAM]);
char *menuJuego(int miTablero[][TAM], int miTableroJuego[][TAM]);
void dibujarTableroJuego(int miTableroJuego[][TAM]);


int main()
{
	srand(time(NULL));
	int tablero[TAM][TAM], tableroJuego[TAM][TAM];
	char respuesta[SINO];

	do
	{
		inicializarTablero(tablero, tableroJuego);

		generarBarcos(tablero);

		strcpy(respuesta, menuJuego(tablero, tableroJuego));

	}while(!strcmp(respuesta, "si"));

	cout << endl;
	cout << "Hasta la próxima!!" << endl;
	cout << endl;

	system("pause");
	return 0;
}

void inicializarTablero(int miTablero[][TAM], int miTableroJuego[][TAM])
{
	for(int i = 0; i < TAM; i++)  // BUCLE FOR QUE INICIALIZA TODAS LAS CASILLAS DEL TABLERO A 0
	{
		for(int j = 0; j < TAM; j++)
		{
			miTablero[i][j] = 0;
			miTableroJuego[i][j] = 0;
		}
	}
}

void generarBarcos(int miTablero[][TAM])
{
	int tabAux[TAM][TAM];
	int fila = 0, columna = 0, pegado = 0, columna2 = 0, fila2 = 0, bloq = 0, dir = 0, n = 0;

	for(int i = 0; i < TAM; i++)  // BUCLE FOR QUE INICIALIZA TODAS LAS CASILLAS DEL TABAUX A 0
	{
		for(int j = 0; j < TAM; j++)
		{
			tabAux[i][j] = 0;
			tabAux[i][j] = 0;
		}
	}


	for(int i = 0; i < BARCOS; i++)		// BUCLE FOR QUE COLOCA X "BARCOS" DE TAMAÑO 4 EN EL TABLERO DE FORMA ALEATORIA SIN TOCARSE UNOS CON OTROS
	{
		if(i < 1)		// N TAMAÑO Y NÚMERO DE BARCOS A GENERAR
			n = 4;
		else
		{
			if(i >= 1 && i < 3)
				n = 3;
			else
			{
				if(i >= 3 && i < 6)
					n = 2;
				else n = 1;
			}
		}

		fila = rand()%TAM;
		columna = rand()%TAM;
		int dirCol[2] = {columna-1, columna+1};
		int dirFila[2] = {fila-1, fila+1};

		bloq = rand()%2;		// 0 BLOQUEAR FILA, 1 BLOQUEAR COLUMNA

		if(bloq == 1)		// FILA BLOQUEADA
		{
			columna2 = dirCol[rand()%2];		// DIRECCIÓN DEL BARCO EN LA FILA BLOQUEADA

			if(columna2 < columna)
			{	
				if(columna2 > 1)
				{
					for(columna2 = columna; columna2 > columna - n; columna2--)
						tabAux[fila][columna2] = 1;

					fila2 = fila;
					columna2 = columna;
					if(miTablero[fila2][columna2] == 0)		// IF PARA COMPROBAR SI HAY BARCOS PEGADOS
					{
						for(int fTam = fila2 - 1; fTam <= fila2 + 1 && pegado != 1; fTam++)
						{
							for(int fCol = columna2 + 1; fCol >= columna2 - n && pegado != 1; fCol--)
							{
								if(fTam >= 0 && fTam < TAM && fCol >= 0 && fCol < TAM)
								{
									if(miTablero[fTam][fCol] == 1)
										pegado = 1;
								}
							}
						}
						if(pegado == 1)
							i--;
						else 
						{
							for(columna2 = columna; columna2 > columna - n; columna2--)
								miTablero[fila][columna2] = 1;
						}
						pegado = 0;
					}
					else i--;
				}
				else i--;
			}
			else 
			{
				if(columna2 <= 7)
				{
					for(columna2 = columna; columna2 < columna + n; columna2++)
						tabAux[fila][columna2] = 1;

					fila2 = fila;
					columna2 = columna;
					if(miTablero[fila2][columna2] == 0)		// IF PARA COMPROBAR SI HAY BARCOS PEGADOS
					{
						for(int fTam = fila2 - 1; fTam <= fila2 + 1 && pegado != 1; fTam++)
						{
							for(int fCol = columna2 - 1; fCol <= columna2 + n && pegado != 1; fCol++)
							{
								if(fTam >= 0 && fTam < TAM && fCol >= 0 && fCol < TAM)
								{
									if(miTablero[fTam][fCol] == 1)
										pegado = 1;
								}
							}
						}
						if(pegado == 1)
							i--;
						else 
						{
							for(columna2 = columna; columna2 < columna + n; columna2++)
								miTablero[fila][columna2] = 1;
						}
						pegado = 0;
					}
					else i--;
				}
				else i--;
			}
		}
		else		// COLUMNA BLOQUEADA
		{
			fila2 = dirFila[rand()%2];		// DIRECCIÓN DEL BARCO EN LA COLUMNA BLOQUEADA

			if(fila2 < fila)
			{	
				if(fila2 > 1)
				{
					for(fila2 = fila; fila2 > fila - n; fila2--)
						tabAux[fila2][columna] = 1;

					fila2 = fila;
					columna2 = columna;
					if(miTablero[fila2][columna2] == 0)		// IF PARA COMPROBAR SI HAY BARCOS PEGADOS
					{
						for(int fCol = columna2 - 1; fCol <= columna2 + 1 && pegado != 1; fCol++)
						{
							for(int fTam = fila2 + 1; fTam >= fila2 - n && pegado != 1; fTam--)
							{							
								if(fTam >= 0 && fTam < TAM && fCol >= 0 && fCol < TAM)
								{
									if(miTablero[fTam][fCol] == 1)
										pegado = 1;
								}
							}
						}
						if(pegado == 1)
							i--;
						else 
						{
							for(fila2 = fila; fila2 > fila - n; fila2--)
								miTablero[fila2][columna] = 1;
						}
						pegado = 0;
					}
					else i--;
				}
				else i--;
			}
			else 
			{
				if(fila2 <= 7)
				{
					for(fila2 = fila; fila2 < fila + n; fila2++)
						tabAux[fila2][columna] = 1;

					/*tabAux[fila][columna] = 1;
					tabAux[fila2][columna] = 1;
					fila2++;
					tabAux[fila2][columna] = 1;
					fila2++;
					tabAux[fila2][columna] = 1;*/

					fila2 = fila;
					columna2 = columna;
					if(miTablero[fila2][columna2] == 0)		// IF PARA COMPROBAR SI HAY BARCOS PEGADOS
					{
						for(int fCol = columna2 - 1; fCol <= columna2 + 1 && pegado != 1; fCol++)
						{
							for(int fTam = fila2 - 1; fTam <= fila2 + n && pegado != 1; fTam++)
							{
								if(fTam >= 0 && fTam < TAM && fCol >= 0 && fCol < TAM)
								{
									if(miTablero[fTam][fCol] == 1)
										pegado = 1;
								}
							}
						}
						if(pegado == 1)
							i--;
						else 
						{
							for(fila2 = fila; fila2 < fila + n; fila2++)
								miTablero[fila2][columna] = 1;
						}
						pegado = 0;
					}
					else i--;
				}
				else i--;
			}
		}
	}

	/*for(int i = 0; i < TAM; i++)  // MUESTRA EL TABLERO AUXILIAR
	{
		if(i != 9)
			cout << " " << i+1 << "  ";
		else cout << i+1 << "  ";
		for(int j = 0; j < TAM; j++)
		{
			cout << tabAux[i][j] << " ";
		}
		cout << endl;
	}*/

}

void dibujarTableroSolucion(int miTablero[][TAM])
{
	for(int i = 0; i < TAM; i++)  // MUESTRA EL TABLERO
	{
		if(i != 9)
			cout << " " << i+1 << "  ";
		else cout << i+1 << "  ";
		for(int j = 0; j < TAM; j++)
		{
			cout << miTablero[i][j] << " ";
		}
		cout << endl;
	}
}

char *menuJuego(int miTablero[][TAM], int miTableroJuego[][TAM])
{
	int fila = 0, columna = 0, vivos = BARCOS, comprobar = 0;
	char answer[SINO];

	while(vivos >= 1)  // NUCLEO DEL JUEGO
	{
		cout << endl;
		cout << "EL JUEGO DE LOS SUPER BARCOS" << endl;

		cout << endl << "    ";

		for(int j = 0; j < TAM; j++)
			cout << j+1 << " ";
		cout << "(Columnas)" << endl << endl;
		//dibujarTableroSolucion(miTablero);
		dibujarTableroJuego(miTableroJuego);
		cout << endl << "(Filas)" << endl << endl;
		cout << "SinMarcar: 0  Agua: 1  Barco: 7" << endl << endl;

		cout << "Introduce las coordenadas sobre a las que quieres disparar" << endl;
		cout << "Fila: ";
		cin >> fila;
		while(fila > 10 || fila < 1)
		{
			cout << "ERROR, introduce un número entre 1 y 10: ";
			cin >> fila;
		}
		cout << "Columna: ";
		cin >> columna;
		while(columna > 10 || columna < 1)
		{
			cout << "ERROR, introduce un número entre 1 y 10: ";
			cin >> columna;
		}
		cout << endl;

		if(miTableroJuego[fila-1][columna-1] == 0)
		{
			if(miTablero[fila-1][columna-1] == 1)
			{
				cout << "BOOOOM, headshot!!" << endl;
				vivos--;
				cout << "Barcos restantes: " << vivos << endl;
				miTableroJuego[fila-1][columna-1] = 7;
			}
			else
			{
				cout << "AGUA, prueba otra vez" << endl;
				cout << "Barcos restantes: " << vivos << endl;
				miTableroJuego[fila-1][columna-1] = 1;
			}
		}
		else
		{
			cout << "Esas coordenadas ya están marcadas, prueba otra vez" << endl;
			cout << "Barcos restantes: " << vivos << endl;
		}
	}

	cout << "¿Quieres volver a jugar?(si/no) ";

	cin >> answer;
	while(strcmp(answer, "si") != 0 && strcmp(answer, "no") != 0)
	{
		cout << endl << "WTF, 'si' o 'no'. Es MUY fácil: ";
		cin >> answer;
	}

	return answer;
}

void dibujarTableroJuego(int miTableroJuego[][TAM])
{
	for(int i = 0; i < TAM; i++)  // MUESTRA EL TABLERO
	{
		if(i != 9)
			cout << " " << i+1 << "  ";
		else cout << i+1 << "  ";
		for(int j = 0; j < TAM; j++)
		{
			cout << miTableroJuego[i][j] << " ";
		}
		cout << endl;
	}
}