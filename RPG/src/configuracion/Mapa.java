package configuracion;

import java.util.ArrayList;
import java.util.List;

public class Mapa extends Leer {
	private int matriz[][];
	private List<Integer>[] locObjeto = new List[getNumLoc()]; // Array de listas -> locObjeto[indexLocalizaciones] =
																// Lista de objetos que hay en esa localización
	private List<String> localizaciones;
	private List<String> adyacenciasLocalizaciones;

	public Mapa() {
		this.localizaciones = getLoc();
		this.adyacenciasLocalizaciones = getAdLoc();

		// Gracias a la matriz de adyacencias puedo usar el mapa como un grafo
		this.matriz = new int[getNumLoc()][getNumLoc()];
		// Preparamos los String por individual y los metemos en la matriz de
		// adyacencias

		for (int i = 0; i < getNumLoc(); i++) {
			String[] temp = adyacenciasLocalizaciones.get(i).split(",");

			for (String element : temp) {
				matriz[i][localizaciones.indexOf(element)] = 1;
			}
		}

		// Preparo el array de listas
		for (int i = 0; i < getObj().size(); i++) {
			locObjeto[i] = new ArrayList<>();
		}

		// Meto los valores
		int i = 0;
		for (String element : getLocObj()) {
			if (localizaciones.contains(element)) {
				locObjeto[localizaciones.indexOf(element)].add(i);
				i++;
			}
		}
	}
}
