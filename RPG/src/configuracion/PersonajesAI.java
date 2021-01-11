package configuracion;

import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

import control.Boton;
import graficos.Sprites;

public class PersonajesAI extends Leer implements Accionable {

	private String nombre;
	private int objeto, localizacion, personajePedido = -1;
	private final int objetivoObjeto, objetivolocalizacion; // personajePedido/objeto = -1 si no hay
	private boolean locVisitados[] = new boolean[getNumLoc()];

	private List<Integer> ruta;
	private int posicionRuta;

	private List<Integer> creenciasLocPersonajes[] = new List[getNumLoc()];

	private List<Integer> registro = new ArrayList<Integer>();

	private Sprites portrait;

	public PersonajesAI(String nombre, int objeto, int localizacion, int objetivoObjeto, int objetivoLocalizacion,
			int[][] matriz) {

		// Controlar el nombre, localizaciones y Objetos
		this.nombre = nombre;
		this.objeto = objeto;
		this.localizacion = localizacion;
		this.objetivoObjeto = objetivoObjeto;
		this.objetivolocalizacion = objetivoLocalizacion;

		this.ruta = DFS(matriz, localizacion);
		this.posicionRuta = 0;

		portrait = new Sprites("/sprites/portraits/" + nombre + ".png", 114, 64);

		// Tanto las creencias como los registros empiezan vacios
		for (int i = 0; i < getNumLoc(); i++) {
			creenciasLocPersonajes[i] = new ArrayList<Integer>();
		}

	}

	public List<Integer> DFS(int[][] matriz, int inicio) {

		List<Integer> ruta = new ArrayList<Integer>(9);
		boolean[] visitado = new boolean[matriz.length];
		visitado[inicio] = true;
		Stack<Integer> stack = new Stack<>();

		stack.push(inicio);

		int i, x;

		while (!stack.isEmpty()) {
			x = stack.pop();
			ruta.add(x);
			for (i = 0; i < matriz.length; i++) {
				if (matriz[x][i] == 1 && visitado[i] == false) {
					stack.push(x);
					visitado[i] = true;
					ruta.add(i);
					x = i;
					i = -1;
				}
			}
		}
		ruta.remove(0);
		return ruta;
	}

	public void setCreenciasLocPersonajes(int localizacion, int personaje) {
		// Si meto -1 es para hacer reset
		if (personaje == -1) {
			creenciasLocPersonajes[localizacion].clear();
		} else if (!creenciasLocPersonajes[localizacion].contains(personaje)) {
			this.creenciasLocPersonajes[localizacion].add(personaje);
		}
	}

	public Sprites getPortrait() {
		return portrait;
	}

