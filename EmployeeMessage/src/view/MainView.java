package view;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import view.DepView.DepartmentView;
import view.EmpView.EmployeeView;
import view.ProView.ProjectView;
import view.ScoreView.ScoreView;

public class MainView {
	public void init() {
		JFrame frame = new JFrame("管理系统");
		frame.setSize(300, 200);
		frame.setLocationRelativeTo(null);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel panel = (JPanel)frame.getContentPane();
		panel.setLayout(new FlowLayout());
		
		JButton empBtn = new JButton("员工管理");
		empBtn.setPreferredSize(new Dimension(130, 70));
		JButton depBtn = new JButton("部门管理");
		depBtn.setPreferredSize(new Dimension(130, 70));
		JButton proBtn = new JButton("项目管理");
		proBtn.setPreferredSize(new Dimension(130, 70));
		JButton scoreBtn = new JButton("绩效管理");
		scoreBtn.setPreferredSize(new Dimension(130, 70));
		panel.add(empBtn);
		panel.add(depBtn);
		panel.add(proBtn);
		panel.add(scoreBtn);
		empBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new EmployeeView().init();
			}
		});
		depBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new DepartmentView().init();
			}
		});
		proBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ProjectView().init();
			}
		});
		scoreBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new ScoreView().init();
			}
		});
		frame.setVisible(true);
	}
	
	public static void main(String[] args) {
		MainView view = new MainView();
		view.init();
	}
}
