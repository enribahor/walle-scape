package tp.pr5.messages;

import tp.pr5.Direction;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 */
public class Messages {

	//Instruccion Drop
	/**
	 * Si al buscar un objeto en un contenedor resulta que no esta
	 */
	public static String isNotInContainer(String id){
		return "You do not have any " + id +".";
	}
	
	/**
	 * Mensaje para cuando no podemos soltar un objeto en un place.
	 */
	public static String canNotDrop(String id){
		return "WALL·E says: This place already has " + id;
	}
	
	/**
	 * Mensaje para cuando se ha conseguido hacer un drop de un item
	 */
	public static String dropOk(String id){
		return "WALL·E says: Great! I have dropped " + id;
	}
	
	//Instruccion Pick
	/**
	 * Mensaje para cuando se hace pick de un objeto que ya tiene el robot
	 */
	public static String itemAlreadyExistWhenPick(String id){
		return "WALL·E says: I am stupid! I had already the object " + id;
	}
	
	/**
	 * Mensaje para cuando un item no se encuentra en el place
	 * @param id
	 * @return
	 */
	public static String itemNoExitsPick(String id){
		return "WALL·E says: Ooops, this place has not the object " + id;
	}
	
	/**
	 * Mensaje para cuando el pick se realiza correctamente
	 * @param id
	 * @return
	 */
	public static String pickOk(String id){
		return "WALL·E says: I am happy! Now I have " + id;
	}
	
	//Instruccion operate
	/**
	 * Mensaje para cuando no existe un item en el contenedor al hacer el operate
	 */
	public static String notExistItemOperate(){
		return "I have not such object";
	}
	
	/**
	 * Mensaje para cuando un item no se puede usar. Ej: una llave en una puerta que no le corresponde
	 * @return
	 */
	public static String canNotUseOperate(String id){
		return "WALL·E says: I have problems using the object " + id;
	}
	
	/**
	 * Mensaje para cuando un item no se puede usar ya que se ha gastado
	 */
	public static String spentItemOperate(String id){
		return "WALL·E says: What a pity! I have no more "+id+" in my inventory";
	}
	
	//Instruccion scan
	public static String itemDescriptionFuel(String id, String description, int power, int times){
		return "WALL·E says: " + id +
				": " + description + "// power = " + power + ", times = " + times;
	}
	
	public static String itemDescription(String id, String description){
		return "WALL·E says: " + id + ": " + description;
	}
	
	//Instruccion move
	/**
	 * Mensaje para cuando se cierra la aplicación o se llega a la nave espacial
	 */
	public static String byeByeMessage(){
		return "WALL·E says: I am at my spaceship. Bye bye";
	}
	
	public static String moveMessage(Direction dir){
		return "WALL·E says: Moving in direction " + dir;
	}
	
	/**
	 * Para preguntar antes de salir
	 * @return
	 */
	public static String areYouSureExit(){
		
		return "Are you sure you want to quit?";
	}
	
	//Instruccion turn
	/**
	 * Mensaje para cuando la direccion de rotación es incorrecta
	 */
	public static String rotationUnkwownTurn(){
		return "I do not understand. Please repeat";
	}
	
	/**
	 * Mensaje para cuando se ha girado el robot, indicar a que direccion esta mirando
	 */
	public static String currentHeadigTurn(Direction dir){
		 return "WALL·E is looking at direction " + dir ;
	}
	
	public static String deadMessage(){
		return "WALL·E says: I have not energy. I can not continue";
	}
	
	/**
	 * Mensaje para cuando se quiere mostrar el fuel y el material reciclado del robot
	 * @param fuel
	 * @param recycledMaterial
	 * @return
	 */
	public static String robotStateMessage(int fuel, int recycledMaterial){
		return "      * My power is " + fuel + "\n" 
			+ "      * My recycled material is " + recycledMaterial;
	}
	
	//Sintax messages
	/**
	 * Para cuando no se especifica el mapa
	 */
	public static String isNotMapFile(){
		return "Map file not specified";
	}
	
	public static String notSpecifiedInterface(){
		return "Interface not specified";
	}
	/**
	 * Para cuando la interfaz no es ni swing ni console
	 * @return
	 */
	public static String wrongInterface(){
		return "Wrong type of interface";
	}
	
	//Mensajes específicos del InfoPanel
	/**
	 * Para cambiar la etiqueta del PanelInfo y decir que los atributos del robot se han actualizado
	 */
	public static String attributesUpdated(int fuel, int recycledMaterial){
		return "Robot attributes has been updated: (" + fuel + ", " + recycledMaterial + ")";
	}
	
}
