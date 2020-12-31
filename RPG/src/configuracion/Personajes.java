package configuracion;

import java.util.ArrayList;
import java.util.List;

public class Personajes extends Leer {

	private String nombre;
	private List<Integer> registro = new ArrayList<Integer>();
	private int objeto, localizacion, creenciasPersonajes[] = new int[getNumPersonajes()],
			creenciasMapa[] = new int[getNumLoc()];

	public Personajes(String nombre, int objeto, int localizacion) {

		// Controlar el nombre, localizaciones y Objetos
		this.nombre = nombre;
		this.objeto = objeto;
		this.localizacion = localizacion;

		// Si está en -1 es que el personaje no tiene ningún objeto
		for (int i = 0; i < getNumPersonajes(); i++) {
			creenciasPersonajes[i] = -1;
		}
		for (int i = 0; i < getNumLoc(); i++) {
			creenciasMapa[i] = -1;
		}

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

	public int[] getCreenciasPersonajes() {
		return creenciasPersonajes;
	}

	public void setCreenciasPersonajes(int[] creenciasPersonajes) {
		this.creenciasPersonajes = creenciasPersonajes;
	}

	public int[] getCreenciasMapa() {
		return creenciasMapa;
	}

	public void setCreenciasMapa(int[] creenciasMapa) {
		this.creenciasMapa = creenciasMapa;
	}
}
