package tp.pr5.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.List;


import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;


import tp.pr5.RobotEngineObserver;
import tp.pr5.items.InventoryObserver;
import tp.pr5.items.Item;
import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 * RobotPanel displays information about the robot and its inventory. More specifically, it contains labels to show the
 * robot fuel and the weight of recycled material and a table that represents the robot inventory. Each row displays 
 * information about an item contained in the inventory.
 */
@SuppressWarnings("serial")
public class RobotPanel extends JPanel implements RobotEngineObserver, InventoryObserver{

	private final String TITLE_PANEL = "Robot info";
	private final String FUEL = "Fuel: ";
	private final String RECYCLED = "Recycled: ";
	private JLabel JLabelInfo;
	private RobotTable robotTable;
	private JTable table;
	private String itemId;//Atributo para saber que fila de la tabla se ha seleccionado
	private ItemContainer itemContainer;
	
	public RobotPanel(){
		this.itemContainer = new ItemContainer();
		initPanelTable();
	}
	
	/**
	 * Método que inicializa el PanelTable
	 */
	void initPanelTable(){
		//Creacion
		this.robotTable = new RobotTable(this.itemContainer);
		this.table = new JTable(this.robotTable);
		this.JLabelInfo = new JLabel();
		
		//Inicializacion
		this.setBorder(new TitledBorder(TITLE_PANEL));
		this.JLabelInfo.setHorizontalAlignment(JTextField.CENTER);
		//this.table.addMouseListener(new ListenerTable());
		
		//Colocacion estetica
		this.setLayout(new BorderLayout());
		this.setPreferredSize(new Dimension(150,125));
		
		//Añadir
		this.add(this.JLabelInfo, BorderLayout.NORTH);
		this.add( new JScrollPane(this.table), BorderLayout.CENTER);
		
	}

	/**
	 * Método que cambia las jLabel de fuel y recycled de robotTable
	 * @param fuel - entero que representa la cantidad de fuel a la que queremos cambiar el JLabel
	 * @param recycled - entero que representa la cantidad de recycled a la que queremos cambiar el JLabel
	 */
	public void changeJLabels(int fuel, int recycled){
		this.JLabelInfo.setText(FUEL + fuel + " " + RECYCLED + recycled);
	}
	
	/**
	 * 
	 * @return el robotTable
	 */
	public RobotTable getRobotTable(){
		return robotTable;
	}

	/**
	 * 
	 * @return el JLabelInfo
	 */
	public JLabel getJLabelInfo(){
		return JLabelInfo;
	}
	
	/**
	 * 
	 * @return el itemId
	 */
	public String getItemId() {
		return itemId;
	}

	/**
	 * Método que sirve para fijar el atributo Item
	 * @param itemId
	 */
	public void setItemId(String itemId) {
		this.itemId = itemId;
	}

	/**
	 * 
	 * @author Antonio
	 *
	 * Clase que representa el oyente de la tabla
	 */
	/*public class ListenerTable implements MouseListener{
		@Override
		public void mouseClicked(MouseEvent e) {
			// TODO Auto-generated method stub
			 int row = table.rowAtPoint(e.getPoint());
			 String id = (String) table.getValueAt(row, 0);
			 itemId = id;
		}

		@Override
		public void mouseEntered(MouseEvent e) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseExited(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mousePressed(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void mouseReleased(MouseEvent arg0) {
			// TODO Auto-generated method stub
			
		}

	}*/

	/**
	 * Notifica que el contenedor ha cambiado
	 * @param inventory - Nuevo inventario
	 */
	@Override
	public void inventoryChange(List<Item> inventory) {
		// TODO Auto-generated method stub
		itemContainer.setContainer(inventory);
		this.robotTable.setContainerItem(itemContainer);
	}

	/**
	 * Notifica que el usuario solicita una instrucción scan en el inventario
	 * @param inventoryDescription - Información sobre el inventario
	 */
	@Override
	public void inventoryScanned(String inventoryDescription) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Notifica que el usuario desea escanear un objeto del inventario
	 * @param description - Descrición del objeto
	 */
	@Override
	public void itemScanned(String description) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Notifica que un objeto se ha gastado y que hay que eliminarlo del contenedor.
	 * Después invocará al método inventoryChange
	 * @param itemName - Nombre del objeto gastado
	 */
	@Override
	public void itemEmpty(String itemName) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * El robot engine informa si ha ocurrido algún error
	 * @param msg - Mensaje de error
	 */
	@Override
	public void raiseError(String msg) {
		// TODO Auto-generated method stub
		//JOptionPane.showMessageDialog(null, msg, null, JOptionPane.ERROR_MESSAGE);
	}

	/**
	 * El robot engine informa que ha habido una petición de la ayuda
	 * @param help - Un string con la información de ayuda
	 */
	@Override
	public void communicationHelp(String help) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * El robot engine informa que el robot se ha apagado porque ha llegado a su nave espacial o porque se ha quedado
	 * sin fuel
	 * @param atShip - true si el robot se apaga porque ha llegado a la nave espacial y false si el robot se apaga porque se
	 * 				   se ha quedado sin fuel
	 */
	@Override
	public void engineOff(boolean atShip) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * El robot engine informa que la comunicación ha terminado
	 */
	@Override
	public void communicationCompleted() {
		// TODO Auto-generated method stub
		
	}

	/**
	 * El robot engine informa que el fuel y/o la cantidad de material reciclado ha cambiado
	 * @param fuel - Cantidad actual de fuel
	 * @param recycledMaterial - Cantidad actual de material reciclado
	 */
	@Override
	public void robotUpdate(int fuel, int recycledMaterial) {
		// TODO Auto-generated method stub
		this.JLabelInfo.setText(FUEL + fuel + " " + RECYCLED + recycledMaterial);
	}

	/**
	 * El robot engine informa que el robot quiere decir algo
	 * @param message - El mensaje del robot
	 */
	@Override
	public void robotSays(String message) {
		// TODO Auto-generated method stub
		JOptionPane.showMessageDialog(null, message);
	}
	
	/**
	 * El robot engine informa de que se ha terminado el juego
	 */
	@Override
	public void endGame() {
		// TODO Auto-generated method stub
		
	}
	
	/**
	 * @return un string con el nombre del item seleccionado
	 */
	public String getSelectedItem(){
		int row =  this.table.getSelectedRow();
		return (String) this.table.getValueAt(row, 0);
	}
}
