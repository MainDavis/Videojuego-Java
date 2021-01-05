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

import configuracion.Jugador;
import configuracion.Leer;
import configuracion.Mapa;
import configuracion.PersonajesAI;
import control.Boton;
import control.Raton;
import control.Teclado;
import graficos.Pantalla;

public class Juego extends Canvas implements Runnable {

	private static final long serialVersionUID = 1L;

	private static final int ANCHO = 1366; // 832
	private static final int ALTO = 768;

	private static volatile boolean enFuncionamiento = false; // "Volatile" es que solo pueda ser usado por un Thread a
																// la vez

	private static final String NOMBRE = "Juego";

	private static int aps = 0;
	private static int fps = 0;
	private static int nivel = 0;

	private static JFrame ventana;
	private static Thread thread;
	private static Teclado teclado;
	private static Raton raton;
	private static Leer leer = new Leer();
	private static Mapa mapa = new Mapa();
	private static PersonajesAI personajesAI[] = new PersonajesAI[leer.getNumPersonajes() - 1];
	private static Jugador jugador;
	private static Pantalla pantalla;

	// Creo los botones
	private static Boton btt_newGame = new Boton(533, 334, 300, 100);

	private static BufferedImage imagen = new BufferedImage(ANCHO, ALTO, BufferedImage.TYPE_INT_RGB);
	private static int[] pixeles = ((DataBufferInt) imagen.getRaster().getDataBuffer()).getData();

	private static final ImageIcon icono = new ImageIcon(Juego.class.getResource("/icono.png"));

	private Juego() {

		// Hago todo lo que tenga que ver con la pa
		setPreferredSize(new Dimension(ANCHO, ALTO));

		pantalla = new Pantalla(ANCHO, ALTO, jugador.getNumAnimacion());

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
		raton = new Raton(); // Importo el raton
		addKeyListener(teclado); // A�ado un keyListener para que recopile las teclas pulsadas

	}

	public static void main(String[] args) {

		// Creo los personajes

		for (int i = 1; i < leer.getNumPersonajes(); i++) { // Empiezo en 1 porque el primero es el jugador
			personajesAI[i - 1] = new PersonajesAI(leer.getPersonajes().get(i),
					leer.getPersonajeObjetoInicial(leer.getPersonajes().get(i)), leer.getPersonajeLocIniINT(i),
					leer.getObjObjetivo(i), leer.getLocObjetivo(i));
		}

		System.out.println();

		// Fin de creacion de personajes
		// Creo el personaje del Jugador
		jugador = new Jugador(leer.getPersonajes().get(0), leer.getPersonajeObjetoInicial(leer.getPersonajes().get(0)),
				leer.getPersonajeLocIniINT(0), leer.getObjObjetivo(0), leer.getLocObjetivo(0));

		Juego juego = new Juego();

		juego.iniciar(); // Para los graficos y los controles

		do {
			if (nivel == 1) {

			}
		} while (true);

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

		pantalla.limpiar(); // Limpio la pantalla

		teclado.actualizar();
		addMouseListener(raton);

		if (teclado.h) {
			System.out.print("AYUDA");
		}
		if (teclado.escape) {
			System.out.print("Saliendo");
			System.exit(-1);
		}

		switch (nivel) {
		case 0: // Menu inicial
			pantalla.mostrarMenu();
			raton.actualizarClickBtt(btt_newGame);
			if (btt_newGame.getClick()) {
				nivel = 1;
				System.out.print("CLICK");
			}
			break;
		case 1: // Juego
			pantalla.mostrarJuego(personajesAI, jugador, mapa);
			break;
		}

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
