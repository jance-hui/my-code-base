package view.DepView.Dep2ProView;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Project;

public class Dep2ProTableModel extends AbstractTableModel{
	private List<Project> list;
	private String[] columnNames = {"ID","��Ŀ����"};

	public void setList(List<Project> list) {
		this.list = list;
	}

	public Dep2ProTableModel(List<Project> list) {
		this.list = list;
	}

	@Override
	public int getRowCount() {// ��������
		// TODO Auto-generated method stub
		return list.size();
	}

	public String getColumnName(int column) {
			return columnNames[column];
	}

	@Override
	public int getColumnCount() {// ��������
		// TODO Auto-generated method stub
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if (columnIndex == 0) {
			return list.get(rowIndex).getId();
		} else if (columnIndex == 1) {
			return list.get(rowIndex).getName();
		}  else {
			return "";
		}

	}
}
