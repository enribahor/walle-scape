package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.messages.Messages;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 */
public class Garbage extends Item {

	private int recycledMaterial;
	
	public Garbage(String id,String description, int recycledMaterial){
		super(id,description);
		this.recycledMaterial = recycledMaterial;
	}
	
	/**
	 * Un objeto de tipo Garbage puede usarse si recycledMaterial es mayor que cero
	 */
	@Override
	public boolean canBeUsed() {
		
		return this.recycledMaterial > 0;
	}

	/**
	 * Método que devuelve true si se puede usar.
	 * 
	 */
	@Override
	public boolean use(RobotEngine r, NavigationModule nav) {
		boolean ok;
		if(this.canBeUsed()){
			r.addRecycledMaterial(this.recycledMaterial);
			this.recycledMaterial = 0;
			ok = true;
		}else{
			ok = false;
		}
		return ok;
	}

	@Override
	public String toString(){
		return Messages.itemDescription(id, description);
	}
}
