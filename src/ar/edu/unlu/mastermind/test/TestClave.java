package ar.edu.unlu.mastermind.test;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import ar.edu.unlu.mastermind.juego.Clave;
import ar.edu.unlu.mastermind.juego.FichaCodificadora;

class TestClave {
	Clave miClave;

	@BeforeEach
	void setUp() throws Exception {
		FichaCodificadora[] miCombinacion = { FichaCodificadora.ROJO, FichaCodificadora.BLANCO,
				FichaCodificadora.AZUL, FichaCodificadora.VERDE, FichaCodificadora.NARANJA };
		miClave = new Clave(miCombinacion);
	}

	@Test
	void probarQueLaPrimeraFichaEsRojo() throws Exception {
		assertEquals(FichaCodificadora.ROJO, miClave.getColor(1));
	}

	@Test
	void probarQueLaSegundaFichaEsBlanco() throws Exception {
		assertEquals(FichaCodificadora.BLANCO, miClave.getColor(2));
	}

	@Test
	void probarQuePasarPosicionMayorACincoDaError() {
		assertThrows(Exception.class, () -> miClave.getColor(6));
	}

	@Test
	void probarQueSiLaClaveTieneMasDeCincoElementosNoCargarLaClave() throws Exception {
		FichaCodificadora[] miCombinacion = { FichaCodificadora.ROJO, FichaCodificadora.BLANCO,
				FichaCodificadora.AZUL, FichaCodificadora.VERDE, FichaCodificadora.NARANJA,
				FichaCodificadora.NARANJA };
		miClave = new Clave(miCombinacion);
		assertEquals(null, miClave.getColor(1));
	}

	@Test
	void probarQueSiLaClaveTieneCuatroElementosCargarLaClaveParcialmentePaso1() throws Exception {
		FichaCodificadora[] miCombinacion = { FichaCodificadora.ROJO, FichaCodificadora.BLANCO,
				FichaCodificadora.AZUL, FichaCodificadora.VERDE };
		miClave = new Clave(miCombinacion);
		assertEquals(FichaCodificadora.ROJO, miClave.getColor(1));
	}

	@Test
	void probarQueSiLaClaveTieneCuatroElementosCargarLaClaveParcialmentePaso2() throws Exception {
		FichaCodificadora[] miCombinacion = { FichaCodificadora.ROJO, FichaCodificadora.BLANCO,
				FichaCodificadora.AZUL, FichaCodificadora.VERDE };
		miClave = new Clave(miCombinacion);
		assertEquals(null, miClave.getColor(5));
	}

	@Test
	void cargarManualmenteUnColorEsPosible() throws Exception {
		miClave.setColor(FichaCodificadora.VERDE, 1);
		assertEquals(FichaCodificadora.VERDE, miClave.getColor(1));
	}

	@Test
	void cargarManualmenteFueraDeRangoDaErrorCero() {
		assertThrows(Exception.class, () -> miClave.setColor(FichaCodificadora.VERDE, 0));
	}

	@Test
	void cargarManualmenteFueraDeRangoDaErrorSeis() {
		assertThrows(Exception.class, () -> miClave.setColor(FichaCodificadora.VERDE, 6));
	}

	@Test
	void cargarMenosDeCincoElementosDevulveClaveNoCompleta() {
		FichaCodificadora[] miCombinacion = { FichaCodificadora.ROJO, FichaCodificadora.BLANCO,
				FichaCodificadora.AZUL, FichaCodificadora.VERDE };
		miClave = new Clave(miCombinacion);
		assertFalse(miClave.isCompleta());
	}

	@Test
	void cargarCincoElementosDevuelveClaveCompleta() {
		assertTrue(miClave.isCompleta());
	}

	@Test
	void cargarTresElementosDevuelveClaveNoCompleta() throws Exception {
		miClave = new Clave();
		miClave.setColor(FichaCodificadora.AMARILLO, 1);
		miClave.setColor(FichaCodificadora.AMARILLO, 3);
		miClave.setColor(FichaCodificadora.AMARILLO, 5);
		assertFalse(miClave.isCompleta());
	}
}
