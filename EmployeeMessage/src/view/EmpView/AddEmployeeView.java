package view.EmpView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entity.Department;
import entity.Employee;
import util.CallBack;

public class AddEmployeeView extends SuperEmployeeView {

	private CallBack callBack;
	private static AddEmployeeView addEmp;

	private AddEmployeeView( CallBack callBack) {

		this.callBack = callBack;
	}

	public static AddEmployeeView getInstance(CallBack callBack) {
		if (addEmp == null) {
			addEmp = new AddEmployeeView(callBack);
		}
		return addEmp;
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
		okBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				Employee addEmps = new Employee();
				String name = nameText.getText();
				String sex = sexBox.getSelectedItem().toString();
				int age = Integer.parseInt(ageText.getText());
				int index = depBox.getSelectedIndex();
				Department dep = depList.get(index);
				addEmps.setName(name);
				addEmps.setSex(sex);
				addEmps.setAge(age);
				addEmps.setDep(dep);
				
				boolean flag = empDao.add(addEmps);
				if (flag) {
					JOptionPane.showMessageDialog(null, "已保存");
				} else {
					JOptionPane.showMessageDialog(null, "保存失败");
				}
				callBack.call();
				addEmp = null;// 解决了一次添加后，再次添加会显示上次添加的信息
				frame.dispose();
			}
		});
	}
}
