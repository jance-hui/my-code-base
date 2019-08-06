package b;

import java.util.List;

import javax.swing.table.AbstractTableModel;

import b.Employee;

public class EmployeesTableModel extends AbstractTableModel {
	private List<Employee> list;

	public void setList(List<Employee> list) {
		this.list = list;
	}

	public EmployeesTableModel(List<Employee> list) {
		this.list = list;
	}

	@Override
	public int getRowCount() {// 表格的行数
		// TODO Auto-generated method stub
		return list.size();
	}

	public String getColumnName(int column) {
		if (column == 0) {
			return "姓名";
		} else if (column == 1) {
			return "性别";
		} else if (column == 2) {
			return "年龄";
		} else if (column == 3) {
			return "工号";

		}else {
			return "";
		}
		
	}

	@Override
	public int getColumnCount() {// 表格的列数
		// TODO Auto-generated method stub
		return 4;
	}

	@Override
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		if (columnIndex == 0) {
			return list.get(rowIndex).getName();
		} else if (columnIndex == 1) {
			return list.get(rowIndex).getSex();
		} else if (columnIndex == 2) {
			return list.get(rowIndex).getAge();
		} else if (columnIndex == 3) {
			return list.get(rowIndex).getNo();
		} else {
			return "";
		}

	}
}
