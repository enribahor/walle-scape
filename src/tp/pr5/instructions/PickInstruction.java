package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.Instruction;
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
public class PickInstruction implements Instruction {

	private NavigationModule navigation; 
	private ItemContainer robotContainer;
	private RobotEngine robot;
	private String id;
	boolean ejec; //true gui, false consola
	
	public PickInstruction(){

	}
	
	public PickInstruction(String id){
		this.id = id;
	}
	
	public void setRobot(RobotEngine robot) {
		this.robot = robot;
	}

	public RobotEngine getRobot() {
		return robot;
	}
	
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		String cadenas[] = cad.split(" ");
		if(cadenas.length != 2 || !(cadenas[0].equalsIgnoreCase("PICK")||cadenas[0].equalsIgnoreCase("COGER")))
			throw new WrongInstructionFormatException();
		else 
			this.id = cadenas[1];
		return this;
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "PICK|COGER <id>";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.setRobot(engine);
		this.navigation = navigation;
		this.robotContainer = robotContainer;
		this.robot = engine;

	}

	@Override
	public void execute() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		
		//Comprobamos que el objeto no se encuentre ya en el contenedor del robot
		if(this.robotContainer.containsItem(this.id))
			throw new InstructionExecutionException(Messages.itemAlreadyExistWhenPick(this.id));
		
		else{//si no esta en el contenedor del robot
			//intentamos coger el item del place y si todo ha salido bien, entonces lo eliminamos del place
			Item it = this.navigation.pickItemFromCurrentPlace(this.id);
			if(it == null)//si el elemento no esta en el place
				throw new InstructionExecutionException(Messages.itemNoExitsPick(this.id));
			
			else if (this.robotContainer.addItem(it)){
				//Avisamos a la vista de que el inventario del robot ha cambiado
				this.robotContainer.requestInventoryChange(this.robotContainer.getContainer());
				//Avisamos a la vista de que el contenedor del lugar ha cambiado
				this.navigation.requestPlaceHasChanged();
				this.robot.saySomething(Messages.pickOk(this.id));
				
			}
		}
	}
}
