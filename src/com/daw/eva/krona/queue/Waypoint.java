package com.daw.eva.krona.queue;

import Varies.Cadena;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Scanner;
import java.util.concurrent.TimeUnit;



public class Waypoint {
	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
	
	
	//1. Inicialitzar el procés de comprovació d'un ArrayList i un LinkedList de waypoints.
	public static ComprovacioRendiment inicialitzarComprovacioRendiment() {
		ComprovacioRendiment comprovacioRendimentTmp = new ComprovacioRendiment();
		
		return comprovacioRendimentTmp; 
	}
	
	
	//2. Comprovar rendiment d'inicialització d'un ArrayList i un LinkedList de waypoints.
	public static ComprovacioRendiment comprovarRendimentInicialitzacio(int numObjACrear, ComprovacioRendiment comprovacioRendimentTmp) {
		long tempsInicial;
		long tempsFinal;
		long tempsEnNanosegons;
		long tempsEnMilisegons;
		int meitatLLista;
		
		
		meitatLLista = numObjACrear/2;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		int[] coordenadesTmp = new int[] {0,0,0};

				
		//1.1. Inicilitzem les llistes.
		tempsInicial = System.nanoTime();
		for(int i = 0; i < numObjACrear; i++) {
			//comprovacioRendimentTmp.llistaArrayList.add(new Waypoint_Dades(0, "Òrbita de la Terra", coordenadesTmp, true, LocalDateTime.parse("15-08-1954 00:01", formatter), null, null));
			//ALERTA!!!: coordenadesTmp ÉS 1 OBJECTE (l'hem creat amb un new()) COMPARTIT PER TOTS ELS Waypoint_Dades QUE CREEM PERQUE
			//CADA VEGADA QUE EL COPIEM EN UN OBJECTE DE TIPUS Waypoint EN REALITAT ESTEM COPIANT UNA REFERÈNCIA A L'OBJECTE I NO L'OBJECTE EN SI.
			//SOLUCIÓ:
			comprovacioRendimentTmp.llistaArrayList.add(new Waypoint_Dades(0, "Òrbita de la Terra", new int[] {0,0,0}, true, LocalDateTime.parse("15-08-1954 00:01", formatter), null, null));
			
		}
		tempsFinal = System.nanoTime();
		tempsEnNanosegons = tempsFinal - tempsInicial;
		tempsEnMilisegons = TimeUnit.MILLISECONDS.convert(tempsEnNanosegons, TimeUnit.NANOSECONDS);
		// A partir de java 9 hi ha: Duration.ofNanos(tempsEnNanosegons).toSeconds();
		System.out.println("Temps per a insertar " + numObjACrear + " waypoints en l'ArrayList: " + tempsEnMilisegons);
		 
		
		
		tempsInicial = System.nanoTime();
		for(int i = 0; i < numObjACrear; i++) {
			comprovacioRendimentTmp.llistaLinkedList.add(new Waypoint_Dades(0, "Òrbita de la Terra", coordenadesTmp, true, LocalDateTime.parse("15-08-1954 00:01", formatter), null, null));
		}
		tempsFinal = System.nanoTime();
		tempsEnNanosegons = tempsFinal - tempsInicial;
		tempsEnMilisegons = TimeUnit.MILLISECONDS.convert(tempsEnNanosegons, TimeUnit.NANOSECONDS);
		// A partir de java 9 hi ha: Duration.ofNanos(tempsEnNanosegons).toSeconds();
		System.out.println("Temps per a insertar " + numObjACrear + " waypoints en el LinkedList: " + tempsEnMilisegons);
		
		
		return comprovacioRendimentTmp; 
	}
	
	
	//3. Comprovar rendiment d'inserció en un ArrayList i en un LinkedList de waypoints.
	public static ComprovacioRendiment comprovarRendimentInsercio(ComprovacioRendiment comprovacioRendimentTmp) {
		long tempsInicial;
		long tempsFinal;
		int meitatLLista;
		
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		int[] coordenadesTmp = new int[] {0,0,0};
		
		
		meitatLLista = comprovacioRendimentTmp.llistaArrayList.size()/2;
		System.out.print("llistaArrayList.size(): " + comprovacioRendimentTmp.llistaArrayList.size() + ", meitatLLista: " + meitatLLista + "\n");
		
		
		//1.2. Inserció d'un nou waypoint en la 1a posició de les llistes.
		tempsInicial = System.nanoTime();
		comprovacioRendimentTmp.llistaArrayList.add(0, new Waypoint_Dades(0, "Òrbita de la Terra", coordenadesTmp, true, LocalDateTime.parse("15-08-1954 00:01", formatter), null, null));
		tempsFinal = System.nanoTime();
		System.out.print("Temps per a insertar 1 waypoint en la 1a posició de l'ArrayList: ");
		System.out.println(tempsFinal - tempsInicial); 
		
		tempsInicial = System.nanoTime();
		comprovacioRendimentTmp.llistaLinkedList.add(0, new Waypoint_Dades(0, "Òrbita de la Terra", coordenadesTmp, true, LocalDateTime.parse("15-08-1954 00:01", formatter), null, null));
		tempsFinal = System.nanoTime();
		System.out.print("Temps per a insertar 1 waypoint en la 1a posició del LinkedList: ");
		System.out.println(tempsFinal - tempsInicial); 
		System.out.println("------------");
		
		
		//1.3. Inserció d'un nou waypoint al mig de les llistes.
		tempsInicial = System.nanoTime();
		comprovacioRendimentTmp.llistaArrayList.add(meitatLLista, new Waypoint_Dades(0, "Òrbita de la Terra", coordenadesTmp, true, LocalDateTime.parse("15-08-1954 00:01", formatter), null, null));
		tempsFinal = System.nanoTime();
		System.out.print("Temps per a insertar 1 waypoint al mig (pos. " + meitatLLista + ") de l'ArrayList: ");
		System.out.println(tempsFinal - tempsInicial); 
		
		tempsInicial = System.nanoTime();
		comprovacioRendimentTmp.llistaLinkedList.add(meitatLLista, new Waypoint_Dades(0, "Òrbita de la Terra", coordenadesTmp, true, LocalDateTime.parse("15-08-1954 00:01", formatter), null, null));
		tempsFinal = System.nanoTime();
		System.out.print("Temps per a insertar 1 waypoint al mig (pos. " + meitatLLista + ") del LinkedList: ");
		System.out.println(tempsFinal - tempsInicial);
		System.out.println("------------");
		
		
		//1.4. Inserció d'un nou waypoint al final de les llistes.
		tempsInicial = System.nanoTime();
		comprovacioRendimentTmp.llistaArrayList.add(comprovacioRendimentTmp.llistaArrayList.size(), new Waypoint_Dades(0, "Òrbita de la Terra", coordenadesTmp, true, LocalDateTime.parse("15-08-1954 00:01", formatter), null, null));
		tempsFinal = System.nanoTime();
		System.out.print("Temps per a insertar 1 waypoint al final de l'ArrayList: ");
		System.out.println(tempsFinal - tempsInicial); 
		
		tempsInicial = System.nanoTime();
		comprovacioRendimentTmp.llistaLinkedList.add(comprovacioRendimentTmp.llistaLinkedList.size(), new Waypoint_Dades(0, "Òrbita de la Terra", coordenadesTmp, true, LocalDateTime.parse("15-08-1954 00:01", formatter), null, null));
		tempsFinal = System.nanoTime();
		System.out.print("Temps per a insertar 1 waypoint al final del LinkedList: ");
		System.out.println(tempsFinal - tempsInicial);
		
		return comprovacioRendimentTmp; 
	}
	
	

