package tp.pr5.gui;

import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import tp.pr5.PlaceInfo;

/**
 * 
 * @author Antonio Núñez Guerrero
 * 
 * Representa el mapa de botones del NavigationPanel, donde cada boton representa un Place
 *
 */
@SuppressWarnings("serial")
public class PanelButtons extends JPanel {
	private final int INIT_ROW = 5;
	private final int INIT_COLUMN = 5;
	private final int MAX_CELLS;
	private PlaceCell[][] placeCell;
	private int row; //representa la fila del boton que esta en verde
	private int column; //representa la columna del boton que esta en verde
	private JTextArea jTextPlaceDescription;

	public PanelButtons(JTextArea jTextPlaceDescription){
		this.jTextPlaceDescription = jTextPlaceDescription;
		this.MAX_CELLS = 11;
		this.placeCell = new PlaceCell[MAX_CELLS][MAX_CELLS];
		this.row = INIT_ROW;
		this.column = INIT_COLUMN;
		initPanelButtons();
	} 

	/**
	 * Método que inicializa el PanelButtons con todos los botones, fijando el color verde al botón que reperesenta
	 * el lugar origen.
	 */
	public void initPanelButtons(){
		//this. = new JPanel();
		this.setLayout(new GridLayout(11,11));
		for (int i=0; i< this.MAX_CELLS; i++)
			for (int j=0; j<this.MAX_CELLS; j++){
				PlaceCell cell = new PlaceCell();
				this.placeCell[i][j] = cell;
				cell.addActionListener(new ListenerButton());
				this.add(cell);
			}
	}
	
	/**
	 * 
	 * @author Antonio
	 * 
	 * Clase oyente de los placeCell del PanelButtons
	 */
	public class ListenerButton implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			PlaceCell cell = (PlaceCell) e.getSource();
			if(cell.getPlace()!=null){//Si el boton no tiene asignado un place aun
				String description = cell.getPlace().toString();
				jTextPlaceDescription.setText(description);
			}
		}
	}
	
	/**
	 * 
	 * @return el array bidimensional de PlaceCell
	 */
	public PlaceCell[][] getPlaceCell() {
		return placeCell;
	}

	/**
	 * 
	 * @return la fila del botón que esta en verde
	 */
	public int getRow() {
		return row;
	}
	
	/**
	 * 
	 * @return la columna del botón que está en verde
	 */
	public int getColumn() {
		return column;
	}

	/**
	 * Método que sirve para fijar el atributo row al parámetro row
	 * @param row
	 */
	public void setRow(int row) {
		this.row = row;
	}

	/**
	 * Método que sirve para fijar el atributo column al parámetro column
	 * @param column
	 */
	public void setColumn(int column) {
		this.column = column;
	}
	
	/**
	 * Método que inserta el nombre del place en el botón cada vez que WALL·E se va moviendo a los distintos place
	 * @param row - numero de fila del botón en el que hay que insertar el el nombre
	 * @param column - numero de columa del botón en el que hay que insertar el nombre
	 */
	/*public void setPlaceNameInButton(){
		this.placeCell[this.row][this.column].setText(this.navigationModule.getCurrentPlace().getName());
	}*/
	
	/**
	 * Método que cambia el color del botón que estaba en verde a gris
	 */
	public void changeToGray(){
		this.placeCell[row][column].setBackground(Color.gray);
	}
	
	/**
	 * Método que cambia el color del siguiente botón a verde
	 */
	public void changeToGreen(){
		this.placeCell[row][column].setBackground(Color.green);
	}
	
	/**
	 * Método que inserta el nombre del place en el botón cada vez que WALL·E se va moviendo a los distintos place
	 */
	public void setPlaceNameInButton(String placeName){
		this.placeCell[this.row][this.column].setText(placeName);
	}
	
	/**
	 * Método que fija el place al botón actual
	 * @param place - place del boton
	 */
	public void setPlace(PlaceInfo place){
		this.placeCell[this.row][this.column].setPlace(place);
	}
}
