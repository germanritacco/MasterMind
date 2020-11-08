package ar.edu.unlu.mastermind.juego;

import java.util.ArrayList;
import java.util.List;

public class Tablero {
	private List<Clave> intentos;
	private List<FichaResultado[]> resultados;
	private ClaveSecreta claveSecreta;
	private boolean jugable = true;

	public Tablero() {
		intentos = new ArrayList<Clave>();
		resultados = new ArrayList<>();
		this.inicializar();
	}
	
	public Tablero (ClaveSecreta claveSecreta) {
		this();
			this.claveSecreta  = claveSecreta;
	}

	public void inicializar() {
		intentos.clear();
		resultados.clear();
		jugable = true;
		claveSecreta = new ClaveSecreta();
	}

	public Integer getIntentos() {
		return intentos.size();
	}

	public void agregarClave(Clave clave) {
		if (clave.isCompleta() && jugable) {
			intentos.add(clave);
			try {
				FichaResultado[] resultado = claveSecreta.comparar(clave);
				boolean cambiar = true;
				for (FichaResultado r : resultado)
					cambiar &= (r == FichaResultado.BUENA);
				jugable = !cambiar;
				
				resultados.add(resultado);
				resultados.add(claveSecreta.comparar(clave));
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public FichaResultado[] getRespuesta() {
		FichaResultado[] respuesta;
		if (resultados.size() > 0) {
			respuesta = resultados.get(resultados.size() - 1);
		} else {
			respuesta = null;
		}
		return respuesta;
	}

}
