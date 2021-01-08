package configuracion;

import java.util.ArrayList;
import java.util.List;

import control.Boton;
import graficos.Sprites;

public class PersonajesAI extends Leer implements Accionable {

	private String nombre;
	private int objeto, localizacion, personajePedido = -1;
	private final int objetivoObjeto, objetivolocalizacion; // personajePedido/objeto = -1 si no hay

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

	public int[] dameAccion(Boton[] btt_personajes, Boton[] btt_objetos, Boton[] btt_localizaciones,
			Boton[] btt_acciones) {

		return null;
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
			if (objeto != -1) {
				for (int i = 0; i < personajesAI.length; i++) {

					if (objeto == personajesAI[i].objetivoObjeto && localizacion == personajesAI[i].localizacion) {
						if (personajePedido == i) {
							// Le doy el objeto
							personajesAI[i].objeto = objeto;
							objeto = -1; // Quito el objeto
							registro.add(4);
							return;
						} else {
							registro.add(3);
							return; // No hago nada y espero
						}
					}
				}

			} else if (true) { // Si no tengo ningun objeto

			}

		} else { // Si tengo el objeto que necesito

		}

	}
}
