// Sergio López Jurado
#include <iostream>
using namespace std;
#include <stdlib.h>
#include <time.h>
#include <string.h>
#define TAM 10
#define BARCOS 15
#define SINO 5
//#define PEGADOS 3


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
	int fila = 0, columna = 0, pegado = 0;

	for(int i = 0; i < BARCOS; i++)  // BUCLE FOR QUE COLOCA 15 "BARCOS" EN EL TABLERO DE FORMA ALEATORIA SIN TOCARSE UNOS CON OTROS
	{
		fila = rand()%TAM;
		columna = rand()%TAM;
		pegado = 0;

		if(miTablero[fila][columna] == 0)
		{
			for(int fTam = fila - 1; fTam <= fila+1 && pegado != 1; fTam++)
			{
				for(int fCol = columna - 1; fCol <= columna+1 && pegado != 1; fCol++)
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
			else miTablero[fila][columna] = 1;

			/*if(columna == 0) // NO SE PUEDE COMPARAR CON LA PARTE IZQUIERDA DEL TABLERO
			{
				if(fila == 0) // NO SE PUEDE COMPARAR CON LA PARTE SUPERIOR DEL TABLERO
				{
					for(int fTam = fila; fTam < (fila)+2 && pegado != 1; fTam++)
					{
						if(miTablero[fTam][columna+1] == 1)
							pegado = 1;

						if(miTablero[fTam][columna] == 1)
							pegado = 1;
					}
					if(pegado == 1)
						i--;
					else miTablero[fila][columna] = 1;
				}
				else
				{
					if(fila == TAM-1) // NO SE PUEDE COMPARAR CON LA PARTE INFERIOR DEL TABLERO
					{
						for(int fTam = fila; fTam < (fila)+2 && pegado != 1; fTam++)
						{
							if(miTablero[fTam-1][columna+1] == 1)
								pegado = 1;

							if(miTablero[fTam-1][columna] == 1)
								pegado = 1;
						}
						if(pegado == 1)
							i--;
						else miTablero[fila][columna] = 1;
					}
					else
					{
						for(int fTam = fila; fTam < (fila)+3 && pegado != 1; fTam++)
						{
							if(miTablero[fTam-1][columna+1] == 1)
								pegado = 1;

							if(miTablero[fTam-1][columna] == 1)
								pegado = 1;
						}
						if(pegado == 1)
							i--;
						else miTablero[fila][columna] = 1;
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
							if(miTablero[fTam][columna-1] == 1)
								pegado = 1;

							if(miTablero[fTam][columna] == 1)
								pegado = 1;
						}
						if(pegado == 1)
							i--;
						else miTablero[fila][columna] = 1;
					}
					else
					{
						if(fila == TAM-1)  // NO SE PUEDE COMPARAR CON LA PARTE INFERIOR DEL TABLERO
						{
							for(int fTam = fila; fTam < (fila)+2 && pegado != 1; fTam++)
							{
								if(miTablero[fTam-1][columna-1] == 1)
									pegado = 1;

								if(miTablero[fTam-1][columna] == 1)
									pegado = 1;
							}
							if(pegado == 1)
								i--;
							else miTablero[fila][columna] = 1;
						}
						else
						{
							for(int fTam = fila; fTam < (fila)+3 && pegado != 1; fTam++)
							{
								if(miTablero[fTam-1][columna-1] == 1)
									pegado = 1;

								if(miTablero[fTam-1][columna] == 1)
									pegado = 1;
							}
							if(pegado == 1)
								i--;
							else miTablero[fila][columna] = 1;
						}
					}
				}
				else  // EL RESTO DE CASOS, ES DECIR TODAS LAS POSICIONES QUE HAY ENTRE 1 Y TAM-2 TANTO PARA LAS FILAS COMO PARA LAS COLUMNAS
				{
					if(fila == 0)  // NO SE PUEDE COMPARAR CON LA PARTE SUPERIOR DEL TABLERO
					{
						for(int fTam = fila; fTam < (fila)+2 && pegado != 1; fTam++)
						{
							if(miTablero[fTam][columna-1] == 1)
								pegado = 1;

							if(miTablero[fTam][columna] == 1)
								pegado = 1;

							if(miTablero[fTam][columna+1] == 1)
								pegado = 1;
						}
						if(pegado == 1)
							i--;
						else miTablero[fila][columna] = 1;
					}
					else
					{
						if(fila == TAM-1)  // NO SE PUEDE COMPARAR CON LA PARTE INFERIOR DEL TABLERO
						{
							for(int fTam = fila; fTam < (fila)+2 && pegado != 1; fTam++)
							{
								if(miTablero[fTam-1][columna-1] == 1)
									pegado = 1;

								if(miTablero[fTam-1][columna] == 1)
									pegado = 1;
								
								if(miTablero[fTam-1][columna+1] == 1)
									pegado = 1;
							}
							if(pegado == 1)
								i--;
							else miTablero[fila][columna] = 1;
						}
						else
						{
							for(int fTam = fila; fTam < (fila)+3 && pegado != 1; fTam++)
							{
								if(miTablero[fTam-1][columna-1] == 1)
									pegado = 1;

								if(miTablero[fTam-1][columna] == 1)
									pegado = 1;
								
								if(miTablero[fTam-1][columna+1] == 1)
									pegado = 1;
							}
							if(pegado == 1)
								i--;
							else miTablero[fila][columna] = 1;
						}
					}
				}
			}*/
		}
		else i--;
	}
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
		dibujarTableroSolucion(miTablero);
		//dibujarTableroJuego(miTableroJuego);
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
	/*if(strcmp(answer, "si") == 0)
		comprobar = 1;
	else
	{
		if(strcmp(answer, "no") == 0)
			comprobar = 1;
		else comprobar = 0;
	}

	while(comprobar == 0)
	{
		cout << endl << "WTF, 'si' o 'no'. Es MUY fácil: ";
		fflush(stdin);
		gets(answer);
		if(strcmp(answer, "si") == 0)
			comprobar = 1;
		else
		{
			if(strcmp(answer, "no") == 0)
				comprobar = 1;
			else comprobar = 0;
		}
	}*/

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