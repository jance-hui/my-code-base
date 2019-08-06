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

import 字典.Dictionaries;

public class DictionaryView {
	List<Dictionaries> dic = new ArrayList<>();
	Scanner scanner = new Scanner(System.in);
	int flagInt = 0;

	public void init() {
		look();// 先读取
		// 1.设置主窗口
		JFrame mainFrame = new JFrame("字典");
		mainFrame.setSize(500, 250);
		mainFrame.setLocationRelativeTo(null);// 居中
		mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);// 关闭

		// 2.设置主面板
		JPanel mainPanel = (JPanel) mainFrame.getContentPane();
		BoxLayout boxLayout = new BoxLayout(mainPanel, BoxLayout.Y_AXIS);// 主面板纵向布局
		mainPanel.setLayout(boxLayout);

		// 3.两个副面板（查找。增改删）
		JPanel panel1 = new JPanel();
		panel1.setLayout(new FlowLayout(FlowLayout.CENTER, 100, 10));// 居中对齐，横间距20，纵间距10
		JPanel panel2 = new JPanel();
		panel2.setLayout(new FlowLayout(FlowLayout.CENTER, 70, 10));
		mainPanel.add(panel1);
		mainPanel.add(panel2);

		// 3.1.1副面板1 ：一个标签，一个文本框，一个查找按钮
		JLabel findLabel = new JLabel("请输入要查找的单词：");
		panel1.add(findLabel);
		JTextField findText = new JTextField();
		findText.setPreferredSize(new Dimension(80, 30));
		;
		panel1.add(findText);
		JButton findBtn = new JButton("查找");
		panel1.add(findBtn);

