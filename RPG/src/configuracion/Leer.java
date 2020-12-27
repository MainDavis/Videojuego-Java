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

	// private final Leer info = new Leer();

	public Leer() {

		int entrada = 0; // 0->Localizacion, 1->Personajes, 2->Objetos
		String linea;

		// Primero el primer fichero *Estado inicial*

		try {
			FileReader f = new FileReader("C:\\Users\\David\\Desktop\\JavaProject\\RPG\\recursos\\estado_inicial.txt");
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
					System.out.println(localizaciones + " | " + AdyacenciasLocalizaciones);
				}

				// System.out.println(linea + " " + entrada);

			}

			b.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
