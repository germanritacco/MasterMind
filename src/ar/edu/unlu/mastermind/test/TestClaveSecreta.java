package ar.edu.unlu.mastermind.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unlu.mastermind.juego.Clave;
import ar.edu.unlu.mastermind.juego.ClaveSecreta;
import ar.edu.unlu.mastermind.juego.FichaCodificadora;
import ar.edu.unlu.mastermind.juego.FichaResultado;

class TestClaveSecreta {
	ClaveSecreta miClave;

	@BeforeEach
	void setUp() throws Exception {
		miClave = new ClaveSecreta();
	}

	@Test
	void probarQueAlCrearInstaciaHayCombinacion() throws Exception {
		assertTrue(miClave.isCompleta());
	}

	@Test
	void probarEvaluacionDeClave_Paso1() throws Exception {
		Clave claveAProbar = new Clave();
		claveAProbar.setColor(FichaCodificadora.AMARILLO, 1);
		claveAProbar.setColor(FichaCodificadora.AMARILLO, 2);
		claveAProbar.setColor(FichaCodificadora.AMARILLO, 3);
		claveAProbar.setColor(FichaCodificadora.AZUL, 4);
		claveAProbar.setColor(FichaCodificadora.ROJO, 5);
		miClave.setColor(FichaCodificadora.ROJO, 1);
		miClave.setColor(FichaCodificadora.BLANCO, 2);
		miClave.setColor(FichaCodificadora.ROJO, 3);
		miClave.setColor(FichaCodificadora.AZUL, 4);
		miClave.setColor(FichaCodificadora.VERDE, 5);
		FichaResultado[] miResultado = miClave.comparar(claveAProbar);

		assertEquals(FichaResultado.MALA, miResultado[0]);
		assertEquals(FichaResultado.MALA, miResultado[1]);
		assertEquals(FichaResultado.MALA, miResultado[2]);
		assertEquals(FichaResultado.BUENA, miResultado[3]);
		assertEquals(FichaResultado.REGULAR, miResultado[4]);
	}

	@Test
	void probarEvaluacionDeClave_Paso2() throws Exception {
		Clave claveAProbar = new Clave();
		claveAProbar.setColor(FichaCodificadora.ROJO, 1);
		claveAProbar.setColor(FichaCodificadora.AZUL, 2);
		claveAProbar.setColor(FichaCodificadora.ROJO, 3);
		claveAProbar.setColor(FichaCodificadora.ROJO, 4);
		claveAProbar.setColor(FichaCodificadora.VIOLETA, 5);
		miClave.setColor(FichaCodificadora.ROJO, 1);
		miClave.setColor(FichaCodificadora.BLANCO, 2);
		miClave.setColor(FichaCodificadora.ROJO, 3);
		miClave.setColor(FichaCodificadora.AZUL, 4);
		miClave.setColor(FichaCodificadora.VERDE, 5);
		FichaResultado[] miResultado = miClave.comparar(claveAProbar);

		assertEquals(FichaResultado.BUENA, miResultado[0]);
		assertEquals(FichaResultado.REGULAR, miResultado[1]);
		assertEquals(FichaResultado.BUENA, miResultado[2]);
		assertEquals(FichaResultado.MALA, miResultado[3]);
		assertEquals(FichaResultado.MALA, miResultado[4]);
	}

}
