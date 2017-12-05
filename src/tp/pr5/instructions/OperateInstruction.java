package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.Instruction;
import tp.pr5.instructions.exceptions.InstructionExecutionException;
import tp.pr5.instructions.exceptions.WrongInstructionFormatException;
import tp.pr5.items.CodeCard;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;
import tp.pr5.messages.Messages;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 */
public class OperateInstruction implements Instruction {
	private NavigationModule navigation; 
	private ItemContainer robotContainer;
	private RobotEngine robot;
	private String id;
	boolean ejec; //true gui, false consola

	public OperateInstruction(){
		
	}
	
	public OperateInstruction(String id){
		this.id = id;
	}
	
	@Override
	public Instruction parse(String cad) throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		String cadenas[] = cad.split(" ");
		if(cadenas.length!=2 || !(cadenas[0].equalsIgnoreCase("OPERATE") || cadenas[0].equalsIgnoreCase("OPERAR")))
			throw new WrongInstructionFormatException();
		else
			this.id = cadenas[1];
		return this;
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		
		return "OPERATE|OPERAR <ID>";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		this.robot = engine;
		this.navigation = navigation;
		this.robotContainer = robotContainer;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		Item item = this.robotContainer.getItem(id);
		if(!this.robotContainer.containsItem(this.id)){
			throw new InstructionExecutionException(Messages.notExistItemOperate());
		
		//si no se ha podido usar el item
		}else if(!item.use(this.robot, this.navigation)){ 
			throw new InstructionExecutionException(Messages.canNotUseOperate(this.id));
	
		//si el elemento no puede ser usado
		}else if (!item.canBeUsed()){
			this.robot.saySomething(Messages.spentItemOperate(this.id));
			this.robotContainer.removeItem(item);
		}
		
		//Avisamos a la vista de que el inventario del robot ha cambiado
		this.robotContainer.requestInventoryChange(this.robotContainer.getContainer());
		
		if(item.getClass() != CodeCard.class){
			//Avisamos a la vista de que el fuel y/o el material reciclado han cambiado
			this.robot.requestRobotUpdate(this.robot.getFuel(), this.robot.getRecycledMaterial());
		}
		
		if(this.robot.getFuel() == 0){//si la energia llega a 0
			this.robot.requestQuit();
		}
	}
}