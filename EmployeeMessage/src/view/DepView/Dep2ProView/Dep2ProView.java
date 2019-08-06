package view.DepView.Dep2ProView;

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

import dao.Dep2ProDao;
import entity.Department;
import entity.Project;

public class Dep2ProView {
	
	JTable table;
	Dep2ProTableModel model;
	List<Project> list = new ArrayList<>();
	Department dep;
	Dep2ProDao proDao = new Dep2ProDao();
	JComboBox addBox = new JComboBox();
	List<Project> addList;

	public Dep2ProView(Department dep) {
		this.dep = dep;
	}

	public void init() {
		JFrame frame = new JFrame("��Ŀ����");
		frame.setSize(500, 400);// ���ô��ڴ�С
		frame.setLocationRelativeTo(null);// ���ھ���
		JPanel mainPanel = (JPanel) frame.getContentPane();// ���������
		BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);// ���򲼾�
		mainPanel.setLayout(boxLayout);

		// 2.������壺��Ϊ3�󲿷�
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));// ���У�������20��������10
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		// ��һ����
		JLabel nameLabel = new JLabel("��������");// ������ǩ
		panel1.add(nameLabel);// ��������ӵ����1
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(100, 30));
		panel1.add(nameText);
		nameText.setText(dep.getName());
		// �ڶ�����
		model = new Dep2ProTableModel(list);
		table = new JTable();
		refresh();
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);// �������ؼ�
		scrollPane.setPreferredSize(new Dimension(400, 200));// ���ù�������С
		panel2.add(scrollPane);// ����������ӵ����2
		// ��������
		
		addBox.setPreferredSize(new Dimension(100, 30));
		JButton addBtn = new JButton("������Ŀ");
		JButton delBtn = new JButton("ɾ����Ŀ");
		panel3.add(addBox);
		panel3.add(addBtn);
		panel3.add(delBtn);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Project addPro = new Project();
				addPro = addList.get(addBox.getSelectedIndex()-1);
				boolean flag = proDao.add(addPro, dep);
				if (flag) {
					JOptionPane.showMessageDialog(null, "��ӳɹ�");
				} else {
					JOptionPane.showMessageDialog(null, "���ʧ��");
				}
				refresh();
			}
		});
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int index = table.getSelectedRow();
				if (index > -1) {
					Project pro = new Project();
					pro = list.get(index);
					boolean flag = proDao.del(pro, dep);
					if (flag) {
						JOptionPane.showMessageDialog(null, "ɾ���ɹ�");
					} else {
						JOptionPane.showMessageDialog(null, "ɾ��ʧ��");
					}
					refresh();

				} else {
					JOptionPane.showMessageDialog(null, "��ѡ����");
				}
			}
		});

		frame.setVisible(true);
	}

	public void refresh() {
		list = proDao.searchByDepartment(dep);
		addBox.removeAllItems();
		addBox.addItem("");
		addList = proDao.searchByNotDepartment(dep);
		for (int i = 0; i < addList.size(); i++) {
			addBox.addItem(addList.get(i).getName());
		}
		model.setList(list);
		table.updateUI();
	}

}
