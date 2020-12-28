package configuracion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Leer {
	private final List<String> personajes = new ArrayList<String>();
	private final List<String> localizaciones = new ArrayList<String>();
	private final List<String> AdyacenciasLocalizaciones = new ArrayList<String>();
	private final List<String> objetos = new ArrayList<String>();
	private final List<String> localizacionObjetivo = new ArrayList<String>();
	private final List<String> ObjetoObjetivo = new ArrayList<String>();

	public Leer() {

		int entrada = 0; // 0->Localizacion, 1->Personajes, 2->Objetos
		String linea;

		// Primero el primer fichero *Estado inicial*

		try {
			String filePath = System.getProperty("user.dir") + "\\recursos\\estado_inicial.txt";
			FileReader f = new FileReader(filePath);
			BufferedReader b = new BufferedReader(f);

			while ((linea = b.readLine()) != null) {
				if (linea.equals("<Localizacion>")) {
					entrada = 0;
					continue;
				} else if (linea.equals("<Personajes>")) {
					entrada = 1;
					continue;
				} else if (linea.equals("<Objetos>")) {
					entrada = 2;
					continue;
				}

				if (entrada == 0) {
					if (linea.indexOf("(") != -1) {
						AdyacenciasLocalizaciones.add(linea);
						localizaciones.add(linea.substring(0, linea.indexOf("(")));
					}
				} else if (entrada == 1) {
					if (linea.indexOf("(") != -1) {
						personajes.add(linea);
					}
				} else if (entrada == 2) {
					if (linea.indexOf("(") != -1) {
						objetos.add(linea);
					}
				}
			}
			System.out.println(localizaciones);
			System.out.println(AdyacenciasLocalizaciones);
			System.out.println(personajes);
			System.out.println(objetos);
			b.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
