package view.ProView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JOptionPane;

import entity.Project;
import util.CallBack;

public class AmendProjectView extends SuperProjectView {
	private CallBack callBack;
	private Project amendDep;
	private static AmendProjectView amendDeps;

	private AmendProjectView(Project amendEmp, CallBack callBack) {

		this.amendDep = amendEmp;
		this.callBack = callBack;
	}

	public static AmendProjectView getInstance(Project amendDep, CallBack callBack) {
		if (amendDeps == null) {
			amendDeps = new AmendProjectView(amendDep, callBack);
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
				boolean flag = proDao.amend(amendDep);
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
