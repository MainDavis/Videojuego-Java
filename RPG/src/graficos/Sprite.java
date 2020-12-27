package graficos;

public final class Sprite {
	private final int lado;

	private int x;
	private int y;

	public int[] pixeles;
	private final HojaSprites hoja;

	// Colección de sprites
	public static final Sprite VACIO = new Sprite(64, 0);
	public static final Sprite GRASS31 = new Sprite(64, 3, 1, HojaSprites.grassland);
	// Fin de la colección

	public Sprite(final int lado, final int columna, final int fila, final HojaSprites hoja) {
		this.lado = lado;

		pixeles = new int[lado * lado];

		this.x = columna * lado;
		this.y = fila * lado;
		this.hoja = hoja;

		for (int y = 0; y < lado; y++) {
			for (int x = 0; x < lado; x++) {
				pixeles[x + y * lado] = hoja.pixeles[(x + this.x) + (y + this.y) * hoja.getAncho()];
			}
		}
	}

	public Sprite(final int lado, final int color) {
		this.lado = lado;
		this.hoja = null;
		pixeles = new int[lado * lado];

		for (int i = 0; i < pixeles.length; i++) {
			pixeles[i] = color;
		}
	}

	public int getLado() {
		return lado;
	}

}
