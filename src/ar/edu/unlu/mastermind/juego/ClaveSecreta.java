package ar.edu.unlu.mastermind.juego;

public class ClaveSecreta extends Clave {

	public ClaveSecreta() {
		super();
		try {
			for (int i = 1; i <= 5; i++)
				this.setColor(FichaCodificadora.ROJO, i);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public FichaResultado[] comparar(Clave claveAProbar) throws Exception {
		int[] frecuencias = calcularFrecuencias();
		FichaResultado[] respuesta = { FichaResultado.MALA, FichaResultado.MALA,
				FichaResultado.MALA, FichaResultado.MALA, FichaResultado.MALA };
		// Paso 1 - Obtener las BUENAS
		for (int i = 1; i <= 5; i++) {
			try {
				if (this.getColor(i) == claveAProbar.getColor(i)) {
					respuesta[i - 1] = FichaResultado.BUENA;
					frecuencias[this.getColor(i).ordinal()]--;
				}
			} catch (Exception e) {
				throw new IllegalArgumentException("La clave probada no esta completa..");
			}
		}
		// Paso 2 - Obtener las REGULARES
		for (int i = 1; i <= 5; i++) {
			if (respuesta[i - 1].equals(FichaResultado.MALA)) {
				try {
					if (frecuencias[claveAProbar.getColor(i).ordinal()] > 0) {
						respuesta[i - 1] = FichaResultado.REGULAR;
						frecuencias[this.getColor(i).ordinal()]--;
					}
				} catch (Exception e) {
					throw new IllegalArgumentException("La clave probada no esta completa..");
				}
			}
		}
		return respuesta;
	}

	private int[] calcularFrecuencias() {
		int[] resultado = new int[FichaCodificadora.values().length];
		for (int f : resultado)
			f = 0;
		for (int i = 1; i <= 5; i++) {
			try {
				int pos = this.getColor(i).ordinal();
				resultado[pos]++;
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return resultado;
	}

}
