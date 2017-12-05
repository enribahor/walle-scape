package tp.pr5.gui;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import tp.pr5.messages.Messages;

/**
 * 
 * @author Antonio Núñez Guerrero
 * 
 * Panel donde están todos los botones que se refieren a Instruction
 *
 */
@SuppressWarnings("serial")
public class PanelInstructions extends JPanel {
	private final String BUTTON_MOVE = "MOVE";
	private final String BUTTON_TURN = "TURN";
	private final String BUTTON_PICK = "PICK";
	private final String BUTTON_DROP = "DROP";
	private final String BUTTON_OPERATE = "OPERATE";
	private final String BUTTON_QUIT = "QUIT";
	private final String PANEL_NAME = "Instructions";
	private final String TEXT_ITEM = "textItem";
	private JButton jButtonMove;
	private JButton jButtonTurn;
	private JButton jButtonPick;
	private JButton jButtonDrop;
	private JButton jButtonOperate;
	private JButton jButtonQuit;
	private JTextField jTextFieldItem;
	private JComboBox jComboBoxDirection;
	private GUIController guiController;
	private MainWindow mainWindow;
	private String rotation;
	
	
	public PanelInstructions(GUIController guiController, MainWindow mainWindow){
		this.guiController = guiController;
		this.mainWindow = mainWindow;
		initPanelInstructions();
	}
	
	/**
	 * Método que inicializa el PanelInstructions
	 */
	public void initPanelInstructions(){
		//Creacion
		this.jButtonMove = new JButton();
		this.jButtonTurn = new JButton();
		this.jButtonPick = new JButton();
		this.jButtonDrop = new JButton();
		this.jButtonOperate = new JButton();
		this.jButtonQuit = new JButton();
		this.jTextFieldItem = new JTextField();
		this.jComboBoxDirection = new JComboBox();
		
		//Inicializacion
		this.setBorder(new TitledBorder(PANEL_NAME));
		this.jButtonMove.setText(BUTTON_MOVE);
		this.jButtonTurn.setText(BUTTON_TURN);
		this.jButtonPick.setText(BUTTON_PICK);
		this.jButtonPick.setName(BUTTON_PICK);
		this.jButtonDrop.setText(BUTTON_DROP);
		this.jButtonOperate.setText(BUTTON_OPERATE);
		this.jButtonQuit.setText(BUTTON_QUIT);
		this.jTextFieldItem.setName(TEXT_ITEM);
		this.jButtonPick.addActionListener(new ListenerButtonPick());
		this.jButtonDrop.addActionListener(new ListenerButtonDrop());
		this.jButtonOperate.addActionListener(new ListenerButtonOperate());
		this.jButtonTurn.addActionListener(new ListenerButtonTurn());
		this.jComboBoxDirection.addItemListener(new ListenerJComboxDirection());
		this.jButtonMove.addActionListener(new ListenerButtonMove());
		
		this.jButtonQuit.addActionListener(new ListenerButtonQuit());
		/*this.jTextFieldItem.addActionListener(new ListenerTextFieldItem());
		this.jTextFieldItem.addFocusListener(new ListenerTextFieldItem());
		
		;
		*/
		
		this.jTextFieldItem.setName("jTextFieldItem");
		
		//Colocacion estetica
		this.setLayout(new GridLayout(4,2,5,5));
	
		//Añadir
		this.jComboBoxDirection.addItem("LEFT");
		this.jComboBoxDirection.addItem("RIGHT");
		this.add(this.jButtonMove);
		this.add(this.jButtonQuit);
		this.add(this.jButtonTurn);
		this.add(this.jComboBoxDirection);
		this.add(this.jButtonPick);
		this.add(this.jTextFieldItem);
		this.add(this.jButtonDrop);
		this.add(this.jButtonOperate);
	}
	
	/**
	 * Método que crea un JOptionPane que pregunta al usuario si esta seguro de cerrar la aplicación
	 */
	private int askBeforeClose(){
		int n = JOptionPane.showConfirmDialog(
	            null,
	            Messages.areYouSureExit(),
	            null,
	            JOptionPane.YES_NO_OPTION);
		return n;
	}
	
	/**
	 * Método privado que muestra un JOptionPane preguntando si queremos cerrar o no la aplicación. Si se pulsa en
	 * si entonces se avisa al controlador para que cierre la aplicación.
	 */
	public void tryQuit(){
		int n = askBeforeClose();
		if(n == 0){
			guiController.executeQuitInstruction();
		}
	}
	
	/**
	 * 
	 * @author Antonio Nuñez Guerrero
	 *
	 * Clase oyente del botón pick
	 */
	public class ListenerButtonPick implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String id = jTextFieldItem.getText();
			guiController.executePickInstruction(id);//Mandar el evento al controlador
		}
	}
	
	/**
	 * 
	 * @author Antonio Núñez Guerrero
	 * 
	 * Clase oyente del botón operate
	 */
	public class ListenerButtonOperate implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String id = mainWindow.getRobotPanel().getSelectedItem();
			guiController.executeOperateInstruction(id);//Mandar el evento al controlador
		}
	}
	
	/**
	 * 
	 * @author Antonio Núñez Guerrero
	 * 
	 * Clase oyente del botón drop
	 *
	 */
	public class ListenerButtonDrop implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			String id = mainWindow.getRobotPanel().getSelectedItem();
			guiController.executeDropInstruction(id);
		}
	}
	
	/**
	 * 
	 * @author Antonio Núñez Guerrero
	 * 
	 * Clase oyente del botón turn
	 *
	 */
	public class ListenerButtonTurn implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			guiController.executeTurnInstruction(rotation);
		}
	}
	
	/**
	 * 
	 * @author Antonio Núñez Guerrero
	 * 
	 * Clase oyente del botón move
	 */
	public class ListenerButtonMove implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			guiController.executeMoveInstruction();
		}
	}
	
	/**
	 * 
	 * @author Antonio Núñez Guerrero
	 * 
	 * Clase oyente del botón quit
	 */
	public class ListenerButtonQuit implements ActionListener{
		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			tryQuit();
		}
		
	}
	
	/**
	 * 
	 * @author Antonio
	 *
	 * Clase oyente del JComboxDirection
	 */
	public class ListenerJComboxDirection implements ItemListener{

		@Override
		public void itemStateChanged(ItemEvent arg0) {
			// TODO Auto-generated method stub
			String select=(String)jComboBoxDirection.getSelectedItem();
            rotation = select;
		}
	}
}
