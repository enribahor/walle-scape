package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 */
public class MoveInstruction implements Instruction {
	private NavigationModule navigation; 
	private RobotEngine robot;
	
	public MoveInstruction(){

	}

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		String cadenas[] = cad.split(" ");
		if(cadenas.length != 1 || !(cadenas[0].equalsIgnoreCase("MOVE") || cadenas[0].equalsIgnoreCase("MOVER")))
			throw new WrongInstructionFormatException();
		return this;
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "MOVE|MOVER";
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
		this.navigation.move();
		this.robot.addFuel(-5);
		//Avisamos a la vista de que el fuel y el material reciclado del robot han cambiado
		this.robot.requestRobotUpdate(this.robot.getFuel(), this.robot.getRecycledMaterial());
		
		//Si el fuel del robot llega a 0 o si llega a la nave espacial
		if(this.robot.getFuel() == 0 || this.navigation.getCurrentPlace().isSpaceship()){
			//Avisamos a la vista de que el juego ha terminado
			this.robot.requestQuit();
		}
	}
}
