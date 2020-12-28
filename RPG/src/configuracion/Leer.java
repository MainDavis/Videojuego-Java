package configuracion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Leer {
	private final List<String> personajes = new ArrayList<String>();
	private final List<Integer> personajesLocIni = new ArrayList<Integer>();
	private final List<String> localizaciones = new ArrayList<String>();
	private final List<String> adyacenciasLocalizaciones = new ArrayList<String>();
	private final List<String> objetos = new ArrayList<String>();
	private final List<String> locObjetos = new ArrayList<String>();
	private final List<String> localizacionObjetivo = new ArrayList<String>();
	private final List<String> objetoObjetivo = new ArrayList<String>();

	public Leer() {
		int entrada = 0; // 0->Localizacion, 1->Personajes, 2->Objetos || 0->Localizacion,
		String linea;

		try {
			// Primero el fichero *Estado inicial*

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
						adyacenciasLocalizaciones.add(linea);
						localizaciones.add(linea.substring(0, linea.indexOf("(")));
					}
				} else if (entrada == 1) {
					if (linea.indexOf("(") != -1) {
						personajes.add(linea.split("\\(")[0]);
						personajesLocIni.add(localizaciones
								.indexOf(linea.split("\\(")[1].substring(0, linea.split("\\(")[1].length() - 1)));
					}
				} else if (entrada == 2) {
					if (linea.indexOf("(") != -1) {
						locObjetos.add(linea);
						objetos.add(linea.substring(0, linea.indexOf("(")));
					}
				}
			}

			b.close();

			// Segundo el archivo de *Objetivos*
			filePath = System.getProperty("user.dir") + "\\recursos\\objetivos.txt";
			f = new FileReader(filePath);
			b = new BufferedReader(f);

			while ((linea = b.readLine()) != null) {
				if (linea.equals("<Localización Personajes>")) {
					entrada = 0;
				} else if (linea.equals("<Posesión Objetos>")) {
					entrada = 1;
				}

				if (entrada == 0) {
					if (linea.indexOf("(") != -1) {
						localizacionObjetivo.add(linea);
					}
				} else if (entrada == 1) {
					if (linea.indexOf("(") != -1) {
						objetoObjetivo.add(linea);
					}
				}

				// System.out.println(localizacionObjetivo + "|| " + objetoObjetivo);
			}

			b.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getNumLoc() {
		return localizaciones.size();
	}

	public List<String> getLoc() {
		return localizaciones;
	}

	public List<String> getAdLoc() {
		return adyacenciasLocalizaciones;
	}

	public List<String> getObj() {
		return objetos;
	}

	public List<String> getLocObj() {
		return locObjetos;
	}

	public int getNumPersonajes() {
		return personajes.size();
	}

	public List<String> getPersonajes() {
		return personajes;
	}

	public List<Integer> getPersonajesLocIni() {
		return personajesLocIni;
	}
}
