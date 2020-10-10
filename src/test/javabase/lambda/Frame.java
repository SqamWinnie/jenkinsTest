package javabase.lambda;


/**
 * Created by 11861 on 2018/4/8.
 */

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Frame extends JFrame{
    public Frame(JButton button){
        setTitle("Java GUI 事件监听处理");
        setBounds(100, 100, 500, 350);
        setLayout(new FlowLayout());
        add(button);
        setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }
    public static void main(String[] args){
        JButton button =  new JButton("button");
        Frame frame_ = new Frame(button);
        // Java 8之前：
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                    System.out.println("普通事件监听");
            }
        });
        // Java 8之后：
        button.addActionListener((e) -> System.out.println("lambda 事件监听"));
    }
}
