package view.ProView;


import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entity.Project;
import util.CallBack;

public class AddProjectView extends SuperProjectView {
	private CallBack callBack;
	private static AddProjectView addDep;

	private AddProjectView(CallBack callBack) {

		this.callBack = callBack;
	}

	public static AddProjectView getInstance(CallBack callBack) {
		if (addDep == null) {
			addDep = new AddProjectView(callBack);
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

				Project addPros = new Project();
				String name = nameText.getText();
				addPros.setName(name);
				boolean flag = proDao.add(addPros);
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
