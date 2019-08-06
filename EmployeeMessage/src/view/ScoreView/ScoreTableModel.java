package view.ScoreView;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.swing.table.AbstractTableModel;

import entity.Score;

public class ScoreTableModel extends AbstractTableModel {
	private List<Score> list;
	private String[] columnNames = { "id", "姓名", "部门", "项目", "成绩", "等级" };
	private Set<Score> saveSet = new HashSet<>();

	public void setList(List<Score> list) {
		this.list = list;
	}

	public ScoreTableModel(List<Score> list) {
		this.list = list;

	}

	public boolean isCellEditable(int rowIndex, int columnIndex) {
		if (columnIndex == 4) {// 只能编辑成绩
			if (list.get(rowIndex).getPro().getId() != 0) {// 只能编辑有项目的
				return true;
			}
		}
		return false;
	}

	public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
		if (!aValue.equals("")) {
			list.get(rowIndex).setValue(Integer.parseInt((String) aValue));
			saveSet.add(list.get(rowIndex));
		}
	}

	public Set<Score> getSaveSet() {
		return saveSet;
	}

	public int getRowCount() {// 表格的行数
		return list.size();
	}

	public String getColumnName(int column) {// 表格的列名
		return columnNames[column];
	}

	public int getColumnCount() {// 表格的列数
		return columnNames.length;
	}

	public Object getValueAt(int rowIndex, int columnIndex) {
		if (columnIndex == 0) {
			return list.get(rowIndex).getId();
		} else if (columnIndex == 1) {
			return list.get(rowIndex).getEmp().getName();
		} else if (columnIndex == 2) {
			return list.get(rowIndex).getEmp().getDep().getName();
		} else if (columnIndex == 3) {
			return list.get(rowIndex).getPro().getName();
		} else if (columnIndex == 4) {
			return list.get(rowIndex).getValue();
		} else if (columnIndex == 5) {
			return list.get(rowIndex).getGrade();
		} else {
			return "";
		}

	}
}
