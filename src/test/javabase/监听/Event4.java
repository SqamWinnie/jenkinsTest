package javabase.监听;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Event4 extends JFrame {
    private JButton btBlue, btDialog;

    public Event4() {
        setTitle("Java GUI 事件监听处理");
        setBounds(100, 100, 500, 350);
        setLayout(new FlowLayout());
        btBlue = new JButton("蓝色");
        btDialog = new JButton("弹窗");
        // 将按钮添加事件监听器
        btBlue.addActionListener(new ColorEventListener(this));
        btDialog.addActionListener(new DialogEventListener());

        add(btBlue);
        add(btDialog);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
}

// 外部类ColorEventListener，实现ActionListener接口
class ColorEventListener implements ActionListener {
    private Event4 el;

    ColorEventListener(Event4 el) {
        this.el = el;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Container c = el.getContentPane();
        c.setBackground(Color.BLUE);
    }
}

// 外部类DialogEventListener，实现ActionListener接口
class DialogEventListener implements ActionListener {
    @Override
    public void actionPerformed(ActionEvent e) {
        JDialog dialog = new JDialog();
        dialog.setBounds(300, 200, 400, 300);
        dialog.setVisible(true);
    }
}