	public void setPortrait(Sprites portrait) {
		this.portrait = portrait;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public List<Integer> getRegistro() {
		return registro;
	}

	public void setRegistro(List<Integer> registro) {
		this.registro = registro;
	}

	public int getObjeto() {
		return objeto;
	}

	public void setObjeto(int objeto) {
		this.objeto = objeto;
	}

	public int getLocalizacion() {
		return localizacion;
	}

	public void setLocalizacion(int localizacion) {
		this.localizacion = localizacion;
	}

	public int[] dameAccion(Boton[] btt_personajes, Boton[] btt_objetos, Boton[] btt_localizaciones,
			Boton[] btt_acciones) {

		return null;
	}

	public int getPersonajePedido() {
		return personajePedido;
	}

	public List<Integer> getRuta() {
		return ruta;
	}

	public int getPosicionRuta() {
		return posicionRuta;
	}

	public void setPeticionJugador() {
		this.personajePedido = 0;
	}

	public void mover() {
		if (posicionRuta == ruta.size()) {
			posicionRuta = 0;
		}
		this.localizacion = ruta.get(posicionRuta);
		posicionRuta++;

		// Como me muevo, ya no estoy con los personajes de antes por lo que se cancela
		// el pedido
		personajePedido = -1;

	}

	public void dameAccion(Boton[] btt_personajes, Boton[] btt_objetos, Boton[] btt_localizaciones,
			Boton[] btt_acciones, Jugador jugador, PersonajesAI personajesAI[], Mapa mapa) {

		// Preferencias de la IA
		// SI NO TENGO EL OBJETO OBJETIVO
		// 1. Con objeto
		// 1.1 Si hay un jugador en la sala que necesita mi objeto se lo doy (Y
		// previamente lo ha pedido)
		// 1.1.1 Si tiene objeto no hago nada, espero a que lo deje y me lo pida
		// 1.1.2 Si no tiene objeto se lo doy
		// 1.2 Si está el objeto que necesito en la sala o lo tiene algun personaje en
		// la sala
		// 1.2.1 Si está el objeto en la sala lo cojo
		// 1.2.2.1 Si está el objeto en un personaje dejo el objeto y en el siguiente
		// turno lo pido
		// 1.3 Si no se cumple nada me muevo
		// 2. Sin objeto
		// 2.1 Si está mi objeto en la sala o lo tiene algun personaje en la sala
		// 2.1.1 Si está en la sala lo cojo
		// 2.1.2 Si está en un personaje lo pido
		// 2.2 Si no se cumple nada me muevo
		// CON EL OBJETO OBJETIVO
		// 1. Me muevo

		if (objeto != objetivoObjeto) { // Si no tiene el objeto objetivo
			if (objeto != -1) { // Si tiene un objeto
				// Si un jugador necesita el objeto que tengo o un jugador tiene mi objeto
				for (int i = 0; i < personajesAI.length; i++) {
					if (objeto == personajesAI[i].objetivoObjeto && localizacion == personajesAI[i].localizacion) {
						if (personajePedido == i + 1) {
							// Le doy el objeto
							personajesAI[i].objeto = objeto;
							objeto = -1; // Quito el objeto
							personajePedido = -1; // Quito el pedido
							registro.add(4); // Dar
							return;
						} else {
							registro.add(3); // Nada
							return; // No hago nada y espero
						}
					} else if (personajesAI[i].objeto == objetivoObjeto
							&& personajesAI[i].localizacion == localizacion) {
						// Si hay un jugador que tiene mi objeto, dejo el mio
						mapa.setObjetosLoc(localizacion, objeto);
						objeto = -1;
						registro.add(1); // Dejar
						// Si hay una peticion de objeto lo cancelo
						personajePedido = -1;
						return;
					}
				}

				// Lo mismo pero para el jugador
				if (jugador.getObjObjetivo() == objeto && jugador.getLoc() == localizacion) {
					if (personajePedido == 0) {
						jugador.setObj(objeto);
						objeto = -1;
						personajePedido = -1;
						registro.add(4); // Dar
						return;
					} else {
						registro.add(3); // Nada
						return;
					}
				} else if (jugador.getObj() == objetivoObjeto && jugador.getLoc() == localizacion) {
					mapa.setObjetosLoc(localizacion, objeto);
					objeto = -1;
					registro.add(1); // Dejar
					// Si hay una peticion de objeto lo cancelo
					personajePedido = -1;
					return;
				}

				// Si está el objeto que necesito en la misma localizacion que yo

				if (mapa.getObjetosLoc(localizacion).indexOf(objetivoObjeto) != -1) {
					mapa.setObjetosLoc(localizacion, objeto);
					objeto = -1;
					registro.add(1); // Dejar
					return;
				}

				// Si no es nada de eso me muevo
				mover();
				registro.add(2);

				// Actualizo las creecias que tiene el personaje de la nueva localizacion
				// Primero la reseteo
				if (creenciasLocPersonajes[localizacion].size() > 0)
					setCreenciasLocPersonajes(localizacion, -1);
				// Añado a los personajes
				if (jugador.getLoc() == localizacion)
					setCreenciasLocPersonajes(localizacion, 0);
				for (int i = 0; i < personajesAI.length; i++) {
					if (personajesAI[i].getLocalizacion() == localizacion)
						setCreenciasLocPersonajes(localizacion, i + 1);
				}

				return;

			} else if (true) { // Si no tengo ningun objeto

				// Si hay un jugador con mi objeto lo pido
				for (int i = 0; i < personajesAI.length; i++) {
					if (personajesAI[i].objeto == objetivoObjeto && personajesAI[i].localizacion == localizacion) {
						for (int j = 0; j < personajesAI.length; j++) {
							if (personajesAI[j].nombre.equals(nombre)) {
								personajesAI[i].personajePedido = j + 1;
								registro.add(5); // Pedir
								return;
							}
						}
					}
				}
				// Lo mismo para el jugador
				if (jugador.getObj() == objetivoObjeto && jugador.getLoc() == localizacion) {
					for (int i = 0; i < personajesAI.length; i++) {
						if (personajesAI[i].nombre.equals(nombre)) {
							jugador.setPersonajePedido(i);
							registro.add(5); // Pedir
							return;
						}
					}

				}

				// Si el objeto que necesito está en la sala
				if (mapa.getObjetosLoc(localizacion).indexOf(objetivoObjeto) != -1) {

					objeto = objetivoObjeto;
					mapa.removeObjetoLoc(localizacion, mapa.getObjetosLoc(localizacion).indexOf(objeto));
					registro.add(0); // Coger
					return;
				}

				// Si no se cumple nada me muevo
				mover();
				registro.add(2); // Mover

				// Actualizo las creecias que tiene el personaje de la nueva localizacion
				// Primero la reseteo
				if (creenciasLocPersonajes[localizacion].size() > 0)
					setCreenciasLocPersonajes(localizacion, -1);
				// Añado a los personajes
				if (jugador.getLoc() == localizacion)
					setCreenciasLocPersonajes(localizacion, 0);
				for (int i = 0; i < personajesAI.length; i++) {
					if (personajesAI[i].getLocalizacion() == localizacion)
						setCreenciasLocPersonajes(localizacion, i + 1);
				}
				return;
			}

		} else { // Si tengo el objeto que necesito
			if (localizacion != objetivolocalizacion) {
				// Me muevo
				mover();
				registro.add(2); // Mover

				// Actualizo las creecias que tiene el personaje de la nueva localizacion
				// Primero la reseteo
				if (creenciasLocPersonajes[localizacion].size() > 0)
					setCreenciasLocPersonajes(localizacion, -1);
				// Añado a los personajes
				if (jugador.getLoc() == localizacion)
					setCreenciasLocPersonajes(localizacion, 0);
				for (int i = 0; i < personajesAI.length; i++) {
					if (personajesAI[i].getLocalizacion() == localizacion)
						setCreenciasLocPersonajes(localizacion, i + 1);
				}

				return;
			} else {
				// Si tengo el objeto y estoy en la localizacion objetivo no hago nada
				registro.add(3); // Nada
				return;
			}
		}

	}
}
