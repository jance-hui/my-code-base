package view.EmpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import dao.EmployeeDao;
import entity.Department;
import entity.Employee;
import util.CallBack;

public class AmendEmployeeView extends SuperEmployeeView {

	private CallBack callBack;
	private Employee amendEmp;
	private static AmendEmployeeView amendEmps;

	private AmendEmployeeView(Employee amendEmp, CallBack callBack) {

		this.amendEmp = amendEmp;
		this.callBack = callBack;
	}

	public static AmendEmployeeView getInstance(Employee amendEmp, CallBack callBack) {
		if (amendEmps == null) {
			amendEmps = new AmendEmployeeView(amendEmp, callBack);
		}
		return amendEmps;

	}

	public void findFrame() {
		if (frame == null) {
			init();
		} else {
			frame.setVisible(true);
		}
	}

	public void init() {
		super.init();
		nameText.setText(amendEmp.getName());
		ageText.setText(String.valueOf(amendEmp.getAge()));
		sexBox.setSelectedItem(amendEmp.getSex());
		if (amendEmp.getDep().getName() != null) {
			depBox.setSelectedIndex(amendEmp.getDep().getId()-1);
		}
		okBtn.addActionListener(new ActionListener() {// 保存修改信息
			public void actionPerformed(ActionEvent e) {

				amendEmp.setName(nameText.getText());
				amendEmp.setSex(sexBox.getSelectedItem().toString());
				amendEmp.setAge(Integer.parseInt(ageText.getText()));
				int index = depBox.getSelectedIndex();
				Department dep = new Department();
				dep = depList.get(index);
				amendEmp.setDep(dep);

				EmployeeDao empDao = new EmployeeDao();
				boolean flag = empDao.amend(amendEmp);
				if (flag) {
					JOptionPane.showMessageDialog(null, "修改成功");
				} else {
					JOptionPane.showMessageDialog(null, "保存失败");
				}
				callBack.call();
				amendEmps = null;
				frame.dispose();
			}
		});
	}
}
