package tp.pr5.items;

import tp.pr5.NavigationModule;
import tp.pr5.RobotEngine;
import tp.pr5.Street;
import tp.pr5.messages.Messages;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * A CodeCard can open or close the door placed in the streets. The card contains a code that must match the street 
 * code in order to perform the action.
 */
public class CodeCard extends Item{
	
	private String code;
	
	public CodeCard(String id,String description,String code){
		super(id,description);
		this.code = code;
	}

	
	/**
	 * Una codeCard siempre puede usarse por lo que siempre devuelve true
	 */
	@Override
	public boolean canBeUsed() {
		
		return true;
	}

	/**
	 * Método que intenta usar la codeCard que llama a este.
	 * Devuelve true si la calle estaba cerrada y usamos la codeCard o si estaba abierta y usamos la 
	 * codeCard. Devuelve false en cualquier otro caso.
	 */
	@Override
	public boolean use(RobotEngine r, NavigationModule nav){
		Street st = nav.getHeadingStreet();
		boolean used;
		
		//si la calle no existe
		if(st == null){
			used = false;
		}
		//si la calle existe y el codigo de la calle coincide con el codigo de la tarjeta y además esta abierta
		else if( st != null && st.getCode() != null && st.getCode().equalsIgnoreCase(this.code) && st.isOpen() ){
			used = st.close(this);
		}
		//si la calle existe y el codigo de la calle coincide con el codigo de la tarjeta pero esta cerrada
		else if( st != null && st.getCode() != null && st.getCode().equalsIgnoreCase(this.getCode()) && !st.isOpen() ) {
			used = st.open(this);
		}else{
			used = false;
		}
		
		return used;
	}
	
	/**
	 * 
	 * @return el código de la tarjeta
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Método que sirve para fijar el código de la tarjeta al valor pasado por el párametro code
	 * @param code
	 */
	public void setCode(String code) {
		this.code = code;
	}
	
	@Override
	public String toString(){
		return Messages.itemDescription(id, description);
	}
	
}
