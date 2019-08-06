package b;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class amendEmployeeView extends SuperEmployeeView {
	private List<Employee> list;
	private CallBack callBack;
	private Employee amendEmp;
	private static amendEmployeeView amendEmps;

	private amendEmployeeView(List<Employee> list, Employee amendEmp, CallBack callBack) {
		this.list = list;
		this.amendEmp = amendEmp;
		this.callBack = callBack;
	}

	public static amendEmployeeView getInstance(List<Employee> list, Employee amendEmp, CallBack callBack) {
		if (amendEmps == null) {
			amendEmps = new amendEmployeeView(list, amendEmp, callBack);
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
		ageText.setText(amendEmp.getAge());
		sexBox.setSelectedItem(amendEmp.getSex());
		noText.setText(amendEmp.getNo());
		okBtn.addActionListener(new ActionListener() {// 保存修改信息
			public void actionPerformed(ActionEvent e) {

				amendEmp.setName(nameText.getText());
				amendEmp.setSex(sexBox.getSelectedItem().toString());
				amendEmp.setAge(ageText.getText());
				amendEmp.setNo(noText.getText());
				EmployeeDao empDao = new EmployeeDao();
				empDao.save(list);
				JOptionPane.showMessageDialog(null, "已保存");
				callBack.call();
				frame.dispose();
			}
		});
	}
}