		// 3.1.2点击查找按钮：弹框
		findBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Dictionaries findDics = new Dictionaries();
				boolean flag = true;
				for (int i = 0; i < dic.size(); i++) {
					findDics = dic.get(i);
					if (findText.getText().equals(findDics.getName())) {
						flag = false;
						// 新建查找窗口
						JFrame findFrame = new JFrame("查找");
						findFrame.setSize(400, 180);
						findFrame.setLocationRelativeTo(null);
						// 定义查找主面板
						JPanel findMainPanel = (JPanel) findFrame.getContentPane();
						findMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));
						// 三个副面板：（1.单词、2.意义、3.返回键）
						JPanel findWordPanel = new JPanel();
						JPanel findMeanPanel = new JPanel();
						JPanel findBackPanel = new JPanel();
						findMainPanel.add(findWordPanel);
						findMainPanel.add(findMeanPanel);
						findMainPanel.add(findBackPanel);
						// 副面板1（一个标签，一个单词文本框）
						JLabel findWordLabel = new JLabel("单词：");
						findWordPanel.add(findWordLabel);
						JTextField findWordText = new JTextField();
						findWordText.setPreferredSize(new Dimension(80, 30));
						findWordPanel.add(findWordText);
						findWordText.setText(findDics.getName());
						// 副面板2（一个标签，一个意义文本框）
						JLabel findMeanLabel = new JLabel("意义：");
						findMeanPanel.add(findMeanLabel);
						JTextField findMeanText = new JTextField();
						findMeanText.setPreferredSize(new Dimension(300, 30));
						findMeanPanel.add(findMeanText);
						findMeanText.setText(findDics.getMean());
						// 副面板3（一个返回按钮）
						JButton findBackBtn = new JButton("返回");
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
					JOptionPane.showMessageDialog(null, "对不起，没有此单词");
				}
			}
		});

		// 3.2副面板2（增加按钮，修改按钮，删除按钮）
		JButton addBtn = new JButton("添加");
		JButton amendBtn = new JButton("修改");
		JButton delBtn = new JButton("删除");
		panel2.add(addBtn);
		panel2.add(amendBtn);
		panel2.add(delBtn);

		// 3.2.1添加操作，弹框，查重
		addBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 新建添加主窗口
				JFrame addFrame = new JFrame("添加新单词");
				addFrame.setSize(400, 190);
				addFrame.setLocationRelativeTo(null);
				// 主面板
				JPanel addMainPanel = (JPanel) addFrame.getContentPane();
				addMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));// 居左
				// 三个副面板（1.单词面板。2.意义面板。3.按钮面板）
				JPanel addWordPanel = new JPanel();
				JPanel addMeanPanel = new JPanel();
				JPanel addKeyPanel = new JPanel();
				addMainPanel.add(addWordPanel);
				addMainPanel.add(addMeanPanel);
				addMainPanel.add(addKeyPanel);
				// 副面板一（一个标签，一个文本框）
				JLabel addWordLabel = new JLabel("单词：");
				JTextField addWordText = new JTextField();
				addWordText.setPreferredSize(new Dimension(80, 30));
				addWordPanel.add(addWordLabel);
				addWordPanel.add(addWordText);
				// 副面板二（一个标签，一个文本框）
				JLabel addMeanLabel = new JLabel("意义：");
				JTextField addMeanText = new JTextField();
				addMeanText.setPreferredSize(new Dimension(300, 30));
				addMeanPanel.add(addMeanLabel);
				addMeanPanel.add(addMeanText);
				// 副面板二（两个按钮）
				JButton addTrueBtn = new JButton("添加");
				addTrueBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						Dictionaries addDics = new Dictionaries();
						boolean flag = true;
						for (int i = 0; i < dic.size(); i++) {
							Dictionaries findNameDics = new Dictionaries();
							findNameDics = dic.get(i);
							if (findNameDics.getName().equals(addWordText.getText())) {
								JOptionPane.showMessageDialog(null, "对不起，已有此单词");
								flag = false;
							}
						}
						if (flag) {
							addDics.setName(addWordText.getText());
							addDics.setMean(addMeanText.getText());
							dic.add(addDics);
							save();
							JOptionPane.showMessageDialog(null, "已添加");
							addFrame.dispose();
						}

					}
				});
				JButton addFalseBtn = new JButton("取消");
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
		// 3.2.2修改操作，查询有无次单词弹框，
		amendBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 新建x修改窗口
				JFrame amendFrame = new JFrame("修改单词");
				amendFrame.setSize(300, 150);
				amendFrame.setLocationRelativeTo(null);
				// 主面板（两个副面板）
				JPanel amendMainPanel = (JPanel) amendFrame.getContentPane();
				amendMainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));// 居中
				JPanel amendWordPanel = new JPanel();
				JPanel amendKeyPanel = new JPanel();
				amendMainPanel.add(amendWordPanel);
				amendMainPanel.add(amendKeyPanel);
				// 副面板一（一个标签、一个文本框）
				JLabel amendWordLabel = new JLabel("请输入要修改的单词：");
				JTextField amendWordText = new JTextField();
				amendWordText.setPreferredSize(new Dimension(80, 30));
				amendWordPanel.add(amendWordLabel);
				amendWordPanel.add(amendWordText);
				// 副面板二（一个按钮）
				JButton amendTrueBtn = new JButton("修改");
				amendTrueBtn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						boolean amendFlag = true;
						for(int i = 0; i<dic.size();i++) {
							if(dic.get(i).getName().equals(amendWordText.getText())) {
								amendFlag = false;
								flagInt = i;
								JFrame amendTrueFrame = new JFrame("确认修改");
								amendTrueFrame.setSize(400, 200);
								amendTrueFrame.setLocationRelativeTo(null);
								// 主面板、三个副面板（1.单词、2.意义、3.按钮）
								JPanel amendTrueMainPanel = (JPanel) amendTrueFrame.getContentPane();
								amendTrueMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));// 居左
								JPanel amendTruePanel1 = new JPanel();
								JPanel amendTruePanel2 = new JPanel();
								JPanel amendTruePanel3 = new JPanel();
								amendTrueMainPanel.add(amendTruePanel1);
								amendTrueMainPanel.add(amendTruePanel2);
								amendTrueMainPanel.add(amendTruePanel3);
								// 副面板一（单词标签）
								JLabel amendWordTrueLabel = new JLabel("单词：" );
								JTextField amendWordTrueText = new JTextField();
								amendWordTrueText.setPreferredSize(new Dimension(80, 30));
								amendTruePanel1.add(amendWordTrueLabel);
								amendTruePanel1.add(amendWordTrueText);
								// 副面板二（意义标签）
								JLabel amendMeanTrueLabel = new JLabel("意义：");
								JTextField amendMeanTrueText = new JTextField();
								amendMeanTrueText.setPreferredSize(new Dimension(300, 30));
								amendTruePanel2.add(amendMeanTrueLabel);
								amendTruePanel2.add(amendMeanTrueText);
								// 副面板三（两个按钮）
								JButton amendTrueTrueBtn = new JButton("确认修改");
								amendTrueTrueBtn.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										Dictionaries amendDics = new Dictionaries();
										amendDics = dic.get(flagInt);
										amendDics.setName(amendWordTrueText.getText());
										amendDics.setMean(amendMeanTrueText.getText());
										save();
										JOptionPane.showMessageDialog(null, "已修改");
										amendTrueFrame.dispose();
										amendFrame.dispose();

									}
								});
								JButton amendFalseTrueBtn = new JButton("取消");
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
							JOptionPane.showMessageDialog(null, "对不起，没有此单词，请重新输入");
						}
						
					}
				});
				JButton amendFalseBtn = new JButton("取消");
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
		// 3.2.3删除操作，查询有无次单词弹框，
		delBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// 新建删除窗口
				JFrame delFrame = new JFrame("删除单词");
				delFrame.setSize(300, 150);
				delFrame.setLocationRelativeTo(null);
				// 主面板（两个副面板）
				JPanel delMainPanel = (JPanel) delFrame.getContentPane();
				delMainPanel.setLayout(new FlowLayout(FlowLayout.CENTER));// 居中
				JPanel delWordPanel = new JPanel();
				JPanel delKeyPanel = new JPanel();
				delMainPanel.add(delWordPanel);
				delMainPanel.add(delKeyPanel);
				// 副面板一（一个标签、一个文本框）
				JLabel delWordLabel = new JLabel("请输入要删除的单词：");
				JTextField delWordText = new JTextField();
				delWordText.setPreferredSize(new Dimension(80, 30));
				delWordPanel.add(delWordLabel);
				delWordPanel.add(delWordText);
				// 副面板二（一个按钮）
				JButton delTrueBtn = new JButton("删除");
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
								// 确认删除窗口
								JFrame delTrueFrame = new JFrame("确认删除");
								delTrueFrame.setSize(250, 150);
								delTrueFrame.setLocationRelativeTo(null);
								// 主面板、三个副面板（1.单词、2.意义、3.按钮）
								JPanel delTrueMainPanel = (JPanel) delTrueFrame.getContentPane();
								delTrueMainPanel.setLayout(new FlowLayout(FlowLayout.LEFT));// 居左
								JPanel delTruePanel1 = new JPanel();
								JPanel delTruePanel2 = new JPanel();
								JPanel delTruePanel3 = new JPanel();
								delTrueMainPanel.add(delTruePanel1);
								delTrueMainPanel.add(delTruePanel2);
								delTrueMainPanel.add(delTruePanel3);
								// 副面板一（单词标签）
								JLabel delWordTrueLabel = new JLabel("单词：" + delDics.getName());
								delTruePanel1.add(delWordTrueLabel);
								// 副面板二（意义标签）
								JLabel delMeanTrueLabel = new JLabel("意义：" + delDics.getMean());
								delTruePanel2.add(delMeanTrueLabel);
								// 副面板三（两个按钮）
								JButton delTrueTrueBtn = new JButton("确认删除");
								delTrueTrueBtn.addActionListener(new ActionListener() {
									public void actionPerformed(ActionEvent e) {
										dic.remove(flagInt);
										save();
										JOptionPane.showMessageDialog(null, "已删除");
										delTrueFrame.dispose();
										delFrame.dispose();

									}
								});
								JButton delFalseTrueBtn = new JButton("取消");
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
							JOptionPane.showMessageDialog(null, "对不起没有此单词，请重新输入");
						}
					}
				});
				JButton delFalseBtn = new JButton("取消");
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

		mainFrame.setVisible(true);// 窗口可见

	}

	public void save() {// 保存文件
		try {
			FileOutputStream fos = new FileOutputStream("d:/字典.txt");
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

	public void look() {// 读取文件
		try {
			FileInputStream fis = new FileInputStream("d:/字典.txt");
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
