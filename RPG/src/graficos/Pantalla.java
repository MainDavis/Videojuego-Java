package graficos;

import configuracion.Jugador;
import configuracion.Mapa;
import configuracion.PersonajesAI;

public final class Pantalla {

	private final int ancho;
	private final int alto;

	public final int[] pixeles;

	private Animaciones personajes[] = new Animaciones[10];
	private final int animJugador;

	public Pantalla(final int ancho, final int alto, int animJugador) {
		this.ancho = ancho;
		this.alto = alto;

		pixeles = new int[ancho * alto];

		// Creo el array de personajes con sus animaciones
		personajes[0] = new Animaciones("gunwoman", 22);
		personajes[1] = new Animaciones("king", 8);
		personajes[2] = new Animaciones("knight", 14);
		personajes[3] = new Animaciones("martial", 8);
		personajes[4] = new Animaciones("ninja", 4);
		personajes[5] = new Animaciones("robot", 18);
		personajes[6] = new Animaciones("skeleton_archer", 8);
		personajes[7] = new Animaciones("skeleton_chief", 8);
		personajes[8] = new Animaciones("skeleton_normal", 8);
		personajes[9] = new Animaciones("wizard", 10);

		//
		this.animJugador = animJugador;

	}

	public void limpiar() {
		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = 0;
		}
	}

	public void mostrarMenu() {

		// mostrarFondo(Sprites.fondo2);
		mostrarSprite(0, 0, Sprites.menu_background);
		// UI
		mostrarSprite(533, 334, Sprites.button_start);
		// Robot
		mostrarSpriteAnim(475, 510, 5);

	}

	public void mostrarFin() {
		mostrarSprite(0, 0, Sprites.fin_background);
		mostrarSpriteAnim(360, 518, 0);
	}

	public void mostrarJuego(PersonajesAI AI[], Jugador jugador, Mapa mapa) {
		// Primero imprimo la localización en la que está el jugador
		mostrarSprite(0, 0, mapa.getLocSprite(jugador.getLoc()));
		// Imprimo el personaje del jugador
		mostrarSpriteAnim(587, 288, animJugador);
		// Imprimo el objeto
		if (jugador.getObj() != -1) // Si tengo un objeto
			mostrarSprite(651, 25, mapa.getObjSprite(jugador.getObj()));
		// Imprimo los dos objetivos, la localizacion y el objeto
		mostrarSprite(975, 90, mapa.getObjSprite(jugador.getObjObjetivo())); // Objeto
		mostrarSprite(1060, 90, mapa.getLocPortraitSprite(jugador.getLocObjetivo()));
		// Imprimo los objetos de la sala
		int x = 10;
		// Primera fila

		for (int i = 0; i < 5 && i < mapa.getObjetosLoc(jugador.getLoc()).size(); i++) {
			mostrarSprite(x, 10, mapa.getObjSprite(mapa.getObjetosLoc(jugador.getLoc()).get(i)));
			x += 75;
		}
		// Si es necesario segunda fila
		if (mapa.getObjetosLoc(jugador.getLoc()).size() > 5) {
			x = 10;
			for (int i = 5; i < mapa.getObjetosLoc(jugador.getLoc()).size(); i++) {
				mostrarSprite(x, 85, mapa.getObjSprite(mapa.getObjetosLoc(jugador.getLoc()).get(i)));
				x += 75;
			}
		}

		// Imprimo las localizaciones adyacentes
		x = 250;
		for (int i = 0; i < mapa.getLocalizaciones().size(); i++) {
			if (mapa.getAdyacencias(jugador.getLoc())[i] == 1) {
				mostrarSprite(x, 500, mapa.getLocPortraitSprite(i));
				x += 123;
			}
		}

		// Imprimo los personajes que están en la misma localización que el jugador con
		// sus respectivos objetos
		x = 250;
		for (int i = 0; i < AI.length; i++) {
			if (AI[i].getLocalizacion() == jugador.getLoc()) {
				// Imprimo el portrait del personaje
				mostrarSprite(x, 583, AI[i].getPortrait());
				// Si tiene objeto lo imprimo también
				if (AI[i].getObjeto() != -1)
					mostrarSprite(x, 615, mapa.getObjPortraitSprite(AI[i].getObjeto()));
				// Si un personaje nos está pidiendo un objeto ponemos una mano en el boton de
				// dar para que el jugador lo vea
				if (jugador.getPedido() == i) {
					mostrarSprite(x, 615, Sprites.manoDar);
				}
				x += 123;
			}
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
