package configuracion;

import java.util.List;

public class Mapa extends Leer {
	private int matriz[][];
	private int objeto[];
	private List<String> localizaciones = getLoc();
	private List<String> AdyacenciasLocalizaciones = getAdLoc();
	private static final String nomLocalizacion = "";

	public Mapa() {
		matriz = new int[getNumLoc()][getNumLoc()];

	}

}
