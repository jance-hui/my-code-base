package view.EmpView;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import entity.Employee;

public class EmployeesTableModel extends AbstractTableModel {
	private List<Employee> list;
	private String[] columnNames = {"ID","����","�Ա�","����","����"};

	public void setList(List<Employee> list) {
		this.list = list;
	}

	public EmployeesTableModel(List<Employee> list) {
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
		} else if (columnIndex == 2) {
			return list.get(rowIndex).getSex();
		} else if (columnIndex == 3) {
			return list.get(rowIndex).getAge();
		} else if (columnIndex == 4) {
			return list.get(rowIndex).getDep().getName();
		}else {
			return "";
		}

	}
}
