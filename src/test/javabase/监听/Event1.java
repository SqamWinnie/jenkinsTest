package javabase.监听;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Event1 extends JFrame implements ActionListener {
    private JButton btBlue, btDialog;
    public Event1() {
        setTitle("Java GUI 事件监听处理");
        setBounds(100, 100, 500, 350);
        setLayout(new FlowLayout());
        btBlue = new JButton("蓝色");
        btDialog = new JButton("弹窗");
        // 将按钮添加事件监听器
        btBlue.addActionListener(this);
        btDialog.addActionListener(this);
        add(btBlue);
        add(btDialog);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btBlue) {
            Container c = getContentPane();
            c.setBackground(Color.BLUE);
        }
        else if (e.getSource() == btDialog) {
            JDialog dialog = new JDialog();
            dialog.setBounds(300, 200, 400, 300);
            dialog.setVisible(true);
        }
    }
}

