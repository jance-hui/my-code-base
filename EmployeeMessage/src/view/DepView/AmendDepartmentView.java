package view.DepView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entity.Department;
import util.CallBack;

public class AmendDepartmentView extends SuperDepartmentView {
	private CallBack callBack;
	private Department amendDep;
	private static AmendDepartmentView amendDeps;

	private AmendDepartmentView(Department amendEmp, CallBack callBack) {

		this.amendDep = amendEmp;
		this.callBack = callBack;
	}

	public static AmendDepartmentView getInstance(Department amendDep, CallBack callBack) {
		if (amendDeps == null) {
			amendDeps = new AmendDepartmentView(amendDep, callBack);
		}
		return amendDeps;

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
		nameText.setText(amendDep.getName());
		okBtn.addActionListener(new ActionListener() {// 保存修改信息
			public void actionPerformed(ActionEvent e) {

				amendDep.setName(nameText.getText());
				boolean flag = depDao.amend(amendDep);
				if (flag) {
					JOptionPane.showMessageDialog(null, "修改成功");
				} else {
					JOptionPane.showMessageDialog(null, "保存失败");
				}
				callBack.call();
				amendDeps = null;
				frame.dispose();
			}
		});
	}
}
