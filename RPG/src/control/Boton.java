package control;

public class Boton {
	private int posX, posY, tamX, tamY;

	private boolean click = false;

	public Boton(int posX, int posY, int tamX, int tamY) {

		this.posX = posX;
		this.posY = posY;
		this.tamX = tamX;
		this.tamY = tamY;

	}

	public boolean clicked(boolean click, int x, int y) {
		if (click && x > posX && x < posX + tamX && y > posY && y < posY + tamY) {
			return true;
		} else {
			return false;
		}

	}

	public void setClick(boolean estado) {
		this.click = estado;
	}

	public boolean getClick() {
		return click;
	}

	public int getPosX() {
		return posX;
	}

	public int getPosY() {
		return posY;
	}

	public int getTamX() {
		return tamX;
	}

	public int getTamY() {
		return tamY;
	}
}
