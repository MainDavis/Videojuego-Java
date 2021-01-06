package configuracion;

import java.util.ArrayList;
import java.util.List;

import graficos.Sprites;

public class PersonajesAI extends Leer implements Accionable{

	private String nombre;
	private int objeto, localizacion;
	private final int objetivoObjeto, objetivolocalizacion; // objeto = -1 si no tiene objeto

	private List<Integer> creenciasLocPersonajes[] = new List[getNumLoc()];
	private List<Integer> creenciasLocObjetos[] = new List[getNumLoc()];
	private List<Integer> creenciasPersonajesObjetos[] = new List[getNumPersonajes()];

	private List<Integer> registro = new ArrayList<Integer>();

	private Sprites portrait;

	public PersonajesAI(String nombre, int objeto, int localizacion, int objetivoObjeto, int objetivoLocalizacion) {

		// Controlar el nombre, localizaciones y Objetos
		this.nombre = nombre;
		this.objeto = objeto;
		this.localizacion = localizacion;
		this.objetivoObjeto = objetivoObjeto;
		this.objetivolocalizacion = objetivoLocalizacion;

		portrait = new Sprites("/sprites/portraits/" + nombre + ".png", 114, 64);

		// Tanto las creencias como los registros empiezan vacios

	}

	public void setCreenciasLocPersonajes(int localizacion, int personaje) {
		if (!creenciasLocPersonajes[localizacion].contains(personaje)) {
			this.creenciasLocPersonajes[localizacion].add(personaje);
		}
	}

	public void setCreenciasLocObjetos(int localizacion, int objeto) {
		if (!creenciasLocObjetos[localizacion].contains(objeto)) {
			this.creenciasLocObjetos[localizacion].add(objeto);
		}
	}

	public void setCreenciasPersonajesObjetos(int personaje, int objeto) {
		if (!creenciasPersonajesObjetos[personaje].contains(objeto)) {
			this.creenciasPersonajesObjetos[personaje].add(objeto);
		}
	}

	public List<Integer>[] getCreenciasPersonajesObjetos() { // El unico get que necesito usar de creencias es este
		return creenciasPersonajesObjetos;
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

	public void dameAccion(Jugador jugador, PersonajesAI[] AI, Mapa mapa, int accion) {
		System.out.println("No");
	}
}
