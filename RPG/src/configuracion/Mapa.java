package configuracion;

import java.util.List;

public class Mapa extends Leer {
	private int matriz[][];
	private List<Integer>[] locObjeto = new List[getNumLoc()];
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
//		for (int i = 0; i < getObj().size(); i++) {
//			locObjeto[i] = new ArrayList<>();
//		}
//		// Meto los valores
//		for (String element : getLocObj()) {
//			String temp[] = element.split("\\(");
//			temp[1] = temp[1].substring(0, temp[1].length() - 1);
//			if (localizaciones.contains(temp[1])) {
//				locObjeto[localizaciones.indexOf(temp[1])].add(getObj().indexOf(temp[0]));
//			}
//		}
	}
}
