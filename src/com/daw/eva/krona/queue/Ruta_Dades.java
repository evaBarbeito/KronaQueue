package com.daw.eva.krona.queue;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

public class Ruta_Dades {
	private int id;                             	//Clau primaria. Es crea automàticament pel sistema i és intocable.
	private String nom;
	private ArrayList<Integer> waypoints;       	//Conté una llista de a tots els waypoints que formen part de la ruta.
	private boolean actiu;                      	//TRUE si està activa.
	private LocalDateTime dataCreacio;
	private LocalDateTime dataAnulacio;              //Quan actiu passi a valdre FALSE.
	private LocalDateTime dataModificacio;
    
    
	public Ruta_Dades(int id, String nom, ArrayList<Integer> waypoints, boolean actiu, LocalDateTime dataCreacio,
			LocalDateTime dataAnulacio, LocalDateTime dataModificacio) {
		this.id = id;
		this.nom = nom;
		this.waypoints = waypoints;
		this.actiu = actiu;
		this.dataCreacio = dataCreacio;
		this.dataAnulacio = dataAnulacio;
		this.dataModificacio = dataModificacio;
	}
	

	@Override
    public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
        
        return "RUTA " + id + ":" + 
                "\n   nom = " + nom +
                "\n   actiu = " + actiu +
                "\n   dataCreacio = " + dataCreacio.format(formatter) +
                "\n   dataAnulacio = " + dataAnulacio.format(formatter) +
                "\n   dataModificació = " + dataModificacio.format(formatter) + 
                "\n   waypoints: = " + waypoints ;
    }
    
}
