package view.ProView;

import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import dao.ProjectDao;

public class SuperProjectView {
	JFrame frame;
	JTextField nameText;
	JButton okBtn;
	ProjectDao proDao = new ProjectDao();

	public void init() {
		frame = new JFrame("项目信息");// 分两块。1.添加员工信息；2.确定，取消按钮
		frame.setSize(280, 200);// 设置窗口大小
		frame.setLocationRelativeTo(null);// 居中
		JPanel mainPanel = (JPanel) frame.getContentPane();// 定义副窗口主面板mainAddPanel
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 20));

		JLabel nameLabel = new JLabel("名称：");// 姓名标签
		mainPanel.add(nameLabel);// 将姓名添加到面板1
		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(150, 30));
		mainPanel.add(nameText);

		// 添加和取消按钮
		okBtn = new JButton("保存");
		okBtn.setPreferredSize(new Dimension(60, 40));
		mainPanel.add(okBtn);
		frame.setVisible(true);
	}
}
