package tp.pr5.gui;

import javax.swing.JButton;

import tp.pr5.PlaceInfo;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * Represents a Place in the city on the Swing interface. It is a button, which name is the place name.
 *
 * A PlaceCell needs to store a reference to the place that it represents. However, this place should not be modified by the PlaceCell
 *
 * When the user clicks on a PlaceCell the CityPanel will show the place description if the Place was previously visited.
 */
@SuppressWarnings("serial")
public class PlaceCell extends JButton {
	private PlaceInfo place;
	
	public PlaceCell(){
		
	}

	/**
	 * Método que fija el PlaceInfo pasado por parámetro 
	 * @param place
	 */
	public void setPlace(PlaceInfo place) {
		this.place = place;
	}

	/**
	 * Devuelve el placeInfo
	 * @return
	 */
	public PlaceInfo getPlace() {
		return place;
	}
	
}
