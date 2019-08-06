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

		// 1.新建窗口
		JFrame frame = new JFrame("绩效管理系统");
		frame.setSize(600, 500);// 设置窗口大小
		frame.setLocationRelativeTo(null);// 窗口居中
		JPanel mainPanel = (JPanel) frame.getContentPane();// 定义主面板
		BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);// 纵向布局
		mainPanel.setLayout(boxLayout);

		// 2.设置面板：分为3大部分
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 15, 10));// 居中，横向间距20，纵向间距10
		JPanel panel2 = new JPanel();
		JPanel panel3 = new JPanel();
		panel3.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));
		mainPanel.add(panel1);
		mainPanel.add(panel2);
		mainPanel.add(panel3);

		// 3.1面板中控件设计
		// 3.1第一模块
		JLabel nameLabel = new JLabel("姓名");// 姓名标签
		panel1.add(nameLabel);// 将姓名添加到面板1
		JTextField nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(60, 30));// 设置text1 文本框的大小
		panel1.add(nameText);// 将text1添加到面板上1

		JLabel depLabel = new JLabel("部门");
		panel1.add(depLabel);
		JComboBox depBox = new JComboBox();
		depBox.setPreferredSize(new Dimension(70, 30));
		depBox.addItem("");
		depList = depDao.search();
		for(int i = 0;i<depList.size();i++) {
			depBox.addItem(depList.get(i).getName());
		}
		panel1.add(depBox);

		JLabel ageLabel = new JLabel("项目");
		panel1.add(ageLabel);
		JComboBox proBox = new JComboBox();
		proBox.setPreferredSize(new Dimension(100, 30));
		proBox.addItem("");
		proList = proDao.search();
		for(int i = 0;i<proList.size();i++) {
			proBox.addItem(proList.get(i).getName());
		}
		panel1.add(proBox);

		JButton findBtn = new JButton("查找");// 定义按钮“查找”
		findBtn.setPreferredSize(new Dimension(60, 40));
		panel1.add(findBtn);
		// 添加查找事件
		findBtn.addActionListener(new ActionListener() {// 点击查找，开始监听 查找信息窗口
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

		// 3.2第二模块
		model = new ScoreTableModel(list);
		table = new JTable();
		refreshTable();
		table.setModel(model);
		JScrollPane scrollPane = new JScrollPane(table);// 滚动条控件
		scrollPane.setPreferredSize(new Dimension(500, 300));// 设置滚动条大小
		panel2.add(scrollPane);

		// 3.3第三模块
		JButton saveBtn = new JButton("保存");
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
