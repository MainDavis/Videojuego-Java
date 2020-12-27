package mapa.tile;

import graficos.Sprite;

public class Tile {
	public int x;
	public int y;

	public Sprite sprite;

	// Tileset
	public static final Tile VACIO = new TileVacio(Sprite.VACIO);
	public static final Tile GRASS31 = new TileGrass31(Sprite.GRASS31);

	// Fin de tileset

	public Tile(Sprite sprite) {
		this.sprite = sprite;
	}

//	public void mostrar(int x, int y, Pantalla pantalla) {
//		pantalla.mostrarTile(x << 6, y << 6, this);
//	}

	public boolean solido() {
		return false;
	}
}
