
public class Resolver {

	private int[][] sudoku;
	private int contador = 1;
	private boolean traza;

	public Resolver() { // Si no le pasamos ningún sudoku
		sudoku = new int[9][9];
	}

	public Resolver(int sudoku[][], boolean traza) { // si le pasamos un sudoku para resolver
		this.sudoku = sudoku;
		this.traza = traza;
	}

	public boolean resolver() {
	
		for (int fila = 0; fila < 9; fila++) {
			for (int columna = 0; columna < 9; columna++) {
				if (sudoku[fila][columna] == 0) {
					for (int numero = 1; numero <= 9; numero++) {
						if (!estaElNumero(fila, columna, numero)) {
							sudoku[fila][columna] = numero;
							if (resolver()) {
								if (traza) {
									if (contador % 5 == 0) { // para que no imprima más de 5 por linea
										System.out.println();
									}

									System.out.print("Lamada T nº: " + contador + " ");
									System.out.print(" Fila: " + (fila + 1) + " Columna: " + (columna + 1) + "   ");

									contador++;
								}
								return true;
							} else {
								sudoku[fila][columna] = 0;
								if (traza) {
									if (contador % 5 == 0) {  // para que no imprima más de 5 por linea
										System.out.println();
									}

									System.out.print("Lamada F nº: " + contador + " ");
									System.out.print(" Fila: " + (fila + 1) + " Columna: " + (columna + 1) + "   ");

									contador++;
								}
							}
						}
					}
					return false;
				}
			}
		}

		return true;
	}

	// Comprueba que el número está en esa fila
	private boolean estaEnLaFila(int fila, int numero) {
		for (int i = 0; i < 9; i++) {
			if (sudoku[fila][i] == numero) {
				return true;
			}
		}
		return false;
	}

	// Comprueba que el número está en esa columna
	private boolean estaEnLaColumna(int columna, int numero) {
		for (int i = 0; i < 9; i++) {
			if (sudoku[i][columna] == numero) {
				return true;
			}
		}
		return false;
	}

	// Comprueba que el número este en ese bloque de 3x3
	private boolean estaEnElBloque(int fila, int columna, int numero) {
		int fila2 = fila - fila % 3;
		int columna2 = columna - columna % 3;

		for (int i = fila2; i < fila2 + 3; i++) {
			for (int j = columna2; j < columna2 + 3; j++) {
				if (sudoku[i][j] == numero) {
					return true;
				}
			}

		}
		return false;
	}

	// retorna true si está el número en la fila, o en la columna o en el bloque
	private boolean estaElNumero(int fila, int columna, int numero) {
		return (estaEnLaFila(fila, numero) || estaEnLaColumna(columna, numero)
				|| estaEnElBloque(fila, columna, numero));
	}

	public void imprimirSudoku() {
		System.out.println("\n\n\n");
		System.out.println("         Imprimir Sudoku           ");
		System.out.println("_________________________________\n");
		for (int i = 0; i < 9; i++) {
			if (i % 3 == 0 && i != 0) {
				System.out.println("_________________________________\n");
			}
			for (int j = 0; j < 9; j++) {
				if (j % 3 == 0 && j != 0) {
					System.out.print(" | ");
				}
				System.out.print(" " + sudoku[i][j] + " ");

			}

			System.out.println();
		}
		System.out.println("_________________________________\n\n");
	}
}
