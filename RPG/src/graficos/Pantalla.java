package graficos;

public final class Pantalla {

	private static final int NULL = 0;
	private final int ancho;
	private final int alto;

	public final int[] pixeles;

	public Pantalla(final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;

		pixeles = new int[ancho * alto];
	}

	public void limpiar() {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = 0;
		}
	}

	public void mostrarMapa(int mapa, int frame) {
		switch (mapa) {
		case 0:
			// Primero pinto el fondo
			for (int i = 0; i < pixeles.length; i++) {
				pixeles[i] = HojaSprites.fondo1.pixeles[i];
			}
			// Objetos
			// fuego
			mostrarSpriteFuego(397, 455, frame);
			break;
		case 1:
			for (int i = 0; i < pixeles.length; i++) {
				pixeles[i] = HojaSprites.fondo2.pixeles[i];
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
					if (HojaSprites.rb0.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb0.pixeles[x + (y * 192)];
					}
					break;
				case 1:
					if (HojaSprites.rb1.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb1.pixeles[x + (y * 192)];
					}
					break;
				case 2:
					if (HojaSprites.rb2.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb2.pixeles[x + (y * 192)];
					}
					break;
				case 3:
					if (HojaSprites.rb3.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb3.pixeles[x + (y * 192)];
					}
					break;
				case 4:
					if (HojaSprites.rb4.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb4.pixeles[x + (y * 192)];
					}
					break;
				case 5:
					if (HojaSprites.rb5.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb5.pixeles[x + (y * 192)];
					}
					break;
				case 6:
					if (HojaSprites.rb6.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb6.pixeles[x + (y * 192)];
					}
					break;
				case 7:
					if (HojaSprites.rb7.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb7.pixeles[x + (y * 192)];
					}
					break;
				case 8:
					if (HojaSprites.rb8.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb8.pixeles[x + (y * 192)];
					}
					break;
				case 9:
					if (HojaSprites.rb9.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb9.pixeles[x + (y * 192)];
					}
					break;
				case 10:
					if (HojaSprites.rb10.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb10.pixeles[x + (y * 192)];
					}
					break;
				case 11:
					if (HojaSprites.rb11.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb11.pixeles[x + (y * 192)];
					}
					break;
				case 12:
					if (HojaSprites.rb12.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb12.pixeles[x + (y * 192)];
					}
					break;
				case 13:
					if (HojaSprites.rb13.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb13.pixeles[x + (y * 192)];
					}
					break;
				case 14:
					if (HojaSprites.rb14.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb14.pixeles[x + (y * 192)];
					}
					break;
				case 15:
					if (HojaSprites.rb15.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb15.pixeles[x + (y * 192)];
					}
					break;
				case 16:
					if (HojaSprites.rb16.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb16.pixeles[x + (y * 192)];
					}
					break;
				case 17:
					if (HojaSprites.rb17.pixeles[x + (y * 192)] != 16777215) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.rb17.pixeles[x + (y * 192)];
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
					if (HojaSprites.fuego0.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.fuego0.pixeles[x + (y * 64)];
					}
					break;
				case 1:
					if (HojaSprites.fuego1.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.fuego1.pixeles[x + (y * 64)];
					}
					break;
				case 2:
					if (HojaSprites.fuego2.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.fuego2.pixeles[x + (y * 64)];
					}
					break;
				case 3:
					if (HojaSprites.fuego3.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.fuego3.pixeles[x + (y * 64)];
					}
					break;
				case 4:
					if (HojaSprites.fuego4.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.fuego4.pixeles[x + (y * 64)];
					}
					break;
				case 5:
					if (HojaSprites.fuego5.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.fuego5.pixeles[x + (y * 64)];
					}
					break;
				case 6:
					if (HojaSprites.fuego6.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.fuego6.pixeles[x + (y * 64)];
					}
					break;
				case 7:
					if (HojaSprites.fuego7.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.fuego7.pixeles[x + (y * 64)];
					}
					break;
				case 8:
					if (HojaSprites.fuego8.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.fuego8.pixeles[x + (y * 64)];
					}
					break;
				case 9:
					if (HojaSprites.fuego9.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.fuego9.pixeles[x + (y * 64)];
					}
					break;
				case 10:
					if (HojaSprites.fuego10.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.fuego10.pixeles[x + (y * 64)];
					}
					break;
				case 11:
					if (HojaSprites.fuego11.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.fuego11.pixeles[x + (y * 64)];
					}
					break;
				case 12:
					if (HojaSprites.fuego12.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.fuego12.pixeles[x + (y * 64)];
					}
					break;
				case 13:
					if (HojaSprites.fuego13.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.fuego13.pixeles[x + (y * 64)];
					}
					break;
				case 14:
					if (HojaSprites.fuego14.pixeles[x + (y * 64)] != -1) {
						pixeles[x + (y * ancho) + (posY * ancho) + posX] = HojaSprites.fuego14.pixeles[x + (y * 64)];
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
