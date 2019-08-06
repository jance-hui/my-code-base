package view.ScoreView;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.DepartmentDao;
import dao.EmployeeDao;
import dao.ProjectDao;
import dao.ScoreDao;
import entity.Department;
import entity.Employee;
import entity.Project;
import entity.Score;

public class ScoreView {
	List<Score> list = new ArrayList<>();
	List<Department> depList;
	List<Project> proList;
	
	EmployeeDao empDao = new EmployeeDao();
	DepartmentDao depDao = new DepartmentDao();	
	ProjectDao proDao = new ProjectDao();
	ScoreDao scDao = new ScoreDao();
	
	JTable table;
	ScoreTableModel model;

	public void init() {

		// 1.�½�����
		JFrame frame = new JFrame("��Ч����ϵͳ");
		frame.setSize(600, 500);// ���ô��ڴ�С
		frame.setLocationRelativeTo(null);// ���ھ���
		JPanel mainPanel = (JPanel) frame.getContentPane();// ���������
		BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);// ���򲼾�
		mainPanel.setLayout(boxLayout);

		// 2.������壺��Ϊ3�󲿷�
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));// ���У�������20��������10
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);

		// 3.1����пؼ����
		// 3.1��һģ��
		JLabel nameLabel = new JLabel("����");// ������ǩ
		panel1.add(nameLabel);// ��������ӵ����1
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(60, 30));// ����text1 �ı���Ĵ�С
		panel1.add(nameText);// ��text1��ӵ������1

		JLabel depLabel = new JLabel("����");
		panel1.add(depLabel);
		JComboBox depBox = new JComboBox();
		depBox.setPreferredSize(new Dimension(70, 30));
		depBox.addItem("");
		depList = depDao.search();
		for(int i = 0;i<depList.size();i++) {
			depBox.addItem(depList.get(i).getName());
		}
		panel1.add(depBox);

		JLabel ageLabel = new JLabel("��Ŀ");
		panel1.add(ageLabel);
		JComboBox proBox = new JComboBox();
		proBox.setPreferredSize(new Dimension(100, 30));
		proBox.addItem("");
		proList = proDao.search();
		for(int i = 0;i<proList.size();i++) {
			proBox.addItem(proList.get(i).getName());
		}
		panel1.add(proBox);

		JButton findBtn = new JButton("����");// ���尴ť�����ҡ�
		findBtn.setPreferredSize(new Dimension(60, 40));
		panel1.add(findBtn);
		// ��Ӳ����¼�
		findBtn.addActionListener(new ActionListener() {// ������ң���ʼ���� ������Ϣ����
			public void actionPerformed(ActionEvent e) {
				String name = nameText.getText();
//				int depIndex = depBox.getSelectedIndex()-1;
//				int proIndex = proBox.getSelectedIndex()-1;
				Score sc = new Score();
				Employee emp = new Employee();
				Department dep = new Department();
				Project pro = new Project();
				emp.setName(name);
//				dep.setId(depIndex);
				dep.setName(depBox.getSelectedItem().toString());
//				pro.setId(proIndex);
				pro.setName(proBox.getSelectedItem().toString());
				emp.setDep(dep);
				sc.setEmp(emp);
				sc.setPro(pro);
				list = scDao.searchByCondition(sc);
				refreshTable(list);
			}
		});

		// 3.2�ڶ�ģ��
		model = new ScoreTableModel(list);
		table = new JTable();
		refreshTable();
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);// �������ؼ�
		scrollPane.setPreferredSize(new Dimension(500, 300));// ���ù�������С
		panel2.add(scrollPane);

		// 3.3����ģ��
		JButton saveBtn = new JButton("����");
		panel3.add(saveBtn);
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Set<Score> saveSet = model.getSaveSet();
				scDao.save(saveSet);
				refreshTable();
			}
		});
		
		frame.setVisible(true);
	}

	public void refreshTable() {
		list = scDao.search();
		model.setList(list);
		table.updateUI();
	}

	public void refreshTable(List<Score> list) {
		model.setList(list);
		table.updateUI();
	}
}
