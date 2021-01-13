package configuracion;

import java.util.ArrayList;
import java.util.List;

import control.Boton;

public class Jugador implements Accionable {
	private String nombre;
	private int obj, loc, personajePedido = -1;
	private final int objObjetivo, locObjetivo;
	private List<Integer> registro = new ArrayList<Integer>();

	public Jugador(String nombre, int objeto, int localizacion, int objObjetivo, int locObjetivo) {
		this.nombre = nombre;
		this.obj = objeto; // Si es -1 es que no tiene objeto
		this.loc = localizacion;
		this.objObjetivo = objObjetivo;
		this.locObjetivo = locObjetivo;
	}

	public int getNumAnimacion() {
		String personajes[] = { "gunwoman", "king", "knight", "martial", "ninja", "robot", "skeleton_archer",
				"skeleteon_chief", "skeleton_normal", "wizard" };
		for (int i = 0; true; i++) { // Nunca se va a pasar del final
			if (personajes[i].equals(this.nombre)) {
				return i;
			}
		}
	}

	public String getNombre() {
		return this.nombre;
	}

	public int getLoc() {
		return this.loc;
	}

	public int getObj() {
		return this.obj;
	}

	public int getObjObjetivo() {
		return this.objObjetivo;
	}

	public int getLocObjetivo() {
		return this.locObjetivo;
	}

	public void setLoc(int loc) {
		this.loc = loc;
	}

	public void setObj(int obj) {
		this.obj = obj;
	}

	public void setPersonajePedido(int personaje) {
		this.personajePedido = personaje;
	}

	public void addRegistro(int accion) {
		this.registro.add(accion);
	}

	public List<Integer> getRegistro() {
		return registro;
	}

	public int getPedido() {
		return personajePedido;
	}

	public void dameAccion(Boton[] btt_personajes, Boton[] btt_objetos, Boton[] btt_localizaciones,
			Boton[] btt_acciones, Jugador jugador, PersonajesAI personajesAI[], Mapa mapa) {
		// Botones acciones:
		// 0-CogerObjeto, 1-DejarObjeto, 2-Moverse, 3-Nada, 4-PedirObjeto, 5-DarObjeto
		// Solo funciona si el jugador no tiene ningun objeto y si hay en loc
		if (btt_acciones[0].getClick() && jugador.getObj() == -1 && mapa.getObjetosLoc(jugador.getLoc()).size() != 0) {
			// Miro todos los botones de los objetos
			for (int i = 0; i < mapa.getObjetosLoc(jugador.getLoc()).size(); i++) {
				if (btt_objetos[i].getClick()) { // Recoger el objeto seleccionado
					// Añado el objeto al jugador
					jugador.setObj(mapa.getObjetosLoc(jugador.getLoc()).get(i));
					// Elimino el objeto de la localizacion
					mapa.getObjetosLoc(jugador.getLoc())
							.remove(mapa.getObjetosLoc(jugador.getLoc()).indexOf(jugador.getObj()));
					// Quito los dos botones
					btt_acciones[0].setClick(false);
					btt_objetos[i].setClick(false);
					// Añado la accion al registro
					jugador.addRegistro(0);
				}
			}
		} else if (btt_acciones[1].getClick() && jugador.getObj() != -1) {
			// Añado el objeto al mapa
			mapa.setObjetosLoc(jugador.getLoc(), jugador.getObj());
			// Quito el objeto del jugador
			jugador.setObj(-1);
			// Quito el boton
			btt_acciones[1].setClick(false);
			// Añado la accion al registro
			jugador.addRegistro(1);
		} else if (btt_acciones[2].getClick()) {
			// Miro todos los botones de localizaciones
			for (int j = 0; j < mapa.getAdyacencias(jugador.getLoc()).length; j++) {
				if (btt_localizaciones[j].getClick()) {
					// Cambio de localizacion al jugador
					jugador.setLoc(mapa.adyacenciaLoc(jugador.getLoc()).get(j));
					// Quito los botones
					btt_localizaciones[j].setClick(false);
					btt_acciones[2].setClick(false);
					// Añado la accion al registro
					jugador.addRegistro(2);
				}
			}
		} else if (btt_acciones[3].getClick()) {
			jugador.addRegistro(3);
			btt_acciones[3].setClick(false);
		} else if (btt_acciones[4].getClick()) {
			int contador = 0;
			for (PersonajesAI AI : personajesAI) {
				// Si el personaje AI esta en la misma loc que el jugador
				if (AI.getLocalizacion() == jugador.getLoc()) {
					// Si no tiene ningún objeto y su boton seleccionado
					for (int i = 0; i < personajesAI.length; i++) {
						if (personajesAI[i].getNombre().equals(AI.getNombre())) {
							if (btt_personajes[contador].getClick() && personajePedido == i) {
								// Le doy el objeto al personaje
								AI.setObjeto(jugador.getObj());
								// Le quito el objeto al jugador
								jugador.setObj(-1);
								// Quito el pedido
								this.personajePedido = -1;
								// Quito los botones
								btt_acciones[4].setClick(false);
								btt_personajes[contador].setClick(false);
								// Añado la accion al registro
								jugador.addRegistro(4);
							}
						}
					}

					contador++;
				}
			}
		} else if (btt_acciones[5].getClick()) {
			int contador = 0;
			for (PersonajesAI AI : personajesAI) {
				// Si el personaje AI esta en la misma loc que el jugador
				if (AI.getLocalizacion() == jugador.getLoc()) {
					// Si tiene un objeto y su boton seleccionado
					if (AI.getObjeto() != -1 && btt_personajes[contador].getClick()) {
						AI.setPeticionJugador();
						// Quito los botones
						btt_acciones[5].setClick(false);
						btt_personajes[contador].setClick(false);
						// Añado la accion al registro
						jugador.addRegistro(5);
					}
					contador++;
				}
			}
		}
	}
}
