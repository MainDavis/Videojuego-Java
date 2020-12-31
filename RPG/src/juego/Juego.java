package juego;

import java.awt.BorderLayout;
import java.awt.Canvas;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import configuracion.CrearPersonajes;
import configuracion.Leer;
import configuracion.Mapa;
import configuracion.Personajes;
import control.Teclado;
import graficos.Pantalla;

public class Juego extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int ANCHO = 832; // 832
	private static final int ALTO = 640;

	private static volatile boolean enFuncionamiento = false; // "Volatile" es que solo pueda ser usado por un Thread a
																// la vez

	private static final String NOMBRE = "Juego";

	private static int aps = 0;
	private static int fps = 0;
	private static int frame = 0;
	private static int nivel = 0;

	private static JFrame ventana;
	private static Thread thread;
	private static Teclado teclado;
	private static Leer leer;
	private static Mapa mapa;
	private static CrearPersonajes crearPersonajes;
	private static Personajes personajes;
	private static Pantalla pantalla;

	private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
	private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();

	private static final ImageIcon icono = new ImageIcon(Juego.class.getResource("/icono.png"));

	private Juego() {
		setPreferredSize(new Dimension(ANCHO, ALTO));

		pantalla = new Pantalla(ANCHO, ALTO);

		ventana = new JFrame(NOMBRE); // Creo la ventan
		ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Hago que cuando el usuario le de a la X se cierre la
																// App // app
		ventana.setResizable(false); // Hago que el tama�o de la ventana no pueda ser modificado por el usuario
		ventana.setLayout(new BorderLayout()); // Organizaci�n/Dise�o de la ventana
		ventana.add(this, BorderLayout.CENTER); // A�ado a la ventana el canvas y lo pongo en el centro
		ventana.pack(); // Para que todo funcione con la resoluci�n que hemos puesto
		ventana.setLocationRelativeTo(null); // Fijar la ventana en el centro del escritorio
		ventana.setVisible(true); // La hago visible
		ventana.setIconImage(icono.getImage());

		teclado = new Teclado(); // Importo el Teclado
		addKeyListener(teclado); // A�ado un keyListener para que recopile las teclas pulsadas

	}

	public static void main(String[] args) {
		// Leer los ficheros y configuraci�n
		leer = new Leer();
		mapa = new Mapa();

		// Creo los personajes
		Personajes personajesAi[] = new Personajes[leer.getNumPersonajes() - 1]; // leer.getNumPersonajes()-1 porque el
																					// primero es el jugador

		// for (int i = 0; i < leer.getNumPersonajes(); i++) {
		// personajesAi[i] = new Personajes("Hola", 1, 1);
		// }

		for (int i = 0; i < leer.getNumPersonajes() - 1; i++) {
			String nombre = leer.getPersonajes().get(i + 1);
			int loc = leer.getPersonajesLocIni().get(i + 1);
			int objeto = -1;

			for (String element : leer.getObj()) {
				String nombrePersonaje = element.split("\\(")[0];

				if (leer.getPersonajes().contains(nombrePersonaje) && nombre.equals(nombrePersonaje)) {
					objeto = leer.getObj().indexOf(element);
					continue;
				}
			}

			personajesAi[i] = new Personajes(nombre, loc, objeto);

		}

		for (Personajes Ai : personajesAi) {
			System.out.println(Ai.getNombre());
		}

		// Fin de creacion de personajes

		Juego juego = new Juego();
		juego.iniciar();

		nivel = 1;

	}

	private synchronized void iniciar() {
		enFuncionamiento = true;

		thread = new Thread(this, "Graficos");
		thread.start();
	}

	private synchronized void detener() {
		enFuncionamiento = false;

		try {
			thread.join(); // Espera a que termine lo que est� haciendo el Thread y luego lo detiene
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void actualizar() { // Actualizar las variables del juego

		teclado.actualizar();

		if (teclado.arriba) {

		}
		if (teclado.abajo) {

		}
		if (teclado.derecha) {
			nivel = 1;
		}
		if (teclado.izquierda) {
			nivel = 0;
		}

		pantalla.limpiar();

		if (nivel == 0 && frame > 14 || nivel == 1 && frame > 17)
			frame = 0;
		pantalla.mostrarMapa(nivel, 1);
		frame++;
		aps++;
	}

	private void mostrar() { // Metodos para dibujar los gr�ficos

		BufferStrategy estrategia = getBufferStrategy();

		if (estrategia == null) {
			createBufferStrategy(3);
			return;
		}

		System.arraycopy(pantalla.pixeles, 0, pixeles, 0, pixeles.length);

		Graphics g = estrategia.getDrawGraphics();
		g.drawImage(imagen, 0, 0, getWidth(), getHeight(), null);
		g.dispose(); // Destruye la memoria que g estaba usando

		estrategia.show(); // Para que se vea la imagen

		fps++;
	}

	public void run() { // Lo que se ejecutar� en el segundo Therad
		final int NS_POR_SEGUNDO = 1000000000; // Nanosegundos por segundo
		final byte APS_OBJETIVO = 12; // APS Actualizaciones por segundo
		final double NS_POR_ACTUALIZACION = NS_POR_SEGUNDO / APS_OBJETIVO;

		long referenciaActualizacion = System.nanoTime();
		long referenciaContador = System.nanoTime();

		double tiempoTranscurrido;
		double delta = 0;

		requestFocus(); // Pido el foco para que el usuario no tenga que clickar al principio

		while (enFuncionamiento) {
			final long inicioBucle = System.nanoTime();

			tiempoTranscurrido = inicioBucle - referenciaActualizacion;
			referenciaActualizacion = inicioBucle;

			delta += tiempoTranscurrido / NS_POR_ACTUALIZACION;

			while (delta >= 1) {
				actualizar(); // Para actualizar unas 60 veces por segundo
				delta--;
			}

			mostrar();

			if (System.nanoTime() - referenciaContador > NS_POR_SEGUNDO) {
				ventana.setTitle(NOMBRE + " || APS: " + aps + " || FPS: " + fps);
				aps = 0;
				fps = 0;
				referenciaContador = System.nanoTime();
			}
		}
	}
}
