package configuracion;

import java.util.ArrayList;
import java.util.List;

import graficos.Sprites;

public class Mapa extends Leer {
	private int matriz[][];
	private List<Integer>[] locObjeto = new List[getNumLoc()]; // Array de listas -> locObjeto[indexLocalizaciones] =
																// Lista de objetos que hay en esa localización
	private List<String> localizaciones;
	private List<String> adyacenciasLocalizaciones;
	private Sprites localizacionesSprites[] = new Sprites[getNumLoc()];
	private Sprites locPortraitSprites[] = new Sprites[getNumLoc()];
	private Sprites objetosSprites[] = new Sprites[getNumObj()];
	private Sprites objPortraitSprites[] = new Sprites[getNumObj()];

	public Mapa() {
		this.localizaciones = getLoc();
		this.adyacenciasLocalizaciones = getAdLoc();

		// Creo los sprites de las localizaciones
		int iteracion = 0;
		for (String element : localizaciones) {
			localizacionesSprites[iteracion] = new Sprites("/sprites/fondos/" + element + ".png", 1366, 768);
			iteracion++;
		}
		// Creo los sprites de los portraits de las localizaciones
		iteracion = 0;
		for (String element : localizaciones) {
			locPortraitSprites[iteracion] = new Sprites("/sprites/portraits/" + element + ".png", 114, 64);
			iteracion++;
		}
		// Creo los sprites de los objetos
		iteracion = 0;
		for (String element : getObj()) {
			objetosSprites[iteracion] = new Sprites("/sprites/objetos/" + element + ".png", 64, 64);
			iteracion++;
		}
		// Creo los sprites de los portraits de los objetos
		iteracion = 0;
		for (String element : getObj()) {
			objPortraitSprites[iteracion] = new Sprites("/sprites/portraits/" + element + ".png", 32, 32);
			iteracion++;
		}

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
		for (int i = 0; i < getNumLoc(); i++) {
			locObjeto[i] = new ArrayList<>();
		}

		// Meto los valores
		int i = 0;
		for (String element : getLocObj()) {
			if (localizaciones.contains(element)) {
				locObjeto[localizaciones.indexOf(element)].add(i);
			}
			i++;
		}

	}

	public Sprites getLocSprite(int index) {
		return localizacionesSprites[index];
	}

	public Sprites getObjSprite(int index) {
		return objetosSprites[index];
	}

	public Sprites getLocPortraitSprite(int index) {
		return locPortraitSprites[index];
	}

	public Sprites getObjPortraitSprite(int index) {
		return objPortraitSprites[index];
	}

	public List<String> getLocalizaciones() {
		return localizaciones;
	}

	public int[] getAdyacencias(int loc) {
		return matriz[loc];
	}

	public List<Integer> getObjetosLoc(int loc) {
		return locObjeto[loc];
	}

	public void setObjetosLoc(int loc, int obj) {
		this.locObjeto[loc].add(obj);
	}

	public List<Integer> adyacenciaLoc(int loc) {
		List<Integer> ady = new ArrayList<Integer>();
		for (int i = 0; i < matriz[loc].length; i++) {
			if (matriz[loc][i] == 1) {
				ady.add(i);
			}
		}

		return ady;
	}

	public void removeObjetoLoc(int loc, int obj) {
		locObjeto[loc].remove(obj);
	}
}
