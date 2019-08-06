package view.DepView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entity.Department;
import util.CallBack;

public class AddDepartmentView extends SuperDepartmentView {
	private CallBack callBack;
	private static AddDepartmentView addDep;

	private AddDepartmentView(CallBack callBack) {

		this.callBack = callBack;
	}

	public static AddDepartmentView getInstance(CallBack callBack) {
		if (addDep == null) {
			addDep = new AddDepartmentView(callBack);
		}
		return addDep;
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

				Department addDeps = new Department();
				String name = nameText.getText();
				addDeps.setName(name);
				addDeps.setEmp_count(0);
				boolean flag = depDao.add(addDeps);
				if (flag) {
					JOptionPane.showMessageDialog(null, "已保存");
				} else {
					JOptionPane.showMessageDialog(null, "保存失败");
				}
				callBack.call();
				addDep = null;// 解决了一次添加后，再次添加会显示上次添加的信息
				frame.dispose();
			}
		});
	}
}
