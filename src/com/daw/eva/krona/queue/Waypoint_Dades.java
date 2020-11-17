package com.daw.eva.krona.queue;

import java.io.Serializable;
import java.text.Collator;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Locale;

public class Waypoint_Dades implements Comparable<Waypoint_Dades>, Serializable {
	private int id;                     //Clau primaria. Es crea automàticament pel sistema i és intocable.
	private String nom;
	private int[] coordenades;
	private boolean actiu;              //TRUE si està actiu i es pot fer servir per afegir-lo a alguna ruta.
	private LocalDateTime dataCreacio;
	private LocalDateTime dataAnulacio;      //Quan actiu passi a valdre FALSE.
	private LocalDateTime dataModificacio;
    
    
	public Waypoint_Dades(int id, String nom, int[] coordenades, boolean actiu, LocalDateTime dataCreacio,
			LocalDateTime dataAnulacio, LocalDateTime dataModificacio) {
		this.id = id;
		this.nom = nom;
		this.coordenades = coordenades;
		this.actiu = actiu;
		this.dataCreacio = dataCreacio;
		this.dataAnulacio = dataAnulacio;
		this.dataModificacio = dataModificacio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public int[] getCoordenades() {
		return coordenades;
	}

	public void setCoordenades(int[] coordenades) {
		this.coordenades = coordenades;
	}


	@Override
    public String toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
		String cadenaAImprimir;
		int distancia = 0;
		

		distancia = (int)Math.pow(this.getCoordenades()[0], 2) + (int)Math.pow(this.getCoordenades()[1], 2) + (int)Math.pow(this.getCoordenades()[2], 2);
		
		cadenaAImprimir = "WAYPOINT " + id + ":" + 
                "\n   nom = " + nom +
                "\n   coordenades(x, y, z) = (" + coordenades[0] + "," + coordenades[1] + "," + coordenades[2] + ") (distància = " + distancia + ")" +
                "\n   actiu = " + actiu;
		
        if (dataCreacio == null) {
        	cadenaAImprimir = cadenaAImprimir + "\n   dataCreacio = NULL";
        } else {
        	cadenaAImprimir = cadenaAImprimir + "\n   dataCreacio = " + dataCreacio.format(formatter);
        }
        
        if (dataAnulacio == null) {
        	cadenaAImprimir = cadenaAImprimir + "\n   dataAnulacio = NULL";
        } else {
        	cadenaAImprimir = cadenaAImprimir + "\n   dataAnulacio = " + dataCreacio.format(formatter);
        }
        
        if (dataModificacio == null) {
        	cadenaAImprimir = cadenaAImprimir + "\n   dataModificacio = NULL";
        } else {
        	cadenaAImprimir = cadenaAImprimir + "\n   dataModificacio = " + dataModificacio.format(formatter);
        }

        return cadenaAImprimir;
    }


	// Per a saber si 2 waypoints són iguals s'han de comparar les seves coordenades i si són iguals, a llavors, el seu nom. 
	@Override
	public int compareTo(Waypoint_Dades o) {
		int result = 0;
		int distanciaM1 = 0;
        int distanciaM2 = 0;

		if (Arrays.equals(this.getCoordenades(), o.getCoordenades())) {
			// Arrays.equals(int[] a, int[] b) retorna TRUE si a i b són iguals i sinó FALSE.
			
			// result = this.getNom().compareTo(o.getNom());
			// El problema de fer servir el compareTo() és que falla quan 2 paraules només es diferencien perquè una porta accent 
			// o està en majúscules.
			//
			// result = 0 si this.getNom() i o.getNom() són iguals.
			// result = -1 si this.getNom() és diferent a o.getNom() i anterior alfabèticament.
			// result = 1 si this.getNom() és diferent a o.getNom() i posterior alfabèticament.
			
			//Anem a ordenar alfabèticament independenment de si hi ha majúscules o accents.
			Collator tertiaryCollator = Collator.getInstance(new Locale("es"));
            tertiaryCollator.setStrength(Collator.TERTIARY);
            result = tertiaryCollator.compare(this.getNom(), o.getNom());
            //tertiaryCollator retorna valors tal com o fa el compareTo().
		} else {
			// Com que les dues coordenades són diferents, hem de determinar quina és la més petita per a poder ordenar-les
			// de més petita a més gran.
			// Simularem que la suma dels quadrats dels 3 números és la distància fins la Terra. Qui tingui el número més
			// petit estarà més a prop (-1) i qui tingui el número més gran més allunyat (1).
			distanciaM1 = (int)Math.pow(this.getCoordenades()[0], 2) + (int)Math.pow(this.getCoordenades()[1], 2) + (int)Math.pow(this.getCoordenades()[2], 2);
			distanciaM2 = (int)Math.pow(o.getCoordenades()[0], 2) + (int)Math.pow(o.getCoordenades()[1], 2) + (int)Math.pow(o.getCoordenades()[2], 2);
			
			if (distanciaM1 < distanciaM2){
				result = -1;						// result = -1 si volem que this.coordenades sigui anterior a o.coordenades .
            } else {
                if (distanciaM1 > distanciaM2) {
                	result = 1;						// result = 1 si volem que this.coordenades sigui posterior a o.coordenades .
                } else {
                	result = 0;						// result = 0 si volem que this.coordenades i o.coordenades siguin iguals.
                }
            }
		}
		
		return result;
	}
  
    
}
