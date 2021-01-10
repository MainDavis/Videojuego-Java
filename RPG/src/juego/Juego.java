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
import graficos.Sprites;

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
	private static Boton btt_personajes[] = new Boton[leer.getNumPersonajes() - 1];
	private static Boton btt_objetos[] = new Boton[leer.getNumObj()];
	private static Boton btt_localizaciones[] = new Boton[leer.getNumLoc()];
	private static Boton btt_acciones[] = new Boton[6];

	// Preparo el buffer de imagenes
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
		// new DFS(mapa.getMatriz());
		System.exit(-1);
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

		// Pongo a los botones sus valores
		// Botones de selección de personajes
		int x = 250;
		for (int i = 0; i < btt_personajes.length; i++) {
			btt_personajes[i] = new Boton(x, 583, 114, 64);
			x += 123;
		}
		// Botones de selección de localizaciones
		x = 250;
		for (int i = 0; i < btt_localizaciones.length; i++) {
			btt_localizaciones[i] = new Boton(x, 500, 114, 64);
			x += 123;
		}
		// Botones de objetos
		x = 10;
		// Primera fila
		for (int i = 0; i < 5 && i < btt_objetos.length; i++) {
			btt_objetos[i] = new Boton(x, 10, 64, 64);
			x += 75;
		}
		if (btt_objetos.length > 5) { // Si se necesita crea la otra fila de botones
			for (int i = 5; i < btt_objetos.length; i++) {
				btt_objetos[i] = new Boton(x, 85, 64, 64);
				x += 75;
			}
		}
		// Creo los botones de acción
		x = 15;
		for (int i = 0; i < 6; i++) {
			btt_acciones[i] = new Boton(x, 672, 216, 66);
			x += 224;
		}

		Juego juego = new Juego();

		juego.iniciar(); // Para los graficos y los controles

		// Empiezan las rondas
		int numAccionesAnteriores;
		while (true) {
			if (nivel == 0)
				System.out.print("MENU");// Por algun motivo solo funciona si esto está wtf
			if (nivel == 1) {
				numAccionesAnteriores = jugador.getRegistro().size();
				do {
					jugador.dameAccion(btt_personajes, btt_objetos, btt_localizaciones, btt_acciones, jugador,
							personajesAI, mapa);
				} while (numAccionesAnteriores == jugador.getRegistro().size());

				for (PersonajesAI AI : personajesAI) {
					AI.dameAccion(btt_personajes, btt_objetos, btt_localizaciones, btt_acciones, jugador, personajesAI,
							mapa);
					System.out.println(AI.getNombre() + " registro: " + AI.getRegistro().toString());
				}
				System.out.println("-----------------");

			}
		}

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
			}
			break;
		case 1: // Juego
			pantalla.mostrarJuego(personajesAI, jugador, mapa);

			// Actualizo los botones

			// Botones de seleccion de objetos solo se actualiza si el boton de coger o
			// dejar esta activado
			if (btt_acciones[0].getClick() || btt_acciones[1].getClick())
				for (int i = 0; i < btt_objetos.length; i++)
					raton.actualizarClickBtt(btt_objetos[i]);
			// Botones de seleccion de personajes
			if (btt_acciones[4].getClick() || btt_acciones[5].getClick())
				for (int i = 0; i < btt_personajes.length; i++)
					raton.actualizarClickBtt(btt_personajes[i]);
			// Botones de seleccion de localizaciones solo se activan si el boton de moverse
			// está activado
			if (btt_acciones[2].getClick())
				for (int i = 0; i < btt_localizaciones.length; i++)
					raton.actualizarClickBtt(btt_localizaciones[i]);

			// Botones de seleccion de accion se deshabilitan si se pulsa otro
			for (int i = 0; i < 6; i++) {
				boolean anterior = btt_acciones[i].getClick();
				raton.actualizarClickBtt(btt_acciones[i]);
				if (anterior == false && btt_acciones[i].getClick()) { // Si se ha pulsado ahora
					for (int j = 0; j < 6; j++) {
						if (j != i)
							btt_acciones[j].setClick(false);
					}
				}
				// Imprimo el sprite de boton pulsado
				if (btt_acciones[i].getClick()) {
					switch (i) {
					case 0:
						pantalla.mostrarSprite(0, 0, Sprites.btt_coger);
						break;
					case 2:
						pantalla.mostrarSprite(0, 0, Sprites.btt_mover);
						break;
					case 4:
						pantalla.mostrarSprite(0, 0, Sprites.btt_dar);
						break;
					case 5:
						pantalla.mostrarSprite(0, 0, Sprites.btt_pedir);
						break;
					}
				}

			}

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
		final byte APS_OBJETIVO = 9; // APS Actualizaciones por segundo
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
