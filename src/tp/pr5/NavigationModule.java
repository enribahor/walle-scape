package tp.pr5;


import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.items.Item;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * This class is in charge of the robot navigation features. It contains the city where the robot looks for garbage, 
 * the current place where the robot is, and the current direction of the robot. It contains methods to handle the 
 * different robot movements and to pick and drop items at the current place.
 */
public class NavigationModule extends Observable<NavigationObserver> {

	private City cityMap;
	private Place currentPlace;
	private Direction currentDirection;
	private List<Direction> directionList;//Lista con las direcciones North, South...
	boolean ejec; //true gui, false consola
	
	public NavigationModule(City aCity, Place initialPlace){
		this.cityMap = aCity;
		this.currentPlace = initialPlace;
		this.directionList = new ArrayList<Direction>();
		this.directionList.add(Direction.NORTH);
		this.directionList.add(Direction.EAST);
		this.directionList.add(Direction.SOUTH);
		this.directionList.add(Direction.WEST);
	}
	
	/**
	 * Método que comprueba si el place en el q esta WALL·E es su nave espacial.
	 * @return un booleano que indica si es la nave espacial o no
	 */
	public boolean atSpaceship(){
		
		return this.currentPlace.isSpaceship();
	}
	
	/**
	 * Método que devuelve la calle a la que está mirando WALL·E
	 * @return la calle a la que está mirando
	 */
	public Street getHeadingStreet(){
		return this.cityMap.lookForStreet(this.currentPlace, 
				this.currentDirection);
	}
	
	/**
	 * Inicializa la direccion inicial a la que esta mirando de acuerdo con el parámetro
	 * @param heading - dirección a la que queremos que mire inicialmente
	 */
	public void initHeading(Direction heading){
	
		this.currentDirection = heading;
	}
	
	/**
	 * Método para hacer que WALL·E gire hacia la dirección pasada por parámetro.
	 * @param inst con la dirección a la que debe girar WALL·E
	 */
	public void rotate(Rotation rotation){
		Direction d = null;
		
		if(rotation == Rotation.RIGHT){
			d = turnRight();
		}else if(rotation == Rotation.LEFT){
			d = turnLeft();
		}
		
		this.currentDirection = d;
	}
	
	/**
	 * Método que hace girar a la derecha a WALL.E. Utiliza un ListIetrator, que inicialmente apunta al elemento
	 * de la lista que sea igual a currentDirection, para para recorrer la lista (directionList)hacia delante. 
	 * Cuando el iterador llega a la última posicion de la lista (WEST) hacemos que el iterador apunte al primer 
	 * elemento (NORTH).
	 * @return La direccion hacia la que ha girado WALL·E.
	 */
	private Direction turnRight(){
		Direction d;
		ListIterator<Direction> it = this.directionList.listIterator(this.currentDirection.ordinal() + 1);
		if(!it.hasNext()){
			it = this.directionList.listIterator(0);
			d= it.next();
		}else{
			d = it.next();
		}
		
		return d;
	}
	
	/**
	 * Método que hace girar a la izquierda a WALL.E. Utiliza un ListIetrator, que inicialmente apunta al elemento
	 * de la lista que sea igual a currentDirection, para para recorrer la lista (directionList)hacia atrás. 
	 * Cuando el iterador llega a la primera posicion de la lista (NORTH) hacemos que el iterador apunte al último 
	 * elemento (WEST).
	 * @return La direccion hacia la que ha girado WALL·E.
	 */
	private Direction turnLeft(){
		Direction d;
		ListIterator<Direction> it = this.directionList.listIterator(this.currentDirection.ordinal());
		if(!it.hasPrevious()){
			it = this.directionList.listIterator(4);
			d = it.previous();
		}
		else{
			d = it.previous();
		}
		return d;
	}
	
	/**
	 * Intenta coger un elemento del place definido por su identificador. Si la accion fue completada entonces
	 * lo elimina del contenedor de place
	 * @param id - identificador del objeto
	 * @return El item si éste existe en el place, null en cc
	 */
	public Item pickItemFromCurrentPlace(String id){
		//Intentamos coger el item
		Item it = this.currentPlace.pickItem(id);
		/*if(it != null)
			this.currentPlace.getContainerItem().getContainer().remove(id);*/
		return it;
	}
	
	/**
	 * Método que borra un item en el lugar en el que esta presente WALL·E
	 */
	public void dropItemAtCurrentPlace(Item it){
		this.currentPlace.dropItem(it);
	}
	
	/**
	 * Comprueba si hay un objeto en el place con el mismo id
	 * @param id - para comprobar si esta en place
	 * @return true si ese objeto esta en place, false en cc
	 */
	public boolean findItemAtCurrentPlace(String id){
		return this.currentPlace.getContainerItem().getItem(id) != null;
	}
	
