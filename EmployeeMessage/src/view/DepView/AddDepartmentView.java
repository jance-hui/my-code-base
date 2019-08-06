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
					JOptionPane.showMessageDialog(null, "�ѱ���");
				} else {
					JOptionPane.showMessageDialog(null, "����ʧ��");
				}
				callBack.call();
				addDep = null;// �����һ����Ӻ��ٴ���ӻ���ʾ�ϴ���ӵ���Ϣ
				frame.dispose();
			}
		});
	}
}
