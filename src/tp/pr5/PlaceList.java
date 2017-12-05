package tp.pr5;

import java.util.ArrayList;
import java.util.List;

/**
 * 
 * @author Antonio Nuñez Guerrero
 * 
 * Clase auxiliar (no viene en la documentación) para manejar algunas operaciones sin tener que capturar RunTimeException.
 * Se utiliza en la clase CityLoaderFromTxtFile, para cargar los diferentes place del fichero.
 *
 */
public class PlaceList {

	private List<Place> placeList;
	
	public PlaceList(){
		this.placeList = new ArrayList<Place>();
	}
	
	/**
	 * Añade el elemento que le pasamos por parámetro directamente.
	 * SUPONE QUE LOS PLACE SE PASAN POR ORDEN NUMÉRICO.
	 * @param item
	 * @return true si se ha añadido al placeList. False en caso contrario
	 */
	public boolean addPlace(Place place){
		
		return this.placeList.add(place);
	}
	
	/**
	 * Método que devuelve el place que ocupa la posición i pasada por parámetro
	 * @param i posicion del place en la lista
	 * @return Devuelve null en caso de que no se encuentre el item, el item en caso de que se encuentre.
	 */
	public Place getPlace(int i){
		Place place = null;
		
		if(i < numElems())
			place = this.placeList.get(i);
		
		return place;
	}
	
	
	/**
	 * Comprueba si el place con su nombre existe en el contenedor
	 * @param name - nombre del objeto a buscar
	 * @return true si existe en el contenedor un item con el mismo id
	 */
	/*public boolean containsPlace(String name){
		
		return this.getPlace(name)!=null;
	}*/
	
	public int numElems(){

		return this.placeList.size();
	}
}