	//4. Modificació dels waypoints de l'ArrayList i del LinkedList.
	public static ComprovacioRendiment modificarWaypoints(ComprovacioRendiment comprovacioRendimentTmp) {
		int ultimaPosicio;
		Waypoint_Dades waypointTmp;
		
		
		List<Integer> idsPerArrayList = new ArrayList<Integer>();
		
		System.out.println("---- APARTAT 1 ----");
		for(int i = 0; i < comprovacioRendimentTmp.llistaArrayList.size(); i++) {
			idsPerArrayList.add(i);
		}
		System.out.println("S'ha inicialitzat la llista idsPerArrayList amb " + idsPerArrayList.size() + " elements.");
		System.out.println("El 1r element té el valor: " + idsPerArrayList.get(0));
		ultimaPosicio = idsPerArrayList.size() - 1;
		System.out.println("L'últim element té el valor: " + idsPerArrayList.get(ultimaPosicio));
		
		
		System.out.println();
		System.out.println("---- APARTAT 2 ----");
		for(int i = 0; i < idsPerArrayList.size(); i++) {
			System.out.println("ABANS DEL CANVI: comprovacioRendimentTmp.llistaArrayList.get(" + i + ").getId(): " + comprovacioRendimentTmp.llistaArrayList.get(i).getId());
			
			comprovacioRendimentTmp.llistaArrayList.get(i).setId(idsPerArrayList.get(i).intValue());
			
			System.out.println("DESPRÉS DEL CANVI: comprovacioRendimentTmp.llistaArrayList.get(" + i + ").getId(): " + comprovacioRendimentTmp.llistaArrayList.get(i).getId());
			System.out.println();
		}
		
		
		System.out.println();
		System.out.println("---- APARTAT 3.1 (bucle for) ----");
		for(Waypoint_Dades waypointTmp2 : comprovacioRendimentTmp.llistaArrayList) {
			System.out.println("ID = " + waypointTmp2.getId() + ", nom = " + waypointTmp2.getNom());
		}
	
		
		System.out.println();
		System.out.println("---- APARTAT 3.2 (Iterator) ----");
		Iterator <Waypoint_Dades> it = comprovacioRendimentTmp.llistaArrayList.iterator();
		while (it.hasNext()) {
			waypointTmp = it.next();
			// Amb hasNext() preguntem si hi ha elements que encara no hem recorregut.
			// El iterator inicialment apunta a fora (podríem dir que apunta a l'element de la posició -1).
			// Quan fem it.next() per 1a vegada, l'apuntador passa a apuntar a l'element de la posició 0.
			// Cada vegada que fem it.next() passem a apuntar al següent element.
			System.out.println("ID = " + waypointTmp.getId() + ", nom = " + waypointTmp.getNom());
		}
		
		
		System.out.println();
		System.out.println("---- APARTAT 4 ----");
		System.out.println("Preparat per esborrar el contingut de llistaLinkedList que té " + comprovacioRendimentTmp.llistaLinkedList.size() + " elements.");
		comprovacioRendimentTmp.llistaLinkedList.clear();
		System.out.println("Esborrada. Ara llistaLinkedList té " + comprovacioRendimentTmp.llistaLinkedList.size() + " elements.");
		comprovacioRendimentTmp.llistaLinkedList.addAll(comprovacioRendimentTmp.llistaArrayList);
		System.out.println("Copiats els elements de llistaArrayList en llistaLinkedList que ara té " + comprovacioRendimentTmp.llistaLinkedList.size() + " elements.");
		
		
		
		System.out.println();
		System.out.println("---- APARTAT 5.1 (bucle for) ----");
		int idTmp;
		for(Waypoint_Dades waypointTmp2 : comprovacioRendimentTmp.llistaArrayList) {
			idTmp = waypointTmp2.getId();
			// waypointTmp2 fa referència (apunta) al mateix objecte que el que està en la comprovacioRendimentTmp.llistaArrayList (el que ha trobat al fer el for()).
			
			if (waypointTmp2.getId() > 5) {
				waypointTmp2.setNom("Òrbita de Mart");
				System.out.println("Modificat el waypoint amb id = " + waypointTmp2.getId()); 
			}
		}
		
		System.out.println();
		System.out.println("---- APARTAT 5.1 (comprovació) ----");
		for(int i = 0; i < comprovacioRendimentTmp.llistaArrayList.size(); i++) {
			System.out.println("El waypoint amb id = " + comprovacioRendimentTmp.llistaArrayList.get(i).getId() + " té el nom = " + comprovacioRendimentTmp.llistaArrayList.get(i).getNom());
		}
		
		
		System.out.println();
		System.out.println("---- APARTAT 5.2 (Iterator) ----");
		it = comprovacioRendimentTmp.llistaLinkedList.iterator();
		while (it.hasNext()) {
			waypointTmp = it.next();
			
			if (waypointTmp.getId() < 5) {
				waypointTmp.setNom("Punt Lagrange entre la Terra i la LLuna");
				System.out.println("Modificat el waypoint amb id = " + waypointTmp.getId()); 
			}
		}

		System.out.println();
		System.out.println("---- APARTAT 5.2 (comprovació) ----");
		it = comprovacioRendimentTmp.llistaLinkedList.iterator();
		while (it.hasNext()) {
			waypointTmp = it.next();
			System.out.println("El waypoint amb id = " + comprovacioRendimentTmp.llistaArrayList.get(waypointTmp.getId()).getId() + " té el nom = " + comprovacioRendimentTmp.llistaArrayList.get(waypointTmp.getId()).getNom());
		}
		
		
		return comprovacioRendimentTmp; 
	}
	
	
	
