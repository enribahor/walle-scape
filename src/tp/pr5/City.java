package tp.pr5;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * This class represents the city where the robot is wandering. It contains information about the streets 
 * and the places in the city
 */
public class City {

	private ArrayList<Street> cityMap;
	private int numStreet;

	//Constructora por defecto
	public City(){
		this.cityMap = new ArrayList<Street>();
	}
	
	public City(Street[] cityMap){
		this.cityMap = new ArrayList<Street>(Arrays.asList(cityMap));
	}
	
	/**
	 * Busca en el mapa de la ciudad la calle que conecta el lugar en el que esta WALL·E y la dirección
	 * a la que esta mirando.
	 * Devuelve null en el caso de que no encuentre dicha calle.
	 */
	public Street lookForStreet(Place currentPlace, Direction currentHeading){
		int i =0;
		boolean enc = false;
		Street st = null;
		
		while(!enc && i < this.cityMap.size()){
			if( this.cityMap.get(i).comeOutFrom(currentPlace, currentHeading)){
				st = this.cityMap.get(i);
				enc=true;
			}
			else 
				i++;
		}
		return st;
	}
	
	/**
	 * Añade una calle a cityMap
	 * @param street - Calle a añadir
	 */
	public void addStreet(Street street){
		this.cityMap.add(street);
		this.numStreet++;
	}

	/**
	 * 
	 * @return Un ArrayList de Street
	 */
	public ArrayList<Street> getCityMap() {
		return cityMap;
	}
	
	/**
	 * 
	 * @return Un entero con el numero de la calle
	 */
	public int getNumStreet() {
		return numStreet;
	}

	/**
	 * Método para fijar al atributo cityMap el parámetro cityMap
	 * @param cityMap
	 */
	public void setCityMap(ArrayList<Street> cityMap) {
		
		this.cityMap = cityMap;
	}
	
	/**
	 * Método para fijar al atributo numStreet el parámetro numStreet
	 * @param numStreet
	 */
	public void setNumStreet(int numStreet) {
		this.numStreet = numStreet;
	}
}
