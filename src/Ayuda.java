
public class Ayuda {
	boolean correcto;

	public Ayuda(boolean correcto) {
		super();
		this.correcto = correcto;
	}

	public void imprimirAyuda() {
		System.out.println("SINTAXIS: sudoku [-t][-h] [fichero entrada]");
		System.out.println("         -t       Traza la llamada recursiva y sus parámetros");
		System.out.println("         -h       Muestra esta ayuda");
		System.out.println("[fichero entrada] Tabla inicial del soduku");
	}
}