	//5. Esborrar waypoints de l'ArrayList i del LinkedList.
	public static ComprovacioRendiment esborrarWaypoints(ComprovacioRendiment comprovacioRendimentTmp) {
		Waypoint_Dades waypointTmp;
		
		/*
		System.out.println();
		System.out.println("---- APARTAT 1 (bucle for) ----");
		int idTmp;
		for(Waypoint_Dades waypointTmp2 : comprovacioRendimentTmp.llistaArrayList) {
			idTmp = waypointTmp2.getId();
			// waypointTmp2 fa referència (apunta) al mateix objecte que el que està en la comprovacioRendimentTmp.llistaArrayList (el que ha trobat al fer el for()).
			
			if (waypointTmp2.getId() < 6) {
				comprovacioRendimentTmp.llistaArrayList.remove(idTmp);
				// Si esborrem un waypoint a llavors el for es descojona perquè no s'entera de que hi ha 1 element menys i peta tot.
				System.out.println("Esborrat el waypoint amb id = " + waypointTmp2.getId()); 
			}
		}
		
		System.out.println();
		System.out.println("---- APARTAT 1 (comprovació) ----");
		for(int i = 0; i < comprovacioRendimentTmp.llistaArrayList.size(); i++) {
			System.out.println("El waypoint amb id = " + comprovacioRendimentTmp.llistaArrayList.get(i).getId() + " té el nom = " + comprovacioRendimentTmp.llistaArrayList.get(i).getNom());
		}*/
		
		
		System.out.println();
		System.out.println("---- APARTAT 2 (Iterator) ----");
		Iterator <Waypoint_Dades> it = comprovacioRendimentTmp.llistaLinkedList.iterator();
		while (it.hasNext()) {
			waypointTmp = it.next();
			// Amb hasNext() preguntem si hi ha elements que encara no hem recorregut.
			// El iterator inicialment apunta a fora (podríem dir que apunta a l'element de la posició -1).
			// Quan fem it.next() per 1a vegada, l'apuntador passa a apuntar a l'element de la posició 0.
			// Cada vegada que fem it.next() passem a apuntar al següent element.

			if (waypointTmp.getId() > 4) {
				System.out.println("Esborrat el waypoint amb id = " + waypointTmp.getId()); 
				// La següent instrucció fa que el iterator es descojoni perquè no s'entera que s'ha esborrar 1 element.
				//comprovacioRendimentTmp.llistaLinkedList.remove(waypointTmp.getId());
				
				it.remove();
				// Esborrem l'element que estem apuntant.
			}
		}

		System.out.println();
		System.out.println("---- APARTAT 2 (comprovació) ----");
		it = comprovacioRendimentTmp.llistaLinkedList.iterator();
		while (it.hasNext()) {
			waypointTmp = it.next();
			System.out.println("El waypoint amb id = " + comprovacioRendimentTmp.llistaArrayList.get(waypointTmp.getId()).getId() + " té el nom = " + comprovacioRendimentTmp.llistaArrayList.get(waypointTmp.getId()).getNom());
		}
		
		
		System.out.println();
		System.out.println("---- APARTAT 3 (listIterator) ----");
		ListIterator <Waypoint_Dades> listIt = comprovacioRendimentTmp.llistaLinkedList.listIterator();
		while (listIt.hasNext()) {
			waypointTmp = listIt.next();
			// Amb hasNext() preguntem si hi ha elements que encara no hem recorregut.
			// El iterator inicialment apunta a fora (podríem dir que apunta a l'element de la posició -1).
			// Quan fem it.next() per 1a vegada, l'apuntador passa a apuntar a l'element de la posició 0.
			// Cada vegada que fem it.next() passem a apuntar al següent element.
			
			if (waypointTmp.getId() == 2) {
				System.out.println("Esborrat el waypoint amb id = " + waypointTmp.getId()); 
				listIt.remove();
			}
		}

		System.out.println();
		System.out.println("---- APARTAT 3 (comprovació) ----");
		listIt = comprovacioRendimentTmp.llistaLinkedList.listIterator(comprovacioRendimentTmp.llistaLinkedList.size() - 1);
		// El size() et dona la quantitat d'elements que hi ha, però les posicions que ocupen comencen per la 0 i no per la 1.
		// Si size() = 100 vol dir que hi ha 100 elements i que ocuparan des de la posició 0 fins la 99.
		while (listIt.hasPrevious()) {
			waypointTmp = listIt.previous();
			System.out.println("El waypoint amb id = " + waypointTmp.getId() + " té el nom = " + waypointTmp.getNom());
		}
		
		
		return comprovacioRendimentTmp; 
	}
	
	
	
