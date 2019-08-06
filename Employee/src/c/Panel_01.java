package c;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Panel_01 extends JPanel{
    private JButton but_01;
    private JPanel jPanel;
    private Panel_01(final JFrame jFrame){
        setLayout(null);
        System.out.println("正常");
        but_01 = new JButton("界面1");
        //点击事件
        but_01.addActionListener(new ActionListener() {
            
            @Override
            public void actionPerformed(ActionEvent e) {
                if(e.getSource()==but_01){
                    jFrame.setContentPane(Panel_02.getInstance(jFrame));
                    jFrame.validate();//使面板生效，刷新
                }
            }
        });
        but_01.setBounds(120, 45, 71, 28);
        jPanel = new JPanel();
        jPanel.setLayout(null);
        jPanel.setBounds(0, 76, 450, 224);
        jPanel.add(but_01);
        add(jPanel);
    }
    private static Panel_01 panel_01 = null;
    public static Panel_01 getInstance(JFrame jFrame){
        panel_01 = new Panel_01(jFrame);
        return panel_01;
    }
}
