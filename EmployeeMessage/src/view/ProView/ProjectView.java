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
		// 1.新建窗口
		JFrame frame = new JFrame("项目管理系统");
		frame.setSize(600, 500);// 设置窗口大小
		frame.setLocationRelativeTo(null);// 窗口居中
		JPanel mainPanel = (JPanel) frame.getContentPane();// 定义主面板
		BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);// 纵向布局
		mainPanel.setLayout(boxLayout);

		// 2.设置面板：分为3大部分
		JPanel panel1 = new JPanel();// 第一模块：姓名，性别，年龄，工号，查找按钮
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));// 居中，横向间距20，纵向间距10
		JPanel panel2 = new JPanel();// 第二模块：表格输出员工信息
		JPanel panel3 = new JPanel();// 第三模块：添加、修改、删除按钮，弹框
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 60, 10));
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);

		// 3.1面板中控件设计
		// 3.1第一模块
		JLabel nameLabel = new JLabel("项目名称");// 姓名标签
		panel1.add(nameLabel);// 将姓名添加到面板1
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(100, 30));
		panel1.add(nameText);

		JButton findBtn = new JButton("查找");// 定义按钮“查找”
		findBtn.setPreferredSize(new Dimension(60, 40));
		panel1.add(findBtn);
		// 添加查找事件
		findBtn.addActionListener(new ActionListener() {// 点击查找，开始监听 查找信息窗口
			public void actionPerformed(ActionEvent e) {
				Project pro = new Project();
				String name = nameText.getText();				
				pro.setName(name);
				list = proDao.searchByCondition(pro);
				refreshTable(list);

			}
		});

		// 3.2第二模块
		model = new ProjectTableModel(list);
		table = new JTable();
		refreshTable();
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);// 滚动条控件
		scrollPane.setPreferredSize(new Dimension(500, 300));// 设置滚动条大小
		panel2.add(scrollPane);// 将滚动条添加到面板2

		// 3.3第三模块
		JButton addBtn = new JButton("添加");// 定义按钮“添加”
		addBtn.setPreferredSize(new Dimension(60, 40));
		panel3.add(addBtn);
		addBtn.addActionListener(new ActionListener() {// 点击添加按钮，监听 添加信息窗口
			public void actionPerformed(ActionEvent e) {
				AddProjectView addInstance = AddProjectView.getInstance(new CallBack() {
					public void call() {
						refreshTable();
					}
				});
				addInstance.findFrame();

			}

		});

		JButton amendBtn = new JButton("修改");// 定义按钮“修改”
		amendBtn.setPreferredSize(new Dimension(60, 40));
		panel3.add(amendBtn);
		amendBtn.addActionListener(new ActionListener() {// 修改信息窗口
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
					JOptionPane.showMessageDialog(null, "请选择部门");
				}
			}
		});

		JButton deleteBtn = new JButton("删除");// 定义按钮“删除”
		deleteBtn.setPreferredSize(new Dimension(60, 40));
		panel3.add(deleteBtn);
		deleteBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				delete();
			}
		});

		frame.setVisible(true);
	}

	public void delete() {// 单个删除
		int index = table.getSelectedRow();
		if (index > -1) {
			Project delPro = new Project();
			delPro = list.get(index);
			int option = JOptionPane.showConfirmDialog(null, "确认删除吗", "确认", JOptionPane.YES_NO_OPTION);
			if (option == 0) {
				boolean flag = proDao.del(delPro);
				if (flag) {
					JOptionPane.showMessageDialog(null, "已删除");
				} else {
					JOptionPane.showMessageDialog(null, "删除失败");
				}
				refreshTable();
			}
		} else {
			JOptionPane.showMessageDialog(null, "请选择部门");
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
