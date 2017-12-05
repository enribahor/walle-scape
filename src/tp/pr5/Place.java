package tp.pr5;

import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * It represents a place in the city. Places are connected by streets according to the 4 compass directions, North, East, South and West. Every place has a name and a textual description about itself. This description is displayed when the robot arrives at the place.
 * A place can represent the spaceship where the robot is safe. When the robot arrives at this place, the application is over.
 */
public class Place implements PlaceInfo {
	private String name;
	private boolean isSpaceShip;
	private String description;
	private ItemContainer containerItem;

	public Place(String name, boolean isSpaceShip, String description){
		this.name= name;
		this.isSpaceShip = isSpaceShip;
		this.description = description;
		this.containerItem = new ItemContainer();
	}
	
	/**
	 * Metodo que devuelve un string con el nombre y la descripción del lugar
	 * @return c - string con la descripciñon del lugar 
	 */
	public String toString(){
		String c;
		c = this.name + "\n" + this.description + "\n";
		
		if(this.containerItem.getContainer().size() == 0)
			c = c + "The place is empty. There are no objects to pick\n";
		else{
			c = c + "The place contains these objects:\n";
			for (int i=0; i<this.containerItem.getContainer().size(); i++){
				c = c + "   " + this.containerItem.getContainer().get(i).getId() + "\n";
			}
		}
		return c;
	}
	
	/**
	 * Método que intenta añadir un item al contenedor de Place.
	 * @param it
	 * @return true si se ha añadido, false en caso contrario.
	 */
	public boolean addItem(Item it){
		return this.containerItem.addItem(it);
	}
	
	/**
	 * Método que llama al pickItem del containerItem
	 * @param id
	 * @return true si el metétodo llamado devuelve true.
	 */
	public Item pickItem(String id){
		return this.containerItem.pickItem(id);
	}
	
	/**
	 * Comprueba si un item esta en place
	 * @param id - identificador del objeto a buscar
	 * @return true si y solo si el place contiene el item identificado por id
	 */
	public boolean existItem(String id){
		
		return this.containerItem.getItem(id) != null;
	}
	
	/**
	 * Suelta un item en el ItemContainer de place. Si falla devuelve false
	 * @param it - El nombre del item a soltar
	 * @return true si y solo si se ha soltado en el place, falso cuando el item a soltar ya exista en el place
	 */
	public boolean dropItem(Item item){
		return this.containerItem.addItem(item);
	}
	//Getters y setters
	/**
	 * 
	 * @return El nombre del place
	 */
	public String getName() {
		return name;
	}
	
	/**
	 * 
	 * @return La descripción del Place
	 */
	public String getDescription() {
		return description;
	}
	
	/**
	 * 
	 * @return El ContainerItem del Place
	 */
	public ItemContainer getContainerItem() {
		return containerItem;
	}
	
	/**
	 * 
	 * @return Un booleano que indica si el Place es una nave espacial o no
	 */
	public boolean isSpaceship() {
		return this.isSpaceShip;
	}

	/**
	 * Método que sirve para fijar el atributo name al valor del name pasado por parámetro
	 * @param name 
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Método que sirve para fijar el atributo isSpaceShip al valor del isSpaceShip pasado por parámetro
	 * @param isSpaceShip
	 */
	public void setSpaceShip(boolean isSpaceShip) {
		this.isSpaceShip = isSpaceShip;
	}
	
	/**
	 * Método que comprueba si el objeto de clase place es igual que el objeto place pasado por parametro
	 * Se hace un casting para transformarlo a item
	 * @param place
	 * @retrun True si el id del Item de clase es igual al id del Item pasado por parámetro.
	 */
	public boolean equals(Place place){
		
		return this.name.equals(place.getName());
		
	}

	/**
	 * Método que compara el objeto de clase place con el objeto pasado por parámetro place según su nombre
	 * Se hace un casting para transformarlo a item
	 * Ignora las mayúsculas
	 * @param anObject
	 * @return -1 si this.name < anObject.name
	 * 			1 si this.name > anObject.name
	 * 			0 si this.name = anObject.name
	 */
	public int compareTo(Place place) {
		// TODO Auto-generated method stub
		
		return this.name.compareTo(place.getName());
	}
}
