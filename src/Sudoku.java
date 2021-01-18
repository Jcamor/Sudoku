import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Scanner;


public class Sudoku {

	static int[][] tablasudoku = {  /// sudoku de ejemplo
			{ 2, 0, 5, 0, 0, 0, 0, 0, 0 },
			{ 3, 0, 8, 6, 0, 0, 9, 0, 0 },
			{ 0, 0, 0, 1, 0, 0, 4, 0, 0 },
			{ 0, 0, 0, 0, 5, 0, 0, 1, 0 },
			{ 0, 0, 0, 0, 9, 0, 0, 2, 0 },
			{ 8, 7, 0, 0, 2, 0, 0, 0, 0 },
			{ 0, 0, 0, 0, 8, 9, 0, 0, 3 },
			{ 0, 0, 6, 0, 0, 3, 0, 0, 5 },
			{ 5, 0, 4, 0, 0, 0, 0, 0, 1 } 
			};
	static boolean correcto = false;
	static String nombreArchivo;
	static boolean traza = false;
	static boolean ficheroEntrada = false;



	public static void main(String[] args) {

		switch (args.length) {
		
		case 0:
			correcto = true;
			break;
		case 1:
			if (args[0].equals("-h")) { // ayuda
				correcto = false;
				break;
			} else {
				if (args[0].equals("-t")) { // traza
					traza = true;
				} else {
					nombreArchivo = args[0]; // entrada por archivo
					ficheroEntrada = true;
				}
				correcto = true;
				break;
			}

		case 2:
			if (args[0].equals("-h")) {
				correcto = false;
			} else {
				if (args[0].equals("-t")) {
					traza = true;
					ficheroEntrada = true;
					nombreArchivo = args[1];
					correcto = true;
					
					correcto = true;

				} else {
					correcto = false;
				}
				break;
			}
			break;
		}

		if (correcto) {

			if (ficheroEntrada) {
				try {
					cargarSudokuFichero(); // cargar por fichero
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				cargarSudokuPantalla(); // cargar sudoku por pantalla
	
			} 
			
			Resolver sudoku = new Resolver(tablasudoku, traza);
			
			if (sudoku.resolver()) {

				sudoku.imprimirSudoku();
			} else {
				System.out.println("No es posible resolverlo");
			}
		} else {
			Ayuda ayuda = new Ayuda(correcto);
			ayuda.imprimirAyuda();
		}

	}

	private static void cargarSudokuFichero() throws FileNotFoundException, IOException {
		// TODO Auto-generated method stub
		
		try {
			BufferedReader buffer = new BufferedReader(new InputStreamReader(new FileInputStream(nombreArchivo), "UTF-8"));
			String linea; 
			int i=0;
			int k=0;
			while((linea=buffer.readLine()) != null) {
				k=0;
				char[] caracteres = linea.toCharArray();
				for (int j = 0; j<caracteres.length; j=j+2) {
					if (caracteres[j] == '-') {
						tablasudoku[i][k] = 0;
					}else {
						tablasudoku[i][k] = Integer.parseInt(linea.substring(j, (j+1)));
					}
					k++;
				}
				i++;
			}
			buffer.close();
		} catch (IOException ex) {
			/* Error en el primer parámetro */
			System.err.println("No se puede abrir el fichero de sudokus para su lectura.");
		}
		

	}

	private static void cargarSudokuPantalla() {
		// TODO Auto-generated method stub
		Scanner scanner = new Scanner(System.in);
		System.out.println("         Sudoku por pantalla:");
		System.out.println("Primero las filas y después columnas si no se sabe ponga 0");
		for (int i=0; i<9;i++) {
			for (int j=0; j<9;j++) {
				System.out.print("Fila: "+(i+1) + " Columna: "+(j+1)+ " : " );
				String entrada = scanner.next();
					tablasudoku[i][j] = Integer.parseInt(entrada);
			}
		}

		scanner.close();
	}

}