	//6. Modificar coordenades i nom dels waypoints de l'ArrayList amb ID parell.
	public static ComprovacioRendiment modificarCoordenadesINomDeWaypoints(ComprovacioRendiment comprovacioRendimentTmp) {
		Waypoint_Dades waypointTmp;
		Scanner sc = new Scanner(System.in);
		String nomNou;
		String coordenadesNoves = null;
		int posicio;
		int numParamLLegits;
		int[] coordenadesTmp;
		boolean coordenadesCorrectes;
		
		
		Iterator<Waypoint_Dades> it = comprovacioRendimentTmp.llistaArrayList.iterator();
		
		while (it.hasNext()) {
			waypointTmp = it.next();
			// Amb hasNext() preguntem si hi ha elements que encara no hem recorregut.
			// El iterator inicialment apunta a fora (podríem dir que apunta a l'element de la posició -1).
			// Quan fem it.next() per 1a vegada, l'apuntador passa a apuntar a l'element de la posició 0.
			// Cada vegada que fem it.next() passem a apuntar al següent element.
			
			if (waypointTmp.getId() % 2 == 0) {
				//Se debe limpiar el buffer de entrada si se van a leer datos de tipo carácter a continuación de
		        //la lectura de datos numéricos.  La forma más sencilla de limpiar el buffer de entrada en Java
		        //es ejecutar la instrucción: sc.nextLine();
				
				System.out.println("------ Modificar el waypoint amb id = " + waypointTmp.getId() + " ------"); 
				
				System.out.println("Nom actual: " + waypointTmp.getNom());
				System.out.print("Nom nou: ");
				nomNou = sc.nextLine();
				waypointTmp.setNom(nomNou);
				
				coordenadesCorrectes = false;
				while (coordenadesCorrectes == false) {
					
					numParamLLegits = 0;
					while (numParamLLegits != 3) {
						System.out.println("Coordenades actuals: " + waypointTmp.getCoordenades()[0] + " " +  waypointTmp.getCoordenades()[1] + " " + waypointTmp.getCoordenades()[2]);
						System.out.print("Coordenades noves (format: 1 13 7): ");
						coordenadesNoves = sc.nextLine();
						
						numParamLLegits = coordenadesNoves.split(" ").length;
						//System.out.println("coordenadesNoves.split(\" \").length = " + coordenadesNoves.split(" ").length);
						if (numParamLLegits != 3) {
							System.out.println("ERROR: introduir 3 paràmentres separats per 1 espai en blanc. Has introduit " + numParamLLegits + " paràmetres.");	
						}
						
					}
					
					posicio = 0;
					coordenadesTmp = new int[] {0,0,0};		// coordenadesTmp és un objecte (perquè hem fet un nex()) i si el fem servir per a diversos
															// waypoints, a llavors estem fent servir una referència seva i no el propi objecte.
															// Això implica que hem de fer un nou new() per a cada waypoint, sinò estaran fent servir
															// el mateix objecte coordenadesTmp.
					for (String coordenada: coordenadesNoves.split(" ")) {

						if (Cadena.stringIsInt(coordenada)) {
							coordenadesTmp[posicio] = Integer.parseInt(coordenada);
							
							posicio++;
						} else {
							System.out.println("ERROR: coordenada " + coordenada + " no vàlida.");
						}
		            }	
					
					if (posicio == 3) {
						waypointTmp.setCoordenades(coordenadesTmp);
						
						coordenadesCorrectes = true;
					}
				}
				
				System.out.println();
			}
		}
		
		return comprovacioRendimentTmp;
	}
	
		
	
