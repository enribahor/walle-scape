package tp.pr5.instructions;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.instructions.DropInstruction;
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
public class DropInstruction implements Instruction {

	private NavigationModule navigation; 
	private ItemContainer robotContainer;
	private RobotEngine robotEngine;
	private String id;
	
	public DropInstruction (){
		
	}
	
	public DropInstruction(String id){
		this.id = id;
	}

	@Override
	public String getHelp() {
		// TODO Auto-generated method stub
		return "DROP <id>|SOLTAR <id>";
	}

	@Override
	public void configureContext(RobotEngine engine,
			NavigationModule navigation, ItemContainer robotContainer) {
		// TODO Auto-generated method stub
		
		this.navigation = navigation;
		this.robotContainer = robotContainer;
		this.robotEngine = engine;
	}

	@Override
	public void execute() throws InstructionExecutionException {
		// TODO Auto-generated method stub
		Item it = this.robotContainer.getItem(this.id);//buscamos el item en el contenedor del robot
		if(it == null){//si no esta
			throw new InstructionExecutionException(Messages.isNotInContainer(this.id));
		}
		else if(!this.navigation.getCurrentPlace().dropItem(it)){//si no podemos soltar un objeto en el place	
			throw new InstructionExecutionException(Messages.canNotDrop(it.getId()));

		}else {//Si ha habido exito
			this.robotContainer.removeItem(it);
			//Avisamos a la vista de que el inventario del robot ha cambiado
			this.robotContainer.requestInventoryChange(this.robotContainer.getContainer());
			//Avisamos a la vista de que el contenedor del lugar ha cambiado
			this.navigation.requestPlaceHasChanged();
			this.robotEngine.saySomething(Messages.dropOk(this.id));
			
		}
	}
	
	@Override
	public DropInstruction parse(String cad) throws WrongInstructionFormatException {
		// TODO Auto-generated method stub
		String cadenas[] = cad.split(" ");
		
		if (cadenas.length != 2 || !(cadenas[0].equalsIgnoreCase("DROP")||cadenas[0].equalsIgnoreCase("soltar")))
			throw new WrongInstructionFormatException("");
		else
			this.id = cadenas[1];
			
		return this;
	}

}
