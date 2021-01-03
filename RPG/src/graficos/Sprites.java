package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Sprites {
	private final int ancho;
	private final int alto;
	public final int[] pixeles;

	// Fondos
	public static Sprites castillo = new Sprites("/sprites/fondos/muerte.png", 1366, 768);
	public static Sprites menu_background = new Sprites("/sprites/fondos/menu_background.png", 1366, 768);
	// UI
	public static Sprites button_start = new Sprites("/sprites/ui/new_game_button.png", 300, 100);

	// Fin de la colecci�n

	public Sprites(final String ruta, final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;

		pixeles = new int[ancho * alto];

		BufferedImage imagen;

		try {
			imagen = ImageIO.read(Sprites.class.getResourceAsStream(ruta));
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getAncho() {
		return ancho;
	}

	public int getAlto() {
		return alto;
	}

}
