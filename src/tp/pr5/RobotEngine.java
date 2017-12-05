package tp.pr5;

import java.util.Iterator;
import java.util.List;

import tp.pr5.Interpreter;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * This class represents the robot engine. It controls robot movements by processing the instructions provided by the 
 * controllers. The engine stops when the robot arrives at the space ship, runs out of fuel or receives a quit instruction.
 * The robot engine is also responsible for updating the fuel level and the recycled material according to the actions 
 * that the robot performs in the city.
 * The robot engine contains an inventory, where the robot stores the items that it collects from the city
 */
public class RobotEngine extends Observable<RobotEngineObserver>{
	
	private Direction currentDirection;
	private City cityMap;
	private int engine;
	private int rMaterial;
	private ItemContainer containerItem;
	private NavigationModule navigation;
	private boolean quit;

	public RobotEngine(City cityMap, Place initialPlace, Direction dir){
		this.currentDirection = dir;
		this.cityMap = cityMap;
		this.engine = 100;
		this.rMaterial = 0;
		this.containerItem = new ItemContainer();
		this.navigation = new NavigationModule(cityMap, initialPlace);
		this.navigation.initHeading(dir);
		this.quit=false;
		//this.robotPanel = new RobotPanel(this);
	}

	
	/**
	 * Metodo privado que genera y ejecuta una instrucción creada a partir de un string que recibe como parámetro
	 * @param line - representa una instruccion mas la id de un item
	 * @param ejec - para saber si se ejecuta por consola(false) o por gui(true)
	 * 
	 */
	public void generarYEjecutarInstruccion(String line){
		Instruction inst;
		try {
			inst = Interpreter.generateInstruction(line);
			this.communicateRobot(inst);
			
		} catch (WrongInstructionFormatException e) {
			// TODO Auto-generated catch block
			requestError(e.getMessage());
		}
	}
	
	
	/**
	 * 
	 * @return la dirección actual de WALL·E
	 */
	public Direction getCurrentDirection() {
		return currentDirection;
	}

	/**
	 * 
	 * @return el mapa con todas las calles
	 */
	public City getCityMap(){
		return cityMap;
	}
	

	/**
	 * 
	 * @return El contenedor de objetos de WALL·E
	 */
	public ItemContainer getContainerItem(){
		return containerItem;
	}
	
	
	/**
	 * 
	 * @return El lugar actual en el que se encuentra WALL·E
	 */
	public Place getCurrentPlace() {
		return this.navigation.getCurrentPlace();
	}

	/**
	 * 
	 * @return el navigationModule de WALL·E
	 */
	public NavigationModule getNavigation() {
		return navigation;
	}
	
	/**
	 * 
	 * @return Si se ha acabado el juego o no. El juego se acaba cuando la cantidad de fuel de WALL·E llega a cero
	 * o cuando se decide cerrar la aplicacion, mediante la orden quit,
	 */
	public boolean isQuit() {
		return quit;
	}

	/**
	 * Método que sirve para fijar el atributo cityMap al parámetro cityMap
	 * @param cityMap
	 */
	public void setCityMap(City cityMap){
		this.cityMap = cityMap;
	}

	/**
	 * Método que sirve para fijar el atributo containerItem al parámetro containerItem
	 * @param containerItem
	 */
	public void setContainerItem(ItemContainer containerItem) {
		this.containerItem = containerItem;
	}

	/**
	 * Método que sirve para fijar el atributo quit al parámetro quit, para cuando se tenga que cerrar la aplicación
	 * @param quit
	 */
	public void setQuit(boolean quit) {
		this.quit = quit;
	}
	
	//////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	
	/**
	 * Método que sirve para configurar el contexto de la instruccion pasada por parámetro y ejecutarla
	 * Si ocurre alguna excepción durante la ejecucion de una instrucción entonces se muestra su mensaje
	 * por consola o por JOptionPane dependiendo en que modo este ejecutando.
	 * @param c - Instrucción a configurar
	 */
	public void communicateRobot(Instruction c){
	
		c.configureContext(this, this.navigation, this.containerItem);
		
		try {
			c.execute();
		} catch (InstructionExecutionException e) {
			// TODO Auto-generated catch block
			this.requestError(e.getMessage());
		}
	}
	
	/**
	 * Comprueba si la simulación ha acabado
	 * @return true si el juego ha acabado
	 */
	public boolean isOver(){
		return this.engine == 0;
	}
	
