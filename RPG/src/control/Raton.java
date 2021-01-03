package control;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Raton extends MouseAdapter {

	public int posicion[] = new int[2];
	public boolean click;

	// Si hay tiempo mirar un temporizador
	public void mousePressed(MouseEvent e) {
		click = true;
		posicion[0] = e.getX();
		posicion[1] = e.getY();

	}

	public void mouseReleased(MouseEvent e) {
		click = false;
	}

	public void actualizarClickBtt(Boton btt) {
		if (click && posicion[0] > btt.getPosX() && posicion[0] < btt.getPosX() + btt.getTamX()
				&& posicion[1] > btt.getPosY() && posicion[1] < btt.getPosY() + btt.getTamY())
			btt.setClick(!btt.getClick());
	}

}
