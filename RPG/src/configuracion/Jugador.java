package configuracion;

import java.util.ArrayList;
import java.util.List;

import control.Boton;

public class Jugador implements Accionable {
	private String nombre;
	private int obj, loc;
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

	public int[] dameAccion(Boton[] btt_personajes, Boton[] btt_objetos, Boton[] btt_localizaciones,
			Boton[] btt_acciones, Jugador jugador, PersonajesAI personajesAI[], Mapa mapa) {
		// Botones acciones:
		// 0-CogerObjeto, 1-DejarObjeto, 2-Moverse, 3-Nada, 4-PedirObjeto, 5-DarObjeto
		if (btt_acciones[0].getClick() && jugador.getObj() != -1 && mapa.getLocObj().size() != 0) { // Solo funciona si
																									// el jugador no
																									// tiene ningun
																									// objeto y si hay
																									// en loc

		}

		return null;
	}
}
