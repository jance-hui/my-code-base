package b;

import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class SuperEmployeeView {
	JFrame frame;
	JTextField nameText;
	JComboBox sexBox;
	JTextField ageText;
	JTextField noText ;
	JButton okBtn;

	public void init() {
		frame = new JFrame("员工信息");// 分两块。1.添加员工信息；2.确定，取消按钮
		frame.setSize(280, 420);// 设置窗口大小
		frame.setLocationRelativeTo(null);// 居中
		JPanel mainPanel = (JPanel) frame.getContentPane();// 定义副窗口主面板mainAddPanel
		mainPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));

		JLabel nameLabel = new JLabel("姓名：");// 姓名标签
		mainPanel.add(nameLabel);// 将姓名添加到面板1
		nameText = new JTextField();
		nameText.setPreferredSize(new Dimension(100, 30));// 设置text1 文本框的大小
		mainPanel.add(nameText);// 将text1添加到面板上1

		JLabel sexLabel = new JLabel("性别：");// 性别标签
		mainPanel.add(sexLabel);
		sexBox = new JComboBox();
		sexBox.setPreferredSize(new Dimension(100, 30));
		sexBox.addItem("男");
		sexBox.addItem("女");
		mainPanel.add(sexBox);

		JLabel ageLabel = new JLabel("年龄：");// 年龄标签
		mainPanel.add(ageLabel);
		ageText = new JTextField();
		ageText.setPreferredSize(new Dimension(100, 30));
		mainPanel.add(ageText);

		JLabel noLabel = new JLabel("工号：");// 工号标签
		mainPanel.add(noLabel);
		noText = new JTextField();
		noText.setPreferredSize(new Dimension(100, 30));
		mainPanel.add(noText);

		// 添加和取消按钮
		okBtn = new JButton("保存");
		okBtn.setPreferredSize(new Dimension(60, 40));
		mainPanel.add(okBtn);
		frame.setVisible(true);
	}
}
