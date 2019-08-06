package view.DepView;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Department;


public class DepartmentTableModel extends AbstractTableModel{
	private List<Department> list;
	private String[] columnNames = {"ID","��������","Ա������"};

	public void setList(List<Department> list) {
		this.list = list;
	}

	public DepartmentTableModel(List<Department> list) {
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
		} else if (columnIndex == 2) {
			return list.get(rowIndex).getEmp_count();
		}  else {
			return "";
		}

	}
}
