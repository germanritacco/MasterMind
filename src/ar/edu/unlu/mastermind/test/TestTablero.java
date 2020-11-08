package ar.edu.unlu.mastermind.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unlu.mastermind.juego.Clave;
import ar.edu.unlu.mastermind.juego.ClaveSecreta;
import ar.edu.unlu.mastermind.juego.FichaCodificadora;
import ar.edu.unlu.mastermind.juego.FichaResultado;
import ar.edu.unlu.mastermind.juego.Tablero;

class TestTablero {
	Tablero tablero;

	@BeforeEach
	void setUp() throws Exception {
		tablero = new Tablero();
	}

	@Test
	void alInicializarElTableroDevuelveIntentosCero() {
		tablero.inicializar();
		assertEquals(0, tablero.getIntentos());
	}

	@Test
	void alInicializarElTableroDevuelveIntentosCero_paso2() {
		FichaCodificadora[] miCombinacion = { FichaCodificadora.ROJO, FichaCodificadora.BLANCO,
				FichaCodificadora.AZUL, FichaCodificadora.VERDE, FichaCodificadora.NARANJA };
		tablero.agregarClave(new Clave(miCombinacion));
		tablero.inicializar();
		assertEquals(0, tablero.getIntentos());
	}

	@Test
	void cargarClaveNuevaDevuelveIntentosUno() {
		FichaCodificadora[] miCombinacion = { FichaCodificadora.ROJO, FichaCodificadora.BLANCO,
				FichaCodificadora.AZUL, FichaCodificadora.VERDE, FichaCodificadora.NARANJA };
		tablero.agregarClave(new Clave(miCombinacion));
		assertEquals(1, tablero.getIntentos());
	}

	// 1 - La clave agregada debe estar completa.

	@Test
	void cargarClaveIncompletaNoEstaPermitido() {
		FichaCodificadora[] miCombinacion = { FichaCodificadora.ROJO, FichaCodificadora.BLANCO,
				FichaCodificadora.AZUL, FichaCodificadora.VERDE };
		tablero.agregarClave(new Clave(miCombinacion));
		assertEquals(0, tablero.getIntentos());
	}

	// 2 - cuando se agrega una clave automáticamente se obtiene el resultado y se
	// puede consultar al tablero.

	@Test
	void cargarClaveNuevaGeneraResultados() {
		FichaCodificadora[] miCombinacion = { FichaCodificadora.ROJO, FichaCodificadora.BLANCO,
				FichaCodificadora.AZUL, FichaCodificadora.VERDE, FichaCodificadora.NARANJA };
		tablero.agregarClave(new Clave(miCombinacion));
		FichaResultado[] resultado = tablero.getRespuesta();
		assertEquals(5, resultado.length);
		for (FichaResultado f : resultado) {
			assertNotEquals(null, f);
		}
	}

	// 3 - Preguntar por resultado cuando nunca se cargo una clave devuelve null

	@Test
	void preguntarPorResultadosCuandoNoHayClaveDevuelveNulo() {
		FichaResultado[] resultado = tablero.getRespuesta();
		assertEquals(null, resultado);
	}

	// 4 - No se puede agregar claves si la clave secreta se ha adivinado

	@Test
	void noSePuedeSeguirAgregandoClavesCuandoSeAdivinoLaClaveSecreta() throws Exception {
		FichaCodificadora[] miCombinacion = { FichaCodificadora.ROJO, FichaCodificadora.BLANCO,
				FichaCodificadora.AZUL, FichaCodificadora.VERDE, FichaCodificadora.NARANJA };
		ClaveSecreta claveSecreta = new ClaveSecreta();
		int i = 1;
		for (FichaCodificadora c : miCombinacion)
			claveSecreta.setColor(c, i++);
		tablero = new Tablero(claveSecreta);
		tablero.agregarClave(new Clave(miCombinacion));
		int intentos = tablero.getIntentos();
		tablero.agregarClave(new Clave(miCombinacion));
		assertEquals(intentos, tablero.getIntentos());
	}

}
