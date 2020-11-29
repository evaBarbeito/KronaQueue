package com.daw.eva.krona.queue;

import Varies.Data;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.time.LocalDateTime;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;



public class Ruta {
	
	public static List<Waypoint_Dades> crearRutaInicial() {
		List<Waypoint_Dades> llistaWaypointLinkedList = null;
		
		
		llistaWaypointLinkedList = new LinkedList<Waypoint_Dades>();
		
		llistaWaypointLinkedList.add(new Waypoint_Dades(0, "Òrbita de la Terra", new int[] {0,0,0}, true, LocalDateTime.parse("21-10-2020 01:10", Data.formatter), null, LocalDateTime.parse("22-10-2020 23:55", Data.formatter)));
		llistaWaypointLinkedList.add(new Waypoint_Dades(1, "Punt Lagrange Terra-LLuna", new int[] {1,1,1}, true, LocalDateTime.parse("21-10-2020 01:00", Data.formatter), null, LocalDateTime.parse("22-10-2020 23:55", Data.formatter)));
		llistaWaypointLinkedList.add(new Waypoint_Dades(2, "Òrbita de la LLuna", new int[] {2,2,2}, true, LocalDateTime.parse("21-10-2020 00:50", Data.formatter), null, LocalDateTime.parse("22-10-2020 23:55", Data.formatter)));
		llistaWaypointLinkedList.add(new Waypoint_Dades(3, "Òrbita de Mart", new int[] {3,3,3}, true, LocalDateTime.parse("21-10-2020 00:40", Data.formatter), null, LocalDateTime.parse("22-10-2020 23:55", Data.formatter)));
		llistaWaypointLinkedList.add(new Waypoint_Dades(4, "Òrbita de Júpiter", new int[] {4,4,4}, true, LocalDateTime.parse("21-10-2020 00:30", Data.formatter), null, LocalDateTime.parse("22-10-2020 23:55", Data.formatter)));
		llistaWaypointLinkedList.add(new Waypoint_Dades(5, "Punt Lagrange Júpiter-Europa", new int[] {5,5,5}, true, LocalDateTime.parse("21-10-2020 00:20", Data.formatter), null, LocalDateTime.parse("22-10-2020 23:55", Data.formatter)));
		llistaWaypointLinkedList.add(new Waypoint_Dades(6, "Òrbita de Europa", new int[] {6,6,6}, true, LocalDateTime.parse("21-10-2020 00:10", Data.formatter), null, LocalDateTime.parse("22-10-2020 23:55", Data.formatter)));
		llistaWaypointLinkedList.add(new Waypoint_Dades(7, "Òrbita de Venus", new int[] {7,7,7}, true, LocalDateTime.parse("21-10-2020 00:01", Data.formatter), null, LocalDateTime.parse("22-10-2020 23:55", Data.formatter)));
		
		return llistaWaypointLinkedList;
	}
	
	
	
	
	//10. Inicialitzar una ruta.
	// Hem de recordar d'executar el menú 1 que és el que crida al constructor de ComprovacioRendiment perquè així es creii "pilaWaypoints"
	// quan s'executa "this.pilaWaypoints = new ArrayDeque<Waypoint_Dades>();" en el constructor de la classe ComprovacioRendiment.
	public static ComprovacioRendiment inicialitzarRuta(ComprovacioRendiment comprovacioRendimentTmp) {
		List<Waypoint_Dades> llistaWaypointLinkedList;
		Waypoint_Dades wtmp;
		
		
		//comprovacioRendimentTmp = serialitzarRuta(comprovacioRendimentTmp);
		//llistaWaypointLinkedList = deserialitzarRuta();
		llistaWaypointLinkedList = crearRutaInicial();
		
		for (Waypoint_Dades waypointTmp : llistaWaypointLinkedList) {
			comprovacioRendimentTmp.pilaWaypoints.push(waypointTmp);
		}
		
		wtmp = new Waypoint_Dades(4, "Òrbita de Júpiter REPETIDA", new int[] {4,4,4}, true, LocalDateTime.parse("21-10-2020 00:30", Data.formatter), null, LocalDateTime.parse("22-10-2020 23:55", Data.formatter));
		comprovacioRendimentTmp.pilaWaypoints.addFirst(wtmp);   // a sobre la pila, sobre el 7
		comprovacioRendimentTmp.wtmp = wtmp;
		
		
		return comprovacioRendimentTmp;
	}
	
	
	//11. Visualitzar una ruta.
	public static void visualitzarRuta(ComprovacioRendiment comprovacioRendimentTmp) {
		System.out.println("La ruta està formada pels waypoints:");
		
		for (Waypoint_Dades waypointTmp : comprovacioRendimentTmp.pilaWaypoints) {
			System.out.println(waypointTmp.toString());   //llegeix de damunt la pila cap a sota, de final a davant d'una cua-llista...
		}
		System.out.println("----------11bis---------");
		System.out.println(comprovacioRendimentTmp.pilaWaypoints);
	}
	
	
	//12. Invertir una ruta i visualitzar-la amb toString().
	public static void invertirRuta(ComprovacioRendiment comprovacioRendimentTmp) {		
		//Cuando usamos una Deque como pila (stack). Només es poden fer servir els mètodes d'una pila (push, pop,...).
		Deque<Waypoint_Dades> pilaWaypointsInversa = new ArrayDeque<Waypoint_Dades>();
		
        while (!comprovacioRendimentTmp.pilaWaypoints.isEmpty()) {
        	pilaWaypointsInversa.push(comprovacioRendimentTmp.pilaWaypoints.pop());
        	
        	// Hem buidat comprovacioRendimentTmp.pilaWaypoints per culpa dels pop() que li hem fet.
        }
        
        while (!pilaWaypointsInversa.isEmpty()) {
        	System.out.println(pilaWaypointsInversa.pop());
        }
        
        //System.out.println("pilaWaypointsInversa.toString(): \n" + pilaWaypointsInversa.toString());
	}
	
	
	//13. Accedir a un waypoint concret de la pila.
	// Hem de tornar a generar la ruta, menú 10 si hem executat el menú 12.
	public static void existeixWaypointEnRuta(ComprovacioRendiment comprovacioRendimentTmp) {
		//Iterator it = comprovacioRendimentTmp.pilaWaypoints.descendingIterator();
		Waypoint_Dades wtmp;
		boolean isInQueue = false;
		Waypoint_Dades wp;

		
		if (comprovacioRendimentTmp.pilaWaypoints.size() == 0) {
			System.out.println("La ruta està buida.");
		} else {
			if (comprovacioRendimentTmp.pilaWaypoints.contains(comprovacioRendimentTmp.wtmp)) {
				System.out.println("SI hem trobat el waypoint " + comprovacioRendimentTmp.wtmp.getNom() + " emmagatzemat en comprovacioRendimentTmp.wtmp, en la llista.");
			} else {
				System.out.println("NO hem trobat el waypoint " + comprovacioRendimentTmp.wtmp.getNom() + " emmagatzemat en comprovacioRendimentTmp.wtmp, en la llista.");
			}
			
			
			//Si sobre-escrivim el mètode equals, contains i equals, a sota, trobaran el nou punt wtmp. Sino, donarà fals, sempre, el  mètode contains... cal sobre-escriure'l
			
			wtmp = new Waypoint_Dades(4, "Òrbita de Júpiter Ruepe", new int[] {4,4,4}, true, LocalDateTime.parse("21-10-2020 00:30", Data.formatter), null, LocalDateTime.parse("22-10-2020 23:55", Data.formatter));
			if (comprovacioRendimentTmp.pilaWaypoints.contains(wtmp)) {
				System.out.println("SI hem trobat el waypoint " + wtmp.getNom() + " creat ara mateix, en la llista.");
			} else {
				System.out.println("NO hem trobat el waypoint " + wtmp.getNom() + " creat ara mateix, en la llista.");
			}
			
			isInQueue = false;
			// Do the same with the new wtmp waypoint
			while ((!isInQueue) && (!comprovacioRendimentTmp.pilaWaypoints.isEmpty())) {
				wp = comprovacioRendimentTmp.pilaWaypoints.pop();
				if (wp.equals(wtmp)) {
					isInQueue = true;
				}
			}
			if (isInQueue) {
				System.out.println("El waypoint wtmp està a la cua.");
			} else {
				System.out.println("El waypoint wtmp no està a la cua.");
			}

		}
		
		//pilaWaypoints.
		
	}
	

}
