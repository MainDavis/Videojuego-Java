package configuracion;

import java.util.ArrayList;
import java.util.List;

public class Jugador {
	private String nombre;
	private int obj, loc;
	private List<Integer> registro = new ArrayList<Integer>();

	public Jugador(String nombre, int objeto, int localizacion) {
		this.nombre = nombre;
		this.obj = objeto;
		this.loc = localizacion;
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
}
