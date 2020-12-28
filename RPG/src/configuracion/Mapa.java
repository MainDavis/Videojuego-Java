package configuracion;

import java.util.ArrayList;
import java.util.List;

public class Mapa extends Leer {
	private int matriz[][];
	private List<Integer>[] locObjeto = new List[getObj().size()];
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
			String[] strMatriz = (adyacenciasLocalizaciones.get(i)).split(",");
			if ((adyacenciasLocalizaciones.get(i)).split(",").length > 1) {
				strMatriz[0] = strMatriz[0].split("\\(")[1]; // Quito el primer nombre
				strMatriz[strMatriz.length - 1] = strMatriz[strMatriz.length - 1].substring(0,
						strMatriz[strMatriz.length - 1].length() - 1);
				for (int j = 1; j < strMatriz.length; j++) {
					strMatriz[j] = strMatriz[j].trim();
				}
			} else {
				strMatriz[0] = strMatriz[0].split("\\(")[1];
				strMatriz[0] = strMatriz[0].substring(0, strMatriz[0].length() - 1);
			}

			for (String element : strMatriz) {
				matriz[i][localizaciones.indexOf(element)] = 1;
			}
		}

		// Preparo el array de listas
		for (int i = 0; i < getObj().size(); i++) {
			locObjeto[i] = new ArrayList<>();
		}
		// Meto los valores
		for (String element : getLocObj()) {
			String temp[] = element.split("\\(");
			temp[1] = temp[1].substring(0, temp[1].length() - 1);
			if (localizaciones.contains(temp[1])) {
				locObjeto[localizaciones.indexOf(temp[1])].add(getObj().indexOf(temp[0]));
			}
		}

		for (List<Integer> element : locObjeto) {
			System.out.println(element);
		}

	}
}
