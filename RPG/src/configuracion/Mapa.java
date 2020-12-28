package configuracion;

import java.util.List;

public class Mapa extends Leer {
	private int matriz[][];
	private int objeto[];
	private List<String> localizaciones;
	private List<String> AdyacenciasLocalizaciones;

	public Mapa() {
		this.localizaciones = getLoc();
		this.AdyacenciasLocalizaciones = getAdLoc();
		this.matriz = new int[getNumLoc()][getNumLoc()]; // Gracias a la matriz de adyacencias puedo usar el mapa como
															// un grafo

		// Preparamos los String por individual y los metemos en la matriz de
		// adyacencias
		for (int i = 0; i < getNumLoc(); i++) {
			String[] strMatriz = (AdyacenciasLocalizaciones.get(i)).split(",");
			if ((AdyacenciasLocalizaciones.get(i)).split(",").length > 1) {
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
	}
}
