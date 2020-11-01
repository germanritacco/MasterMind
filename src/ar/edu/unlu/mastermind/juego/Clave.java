package ar.edu.unlu.mastermind.juego;

public class Clave {
	private FichaCodificadora[] miClave = new FichaCodificadora[5];

	public Clave(FichaCodificadora[] miCombinacion) {
		if (miCombinacion.length <= 5) {
			int i = 0;
			for (FichaCodificadora f : miCombinacion)
				miClave[i++] = f;
		}
	}

	public Clave() {
	}

	public FichaCodificadora getColor(int posicion) throws Exception {
		if (posicion >= 1 && posicion <= miClave.length)
			return miClave[posicion - 1];
		else
			throw new Exception("Posicion fuera de rango...");
	}

	public void setColor(FichaCodificadora ficha, int posicion) throws Exception {
		if (posicion >= 1 && posicion <= miClave.length)
			miClave[posicion - 1] = ficha;
		else
			throw new Exception("Posicion fuera de rango...");
	}

	public Boolean isCompleta() {
		Boolean miRespuesta = true;
		for (FichaCodificadora f : miClave) {
			miRespuesta &= (f != null);
		}
		return miRespuesta;
	}
}
