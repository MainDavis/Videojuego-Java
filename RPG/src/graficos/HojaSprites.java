package graficos;

import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;

public class HojaSprites {
	private final int ancho;
	private final int alto;
	public final int[] pixeles;

	// Colección de hojas de sprites
	public static HojaSprites grassland = new HojaSprites("/texturas/suelo.png", 1024, 1024);
	public static HojaSprites fondo1 = new HojaSprites("/texturas/fondo1.png", 832, 640);
	public static HojaSprites fondo2 = new HojaSprites("/texturas/fondo2.png", 832, 640);
	// Animaciones
	// Fuego
	public static HojaSprites fuego0 = new HojaSprites("/texturas/anim/fuego0.png", 64, 64);
	public static HojaSprites fuego1 = new HojaSprites("/texturas/anim/fuego1.png", 64, 64);
	public static HojaSprites fuego2 = new HojaSprites("/texturas/anim/fuego2.png", 64, 64);
	public static HojaSprites fuego3 = new HojaSprites("/texturas/anim/fuego3.png", 64, 64);
	public static HojaSprites fuego4 = new HojaSprites("/texturas/anim/fuego4.png", 64, 64);
	public static HojaSprites fuego5 = new HojaSprites("/texturas/anim/fuego5.png", 64, 64);
	public static HojaSprites fuego6 = new HojaSprites("/texturas/anim/fuego6.png", 64, 64);
	public static HojaSprites fuego7 = new HojaSprites("/texturas/anim/fuego7.png", 64, 64);
	public static HojaSprites fuego8 = new HojaSprites("/texturas/anim/fuego8.png", 64, 64);
	public static HojaSprites fuego9 = new HojaSprites("/texturas/anim/fuego9.png", 64, 64);
	public static HojaSprites fuego10 = new HojaSprites("/texturas/anim/fuego10.png", 64, 64);
	public static HojaSprites fuego11 = new HojaSprites("/texturas/anim/fuego11.png", 64, 64);
	public static HojaSprites fuego12 = new HojaSprites("/texturas/anim/fuego12.png", 64, 64);
	public static HojaSprites fuego13 = new HojaSprites("/texturas/anim/fuego13.png", 64, 64);
	public static HojaSprites fuego14 = new HojaSprites("/texturas/anim/fuego14.png", 64, 64);
	// Robot
	public static HojaSprites rb0 = new HojaSprites("/texturas/anim/rb0.png", 192, 192);
	public static HojaSprites rb1 = new HojaSprites("/texturas/anim/rb1.png", 192, 192);
	public static HojaSprites rb2 = new HojaSprites("/texturas/anim/rb2.png", 192, 192);
	public static HojaSprites rb3 = new HojaSprites("/texturas/anim/rb3.png", 192, 192);
	public static HojaSprites rb4 = new HojaSprites("/texturas/anim/rb4.png", 192, 192);
	public static HojaSprites rb5 = new HojaSprites("/texturas/anim/rb5.png", 192, 192);
	public static HojaSprites rb6 = new HojaSprites("/texturas/anim/rb6.png", 192, 192);
	public static HojaSprites rb7 = new HojaSprites("/texturas/anim/rb7.png", 192, 192);
	public static HojaSprites rb8 = new HojaSprites("/texturas/anim/rb8.png", 192, 192);
	public static HojaSprites rb9 = new HojaSprites("/texturas/anim/rb9.png", 192, 192);
	public static HojaSprites rb10 = new HojaSprites("/texturas/anim/rb10.png", 192, 192);
	public static HojaSprites rb11 = new HojaSprites("/texturas/anim/rb11.png", 192, 192);
	public static HojaSprites rb12 = new HojaSprites("/texturas/anim/rb12.png", 192, 192);
	public static HojaSprites rb13 = new HojaSprites("/texturas/anim/rb13.png", 192, 192);
	public static HojaSprites rb14 = new HojaSprites("/texturas/anim/rb14.png", 192, 192);
	public static HojaSprites rb15 = new HojaSprites("/texturas/anim/rb15.png", 192, 192);
	public static HojaSprites rb16 = new HojaSprites("/texturas/anim/rb16.png", 192, 192);
	public static HojaSprites rb17 = new HojaSprites("/texturas/anim/rb17.png", 192, 192);
	// Fin de la colección

	public HojaSprites(final String ruta, final int ancho, final int alto) {
		this.ancho = ancho;
		this.alto = alto;

		pixeles = new int[ancho * alto];

		BufferedImage imagen;

		try {
			imagen = ImageIO.read(HojaSprites.class.getResourceAsStream(ruta));
			imagen.getRGB(0, 0, ancho, alto, pixeles, 0, ancho);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	public int getAncho() {
		return ancho;
	}

}
