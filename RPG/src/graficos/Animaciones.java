package graficos;

public class Animaciones {
	public int frame = 0;
	private final String name;
	private final int numSprites;
	private Sprites[] sprites;

	public Animaciones(final String name, final int numSprites) {
		this.name = name;
		this.numSprites = numSprites;
		final String ruta = "/sprites/personajes/" + name;

		sprites = new Sprites[numSprites];
		for (int i = 0; i < sprites.length; i++) {
			sprites[i] = new Sprites(ruta + "/idle" + (i + 1) + ".png", 192, 192); // Todos los personajes tienen
																					// 192x192
																					// de tamaño
		}

		if (frame == numSprites) {
			frame = 0;
		}
	}

	public int getFrame() {
		return frame;
	}

	public void upFrame() {
		this.frame++;
	}

	public String getName() {
		return name;
	}

	public int getNumSprites() {
		return numSprites;
	}

	public Sprites getSprite(int i) {
		return sprites[i];
	}
}
