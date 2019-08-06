package view.EmpView;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DepartmentDao;
import dao.EmployeeDao;
import entity.Department;

public class SuperEmployeeView {
	JFrame frame;
	JTextField nameText;
	JComboBox sexBox;
	JTextField ageText;
	JComboBox depBox;
	JButton okBtn;
	EmployeeDao empDao = new EmployeeDao();
	DepartmentDao depDao = new DepartmentDao();
	List<Department> depList;

	public void init() {
		frame = new JFrame("Ա����Ϣ");// �����顣1.���Ա����Ϣ��2.ȷ����ȡ����ť
		frame.setSize(280, 420);// ���ô��ڴ�С
		frame.setLocationRelativeTo(null);// ����
		JPanel mainPanel = (JPanel) frame.getContentPane();// ���帱���������mainAddPanel
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));

		JLabel nameLabel = new JLabel("������");// ������ǩ
		mainPanel.add(nameLabel);// ��������ӵ����1
		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(100, 30));// ����text1 �ı���Ĵ�С
		mainPanel.add(nameText);// ��text1��ӵ������1

		JLabel sexLabel = new JLabel("�Ա�");// �Ա��ǩ
		mainPanel.add(sexLabel);
		sexBox = new JComboBox();
		sexBox.setPreferredSize(new Dimension(100, 30));
		sexBox.addItem("��");
		sexBox.addItem("Ů");
		mainPanel.add(sexBox);

		JLabel ageLabel = new JLabel("���䣺");// �����ǩ
		mainPanel.add(ageLabel);
		ageText = new JTextField();
		ageText.setPreferredSize(new Dimension(100, 30));
		mainPanel.add(ageText);
		
		JLabel depLabel = new JLabel("���ţ�");// �����ǩ
		mainPanel.add(depLabel);
		depList = depDao.search();
		depBox = new JComboBox();
		depBox.setPreferredSize(new Dimension(100, 30));
		for(int i = 0;i<depList.size();i++) {
			depBox.addItem(depList.get(i).getName());
		}
		mainPanel.add(depBox);

		// ��Ӻ�ȡ����ť
		okBtn = new JButton("����");
		okBtn.setPreferredSize(new Dimension(60, 40));
		mainPanel.add(okBtn);
		frame.setVisible(true);
	}
}
