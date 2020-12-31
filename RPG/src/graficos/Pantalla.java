package graficos;

public final class Pantalla {

	private static final int NULL = 0;
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
		personajes[0] = new Animaciones("knight", 14);
		personajes[1] = new Animaciones("martial", 8);
		personajes[0] = new Animaciones("ninja", 4);
		personajes[1] = new Animaciones("king", 6);
		personajes[0] = new Animaciones("gunwoman", 22);
		personajes[1] = new Animaciones("king", 6);
		personajes[0] = new Animaciones("gunwoman", 22);
		personajes[1] = new Animaciones("king", 6);
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
				pixeles[i] = Sprites.fondo1.pixeles[i];
			}
			// Objetos
			// fuego
			mostrarSpriteFuego(397, 455, frame);
			break;
		case 1:
			for (int i = 0; i < pixeles.length; i++) {
				pixeles[i] = Sprites.fondo2.pixeles[i];
			}
			// Objetos
			// Robotito de lucha supremo
			mostrarSpriteRobot(245, 400, frame);
			break;
		}

	}

	public void mostrarSpriteRobot(int posX, int posY, int frame) {
		for (int y = 0; y < 192; y++) {
			for (int x = 0; x < 192; x++) {
				switch (frame) {
				case 0:
					if (Sprites.rb0.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb0.pixeles[x + (y * 192)];
					}
					break;
				case 1:
					if (Sprites.rb1.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb1.pixeles[x + (y * 192)];
					}
					break;
				case 2:
					if (Sprites.rb2.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb2.pixeles[x + (y * 192)];
					}
					break;
				case 3:
					if (Sprites.rb3.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb3.pixeles[x + (y * 192)];
					}
					break;
				case 4:
					if (Sprites.rb4.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb4.pixeles[x + (y * 192)];
					}
					break;
				case 5:
					if (Sprites.rb5.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb5.pixeles[x + (y * 192)];
					}
					break;
				case 6:
					if (Sprites.rb6.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb6.pixeles[x + (y * 192)];
					}
					break;
				case 7:
					if (Sprites.rb7.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb7.pixeles[x + (y * 192)];
					}
					break;
				case 8:
					if (Sprites.rb8.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb8.pixeles[x + (y * 192)];
					}
					break;
				case 9:
					if (Sprites.rb9.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb9.pixeles[x + (y * 192)];
					}
					break;
				case 10:
					if (Sprites.rb10.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb10.pixeles[x + (y * 192)];
					}
					break;
				case 11:
					if (Sprites.rb11.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb11.pixeles[x + (y * 192)];
					}
					break;
				case 12:
					if (Sprites.rb12.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb12.pixeles[x + (y * 192)];
					}
					break;
				case 13:
					if (Sprites.rb13.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb13.pixeles[x + (y * 192)];
					}
					break;
				case 14:
					if (Sprites.rb14.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb14.pixeles[x + (y * 192)];
					}
					break;
				case 15:
					if (Sprites.rb15.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb15.pixeles[x + (y * 192)];
					}
					break;
				case 16:
					if (Sprites.rb16.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb16.pixeles[x + (y * 192)];
					}
					break;
				case 17:
					if (Sprites.rb17.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.rb17.pixeles[x + (y * 192)];
					}
					break;
				}
			}
		}
	}

	public void mostrarSpriteFuego(int posX, int posY, int frame) {
		for (int y = 0; y < 64; y++) {
			for (int x = 0; x < 64; x++) {
				switch (frame) {
				case 0:
					if (Sprites.fuego0.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.fuego0.pixeles[x + (y * 64)];
					}
					break;
				case 1:
					if (Sprites.fuego1.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.fuego1.pixeles[x + (y * 64)];
					}
					break;
				case 2:
					if (Sprites.fuego2.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.fuego2.pixeles[x + (y * 64)];
					}
					break;
				case 3:
					if (Sprites.fuego3.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.fuego3.pixeles[x + (y * 64)];
					}
					break;
				case 4:
					if (Sprites.fuego4.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.fuego4.pixeles[x + (y * 64)];
					}
					break;
				case 5:
					if (Sprites.fuego5.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.fuego5.pixeles[x + (y * 64)];
					}
					break;
				case 6:
					if (Sprites.fuego6.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.fuego6.pixeles[x + (y * 64)];
					}
					break;
				case 7:
					if (Sprites.fuego7.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.fuego7.pixeles[x + (y * 64)];
					}
					break;
				case 8:
					if (Sprites.fuego8.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.fuego8.pixeles[x + (y * 64)];
					}
					break;
				case 9:
					if (Sprites.fuego9.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.fuego9.pixeles[x + (y * 64)];
					}
					break;
				case 10:
					if (Sprites.fuego10.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.fuego10.pixeles[x + (y * 64)];
					}
					break;
				case 11:
					if (Sprites.fuego11.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.fuego11.pixeles[x + (y * 64)];
					}
					break;
				case 12:
					if (Sprites.fuego12.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.fuego12.pixeles[x + (y * 64)];
					}
					break;
				case 13:
					if (Sprites.fuego13.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.fuego13.pixeles[x + (y * 64)];
					}
					break;
				case 14:
					if (Sprites.fuego14.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = Sprites.fuego14.pixeles[x + (y * 64)];
					}
					break;
				}
			}
		}
	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

}
