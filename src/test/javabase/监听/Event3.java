package javabase.监听;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class Event3 extends JFrame {
    private JButton btBlue, btDialog;
    public Event3() {
        setTitle("Java GUI 事件监听处理");
        setBounds(100, 100, 500, 350);
        setLayout(new FlowLayout());
        btBlue = new JButton("蓝色");
        btDialog = new JButton("弹窗");
        // 添加事件监听器(此处即为匿名类)
        btBlue.addActionListener(new ActionListener() {
            // 事件处理
            @Override
            public void actionPerformed(ActionEvent e) {
                Container c = getContentPane();
                c.setBackground(Color.BLUE);
            }
        });
        // 并添加事件监听器
        btDialog.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JDialog dialog = new JDialog();
                dialog.setBounds(300, 200, 400, 300);
                dialog.setVisible(true);
            }
        });
        add(btBlue);
        add(btDialog);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}