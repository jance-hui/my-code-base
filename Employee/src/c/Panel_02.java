package c;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Panel_02 extends JPanel{
    private JLabel jLabel = null;
    private JButton but_02 = null;
    //˽�з���
    private Panel_02(JFrame jFrame){
        jLabel = new JLabel("����1");
        jLabel.setBounds(0, 0, 100, 100);
        but_02 = new JButton("����");
        //����¼�
        but_02.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==but_02){
                    jFrame.setContentPane(Panel_01.getInstance(jFrame));
                    jFrame.validate();//ˢ��
                }
            }
        });
        jLabel.setBounds(100, 100, 100, 100);
        add(but_02);
        add(jLabel);
    }
    private static Panel_02 panel_02=null;
    //����ӿ�
    public static Panel_02 getInstance(JFrame jFrame){
        panel_02 = new Panel_02(jFrame);
        return panel_02;
    }
}