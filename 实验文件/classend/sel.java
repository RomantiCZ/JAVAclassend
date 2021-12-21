package classend;

import com.mysql.jdbc.PreparedStatement;
import java.sql.*;
import javax.swing.*;
import javax.swing.event.AncestorEvent;
import javax.swing.event.AncestorListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseMotionAdapter;
import java.nio.ByteOrder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Vector;
import java.awt.event.*;

public class sel extends JFrame
{
    private Vector rowDate,columns;
    private  JPanel mianban;
    private JTable table;
    private JScrollPane jsp;
    private JButton bt_add,bt_edit,returnPage,bt_sub,cha;
    sel(){
        super("查询学生信息");
        this.setSize(800, 600);
        this.setLocation(600, 200);
        mianban=new JPanel(null);

        bt_edit=new JButton("点击即可修改学生信息");
        bt_edit.setBounds(120,10,200,50);

        bt_add=new JButton("添加");
        bt_add.setBounds(350,10,60,50);
        cha=new JButton("查询");
        cha.setBounds(450,10,60,50);

        bt_sub=new JButton("删除");
        bt_sub.setBounds(550,10,60,50);

        returnPage=new JButton("返回");
        returnPage.setBounds(625,10,60,50);

        showmes();
        mianban.add(bt_edit);

        mianban.add(bt_add);

        mianban.add(cha);

        mianban.add(bt_sub);
        mianban.add(returnPage);


        cha.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sel.super.dispose();
                new seldetail();
            }
        });

        bt_add.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                sel.super.dispose();
                new add();
            }
        });

        returnPage.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

                sel.super.dispose();
                new Manage();
            }
        });
        this.add(mianban);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setVisible(true);
    }

    private void showmes() {
        String url="jdbc:mysql://localhost:3306/classmanage?useUnicode=true&characterEncoding=utf8&useSSL=false";
        String basename="root";
        String pwd="123456";
        try
        {
            //列名
            columns=new Vector();
            String[] tbname={"学号","姓名","性别","生日","年龄","专业","宿舍"};
            columns.add("学号");columns.add("姓名");columns.add("性别");columns.add("生日");columns.add("年龄");columns.add("专业");columns.add("宿舍");

            //
            rowDate=new Vector();
            Class.forName("com.mysql.jdbc.Driver");
            Connection con= DriverManager.getConnection(url,basename,pwd);
            Statement sta=con.createStatement();
            String ml="select * from students";
            PreparedStatement pst= (PreparedStatement) con.prepareStatement(ml);
            ResultSet resset =  pst.executeQuery();
            while (resset.next())
            {
                Vector da=new Vector();
                da.add(resset.getString("num"));
                da.add(resset.getString("name"));
                da.add(resset.getString("gender"));
                da.add(resset.getString("birthday"));
                da.add(resset.getString("age"));
                da.add(resset.getString("major"));
                da.add(resset.getString("locate"));
                rowDate.add(da);
                table=new JTable(rowDate,columns);
                table.setFont(new Font("微软雅黑",Font.PLAIN,13));
                jsp=new JScrollPane(table);
                jsp.setBounds(100,70,600,485);
                mianban.add(jsp);
                table.addMouseListener(new MouseListener() {
                    @Override
                    public void mouseClicked(MouseEvent e) {
                        if (e.getClickCount()==1){
                            Point p = e.getPoint();
                            int row=table.rowAtPoint(p);
                            String snum=table.getValueAt(row,0).toString();
                            String sname=table.getValueAt(row,1).toString();
                            String ssex=table.getValueAt(row,2).toString();
                            String sbir=table.getValueAt(row,3).toString();
                            String sage=table.getValueAt(row,4).toString();
                            String smajor=table.getValueAt(row,5).toString();
                            String slocate=table.getValueAt(row,6).toString();
                            bt_edit.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    stuedit stu= new stuedit();
                                    stu.fnum.setText(snum);
                                    stu.fname.setText(sname);
                                    stu.fbirthday.setText(sbir);
                                    stu.fage.setText(sage);
                                    stu.fmajor.setText(smajor);
                                    stu.flocate.setText(slocate);
                                }
                            });

                            bt_sub.addActionListener(new ActionListener() {
                                @Override
                                public void actionPerformed(ActionEvent e) {
                                    String url="jdbc:mysql://localhost:3306/classmanage?useUnicode=true&characterEncoding=utf8&useSSL=false";
                                    String basename="root";
                                    String pwd="123456";
                                    try
                                    {
                                        Class.forName("com.mysql.jdbc.Driver");
                                        Connection con = DriverManager.getConnection(url, basename, pwd);
                                        Statement sta = con.createStatement();
                                        String ml="delete from students where num=? and name=? ";
                                        com.mysql.jdbc.PreparedStatement ps= (com.mysql.jdbc.PreparedStatement) con.prepareStatement(ml);
                                        ps.setString(1,snum);
                                        ps.setString(2,sname);
                                        int res=ps.executeUpdate();
                                        if (res>0){
                                            JOptionPane.showMessageDialog(null,"该生信息已删除");
                                               sel.super.dispose();
                                               new sel();

                                        }

                                    }catch (Exception error){
                                        System.out.println(error.getMessage());
                                        error.printStackTrace();
                                    }
                                }
                            });
                        }
                    }

                    @Override
                    public void mousePressed(MouseEvent e) {

                    }

                    @Override
                    public void mouseReleased(MouseEvent e) {

                    }

                    @Override
                    public void mouseEntered(MouseEvent e) {

                    }

                    @Override
                    public void mouseExited(MouseEvent e) {

                    }
                });
            }

        }catch (Exception error)
        {
            System.out.println(error.getMessage());
            error.printStackTrace();
        }

    }

    public static void main(String[] args) {
        sel se=new sel();
    }
}
