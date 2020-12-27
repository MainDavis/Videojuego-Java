package mapa;

import java.util.Random;

public class MapaGenerado extends Mapa {

	private static final Random rand = new Random();

	public MapaGenerado(int ancho, int alto) {
		super(ancho, alto);
	}

	protected void generarMapa() {
		for (int y = 0; y < alto; y++) {
			for (int x = 0; x < ancho; x++) {
				tiles[x + y * ancho] = rand.nextInt(3);
			}
		}
	}

}
