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
		JFrame frame = new JFrame("项目管理");
		frame.setSize(500, 400);// 设置窗口大小
		frame.setLocationRelativeTo(null);// 窗口居中
		JPanel mainPanel = (JPanel) frame.getContentPane();// 定义主面板
		BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);// 纵向布局
		mainPanel.setLayout(boxLayout);

		// 2.设置面板：分为3大部分
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));// 居中，横向间距20，纵向间距10
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);
		// 第一部分
		JLabel nameLabel = new JLabel("部门名称");// 姓名标签
		panel1.add(nameLabel);// 将姓名添加到面板1
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(100, 30));
		panel1.add(nameText);
		nameText.setText(dep.getName());
		// 第二部分
		model = new Dep2ProTableModel(list);
		table = new JTable();
		refresh();
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);// 滚动条控件
		scrollPane.setPreferredSize(new Dimension(400, 200));// 设置滚动条大小
		panel2.add(scrollPane);// 将滚动条添加到面板2
		// 第三部分
		
		addBox.setPreferredSize(new Dimension(100, 30));
		JButton addBtn = new JButton("增加项目");
		JButton delBtn = new JButton("删除项目");
		panel3.add(addBox);
		panel3.add(addBtn);
		panel3.add(delBtn);
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Project addPro = new Project();
				addPro = addList.get(addBox.getSelectedIndex()-1);
				boolean flag = proDao.add(addPro, dep);
				if (flag) {
					JOptionPane.showMessageDialog(null, "添加成功");
				} else {
					JOptionPane.showMessageDialog(null, "添加失败");
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
						JOptionPane.showMessageDialog(null, "删除成功");
					} else {
						JOptionPane.showMessageDialog(null, "删除失败");
					}
					refresh();

				} else {
					JOptionPane.showMessageDialog(null, "请选择部门");
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
