package configuracion;

public final class Personajes extends Leer {

	private String nombre;

	private int objeto, creenciasPersonajes[], creenciasMapa[], registro[], localizacion;

	public Personajes[] personaje = new Personajes[getNumPersonajes()];

	public Personajes(String nombre, int objeto, int localizacion, int creenciasPersonajes[], int creenciasMapa[],
			int registro[]) {
		this.nombre = nombre;
		this.objeto = objeto;
		this.localizacion = localizacion;
		this.creenciasPersonajes = creenciasPersonajes;
		this.creenciasMapa = creenciasMapa;
		this.registro = registro;
	}
}
