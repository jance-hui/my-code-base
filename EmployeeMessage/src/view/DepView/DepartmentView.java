package view.DepView;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;

import dao.DepartmentDao;
import entity.Department;
import util.CallBack;
import view.DepView.Dep2ProView.Dep2ProView;

public class DepartmentView {
	JTable table;
	DepartmentTableModel model;
	List<Department> list = new ArrayList<>();
	DepartmentDao depDao = new DepartmentDao();

	public static void main(String[] args) {
		DepartmentView view = new DepartmentView();
		view.init();
	}

	public void init() {
		// 1.�½�����
		JFrame frame = new JFrame("���Ź���ϵͳ");
		frame.setSize(600, 500);// ���ô��ڴ�С
		frame.setLocationRelativeTo(null);// ���ھ���
		JPanel mainPanel = (JPanel) frame.getContentPane();// ���������
		BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);// ���򲼾�
		mainPanel.setLayout(boxLayout);

		// 2.������壺��Ϊ3�󲿷�
		JPanel panel1 = new JPanel();// ��һģ�飺�������Ա����䣬���ţ����Ұ�ť
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));// ���У�������20��������10
		JPanel panel2 = new JPanel();// �ڶ�ģ�飺������Ա����Ϣ
		JPanel panel3 = new JPanel();// ����ģ�飺��ӡ��޸ġ�ɾ����ť������
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 10));
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);

		// 3.1����пؼ����
		// 3.1��һģ��
		JLabel nameLabel = new JLabel("��������");// ������ǩ
		panel1.add(nameLabel);// ��������ӵ����1
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(100, 30));
		panel1.add(nameText);
		JLabel numLabel = new JLabel("��������");// ������ǩ
		panel1.add(numLabel);// ��������ӵ����1
		JTextField numText = new JTextField();
		numText.setPreferredSize(new Dimension(50, 30));
		panel1.add(numText);

		JButton findBtn = new JButton("����");// ���尴ť�����ҡ�
		findBtn.setPreferredSize(new Dimension(60, 40));
		panel1.add(findBtn);
		// ��Ӳ����¼�
		findBtn.addActionListener(new ActionListener() {// ������ң���ʼ���� ������Ϣ����
			public void actionPerformed(ActionEvent e) {
				Department dep = new Department();
				String name = nameText.getText();
				int num = -1;
				if (!numText.getText().equals("")) {
					num = Integer.parseInt(numText.getText());
				}
				dep.setName(name);
				dep.setEmp_count(num);
				list = depDao.searchByCondition(dep);
				refreshTable(list);

			}
		});

		// 3.2�ڶ�ģ��
		model = new DepartmentTableModel(list);
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
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddDepartmentView addInstance = AddDepartmentView.getInstance(new CallBack() {
					public void call() {
						refreshTable();
					}
				});
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
					Department amendDep = list.get(index);
					AmendDepartmentView amendInstance = AmendDepartmentView.getInstance(amendDep, new CallBack() {
						public void call() {
							refreshTable();
						}
					});
					amendInstance.findFrame();
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ����");
				}
			}
		});

		JButton deleteBtn = new JButton("ɾ��");// ���尴ť��ɾ����
		deleteBtn.setPreferredSize(new Dimension(60, 40));
		panel3.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});
		
		JButton proBtn = new JButton("��Ŀ����");
		panel3.add(proBtn);
		proBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index != -1) {
					Department dep = list.get(index);
					Dep2ProView proView = new Dep2ProView(dep);
					proView.init();
				} else {
					JOptionPane.showMessageDialog(null, "��ѡ����");
				}
				
			}
		});
		frame.setVisible(true);
	}

	public void delete() {// ɾ��
		int index = table.getSelectedRow();
		if (index > -1) {
			Department delDep = new Department();
			delDep = list.get(index);
			int option = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����", "ȷ��", JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				boolean flag = depDao.del(delDep);
				if (flag) {
					JOptionPane.showMessageDialog(null, "��ɾ��");
				} else {
					JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
				}
				refreshTable();
			}
		} else {
			JOptionPane.showMessageDialog(null, "��ѡ����");
		}

	}

	public void refreshTable() {
		list = depDao.search();
		model.setList(list);
		table.updateUI();
	}

	public void refreshTable(List<Department> list) {
		model.setList(list);
		table.updateUI();
	}
}