	/**
	 * Método que suma a la cantidad del robot la cantidad pasada por parámetro
	 * @param weight - cantidad de material reciclado a añadir
	 */
	public void addRecycledMaterial(int weight){
		this.rMaterial += weight;
	}
	
	/**
	 * Método que añade la cantidad de fuel pasada por parámetro a la cantidad que tenía el robot.
	 * @param fuel - cantidad de fuel a sumar
	 */
	public void addFuel(int fuel){
		this.engine += fuel;
		if(this.engine < 0)
			this.engine = 0;
	}
	
	/**
	 * 
	 * @return La cantidad de fuel que tiene WALL·E
	 */
	public int getFuel(){
		return this.engine;
	}
	
	/**
	 * 
	 * @return la cantidad de material reciclado que tiene WALL·E
	 */
	public int getRecycledMaterial(){
		return this.rMaterial;
	}
	
	/**
	 * Solicita el motor para informar a los observadores que la simulación comienza
	 */
	public void requestStart(){
		//Iterator<RobotEngineObserver> it;
		
		for (Iterator<RobotEngineObserver> it = this.iterator(); it.hasNext();){
			it.next().robotUpdate(this.engine, this.rMaterial);
		}
	}
	
	/**
	 * Solicita el fin de la simulación
	 */
	public void requestQuit(){
		if(this.navigation.atSpaceship()){
			for (Iterator<RobotEngineObserver> it = this.iterator(); it.hasNext();){
				it.next().engineOff(true);
			}
			
		}else if(this.engine == 0){
			for (Iterator<RobotEngineObserver> it = this.iterator(); it.hasNext();){
				it.next().engineOff(false);
			}
		}
		
		//Avisar a las vistas de que se quiere cerrar el juego
		for (Iterator<RobotEngineObserver> it = this.iterator(); it.hasNext();){
			it.next().endGame();
		}	
	}
	
	/**
	 * Solicita al motor para que informe de todas las posible instrucciones
	 */
	public void requestHelp(){
		for (Iterator<RobotEngineObserver> it = this.iterator(); it.hasNext();){
			it.next().communicationHelp(generateHelp());
		}
	}
	
	/**
	 * Solicita al motor informar que ha ocurrido un error
	 * @param msg - Mensaje con el error
	 */
	public void requestError(String msg){
		for (Iterator<RobotEngineObserver> it = this.iterator(); it.hasNext();){
			it.next().raiseError(msg);
		}
	}
	
	/**
	 * Solicita al motor para que informe que se ha actualizado el fuel y el material reciclado del robot
	 * @param fuel - fuel actual del robot
	 * @param recycledMaterial - material reciclado del robot
	 */
	public void requestRobotUpdate(int fuel, int recycledMaterial){
		for (Iterator<RobotEngineObserver> it = this.iterator(); it.hasNext();){
			it.next().robotUpdate(fuel, recycledMaterial);
		}
	}
	
	
	/**
	 * Solicita al motor decir algo
	 * @param message - Mensaje para decir
	 */
	public void saySomething(String message){
		for (Iterator<RobotEngineObserver> it = this.iterator(); it.hasNext();){
			it.next().robotSays(message);
		}
	}
	
	/**
	 * Registra un navigation observer al modelo
	 * @param robotObserver
	 */
	public void addNavigationObserver(NavigationObserver robotObserver){
		this.navigation.addObserver(robotObserver);
	}


	/**
	 * Registra un engine observer al modelo
	 * @param observer
	 */
	public void addEngineObserver(RobotEngineObserver observer){
		addObserver(observer);
	}
	
	/**
	 * Registra un item container al modelo
	 * @param observer
	 */
	public void addItemContainerObserver(InventoryObserver observer){
		this.containerItem.addObserver(observer);
	}
	
	/**
	 * Método privado 
	 * @return Devuelve un String con la ayuda
	 */
	private String generateHelp(){
		String str="";
		List<Instruction> list = Interpreter.creaListaInstruccines();
		System.out.println("The valid instructions for WALL-E are:");
		Interpreter.creaListaInstruccines();
		
		Iterator<Instruction> it = list.iterator();
		while(it.hasNext()){
			Instruction inst = it.next();
			//System.out.println(inst.getHelp());
			str = str + inst.getHelp() + "\n";
		}
		
		return str;
	}
	
}