	/**
	 * Método que imprime por pantalla la información de los objetos relativa a cuando se ejecuta la instrucción scan
	 */
	public void scanCurrentPlace(){
		System.out.println(this.currentPlace.getContainerItem().toString());
	}
	
	/**
	 * Método que devuelve la dirección a la que actualmente se encuentra mirando WALL·E
	 * @return direccion actual
	 */
	public Direction getCurrentHeading(){
		return this.currentDirection;
	}
	
	/**
	 * Este método sirve para mover al robot a la siguiente lugar. si el movimiento no es posible porque 
	 * no hay calle o la calle esta cerrada entonces lanza una excepción. En otro caso el lugar inicial 
	 * es cargado de acuerdo con el movimiento.
	 * @throws InstructionExecutionException
	 * @return - String con el mensaje correspondiente
	 */
	public void move() throws InstructionExecutionException{
		Place nextPlace = null;
		Street st = this.cityMap.lookForStreet(this.currentPlace, this.currentDirection);
		
		if(st == null){
			throw new InstructionExecutionException("WALL·E says: There is no street in direction "+
					this.currentDirection);
		}
		//Si se ha encontrado el lugar y la calle está abierta
		else if( st != null && !this.getHeadingStreet().isOpen()){
				throw new InstructionExecutionException("WALL·E says: Arrggg, there is a street but it is closed!");
		//Si se ha encontrado el lugar y la calle está abierta y la ejecucion es por consola
		}else if (st != null && this.getHeadingStreet().isOpen() && !this.ejec){
			//System.out.println("WALL·E says: Moving in direction " + this.currentDirection);
			//Asignamos a nextPlace el lugar al que se acaba de mover WALL·E
			nextPlace= st.nextPlace(this.currentPlace);
			//Utilizamos nextPlace para asignarselo a this.currentPlace porque ahora es el lugar inicial
			this.currentPlace = nextPlace;
			//Avisamos a la vista de que el robot ha llegado a otro lugar
			this.requestRobotArrivesAtPlace();
		}
	}
	
	/**
	 * 
	 * @return un booleano que indica si se esta ejecutando por GUI (true) o por consola(false)
	 */
	public boolean isEjec() {
		return ejec;
	}
	
	/**
	 * 
	 * @return El Place actual de WALL·E
	 */
	public Place getCurrentPlace() {
		return currentPlace;
	}

	/**
	 * Método que sirve para figar el atributo ejec al ejec pasado por parámetro
	 * @param ejec
	 */
	public void setEjec(boolean ejec) {
		this.ejec = ejec;
	}
	
	/**
	 * Método que sirve para fijar el atributo currentPlace al currentPlace pasado por parámetro
	 * @param currentPlace
	 */
	public void setCurrentPlace(Place currentPlace) {
		this.currentPlace = currentPlace;
	}
	
	/**
	 * Informa a la vista que la direccion a la que estaba mirando el robot ha cambiado
	 * @param direction - dirección actual a la que esta mirando el robot
	 */
	public void requestHeadingChanged(Direction direction){
		for(Iterator<NavigationObserver> it = this.iterator(); it.hasNext(); ){
			it.next().headingChanged(direction);
		}
	}
	
	/**
	 * Informa a la vista que se va a inicializar el navigation module.
	 */
	public void requestInitNavigationModule(){
		for(Iterator<NavigationObserver> it = this.iterator(); it.hasNext(); ){
			//Inicializar el navigation module del robot
			it.next().initNavigationModule(currentPlace, currentDirection);
		}
	}

	/**
	 * Informa a la vista que el lugar a cambiado porque o bien se ha cogido un item o bien se ha soltado un item
	 */
	public void requestPlaceHasChanged() {
		// TODO Auto-generated method stub
		for(Iterator<NavigationObserver> it = this.iterator(); it.hasNext(); ){
			it.next().placeHasChanged(this.currentPlace);
		}
	}
	
	/**
	 * Notifica que el robot ha llegado a un lugar
	 */
	public void requestRobotArrivesAtPlace(){
		for(Iterator<NavigationObserver> it = this.iterator(); it.hasNext(); ){
			it.next().robotArrivesAtPlace(this.currentDirection, this.currentPlace);
		}
	}
	
	/**
	 * Notifica de que el robot ha hecho un radar en un lugar
	 */
	public void requestPlaceScanned(){
		for(Iterator<NavigationObserver> it = this.iterator(); it.hasNext(); ){
			it.next().placeScanned(this.currentPlace);
		}
	}
}
