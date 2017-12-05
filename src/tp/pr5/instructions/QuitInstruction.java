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
public class QuitInstruction implements Instruction {
	private RobotEngine robot;
	public QuitInstruction(){
		
	}
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		String cadenas[] = cad.split(" ");
		/*
		 * Lanza una excepcion cuando la longitud de la cadena es distinta de 1 o si la instruccion no 
		 * es salir o quit
		 */
		if( cadenas.length != 1 || !(cadenas[0].equalsIgnoreCase("QUIT") || cadenas[0].equalsIgnoreCase("SALIR")) )
			throw new WrongInstructionFormatException();
		return this;
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "QUIT|SALIR";
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
		this.robot.requestQuit();
	}
}
