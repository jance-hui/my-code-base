package ����;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.math.BigDecimal;

import javax.swing.*;

public class Test extends JFrame {

	String a2 = "";
	int pd;

	public Test() {
		Container c1 = getContentPane();
		getContentPane().setLayout(new FlowLayout());// ����
		JLabel jl1 = new JLabel("������Ҫ��Ӽ�����������");
		JLabel jl2 = new JLabel("ѡ������ķ�����");
		JLabel jl3 = new JLabel("�����");

		JTextField jt1 = new JTextField(10);
		JTextField jt2 = new JTextField(10);
		JTextField jt3 = new JTextField(20);
		jt3.setEditable(false);

		JButton jb = new JButton("����");
		jb.setBorderPainted(true);// ���ð�ť��ʾ�߽�

		JRadioButton jr1 = new JRadioButton("�ӷ�");// ��ѡ��ť
		JRadioButton jr2 = new JRadioButton("����");
		ButtonGroup group = new ButtonGroup();// û����䣬��ĵ�ѡ��ť�Ͳ��е�ѡ��ť�������Լ�ȥ������
		group.add(jr1);
		group.add(jr2);

		jb.addActionListener(// Ϊת����ť��Ӽ����¼�
				new ActionListener() {

					public void actionPerformed(ActionEvent arg0) {
						// �����Ի���
						String e1 = jt1.getText();// ���������
						String e2 = jt2.getText();
						int e11 = Integer.parseInt(e1);
						int e22 = Integer.parseInt(e2);
						if (pd == 1)// �ӷ�
						{
							int a = e11 + e22;
							a2 = String.valueOf(a);

						}

				else if (pd == 2) {
							int a = e11 - e22;
							a2 = String.valueOf(a);

						}

						jt3.setText(a2);
					}

				});

		jr1.addActionListener(//
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO �Զ����ɵķ������
						pd = 1;
					}
				});
		jr2.addActionListener(//
				new ActionListener() {

					@Override
					public void actionPerformed(ActionEvent arg0) {
						// TODO �Զ����ɵķ������
						pd = 2;
					}
				});

		c1.add(jl1);
		c1.add(jt1);
		c1.add(jt2);
		c1.add(jl2);
		c1.add(jr1);
		c1.add(jr2);
		c1.add(jl3);
		c1.add(jt3);
		c1.add(jb);
		setSize(420, 200);
		setVisible(true);
	}

	public static void main(String args[]) {
		new Test();
	}
}
