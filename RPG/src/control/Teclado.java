package control;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Teclado implements KeyListener {

	private final static int numTeclas = 120;
	private boolean[] teclas = new boolean[numTeclas];

	public boolean h;
	public boolean escape;

	public void actualizar() {

		h = teclas[KeyEvent.VK_H];
		escape = teclas[KeyEvent.VK_ESCAPE];

	}

	public void keyPressed(KeyEvent arg0) {
		teclas[arg0.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent arg0) {
		teclas[arg0.getKeyCode()] = false;
	}

	public void keyTyped(KeyEvent arg0) {

	}

}
