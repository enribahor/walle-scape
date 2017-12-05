package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.border.TitledBorder;

import tp.pr5.Direction;
import tp.pr5.NavigationObserver;
import tp.pr5.PlaceInfo;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * This class is in charge of the panel that displays the information about the robot heading and the city that is traversing.
 * It contains the grid that represents the city in the Swing interface, a text area to show the place descriptions, and a 
 * label with an icon which represents the robot heading
 *
 * The 11x11 grid contains PlaceCell objects and the first place starts at (5,5). This panel will update the visited places 
 * when the robot moves from one place to another. Additionally it will show the place description on a text area if the user 
 * clicks on a visited place. 
 */
@SuppressWarnings("serial")
public class NavigationPanel extends JPanel implements NavigationObserver{
	private PanelButtons jPanelButtons;
	private JPanel jPanelPicture;
	private JPanel jpanelCenter;
	private JTextArea jTextPlaceDescription;
	private JPanel jPanelTextPlaceDescription;
	private JScrollPane JScrollPlaceDescription;
	private JLabel jLabelRobotPicture;

	public NavigationPanel(){
		initNavigationPanel();
	}
	
	/**
	 * Método que inicializa el navigationPanel
	 */
	public void initNavigationPanel(){
		//Creacion
		this.jpanelCenter = new JPanel();
		this.jPanelPicture = new JPanel();
		this.jTextPlaceDescription = new JTextArea();
		this.jPanelButtons = new PanelButtons(this.jTextPlaceDescription);
		this.JScrollPlaceDescription = new JScrollPane(this.jTextPlaceDescription, 
				JScrollPane.VERTICAL_SCROLLBAR_ALWAYS ,JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
		this.jLabelRobotPicture = new JLabel();
		this.jPanelTextPlaceDescription = new JPanel();
		
		//Inicializacion
		this.jPanelButtons.setBorder(new TitledBorder("City Map"));
		this.jPanelTextPlaceDescription.setBorder(new TitledBorder("Log"));
		this.jTextPlaceDescription.setRows(5);
		this.jTextPlaceDescription.setColumns(64);
		//this.JScrollPlaceDescription.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		//this.JScrollPlaceDescription.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		
		//Colocacion estética
		this.setLayout(new FlowLayout());
		this.setLayout(new BorderLayout());
		this.jPanelPicture.setLayout(new BorderLayout());
		this.jpanelCenter.setLayout(new BorderLayout());
		this.jPanelTextPlaceDescription.setLayout(new GridLayout());
		
		
		//Añadir
		this.jPanelPicture.add(this.jLabelRobotPicture, BorderLayout.CENTER);
		this.jpanelCenter.add(jPanelPicture, BorderLayout.WEST);
		this.jpanelCenter.add(this.jPanelButtons, BorderLayout.CENTER);
		this.add(this.jpanelCenter,BorderLayout.CENTER);
		this.jPanelTextPlaceDescription.add(this.JScrollPlaceDescription);	
		this.add(this.jPanelTextPlaceDescription,BorderLayout.SOUTH);

	}
	
	/**
	 * Notifica que la direccion a la que estaba mirando el robot ha cambiado.
	 * Especificado por: headingChanged en la interfaz NavigationObserver
	 * @param newHeading - La nueva direccion
	 */
	@Override
	public void headingChanged(Direction newHeading) {
		// TODO Auto-generated method stub
		changeRobotPicture(newHeading);
	}

	/**
	 * Notifica que el NavigationModule ha sido inicializado
	 * Especificado por: initNavigationModule en la interfaz NavigationObserver
	 * @param initialPlace - El lugar donde el robot empieza la simulación
	 * @param heading - La dirección inicial a la que el robot esta mirando
	 */
	@Override
	public void initNavigationModule(PlaceInfo initialPlace, Direction heading) {
		// TODO Auto-generated method stub
		
		//Inicializar la imagen que representa la dirección a la que está mirando el robot
		changeRobotPicture(heading);
		//Inicializar el color del botón a verde
		initJButtonColor(initialPlace);
		//Mostrar la descripción del lugar incial
		jTextPlaceDescription.setText(initialPlace.toString());
		//Insertar el initialPlace al boton correspondiente
		this.jPanelButtons.setPlace(initialPlace);
	}
	
	/**
	 * Notifica que el robot ha llegado a un lugar
	 * @param heading - La dirección de movimiento del robot
	 * @param place - El lugar a donde llega el robot
	 */
	@Override
	public void robotArrivesAtPlace(Direction heading, PlaceInfo place) {
		// TODO Auto-generated method stub
		//Cambiar el color del botón y poner el nombre
		changeJButtonColor(heading, place);
		//Mostrar la descripción del lugar en el JTextArea
		jTextPlaceDescription.setText(place.toString());
		//Insertar el place al boton correspondiente
		this.jPanelButtons.setPlace(place);
	}
	
	/**
	 * Método que cambia la imagen de WALL·E en fución de la dirección a la que este mirando
	 */
	public void changeRobotPicture(Direction direction){
		if(direction == Direction.NORTH){
			this.jLabelRobotPicture.setIcon(new ImageIcon("src/tp/pr5/gui/images/walleNorth.png"));
		}else if(direction == Direction.EAST){
			this.jLabelRobotPicture.setIcon(new ImageIcon("src/tp/pr5/gui/images/walleEast.png"));
		}else if(direction == Direction.WEST){
			this.jLabelRobotPicture.setIcon(new ImageIcon("src/tp/pr5/gui/images/walleWest.png"));
		}else{
			this.jLabelRobotPicture.setIcon(new ImageIcon("src/tp/pr5/gui/images/walleSouth.png"));
		}
	}
	
	/**
	 * Método que cambia el color de un botón del PanelButtons a verde y añade el nombre del lugar al boton.
	 *  Antes de cambiarlo a verde cambia el botón anterior a gris.
	 * @param heading - Dirección actual a la que esta mirando el robot
	 * 		  currentPlace - Lugar actual en el que se encuentra el robot
	 */
	public void changeJButtonColor(Direction heading, PlaceInfo currentPlace){
		//1- Calcular la fila y la columna del boton que queremos cambiar de color
		int i=0;
		int j=0;
		if(heading == Direction.NORTH){
			i=-1;
		}else if(heading == Direction.SOUTH){
			i=+1;
		}else if(heading == Direction.EAST){
			j=+1;
		}else{
			j=-1;
		}
		
		//2- Cambiar el color del placeCell anterior a gris
		this.jPanelButtons.changeToGray();
		
		//3- Cambiar el color del placeCell siguiente a verde
		int row = this.jPanelButtons.getRow() + i;
		int column = this.jPanelButtons.getColumn() + j;
		this.jPanelButtons.setRow(row);
		this.jPanelButtons.setColumn(column);
		this.jPanelButtons.changeToGreen();
		
		//4- Insertar el nombre del botón
		this.jPanelButtons.setPlaceNameInButton(currentPlace.getName());
		
		//5- Fijar el place al boton
		this.jPanelButtons.setPlace(currentPlace);
	}
	
	/**
	 * Método que inicializa a verde el botón que representa el lugar inicial y despues le añade el nombre del lugar.
	 */
	public void initJButtonColor(PlaceInfo currentPlace){
		this.jPanelButtons.changeToGreen();
		this.jPanelButtons.setPlaceNameInButton(currentPlace.getName());
	}

	/**
	 * Notifica una petición del usuario de una RadarInstruction
	 * @param placeDescription - Información del lugar actual
	 */
	@Override
	public void placeScanned(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Notifica que el lugar donde el robot permanecía ha cambiado (porque el robot ha cogido o soltado un objeto)
	 * @param placeDescription
	 */
	@Override
	public void placeHasChanged(PlaceInfo placeDescription) {
		// TODO Auto-generated method stub
		//Mostrar la descripción del lugar en el JTextArea
		jTextPlaceDescription.setText(placeDescription.toString());
	}
}