	//7. Visualitzar els waypoints de l'ArrayList ordenats.
	public static void visualitzarWaypointsOrdenats(ComprovacioRendiment comprovacioRendimentTmp) {
		ArrayList<Waypoint_Dades> llistaWaypointsOrdenats = new ArrayList<Waypoint_Dades>();
		

		llistaWaypointsOrdenats.addAll(comprovacioRendimentTmp.llistaArrayList);
		
		Collections.sort(llistaWaypointsOrdenats);
		
		for (Waypoint_Dades waypointTmp : llistaWaypointsOrdenats) {
			System.out.println(waypointTmp);
		}
	}
	
	
	
	//8. LListar els waypoints de l'ArrayList que es trobin a certa distància de la Terra.
	public static void waypointsACertaDistanciaMaxDeLaTerra(ComprovacioRendiment comprovacioRendimentTmp) {
		Scanner sc = new Scanner(System.in);
		String distanciaALaTerraString;
		int distanciaALaTerraInt;
		ArrayList<Waypoint_Dades> llistaWaypointsOrdenats = new ArrayList<Waypoint_Dades>();
		int distancia = 0;
		
		
		System.out.print("Distància màxima de la Terra: ");
		distanciaALaTerraString = sc.nextLine();
		while (Cadena.stringIsInt(distanciaALaTerraString) == false) {
			System.out.println("ERROR: la distància màxima de la Terra " + distanciaALaTerraString + " no és correcta.");
			
			System.out.print("Distància màxima de la Terra: ");
			distanciaALaTerraString = sc.nextLine();
		}
		
		distanciaALaTerraInt = Integer.parseInt(distanciaALaTerraString);

		llistaWaypointsOrdenats.addAll(comprovacioRendimentTmp.llistaArrayList);
		
		Collections.sort(llistaWaypointsOrdenats);
		
		for (Waypoint_Dades waypointTmp : llistaWaypointsOrdenats) {
			distancia = (int)Math.pow(waypointTmp.getCoordenades()[0], 2) + (int)Math.pow(waypointTmp.getCoordenades()[1], 2) + (int)Math.pow(waypointTmp.getCoordenades()[2], 2);

			if (distancia <= distanciaALaTerraInt) {
				System.out.println(waypointTmp);				
			}
		}		
	}
	
	
	
	
	
	

	
	
	//3. LListar els waypoints  de una ruta
	
	//5. Modificar un waypoint
	
	//17. LListar les rutes que tinguin un waypoint concret
	
	//13. LListar les rutes(amb tota la informació dels waypoints)
	
	//15. Crear una ruta
}
