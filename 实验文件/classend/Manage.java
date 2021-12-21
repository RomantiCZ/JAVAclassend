package classend;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Manage extends JFrame
{
    private JPanel mianban;
    private JLabel name;
    private JLabel chose;
    private JButton addstu;
    private JButton selstu;
    private JButton editmanage;
    private JButton exitman;
    Manage()
    {
        super("管理员管理");
        this.setSize(800, 600);
        this.setLocation(600, 200);
        mianban=new JPanel(null);

        name=new JLabel("管理员,你好!");
        name.setFont(new Font("微软雅黑",Font.BOLD,40));
        name.setBounds(250, 30, 250, 100);

        chose=new JLabel("请选择你的操作");
        chose.setFont(new Font("微软雅黑",Font.BOLD,20));
        chose.setBounds(300, 150, 180, 25);

        selstu=new JButton("查询学生信息");
        selstu.setBounds(150,250,150,50);

        addstu=new JButton("添加学生信息");
        addstu.setBounds(400,250,150,50);

        editmanage=new JButton("更改管理员信息");
        editmanage.setBounds(150,350,150,50);

        exitman =new JButton("注销管理员");
        exitman.setBounds(400,350,150,50);

        mianban.add(name);
        mianban.add(chose);
        mianban.add(selstu);
        mianban.add(addstu);
        mianban.add(editmanage);
        mianban.add(exitman);
        this.add(mianban);
        //对按钮的事件处理
        selstu.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manage.super.dispose();
                new sel();
            }
        });

         addstu.addActionListener(new ActionListener() {
             @Override
             public void actionPerformed(ActionEvent e) {
                 Manage.super.dispose();
                 new add();
             }

         });

        editmanage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Manage.super.dispose();
                new edit();
            }
        });

        exitman.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(null,"管理员注销成功");
                Manage.super.dispose();
                new ClassManage();
            }
        });


        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    public static void main(String[] args)
    {
        Manage manpeople=new Manage();
    }
}
