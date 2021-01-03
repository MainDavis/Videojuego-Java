package configuracion;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
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
		String personajes[] = { "gunwoman", "king", "knight", "martial", "ninja", "robot", "skeletonArcher",
				"skeleteonChief", "skeletonNormal", "wizard" };
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
}
