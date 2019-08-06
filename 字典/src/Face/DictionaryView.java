package Face;

import java.util.List;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Frame;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import �ֵ�.Dictionaries;

public class DictionaryView {
	List<Dictionaries> dic = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);
	int flagInt = 0;

	public void init() {
		look();// �ȶ�ȡ
		// 1.����������
		JFrame mainFrame = new JFrame("�ֵ�");
		mainFrame.setSize(500, 250);
		mainFrame.setLocationRelativeTo(null);// ����
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// �ر�

		// 2.���������
		JPanel mainPanel = (JPanel) mainFrame.getContentPane();
		BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);// ��������򲼾�
		mainPanel.setLayout(boxLayout);

		// 3.��������壨���ҡ�����ɾ��
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));// ���ж��룬����20���ݼ��10
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 10));
		mainPanel.add(panel1);
		mainPanel.add(panel2);

		// 3.1.1�����1 ��һ����ǩ��һ���ı���һ�����Ұ�ť
		JLabel findLabel = new JLabel("������Ҫ���ҵĵ��ʣ�");
		panel1.add(findLabel);
		JTextField findText = new JTextField();
		findText.setPreferredSize(new Dimension(80, 30));
		;
		panel1.add(findText);
		JButton findBtn = new JButton("����");
		panel1.add(findBtn);

		// 3.1.2������Ұ�ť������
		findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dictionaries findDics = new Dictionaries();
				boolean flag = true;
				for (int i = 0; i < dic.size(); i++) {
					findDics = dic.get(i);
					if (findText.getText().equals(findDics.getName())) {
						flag = false;
						// �½����Ҵ���
						JFrame findFrame = new JFrame("����");
						findFrame.setSize(400, 180);
						findFrame.setLocationRelativeTo(null);
						// ������������
						JPanel findMainPanel = (JPanel) findFrame.getContentPane();
						findMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
						// ��������壺��1.���ʡ�2.���塢3.���ؼ���
						JPanel findWordPanel = new JPanel();
						JPanel findMeanPanel = new JPanel();
						JPanel findBackPanel = new JPanel();
						findMainPanel.add(findWordPanel);
						findMainPanel.add(findMeanPanel);
						findMainPanel.add(findBackPanel);
						// �����1��һ����ǩ��һ�������ı���
						JLabel findWordLabel = new JLabel("���ʣ�");
						findWordPanel.add(findWordLabel);
						JTextField findWordText = new JTextField();
						findWordText.setPreferredSize(new Dimension(80, 30));
						findWordPanel.add(findWordText);
						findWordText.setText(findDics.getName());
						// �����2��һ����ǩ��һ�������ı���
						JLabel findMeanLabel = new JLabel("���壺");
						findMeanPanel.add(findMeanLabel);
						JTextField findMeanText = new JTextField();
						findMeanText.setPreferredSize(new Dimension(300, 30));
						findMeanPanel.add(findMeanText);
						findMeanText.setText(findDics.getMean());
						// �����3��һ�����ذ�ť��
						JButton findBackBtn = new JButton("����");
						findBackPanel.add(findBackBtn);
						findBackBtn.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								findFrame.dispose();
							}
						});

						findFrame.setVisible(true);
					}
				}
				if (flag) {
					JOptionPane.showMessageDialog(null, "�Բ���û�д˵���");
				}
			}
		});

		// 3.2�����2�����Ӱ�ť���޸İ�ť��ɾ����ť��
		JButton addBtn = new JButton("���");
		JButton amendBtn = new JButton("�޸�");
		JButton delBtn = new JButton("ɾ��");
		panel2.add(addBtn);
		panel2.add(amendBtn);
		panel2.add(delBtn);

		// 3.2.1��Ӳ��������򣬲���
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �½����������
				JFrame addFrame = new JFrame("����µ���");
				addFrame.setSize(400, 190);
				addFrame.setLocationRelativeTo(null);
				// �����
				JPanel addMainPanel = (JPanel) addFrame.getContentPane();
				addMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));// ����
				// ��������壨1.������塣2.������塣3.��ť��壩
				JPanel addWordPanel = new JPanel();
				JPanel addMeanPanel = new JPanel();
				JPanel addKeyPanel = new JPanel();
				addMainPanel.add(addWordPanel);
				addMainPanel.add(addMeanPanel);
				addMainPanel.add(addKeyPanel);
				// �����һ��һ����ǩ��һ���ı���
				JLabel addWordLabel = new JLabel("���ʣ�");
				JTextField addWordText = new JTextField();
				addWordText.setPreferredSize(new Dimension(80, 30));
				addWordPanel.add(addWordLabel);
				addWordPanel.add(addWordText);
				// ��������һ����ǩ��һ���ı���
				JLabel addMeanLabel = new JLabel("���壺");
				JTextField addMeanText = new JTextField();
				addMeanText.setPreferredSize(new Dimension(300, 30));
				addMeanPanel.add(addMeanLabel);
				addMeanPanel.add(addMeanText);
				// ��������������ť��
				JButton addTrueBtn = new JButton("���");
				addTrueBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Dictionaries addDics = new Dictionaries();
						boolean flag = true;
						for (int i = 0; i < dic.size(); i++) {
							Dictionaries findNameDics = new Dictionaries();
							findNameDics = dic.get(i);
							if (findNameDics.getName().equals(addWordText.getText())) {
								JOptionPane.showMessageDialog(null, "�Բ������д˵���");
								flag = false;
							}
						}
						if (flag) {
							addDics.setName(addWordText.getText());
							addDics.setMean(addMeanText.getText());
							dic.add(addDics);
							save();
							JOptionPane.showMessageDialog(null, "�����");
							addFrame.dispose();
						}

					}
				});
				JButton addFalseBtn = new JButton("ȡ��");
				addFalseBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						addFrame.dispose();
					}
				});
				addKeyPanel.add(addTrueBtn);
				addKeyPanel.add(addFalseBtn);

				addFrame.setVisible(true);

			}
		});
		// 3.2.2�޸Ĳ�������ѯ���޴ε��ʵ���
		amendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �½�x�޸Ĵ���
				JFrame amendFrame = new JFrame("�޸ĵ���");
				amendFrame.setSize(300, 150);
				amendFrame.setLocationRelativeTo(null);
				// ����壨��������壩
				JPanel amendMainPanel = (JPanel) amendFrame.getContentPane();
				amendMainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));// ����
				JPanel amendWordPanel = new JPanel();
				JPanel amendKeyPanel = new JPanel();
				amendMainPanel.add(amendWordPanel);
				amendMainPanel.add(amendKeyPanel);
				// �����һ��һ����ǩ��һ���ı���
				JLabel amendWordLabel = new JLabel("������Ҫ�޸ĵĵ��ʣ�");
				JTextField amendWordText = new JTextField();
				amendWordText.setPreferredSize(new Dimension(80, 30));
				amendWordPanel.add(amendWordLabel);
				amendWordPanel.add(amendWordText);
				// ��������һ����ť��
				JButton amendTrueBtn = new JButton("�޸�");
				amendTrueBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean amendFlag = true;
						for(int i = 0; i<dic.size();i++) {
							if(dic.get(i).getName().equals(amendWordText.getText())) {
								amendFlag = false;
								flagInt = i;
								JFrame amendTrueFrame = new JFrame("ȷ���޸�");
								amendTrueFrame.setSize(400, 200);
								amendTrueFrame.setLocationRelativeTo(null);
								// ����塢��������壨1.���ʡ�2.���塢3.��ť��
								JPanel amendTrueMainPanel = (JPanel) amendTrueFrame.getContentPane();
								amendTrueMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));// ����
								JPanel amendTruePanel1 = new JPanel();
								JPanel amendTruePanel2 = new JPanel();
								JPanel amendTruePanel3 = new JPanel();
								amendTrueMainPanel.add(amendTruePanel1);
								amendTrueMainPanel.add(amendTruePanel2);
								amendTrueMainPanel.add(amendTruePanel3);
								// �����һ�����ʱ�ǩ��
								JLabel amendWordTrueLabel = new JLabel("���ʣ�" );
								JTextField amendWordTrueText = new JTextField();
								amendWordTrueText.setPreferredSize(new Dimension(80, 30));
								amendTruePanel1.add(amendWordTrueLabel);
								amendTruePanel1.add(amendWordTrueText);
								// �������������ǩ��
								JLabel amendMeanTrueLabel = new JLabel("���壺");
								JTextField amendMeanTrueText = new JTextField();
								amendMeanTrueText.setPreferredSize(new Dimension(300, 30));
								amendTruePanel2.add(amendMeanTrueLabel);
								amendTruePanel2.add(amendMeanTrueText);
								// ���������������ť��
								JButton amendTrueTrueBtn = new JButton("ȷ���޸�");
								amendTrueTrueBtn.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										Dictionaries amendDics = new Dictionaries();
										amendDics = dic.get(flagInt);
										amendDics.setName(amendWordTrueText.getText());
										amendDics.setMean(amendMeanTrueText.getText());
										save();
										JOptionPane.showMessageDialog(null, "���޸�");
										amendTrueFrame.dispose();
										amendFrame.dispose();

									}
								});
								JButton amendFalseTrueBtn = new JButton("ȡ��");
								amendFalseTrueBtn.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										amendTrueFrame.dispose();
										amendFrame.dispose();
									}
								});
								amendTruePanel3.add(amendTrueTrueBtn);
								amendTruePanel3.add(amendFalseTrueBtn);
								amendTrueFrame.setVisible(true);
								
							}
						}
						if(amendFlag) {
							JOptionPane.showMessageDialog(null, "�Բ���û�д˵��ʣ�����������");
						}
						
					}
				});
				JButton amendFalseBtn = new JButton("ȡ��");
				amendFalseBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						amendFrame.dispose();
					}
				});
				amendKeyPanel.add(amendTrueBtn);
				amendKeyPanel.add(amendFalseBtn);
				amendFrame.setVisible(true);
			}
		});
		// 3.2.3ɾ����������ѯ���޴ε��ʵ���
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// �½�ɾ������
				JFrame delFrame = new JFrame("ɾ������");
				delFrame.setSize(300, 150);
				delFrame.setLocationRelativeTo(null);
				// ����壨��������壩
				JPanel delMainPanel = (JPanel) delFrame.getContentPane();
				delMainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));// ����
				JPanel delWordPanel = new JPanel();
				JPanel delKeyPanel = new JPanel();
				delMainPanel.add(delWordPanel);
				delMainPanel.add(delKeyPanel);
				// �����һ��һ����ǩ��һ���ı���
				JLabel delWordLabel = new JLabel("������Ҫɾ���ĵ��ʣ�");
				JTextField delWordText = new JTextField();
				delWordText.setPreferredSize(new Dimension(80, 30));
				delWordPanel.add(delWordLabel);
				delWordPanel.add(delWordText);
				// ��������һ����ť��
				JButton delTrueBtn = new JButton("ɾ��");
				delTrueBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						// int delInt = 0;
						Dictionaries delDics = new Dictionaries();
						boolean delFlag = true;
						for (int i = 0; i < dic.size(); i++) {
							delDics = dic.get(i);
							if (delDics.getName().equals(delWordText.getText())) {
								delFlag = false;
								flagInt = i;
								// ȷ��ɾ������
								JFrame delTrueFrame = new JFrame("ȷ��ɾ��");
								delTrueFrame.setSize(250, 150);
								delTrueFrame.setLocationRelativeTo(null);
								// ����塢��������壨1.���ʡ�2.���塢3.��ť��
								JPanel delTrueMainPanel = (JPanel) delTrueFrame.getContentPane();
								delTrueMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));// ����
								JPanel delTruePanel1 = new JPanel();
								JPanel delTruePanel2 = new JPanel();
								JPanel delTruePanel3 = new JPanel();
								delTrueMainPanel.add(delTruePanel1);
								delTrueMainPanel.add(delTruePanel2);
								delTrueMainPanel.add(delTruePanel3);
								// �����һ�����ʱ�ǩ��
								JLabel delWordTrueLabel = new JLabel("���ʣ�" + delDics.getName());
								delTruePanel1.add(delWordTrueLabel);
								// �������������ǩ��
								JLabel delMeanTrueLabel = new JLabel("���壺" + delDics.getMean());
								delTruePanel2.add(delMeanTrueLabel);
								// ���������������ť��
								JButton delTrueTrueBtn = new JButton("ȷ��ɾ��");
								delTrueTrueBtn.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										dic.remove(flagInt);
										save();
										JOptionPane.showMessageDialog(null, "��ɾ��");
										delTrueFrame.dispose();
										delFrame.dispose();

									}
								});
								JButton delFalseTrueBtn = new JButton("ȡ��");
								delFalseTrueBtn.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										delTrueFrame.dispose();
										delFrame.dispose();
									}
								});
								delTruePanel3.add(delTrueTrueBtn);
								delTruePanel3.add(delFalseTrueBtn);
								delTrueFrame.setVisible(true);
							}
						}
						if (delFlag) {
							JOptionPane.showMessageDialog(null, "�Բ���û�д˵��ʣ�����������");
						}
					}
				});
				JButton delFalseBtn = new JButton("ȡ��");
				delFalseBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						delFrame.dispose();
					}
				});
				delKeyPanel.add(delTrueBtn);
				delKeyPanel.add(delFalseBtn);

				delFrame.setVisible(true);
			}
		});

		mainFrame.setVisible(true);// ���ڿɼ�

	}

	public void save() {// �����ļ�
		try {
			FileOutputStream fos = new FileOutputStream("d:/�ֵ�.txt");
			ObjectOutputStream oos = new ObjectOutputStream(fos);
			oos.writeObject(dic);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public void look() {// ��ȡ�ļ�
		try {
			FileInputStream fis = new FileInputStream("d:/�ֵ�.txt");
			ObjectInputStream ois = new ObjectInputStream(fis);
			dic = (ArrayList<Dictionaries>) ois.readObject();
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		DictionaryView dictionaryView = new DictionaryView();
		dictionaryView.init();
	}
}
