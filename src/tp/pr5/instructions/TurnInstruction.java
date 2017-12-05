package tp.pr5.instructions;


import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Rotation;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 */
public class TurnInstruction implements Instruction {
	private NavigationModule navigation; 
	//private ItemContainer robotContainer;
	private RobotEngine robot;
	private Rotation rotation;

	public TurnInstruction(){
		
	}
	
	public TurnInstruction(Rotation rotation){
		this.rotation = rotation;
	}
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		String cadenas[] = cad.split(" ");
		
		/*Lanzamos la excepcion cuando:
		 * 1- La longitud de la cadena a parsear es distinta de 2
		 * 2- La primera palabra de la cadena no es ni turn ni girar
		 * 3- La segunda palabra no es ni RIGHT NI LEFT*/
		if(cadenas.length != 2 || !(cadenas[0].equalsIgnoreCase("TURN") || cadenas[0].equalsIgnoreCase("GIRAR"))
				|| !( cadenas[1].equalsIgnoreCase("RIGHT") || cadenas[1].equalsIgnoreCase("LEFT") )){
			throw new WrongInstructionFormatException("I do not understand. Please repeat");
		//Si la acción es turn o girar y rotation es right
		}else if( (cadenas[0].equalsIgnoreCase("TURN") || cadenas[0].equalsIgnoreCase("GIRAR"))
				&& (cadenas[1].equalsIgnoreCase("RIGHT") ) ){
			this.rotation = Rotation.RIGHT;
		//Si la acción es turn o girar y rotation es left
		}else if( (cadenas[0].equalsIgnoreCase("TURN") || cadenas[0].equalsIgnoreCase("GIRAR"))
				&& (cadenas[1].equalsIgnoreCase("LEFT")) ) {
			this.rotation = Rotation.LEFT;
		}
		return this;
	}


	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "TURN | GIRAR < LEFT|RIGHT >";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.robot = engine;
		this.navigation = navigation;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		//Aqui se fija el atributo currentDirection al punto cardinal segun en la direccion que
		//en que se gira
		this.navigation.rotate(this.rotation);
		//Avisa a la vista de que la dirección a la que está mirando el robot ha cambiado
		this.navigation.requestHeadingChanged(this.navigation.getCurrentHeading());
		//Si Rotation es desconocida
		/*if(this.rotation == Rotation.UNKNOWN)
			throw new InstructionExecutionException (Messages.rotationUnkwownTurn());*/
		//Si es conocida
		
		this.robot.addFuel(-5);
		//Avisamos a la vista de que el fuel y el material reciclado del robot han cambiado
		this.robot.requestRobotUpdate(this.robot.getFuel(), this.robot.getRecycledMaterial());
		if(this.robot.getFuel() == 0){//si la energia llega a 0
			//Avisamos a la vista de que el juego ha terminado
			this.robot.requestQuit();
		}
	}
}
