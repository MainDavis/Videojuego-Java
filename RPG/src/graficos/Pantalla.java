package graficos;

public final class Pantalla {

	private final int ancho;
	private final int alto;

	public final int[] pixeles;

	private Animaciones personajes[] = new Animaciones[10];

	public Pantalla(final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;

		pixeles = new int[ancho * alto];

		// Creo el array de personajes con sus animaciones
		personajes[0] = new Animaciones("gunwoman", 22);
		personajes[1] = new Animaciones("king", 6);
		personajes[2] = new Animaciones("knight", 14);
		personajes[3] = new Animaciones("martial", 8);
		personajes[4] = new Animaciones("ninja", 4);
		personajes[5] = new Animaciones("robot", 18);
		personajes[6] = new Animaciones("skeletonArcher", 8);
		personajes[7] = new Animaciones("skeletonChief", 8);
		personajes[8] = new Animaciones("skeletonNormal", 8);
		personajes[9] = new Animaciones("wizard", 10);

	}

	public void limpiar() {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = 0;
		}
	}

	public void mostrarMapa(int mapa, int frame) {
		switch (mapa) {
		case 0: // Menu
			// Primero pinto el fondo
			for (int i = 0; i < pixeles.length; i++) {
				pixeles[i] = Sprites.castillo.pixeles[i];
			}
			// Objetos

			break;
		case 1:
			// mostrarFondo(Sprites.fondo2);
			mostrarSprite(0, 0, Sprites.menu_background);
			// UI
			mostrarSprite(266, 270, Sprites.button_start);
			// Animaciones
			mostrarSpriteAnim(-30, 0, 4);
			mostrarSpriteAnim(-20, 225, 1);
			mostrarSpriteAnim(-20, 450, 3);
			mostrarSpriteAnim(625, 0, 6);
			mostrarSpriteAnim(625, 225, 7);
			mostrarSpriteAnim(625, 450, 8);

			mostrarSpriteAnim(225, 0, 5);
			mostrarSpriteAnim(425, 0, 0);
			mostrarSpriteAnim(225, 450, 2);
			mostrarSpriteAnim(425, 450, 9);

			break;
		}

	}

	public void mostrarSpriteAnim(int posX, int posY, int personaje) {

		for (int y = 0; y < 192; y++) {
			for (int x = 0; x < 192; x++) {
				if (personajes[personaje].getSprite().pixeles[x + (y * 192)] != 0) {
					pixeles[x + (y * ancho) + (posY * ancho) + posX] = personajes[personaje].getSprite().pixeles[x
							+ (y * 192)];
				}
			}
		}
		personajes[personaje].upFrame();
	}

	public void mostrarSprite(int posX, int posY, Sprites sprite) {
		for (int y = 0; y < sprite.getAlto(); y++) {
			for (int x = 0; x < sprite.getAncho(); x++) {
				if (sprite.pixeles[x + (y * sprite.getAncho())] != 0) {
					pixeles[x + (y * ancho) + (posY * ancho) + posX] = sprite.pixeles[x + (y * sprite.getAncho())];
				}
			}
		}
	}

	public void mostrarFondo(Sprites sprite) {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = sprite.pixeles[i];
		}
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

}
