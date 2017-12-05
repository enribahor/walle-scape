package tp.pr5.gui;

import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * Clase que representa el Panel donde va colocado el JTextArea que muestra la descripción
 * 
 */
@SuppressWarnings("serial")
public class PanelPlaceDescription extends JPanel{
	public JTextArea jTextArea;
	
	public PanelPlaceDescription(){
		initPanelPlaceDescription();
	}
	
	/**
	 * Método que inicializa el PanelPlaceDescription
	 */
	public void initPanelPlaceDescription(){
		//Creacion
		this.jTextArea = new JTextArea();
		//Inicializacion
		this.jTextArea.setRows(5);
		//this.jTextArea.setBounds(10,50,400,300);
		this.setBorder(new TitledBorder("Log"));
		
		//Colocacion estética
		
		//Añadir
		this.add(this.jTextArea);
	}
} 
