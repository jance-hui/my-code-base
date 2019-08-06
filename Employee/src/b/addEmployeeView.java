package b;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class addEmployeeView extends SuperEmployeeView {

	private List<Employee> list;
	private CallBack callBack;
	private static addEmployeeView addEmp;

	private addEmployeeView(List<Employee> list, CallBack callBack) {
		this.list = list;
		this.callBack = callBack;
	}

	public static addEmployeeView getInstance(List<Employee> list, CallBack callBack) {
		if (addEmp == null) {
			addEmp = new addEmployeeView(list, callBack);
		}
		return addEmp;
	}
	// public addEmployeeView(List<Employee> list, CallBack callBack) {
	// this.list = list;
	// this.callBack = callBack;
	// }

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

			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub

				Employee addEmps = new Employee();
				boolean addFlag = true;
				String strAddNo = noText.getText();// ��ȡ���ţ�����
				for (int i = 0; i < list.size(); i++) {
					Employee findNoEmp = new Employee();
					findNoEmp = list.get(i);
					if (findNoEmp.getNo().equals(strAddNo)) {// ��������ͬ�������´��ڣ������Ϣ
						JOptionPane.showMessageDialog(null, "�Բ����Ѿ��д˹���Ա�������������");
						addFlag = false;
						break;
					}
				}
				if (addFlag) {
					String name = nameText.getText();
					String sex = sexBox.getSelectedItem().toString();
					String age = ageText.getText();
					String no = noText.getText();
					addEmps.setName(name);
					addEmps.setSex(sex);
					addEmps.setAge(age);
					addEmps.setNo(no);
					list.add(addEmps);
					EmployeeDao empDao = new EmployeeDao();
					empDao.save(list);
					JOptionPane.showMessageDialog(null, "�ѱ���");
					callBack.call();
					addEmp = null;// �����һ����Ӻ��ٴ���ӻ���ʾ�ϴ���ӵ���Ϣ
					frame.dispose();
				}
			}
		});
	}
}
