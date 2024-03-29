package configuracion;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Leer {

	private final List<String> personajes = new ArrayList<String>();
	private final List<String> localizaciones = new ArrayList<String>();
	private final List<String> objetos = new ArrayList<String>();

	private final List<String> personajesLocIni = new ArrayList<String>();
	private final List<String> adyacenciasLocalizaciones = new ArrayList<String>();

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
						// Compruebo que se han introducido localizaciones validas
						String temp[] = (linea.substring(linea.indexOf("(") + 1, linea.indexOf(")")).toLowerCase()
								.replaceAll(" ", "")).split(",");
						for (String element : temp) {
							if (!comprobarLocalizacion(element)) {
								System.out.println(element + " no es una localizacion valida");
								System.exit(-1);
							}
						}
						adyacenciasLocalizaciones.add(linea.substring(linea.indexOf("(") + 1, linea.indexOf(")"))
								.toLowerCase().replaceAll(" ", ""));
						// Compruebo que se han introducido localizaciones validas
						if (comprobarLocalizacion(linea.substring(0, linea.indexOf("(")).toLowerCase())) {
							localizaciones.add(linea.substring(0, linea.indexOf("(")).toLowerCase());
						} else {
							System.out.println(linea.substring(0, linea.indexOf("(")).toLowerCase()
									+ " No es una localizacion valida");
							System.exit(-1);
						}
					}
				} else if (entrada == 1) {
					if (linea.indexOf("(") != -1) {
						// Compruebo que se han introducido personajes validos
						if (comprobarPersonaje(linea.split("\\(")[0].toLowerCase())) {
							personajes.add(linea.split("\\(")[0].toLowerCase());
						} else {
							System.out.println(linea.split("\\(")[0].toLowerCase() + " no es un nombre válido");
							System.exit(-1);
						}

						if (comprobarLocalizacion(
								linea.split("\\(")[1].substring(0, linea.split("\\(")[1].length() - 1).toLowerCase())) {
							personajesLocIni.add(linea.split("\\(")[1].substring(0, linea.split("\\(")[1].length() - 1)
									.toLowerCase());
						} else {
							System.out.println(
									linea.split("\\(")[1].substring(0, linea.split("\\(")[1].length() - 1).toLowerCase()
											+ " No es una localizacion valida");
							System.exit(-1);
						}

					}
				} else if (entrada == 2) {
					if (linea.indexOf("(") != -1) {
						if (personajes
								.contains(linea.substring(linea.indexOf("(") + 1, linea.indexOf(")")).toLowerCase())
								|| localizaciones.contains(
										linea.substring(linea.indexOf("(") + 1, linea.indexOf(")")).toLowerCase())) {
							locObjetos.add(linea.substring(linea.indexOf("(") + 1, linea.indexOf(")")).toLowerCase());

						} else {
							System.out.println(linea.substring(linea.indexOf("(") + 1, linea.indexOf(")")).toLowerCase()
									+ " no es un nombre o lugar válido");
							System.exit(-1);
						}
						if (comprobarObjeto(linea.substring(0, linea.indexOf("(")).toLowerCase())) {
							objetos.add(linea.substring(0, linea.indexOf("(")).toLowerCase());
						} else {
							System.out.println(
									linea.substring(0, linea.indexOf("(")).toLowerCase() + " no es un objeto valido.");
							System.exit(-1);
						}
					}
				}
			}

			b.close();

			// Segundo el archivo de *Objetivos*
			filePath = System.getProperty("user.dir") + "\\recursos\\objetivos.txt";
			f = new FileReader(filePath);
			b = new BufferedReader(f);

			while ((linea = b.readLine()) != null) {
				if (linea.equals("<Localizaciones Personajes>")) {
					entrada = 0;
				} else if (linea.equals("<Posesiones Objetos>")) {
					entrada = 1;
				}

				if (entrada == 0) {
					if (linea.indexOf("(") != -1) {
						if (comprobarPersonaje(linea.substring(0, linea.indexOf("(")).toLowerCase())
								&& comprobarLocalizacion(
										linea.substring(linea.indexOf("(") + 1, linea.length() - 1).toLowerCase())) {
							localizacionObjetivo.add(linea.substring(0, linea.indexOf("(")).toLowerCase() + ","
									+ linea.substring(linea.indexOf("(") + 1, linea.length() - 1).toLowerCase());
						} else {
							System.out.println(linea.substring(0, linea.indexOf("(")) + " o "
									+ linea.substring(linea.indexOf("(") + 1, linea.length() - 1)
									+ " no es un nombre o localizacion validos");
							System.exit(-1);
						}
					}
				} else if (entrada == 1) {
					if (linea.indexOf("(") != -1) {
						if (comprobarPersonaje(
								linea.substring(linea.indexOf("(") + 1, linea.length() - 1).toLowerCase())
								&& comprobarObjeto(linea.substring(0, linea.indexOf("(")).toLowerCase())) {
							objetoObjetivo.add(linea.substring(linea.indexOf("(") + 1, linea.length() - 1).toLowerCase()
									+ "," + linea.substring(0, linea.indexOf("(")).toLowerCase());
						} else {
							System.out.println(linea.substring(linea.indexOf("(") + 1, linea.length() - 1) + " o "
									+ linea.substring(0, linea.indexOf("(")) + " no es un nombre u objeto validos");
							System.exit(-1);
						}
					}
				}
			}
			b.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public int getNumLoc() {
		return localizaciones.size();
	}

	public int getNumObj() {
		return objetos.size();
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

	public int getObjObjetivo(int i) {
		for (String element : objetoObjetivo) {
			if (personajes.get(i).equals(element.split(",")[0])) {
				return objetos.indexOf(element.split(",")[1]);
			}
		}
		return -1; // Nunca va a llegar a esto
	}

	public int getLocObjetivo(int i) {
		for (String element : localizacionObjetivo) {
			if (personajes.get(i).equals(element.split(",")[0])) {
				return localizaciones.indexOf(element.split(",")[1]);
			}
		}
		return -1;
	}

	public int getNumPersonajes() {
		return personajes.size();
	}

	public List<String> getPersonajes() {
		return personajes;
	}

	public List<String> getPersonajesLocIni() {
		return personajesLocIni;
	}

	public int getPersonajeLocIniINT(int index) {
		return (localizaciones.indexOf(personajesLocIni.get(index)));
	}

	public int getPersonajeObjetoInicial(String nombre) {
		int objeto = -1;
		for (String element : locObjetos) {
			if (nombre.equals(element)) {
				objeto = locObjetos.indexOf(element);
			}
		}

		return objeto;
	}

	public boolean comprobarPersonaje(String nombre) {
		if (nombre.split("\\(")[0].toLowerCase().equals("gunwoman") || nombre.equals("king") || nombre.equals("knight")
				|| nombre.equals("martial") || nombre.equals("ninja") || nombre.equals("robot")
				|| nombre.equals("skeleton_archer") || nombre.equals("skeleton_chief")
				|| nombre.equals("skeleton_normal") || nombre.equals("wizard")) {
			return true;
		}
		return false;
	}

	public boolean comprobarLocalizacion(String nombre) {
		if (nombre.equals("bambu") || nombre.equals("playa") || nombre.equals("cascada") || nombre.equals("castillo")
				|| nombre.equals("catedral") || nombre.equals("cienaga") || nombre.equals("muerte")
				|| nombre.equals("nieve") || nombre.equals("pueblo") || nombre.equals("templo")) {
			return true;
		}
		return false;
	}

	public boolean comprobarObjeto(String nombre) {
		if (nombre.equals("anillo") || nombre.equals("daga") || nombre.equals("espada") || nombre.equals("gemas")
				|| nombre.equals("grimorio") || nombre.equals("llave") || nombre.equals("maza")
				|| nombre.equals("medallon") || nombre.equals("pocion") || nombre.equals("yelmo")) {
			return true;
		}
		return false;
	}

}
