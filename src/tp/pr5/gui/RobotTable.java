package tp.pr5.gui;


import javax.swing.table.AbstractTableModel;

import tp.pr5.items.ItemContainer;

/**
 * 
 * @author Antonio Núñez Guerrero
 *
 */
@SuppressWarnings("serial")
public class RobotTable extends AbstractTableModel {
	private final String COLUMN_ID = "Id";
	private final String COLUMN_DESCRIPTION = "Description";
	private ItemContainer containerItem;
	private String [] columnsName;


	public RobotTable(){

	}

	public RobotTable(ItemContainer containerItem){
		this.columnsName = new String[] {COLUMN_ID,COLUMN_DESCRIPTION};
		this.containerItem = containerItem;
	}

	@Override
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return 2;
	}

	@Override
	public String getColumnName(int column){
		/*String columName;
		if(column == 0)
			columName = COLUMN_ID;
		else if (column == 1)
			columName = COLUMN_DESCRIPTION;
		else
			columName = null;
		return columName;*/
		return this.columnsName[column];
	}

	@Override
	public int getRowCount() {
		// TODO Auto-generated method stub
		//return 3;
		return this.containerItem.getContainer().size();
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		String str = null;
		// si la columna es la de las ids y el numero de fila no supera el numero de objetos del contenedor
		if(columnIndex == 0 && rowIndex < this.containerItem.numberOfItems() && rowIndex!= -1){
			str = this.containerItem.getContainer().get(rowIndex).getId();

		// si la columna es la de las description y el numero de fila no supera el numero de objetos del contenedor	
		}else if (columnIndex == 1 && rowIndex < this.containerItem.numberOfItems() && rowIndex!= -1){
			str = this.containerItem.getContainer().get(rowIndex).getDescription();
		}
		
		return str;
	}

	/**
	 * Método que fija el contenedor y actualiza la tabla
	 * @param containerItem
	 */
	public void setContainerItem(ItemContainer containerItem) {
		this.containerItem = containerItem;
		this.fireTableDataChanged();
	}
}
