package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 */
public class HelpInstruction implements Instruction {
	private RobotEngine robot;

	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		String cadenas[] = cad.split(" ");
		if(cadenas.length!=1 || !(cadenas[0].equalsIgnoreCase("HELP") || cadenas[0].equalsIgnoreCase("AYUDA")))
			throw new WrongInstructionFormatException();
		return this;
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		
		return "HELP|AYUDA";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.robot = engine;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		this.robot.requestHelp();

	}

}
