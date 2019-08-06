package view.ProView;

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

import dao.ProjectDao;
import entity.Project;
import util.CallBack;

public class ProjectView {
	JTable table;
	ProjectTableModel model;
	List<Project> list = new ArrayList<>();
	ProjectDao proDao = new ProjectDao();

	public static void main(String[] args) {
		ProjectView view = new ProjectView();
		view.init();
	}

	public void init() {
		// 1.�½�����
		JFrame frame = new JFrame("��Ŀ����ϵͳ");
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
		JLabel nameLabel = new JLabel("��Ŀ����");// ������ǩ
		panel1.add(nameLabel);// ��������ӵ����1
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(100, 30));
		panel1.add(nameText);

		JButton findBtn = new JButton("����");// ���尴ť�����ҡ�
		findBtn.setPreferredSize(new Dimension(60, 40));
		panel1.add(findBtn);
		// ��Ӳ����¼�
		findBtn.addActionListener(new ActionListener() {// ������ң���ʼ���� ������Ϣ����
			public void actionPerformed(ActionEvent e) {
				Project pro = new Project();
				String name = nameText.getText();				
				pro.setName(name);
				list = proDao.searchByCondition(pro);
				refreshTable(list);

			}
		});

		// 3.2�ڶ�ģ��
		model = new ProjectTableModel(list);
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
				AddProjectView addInstance = AddProjectView.getInstance(new CallBack() {
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
					Project amendPro = list.get(index);
					AmendProjectView amendInstance = AmendProjectView.getInstance(amendPro, new CallBack() {
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

		frame.setVisible(true);
	}

	public void delete() {// ����ɾ��
		int index = table.getSelectedRow();
		if (index > -1) {
			Project delPro = new Project();
			delPro = list.get(index);
			int option = JOptionPane.showConfirmDialog(null, "ȷ��ɾ����", "ȷ��", JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				boolean flag = proDao.del(delPro);
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
		list = proDao.search();
		model.setList(list);
		table.updateUI();
	}

	public void refreshTable(List<Project> list) {
		model.setList(list);
		table.updateUI();
	}
}
