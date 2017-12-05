package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;
import tp.pr5.messages.Messages;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 */
public class ScanInstruction implements Instruction {
	private ItemContainer robotContainer;
	private String id;
	private RobotEngine robotEngine;
	boolean ejec; //true gui, false consola

	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		String cadenas[] = cad.split(" ");
		/*
		 * Lanzamos la excepcion cuando:
		 * 1- Si las palabras que contiene la instruccion no son o dos o 1
		 * 2- Si la primera palabra no es ni scan ni mover
		 */
		if( !(cadenas.length != 2 || cadenas.length != 1) || 
				!(cadenas[0].equalsIgnoreCase("SCAN") || cadenas[0].equalsIgnoreCase("ESCANEAR")) )
			throw new WrongInstructionFormatException();
		else if (cadenas.length == 2)
			this.id = cadenas[1];
		return this;
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "SCAN | ESCANEAR [id]";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.robotContainer = robotContainer;
		this.robotEngine = engine;
		
	}

	@Override
	public void execute() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		Item it1 = null;
		if(this.id != null){
			it1 = this.robotContainer.getItem(this.id);//cogemos el item con el id dado
			
		}
		//Si la instruccion no tiene id y el numero de elmentos en el inventario es mayor que 0
		if(this.id == null && this.robotContainer.getContainer().size() >0){
			this.robotEngine.saySomething("WALL·E says: I am carrying the following items");
			this.robotContainer.requestInventoryScanned();
			
		}
		//Si la instruccion tiene un id y el numero de elementos en el inventario es mayor que 0 y si el item
		//no existe en el inventario
		else if(this.id != null && this.robotContainer.getContainer().size() >0 && it1 == null){
			throw new InstructionExecutionException(Messages.notExistItemOperate());
			
		}
		//Si la instruccion tiene un id y el numero de elementos en el inventario es mayor que 0 y si el item
		//si existe en el inventario 
		else if (this.id != null && this.robotContainer.getContainer().size() >0) {
			this.robotEngine.saySomething(it1.toString());
			
		}
		//Si el inventario no tiene elementos
		else if(this.robotContainer.getContainer().size() == 0){
			throw new InstructionExecutionException("WALL·E says: My inventory is empty");
		}
	}
}
