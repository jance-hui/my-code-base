package view.EmpView;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.DepartmentDao;
import dao.EmployeeDao;
import entity.Department;
import entity.Employee;
import util.CallBack;

public class EmployeeView {
	List<Employee> list = new ArrayList<>();
	EmployeeDao empDao = new EmployeeDao();
	JTable table;
	EmployeesTableModel model;
	DepartmentDao depDao = new DepartmentDao();
	List<Department> depList;

	public static void main(String[] args) {
		EmployeeView view = new EmployeeView();
		view.init();
	}

	public void init() {

		// 1.�½�����
		JFrame frame = new JFrame("Ա������ϵͳ");
		frame.setSize(600, 500);// ���ô��ڴ�С
		frame.setLocationRelativeTo(null);// ���ھ���
		JPanel mainPanel = (JPanel) frame.getContentPane();// ���������
		BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);// ���򲼾�
		mainPanel.setLayout(boxLayout);

		// 2.������壺��Ϊ3�󲿷�
		JPanel panel1 = new JPanel();// ��һģ�飺�������Ա����䣬���ţ����Ұ�ť
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));// ���У�������20��������10
		JPanel panel2 = new JPanel();// �ڶ�ģ�飺������Ա����Ϣ
		JPanel panel3 = new JPanel();// ����ģ�飺��ӡ��޸ġ�ɾ����ť������
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

		JLabel sexLabel = new JLabel("�Ա�");// �Ա��ǩ
		panel1.add(sexLabel);
		JComboBox sexBox = new JComboBox();
		sexBox.setPreferredSize(new Dimension(60, 30));
		sexBox.addItem("");
		sexBox.addItem("��");
		sexBox.addItem("Ů");
		panel1.add(sexBox);

		JLabel ageLabel = new JLabel("����");// �����ǩ
		panel1.add(ageLabel);
		JTextField ageText = new JTextField();
		ageText.setPreferredSize(new Dimension(60, 30));
		panel1.add(ageText);

		JLabel depLabel = new JLabel("����");// �Ա��ǩ
		panel1.add(depLabel);
		JComboBox depBox = new JComboBox();
		depBox.setPreferredSize(new Dimension(70, 30));
		depBox.addItem("");
		depList = depDao.search();
		for(int i = 0;i<depList.size();i++) {
			depBox.addItem(depList.get(i).getName());
		}
		panel1.add(depBox);

		JButton findBtn = new JButton("����");// ���尴ť�����ҡ�
		findBtn.setPreferredSize(new Dimension(60, 40));
		panel1.add(findBtn);
		// ��Ӳ����¼�
		findBtn.addActionListener(new ActionListener() {// ������ң���ʼ���� ������Ϣ����
			public void actionPerformed(ActionEvent e) {
				int age = -1;
				if (!ageText.getText().equals("")) {
					age = Integer.parseInt(ageText.getText());
				}
				String name = nameText.getText();
				String sex = sexBox.getSelectedItem().toString();
				int depIndex = depBox.getSelectedIndex();
				Employee emp = new Employee();
				emp.setName(name);
				emp.setSex(sex);
				emp.setAge(age);
				Department dep = new Department();
				dep.setId(depIndex);
				emp.setDep(dep);
				list = empDao.searchByCondition(emp);
				refreshTable(list);

			}
		});

		// 3.2�ڶ�ģ��
		model = new EmployeesTableModel(list);
		table = new JTable();
		refreshTable();
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);// �������ؼ�
		scrollPane.setPreferredSize(new Dimension(500, 300));// ���ù�������С
		panel2.add(scrollPane);// ����������ӵ����2

		// 3.3����ģ��
		JButton addBtn = new JButton("���");// ���尴ť����ӡ�
		addBtn.setPreferredSize(new Dimension(60, 40));
		panel3.add(addBtn);
		addBtn.addActionListener(new ActionListener() {// �����Ӱ�ť������ �����Ϣ����
			public void actionPerformed(ActionEvent e) {
				AddEmployeeView addInstance = AddEmployeeView.getInstance(new CallBack() {
					public void call() {
						refreshTable();
					}
				});
				// System.out.println(addInstance);
				addInstance.findFrame();

			}

		});

		JButton amendBtn = new JButton("�޸�");// ���尴ť���޸ġ�
		amendBtn.setPreferredSize(new Dimension(60, 40));
		panel3.add(amendBtn);
		amendBtn.addActionListener(new ActionListener() {// �޸���Ϣ����
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index != -1) {
					Employee amendEmp = list.get(index);
					AmendEmployeeView amendInstance = AmendEmployeeView.getInstance(amendEmp, new CallBack() {
						public void call() {
							refreshTable();
						}
					});
					amendInstance.findFrame();
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ��Ա��");
				}
			}
		});

		JButton deleteBtn = new JButton("ɾ��");// ���尴ť��ɾ����
		deleteBtn.setPreferredSize(new Dimension(60, 40));
		panel3.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				deleteBatch();
			}
		});
		frame.setVisible(true);
	}

	public void delete() {// ����ɾ��
		int index = table.getSelectedRow();
		if (index > -1) {
			int option = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����", "ȷ��", JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				Employee delEmp = new Employee();
				delEmp = list.get(index);
				boolean flag = empDao.del(delEmp);
				if (flag) {
					JOptionPane.showMessageDialog(null, "��ɾ��");
				} else {
					JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
				}
				refreshTable();
			}
		} else {
			JOptionPane.showMessageDialog(null, "��ѡ��Ա��");
		}

	}

	public void deleteBatch() {// ����ɾ��
		int[] indexs = table.getSelectedRows();
		if (indexs.length > 0) {
			int option = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����", "ȷ��", JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				for (int i = indexs.length - 1; i >= 0; i--) {
					Employee delEmp = new Employee();
					delEmp = list.get(indexs[i]);// temporaryList��list�е�ѡ�����ݶ�ɾ��
					boolean flag = empDao.del(delEmp);
					if (flag) {
						JOptionPane.showMessageDialog(null, "��ɾ��");
					} else {
						JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
					}
					refreshTable();
				}

			}
		} else {
			JOptionPane.showMessageDialog(null, "��ѡ��Ա��");
		}
	}

	public void refreshTable() {
		list = empDao.search();
		model.setList(list);
		table.updateUI();
	}

	public void refreshTable(List<Employee> list) {
		model.setList(list);
		table.updateUI();
	}
}
