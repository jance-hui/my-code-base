package view.DepView;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.DepartmentDao;

public class SuperDepartmentView {
	JFrame frame;
	JTextField nameText;
	JButton okBtn;
	DepartmentDao depDao = new DepartmentDao();

	public void init() {
		frame = new JFrame("������Ϣ");// �����顣1.����Ա����Ϣ��2.ȷ����ȡ����ť
		frame.setSize(280, 200);// ���ô��ڴ�С
		frame.setLocationRelativeTo(null);// ����
		JPanel mainPanel = (JPanel) frame.getContentPane();// ���帱���������mainAddPanel
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));

		JLabel nameLabel = new JLabel("���ƣ�");// ������ǩ
		mainPanel.add(nameLabel);// ���������ӵ����1
		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(100, 30));
		mainPanel.add(nameText);

		// ���Ӻ�ȡ����ť
		okBtn = new JButton("����");
		okBtn.setPreferredSize(new Dimension(60, 40));
		mainPanel.add(okBtn);
		frame.setVisible(true);
	}
}