package configuracion;

import java.util.Arrays;
import java.util.List;

public class Mapa extends Leer {
	private int matriz[][];
	private int objeto[];
	private List<String> localizaciones = getLoc();
	private List<String> AdyacenciasLocalizaciones;
	private static final String nomLocalizacion = "";

	public Mapa() {
		this.AdyacenciasLocalizaciones = getAdLoc();
		this.matriz = new int[getNumLoc()][getNumLoc()];
		String[] strMatriz = (AdyacenciasLocalizaciones.get(1)).split(",");
		System.out.println(strMatriz[0]);

		System.out.println(Arrays.toString(strMatriz)); // [A, B, C, D]

	}

}
