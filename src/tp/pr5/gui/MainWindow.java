package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JSplitPane;
import javax.swing.WindowConstants;

import tp.pr5.RobotEngineObserver;
import tp.pr5.messages.Messages;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * This class creates the window for the Swing interface. The MainWindow contains the following components:
 *
 *   A menu with the QUIT action
 *   An Action panel with several buttons to perform MOVE, TURN, OPERATE, PICK, and DROP actions. Additionally it 
 *   has a combo box of turn rotations and a text field to write the name of the item that the robot wants to pick 
 *   from the current place
 *   A RobotPanel that displays the robot information (fuel and recycled material) and the robot inventory, which 
 *   shows a table with item names and descriptions that the robot carries. The user can select the items contained 
 *   in the inventory in order to DROP or OPERATE an item
 *   A NavigationPanel that represents the city. It shows the places that the robot has visited and an icon that 
 *   represents the robot heading. The robot heading is updated when the user performs a TURN action. The visible 
 *   places are updated when the robot performs a MOVE action. Once a place is visited, the user can click on it in 
 *   order to display the place description (similar to the RADAR command).
 *   An InfoPanel that displays information about different events that occur during the game
 *
 * This window implements the GameObserver interface in order to be notified about the game events. 
 *
 */
@SuppressWarnings("serial")
public class MainWindow extends javax.swing.JFrame implements RobotEngineObserver{

	private final String MENU_ITEM_FILE = "File"; 
	private JMenuBar jMenuBar;
	private JMenuItem jMenuItemFile;
	private JPanel jPanelNorth;
	private JSplitPane jSplitPaneRobotPanel;
	private NavigationPanel navigationPanel;
	private PanelInstructions panelInstruction;
	private RobotPanel robotPanel;
	private GUIController guiController; 
	private InfoPanel infoPanel;
	//infopanel
	
	public MainWindow(){
		initWindow();
	}
	
	public MainWindow(GUIController guiController){
		this.guiController = guiController;
		initWindow();
	}
	
	/**
	 * Método para inicializar la ventana principal con todos sus paneles. y componentes swing	 
	 */
	public void initWindow(){
		//Creacion
		this.jMenuBar = new JMenuBar();
		this.jMenuItemFile = new JMenuItem();
		this.jPanelNorth = new JPanel();
		this.navigationPanel = new NavigationPanel();
		this.jSplitPaneRobotPanel = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
		this.robotPanel = new RobotPanel();
		this.panelInstruction = new PanelInstructions(guiController, this);
		this.infoPanel = new InfoPanel();
		
		//Inicializacion
		this.jMenuItemFile.setText(MENU_ITEM_FILE);
		this.setSize(800,600);
		this.setVisible(true);
		this.setDefaultCloseOperation(WindowConstants.DO_NOTHING_ON_CLOSE);
		this.addWindowListener(new WListener());
		
		//Colocación y estética
		this.jPanelNorth.setLayout(new BorderLayout());
		
		//Añadir
		this.jMenuBar.add(this.jMenuItemFile);
		this.jSplitPaneRobotPanel.add(this.panelInstruction);
		this.jSplitPaneRobotPanel.add(this.robotPanel);
		this.jPanelNorth.add(this.jMenuBar, BorderLayout.NORTH);
		this.jPanelNorth.add(this.jSplitPaneRobotPanel, BorderLayout.CENTER);
		this.add(this.jPanelNorth, BorderLayout.NORTH);
		this.add(this.navigationPanel, BorderLayout.CENTER);
		this.add(this.infoPanel, BorderLayout.SOUTH);
		
		//Añadir las vistas al controlador
		this.guiController.addRobotEngineObserver(this);
		this.guiController.addRobotEngineObserver(robotPanel);
		this.guiController.addNavigationObserver(navigationPanel);
		this.guiController.addInventoryObserver(robotPanel);
		this.guiController.addInventoryObserver(infoPanel);
		this.guiController.addNavigationObserver(infoPanel);
		this.guiController.addRobotEngineObserver(infoPanel);
		
		this.setLocation(320,0);
		//this.pack();
		
	}
	
	/**
	 * El robot engine informa que se ha producido un error
	 * Especificado por: raiseError en la interfaz RobotEngineObserver
	 * @param msg - Mensaje de error
	 */
	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, msg, null, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * El robot engine informa que la ayuda ha sido solicitada
	 * Especificado por: communicationHelp en la interfaz RobotEngineObserver
	 * @param help - Mensaje con la ayuda
	 */
	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * El robot engine informa que el robot se ha apagado(porque ha llegado a la nave espacial o porque se ha quedado sin
	 * fuel)
	 * Especificado por: engineOff en la interfaz RobotEngineObserver
	 * @param atShip - true si ha llegado a la nave espacial false si se ha quedado sin fuel
	 */
	@Override
	public void engineOff(boolean atShip) {
		// TODO Auto-generated method stub
		if(atShip){
			JOptionPane.showMessageDialog(null, Messages.byeByeMessage());
		}else{
			JOptionPane.showMessageDialog(null, Messages.deadMessage(), null, JOptionPane.ERROR_MESSAGE);
		}
		//Avisamos al controlador de que el juego ha terminado
		//this.endGame();
	}

	/**
	 * El robot engine informa que la comunicación ha terminado
	 * Especificado por: communicationCompleted en la interfaz RobotEngineObserver
	 */
	@Override
	public void communicationCompleted() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * El robot engine informa que el fuel y/o la cantidad de material reciclado ha cambiado
	 * Especificado por: robotUpdate en la interfaz RobotEngineObserver
	 * @param fuel - Cantidad actual de fuel
	 * @param recycledMaterial - Cantidad actual de material reciclado
	 */
	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		// TODO Auto-generated method stub
	}

	/**
	 * El robot engine informa que el robot quiere decir algo
	 * Especificado por: robotSays en la interfaz RobotEngineObserver
	 * @param message - El mensaje del robot
	 */
	@Override
	public void robotSays(String message) {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * El robot engine informa de que se ha terminado el juego
	 */
	@Override
	public void endGame() {
		// TODO Auto-generated method stub
		System.exit(0);
	}

	/**
	 * @return el robotPanel
	 */
	public RobotPanel getRobotPanel() {
		return robotPanel;
	}
	
	/**
	 * 
	 * @author Antonio Nuñez Guerrero
	 * 
	 * Utilizada para implementar el windowClosing para poder preguntar antes de salir de la aplicación
	 *
	 */
	public class WListener implements WindowListener{

		@Override
		public void windowActivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosed(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowClosing(WindowEvent arg0) {
			// TODO Auto-generated method stub
			panelInstruction.tryQuit();
		}

		@Override
		public void windowDeactivated(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowDeiconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowIconified(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void windowOpened(WindowEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		
	}

	
}
