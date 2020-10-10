package javabase.监听;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

class Event2 extends JFrame {
    private JButton btBlue, btDialog;
    // 构造方法
    public Event2() {
        setTitle("Java GUI 事件监听处理");
        setBounds(100, 100, 500, 350);
        setLayout(new FlowLayout());
        btBlue = new JButton("蓝色");
        btDialog = new JButton("弹窗");
        // 添加事件监听器对象(面向对象思想)
        btBlue.addActionListener(new ColorEventListener());
        btDialog.addActionListener(new DialogEventListener());

        add(btBlue);
        add(btDialog);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    // 内部类ColorEventListener，实现ActionListener接口
    class ColorEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            Container c = getContentPane();
            c.setBackground(Color.BLUE);
        }
    }
    // 内部类DialogEventListener，实现ActionListener接口
    class DialogEventListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            JDialog dialog = new JDialog();
            dialog.setBounds(300, 200, 400, 300);
            dialog.setVisible(true);
        }
    }
}
