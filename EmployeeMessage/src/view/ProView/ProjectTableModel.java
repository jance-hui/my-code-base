package view.ProView;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Project;


public class ProjectTableModel extends AbstractTableModel{
	private List<Project> list;
	private String[] columnNames = {"ID","��Ŀ����"};

	public void setList(List<Project> list) {
		this.list = list;
	}

	public ProjectTableModel(List<Project> list) {
		this.list = list;
	}

	@Override
	public int getRowCount() {// ��������
		return list.size();
	}

	public String getColumnName(int column) {
			return columnNames[column];
	}

	@Override
	public int getColumnCount() {// ��������
		return columnNames.length;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return list.get(rowIndex).getId();
		} else if (columnIndex == 1) {
			return list.get(rowIndex).getName();
		}  else {
			return "";
		}

	}
}
